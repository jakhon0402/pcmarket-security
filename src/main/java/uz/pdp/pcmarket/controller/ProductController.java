package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.ProductDto;
import uz.pdp.pcmarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR','MODERATOR')")
    @GetMapping
    public List<Product> getProducts(){return productService.getProducts();}

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR','MODERATOR')")
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id){return productService.getProductById(id);}

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editProduct(@RequestBody ProductDto productDto,
                                                           @PathVariable Integer id){
        ApiResponse apiResponse = productService.editProduct(productDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id){
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
