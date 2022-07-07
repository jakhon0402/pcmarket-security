package uz.pdp.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @Column(nullable = false)
    private double price;

    @OneToOne
    private Attachment attachment;

    @Column(nullable = false)
    private String specification;

    @OneToOne
    private Review review;

    @Column(nullable = false)
    private String guarantee;
}
