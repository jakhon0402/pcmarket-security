package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent,Integer> {
}
