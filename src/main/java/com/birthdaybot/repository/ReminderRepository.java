package com.birthdaybot.repository;

import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ReminderRepository extends CrudRepository<ReminderEntity, UUID> {

    @Query(nativeQuery = true, value =  """
            select u1_0.user_id as userId, l1_0.birthday_person as birthdayPerson,
            l1_0.birthday_person_nickname as birthdayPersonNickname from telegram_user u1_0
            join reminder l1_0 on u1_0.uuid=l1_0.uuid
            where extract(day from l1_0.birthday)=extract(day from current_date)
            and extract(month from l1_0.birthday)=extract(month from current_date)""")
    List<AllTodayRemindersDto> findAllTodayReminders();
}

/*
"select user.userId, reminder.birthdayPerson, reminder.birthdayPersonNickname from UserEntity user\n" +
        "join user.listOfReminders reminder\n" +
        "where day(reminder.birthday) = day(current_date()) and month(reminder.birthday) = month(current_date())"*/
