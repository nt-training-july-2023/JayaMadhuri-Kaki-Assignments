package com.capstone.assessmentportal.service.serviceimplementation;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentportal.dto.CategoryDetailsDto;
import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.CategoryService;

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
       logger.error(ValidationMessage.CATEGORY_ALREADYEXISTS);
       throw new AlreadyExistsException(ValidationMessage
               .CATEGORY_ALREADYEXISTS);
    }
    logger.info(ValidationMessage.CATEGORIES_ADDED);
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
    logger.info(ValidationMessage.CATEGORIES_RETRIEVED);
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
    logger.info(ValidationMessage.CATEGORY_LOGGER_MSG);
    CategoryDetailsDto categoryDto = new CategoryDetailsDto();
    categoryDto.setCategoryId(category.getCategoryId());
    categoryDto.setCategoryName(category.getCategoryName());
    categoryDto.setCategoryDescription(category.getCategoryDescription());
    return categoryDto;
  }
  @Override
  public final CategoryDetailsDto getCategoryById(final Long categoryId) {
    Category category = categoryRepo.findById(categoryId).orElseThrow(
            () -> new NoSuchElementException(ValidationMessage
                    .CATEGORY_NOSUCHELEMENT));
    logger.info(ValidationMessage.CATEGORIES_RETRIEVED_BY_ID);
    CategoryDetailsDto categoryDto = new CategoryDetailsDto();
    categoryDto.setCategoryId(category.getCategoryId());
    categoryDto.setCategoryName(category.getCategoryName());
    categoryDto.setCategoryDescription(category.getCategoryDescription());
    return categoryDto;
  }
  @Override
  public final void deleteCategory(final Long categoryId) {
    categoryRepo.findById(categoryId).orElseThrow(
                      () -> new NoSuchElementException(ValidationMessage
                              .CATEGORY_NOSUCHELEMENT));
    logger.info(ValidationMessage.CATEGORIES_DELETED);
    categoryRepo.deleteById(categoryId);
  }
  @Override
  public final CategoryDetailsDto updateCategory(final Long categoryId,
                     final CategoryDetailsDto categoryDto) {
    Category category = categoryRepo
               .findById(categoryId).orElseThrow(
                       () -> new NoSuchElementException(ValidationMessage
                               .CATEGORY_NOSUCHELEMENT));
    category.setCategoryName(categoryDto.getCategoryName());
    category.setCategoryDescription(categoryDto
            .getCategoryDescription());
    logger.info(ValidationMessage.CATEGORIES_UPDATED);
    Category categoryObj = categoryRepo.save(category);
    CategoryDetailsDto categoryDtoObj = new CategoryDetailsDto();
    categoryDtoObj.setCategoryName(categoryObj.getCategoryName());
    categoryDtoObj.setCategoryDescription(categoryObj
             .getCategoryDescription());
    return categoryDtoObj;
  }
}
