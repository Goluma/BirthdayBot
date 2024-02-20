package com.birthdaybot.bot.schedule.impl;

import com.birthdaybot.bot.Bot;
import com.birthdaybot.bot.schedule.RemindersSender;
import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
public class RemindersSenderImpl implements RemindersSender {

    private Bot bot;
    private UserService userService;

    public RemindersSenderImpl(Bot bot, UserService userService){
        this.bot = bot;
        this.userService = userService;
    }

    @Override
    public void sendReminders(List<AllTodayRemindersDto> reminderEntityList){
        if (reminderEntityList == null || reminderEntityList.size() == 0){
            return;
        }

        reminderEntityList.stream().forEach(x -> {
                    bot.sendText(x.getUserId(),
                            String.format("Поздравьте пользователя %s %s",
                                    x.getBirthdayPerson(),
                                    x.getBirthdayPersonNickname()));
                    bot.sendButton(x.getUserId(), "Поздравил!");
                });


        log.info("Reminders sent");

    }

}
