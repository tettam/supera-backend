package br.com.banco.service;

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
  public List<TransferenciaDTO> findDataEntrada(ZonedDateTime dataEntrada){
    List<Transferencia> transferencia = transferenciaRepository.findByAposDataTransferencia(dataEntrada);
    List<TransferenciaDTO> transferenciaDTOs = transferencia.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }

  //Filtrar por data final

  //Filtrar entre as data

  //Filtrar por nome do operador
  public List<TransferenciaDTO> findNomeOperador(Long id, TransferenciaDTO transferenciaDTO){
    Conta conta = findConta(id);
    List<Transferencia> transferencias = transferenciaRepository.findByNomeOperadorTransicao(transferenciaDTO.getNomeOperadorTransicao());
    List<TransferenciaDTO> transferenciaDTOs = transferencias.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTOs;
  }

  //Todos os filtros

  //Busca por conta
  protected Conta findConta(Long id){
    Optional<Conta> objeto = contaRepository.findById(id);
    Conta conta = objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    return conta;
  }
}
