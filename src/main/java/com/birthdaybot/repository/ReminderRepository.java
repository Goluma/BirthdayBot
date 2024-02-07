package com.birthdaybot.repositories;

import com.birthdaybot.domain.entities.ReminderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends CrudRepository<ReminderEntity, Long> {

    @Query("from ReminderEntity where birthday = ?1")
    Iterable<ReminderEntity> findAllByDate(String str);

    @Query(nativeQuery = true, value = "select u.user_id, r.birthday_person, r.birthday_person_nickname" +
            " from reminder r join telegram_user u on u.uuid = r.uuid where r.birthday = ?1")
    List<Object[]> findAllRemindersWithUsersId(String date);
}
