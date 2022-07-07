package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.entity.ProductProperty;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.ProductPropertyDto;
import uz.pdp.pcmarket.repository.ProductPropertyRepo;
import uz.pdp.pcmarket.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPropertyService {
    @Autowired
    ProductPropertyRepo productPropertyRepo;

    @Autowired
    ProductRepo productRepo;

    public List<ProductProperty> getProductProperties(){return productPropertyRepo.findAll();}

    public ProductProperty getProductPropertyById(Integer id){
        Optional<ProductProperty> optionalProductProperty = productPropertyRepo.findById(id);
        return optionalProductProperty.orElse(null);
    }

    public ApiResponse addProductProperty(ProductPropertyDto productPropertyDto){
        Optional<Product> optionalProduct = productRepo.findById(productPropertyDto.getProductId());
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }
        ProductProperty productProperty = new ProductProperty();
        productProperty.setName(productPropertyDto.getName());
        productProperty.setValue(productPropertyDto.getValue());
        productProperty.setProduct(optionalProduct.get());
        productPropertyRepo.save(productProperty);
        return new ApiResponse("ProductProperty qo'shildi!",true);
    }

    public ApiResponse editProductProperty(ProductPropertyDto productPropertyDto,Integer id){
        Optional<ProductProperty> optionalProductProperty = productPropertyRepo.findById(id);
        if(!optionalProductProperty.isPresent()){
            return new ApiResponse("Bunday idlik productProperty mavjud emas!",false);
        }
        Optional<Product> optionalProduct = productRepo.findById(productPropertyDto.getProductId());
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }
        ProductProperty productProperty = optionalProductProperty.get();
        productProperty.setName(productPropertyDto.getName());
        productProperty.setValue(productPropertyDto.getValue());
        productProperty.setProduct(optionalProduct.get());
        productPropertyRepo.save(productProperty);
        return new ApiResponse("ProductProperty tahrirlandi!",true);
    }

    public ApiResponse deleteProductProperty(Integer id){
        Optional<ProductProperty> optionalProductProperty = productPropertyRepo.findById(id);
        if(!optionalProductProperty.isPresent()){
            return new ApiResponse("Bunday idlik productProperty mavjud emas!",false);
        }
        ProductProperty productProperty = optionalProductProperty.get();
        productPropertyRepo.delete(productProperty);
        return new ApiResponse("ProductProperty o'chirildi!",true);
    }
}
