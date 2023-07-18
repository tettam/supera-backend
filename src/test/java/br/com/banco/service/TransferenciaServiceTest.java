package br.com.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.model.dto.TransferenciaDTO;
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
    conta = new Conta();
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

  // @Test
  // public void testValorFiltradoTransferencias(){
  //   List<TransferenciaDTO> transferencias = new ArrayList<>();
  //   TransferenciaDTO transferenciaDTO1 = new TransferenciaDTO();
  //   TransferenciaDTO transferenciaDTO2 = new TransferenciaDTO();
  //   ZonedDateTime zonedDateTime = ZonedDateTime.parse("2019-01-01T12:00:00");
  //   Transferencia transferencia1 = new Transferencia();
  //   Transferencia transferencia2 = new Transferencia();

  //   transferenciaDTO1.setId(1L);
  //   transferenciaDTO1.setNomeOperadorTransicao("Joao");
  //   transferenciaDTO1.setValor(new BigDecimal("-500.00"));
  //   transferenciaDTO1.setTipo("SAQUE");
  //   transferenciaDTO1.setDataTransferencia(zonedDateTime);

  //   transferenciaDTO2.setId(2L);
  //   transferenciaDTO2.setNomeOperadorTransicao("José");
  //   transferenciaDTO2.setValor(new BigDecimal("1000.00"));
  //   transferenciaDTO2.setTipo("TRANSFERENCIA");
  //   transferenciaDTO2.setDataTransferencia(zonedDateTime);

  //   transferencias.add(transferencia1);
  //   transferencias.add(transferencia2);
  //   BigDecimal valorTotal = testValorFiltradoTransferencias(transferencias);
  //   assertEquals(new BigDecimal("500"), valorTotal);
  // }
   
}