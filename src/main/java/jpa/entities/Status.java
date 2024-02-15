package jpa.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userStatus;

    @OneToMany(mappedBy = "status",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();

    public Status() {
        this.userStatus = "OPEN";
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }
}