package br.com.recatalog.bolsab3.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.bolsab3.model.domain.Cotacao;
import br.com.recatalog.bolsab3.service.CotacaoService;


@RestController
@RequestMapping("api/b3")
public class CotacaoResource {
	
	@Autowired
	 CotacaoService cotacaoService;

	@GetMapping("cotacao")
	public ResponseEntity<Object> quotes() {
		
		List<Cotacao> lista = cotacaoService.findByPapelAndDate("", "");

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}