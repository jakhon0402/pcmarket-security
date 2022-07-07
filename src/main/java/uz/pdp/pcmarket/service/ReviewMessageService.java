package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Review;
import uz.pdp.pcmarket.entity.ReviewMessage;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.ReviewMessageDto;
import uz.pdp.pcmarket.repository.ReviewMessageRepo;
import uz.pdp.pcmarket.repository.ReviewRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewMessageService {

    @Autowired
    ReviewMessageRepo reviewMessageRepo;

    @Autowired
    ReviewRepo reviewRepo;

    public List<ReviewMessage> getReviewMessages(){return reviewMessageRepo.findAll();}

    public ReviewMessage getReviewMessageById(Integer id){
        Optional<ReviewMessage> optionalReviewMessage = reviewMessageRepo.findById(id);
        return optionalReviewMessage.orElse(null);
    }

    public ApiResponse addReviewMessage(ReviewMessageDto reviewMessageDto){
        Optional<Review> optionalReviewMessage = reviewRepo.findById(reviewMessageDto.getReviewId());
        if(!optionalReviewMessage.isPresent()){
            return new ApiResponse("Bunday idlik review mavjud emas!",false);
        }
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setText(reviewMessageDto.getText());
        reviewMessage.setFullName(reviewMessageDto.getFullName());
        reviewMessage.setEmail(reviewMessageDto.getEmail());
        reviewMessage.setReview(optionalReviewMessage.get());
        reviewMessageRepo.save(reviewMessage);
        return new ApiResponse("ReviewMessage qo'shildi!",true);
    }

    public ApiResponse editReviewMessage(ReviewMessageDto reviewMessageDto,Integer id){
        Optional<ReviewMessage> optionalReviewMessage = reviewMessageRepo.findById(id);
        if(!optionalReviewMessage.isPresent()){
            return new ApiResponse("Bunday idlik reviewMessage mavjud emas!",false);
        }
        Optional<Review> optionalReview= reviewRepo.findById(reviewMessageDto.getReviewId());
        if(!optionalReview.isPresent()){
            return new ApiResponse("Bunday idlik parent review mavjud emas!",false);
        }
        ReviewMessage reviewMessage = optionalReviewMessage.get();
        reviewMessage.setText(reviewMessageDto.getText());
        reviewMessage.setFullName(reviewMessageDto.getFullName());
        reviewMessage.setEmail(reviewMessageDto.getEmail());
        reviewMessage.setReview(optionalReview.get());
        reviewMessageRepo.save(reviewMessage);
        return new ApiResponse("ReviewMessage tahrirlandi!",true);
    }

    public ApiResponse deleteReviewMessage(Integer id){
        Optional<ReviewMessage> optionalReviewMessage = reviewMessageRepo.findById(id);
        if(!optionalReviewMessage.isPresent()){
            return new ApiResponse("Bunday idlik reviewMessage mavjud emas!",false);
        }
        ReviewMessage reviewMessage = optionalReviewMessage.get();
        reviewMessageRepo.delete(reviewMessage);
        return new ApiResponse("ReviewMessage o'chirildi!",true);
    }
}
