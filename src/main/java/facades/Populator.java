///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package facades;
//
//
//import javax.persistence.EntityManagerFactory;
//
//import utils.EMF_Creator;
//
///**
// * @author tha
// */
//public class Populator {
//    public static void populate() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        PersonFacade fe = PersonFacade.getPersonFacade(emf);
//        PersonDTO p1 = fe.create(new PersonDTO(new Person("Jens", 45)));
//        HobbyDTO h1 = fe.createHobby(new HobbyDTO(new Hobby("Fodbold", "Fodbold er en boldspil")));
//        fe.addHobby(p1.getId(), h1.getId());
//
//    }
//
//    public static void removeHobbyFromPersonTest() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        PersonFacade fe = PersonFacade.getPersonFacade(emf);
//        fe.removeHobbyFromPerson(1L, 1L);
//
//
//    }
//
//
//
//    public static void main(String[] args) {
//        populate();
//    }
//}
