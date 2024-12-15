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
@Table(name = "item_t")
public class Item extends BaseEntity {
    private String name;
    private Double weight;
    private String units;
    @OneToMany
    private Set<Ingredient> ingredients;
}
