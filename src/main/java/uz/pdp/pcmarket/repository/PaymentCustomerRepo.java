package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.PaymentCustomer;

public interface PaymentCustomerRepo extends JpaRepository<PaymentCustomer,Integer> {
}
