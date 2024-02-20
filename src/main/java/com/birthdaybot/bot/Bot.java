package com.birthdaybot.bot;

import com.birthdaybot.bot.handlers.CommandHandler;
import com.birthdaybot.bot.service.impl.AuxiliaryServiceImpl;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Log
public class Bot extends TelegramLongPollingBot {

    private AuxiliaryServiceImpl auxiliaryServiceImpl;
    private CommandHandler commandHandler;
    private int flagToAddReminder;
    private boolean flagToDeleteReminder;
    private String botUserName;
    private String botToken;


    public Bot(
            TelegramBotsApi telegramBotsApi,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken,
            AuxiliaryServiceImpl auxiliaryServiceImpl, CommandHandler commandHandler) throws TelegramApiException {

        this.botUserName = botUsername;
        this.botToken = botToken;
        this.auxiliaryServiceImpl = auxiliaryServiceImpl;
        this.commandHandler = commandHandler;
        telegramBotsApi.registerBot(this);
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage() == null){
            return;
        }
        Message msg = update.getMessage();
        User user = msg.getFrom();
        if (msg.getText() == null){
            log.info(user.getId() + " " + user.getFirstName() + " sent not text");
            sendText(user.getId(), "Нельзя отправлять не текстовые сообщения.");
        } else if (flagToAddReminder > 0){
            commandHandler.addReminder(user, msg, flagToAddReminder);
            if (flagToAddReminder == 1){
                sendText(user.getId(), "Введите никнейм пользователя (не обязательно, нажмите кнопку \"Пропустить\").");
                sendButton(user.getId(), "Пропустить");
            } else if (flagToAddReminder == 2){
                sendText(user.getId(), "Введите дату рождения в формате гггг-мм-дд (например, 2004-06-26)");
            } else if (flagToAddReminder == 3){
                sendText(user.getId(), "Напоминание успешно создано!");
                sendReplyKeyboard(user.getId());
            }
            flagToAddReminder++;
            if (flagToAddReminder > 3){
                flagToAddReminder = 0;
            }
        } else if (flagToDeleteReminder){
            if (msg.getText().matches("\\d+")){
                String str = commandHandler.deleteReminder(user, msg);
                sendText(user.getId(), str);
                if (str.equals("Напоминание удалено.")){
                    flagToDeleteReminder = false;
                    sendReplyKeyboard(user.getId());
                }
            } else {
                sendText(user.getId(), "Отправьте число.");
            }
        } else if (msg.isCommand()){
            if (msg.getText().equals("/start")) {

                commandHandler.addUser(user, msg);
                sendText(user.getId(), "Приветствую. Бот поможет Вам не забыть" +
                        " поздравить друга с днем рождения.");
                sendText(user.getId(), "Следующие команды пригодятся при использовании бота:\n" +
                        " -    /addreminder - добавить напоминание\n" +
                        " -    /showreminders - показать все напоминания" +
                        " -    /deleteuser - удалить всю информацию о пользователе\n" +
                        " -    /deletereminder - удалить напоминание по Вашему выбору");
                sendReplyKeyboard(user.getId());
            } else if (msg.getText().equals("/addreminder")){

                flagToAddReminder = 1;
                sendText(user.getId(), "Введите имя и фамилию человека, которого хотите поздравить.");
            } else if (msg.getText().equals("/deletereminder")){

                List<ReminderEntity> listOfReminders = auxiliaryServiceImpl.getUsersReminders(user.getId());
                if (listOfReminders.size() < 1){
                    sendText(user.getId(), "У вас нет напоминаний.");
                    sendReplyKeyboard(user.getId());
                    return;
                }
                sendText(user.getId(), "Укажите номер напоминания, которое хотите удалить.");
                String str = commandHandler.showReminders(user, listOfReminders);
                sendText(user.getId(), str);
                flagToDeleteReminder = true;
            } else if (msg.getText().equals("/showreminders")){

                List<ReminderEntity> listOfReminders = auxiliaryServiceImpl.getUsersReminders(user.getId());
                if (listOfReminders.size() < 1){
                    sendText(user.getId(), "У вас нет напоминаний.");
                    sendReplyKeyboard(user.getId());
                    return;
                }
                String str = commandHandler.showReminders(user, listOfReminders);
                sendText(user.getId(), str);
                log.info("Reminder sent.");
                sendReplyKeyboard(user.getId());

            } else {
                commandHandler.deleteUser(user);
                sendText(user.getId(), "Пользователь удален, чтобы продолжить работу с ботом, нажмите start.");
                sendButton(user.getId(), "/start");
            }
        } else if (msg.getText().equals("Поздравил!")) {
            sendReplyKeyboard(user.getId());

            auxiliaryServiceImpl.deleteRemindersFromTheList(user.getId());
        } else {
            log.info(user.getId() + " " + user.getFirstName() + "  wrote  " + msg.getText());
            copyMessage(user.getId(), msg.getMessageId());
        }
    }


    public ReplyKeyboardMarkup createNewButton(String str){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton(str));

        List<KeyboardRow> keyboardRows = List.of(firstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup setButtons(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("/addreminder"));
        firstRow.add(new KeyboardButton("/showreminders"));

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("/deletereminder"));
        secondRow.add(new KeyboardButton("/deleteuser"));

        List<KeyboardRow> keyboardRows = List.of(firstRow, secondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public void sendButton(Long id, String str){
        ReplyKeyboardMarkup replyKeyboardMarkup = createNewButton(str);
        SendMessage sendMessage = SendMessage.builder()
                .chatId(id.toString())
                .parseMode("HTML").text("<b>Menu</b>")
                .replyMarkup(replyKeyboardMarkup)
                .build();

        try{
            execute(sendMessage);
        } catch (TelegramApiException e){
            throw new RuntimeException();
        }
    }

    public void sendReplyKeyboard(Long id){
        ReplyKeyboardMarkup replyKeyboardMarkup = setButtons();
        SendMessage sendMessage = SendMessage.builder()
                .chatId(id.toString())
                .parseMode("HTML").text("<b>Menu</b>")
                .replyMarkup(replyKeyboardMarkup)
                .build();

        try{
            execute(sendMessage);
            log.info("Buttons have been send");
        } catch (TelegramApiException e){
            throw new RuntimeException();
        }
    }

    public void copyMessage(Long id, Integer msgId){
        CopyMessage copyMessage = CopyMessage.builder()
                .fromChatId(id.toString())
                .chatId(id.toString())
                .messageId(msgId)
                .build();

        try{
            execute(copyMessage);
            log.info("Copy sent");
        } catch (TelegramApiException e){
            throw new RuntimeException();
        }
    }

    public void sendText(Long chatId, String text){
        SendMessage sm = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
        try {
            execute(sm);
            log.info("Message have sent!");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
