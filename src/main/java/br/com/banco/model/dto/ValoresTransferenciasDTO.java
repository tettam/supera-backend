package br.com.banco.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ValoresTransferenciasDTO {
  
  private BigDecimal somaTotal;
  private BigDecimal valorFiltrado;
  private List<TransferenciaDTO> transferencias;

  public ValoresTransferenciasDTO(BigDecimal somaTotal, BigDecimal valorFiltrado, List<TransferenciaDTO> transferencias) {
	this.somaTotal = somaTotal;
	this.valorFiltrado = valorFiltrado;
	this.transferencias = transferencias;
  }
}
