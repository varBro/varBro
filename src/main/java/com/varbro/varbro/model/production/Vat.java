package com.varbro.varbro.model.production;

import com.varbro.varbro.model.User;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Vat {

    @Id
    @GeneratedValue
    @Column(name = "vat_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private ProcessPhase processPhase;
    private int capacity;
    private LocalDate startTime;
    private LocalDate lastUpdated;

    public enum ProcessPhase {
        NOT_STARTED("Not started"),
        Malting,
        Mashing,
        Lautering,
        Boiling,
        Fermenting,
        Conditioning,
        Filtering,
        Packaging
    }

    public Vat() {}

    public Vat(int capacity) {
        this.processPhase = ProcessPhase.NOT_STARTED;
        this.capacity = capacity;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Beer getBeer() { return beer; }

    public void setBeer(Beer beer) { this.beer = beer; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public ProcessPhase getProcessPhase() { return processPhase; }

    public void setProcessPhase(ProcessPhase processPhase) { this.processPhase = processPhase; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public LocalDate getStartTime() { return startTime; }

    public void setStartTime(LocalDate startTime) { this.startTime = LocalDate.now(); }

    public LocalDate getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = LocalDate.now(); }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vat vat = (Vat) o;
        return id == vat.id &&
                capacity == vat.capacity &&
                Objects.equals(beer, vat.beer) &&
                Objects.equals(user, vat.user) &&
                processPhase == vat.processPhase &&
                Objects.equals(startTime, vat.startTime) &&
                Objects.equals(lastUpdated, vat.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beer, user, processPhase, capacity, startTime, lastUpdated);
    }

}
