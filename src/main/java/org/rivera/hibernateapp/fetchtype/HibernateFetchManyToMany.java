package org.rivera.hibernateapp.fetchtype;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Alumno;
import org.rivera.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {
  public static void main(String[] args) {

    EntityManager em = JpaUtil.getEntityManager();

    List<Alumno> students = em.createQuery("SELECT DISTINCT a from Alumno a LEFT OUTER JOIN FETCH a.listCourses", Alumno.class)
                    .getResultList();

    System.out.println("Como es Lazy, hace una consulta por cada Alumno para ver sus cursos, por eso optimizo con JOIN FETCH");

    students.forEach( a -> System.out.println(a.getName() + a.getListCourses()) );

    em.close();
  }
}
