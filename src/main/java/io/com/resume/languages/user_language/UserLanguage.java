package io.com.resume.languages.user_language;

import io.com.resume.languages.language.Language;
import io.com.resume.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    private int level;

    public UserLanguage() {
    }

    public UserLanguage(Long id, Language language, int level) {
        this.id = id;
        this.language = language;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLanguage that = (UserLanguage) o;
        return level == that.level && Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, language, level);
    }
}
