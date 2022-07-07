package uz.pdp.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pcmarket.entity.Product;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPropertyDto {
    private String name;

    private String value;

    private Integer productId;
}
