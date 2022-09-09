package org.rivera.hibernateapp.fetchtypejpql;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
  public static void main(String[] args) {
    System.out.println("Por defecto las anotaciones que terminan en 'Many' son Lazy y las que terminan en One son 'Eager'");
    System.out.println("Lazy hace consultas solo cuando son utilizadas, 'Eager' siempre carga todo aunque no se utilice(significa entusiasta)");
    System.out.println("La consulta personalizada carga una consulta mas, el OneToOne y la tabla de direcciones hasta que es utilizada");
    EntityManager em = JpaUtil.getEntityManager();

    System.out.println("Para optimizar y reducir las consultas hechas, puedo hacer solo una utilizando los 'left join' en las tablas relacionadas de Clientes");
    System.out.println("'LEFT OUTER JOIN' Trae todos los clientes aunque no tengan direcciones enlazadas");
    System.out.println("'INNER OUTER JOIN' Trae todos los clientes solo si tienen direcciones enlazadas, si no da error. Por eso suele ser mejor LEFT");
    System.out.println("'OUTER' Es opcional");
    System.out.println("'FETCH' Es para poblar direcciones en el objeto Cliente");
    Cliente client = em.createQuery("SELECT c FROM Cliente c LEFT OUTER JOIN FETCH c.listAddress LEFT JOIN FETCH c.clientDetail WHERE c.id=:id", Cliente.class)
                    .setParameter("id", 1L)
                    .getSingleResult();
    System.out.println(client.getName() + client.getListAddress());

    em.close();
  }
}
