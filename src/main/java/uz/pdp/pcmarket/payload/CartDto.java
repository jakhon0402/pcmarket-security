package uz.pdp.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer productId;

    private Integer quantity;

    private double subtotal;

    private Integer customerId;
}
