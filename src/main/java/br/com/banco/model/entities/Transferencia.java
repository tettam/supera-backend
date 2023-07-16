package br.com.banco.model.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Transferencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private ZonedDateTime dataTransferencia;
  private BigDecimal valor;
  private String tipo;
  private String nomeOperadorTransicao;

  @ManyToOne
  @JoinColumn(name = "conta_id")
  @JsonIgnore
  private Conta conta;
}
