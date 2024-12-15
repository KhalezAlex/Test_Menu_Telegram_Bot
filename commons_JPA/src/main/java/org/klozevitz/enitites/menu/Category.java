package org.klozevitz.enitites.menu;

import lombok.*;
import org.klozevitz.enitites.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_t")
public class Category extends BaseEntity {
    private String name;
    @OneToMany
    private Set<Item> list;
}
