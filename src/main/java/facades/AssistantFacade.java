package facades;

import dtos.AssistantDTO;
import dtos.IngredientDTO;
import entities.Assistant;
import entities.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class AssistantFacade {

    private static EntityManagerFactory emf;
    private static AssistantFacade instance;

    private AssistantFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static AssistantFacade getAssistantFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssistantFacade();
        }
        return instance;
    }
    

    public List<AssistantDTO> getAllAssistants() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AssistantDTO> query = em.createQuery("SELECT new dtos.AssistantDTO(a) FROM Assistant a", AssistantDTO.class);
            List<AssistantDTO> assistantDTOS = query.getResultList();
            return assistantDTOS;
        } finally {
            em.close();
        }
    }

    public AssistantDTO createAssistant(AssistantDTO assistantDTO) {
        EntityManager em = emf.createEntityManager();
        Assistant assistant = new Assistant(assistantDTO.getName(), assistantDTO.getLanguage(), assistantDTO.getExperience(), assistantDTO.getPricePerHour());
        try{
            em.getTransaction().begin();
            em.persist(assistant);
            em.getTransaction().commit();
            return new AssistantDTO(assistant);
        } finally {
            em.close();
        }
    }





}

