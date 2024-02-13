package jpa.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String status;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy="ticketList")
    private List<Tag> tagList = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
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

    public void setName(String username) {
        this.name = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String email) {
        this.description = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String password) {
        this.status = password;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(Tag tag) {
        this.tagList.add(tag);
    }
}
