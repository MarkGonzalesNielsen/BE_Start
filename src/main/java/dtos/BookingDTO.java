package dtos;

import java.util.Objects;

public class BookingDTO {
    private int id;
    private String date;
    private String Duration;

    public BookingDTO() {
    }

    public BookingDTO(int id, String date, String Duration) {
        this.id = id;
        this.date = date;
        this.Duration = Duration;
    }

    public BookingDTO(String date, String duration) {
        this.date = date;
        Duration = duration;
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
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDTO that = (BookingDTO) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(Duration, that.Duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, Duration);
    }
}
