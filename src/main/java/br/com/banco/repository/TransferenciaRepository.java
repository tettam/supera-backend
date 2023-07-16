package br.com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.model.entities.Transferencia;
import java.util.List;
import br.com.banco.model.entities.Conta;
import java.time.ZonedDateTime;



public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
  List<Transferencia> findByConta(Conta conta);
  
  @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia > :dataReferencia ORDER BY t.dataTransferencia")
  List<Transferencia> findByAposDataTransferencia(ZonedDateTime dataReferencia);
}
