package org.klozevitz.enitites.menu;

import lombok.*;
import org.klozevitz.enitites.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient_t")
public class Ingredient extends BaseEntity {
    public String name;
    public Double weight;
    public String units;
}
