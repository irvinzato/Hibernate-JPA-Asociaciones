package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Factura;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBiDireccionalFind {
  public static void main(String[] args) {
    System.out.println("CLASE CLIENTE TIENE RELACIÓN INVERSA CON FACTURA, relación bi direccional entre tablas");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      Cliente client = em.find(Cliente.class, 8L);

      Factura facture1 = new Factura("Compra de alimentos", 2000L);
      Factura facture2 = new Factura("Compra de tecnología", 22000L);
      System.out.println("Utilizo método que creé en Cliente 'addFacture' para reducir lineas de código en esta clase principal");
      System.out.println("Importante también modificar facturas por la relación bi direccional que tienen - si no se modifican no guarda el 'id_cliente' en tabla facturas");

      client.addFacture(facture1);
      client.addFacture(facture2);

      em.merge(client);   //Es opcional porque el commit detecta que modifique un Cliente
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
