package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.entity.Brand;
import uz.pdp.pcmarket.projection.CustomBrand;

@RepositoryRestResource(path = "brand",collectionResourceRel = "list",excerptProjection = CustomBrand.class)
public interface BrandRepo extends JpaRepository<Brand,Integer> {
}
