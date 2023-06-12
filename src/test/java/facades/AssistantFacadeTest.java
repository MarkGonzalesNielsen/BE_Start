package facades;

import dtos.AssistantDTO;
import entities.Assistant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AssistantFacadeTest {

    private static EntityManagerFactory emf;
    private static AssistantFacade facade;

    Assistant assistant1;
    Assistant assistant2;


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssistantFacade.getAssistantFacade(emf);
    }

    @BeforeEach
    void setUp() {
        assistant1 = new Assistant("Ole", "Danish", "5 years", "150 kr/hour");
        assistant2 = new Assistant("Hassan", "Danish", "5 years", "150 kr/hour");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("Assistant.deleteAllRows").executeUpdate();
        em.getTransaction().commit();

        try{
            em.getTransaction().begin();
            em.persist(assistant1);
            em.persist(assistant2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllHousesTest(){
        System.out.println("Get all assistants test!");
        assertEquals(2,facade.getAllAssistants().size());
    }

    @Test
    void createAssistantTest(){
        System.out.println("create assistant test!");
        AssistantDTO assistantDTO = new AssistantDTO("Jørgen", "Danish", "5 years", "150 kr/hour");
        AssistantDTO assistant = facade.createAssistant(assistantDTO);
        assertNotNull(assistant.getId());
    }



}