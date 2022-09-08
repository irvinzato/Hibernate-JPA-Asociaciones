package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//EJEMPLO TOTALMENTE DIFERENTE DE LOS ANTERIORES PARA TRABAJAR CON "MANYTOMANY"
@Entity
@Table(name = "alumnos")
public class Alumno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre")
  private String name;

  @Column(name = "apellido")
  private String lastName;

  //Alumno es la clase principal, no utilizo "CascadeType.ALL" porque cuando elimine un alumno el curso debe persistir
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Como no le pongo @JoinColumn crea tabla intermedia
  @JoinTable(name = "alumnos_cursos", joinColumns = @JoinColumn(name = "id_alumno") //Segundo atributo FK de la tabla principal, tercer atributo FK de la tabla secundaria
            , inverseJoinColumns = @JoinColumn(name = "id_curso"), uniqueConstraints = @UniqueConstraint(columnNames = {"id_alumno", "id_curso"})) //Ultimo atributo que sean únicos los pares
  private List<Curso> listCourses;

  public Alumno() {
    this.listCourses = new ArrayList<>();
  }

  public Alumno(String name, String lastName) {
    this();
    this.name = name;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Curso> getListCourses() {
    return listCourses;
  }

  public void setListCourses(List<Curso> listCourses) {
    this.listCourses = listCourses;
  }

  @Override
  public String toString() {
    return "Alumno{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", listCourses=" + listCourses +
            '}';
  }
}
