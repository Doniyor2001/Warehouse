package ai.ecma.appwarehouseproject.entity;

import ai.ecma.appwarehouseproject.entity.abs.AbsEntity;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "supplier")
@SQLDelete(sql = "update supplier set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Supplier extends AbsEntity {

    private String name;

    @NotNull
    @Pattern(regexp = "^[+]998([- ])?(90|91|93|94|95|98|99|33|97|71|88|77)([- ])?(\\d{3})([- ])?(\\d{2})([- ])?(\\d{2})$")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
