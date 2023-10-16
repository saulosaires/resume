package io.com.resume.link;

import io.com.resume.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    private String url;

    public Link() {
    }

    public Link(Long id, String label, String url) {
        this.id = id;
        this.label = label;
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(id, link.id) && Objects.equals(label, link.label) && Objects.equals(url, link.url) && Objects.equals(user, link.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, url, user);
    }
}
