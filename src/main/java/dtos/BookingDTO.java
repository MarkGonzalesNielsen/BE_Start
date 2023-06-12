package dtos;

import entities.Booking;
import entities.Ingredient;

import java.util.Objects;

public class BookingDTO {
    private int id;
    private String date;
    private String duration;

    public BookingDTO(String date, String duration) {
        this.date = date;
        duration = duration;
    }

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.date = booking.getDate();
        this.duration = booking.getDuration();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDTO that = (BookingDTO) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, duration);
    }
}
