package com.birthdaybot.repository;

import com.birthdaybot.domain.entitiy.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    @Query(nativeQuery = true, value = "select * from telegram_user where user_id = :user_id and deleted_at is null")
    Optional<UserEntity> findByUserId(@Param("user_id") Long userId);

    @Query(nativeQuery = true, value = "select * from telegram_user where uuid = :uuid and deleted_at is null")
    Optional<UserEntity> findByUuid(@Param("uuid") UUID userUuid);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from telegram_user u where u.user_id = :user_id")
    void deleteByUserId(@Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update telegram_user set deleted_at = :deleted_at where user_id = :user_id")
    void deleteFromSelect(@Param("user_id") Long userId, @Param("deleted_at") LocalDateTime now);
}

