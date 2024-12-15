package org.klozevitz.enitites.appUsers;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.klozevitz.enitites.BaseEntity;
import org.klozevitz.enitites.appUsers.enums.UserState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractAppUser extends BaseEntity {
    private Long telegramUserId;
    @CreationTimestamp
    private LocalDateTime firstLoginDate;
    private String username;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private UserState State;
    @OneToOne
    private Company company;
}
