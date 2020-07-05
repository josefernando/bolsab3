package br.com.recatalog.bolsab3.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.bolsab3.model.domain.Cotacao;
import br.com.recatalog.bolsab3.service.CotacaoService;
import br.com.recatalog.bolsab3.service.Data;

@RestController
@RequestMapping("api/b3")
public class CotacaoResource {
	
	@Autowired
	 CotacaoService cotacaoService;
	
	// ex.:  quotes?fields=field1&fields=field2&start=20200407&end=20200408
	@GetMapping("quotes")
	public ResponseEntity<Object> quotes(@RequestParam(name = "fields") String[] fields
            , @RequestParam(name = "start") Optional<String> startDate
            , @RequestParam(name = "end") Optional<String> endDate ) {
		
		String startDate1 = startDate.orElseGet(() -> null);
		String endDate1 = endDate.orElseGet(() -> null);
		
//		Data data = cotacaoService.findNegotiatedStockSymbolBetweenDates(startDate1, endDate1);
		Data data = cotacaoService.findNegotiatedPapersBetweenDates(fields[0], startDate1, endDate1);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("quotes/?fields=name") public ResponseEntity<Object> quotesName()
	 * {
	 * 
	 * List<String> lista = cotacaoService.findNegotiatedPapersByDate("20200407");
	 * 
	 * return new ResponseEntity<>(lista, HttpStatus.OK); }
	 */

	@GetMapping("quotes/{papel}")
	public ResponseEntity<Object> quotesByPapel(@PathVariable String papel
			                             , @RequestParam(name = "start") Optional<String> startDate
			                             , @RequestParam(name = "end") Optional<String> endDate) {
		
		String papel1 = papel;
		
		String startDate1 = startDate.orElseGet(() -> null);
		String endDate1 = endDate.orElseGet(() -> null);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDateFormat.format(new Date());
		
		if(startDate1 == null || endDate1 == null) {
			startDate1 = date;
			endDate1 = date;
		}
		
	    Date firstDate = null;
	    Date secondDate = null;
		
		try {
	    firstDate = simpleDateFormat.parse(startDate1);
	    secondDate = simpleDateFormat.parse(endDate1);
		} catch(Exception e) {
			firstDate = new Date();
			secondDate = new Date();
		}
		
	    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		if(diff > 30) {  // Se o período > 30 dias então retorna apenas o dia da data inicial
			endDate1 = startDate1;
		}
	    
		List<Cotacao> lista = cotacaoService.findByPapelAndBetweenDate(papel1, startDate1, endDate1);

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}