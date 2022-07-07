package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CustomerDto;
import uz.pdp.pcmarket.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public List<Customer> getCustomers(){return customerService.getCustomers();}

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id){return customerService.getCustomerById(id);}

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCustomer(@RequestBody CustomerDto customerDto,
                                                @PathVariable Integer id){
        ApiResponse apiResponse = customerService.editCustomer(customerDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id){
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
