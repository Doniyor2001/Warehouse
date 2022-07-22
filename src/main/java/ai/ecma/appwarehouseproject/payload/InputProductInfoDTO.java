package ai.ecma.appwarehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductInfoDTO {

    private Long productId;

    private Long measureId;

    private Double amount;

    private Double price;

    private Timestamp expiredDate;

    private Long incomeId;

    private Double finalPrice;

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = Timestamp.valueOf(expiredDate);
    }

}
