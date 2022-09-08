package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Factura;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToOne {
  public static void main(String[] args) {

    System.out.println("TENGO CONFIGURACIÓN 'drop-and-create' EN XML PARA CREAR Y DESTRUIR TABLAS CADA QUE EJECUTO");
    System.out.println("Facturas tiene relación inversa con Cliente - OneToMany y ManyToOne");
    EntityManager em = JpaUtil.getEntityManager();

    try{
      em.getTransaction().begin();

      System.out.println("Cliente nuevo con Factura nueva");
      Cliente client = new Cliente("Loki", "Nordico");
      client.setWayToPay("credito");
      em.persist(client);

      Factura facture = new Factura("Compra hecha de una daga", 8500L);
      facture.setClient(client);  //Se debe asignar un cliente que ya exista en la DB !
      em.persist(facture);

      System.out.println(facture);

      em.getTransaction().commit();
    } catch( Exception e ) {
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

  }
}
