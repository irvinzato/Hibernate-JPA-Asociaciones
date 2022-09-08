package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Factura;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBiDireccional {
  public static void main(String[] args) {
    System.out.println("CLASE CLIENTE TIENE RELACIÓN INVERSA CON FACTURA, relación bi direccional entre tablas");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      Cliente client = new Cliente("Hades", "Infra");
      client.setWayToPay("credito");

      Factura facture1 = new Factura("Compra de alimentos", 2000L);
      Factura facture2 = new Factura("Compra de tecnología", 22000L);
      System.out.println("También puedo utilizar método que creé en Cliente 'addFacture' para reducir lineas de código en esta clase principal");
      client.getListFactures().add(facture1);
      client.getListFactures().add(facture2);

      System.out.println("Importante también modificar facturas por la relación bi direccional que tienen - si no se modifican no guarda el 'id_cliente' en tabla facturas");
      facture1.setClient(client);
      facture2.setClient(client);

      em.persist(client);   //Por cascade se guarda primero el Cliente, después las facturas
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
