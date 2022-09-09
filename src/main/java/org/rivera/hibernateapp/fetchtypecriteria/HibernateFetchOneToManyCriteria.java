package org.rivera.hibernateapp.fetchtypecriteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
  public static void main(String[] args) {

    EntityManager em = JpaUtil.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);   //Criterio que devuelve la Query
    Root<Cliente> clients = query.from(Cliente.class);  //Tabla donde consulto

    System.out.println("De la forma corta para obtener los clientes hace muchas consultar por las relación 'eager' que tiene con clientes_detalles");
    System.out.println("Importante que los atributos son los nombres dados en la clase, importante distinguir entre atributos y columnas de tablas");

    clients.fetch("listAddress", JoinType.LEFT);
    clients.fetch("clientDetail", JoinType.LEFT);
    query.select(clients).distinct(true);   //Para que no de dos veces los Clientes que tiene más de una dirección
    List<Cliente> listClients = em.createQuery(query)
                    .getResultList();

    System.out.println("Por cada cliente ejecuta el Lazy SIN los 'join' para sus direcciones");
    listClients.forEach( c -> System.out.println(c.getName() + " direcciones: " + c.getListAddress()));

    em.close();

  }
}
