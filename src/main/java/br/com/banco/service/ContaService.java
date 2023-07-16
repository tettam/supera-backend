package br.com.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.entities.Conta;
import br.com.banco.repository.ContaRepository;

@Service
public class ContaService {

  @Autowired
  private ContaRepository contaRepository;

  public Conta findById(Long id){
    Optional<Conta> objeto = contaRepository.findById(id);
    return objeto.orElseThrow(() -> new ResourceNotFoundException(id));
  } 
}
