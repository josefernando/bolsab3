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
		return cotacaoRepository.findByPapelAndDate(papel, startDate);
	}
	
	public List<Cotacao> findByPapelAndBetweenDate(String papel, String startDate, String endDate) {
		return cotacaoRepository.findByPapelAndBetweenDate(papel, startDate, endDate);
	}
	
	public Data findNegotiatedPapersBetweenDates(String paper, String startDate, String endDate) {
		return new Data(cotacaoRepository.findNegotiatedPapersBetweenDates(paper, startDate, endDate));
	}
	
	public Data findNegotiatedStockSymbolBetweenDates(String startDate, String endDate) {
		return new Data(cotacaoRepository.findNegotiatedStockSymbolBetweenDates(startDate, endDate));
	}	
	

	
//	public List<String> findNegotiatedPapersByDate(String date) {
//		return cotacaoRepository.findNegotiatedPapersByDate(date);
//	}
}