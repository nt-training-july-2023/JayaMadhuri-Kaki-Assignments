package com.capstone.assessmentPortal.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * users entity class.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Users {
    /**
     * user id autogenerated attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    /**
     * first name of user attribute.
     */
    private String firstName;
    /**
     * last name of user attribute.
     */
    private String lastName;
    /**
     * date of birth(dd-mm-yyyy) attribute.
     */
    private String dateOfBirth;
    /**
     * user gender attribute.
     */
    private String gender;
    /**
     * user email attribute.
     */
    private String emailId;
    /**
     * user password attribute.
     */
    private String password;
    /**
     * role of user attribute.
     */
    private String userType = "Student";
    /**
     * list of results of user attribute.
     */
    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Results> results = new ArrayList<>();
    /**
     * get results.
     * @return results
     */
    public final List<Results> getResults() {
        return new ArrayList<>(results);
    }
    /**
     * set results.
     * @param res res
     */
    public final void setResults(final List<Results> res) {
        this.results = new ArrayList<>(res);
    }
    /**
     * parameter constructor for users.
     * @param name name
     * @param userid userid
     */
    public Users(final String name, final Long userid) {
        this.userId = userid;
        this.firstName = name;
    }
    /**
     * parameter constructor for users.
     * @param userid userid
     * @param firstname firstname
     * @param lastname lastname
     * @param dateofBirth dateofBirth
     * @param genDer genDer
     * @param emailid emailid
     */
    public Users(final Long userid, final String firstname,
            final String lastname, final String dateofBirth,
            final String genDer, final String emailid) {
        this.userId = userid;
        this.firstName = firstname;
        this.lastName = lastname;
        this.dateOfBirth = dateofBirth;
        this.gender = genDer;
        this.emailId = emailid;
    }
}
