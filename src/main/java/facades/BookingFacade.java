package facades;

import dtos.BookingDTO;
import entities.Booking;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class BookingFacade {

    private static EntityManagerFactory emf;
    private static BookingFacade instance;

    private BookingFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static BookingFacade getBookingFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookingFacade();
        }
        return instance;
    }


    public List<BookingDTO> getAllBookings() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BookingDTO> query = em.createQuery("SELECT new dtos.BookingDTO(b) FROM Booking b", BookingDTO.class);
            List<BookingDTO> bookingDTOS = query.getResultList();
            return bookingDTOS;
        } finally {
            em.close();
        }
    }

    public BookingDTO deleteBooking(int bookingId) {
        EntityManager em = emf.createEntityManager();
        Booking booking = (em.find(Booking.class, bookingId));
        try {
            em.getTransaction().begin();
            em.remove(booking);
            em.getTransaction().commit();
            return new BookingDTO(booking);
        } finally {
            em.close();
        }
    }

}

