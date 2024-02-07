package com.birthdaybot.mapper;

import com.birthdaybot.domain.entitiy.UserEntity;


public final class UserMapperImpl{

    public UserEntity mapToUserEntity(Long userId, String firstName, String lastName){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        return userEntity;
    }
}
