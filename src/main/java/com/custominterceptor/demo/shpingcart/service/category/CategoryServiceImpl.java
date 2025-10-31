package com.custominterceptor.demo.shpingcart.service.category;

import com.custominterceptor.demo.shpingcart.exception.AlreadyExistException;
import com.custominterceptor.demo.shpingcart.exception.ResourceNotFoundException;
import com.custominterceptor.demo.shpingcart.model.Category;
import com.custominterceptor.demo.shpingcart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c-> !categoryRepository
                .existsByName(c.getName()))
                .map(categoryRepository :: save).orElseThrow(()-> new AlreadyExistException(category.getName()+" Category Already Exist"));
    }

    @Override
    public Category updateCategory(Category category, Long id ) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory->{
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository :: delete, ()->{
            throw new ResourceNotFoundException("Resource Not Found");
        });
    }
}
