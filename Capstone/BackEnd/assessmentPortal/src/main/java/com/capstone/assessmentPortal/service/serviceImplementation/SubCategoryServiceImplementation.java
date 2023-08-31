package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
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
  @Override
  public final SubCategoryDetailsDto addSubCategory(final
               SubCategoryDetailsDto subCategory) {
    Optional<SubCategory> existingSubCategory = subCategoryRepo
             .getSubCategoryByName(subCategory.getSubCategoryName());
    if (existingSubCategory.isPresent()) {
      throw new AlreadyExistsException();
    } else {
      if (subCategory.getSubCategoryName().isEmpty()
           || subCategory.getCategoryId() == 0
           || subCategory.getTimeLimitInMinutes().isEmpty()) {
        throw new InputEmptyException();
      } else {
          SubCategory newSubCategory = new SubCategory();
          newSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
          newSubCategory.setSubCategoryDescription(subCategory
                        .getSubCategoryDescription());
          newSubCategory.setTimeLimitInMinutes(subCategory
                        .getTimeLimitInMinutes());
          Category existingCategory = categoryRepo.findById(
                   subCategory.getCategoryId()).orElseThrow(() ->
                   new NotFoundException("Category not found"));
          newSubCategory.setCategory(existingCategory);
          subCategoryRepo.save(newSubCategory);
          return subCategory;
      }
    }
  }
  @Override
  public final List<SubCategoryDetailsDto> getAllSubCategories() {
    List<SubCategory> listOfSubCategories = subCategoryRepo.findAll();
    if (listOfSubCategories.size() == 0) {
      throw new EmptyListException();
    } else {
      return listOfSubCategories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
  }
  /**
   * converting entity to dto for get all method.
   * @return subCategoryDto
   * @param subCategory subCategory
  */
  private SubCategoryDetailsDto convertEntityToDto(final
                      SubCategory subCategory) {
    SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
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
         .orElseThrow(() -> new NoSuchElementException("Cannot "
         + "find Subcategory with id: " + subCategoryId));
    SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
    subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
    subCategoryDto.setSubCategoryDescription(subCategory
                        .getSubCategoryDescription());
    subCategoryDto.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
    subCategoryDto.setCategoryId(subCategory.getCategory().getCategoryId());
    return subCategoryDto;
  }
  @Override
  public final SubCategoryDetailsDto updateSubCategory(final
                    SubCategoryDetailsDto subCategory,
                    final Long subCategoryId) {
    SubCategory existingquiz = subCategoryRepo
                 .findById(subCategoryId).orElse(null);
    if (existingquiz != null) {
      existingquiz.setSubCategoryName(subCategory.getSubCategoryName());
      existingquiz.setSubCategoryDescription(subCategory
               .getSubCategoryDescription());
      existingquiz.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
      if (existingquiz.getSubCategoryName().isEmpty()
       || existingquiz.getTimeLimitInMinutes().isEmpty()) {
        throw new InputEmptyException();
      } else {
          subCategoryRepo.save(existingquiz);
          return subCategory;
      }
    } else {
       throw new NoSuchElementException();
    }
  }
  @Override
  public final void deleteSubCategory(final Long subCategoryId) {
    SubCategory existingquiz = subCategoryRepo
            .findById(subCategoryId).orElse(null);
    if (existingquiz == null) {
      throw new NoSuchElementException();
    } else {
      subCategoryRepo.deleteById(subCategoryId);
    }
  }
  @Override
  public final List<SubCategoryDetailsDto> getSubCategoryByCategoryId(final
                   Long categoryId) {
    Category existingCategory = categoryRepo.findById(categoryId).orElse(null);
    if (existingCategory == null) {
      throw new NoSuchElementException();
    } else {
      List<SubCategory> listOfSubCategories = subCategoryRepo
                 .getSubCategoryByCategoryId(categoryId);
      if (listOfSubCategories.size() == 0) {
        throw new EmptyListException();
      } else {
        return listOfSubCategories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
      }
    }
  }
}
