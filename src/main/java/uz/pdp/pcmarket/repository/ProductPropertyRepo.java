package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.ProductProperty;

public interface ProductPropertyRepo extends JpaRepository<ProductProperty,Integer> {
}
