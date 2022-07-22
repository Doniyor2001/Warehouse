package ai.ecma.appwarehouseproject.entity;

import ai.ecma.appwarehouseproject.entity.abs.AbsSerialNumberEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "income")
@SQLDelete(sql = "update income set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Income extends AbsSerialNumberEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Currency currency;

    @Column(unique = true)
    private String factureNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Income income = (Income) o;
        return getId() != null && Objects.equals(getId(), income.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
