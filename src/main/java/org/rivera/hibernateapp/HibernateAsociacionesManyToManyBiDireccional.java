package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Alumno;
import org.rivera.hibernateapp.entity.Curso;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyBiDireccional {
  public static void main(String[] args) {
    System.out.println("Ejemplo con tablas Alumno y Curso - BI direccional con alumnos nuevos");
    System.out.println("DEBE AGREGAR Y ELIMINAR EN AMBOS SENTIDOS !");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      System.out.println("Primer transacción para crear alumnos y cursos nuevos");
      em.getTransaction().begin();
      Alumno student1 = new Alumno("Irving", "Rivera");
      Alumno student2 = new Alumno("Angeles", "Lopez");

      Curso course1 = new Curso("Java a experto", "Andres");
      Curso course2 = new Curso("Angular a experto", "Fernando");
      //Dentro del método "addCourse" tengo la relación para agregar al curso el alumno también
      student1.addCourse(course1);
      student1.addCourse(course2);
      student2.addCourse(course2);

      em.persist(student1);
      em.persist(student2);

      em.getTransaction().commit();
      System.out.println(student1);
      System.out.println(student2);

      System.out.println("Segunda transacción para borrar curso, SOLO EL CURSO DEL ALUMNO ! no el curso");
      em.getTransaction().begin();
      Curso courseRemove = new Curso("Angular a experto", "Fernando");
      courseRemove.setId(5L);
      student1.removeCourse(courseRemove);
      em.getTransaction().commit();
      System.out.println(student1);
    } catch (Exception e){
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

  }
}
