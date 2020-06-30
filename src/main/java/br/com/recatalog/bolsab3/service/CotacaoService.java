package br.com.recatalog.bolsab3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recatalog.bolsab3.model.domain.Cotacao;
import br.com.recatalog.bolsab3.model.domain.repository.CotacaoRepository;

@Service
public class CotacaoService {
	
	@Autowired
	CotacaoRepository cotacaoRepository;
	
	public List<Cotacao> findByPapelAndDate(String papel, String startDate) {
		return cotacaoRepository.findByPapelAndDate("BBDC4", "2020-06-08");
	}
	
	public List<Cotacao> findByPapelAndBetweenDate(String papel, String startDate, String endDate) {
		return cotacaoRepository.findByPapelAndBetweenDate(papel, startDate, endDate);
	}
}