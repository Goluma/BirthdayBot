package com.birthdaybot.bot.handlers.impl;

import com.birthdaybot.bot.handlers.CommandHandler;
import com.birthdaybot.bot.service.BusinessService;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Service
@Log
public class CommandHandlerImpl implements CommandHandler {

    private BusinessService businessService;

    public CommandHandlerImpl(BusinessService businessService){
        this.businessService = businessService;
    }


    @Override
    public void addReminder(User user, Message msg, int flagToAdd) {
        businessService.addReminder(user, msg, flagToAdd);
    }

    @Override
    public String deleteReminder(User user, Message msg) {
        return businessService.deleteReminder(user, msg);
    }

    @Override
    public void addUser(User user, Message msg) {
        businessService.addUser(user, msg);
    }

    @Override
    public void deleteUser(User user) {
        businessService.deleteUser(user);
    }

    @Override
    public String showReminders(User user, List<ReminderEntity> listOfReminders) {
        return businessService.showReminders(user, listOfReminders);
    }
}
