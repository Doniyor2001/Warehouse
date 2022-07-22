package ai.ecma.appwarehouseproject.entity;

import ai.ecma.appwarehouseproject.entity.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "output_product")
@SQLDelete(sql = "update output_product set deleted = true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class OutputProduct extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Measure measure;

    private Double amount;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Outcome outcome;

    private Double finalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OutputProduct that = (OutputProduct) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
