package dtos;
import entities.Assistant;
import java.util.Objects;

public class AssistantDTO {
    private int id;
    private String name;
    private String language;
    private String experience;
    private String pricePerHour;

    public AssistantDTO(Assistant assistant) {
        this.id = assistant.getId();
        this.name = assistant.getName();
        this.language = assistant.getLanguage();
        this.experience = assistant.getExperience();
        this.pricePerHour = assistant.getPricePerHour();
    }

    //Test constructor
    public AssistantDTO(String name, String language, String experience, String pricePerHour) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssistantDTO that = (AssistantDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(language, that.language) && Objects.equals(experience, that.experience) && Objects.equals(pricePerHour, that.pricePerHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, language, experience, pricePerHour);
    }
}
