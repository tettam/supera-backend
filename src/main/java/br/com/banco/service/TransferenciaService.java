package br.com.banco.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

  // Sem filtros
  public List<TransferenciaDTO> findAll(Long id){
    Conta conta = findConta(id);
    List<Transferencia> transferencia = transferenciaRepository.findByConta(conta);
    List<TransferenciaDTO> transferenciaDTOs = transferencia.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }

  //Filtrar por data inicial
  public List<TransferenciaDTO> findDataInicial(LocalDateTime dataEntrada){
    ZonedDateTime zonedDateTime = dataEntrada.atZone(ZoneId.systemDefault());
    List<Transferencia> transferencia = transferenciaRepository.findByDataInicialTransferencias(zonedDateTime);
    List<TransferenciaDTO> transferenciaDTOs = transferencia.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }

  //Filtrar por data final
  public List<TransferenciaDTO> findDataFinal(LocalDateTime dataInicial){
    ZonedDateTime zonedDateTime = dataInicial.atZone(ZoneId.systemDefault());
    List<Transferencia> transferencias = transferenciaRepository.findByDataFinalTransferencias(zonedDateTime);
    List<TransferenciaDTO> transferenciaDTOs = transferencias.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }
  //Filtrar por nome do operador
  public List<TransferenciaDTO> findNomeOperador(String nomeOperador){
    List<Transferencia> transferencias = transferenciaRepository.findByNomeOperadorTransicao(nomeOperador);
    List<TransferenciaDTO> transferenciaDTOs = transferencias.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }

  //Busca por conta
  public Conta findConta(Long id){
    Optional<Conta> objeto = contaRepository.findById(id);
    Conta conta = objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    return conta;
  }
}
