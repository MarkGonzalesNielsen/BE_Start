package entities;
import javax.persistence.*;
import java.util.*;

@Table(name = "assistant")
@Entity
@NamedQuery(name = "Assistant.deleteAllRows", query = "DELETE from Assistant")
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "experience")
    private String experience;

    @Column(name = "pricePerHour")
    private String pricePerHour;

//    @ManyToOne
//    @JoinColumn(name = "user_name")
//    private User user;

    //    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
//    private List<Car> cars = new ArrayList<>();

    @ManyToMany(mappedBy = "assistants")
    private List<Booking> bookings;


    public Assistant() {
    }


    public Assistant(String name, String language, String experience, String pricePerHour) {
        this.name = name;
        this.language = language;
        this.experience = experience;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
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
        Assistant assistant = (Assistant) o;
        return Objects.equals(id, assistant.id) && Objects.equals(name, assistant.name) && Objects.equals(language, assistant.language) && Objects.equals(experience, assistant.experience) && Objects.equals(pricePerHour, assistant.pricePerHour) && Objects.equals(bookings, assistant.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, language, experience, pricePerHour, bookings);
    }


}