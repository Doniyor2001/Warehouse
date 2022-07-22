package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutcomeUpdateDTO {

    private Long warehouseId;

    private Long currencyId;

    private String factureNumber;
}
