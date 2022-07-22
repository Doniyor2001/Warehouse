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
@Entity(name = "measure")
@SQLDelete(sql = "update measure set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Measure extends AbsActiveEntity {

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Measure measure = (Measure) o;
        return getId() != null && Objects.equals(getId(), measure.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
