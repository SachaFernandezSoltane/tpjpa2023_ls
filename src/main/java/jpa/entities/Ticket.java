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

    @ManyToOne
    private Status status;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy="ticketList")
    private List<Tag> tagList = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(String name, String description, Status status,User user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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