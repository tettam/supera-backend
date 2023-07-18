package br.com.banco.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.banco.service.exception.ResourceNotFoundException;

@ControllerAdvice
public class RequisicaoExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErroDetalhes> handleNaoEncontrado(ResourceNotFoundException ex){
    ErroDetalhes erroDetalhes = new ErroDetalhes(
      LocalDateTime.now(),
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage());
    
    return new ResponseEntity<ErroDetalhes>(erroDetalhes, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroDetalhes> handleGlobalErro(Exception ex){
    ErroDetalhes erroDetalhes = new ErroDetalhes(
      LocalDateTime.now(),
      HttpStatus.BAD_REQUEST.value(),
      "Dados inválidos. Tente novamente");
    
    return new ResponseEntity<ErroDetalhes>(erroDetalhes, HttpStatus.BAD_REQUEST);
  }
}
