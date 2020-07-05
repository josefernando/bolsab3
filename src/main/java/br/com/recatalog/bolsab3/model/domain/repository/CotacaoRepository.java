package br.com.recatalog.bolsab3.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.recatalog.bolsab3.model.domain.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao,Integer> {
	
	@Query(value = "SELECT * FROM tb_cotacao WHERE codigo_negociacao_papel = ?1"
			      + " and data_cotacao = ?2", nativeQuery = true)
	 List<Cotacao> findByPapelAndDate(String papel, String startDate);
	
	
	@Query(value = "SELECT * FROM tb_cotacao WHERE codigo_negociacao_papel = ?1"
			      + " and data_cotacao between ?2 and ?3", nativeQuery = true)
	List<Cotacao> findByPapelAndBetweenDate(String papel, String startDate, String StartDate);

	
	@Query(value = "SELECT distinct codigo_negociacao_papel FROM tb_cotacao WHERE "
		      + " data_cotacao between ?1 and ?2", nativeQuery = true)
	List<Cotacao> findByPapelBetweenDate(String startDate, String StartDate);

	
	//=============  ref.: procedure
	// https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-4/src/main/java/com/baeldung/storedprocedure/repository/CarRepository.java	

	@Query(value = "CALL GET_NEGOTIATED_PAPERS_BETWEEN_DATES(?1, ?2, ?3) ", nativeQuery = true)
	List<String> findNegotiatedPapersBetweenDates(String paper, String startDate, String endDate);

	@Query(value = "CALL FIND_NEGOTIATED_STOCK_SYMBOL_BETWEEN_DATES(?1, ?2) ", nativeQuery = true)
	List<String> findNegotiatedStockSymbolBetweenDates(String startDate, String endDate);
	
	
//	@Query(value = "CALL FIND_NEGOTIATED_PAPERS_BY_DATE(?1) ", nativeQuery = true)
//	List<String> findNegotiatedPapersByDate(String date);
}