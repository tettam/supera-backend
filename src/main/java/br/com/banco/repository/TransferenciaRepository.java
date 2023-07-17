package br.com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.model.entities.Transferencia;
import java.util.List;
import br.com.banco.model.entities.Conta;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
  List<Transferencia> findByConta(Conta conta);
}
