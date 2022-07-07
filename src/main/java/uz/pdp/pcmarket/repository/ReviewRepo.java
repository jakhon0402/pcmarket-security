package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.entity.Review;
import uz.pdp.pcmarket.projection.CustomReview;

@RepositoryRestResource(path = "review",collectionResourceRel = "list",excerptProjection = CustomReview.class)
public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
