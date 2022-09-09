package org.rivera.hibernateapp.fetchtype;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
  public static void main(String[] args) {
    System.out.println("Nunca se debe usar fetch 'Eager' en Many´s");
    System.out.println("Mejoro el rendimiento de las consultas del resultList");
    System.out.println("DISTINCT para evitar que si un usuario tiene varias direcciones lo de mas de 1 vez ");
    System.out.println("LEFT OUTER JOIN FETCH Reduce las consultas para hacerlo en una por cada tabla que quiera obtener datos");

    EntityManager em = JpaUtil.getEntityManager();

    List<Cliente> clients = em.createQuery("SELECT DISTINCT c FROM Cliente c LEFT OUTER JOIN FETCH c.listAddress LEFT OUTER JOIN FETCH c.clientDetail", Cliente.class)
                    .getResultList();

    clients.forEach(c -> System.out.println(c.getName() + c.getListAddress()));
    em.close();

  }
}
