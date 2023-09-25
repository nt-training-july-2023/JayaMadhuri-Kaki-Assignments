package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
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
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          CategoryServiceImplementation.class);
  @Override
  public final CategoryDetailsDto addCategory(final
           CategoryDetailsDto categoryDto) {
    Optional<Category> category = categoryRepo
             .getCategoryByName(categoryDto.getCategoryName());
    if (category.isPresent()) {
       logger.error("Category with same name already exists");
       throw new AlreadyExistsException("Category with same name already exists");
    }
    logger.info("Category Added");
    Category categoryObj = new Category();
    categoryObj.setCategoryId(categoryDto.getCategoryId());
    categoryObj.setCategoryName(categoryDto.getCategoryName());
    categoryObj.setCategoryDescription(categoryDto.getCategoryDescription());
    categoryRepo.save(categoryObj);
    return categoryDto;
  }
  @Override
  public final List<CategoryDetailsDto> getCategories() {
    List<Category> listOfCategories = categoryRepo.findAll();
    logger.info("Retrieved all the categories");
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
    logger.info("Entity to Dto conversion in Category");
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
    logger.info("Retrieved Category By Id");
    CategoryDetailsDto categoryDto = new CategoryDetailsDto();
    categoryDto.setCategoryId(category.getCategoryId());
    categoryDto.setCategoryName(category.getCategoryName());
    categoryDto.setCategoryDescription(category.getCategoryDescription());
    return categoryDto;
  }
  @Override
  public final void deleteCategory(final Long categoryId) {
    categoryRepo.findById(categoryId).orElseThrow(
                      () -> new NoSuchElementException(
                              "Cannot find category with id: "+categoryId));
    logger.info("Category Deleted");
    categoryRepo.deleteById(categoryId);
  }
  @Override
  public final CategoryDetailsDto updateCategory(final Long categoryId,
                     final CategoryDetailsDto categoryDto) {
    Category category = categoryRepo
               .findById(categoryId).orElseThrow(
                       () -> new NoSuchElementException(
                               "Cannot find category with id: "+categoryId));
    logger.info("Category found with id");
    category.setCategoryId(categoryDto.getCategoryId());
    category.setCategoryName(categoryDto.getCategoryName());
    category.setCategoryDescription(categoryDto
            .getCategoryDescription());
    logger.info("Category Updated");
    Category categoryObj = categoryRepo.save(category);
    CategoryDetailsDto categoryDtoObj = new CategoryDetailsDto();
    categoryDtoObj.setCategoryName(categoryObj.getCategoryName());
    categoryDtoObj.setCategoryDescription(categoryObj
             .getCategoryDescription());
    return categoryDtoObj;
  }
}
