package org.rivera.hibernateapp.fetchtypejpql;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToMany {
  public static void main(String[] args) {
    System.out.println("Por defecto las anotaciones que terminan en 'Many' son Lazy y las que terminan en One son 'Eager'");
    System.out.println("Lazy hace consultas solo cuando son utilizadas, 'Eager' siempre carga todo aunque no se utilice(significa entusiasta)");
    System.out.println("Como son solo consultas no necesito el try catch");
    EntityManager em = JpaUtil.getEntityManager();

    Cliente client = em.find(Cliente.class, 1L);  //Hace solo una consulta, a menos que utilice algún "get" que involucre otra tabla
    //System.out.println(client.getListAddress());

    em.close();
  }
}
