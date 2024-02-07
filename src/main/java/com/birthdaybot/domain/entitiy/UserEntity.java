package com.birthdaybot.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "telegram_user")
public class UserEntity {


    @Id
    @GeneratedValue
    private UUID uuid;

    private Long userId;

    private String firstName;

    private String lastName;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "uuid")
    private List<ReminderEntity> listOfReminders = new ArrayList<>();

    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        if ((obj == null) || (obj.getClass() != this.getClass())) return false;

        UserEntity userEntity = (UserEntity) obj;

        return (userEntity.uuid.equals(this.uuid))
                && (userEntity.userId.equals(this.userId))
                && userEntity.firstName.equals(this.firstName)
                && userEntity.lastName.equals(this.lastName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(uuid, userId, firstName, lastName);
    }
}
