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
      System.out.println("Primer transacción - Crear un Cliente nuevo y asignarle facturas");
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

      System.out.println("Segunda transacción - Eliminar factura a Cliente, importante la misma relación bi direccional para quitar de la lista y dejar null la factura Cliente");
      System.out.println("Ocupo segunda opción, utilizando equals en Factura y debo conocer que ID tiene porque no esta dentro del contexto - V518");
      em.getTransaction().begin();
      Factura factureRemove = new Factura("Compra de alimentos", 2000L);
      factureRemove.setId(1L);
      client.getListFactures().remove(factureRemove);   //Puedo simplificar estos dos pasos en un método de "Cliente"(Lo implemente)
      factureRemove.setClient(null);
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
