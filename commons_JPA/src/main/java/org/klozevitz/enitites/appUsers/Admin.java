package org.klozevitz.enitites.appUsers;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_t")
public class Admin extends AbstractAppUser {
}
