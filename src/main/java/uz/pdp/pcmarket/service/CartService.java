package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Cart;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CartDto;
import uz.pdp.pcmarket.repository.CartRepo;
import uz.pdp.pcmarket.repository.CustomerRepo;
import uz.pdp.pcmarket.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CustomerRepo customerRepo;

    public List<Cart> getCarts(){return cartRepo.findAll();}

    public Cart getCartById(Integer id){
        Optional<Cart> optionalCart = cartRepo.findById(id);
        return optionalCart.orElse(null);
    }

    public ApiResponse addCart(CartDto cartDto){
        Optional<Product> optionalProduct = productRepo.findById(cartDto.getProductId());
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }

        Optional<Customer> optionalCustomer = customerRepo.findById(cartDto.getCustomerId());
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Cart cart = new Cart();
        cart.setProduct(optionalProduct.get());
        cart.setQuantity(cartDto.getQuantity());
        cart.setSubtotal(cart.getSubtotal());
        cart.setCustomer(optionalCustomer.get());
        cartRepo.save(cart);
        return new ApiResponse("Cart qo'shildi!",true);
    }

    public ApiResponse editCart(CartDto cartDto,Integer id){
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if(!optionalCart.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Optional<Product> optionalProduct = productRepo.findById(cartDto.getProductId());
        if(!optionalProduct.isPresent()){
            return new ApiResponse("Bunday idlik product mavjud emas!",false);
        }
        Optional<Customer> optionalCustomer = customerRepo.findById(cartDto.getCustomerId());
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Cart cart = optionalCart.get();
        cart.setProduct(optionalProduct.get());
        cart.setQuantity(cartDto.getQuantity());
        cart.setSubtotal(cart.getSubtotal());
        cart.setCustomer(optionalCustomer.get());
        cartRepo.save(cart);
        return new ApiResponse("Cart tahrirlandi!",true);
    }

    public ApiResponse deleteCart(Integer id){
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if(!optionalCart.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Cart cart = optionalCart.get();
        cartRepo.delete(cart);
        return new ApiResponse("Cart o'chirildi!",true);
    }
}
