package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
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
  @Override
  public final Category addCategory(final Category category) {
    Optional<Category> existingCategory = categoryRepo
              .getCategoryByName(category.getCategoryName());
    if (existingCategory.isPresent()) {
      throw new AlreadyExistsException();
    } else {
      if (category.getCategoryName().isEmpty()) {
        throw new InputEmptyException();
      }
      return categoryRepo.save(category);
    }
  }
  @Override
  public final List<Category> getAllCategories() {
    List<Category> listOfCategories = categoryRepo.findAll();
    if (listOfCategories.size() == 0) {
      throw new EmptyListException();
    } else {
      return listOfCategories;
    }
  }
  @Override
  public final Category getCategoryById(final Long categoryId) {
    return categoryRepo.findById(categoryId).orElseThrow(
             () -> new NoSuchElementException(
              "Cannot find category with id: " + categoryId));
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
  public final Category updateCategory(final Long categoryId,
                     final Category category) {
    Category existingCategory = categoryRepo
               .findById(categoryId).orElse(null);
    if (existingCategory != null) {
      existingCategory.setCategoryName(category.getCategoryName());
      existingCategory.setCategoryDescription(category
                .getCategoryDescription());
      if (existingCategory.getCategoryName().isEmpty()) {
        throw new InputEmptyException();
      }
      return categoryRepo.save(existingCategory);
      } else {
       throw new NoSuchElementException();
     }
  }
}
