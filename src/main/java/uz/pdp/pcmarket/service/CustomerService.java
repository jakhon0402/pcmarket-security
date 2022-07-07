package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.entity.Customer;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CustomerDto;
import uz.pdp.pcmarket.repository.CustomerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getCustomers(){return customerRepo.findAll();}

    public Customer getCustomerById(Integer id){
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        return optionalCustomer.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto){

        Customer customer = new Customer();
        customer.setFullName(customer.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customer.getEmail());
        customerRepo.save(customer);
        return new ApiResponse("Customer qo'shildi!",true);
    }

    public ApiResponse editCustomer(CustomerDto customerDto,Integer id){
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik customer mavjud emas!",false);
        }
        Customer customer = optionalCustomer.get();
        customer.setFullName(customer.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customer.getEmail());
        customerRepo.save(customer);
        return new ApiResponse("Customer tahrirlandi!",true);
    }

    public ApiResponse deleteCustomer(Integer id){
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik customer mavjud emas!",false);
        }
        Customer customer = optionalCustomer.get();
        customerRepo.delete(customer);
        return new ApiResponse("Customer o'chirildi!",true);
    }
}
