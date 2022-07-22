package ai.ecma.appwarehouseproject.entity;

import ai.ecma.appwarehouseproject.entity.abs.AbsEntity;
import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "roles")
@SQLDelete(sql = "update roles set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Role extends AbsEntity {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PermissionEnums> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
