package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.Objects;

//Importante añadir en "persistence.xml" las clases involucradas para las tablas en DB
@Entity
@Table(name = "facturas")
public class Factura {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "descripcion")
  private String description;

  @Column(name = "total")
  private Long total;

  @ManyToOne(fetch = FetchType.LAZY)  //Muchas Facturas, un cliente
  @JoinColumn(name = "id_cliente") //Asigno de forma manual el nombre de la llave foránea
  private Cliente client;   //Importante la llave foránea

  public Factura() {
  }

  public Factura(String description, Long total) {
    this.description = description;
    this.total = total;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Cliente getClient() {
    return client;
  }

  public void setClient(Cliente client) {
    this.client = client;
  }

  @Override
  public String toString() {
    return "{ Factura - " +
            "id=" + id +
            ", description='" + description +
            ", total=" + total +
            " }";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Factura factura = (Factura) o;
    return Objects.equals(id, factura.id) && Objects.equals(description, factura.description) && Objects.equals(total, factura.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, total);
  }
}
