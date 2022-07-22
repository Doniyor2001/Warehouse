package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDTO {

    private String firstName;

    private String lastname;

    private String phoneNumber;

    private String email;

    private Long roleId;

    private Long warehouseId;

}
