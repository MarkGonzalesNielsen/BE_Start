package facades;

import dtos.IngredientDTO;
import entities.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class IngredientFacade {

    private static EntityManagerFactory emf;
    private static IngredientFacade instance;

    private IngredientFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static IngredientFacade getIngredientFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new IngredientFacade();
        }
        return instance;
    }




    public List<IngredientDTO> getAllIngredients() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<IngredientDTO> query = em.createQuery("SELECT new dtos.IngredientDTO(i) FROM Ingredient i", IngredientDTO.class);
            List<IngredientDTO> ingredientDTOS = query.getResultList();
            return ingredientDTOS;
        } finally {
            em.close();
        }
    }

    public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = new Ingredient(ingredientDTO.getName());
        try{
            em.getTransaction().begin();
            em.persist(ingredient);
            em.getTransaction().commit();
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }

    //gamle updateIngredient
//    public IngredientDTO updateIngredient(IngredientDTO ingredientDTO) {
//        EntityManager em = emf.createEntityManager();
//        Ingredient ingredient = (em.find(Ingredient.class, ingredientDTO.getId()));
//        try {
//            ingredient.setName(ingredientDTO.getName());
//            em.getTransaction().begin();
//            ingredient = em.merge(ingredient);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new IngredientDTO(ingredient);
//    }

    public IngredientDTO updateIngredient(IngredientDTO ingredientDTO) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = (em.find(Ingredient.class, ingredientDTO.getId()));
        try {
            ingredient.setName(ingredientDTO.getName());
            em.getTransaction().begin();
            ingredient = em.merge(ingredient);
            em.getTransaction().commit();
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }


    public IngredientDTO deleteIngredient(int ingredientId) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = (em.find(Ingredient.class, ingredientId));
        try {
            em.getTransaction().begin();
            em.remove(ingredient);
            em.getTransaction().commit();
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }


    public IngredientDTO getIngredientById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ingredient ingredient = em.find(Ingredient.class, id);
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }
}

