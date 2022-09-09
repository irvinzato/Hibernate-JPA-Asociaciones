package org.rivera.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rivera.hibernateapp.entity.Alumno;
import org.rivera.hibernateapp.entity.Curso;
import org.rivera.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyBiDireccionalFind {
  public static void main(String[] args) {
    System.out.println("Ejemplo con tablas Alumno y Curso - BI direccional con alumnos existentes");
    System.out.println("DEBE AGREGAR Y ELIMINAR EN AMBOS SENTIDOS !");
    EntityManager em = JpaUtil.getEntityManager();

    try {
      System.out.println("Primer transacción para obtener alumnos y cursos");
      em.getTransaction().begin();
      Alumno student1 = em.find(Alumno.class, 1L);
      Alumno student2 = em.find(Alumno.class, 2L);

      Curso course1 = em.find(Curso.class,1L);
      Curso course2 = em.find(Curso.class, 2L);
      //Dentro del método "addCourse" tengo la relación para agregar al curso el alumno también
      student1.addCourse(course1);
      student1.addCourse(course2);
      student2.addCourse(course2);

      em.merge(student1);
      em.merge(student2);

      em.getTransaction().commit();
      System.out.println(student1);
      System.out.println(student2);

      System.out.println("Segunda transacción para borrar curso, SOLO EL CURSO DEL ALUMNO ! no el curso");
      em.getTransaction().begin();
      Curso courseRemove = new Curso("FlexBox", "Dalto");
      courseRemove.setId(2L);
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
