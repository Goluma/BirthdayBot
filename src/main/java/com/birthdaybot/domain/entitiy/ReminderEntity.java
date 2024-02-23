package com.birthdaybot.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "reminder")
public class ReminderEntity {


    @Id
    @GeneratedValue
    private UUID noticeUuid;

    private String birthdayPerson;

    private String birthdayPersonNickname;

    private LocalDate birthday;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;

        ReminderEntity reminderEntity = (ReminderEntity) o;

        return this.noticeUuid.equals(reminderEntity.noticeUuid)
                && this.birthday.equals(reminderEntity.birthday)
                && this.birthdayPerson.equals((reminderEntity.birthdayPerson))
                && this.birthdayPersonNickname.equals(reminderEntity.birthdayPersonNickname);
    }

    @Override
    public int hashCode(){
        return Objects.hash(noticeUuid, birthday, birthdayPerson, birthdayPersonNickname);
    }
}
