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

  public List<TransferenciaDTO> findAll(Long id){
    Conta conta = findConta(id);
    List<Transferencia> transferencia = transferenciaRepository.findByConta(conta);
    List<TransferenciaDTO> transferenciaDTO = transferencia.stream()
      .map(TransferenciaDTO::new)
      .collect(Collectors.toList());

    return transferenciaDTO;
  }

  protected Conta findConta(Long id){
    Optional<Conta> objeto = contaRepository.findById(id);
    Conta conta = objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    return conta;
  }
}
