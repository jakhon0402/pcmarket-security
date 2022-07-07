package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer> {
}
