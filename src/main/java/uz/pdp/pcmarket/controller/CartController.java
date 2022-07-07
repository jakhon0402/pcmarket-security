package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Cart;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CartDto;
import uz.pdp.pcmarket.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping
    public List<Cart> getCarts(){return cartService.getCarts();}

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Integer id){return cartService.getCartById(id);}

    @PostMapping
    public ResponseEntity<ApiResponse> addCart(@RequestBody CartDto cartDto){
        ApiResponse apiResponse = cartService.addCart(cartDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCart(@RequestBody CartDto cartDto,
                                                    @PathVariable Integer id){
        ApiResponse apiResponse = cartService.editCart(cartDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Integer id){
        ApiResponse apiResponse = cartService.deleteCart(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
