package br.com.banco.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ValoresTransferenciasDTO {
  
  private BigDecimal somaTotal;
  private BigDecimal somaFiltrado;
  private List<TransferenciaDTO> transferencias;

  public ValoresTransferenciasDTO(BigDecimal somaTotal, BigDecimal somaFiltrado, List<TransferenciaDTO> transferencias) {
	this.somaTotal = somaTotal;
	this.somaFiltrado = somaFiltrado;
	this.transferencias = transferencias;
  }
}
