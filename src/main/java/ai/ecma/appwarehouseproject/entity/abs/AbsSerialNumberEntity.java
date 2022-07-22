package ai.ecma.appwarehouseproject.entity.abs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbsSerialNumberEntity extends AbsEntity{

    private String serialNumber = generateSerialNumber();

    private String generateSerialNumber(){
        String code = String.valueOf((int)(Math.random()*1_000_000_000));
        return code.substring(0,6);
    }

}
