package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

//Importante añadir en "persistence.xml" las clases involucradas para las tablas en DB
@Entity
@Table(name = "direcciones")
public class Direccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "calle")
  private String street;

  @Column(name = "numero")
  private Integer num;

  public Direccion() {
  }

  public Direccion(String street, Integer num) {
    this.street = street;
    this.num = num;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  @Override
  public String toString() {
    return "Direccion{" +
            "id=" + id +
            ", street='" + street +
            ", num=" + num +
            '}';
  }
}
