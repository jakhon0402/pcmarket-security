package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.*;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.PaymentCustomerDto;
import uz.pdp.pcmarket.repository.CartRepo;
import uz.pdp.pcmarket.repository.CurrencyRepo;
import uz.pdp.pcmarket.repository.CustomerRepo;
import uz.pdp.pcmarket.repository.PaymentCustomerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentCustomerService {
    @Autowired
    PaymentCustomerRepo paymentCustomerRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CurrencyRepo currencyRepo;

    public List<PaymentCustomer> getPaymentCustomers(){return paymentCustomerRepo.findAll();}

    public PaymentCustomer getPaymentCustomerById(Integer id){
        Optional<PaymentCustomer> optionalPaymentCustomer = paymentCustomerRepo.findById(id);
        return optionalPaymentCustomer.orElse(null);
    }

    public ApiResponse addPaymentCustomer(PaymentCustomerDto paymentCustomerDto){
        Optional<Customer> optionalCustomer = customerRepo.findById(paymentCustomerDto.getCustomerId());
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik customer mavjud emas!",false);
        }

        Optional<Cart> optionalCart = cartRepo.findById(paymentCustomerDto.getCartId());
        if(!optionalCart.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(paymentCustomerDto.getCurrencyId());
        if(!optionalCurrency.isPresent()){
            return new ApiResponse("Bunday idlik currency mavjud emas!",false);
        }
        PaymentCustomer paymentCustomer = new PaymentCustomer();
        paymentCustomer.setCustomer(optionalCustomer.get());
        paymentCustomer.setCart(optionalCart.get());
        paymentCustomer.setDate(paymentCustomerDto.getDate());
        paymentCustomer.setCurrency(optionalCurrency.get());
        paymentCustomer.setTotal(paymentCustomerDto.getTotal());
        paymentCustomerRepo.save(paymentCustomer);
        return new ApiResponse("PaymentCustomer qo'shildi!",true);
    }

    public ApiResponse editPaymentCustomer(PaymentCustomerDto paymentCustomerDto,Integer id){
        Optional<PaymentCustomer> optionalPaymentCustomer = paymentCustomerRepo.findById(id);
        if(!optionalPaymentCustomer.isPresent()){
            return new ApiResponse("Bunday idlik paymentCustomer mavjud emas!",false);
        }
        Optional<Customer> optionalCustomer = customerRepo.findById(paymentCustomerDto.getCustomerId());
        if(!optionalCustomer.isPresent()){
            return new ApiResponse("Bunday idlik customer mavjud emas!",false);
        }

        Optional<Cart> optionalCart = cartRepo.findById(paymentCustomerDto.getCartId());
        if(!optionalCart.isPresent()){
            return new ApiResponse("Bunday idlik cart mavjud emas!",false);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(paymentCustomerDto.getCurrencyId());
        if(!optionalCurrency.isPresent()){
            return new ApiResponse("Bunday idlik currency mavjud emas!",false);
        }
        PaymentCustomer paymentCustomer = optionalPaymentCustomer.get();
        paymentCustomer.setCustomer(optionalCustomer.get());
        paymentCustomer.setCart(optionalCart.get());
        paymentCustomer.setDate(paymentCustomerDto.getDate());
        paymentCustomer.setCurrency(optionalCurrency.get());
        paymentCustomer.setTotal(paymentCustomerDto.getTotal());
        paymentCustomerRepo.save(paymentCustomer);
        return new ApiResponse("PaymentCustomer tahrirlandi!",true);
    }

    public ApiResponse deletePaymentCustomer(Integer id){
        Optional<PaymentCustomer> optionalPaymentCustomer = paymentCustomerRepo.findById(id);
        if(!optionalPaymentCustomer.isPresent()){
            return new ApiResponse("Bunday idlik paymentCustomer mavjud emas!",false);
        }
        PaymentCustomer paymentCustomer = optionalPaymentCustomer.get();
        paymentCustomerRepo.delete(paymentCustomer);
        return new ApiResponse("PaymentCustomer o'chirildi!",true);
    }
}
