package uz.pdp.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.Brand;
import uz.pdp.pcmarket.entity.Category;
import uz.pdp.pcmarket.entity.Review;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;

    private String title;

    private Integer categoryId;

    private Integer brandId;

    private double price;

    private Integer attachmentId;

    private String specification;

    private Integer reviewId;

    private String guarantee;
}
