package com.birthdaybot.bot.schedule;

import com.birthdaybot.domain.dto.AllTodayRemindersDto;

import java.util.List;

public interface RemindersSender {

    void sendReminders(List<AllTodayRemindersDto> reminderEntityList);
}
