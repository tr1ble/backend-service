package com.epam.learn.mapper;

import com.epam.learn.config.MappersConfig;
import com.epam.learn.dto.subscription.SubscriptionDto;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MappersConfig.class)
public abstract class SubscriptionMapper {
  @Autowired
  protected UserService userService;
  @Mapping(target = "startDate", ignore = true)
  @Mapping(target = "user", expression = "java(userService.getDtoById(source.getUserId()))")
  public abstract SubscriptionDto mapRequestToDomain(SubscriptionRequestDto source);
  @Mapping(target = "userId", expression = "java(source.getUser().getId())")
  public abstract SubscriptionResponseDto mapDomainToResponse(SubscriptionDto source);
}
