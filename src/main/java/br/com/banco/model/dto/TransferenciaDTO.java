package br.com.banco.model.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.beans.BeanUtils;

import br.com.banco.model.entities.Transferencia;
import lombok.Data;

@Data
public class TransferenciaDTO {

  private ZonedDateTime dataTransferencia;
  private BigDecimal valor;
  private String tipo;
  private String nomeOperadorTransicao;

  public TransferenciaDTO(Transferencia transferencia) {
    this.dataTransferencia = transferencia.getDataTransferencia();
    this.valor = transferencia.getValor();
    this.tipo = transferencia.getTipo();
    this.nomeOperadorTransicao = transferencia.getNomeOperadorTransacao();
  }

  public static Transferencia converterTransferencia(TransferenciaDTO transferenciaDTO) {
    Transferencia transferencia = new Transferencia();
    BeanUtils.copyProperties(transferenciaDTO, transferencia);
    return transferencia;
  }
}