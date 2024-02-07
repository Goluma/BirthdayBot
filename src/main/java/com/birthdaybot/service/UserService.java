package com.birthdaybot.service;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.domain.entitiy.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserEntity saveUser(UserEntity userEntity);

    Optional<UserEntity> findUserById(Long userId);

    void delete(Long id);

    void addReminderToUser(ReminderEntity reminderEntity, Long id);

    UserEntity findById(UUID userId);
}
