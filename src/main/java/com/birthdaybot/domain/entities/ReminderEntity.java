package com.birthdaybot.domain.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "reminder")
public class ReminderEntity {

    @Id
    @SequenceGenerator(name = "notice_seq", sequenceName = "notice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq")
    private Long noticeId;

    private String birthdayPerson;

    private String birthdayPersonNickname;

    private String birthday;

    private UUID uuid;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;

        ReminderEntity reminderEntity = (ReminderEntity) o;

        return this.noticeId.equals(reminderEntity.noticeId)
                && this.birthday.equals(reminderEntity.birthday)
                && this.birthdayPerson.equals((reminderEntity.birthdayPerson))
                && this.birthdayPersonNickname.equals(reminderEntity.birthdayPersonNickname);
    }

    @Override
    public int hashCode(){
        return Objects.hash(noticeId, birthday, birthdayPerson, birthdayPersonNickname);
    }
}
