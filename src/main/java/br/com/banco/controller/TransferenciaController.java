package br.com.banco.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.dto.TransferenciaDTO;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping(value = "/api/transferencias")
public class TransferenciaController {

  @Autowired
  private TransferenciaService transferenciaService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<List<TransferenciaDTO>> getTransferencias(
    @PathVariable Long id,
    @RequestParam(required = false) String nomeOperadorTransicao,
    @RequestParam(required = false) LocalDateTime dataInicio,
    @RequestParam(required = false) LocalDateTime dataFinal){

    List<TransferenciaDTO> transferenciasDTO = transferenciaService.findAll(id);
    return ResponseEntity.ok().body(transferenciasDTO);
  }
}