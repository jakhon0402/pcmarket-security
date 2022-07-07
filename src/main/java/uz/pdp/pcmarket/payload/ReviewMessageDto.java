package uz.pdp.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pcmarket.entity.Review;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessageDto {

    private String text;

    private String fullName;

    private String email;

    private Integer reviewId;
}
