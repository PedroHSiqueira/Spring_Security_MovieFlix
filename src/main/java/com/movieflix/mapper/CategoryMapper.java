package com.movieflix.mapper;

import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryResponse categoryResponse) {
        return Category.builder().name(categoryResponse.name()).build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
    }
}
