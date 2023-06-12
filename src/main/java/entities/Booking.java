package entities;

import javax.persistence.*;
import java.util.*;

@Table(name = "booking")
@Entity
@NamedQuery(name = "Booking.deleteAllRows", query = "DELETE from Booking")

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "duration")
    private String duration;

    @ManyToMany()
    private List<Assistant> assistants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


    public Booking(String date, String duration) {
        this.date = date;
        this.duration = duration;
    }

    public Booking() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(date, booking.date) && Objects.equals(duration, booking.duration) && Objects.equals(assistants, booking.assistants) && Objects.equals(car, booking.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, duration, assistants, car);
    }
}