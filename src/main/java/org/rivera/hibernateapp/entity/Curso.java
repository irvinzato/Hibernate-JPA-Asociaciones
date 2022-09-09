package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  @ManyToMany(mappedBy = "listCourses") //Porque cursos es la clase que mapea a la clase principal(Alumno)
  private List<Alumno> listStudents;

  public Curso() {
    this.listStudents = new ArrayList<>();
  }

  public Curso(String course, String teacher) {
    this();
    this.tittle = course;
    this.teacher = teacher;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public List<Alumno> getListStudents() {
    return listStudents;
  }

  public void setListStudents(List<Alumno> listStudents) {
    this.listStudents = listStudents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Curso curso = (Curso) o;
    return Objects.equals(id, curso.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
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
