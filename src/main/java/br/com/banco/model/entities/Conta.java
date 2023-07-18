package br.com.banco.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Conta {
  
  @Id
  @Column(name = "idConta")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo não pode estar em branco")
  @Size(message = "Campo máximo de 50 caracteres",max = 50)
  @Column(name = "nomeResponsavel")
  private String nome;

  @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
  List<Transferencia> transferencias;
}
  