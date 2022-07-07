package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Review;

@Projection(types = Review.class)
public interface CustomReview {
    Integer getId();

    Integer getStars();

}
