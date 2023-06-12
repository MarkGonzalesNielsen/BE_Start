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
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "instructions")
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToMany(mappedBy = "assistants")
    private List<Booking> bookings;

//    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
//    private List<Car> cars = new ArrayList<>();



    public Assistant() {
    }

    public Assistant(String title, String description, String instructions, User user){
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(id, assistant.id) && Objects.equals(title, assistant.title) && Objects.equals(description, assistant.description) && Objects.equals(instructions, assistant.instructions) && Objects.equals(user, assistant.user) && Objects.equals(bookings, assistant.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, instructions, user, bookings);
    }
}