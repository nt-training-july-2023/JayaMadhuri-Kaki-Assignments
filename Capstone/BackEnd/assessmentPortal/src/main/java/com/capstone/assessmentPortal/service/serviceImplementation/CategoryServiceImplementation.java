package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.service.CategoryService;

/**
 * category service implementation class.
*/

@Service
public class CategoryServiceImplementation implements CategoryService {
  /**
   * autowiring category repository.
  */
  @Autowired
  private CategoryRepo categoryRepo;
  /**
   * parameter constructor.
   * @param categoryRepo2 categoryRepo2
  */
  public CategoryServiceImplementation(final CategoryRepo categoryRepo2) {
    this.categoryRepo = categoryRepo2;
}
@Override
  public final CategoryDetailsDto addCategory(final
           CategoryDetailsDto category) {
      if (category.getCategoryName().isEmpty()) {
        throw new InputEmptyException();
      } else {
     Optional<Category> existingCategory = categoryRepo
             .getCategoryByName(category.getCategoryName());
     if (existingCategory.isPresent()) {
         throw new AlreadyExistsException();
       }
      Category newCategory = new Category();
      newCategory.setCategoryId(category.getCategoryId());
      newCategory.setCategoryName(category.getCategoryName());
      newCategory.setCategoryDescription(category.getCategoryDescription());
      categoryRepo.save(newCategory);
      return category;
    }
  }
  @Override
  public final List<CategoryDetailsDto> getAllCategories() {
    List<Category> listOfCategories = categoryRepo.findAll();
      return listOfCategories.stream()
              .map(this::convertEntityToDto)
              .collect(Collectors.toList());
  }
  /**
   * converting entity to dto for get all method.
   * @return categoryDto
   * @param category category
  */
  private CategoryDetailsDto convertEntityToDto(final Category category) {
    CategoryDetailsDto categoryDto = new CategoryDetailsDto();
    categoryDto.setCategoryId(category.getCategoryId());
    categoryDto.setCategoryName(category.getCategoryName());
    categoryDto.setCategoryDescription(category.getCategoryDescription());
    return categoryDto;
  }
  @Override
  public final CategoryDetailsDto getCategoryById(final Long categoryId) {
    Category category = categoryRepo.findById(categoryId).orElseThrow(
            () -> new NoSuchElementException(
                    "Cannot find category with id: " + categoryId));
    CategoryDetailsDto categoryDto = new CategoryDetailsDto();
    categoryDto.setCategoryId(category.getCategoryId());
    categoryDto.setCategoryName(category.getCategoryName());
    categoryDto.setCategoryDescription(category.getCategoryDescription());
    return categoryDto;
  }
  @Override
  public final void deleteCategory(final Long categoryId) {
    Category existingCategory = categoryRepo
              .findById(categoryId).orElse(null);
    if (existingCategory == null) {
      throw new NoSuchElementException();
    } else {
      categoryRepo.deleteById(categoryId);
    }
  }
  @Override
  public final CategoryDetailsDto updateCategory(final Long categoryId,
                     final CategoryDetailsDto category) {
    Category existingCategory = categoryRepo
               .findById(categoryId).orElse(null);
    if (existingCategory != null) {
      existingCategory.setCategoryId(category.getCategoryId());
      existingCategory.setCategoryName(category.getCategoryName());
      existingCategory.setCategoryDescription(category
                .getCategoryDescription());
      if (existingCategory.getCategoryName().isEmpty()) {
        throw new InputEmptyException();
      }
      Category updatedCategory = categoryRepo.save(existingCategory);
      CategoryDetailsDto updatedCategoryDto = new CategoryDetailsDto();
      updatedCategoryDto.setCategoryName(updatedCategory.getCategoryName());
      updatedCategoryDto.setCategoryDescription(updatedCategory
                 .getCategoryDescription());
      return updatedCategoryDto;
      } else {
       throw new NoSuchElementException();
     }
  }
}
