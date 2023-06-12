package dtos;


import java.util.Objects;

public class AssistantDTO {
    private int id;
    private String title;
    private String description;
    private String instructions;

    public AssistantDTO(String title, String description, String instructions) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
    }

    public AssistantDTO(int id, String title, String description, String instructions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
    }

    public AssistantDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssistantDTO that = (AssistantDTO) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, instructions);
    }
}
