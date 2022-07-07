package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Currency;

@Projection(types = Currency.class)
public interface CustomCurrency {
    Integer getId();

    String getName();

    String getActive();

}
