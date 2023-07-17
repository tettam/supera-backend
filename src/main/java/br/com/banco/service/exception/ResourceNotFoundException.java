package br.com.banco.service.exception;

public class ResourceNotFoundException  extends RuntimeException {

  public ResourceNotFoundException(Long id){
    super("Conta com o ID " + id + " não foi encontrado");
  } 
}
