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

}