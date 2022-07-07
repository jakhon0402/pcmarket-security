package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.Brand;

@Projection(types = Brand.class)
public interface CustomBrand {
    Integer getId();

    Attachment getAttachment();
}
