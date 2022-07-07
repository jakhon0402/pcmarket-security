package uz.pdp.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCustomerDto {
    private Integer customerId;

    private Integer cartId;

    private Date date;

    private Integer currencyId;

    private double total;
}
