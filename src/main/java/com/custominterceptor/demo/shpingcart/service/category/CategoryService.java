package com.custominterceptor.demo.shpingcart.service.category;

import com.custominterceptor.demo.shpingcart.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategory();

    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);

    void deleteCategory(Long id);
}
