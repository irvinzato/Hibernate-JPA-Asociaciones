package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.ClienteDetalle;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccional {
  public static void main(String[] args) {
    System.out.println("Relación bi direccional entre Cliente y ClienteDetalle - Para nuevo Cliente");
    EntityManager em = JpaUtil.getEntityManager();

    try{
      em.getTransaction().begin();
      Cliente client = new Cliente("Juan", "Moguel");
      client.setWayToPay("paypal");

      ClienteDetalle detail = new ClienteDetalle(true, 8000L);
      System.out.println("Puedo utilizar el método 'addDetailClient' para simplificar estas lineas");
      client.setClientDetail(detail);
      detail.setClient(client);

      em.persist(client); //En cascada guarda el detalle

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
