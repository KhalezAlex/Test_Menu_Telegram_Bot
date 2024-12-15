package org.klozevitz.enitites.appUsers;

import lombok.*;
import org.klozevitz.enitites.menu.Category;

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
@Table(name = "department_t")
public class Department extends AbstractAppUser {
    @OneToMany
    private Set<Employee> employees;
    @OneToMany
    private Set<Category> menu;
}
