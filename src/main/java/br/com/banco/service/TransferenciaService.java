package br.com.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<Transferencia> findAll(Long id, Transferencia transferencia){
    Optional<Conta> objeto = contaRepository.findById(id);
    Conta conta = objeto.orElseThrow(() -> new ResourceNotFoundException(id)); 

    return transferenciaRepository.findByConta(conta);
  }
}
