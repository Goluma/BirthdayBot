package com.birthdaybot.domain.dto;

public interface AllTodayRemindersDto {


    // @Value("#{user.userId}")
    Long getUserId();

   // @Value("#{reminder.birthdayPerson}")
    String getBirthdayPerson();

    //  @Value("#{reminder.birthdayPersonNickname}")
    String getBirthdayPersonNickname();

//    private Long userId;
//
//    private String birthdayPerson;
//
//    private String birthdayPersonNickname;
//
//    public FindAllTodayRemindersDto(Long userID, String birthdayPerson, String birthdayPersonNickname) {
//        this.userId = userID;
//        this.birthdayPerson = birthdayPerson;
//        this.birthdayPersonNickname = birthdayPersonNickname;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FindAllTodayRemindersDto that = (FindAllTodayRemindersDto) o;
//        return Objects.equals(userId, that.userId) && Objects.equals(birthdayPerson, that.birthdayPerson) && Objects.equals(birthdayPersonNickname, that.birthdayPersonNickname);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, birthdayPerson, birthdayPersonNickname);
//    }
}
