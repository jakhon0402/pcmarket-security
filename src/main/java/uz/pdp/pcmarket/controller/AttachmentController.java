package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.repository.AttachmentContentRepo;
import uz.pdp.pcmarket.repository.AttachmentRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentRepo attachmentRepo;
    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if(file!=null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setFileName(originalFilename);
            attachment.setContentType(contentType);
            attachment.setSize(size);
            Attachment savedAttachment = attachmentRepo.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepo.save(attachmentContent);
            return "File saved !";
        }
        else return "Error ! : Xatolik !";
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR','MODERATOR')")
    @GetMapping("/download/{id}")
    public void downloadFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if(optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findById(id);
            if(optionalAttachmentContent.isPresent()){
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition", "attachment; filename=\""+attachment.getFileName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());

            }
        }
    }
}
