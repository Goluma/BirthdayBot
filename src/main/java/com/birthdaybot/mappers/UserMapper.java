package com.birthdaybot.mappers;

import com.birthdaybot.domain.entities.UserEntity;

public interface UserMapper {

    UserEntity mapToUserEntity(Long userId, String firstName, String lastName);
}
