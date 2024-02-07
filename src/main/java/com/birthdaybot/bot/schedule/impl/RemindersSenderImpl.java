package com.birthdaybot.bot.schedule.impl;

import com.birthdaybot.bot.Bot;
import com.birthdaybot.bot.schedule.RemindersSender;
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
    public void sendReminders(List<Object[]> reminderEntityList){
        if (reminderEntityList == null || reminderEntityList.size() == 0){
            return;
        }
        for (int i = 0; i < reminderEntityList.size(); i++){
            Object[] row = reminderEntityList.get(i);
            bot.sendText((Long) row[0],
                    String.format("Поздравьте пользователя %s %s", row[1], row[2]));
            bot.sendButton((Long) row[0], "Поздравил!");
            log.info("Reminders sent");
        }

    }

}
