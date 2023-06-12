package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "car")
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "registrationNumber")
    private String registrationNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "make")
    private String make;

    @Column(name = "year")
    private int year;


    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Booking> bookings;


    public Car() {
    }

    public Car(String registrationNumber, String brand, String make, int year) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && Objects.equals(registrationNumber, car.registrationNumber) && Objects.equals(brand, car.brand) && Objects.equals(make, car.make) && Objects.equals(bookings, car.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, brand, make, year, bookings);
    }
}