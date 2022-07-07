package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.*;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.ProductDto;
import uz.pdp.pcmarket.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    ReviewRepo reviewRepo;

    public List<Product> getProducts(){return productRepo.findAll();}

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElse(null);
    }

    public ApiResponse addProduct(ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik category mavjud emas!",false);
        }

        Optional<Brand> optionalBrand = brandRepo.findById(productDto.getBrandId());
        if(!optionalBrand.isPresent()){
            return new ApiResponse("Bunday idlik brand mavjud emas!",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepo.findById(productDto.getAttachmentId());
        if(!optionalAttachment.isPresent()){
            return new ApiResponse("Bunday idlik attachment mavjud emas!",false);
        }

        Optional<Review> optionalReview = reviewRepo.findById(productDto.getReviewId());
        if(!optionalReview.isPresent()){
            return new ApiResponse("Bunday idlik review mavjud emas!",false);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setTitle(productDto.getTitle());
        product.setCategory(optionalCategory.get());
        product.setBrand(optionalBrand.get());
        product.setPrice(productDto.getPrice());
        product.setAttachment(optionalAttachment.get());
        product.setSpecification(productDto.getSpecification());
        product.setGuarantee(productDto.getGuarantee());
        product.setReview(optionalReview.get());
        productRepo.save(product);
        return new ApiResponse("Product qo'shildi!",true);
    }

    public ApiResponse editProduct(ProductDto productDto,Integer id){
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }

        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik category mavjud emas!",false);
        }

        Optional<Brand> optionalBrand = brandRepo.findById(productDto.getBrandId());
        if(!optionalBrand.isPresent()){
            return new ApiResponse("Bunday idlik brand mavjud emas!",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepo.findById(productDto.getAttachmentId());
        if(!optionalAttachment.isPresent()){
            return new ApiResponse("Bunday idlik attachment mavjud emas!",false);
        }

        Optional<Review> optionalReview = reviewRepo.findById(productDto.getReviewId());
        if(!optionalReview.isPresent()){
            return new ApiResponse("Bunday idlik review mavjud emas!",false);
        }

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setTitle(productDto.getTitle());
        product.setCategory(optionalCategory.get());
        product.setBrand(optionalBrand.get());
        product.setPrice(productDto.getPrice());
        product.setAttachment(optionalAttachment.get());
        product.setSpecification(productDto.getSpecification());
        product.setGuarantee(productDto.getGuarantee());
        product.setReview(optionalReview.get());
        productRepo.save(product);
        return new ApiResponse("Product tahrirlandi!",true);
    }

    public ApiResponse deleteProduct(Integer id){
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }
        Product product = optionalProduct.get();
        productRepo.delete(product);
        return new ApiResponse("Product o'chirildi!",true);
    }
}
