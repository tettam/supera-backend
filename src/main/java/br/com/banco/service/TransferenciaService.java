package br.com.banco.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.dto.TransferenciaDTO;
import br.com.banco.model.entities.Conta;
import br.com.banco.model.entities.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.exception.ResourceNotFoundException;

@Service
public class TransferenciaService {
  
  @Autowired
  private TransferenciaRepository transferenciaRepository;
  
  @Autowired
  private ContaRepository contaRepository;

  public List<TransferenciaDTO> findFiltrosTransferencias(
    Long id,
    String nomeOperadorTransicao,
    LocalDateTime dataInicio, 
    LocalDateTime dataFinal){

    Conta conta = findConta(id);
    List<Transferencia> transferencias = transferenciaRepository.findByConta(conta);

    return transferencias.stream()
      //Filtrar por nome do operador
      .filter(transferencia -> (nomeOperadorTransicao == null
        || transferencia.getNomeOperadorTransacao() != null
        && transferencia.getNomeOperadorTransacao()
        .equals(nomeOperadorTransicao)))

      //Filtrar por data de inicio
      .filter(transferencia -> (dataInicio == null
        || transferencia.getDataTransferencia()
        .isAfter(dataInicio.atZone(ZoneId.systemDefault()))))
       
      //Filtrar por data final  
      .filter(transferencia -> (dataFinal == null
        || transferencia.getDataTransferencia()
        .isBefore(dataFinal.atZone(ZoneId.systemDefault()))))


      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());
  }

  //Busca por conta
  public Conta findConta(Long id){
    Optional<Conta> objeto = contaRepository.findById(id);
    Conta conta = objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    return conta;
  }
}
