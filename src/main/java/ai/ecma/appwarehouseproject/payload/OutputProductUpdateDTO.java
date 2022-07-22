package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductUpdateDTO {

    private Long productId;

    private Long measureId;

    private Double amount;

    private Double price;

    private Long outcomeId;

    private Double finalPrice;

}
