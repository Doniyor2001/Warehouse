package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDTO {
    private String name;
    private Long categoryId;
    private Long attachmentId;
    private Long measureId;
}
