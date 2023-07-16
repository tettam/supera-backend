package br.com.banco.service.exception;

public class ResourceNotFoundException  extends RuntimeException {

  public ResourceNotFoundException(Long id){
    super("Conta com o " + id + " não encontrado");
  } 
}
