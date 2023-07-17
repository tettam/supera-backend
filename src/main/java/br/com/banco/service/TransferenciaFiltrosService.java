package br.com.banco.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.dto.TransferenciaDTO;

@Service
public class TransferenciaFiltrosService {
  
  @Autowired
  private TransferenciaService transferenciaService;

  public List<TransferenciaDTO> findTransferenciaFiltros(
    Long id,
    LocalDateTime dataInicio, 
    LocalDateTime dataFinal, 
    String nomeOperador) {
      List<TransferenciaDTO> transferenciaDTOs = transferenciaService.findAll(id);

      return transferenciaDTOs.stream()
        .filter(transferenciaDTOs -> (nomeOperador == null 
            || transferenciaService.findNomeOperador(nomeOperador)))
        .filter(transferenciaDTOs -> (dataInicio == null
            || transferenciaService.findDataEntrada(dataInicio)))

    }
}
