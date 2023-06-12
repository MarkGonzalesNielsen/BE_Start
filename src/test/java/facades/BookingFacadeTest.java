package facades;
import entities.Booking;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static junit.framework.Assert.assertEquals;

class BookingFacadeTest {

    private static EntityManagerFactory emf;
    private static BookingFacade facade;

    Booking booking1;
    Booking booking2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = BookingFacade.getBookingFacade(emf);
    }

    @BeforeEach
    void setUp() {
        booking1 = new Booking("1. Jul - 15.00","1 Hour");
        booking2 = new Booking("10. Jul - 10.00","2 Hours");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("Booking.deleteAllRows").executeUpdate();
        em.getTransaction().commit();

        try{
            em.getTransaction().begin();
            em.persist(booking1);
            em.persist(booking2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllbookinsTest(){
        System.out.println("Get all bookings test!");
        assertEquals(2,facade.getAllBookings().size());
    }

    @Test
    void deleteBookingTest(){
        System.out.println("Delete booking test!");
        facade.deleteBooking(booking1.getId());
        Assertions.assertEquals(1,facade.getAllBookings().size());
    }


}