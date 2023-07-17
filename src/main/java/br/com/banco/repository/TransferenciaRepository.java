package br.com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.model.entities.Transferencia;
import java.util.List;
import br.com.banco.model.entities.Conta;
import java.time.ZonedDateTime;



public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
  List<Transferencia> findByConta(Conta conta);
  List<Transferencia> findByNomeOperadorTransicao(String nomeOperadorTransicao);
  
  //Filtro - data de inicial
  @Query("SELECT tempo FROM Transferencia tempo WHERE tempo.dataTransferencia >= :dataReferencia ORDER BY tempo.dataTransferencia")
  List<Transferencia> findByDataInicialTransferencias(ZonedDateTime dataReferencia);

   //Filtro - data de final
  @Query("SELECT tempo FROM Transferencia tempo WHERE tempo.dataTransferencia <= :dataReferencia ORDER BY tempo.dataTransferencia")
  List<Transferencia> findByDataFinalTransferencias(ZonedDateTime dataReferencia);
}
