package com.capstone.assessmentportal.service.serviceimplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentportal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.SubCategoryService;

/**
 * subcategory service implementation class.
*/

@Service
public class SubCategoryServiceImplementation implements SubCategoryService {
  /**
    * autowiring subcategory repository.
  */
  @Autowired
  private SubCategoryRepo subCategoryRepo;
  /**
    * autowiring category repository.
  */
  @Autowired
  private CategoryRepo categoryRepo;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          SubCategoryServiceImplementation.class);
@Override
  public final SubCategoryDetailsDto addSubCategory(final
               SubCategoryDetailsDto subCategoryDto) {
         Optional<SubCategory> subCategory = subCategoryRepo
                 .getSubCategoryByName(subCategoryDto.getSubCategoryName());
         if (subCategory.isPresent()) {
             logger.error(ValidationMessage
                     .QUIZ_ALREADYEXISTS);
             throw new AlreadyExistsException(ValidationMessage
                     .QUIZ_ALREADYEXISTS);
         }
          SubCategory subCategoryObj = new SubCategory();
          subCategoryObj.setSubCategoryId(subCategoryDto.getSubCategoryId());
          subCategoryObj.setSubCategoryName(subCategoryDto
                  .getSubCategoryName());
          subCategoryObj.setSubCategoryDescription(subCategoryDto
                        .getSubCategoryDescription());
          subCategoryObj.setTimeLimitInMinutes(subCategoryDto
                        .getTimeLimitInMinutes());
          Category category = categoryRepo.findById(
                  subCategoryDto.getCategoryId()).orElseThrow(() ->
                   new NoSuchElementException(ValidationMessage
                           .CATEGORY_NOSUCHELEMENT));
          subCategoryObj.setCategory(category);
          logger.info(ValidationMessage.QUIZ_ADDED);
          subCategoryRepo.save(subCategoryObj);
          return subCategoryDto;
  }
  @Override
  public final List<SubCategoryDetailsDto> getSubCategories() {
    List<SubCategory> listOfSubCategories = subCategoryRepo.findAll();
      logger.info(ValidationMessage.QUIZ_RETRIEVED);
      return listOfSubCategories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
  }
  /**
   * converting entity to dto for get all method.
   * @return subCategoryDto
   * @param subCategory subCategory
  */
  private SubCategoryDetailsDto convertEntityToDto(final
                      SubCategory subCategory) {
    logger.info(ValidationMessage.QUIZ_LOGGER_MSG);
    SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
    subCategoryDto.setSubCategoryId(subCategory.getSubCategoryId());
    subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
    subCategoryDto.setSubCategoryDescription(subCategory
              .getSubCategoryDescription());
    subCategoryDto.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
    subCategoryDto.setCategoryId(subCategory.getCategory().getCategoryId());
    return subCategoryDto;
  }
  @Override
  public final SubCategoryDetailsDto getSubCategoryById(final
                     Long subCategoryId) {
    SubCategory subCategory = subCategoryRepo.findById(subCategoryId)
         .orElseThrow(() -> new NoSuchElementException(ValidationMessage
                 .QUIZ_NOSUCHELEMENT));
    logger.info(ValidationMessage.QUIZ_RETRIEVED_BY_ID);
    SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
    subCategoryDto.setSubCategoryId(subCategory.getSubCategoryId());
    subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
    subCategoryDto.setSubCategoryDescription(subCategory
                        .getSubCategoryDescription());
    subCategoryDto.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
    subCategoryDto.setCategoryId(subCategory.getCategory().getCategoryId());
    return subCategoryDto;
  }
  @Override
  public final SubCategoryDetailsDto updateSubCategory(final
                    SubCategoryDetailsDto subCategoryDto,
                    final Long subCategoryId) {
    SubCategory quiz = subCategoryRepo
                 .findById(subCategoryId).orElseThrow(
                         () -> new NoSuchElementException(ValidationMessage
                                 .QUIZ_NOSUCHELEMENT));
    categoryRepo.findById(
            subCategoryDto.getCategoryId()).orElseThrow(() ->
             new NoSuchElementException(ValidationMessage
                     .CATEGORY_NOSUCHELEMENT));
    Optional<SubCategory> subCategory = subCategoryRepo
            .getSubCategoryByName(subCategoryDto.getSubCategoryName());
    boolean isSubCategoryPresent = subCategory.isPresent();
    if (!quiz.getSubCategoryName().equals(subCategoryDto
            .getSubCategoryName()) && isSubCategoryPresent) {
        logger.error(ValidationMessage.QUIZ_ALREADYEXISTS);
        throw new AlreadyExistsException(ValidationMessage
                .QUIZ_ALREADYEXISTS);
    }
    quiz.setSubCategoryName(subCategoryDto.getSubCategoryName());
    quiz.setSubCategoryDescription(subCategoryDto
           .getSubCategoryDescription());
    quiz.setTimeLimitInMinutes(subCategoryDto.getTimeLimitInMinutes());
    logger.info(ValidationMessage.QUIZ_UPDATED);
    subCategoryRepo.save(quiz);
    return subCategoryDto;
  }
  @Override
  public final void deleteSubCategory(final Long subCategoryId) {
    subCategoryRepo
            .findById(subCategoryId).orElseThrow(
                    () -> new NoSuchElementException(ValidationMessage
                            .QUIZ_NOSUCHELEMENT));
    logger.info(ValidationMessage.QUIZ_DELETED);
    subCategoryRepo.deleteById(subCategoryId);
  }
  @Override
  public final List<SubCategoryDetailsDto> getSubCategoryByCategoryId(final
                   Long categoryId) {
    categoryRepo.findById(categoryId).orElseThrow(
            () -> new NoSuchElementException(ValidationMessage
                    .CATEGORY_NOSUCHELEMENT));
    List<SubCategory> listOfSubCategories = subCategoryRepo
                 .getSubCategoryByCategoryId(categoryId);
    logger.info(ValidationMessage.QUIZ_RETRIEVED_BY_CATEGORY_ID);
    return listOfSubCategories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
  }
}
