package org.rivera.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")   //Nombre de tabla sql
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)   //Indico que es AI
  private Long id;

  @Column(name = "nombre")    //Como no se llaman igual que la tabla se lo indico
  private String name;        //Importante el valor en objeto y valor en base de datos para hacer consultas

  @Column(name = "apellido")
  private String lastName;

  @Column(name = "forma_pago")
  private String wayToPay;

  @Embedded   //Que se incluye alguna otra clase(TODOS LOS ATRIBUTOS, TODO EL CONTENIDO)
  private Auditoria aud = new Auditoria();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  //Configuración para cada que se crea un Cliente creara sus dependientes(relacionados), segundo parámetro por si desvinculo una dirección quede null(Se elimine de forma automática)
  //@JoinColumn(name = "cliente_id")     //Si no pongo esta anotación Hibernate me propone una tabla intermedia para relacionar cliente y direcciones, de esta manera se crea "cliente_id" como FK en tabla direcciones
  @JoinTable(name = "clientes_direcciones", joinColumns = @JoinColumn(name = "id_cliente")  //De esta forma es como no poner las anotaciones e hibernate propone una tabla nueva
            , inverseJoinColumns = @JoinColumn(name = "id_direccion")                       //pero la ventaja es que poniendo la anotación la puedo personalizar bastante
            , uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}))
  private List<Direccion> listAddress;

  //Esta relación es "bi direccional" no lleva "@JoinColum" porque la FK está en la tabla "facturas"
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")  //"mappedBy" hace referencia a la variable que puse en "Factura", la que contiene la relación
  private List<Factura> listFactures;


  // ¡Siempre debe haber un constructor vacío! para que JPA pueda instancear la clase
  public Cliente() {
    this.listAddress = new ArrayList<>();
    this.listFactures = new ArrayList<>();
  }

  public Cliente(String name, String lastName) {
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

  public String getWayToPay() {
    return wayToPay;
  }

  public void setWayToPay(String wayToPay) {
    this.wayToPay = wayToPay;
  }

  public List<Direccion> getListAddress() {
    return listAddress;
  }

  public void setListAddress(List<Direccion> listAddress) {
    this.listAddress = listAddress;
  }

  public List<Factura> getListFactures() {
    return listFactures;
  }

  public void setListFactures(List<Factura> listFactures) {
    this.listFactures = listFactures;
  }

  public void addFacture(Factura facture) {
    this.listFactures.add(facture);
    facture.setClient(this);    //Relación inversa
  }

  public void removeFacture(Factura facture) {
    this.listFactures.remove(facture);
    facture.setClient(null);    //Relación inversa
  }

  @Override
  public String toString() {
    LocalDateTime createIn = this.aud != null ?aud.getCreateIn() :null;
    LocalDateTime editedIn = this.aud != null ?aud.getEditedIn() :null;
    return "{ Cliente " +
            "id=" + id +
            ", name='" + name +
            ", lastName='" + lastName +
            ", wayToPay='" + wayToPay +
            ", creado en = " + createIn +
            ", editado en = " + editedIn +
            ", direcciones = " + listAddress +
            ", facturas = " + listFactures +
            " }";
  }

  //Nota - V.510 Se explica como añadir una configuración en "persistence.xml" para que la tabla se elimine y se vuelva a crear al utilizarla
  //E importaciones de tablas Sql
}
