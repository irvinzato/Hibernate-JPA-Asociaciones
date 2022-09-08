package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

//EJEMPLO TOTALMENTE DIFERENTE DE LOS ANTERIORES PARA TRABAJAR CON "MANYTOMANY"
@Entity
@Table(name = "cursos")
public class Curso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "titulo")
  private String tittle;

  @Column(name = "profesor")
  private String teacher;

  public Curso() {
  }

  public Curso(String course, String teacher) {
    this.tittle = course;
    this.teacher = teacher;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCourse() {
    return tittle;
  }

  public void setCourse(String course) {
    this.tittle = course;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  @Override
  public String toString() {
    return "Cursos{" +
            "id=" + id +
            ", course='" + tittle + '\'' +
            ", teacher='" + teacher + '\'' +
            '}';
  }
}
