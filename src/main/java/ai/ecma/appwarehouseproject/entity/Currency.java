package ai.ecma.appwarehouseproject.entity;

import ai.ecma.appwarehouseproject.entity.abs.AbsActiveEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "currency")
@SQLDelete(sql = "update currency set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Currency extends AbsActiveEntity {

    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Currency currency = (Currency) o;
        return getId() != null && Objects.equals(getId(), currency.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
