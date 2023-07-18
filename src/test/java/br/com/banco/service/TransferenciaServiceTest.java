package br.com.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.model.entities.Conta;
import br.com.banco.model.entities.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest {
  
  @InjectMocks
  TransferenciaService transferenciaService;

  @Mock
  TransferenciaRepository transferenciaRepository;

  @Mock
  ContaRepository contaRepository;

  Conta conta;

  Transferencia transferencia;

  @BeforeEach
  public void setUp(){
    Long id = 1L;
    Conta conta = new Conta();
    conta.setId(id);
  }

  @Test
  public void testBuscarTransferenciasPorConta() {
    when(contaRepository.findById(conta.getId())).thenReturn(Optional.of(conta));
    Conta contaEncontrada = transferenciaService.findConta(conta.getId());
    assertEquals(conta, contaEncontrada);
    verifyNoMoreInteractions(transferenciaRepository);
  }

  @Test
  public void testBuscarTransferenciasPorContaInexistente(){
    Long idInexistente = 999999999L;
    when(contaRepository.findById(idInexistente)).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> {
      transferenciaService.findConta(idInexistente);
    });
  }
   
}