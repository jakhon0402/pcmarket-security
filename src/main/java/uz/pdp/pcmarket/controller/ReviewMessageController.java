package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.ReviewMessage;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.ReviewMessageDto;
import uz.pdp.pcmarket.service.ReviewMessageService;

import java.util.List;

@RestController
@RequestMapping("/reviewMessage")
public class ReviewMessageController {
    @Autowired
    ReviewMessageService reviewMessageService;

    @GetMapping
    public List<ReviewMessage> getReviewMessages(){return reviewMessageService.getReviewMessages();}

    @GetMapping("/{id}")
    public ReviewMessage getReviewMessage(@PathVariable Integer id){return reviewMessageService.getReviewMessageById(id);}

    @PostMapping
    public ResponseEntity<ApiResponse> addReviewMessage(@RequestBody ReviewMessageDto reviewMessageDto){
        ApiResponse apiResponse = reviewMessageService.addReviewMessage(reviewMessageDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editReviewMessage(@RequestBody ReviewMessageDto reviewMessageDto,
                                                  @PathVariable Integer id){
        ApiResponse apiResponse = reviewMessageService.editReviewMessage(reviewMessageDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReviewMessage(@PathVariable Integer id){
        ApiResponse apiResponse = reviewMessageService.deleteReviewMessage(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
