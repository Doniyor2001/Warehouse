package ai.ecma.appwarehouseproject.entity.abs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbsActiveEntity extends AbsEntity {

    private boolean active=true;

}
