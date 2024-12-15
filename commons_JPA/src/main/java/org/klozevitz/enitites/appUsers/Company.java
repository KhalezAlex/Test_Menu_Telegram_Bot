package org.klozevitz.enitites.appUsers;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_t")
public class Company extends AbstractAppUser {
    private String name;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Department> departments;
}
