package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Direccion;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
  public static void main(String[] args) {
    System.out.println("Relación un cliente NUEVO muchas direcciones, puedo cambiar la estructura de las tablas según la anotación @JoinColumn");
    EntityManager em = JpaUtil.getEntityManager();

   try {
     em.getTransaction().begin();

     Cliente client = new Cliente("Emperador", "Jade");
     client.setWayToPay("paypal");

     Direccion direccion1 = new Direccion("Hong", 2);
     Direccion direccion2 = new Direccion("Clish lomas", 212);
     Direccion direccion3 = new Direccion("Rufamina", 14);

     client.getListAddress().add(direccion1);
     client.getListAddress().add(direccion2);
     client.getListAddress().add(direccion3);

     em.persist(client);  //Gracias a la configuración "cascade" en anotación de Cliente se guarda el Cliente y también sus direcciones


     em.getTransaction().commit();
   } catch ( Exception e ){
    em.getTransaction().rollback();
    e.printStackTrace();
   } finally {
    em.close();
   }
  }
}
