package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.ClienteDetalle;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccionalFind {
  public static void main(String[] args) {
    System.out.println("Relación bi direccional entre Cliente y ClienteDetalle - Para Cliente ya existente");
    EntityManager em = JpaUtil.getEntityManager();

    try{
      em.getTransaction().begin();
      Cliente client = em.find(Cliente.class, 3L);

      ClienteDetalle detail = new ClienteDetalle(true, 8000L);
      client.addDetailClient(detail);

      em.merge(client); //Actualizo Cliente existente, si no pongo el merge también funciona

      em.getTransaction().commit();
      System.out.println(client);
    } catch ( Exception e ) {
      em.getTransaction().rollback();
      e.printStackTrace();
    }finally {
      em.close();
    }
  }
}
