package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes_detalles")
public class ClienteDetalle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean prime;

  @Column(name = "puntos_acumulados")
  private Long accumulatedPoints;

  /* @OneToOne //La FK está donde contiene el atributo, por ejemplo esta clase la tiene - Comente porque quiero que la FK este en tabla "clientes"
  @JoinColumn(name = "id_cliente")
  private Cliente client; */

  public ClienteDetalle() {
  }

  public ClienteDetalle(boolean prime, Long accumulatedPoints) {
    this.prime = prime;
    this.accumulatedPoints = accumulatedPoints;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isPrime() {
    return prime;
  }

  public void setPrime(boolean prime) {
    this.prime = prime;
  }

  public Long getAccumulatedPoints() {
    return accumulatedPoints;
  }

  public void setAccumulatedPoints(Long accumulatedPoints) {
    this.accumulatedPoints = accumulatedPoints;
  }

  @Override
  public String toString() {
    return "ClienteDetalle{" +
            "id=" + id +
            ", prime=" + prime +
            ", accumulatedPoints=" + accumulatedPoints +
            '}';
  }
}
