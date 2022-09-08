package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Alumno;
import org.rivera.hibernateapp.entity.Curso;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyFind {
  public static void main(String[] args) {
    System.out.println("Ejemplo con tablas Alumno y Curso - Uni direccional con alumnos existentes");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      Alumno student1 = em.find(Alumno.class, 1L);
      Alumno student2 = em.find(Alumno.class, 2L);

      Curso course1 = em.find(Curso.class, 1L);
      Curso course2 = em.find(Curso.class, 3L);

      student1.getListCourses().add(course1);
      student1.getListCourses().add(course2);
      student2.getListCourses().add(course2);

      em.merge(student1); //No son necesarios los merge, con el commit basta pero es para ser explícito
      em.merge(student2);

      em.getTransaction().commit();
      System.out.println(student1);
      System.out.println(student2);
    } catch (Exception e){
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

  }
}
