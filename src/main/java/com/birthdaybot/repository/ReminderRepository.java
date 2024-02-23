package com.birthdaybot.repository;

import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReminderRepository extends CrudRepository<ReminderEntity, UUID> {

    @Query(nativeQuery = true, value =  """
            select u1_0.user_id as userId, l1_0.birthday_person as birthdayPerson,
            l1_0.birthday_person_nickname as birthdayPersonNickname from telegram_user u1_0
            join reminder l1_0 on u1_0.uuid = l1_0.uuid
            where extract(day from l1_0.birthday) = extract(day from current_date)
            and extract(month from l1_0.birthday) = extract(month from current_date)
            and u1_0.deleted_at is null
            and l1_0.deleted_ar is null""")
    List<AllTodayRemindersDto> findAllTodayReminders();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update reminder set deleted_at = :deleted_at where notice_uuid = :notice_uuid")
    void deleteFromSelect(@Param("notice_uuid") UUID notice_uuid, @Param("deleted_at") LocalDateTime now);

}
