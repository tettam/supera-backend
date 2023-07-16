package br.com.banco.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
public class Conta {
  
  @Id
  @Column(name = "idConta")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nomeResponsavel")
  private String nome;

  @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
  List<Transferencia> transferencias;
}
