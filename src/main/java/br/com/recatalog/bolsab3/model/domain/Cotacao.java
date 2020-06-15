package br.com.recatalog.bolsab3.model.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cotacao")
public class Cotacao {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "data_cotacao")
	private Date dataCotacao;
	
	@Column(name = "codigo_bdi")
	private String codigoBdi;
	
	@Column(name = "codigo_negociacao_papel")
	private String codigoNegociacaoPapel;
	
	@Column(name = "tipo_mercado")
	private int tipoMmercado;
	
	@Column(name = "nome_resumido_empresa_emissora")
	private String nomeResumidoEmpresaEmissora;
	
	@Column(name = "especificacao_papel")
	private String especificacaoPapel;
	
	@Column(name = "prazo_dias_mercado_termo")
	private String prazoDiasMercadoTermo;
	
	@Column(name = "moeda_referencia")
	private String moedaReferencia;
	
	@Column(name = "preco_abertura")
	private BigDecimal precoAbertura;
	
	@Column(name = "preco_maximo")
	private BigDecimal precoMaximo;
	
	@Column(name = "preco_minimo")
	private BigDecimal precoMinimo;
	
	@Column(name = "preco_medio")
	private BigDecimal precoMedio;
	
	@Column(name = "preco_fechamento")
	private BigDecimal precoFechamento;
	
	@Column(name = "preco_melhor_oferta_compra")
	private BigDecimal precoMelhorOfertaCompra;
	
	@Column(name = "preco_melhor_oferta_venda")
	private BigDecimal precoMelhorOfertaVenda;
	
	@Column(name = "total_negocios")
	private int totalNegocios;
	
	@Column(name = "total_titulos_negociados")
	private int totalTitulosNegociados;
	
	@Column(name = "volume_titulos_negociados")
	private BigDecimal volumeTitulosNegociados;
	
	@Column(name = "preco_exercicio_mercado_opcoes")
	private BigDecimal precoExercicioMercadoOpcoes;
	
	@Column(name = "indicador_correcao_preco")
	private int indicadorCorrecaoPreco;
	
	@Column(name = "data_vencimento_mercado_opcoes")
	private  Date dataVencimentoMercadoOpcoes;
	
	@Column(name = "fator_cotacao_papel")
	private int fatorCotacaoPapel;
	
	@Column(name = "preco_exercio_pontos")
	private BigDecimal precoExercioPontos;
	
	@Column(name = "codigo_interno_papel")
	private String codigoInternoPapel;

	@Column(name = "numero_distrib_papel")
	private int numeroDistribPapel;
	
	public Cotacao() {}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getCodigoBdi() {
		return codigoBdi;
	}

	public void setCodigoBdi(String codigoBdi) {
		this.codigoBdi = codigoBdi;
	}

	public String getCodigoNegociacaoPapel() {
		return codigoNegociacaoPapel;
	}

	public void setCodigoNegociacaoPapel(String codigoNegociacaoPapel) {
		this.codigoNegociacaoPapel = codigoNegociacaoPapel;
	}

	public int getTipoMmercado() {
		return tipoMmercado;
	}

	public void setTipoMmercado(int tipoMmercado) {
		this.tipoMmercado = tipoMmercado;
	}

	public String getNomeResumidoEmpresaEmissora() {
		return nomeResumidoEmpresaEmissora;
	}

	public void setNomeResumidoEmpresaEmissora(String nomeResumidoEmpresaEmissora) {
		this.nomeResumidoEmpresaEmissora = nomeResumidoEmpresaEmissora;
	}

	public String getEspecificacaoPapel() {
		return especificacaoPapel;
	}

	public void setEspecificacaoPapel(String especificacaoPapel) {
		this.especificacaoPapel = especificacaoPapel;
	}

	public String getPrazoDiasMercadoTermo() {
		return prazoDiasMercadoTermo;
	}

	public void setPrazoDiasMercadoTermo(String prazoDiasMercadoTermo) {
		this.prazoDiasMercadoTermo = prazoDiasMercadoTermo;
	}

	public String getMoedaReferencia() {
		return moedaReferencia;
	}

	public void setMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
	}

	public BigDecimal getPrecoAbertura() {
		return precoAbertura;
	}

	public void setPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
	}

	public BigDecimal getPrecoMaximo() {
		return precoMaximo;
	}

	public void setPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
	}

	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
	}

	public void setPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}

	public BigDecimal getPrecoFechamento() {
		return precoFechamento;
	}

	public void setPrecoFechamento(BigDecimal precoFechamento) {
		this.precoFechamento = precoFechamento;
	}

	public BigDecimal getPrecoMelhorOfertaCompra() {
		return precoMelhorOfertaCompra;
	}

	public void setPrecoMelhorOfertaCompra(BigDecimal precoMelhorOfertaCompra) {
		this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
	}

	public BigDecimal getPrecoMelhorOfertaVenda() {
		return precoMelhorOfertaVenda;
	}

	public void setPrecoMelhorOfertaVenda(BigDecimal precoMelhorOfertaVenda) {
		this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
	}

	public int getTotalNegocios() {
		return totalNegocios;
	}

	public void setTotalNegocios(int totalNegocios) {
		this.totalNegocios = totalNegocios;
	}

	public int getTotalTitulosNegociados() {
		return totalTitulosNegociados;
	}

	public void setTotalTitulosNegociados(int totalTitulosNegociados) {
		this.totalTitulosNegociados = totalTitulosNegociados;
	}

	public BigDecimal getVolumeTitulosNegociados() {
		return volumeTitulosNegociados;
	}

	public void setVolumeTitulosNegociados(BigDecimal volumeTitulosNegociados) {
		this.volumeTitulosNegociados = volumeTitulosNegociados;
	}

	public BigDecimal getPrecoExercicioMercadoOpcoes() {
		return precoExercicioMercadoOpcoes;
	}

	public void setPrecoExercicioMercadoOpcoes(BigDecimal precoExercicioMercadoOpcoes) {
		this.precoExercicioMercadoOpcoes = precoExercicioMercadoOpcoes;
	}

	public int getIndicadorCorrecaoPreco() {
		return indicadorCorrecaoPreco;
	}

	public void setIndicadorCorrecaoPreco(int indicadorCorrecaoPreco) {
		this.indicadorCorrecaoPreco = indicadorCorrecaoPreco;
	}

	public Date getDataVencimentoMercadoOpcoes() {
		return dataVencimentoMercadoOpcoes;
	}

	public void setDataVencimentoMercadoOpcoes(Date dataVencimentoMercadoOpcoes) {
		this.dataVencimentoMercadoOpcoes = dataVencimentoMercadoOpcoes;
	}

	public int getFatorCotacaoPapel() {
		return fatorCotacaoPapel;
	}

	public void setFatorCotacaoPapel(int fatorCotacaoPapel) {
		this.fatorCotacaoPapel = fatorCotacaoPapel;
	}

	public BigDecimal getPrecoExercioPontos() {
		return precoExercioPontos;
	}

	public void setPrecoExercioPontos(BigDecimal precoExercioPontos) {
		this.precoExercioPontos = precoExercioPontos;
	}

	public String getCodigoInternoPapel() {
		return codigoInternoPapel;
	}

	public void setCodigoInternoPapel(String codigoInternoPapel) {
		this.codigoInternoPapel = codigoInternoPapel;
	}

	public int getNumeroDistribPapel() {
		return numeroDistribPapel;
	}

	public void setNumeroDistribPapel(int numeroDistribPapel) {
		this.numeroDistribPapel = numeroDistribPapel;
	}
}