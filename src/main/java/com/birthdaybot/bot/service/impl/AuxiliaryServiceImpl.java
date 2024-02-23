package com.birthdaybot.bot.service.impl;

import com.birthdaybot.bot.service.AuxiliaryService;
import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.domain.entitiy.UserEntity;
import com.birthdaybot.service.ReminderService;
import com.birthdaybot.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class AuxiliaryServiceImpl implements AuxiliaryService {

    private final ReminderService reminderService;
    private final UserService userService;
    private List<AllTodayRemindersDto> listOfReminders;

    public AuxiliaryServiceImpl(ReminderService reminderService, UserService userService){
        this.reminderService = reminderService;
        this.userService = userService;
    }

    @Override
    public void setListOfTodayReminders(){
        listOfReminders = reminderService.findAllTodayReminders();
        log.info("Reminders have been set");
    }

    public List<AllTodayRemindersDto> getListOfTodayReminders(){
        log.info("Got today reminders list.");
        return listOfReminders;
    }

    @Override
    public void deleteRemindersFromTheList(Long id) {
        listOfReminders.removeIf(x-> x.getUserId().equals(id));
        log.info("Reminders deleted from today list");
    }

    @Override
    public List<ReminderEntity> getUsersReminders(Long userId) {
        Optional<UserEntity> user = userService.findUserById(userId);
        List<ReminderEntity> reminderEntityList = user.get().getListOfReminders();
        reminderEntityList.removeIf(x -> x.getDeletedAt() != null);
        log.info("Got user reminders list.");
        return reminderEntityList;
    }
}
