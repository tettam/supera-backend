package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.entities.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping(value = "/api/transferencias")
public class TransferenciaController {

  @Autowired
  private TransferenciaService transferenciaService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<List<Transferencia>> findAll(@PathVariable Long id){
    List<Transferencia> transferencias = transferenciaService.findAll(id);
    return ResponseEntity.ok().body(transferencias);
  }
}