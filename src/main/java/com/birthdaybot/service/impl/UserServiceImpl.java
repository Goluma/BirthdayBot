package com.birthdaybot.service.impl;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.domain.entitiy.UserEntity;
import com.birthdaybot.repository.UserRepository;
import com.birthdaybot.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteFromSelect(userId, LocalDateTime.now());
    }

    @Override
    public void addReminderToUser(ReminderEntity reminderEntity, Long id) {
        Optional<UserEntity> userEntity = findUserById(id);
        UserEntity updatedUser = userEntity.get();
        updatedUser.getListOfReminders().add(reminderEntity);
        saveUser(updatedUser);
    }

    @Override
    public UserEntity findById(UUID userId) {
        return userRepository.findByUuid(userId).get();
    }
}
