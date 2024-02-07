package com.birthdaybot.bot.service.impl;

import com.birthdaybot.bot.service.AuxiliaryService;
import com.birthdaybot.domain.entities.ReminderEntity;
import com.birthdaybot.domain.entities.UserEntity;
import com.birthdaybot.service.ReminderService;
import com.birthdaybot.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class AuxiliaryServiceImpl implements AuxiliaryService {

    private ReminderService reminderService;
    private UserService userService;
    private List<Object[]> listOfReminders;

    public AuxiliaryServiceImpl(ReminderService reminderService, UserService userService){
        this.reminderService = reminderService;
        this.userService = userService;
    }

    @Override
    public void setListOfTodayReminders(){
        LocalDate localDate = LocalDate.now();
        String str = localDate.getDayOfMonth() + "." + localDate.getMonthValue();
        listOfReminders = reminderService.findAllRemindersWithUsersId(str);
    }

    public List<Object[]> getListOfTodayReminders(){
        return listOfReminders;
    }

    @Override
    public void deleteRemindersFromTheList(Long id) {
        for (int i = 0; i < listOfReminders.size(); i++){
            Object[] row = listOfReminders.get(i);
            if (row[0].equals(id)){
                listOfReminders.remove(i);
            }
        }
    }

    @Override
    public List<ReminderEntity> getUsersReminders(Long userId) {
        Optional<UserEntity> userEntity = userService.findUserById(userId);
        return userEntity.get().getListOfReminders();
    }
}
