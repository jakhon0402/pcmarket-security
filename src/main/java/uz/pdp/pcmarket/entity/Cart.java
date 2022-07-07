package uz.pdp.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private double subtotal;

    @ManyToOne
    private Customer customer;
}
