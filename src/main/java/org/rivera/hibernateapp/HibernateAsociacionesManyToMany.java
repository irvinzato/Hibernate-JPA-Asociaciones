package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Alumno;
import org.rivera.hibernateapp.entity.Curso;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
  public static void main(String[] args) {
    System.out.println("Ejemplo con tablas Alumno y Curso - Uni direccional");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      em.getTransaction().begin();
      Alumno student1 = new Alumno("Irving", "Rivera");
      Alumno student2 = new Alumno("Angeles", "Lopez");

      Curso course1 = new Curso("Java a experto", "Andres");
      Curso course2 = new Curso("Angular a experto", "Fernando");

      student1.getListCourses().add(course1);
      student1.getListCourses().add(course2);
      student2.getListCourses().add(course2);

      em.persist(student1);
      em.persist(student2);

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
