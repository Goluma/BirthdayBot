package com.birthdaybot.mappers.impl;

import com.birthdaybot.domain.entities.UserEntity;
import com.birthdaybot.mappers.UserMapper;

public final class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity mapToUserEntity(Long userId, String firstName, String lastName){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        return userEntity;
    }
}
