package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.ClienteDetalle;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneFind {
  public static void main(String[] args) {
    System.out.println("Relación OneToOne UniDireccional para cliente existente");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      Cliente client = em.find(Cliente.class, 3L);

      ClienteDetalle detailClient = new ClienteDetalle(true, 4000L);
      em.persist(detailClient);       //Como no tengo cascade, primero lo creo, después lo asigno

      client.setClientDetail(detailClient);
      em.getTransaction().commit();
      System.out.println(client);

    } catch ( Exception e ) {
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
  }
}
