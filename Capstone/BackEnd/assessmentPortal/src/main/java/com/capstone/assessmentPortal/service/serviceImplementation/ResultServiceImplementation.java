package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.ResultsDto;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.model.Results;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.repository.ResultRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.repository.UsersRepo;
import com.capstone.assessmentPortal.service.ResultService;

/**
 * Result service implementation class.
*/

@Service
public class ResultServiceImplementation implements ResultService {
  /**
   * autowiring result repository.
  */
  @Autowired
  private ResultRepo resultRepo;
  /**
   * autowiring finalresult of quiz repository.
  */
  @Autowired
  private FinalResultOfQuizRepo finalResultsRepo;
  /**
   * autowiring users repository.
  */
  @Autowired
  private UsersRepo usersRepo;
  /**
   * autowiring Category repository.
  */
  @Autowired
  private CategoryRepo categoryRepo;
  /**
   * autowiring subcategory repository.
  */
  @Autowired
  private SubCategoryRepo subCategoryRepo;
  /**
   * parameter constructor.
   * @param subCategoryRepo2 subCategoryRepo2
   * @param usersRepo2 usersRepo2
   * @param resultRepo2 resultRepo2
   * @param categoryRepo2 categoryRepo2
   * @param finalResultRepo finalResultRepo
  */
  public ResultServiceImplementation(final SubCategoryRepo subCategoryRepo2,
          final UsersRepo usersRepo2, final ResultRepo resultRepo2,
          final CategoryRepo categoryRepo2,
          final FinalResultOfQuizRepo finalResultRepo) {
   this.subCategoryRepo = subCategoryRepo2;
   this.usersRepo = usersRepo2;
   this.resultRepo = resultRepo2;
   this.categoryRepo = categoryRepo2;
   this.finalResultsRepo = finalResultRepo;
}
@Override
  public final ResultsDto addTemporaryResult(final ResultsDto results) {
    Users existinguser = usersRepo.findById(results
             .getStudentId()).orElse(null);
    if (existinguser == null) {
      throw new NoSuchElementException();
    } else {
      SubCategory existingquiz = subCategoryRepo.findById(
      results.getSubCategoryId()).orElse(null);
      if (existingquiz != null) {
            FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
            finalResults.setStudentId(results.getStudentId());
            Optional<Users> users = usersRepo.findById(results.getStudentId());
            finalResults.setStudentName(users.get().getFirstName()
                    + users.get().getLastName());
            Optional<SubCategory> subCategory = subCategoryRepo.findById(results
                    .getSubCategoryId());
            Optional<Category> category = categoryRepo.findById(results
                    .getCategoryId());
            finalResults.setCategoryName(category.get().getCategoryName());
            finalResults.setQuizName(subCategory.get().getSubCategoryName());
            finalResults.setMarksObtained(results.getMarksObtained());
            finalResults.setTotalMarks(results.getTotalMarks());
            finalResults.setNumOfAttemptedQuestions(results
                     .getNumOfAttemptedQuestions());
            finalResults.setTotalQuestions(results.getTotalQuestions());
            finalResults.setDateAndTime(results.getDateAndTime());
          finalResultsRepo.save(finalResults);
          Results res = new Results();
          res.setStudents(existinguser);
          res.setSubCategory(existingquiz);
          res.setTotalMarks(results.getTotalMarks());
          res.setMarksObtained(results.getMarksObtained());
          res.setNumOfAttemptedQuestions(results.getNumOfAttemptedQuestions());
          res.setTotalQuestions(results.getTotalQuestions());
          res.setDateAndTime(results.getDateAndTime());
          resultRepo.save(res);
          return results;
        } else {
          throw new NoSuchElementException();
        }
      }
    }
  @Override
  public final boolean findResultsByUserAndSubCategory(final Long userId,
           final Long subCategoryId) {
    if (userId == null || subCategoryId == null) {
      throw new InputEmptyException();
    }
    Results result = resultRepo
        .findResultsByStudentsAndSubCategory(userId, subCategoryId);
    if (result == null) {
      return false;
    }
    return true;
  }
}
