package com.birthdaybot.service.impl;

import com.birthdaybot.domain.entities.ReminderEntity;
import com.birthdaybot.repositories.ReminderRepository;
import com.birthdaybot.service.ReminderService;
import com.birthdaybot.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.websocket.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReminderServiceImpl implements ReminderService {

    private ReminderRepository reminderRepository;

    public ReminderServiceImpl(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    @Override
    public ReminderEntity saveReminder(ReminderEntity reminderEntity) {
        reminderRepository.save(reminderEntity);
        return reminderEntity;
    }

    @Override
    public void deleteReminder(Long noticeId) {
        reminderRepository.deleteById(noticeId);
    }

    @Override
    public void deleteAllReminders(List<ReminderEntity> listOfReminders) {
        for (int i = 0; i < listOfReminders.size(); i++){
            reminderRepository.delete(listOfReminders.get(i));
        }
    }

    @Override
    public List<ReminderEntity> findAllReminders(String str) {
        return StreamSupport.stream(reminderRepository.findAllByDate(str).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> findAllRemindersWithUsersId(String date) {
        return reminderRepository.findAllRemindersWithUsersId(date);
    }
}
