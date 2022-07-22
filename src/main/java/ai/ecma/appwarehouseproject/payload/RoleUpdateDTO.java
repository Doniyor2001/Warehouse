package ai.ecma.appwarehouseproject.payload;

import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleUpdateDTO {

    private String name;

    private String description;

    private List<PermissionEnums> permissions;

}
