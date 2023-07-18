package br.com.banco.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.dto.ValoresTransferenciasDTO;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping(value = "/api/transferencias")
public class TransferenciaController {

  @Autowired
  private TransferenciaService transferenciaService;

  @GetMapping(value = "/{id}")
  @CrossOrigin
  public ResponseEntity<ValoresTransferenciasDTO> getTransferencias(
    @PathVariable Long id,
    @RequestParam(required = false) String nomeOperadorTransicao,
    @RequestParam(required = false) 
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
    @RequestParam(required = false) 
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

    ValoresTransferenciasDTO valoresTransferencias = transferenciaService.findFiltrosTransferencias(
      id,
      nomeOperadorTransicao,
      dataInicio,
      dataFinal
    );
    return ResponseEntity.ok().body(valoresTransferencias);
  }
}