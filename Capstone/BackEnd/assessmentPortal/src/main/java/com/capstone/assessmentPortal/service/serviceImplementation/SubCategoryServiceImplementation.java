package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.response.ValidationMessage;
import com.capstone.assessmentPortal.service.SubCategoryService;

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
          logger.info("Quiz Added");
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
          subCategoryRepo.save(subCategoryObj);
          return subCategoryDto;
  }
  @Override
  public final List<SubCategoryDetailsDto> getSubCategories() {
    List<SubCategory> listOfSubCategories = subCategoryRepo.findAll();
      logger.info("Retrieved all the quizes");
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
    logger.info("Entity to Dto conversion in Quiz");
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
    logger.info("Retrieved quiz details by id");
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
    logger.info("Quiz with id found");
    categoryRepo.findById(
            subCategoryDto.getCategoryId()).orElseThrow(() ->
             new NoSuchElementException(ValidationMessage
                     .CATEGORY_NOSUCHELEMENT));
    logger.info("Category with id found");
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
    logger.info("Quiz Updated");
    subCategoryRepo.save(quiz);
    return subCategoryDto;
  }
  @Override
  public final void deleteSubCategory(final Long subCategoryId) {
    subCategoryRepo
            .findById(subCategoryId).orElseThrow(
                    () -> new NoSuchElementException(ValidationMessage
                            .QUIZ_NOSUCHELEMENT));
    logger.info("Quiz Deleted");
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
    logger.info("Retrieved quizes with category id");
    return listOfSubCategories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
  }
}
