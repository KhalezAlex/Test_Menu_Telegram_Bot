package org.klozevitz.enitites.appUsers;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.klozevitz.enitites.BaseEntity;
import org.klozevitz.enitites.appUsers.enums.UserState;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user_t")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser extends BaseEntity {
    private Long telegramUserId;
    @CreationTimestamp
    private LocalDateTime firstLoginDate;
    private String username;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private UserState State;
    @OneToOne(mappedBy = "appUser")
    private Admin admin;
    @OneToOne(mappedBy = "appUser")
    private Company company;
    @OneToOne(mappedBy = "appUser")
    private Department department;
    @OneToOne(mappedBy = "appUser")
    private Employee employee;
}
