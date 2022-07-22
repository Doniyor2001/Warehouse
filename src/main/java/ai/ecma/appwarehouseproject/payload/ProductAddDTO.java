package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductAddDTO {

    private String name;

    private Long categoryId;

    private Long attachmentId;

    private Long measureId;

}
