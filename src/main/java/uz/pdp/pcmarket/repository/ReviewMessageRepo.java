package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.ReviewMessage;

public interface ReviewMessageRepo extends JpaRepository<ReviewMessage,Integer> {
}
