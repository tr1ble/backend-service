package com.epam.learn.service.impl;

import com.epam.learn.model.Role;
import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.mapper.UserMapper;
import com.epam.learn.messaging.producer.UserProducer;
import com.epam.learn.repository.RoleRepository;
import com.epam.learn.repository.UserRepository;
import com.epam.learn.service.SecurityService;
import com.epam.learn.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String DEFAULT_ROLE = "ROLE_USER";
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;
  private final UserProducer userProducer;
  private final PasswordEncoder passwordEncoder;
  private final SecurityService securityService;

  @Override
  public UserResponseDto create(UserRequestDto userRequest) {
    User mappedUser = userMapper.mapToDomain(userRequest);
    mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
    Role defaultRole = roleRepository.findByName(DEFAULT_ROLE);
    Set<Role> roles = mappedUser.getRoles();
    roles.add(defaultRole);
    mappedUser.setRoles(roles);
    User user = userRepository.save(mappedUser);
    return userMapper.mapToResponse(user);
  }

  @Override
  public List<UserResponseDto> getById(List<UUID> ids) {
    Iterable<User> users = userRepository.findAllById(ids);
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(userMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserResponseDto> getAll() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(userMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public UserResponseDto getById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return userMapper.mapToResponse(user);
  }

  @Override
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserResponseDto update(UserRequestDto user) {
    User userDto = userMapper.mapToDomain(user);
    return userMapper.mapToResponse(userRepository.save(userDto));
  }

  @Override
  public void send(UUID id) {
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    userProducer.produceUser(userMapper.mapToAvro(user));
  }

  @Override
  public User getDtoById(UUID id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    if(securityService.isBlocked(username)) {
      throw new LockedException("blocked");
    }
    User user = userRepository.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException(username);

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : user.getRoles()){
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}
