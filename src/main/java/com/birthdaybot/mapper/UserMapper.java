package com.birthdaybot.mapper;

import com.birthdaybot.domain.entitiy.UserEntity;
import org.springframework.stereotype.Component;


@Component
public final class UserMapper {

    public UserEntity mapToUserEntity(Long userId, String firstName, String lastName){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        return userEntity;
    }
}
