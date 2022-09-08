package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Direccion;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyFind {
  public static void main(String[] args) {
    System.out.println("Relación un cliente EXISTENTE muchas direcciones, puedo cambiar la estructura de las tablas según la anotación @JoinColumn");
    EntityManager em = JpaUtil.getEntityManager();

   try {
     System.out.println("Primer transacción");
     em.getTransaction().begin();

     Cliente client = em.find(Cliente.class, 3L);

     Direccion direccion1 = new Direccion("Hong", 2);
     Direccion direccion2 = new Direccion("Clish lomas", 212);
     Direccion direccion3 = new Direccion("Rufamina", 14);

     client.getListAddress().add(direccion1);
     client.getListAddress().add(direccion2);
     client.getListAddress().add(direccion3);

     em.merge(client);  //Como ya existe el usuario lo modifico, gracias a la configuración "cascade" en anotación de Cliente se guarda el Cliente y también sus direcciones
     System.out.println(client);
     em.getTransaction().commit();

     System.out.println("Segunda transacción");
     em.getTransaction().begin();
     direccion2 = em.find(Direccion.class, 2L); //Tengo que buscarla porque no estaba dentro del contexto(Solo tiene una relación con Cliente, no existía dentro del contexto)
     client.getListAddress().remove(direccion2);
     em.getTransaction().commit();
     System.out.println(client);


   } catch ( Exception e ){
    em.getTransaction().rollback();
    e.printStackTrace();
   } finally {
    em.close();
   }
  }
}
