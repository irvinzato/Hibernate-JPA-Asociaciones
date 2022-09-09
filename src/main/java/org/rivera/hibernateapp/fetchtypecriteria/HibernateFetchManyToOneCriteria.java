package org.rivera.hibernateapp.fetchtypecriteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.rivera.hibernateapp.entity.Cliente;
import org.rivera.hibernateapp.entity.Factura;
import org.rivera.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
  public static void main(String[] args) {

    EntityManager em = JpaUtil.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
    Root<Factura> factures = query.from(Factura.class);
    //factures.fetch("client", JoinType.LEFT);    //Parecido al fetch que hice en "JPQL" para optimizar. Esta opción es si solo quiero hacer un JOIN

    System.out.println("Hago un JOIN a mi primer tabla 'facturas' para que cargue 'clientes', después a 'clientes' le hago otro JOIN para que cargue 'clientes_detalles'");
    System.out.println("Queda optimizado a una sola consulta");
    Join<Factura, Cliente> clientsFetch = (Join) factures.fetch("client", JoinType.LEFT);    //Puedo hacer cast de "Fetch" a "Join" para utilizar sus variables
    clientsFetch.fetch("clientDetail", JoinType.LEFT);

    query.select(factures).where(cb.equal(clientsFetch.get("id"), 1L));  //accedo a tabla "clientes" id 1 para traer solo facturas de dicho id
    List<Factura> listFactures = em.createQuery(query)
            .getResultList();

    System.out.println("Cambie su relación ManyToOne de facturas a LAZY para que no cargue todo desde un inicio, solo si es requerida");
    listFactures.forEach(f -> System.out.println(f.getDescription() + " Cliente: " + f.getClient().getName()));
    em.close();
  }
}
