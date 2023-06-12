package dtos;


import java.util.Objects;

public class CarDTO {

    private int id;
    private String registrationNumber;
    private String brand;
    private String make;
    private int year;

    //prøv at lave int id om til long så lortet virker
//    public CarDTO(CarDTO i) {
//        if (i.getId() != null)
//            this.id = i.getId();
//        this.name = i.getName();
//    }


    public CarDTO(int id, String registrationNumber, String brand, String make, int year) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public CarDTO(String registrationNumber, String brand, String make, int year) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public CarDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return id == carDTO.id && year == carDTO.year && Objects.equals(registrationNumber, carDTO.registrationNumber) && Objects.equals(brand, carDTO.brand) && Objects.equals(make, carDTO.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, brand, make, year);
    }
}
