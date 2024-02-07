package com.birthdaybot.repository;

import com.birthdaybot.domain.entitiy.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    @Query("from UserEntity where userId = ?1")
    Optional<UserEntity> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("delete from UserEntity u where u.userId = ?1")
    void deleteByUserId(Long userId);
}

