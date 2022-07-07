package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Category;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.CategoryDto;
import uz.pdp.pcmarket.repository.CategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getCategories(){return categoryRepo.findAll();}

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik parent category mavjud emas!",false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        category.setCategory(optionalCategory.get());
        categoryRepo.save(category);
        return new ApiResponse("Category qo'shildi!",true);
    }

    public ApiResponse editCategory(CategoryDto categoryDto,Integer id){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik category mavjud emas!",false);
        }
        Optional<Category> optionalParentCategory = categoryRepo.findById(categoryDto.getCategoryId());
        if(!optionalParentCategory.isPresent()){
            return new ApiResponse("Bunday idlik parent category mavjud emas!",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setCategory(optionalParentCategory.get());
        category.setActive(categoryDto.isActive());
        categoryRepo.save(category);
        return new ApiResponse("Category tahrirlandi!",true);
    }

    public ApiResponse deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik category mavjud emas!",false);
        }
        Category category = optionalCategory.get();
        categoryRepo.delete(category);
        return new ApiResponse("Category o'chirildi!",true);
    }
}
