package br.com.banco.model.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Transferencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo obrigatório")
  private ZonedDateTime dataTransferencia;

  @NotNull(message = "Valor obrigatório")
  @DecimalMin(value = "0.00", inclusive = true)
  private BigDecimal valor;

  @NotBlank(message = "Campo obrigatório")
  @Size(message = "Campo máximo de 15 caracteres",max = 15)
  private String tipo;

  @NotNull
  private String nomeOperadorTransacao;

  @ManyToOne
  @JoinColumn(name = "conta_id")
  @JsonIgnore
  private Conta conta;
}
