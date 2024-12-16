package org.klozevitz.enitites.appUsers;

import javax.persistence.*;
import lombok.*;
import org.klozevitz.enitites.BaseEntity;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_t")
public class Company extends BaseEntity {
    private String name;
    private String email;
    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Department> departments;
}
