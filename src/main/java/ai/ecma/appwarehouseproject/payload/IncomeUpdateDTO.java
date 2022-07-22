package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeUpdateDTO {

    private Long warehouseId;

    private Long supplierId;

    private Long currencyId;

}
