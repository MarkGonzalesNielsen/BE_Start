package dtos;

import entities.Ingredient;

import java.util.Objects;

public class IngredientDTO {
    private int id;
    private String name;

    public IngredientDTO(String name) {
        this.name = name;
    }

    //Før jeg lavede om til int i DTO og ENTITIT
//    public IngredientDTO(Ingredient i){
//        if (i.getId() != null)
//            this.id = i.getId();
//        this.name = i.getName();
//    }


    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

