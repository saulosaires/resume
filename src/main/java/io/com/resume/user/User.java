package io.com.resume.user;

import io.com.resume.country.Country;
import io.com.resume.course.Course;
import io.com.resume.education.Education;
import io.com.resume.experience.Experience;
import io.com.resume.languages.user_language.UserLanguage;
import io.com.resume.link.Link;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String summary;
    private String email;
    private String telephone;
    private String website;
    private String instantMessaging;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<Link> links;

    @OneToMany(mappedBy = "user")
    private List<Education> education;

    @OneToMany(mappedBy = "user")
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    private List<Experience> experiences;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany
    Set<UserLanguage> languages;

    public User() {
    }

    public User(Long id, String name, String summary, String email, String telephone, String website, String instantMessaging,
                LocalDate birthDate, Country country) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.email = email;
        this.telephone = telephone;
        this.website = website;
        this.instantMessaging = instantMessaging;
        this.birthDate = birthDate;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInstantMessaging() {
        return instantMessaging;
    }

    public void setInstantMessaging(String instantMessaging) {
        this.instantMessaging = instantMessaging;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<UserLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<UserLanguage> languages) {
        this.languages = languages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(summary, user.summary) && Objects.equals(email, user.email) && Objects.equals(telephone, user.telephone) && Objects.equals(website, user.website) && Objects.equals(instantMessaging, user.instantMessaging) && Objects.equals(birthDate, user.birthDate) && Objects.equals(links, user.links) && Objects.equals(education, user.education) && Objects.equals(courses, user.courses) && Objects.equals(experiences, user.experiences) && Objects.equals(country, user.country) && Objects.equals(languages, user.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, summary, email, telephone, website, instantMessaging, birthDate, links, education, courses, experiences, country, languages);
    }
}
