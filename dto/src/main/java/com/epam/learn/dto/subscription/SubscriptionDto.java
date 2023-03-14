package com.epam.learn.dto.subscription;

import com.epam.learn.dto.user.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table(name = "subscriptions")
@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubscriptionDto {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @ManyToOne
  private UserDto user;
  @Column
  @Builder.Default
  private LocalDate startDate = LocalDate.now();
}
