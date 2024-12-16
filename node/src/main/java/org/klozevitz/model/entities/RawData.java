package org.klozevitz.model.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.klozevitz.enitites.BaseEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Объекты этого типа будут генерироваться из всех входящих сообщений
 * */

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raw_data_t")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RawData extends BaseEntity {
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Update event;
}

