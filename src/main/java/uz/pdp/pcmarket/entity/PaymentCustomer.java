package uz.pdp.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Cart cart;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private double total;
}
