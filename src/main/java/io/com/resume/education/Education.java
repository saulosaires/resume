package io.com.resume.education;

import io.com.resume.languages.language.Language;
import io.com.resume.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String school;
    private String degree;
    private String city;
    private String description;
    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    public Education() {
    }

    public Education(Long id, String school, String degree, String city, String description, LocalDate startDate, LocalDate endDate,
                     Language language) {
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.city = city;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(id, education.id) && Objects.equals(school, education.school) && Objects.equals(degree, education.degree) && Objects.equals(city, education.city) && Objects.equals(description, education.description) && Objects.equals(startDate, education.startDate) && Objects.equals(endDate, education.endDate) && Objects.equals(user, education.user) && Objects.equals(language, education.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, school, degree, city, description, startDate, endDate, user, language);
    }
}
