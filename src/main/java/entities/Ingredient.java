package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "ingredient")
@Entity
@NamedQuery(name = "Ingredient.deleteAllRows", query = "DELETE from Ingredient")

public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST)
//    private List<RecipeIngredient> recipeIngredients;


    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
//    public List<RecipeIngredient> getRecipeIngredients() {
//        return recipeIngredients;
//    }
//    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
//        this.recipeIngredients = recipeIngredients;
//    }

    //    public int hashCode() {
//        return Objects.hash(id, name, recipeIngredients);
//    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

