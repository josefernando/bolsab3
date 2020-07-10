package br.com.recatalog.bolsab3.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoadDadosHistoricosB3 {

	public static void main(String[] args) throws ParseException, IOException {
		
		/*
		 * Antes de  rodar com o arquivo ..TXT executar "replace all" no NotePad++
		 * com a seguinte regex [^-.$\n\r\t1234567890-QWERTYUIOPASDFGHJKLÇZXCVBNM/*' ]
		 * com replace de um caracter de espaço, onde a moeda for "CZ$"		 
		 */
		
		File file = new File("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\COTAHIST_D08072020.TXT");
		
		Path path = Paths.get("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\COTAHIST_D08072020.LOAD");
		BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		
		long count = 0;
		
		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
				String line = null;
				try {
				line = sc.nextLine();
				count++;
				CotaHistB3 p = createPapelPregao(line);
				if(p != null) {
					writer.write(p.toCvs()+ System.getProperty("line.separator"));
				}
				} catch(Exception e) {
					System.err.println(count);
					e.printStackTrace();
				}
			}
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static CotaHistB3 createPapelPregao(String cotacaoHistorica) throws ParseException {
		if(!cotacaoHistorica.startsWith("01")) return null;
		CotaHistB3 cotacao = new CotaHistB3(cotacaoHistorica);
		if(cotacao.codigo_negociacao_papel.endsWith("T")) return null;
//		if(Integer.parseInt(cotacao.getData_cotacao()) < 20200619) return null;
//		System.out.println(cotacao);
		return cotacao;
	}
	
	public static void printValor() {
		BigDecimal bd = new BigDecimal(BigInteger.valueOf(1234567890),4);
		System.out.println(bd);
	}
}

	class CotaHistB3 {
//		String tipo_reg;
//		Date data_pregao;
		String data_cotacao;
		String codigo_bdi;
		String codigo_negociacao_papel;
		String tipo_mercado;
		String nome_resumido_empresa_emissora; 
		String especificacao_papel; 
		String prazo_dias_mercado_termo; 
		String moeda_referencia;
		BigDecimal preco_abertura;
		BigDecimal preco_maximo;
		BigDecimal preco_minimo;
		BigDecimal preco_medio; 
		BigDecimal preco_fechamento;
		BigDecimal preco_melhor_oferta_compra; 
		BigDecimal preco_melhor_oferta_venda; 
		String total_negocios;
		String total_titulos_negociados;
		BigDecimal volume_titulos_negociados;
		BigDecimal preco_exercicio_mercado_opcoes; 
		String indicador_correcao_preco; 
//		Date data_vencimento_mercado_opcoes;
		String data_vencimento_mercado_opcoes;
		String fator_cotacao_papel;
		BigDecimal preco_exercio_pontos;
		String codigo_interno_papel;
		String numero_distrib_papel;
		
		public CotaHistB3() {}
		
		public CotaHistB3(String pregaoPapel) throws ParseException {
//			this.setTipo_reg(pregaoPapel.substring(0, 2).trim());
//			this.setData_pregao(new SimpleDateFormat("yyyyMMdd").parse(pregaoPapel.substring(2,10))) ;
			this.setData_cotacao(pregaoPapel.substring(2, 10).trim()) ;
			this.setCodigo_bdi(pregaoPapel.substring(10, 12).trim());
			this.setCodigo_negociacao_papel(pregaoPapel.substring(12, 24).trim());
			this.setTipo_mercado(pregaoPapel.substring(24, 27).trim());
			this.setNome_resumido_empresa_emissora(pregaoPapel.substring(27, 39).trim());
			this.setEspecificacao_papel(pregaoPapel.substring(39,49).trim());
			String prazo = pregaoPapel.substring(49, 52).trim().length() > 0 
					? pregaoPapel.substring(49, 52).trim()
					: "000";
			this.setPrazo_dias_mercado_termo(prazo) ;
			this.setMoeda_referencia(pregaoPapel.substring(52,56).trim());
			this.setPreco_abertura(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(56, 69))),2));
			this.setPreco_maximo(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(69, 82))),2));
			this.setPreco_minimo(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(82, 95))),2));
			this.setPreco_medio(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(95, 108))),2));
			this.setPreco_fechamento(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(108, 121))),2));
			this.setPreco_melhor_oferta_compra(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(121, 134))),2));
			this.setPreco_melhor_oferta_venda(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(134, 147))),2));
			this.setTotal_negocios(pregaoPapel.substring(147, 152).trim());
			this.setTotal_titulos_negociados(pregaoPapel.substring(152,170).trim());
			this.setVolume_titulos_negociados(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(170, 188))),2));
			this.setPreco_exercicio_mercado_opcoes(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(188, 201))),2));
			this.setIndicador_correcao_preco(pregaoPapel.substring(201, 202).trim());
//			this.setData_vencimento_mercado_opcoes(new SimpleDateFormat("yyyyMMdd").parse(pregaoPapel.substring(202,210))) ;
			this.setData_vencimento_mercado_opcoes(pregaoPapel.substring(202, 210).trim()) ;
			this.setFator_cotacao_papel(pregaoPapel.substring(210, 217).trim());
			this.setPreco_exercio_pontos(new BigDecimal(BigInteger.valueOf(Long.parseLong(pregaoPapel.substring(217, 230))),2));
			this.setCodigo_interno_papel(pregaoPapel.substring(230, 242).trim());
			this.setNumero_distrib_papel(pregaoPapel.substring(242,245).trim());
			
			// Alguns registros têm valor "," (vírgula)  claramente errado.
			// Então substituo por INVALID*
			if(this.especificacao_papel.contains(",")) { 
				this.especificacao_papel = "INVALID*";
			}
			
		}

//		public String getTipo_reg() {
//			return tipo_reg;
//		}

//		public void setTipo_reg(String tipo_reg) {
//			this.tipo_reg = tipo_reg;
//		}

		public String getData_cotacao() {
			return data_cotacao;
		}

		public void setData_cotacao(String data_cotacao) {
			this.data_cotacao = data_cotacao;
		}

		public String getCodigo_bdi() {
			return codigo_bdi;
		}

		public void setCodigo_bdi(String codigo_bdi) {
			this.codigo_bdi = codigo_bdi;
		}

		public String getCodigo_negociacao_papel() {
			return codigo_negociacao_papel;
		}

		public void setCodigo_negociacao_papel(String codigo_negociacao_papel) {
			this.codigo_negociacao_papel = codigo_negociacao_papel;
		}

		public String getTipo_mercado() {
			return tipo_mercado;
		}

		public void setTipo_mercado(String tipo_mercado) {
			this.tipo_mercado = tipo_mercado;
		}

		public String getNome_resumido_empresa_emissora() {
			return nome_resumido_empresa_emissora;
		}

		public void setNome_resumido_empresa_emissora(String nome_resumido_empresa_emissora) {
			this.nome_resumido_empresa_emissora = nome_resumido_empresa_emissora;
		}

		public String getEspecificacao_papel() {
			return especificacao_papel;
		}

		public void setEspecificacao_papel(String especificacao_papel) {
			this.especificacao_papel = especificacao_papel;
		}

		public String getPrazo_dias_mercado_termo() {
			return prazo_dias_mercado_termo;
		}

		public void setPrazo_dias_mercado_termo(String prazo_dias_mercado_termo) {
			this.prazo_dias_mercado_termo = prazo_dias_mercado_termo;
		}

		public String getMoeda_referencia() {
			return moeda_referencia;
		}

		public void setMoeda_referencia(String moeda_referencia) {
			this.moeda_referencia = moeda_referencia;
		}

		public BigDecimal getPreco_abertura() {
			return preco_abertura;
		}

		public void setPreco_abertura(BigDecimal preco_abertura) {
			this.preco_abertura = preco_abertura;
		}

		public BigDecimal getPreco_maximo() {
			return preco_maximo;
		}

		public void setPreco_maximo(BigDecimal preco_maximo) {
			this.preco_maximo = preco_maximo;
		}

		public BigDecimal getPreco_minimo() {
			return preco_minimo;
		}

		public void setPreco_minimo(BigDecimal preco_minimo) {
			this.preco_minimo = preco_minimo;
		}

		public BigDecimal getPreco_medio() {
			return preco_medio;
		}

		public void setPreco_medio(BigDecimal preco_medio) {
			this.preco_medio = preco_medio;
		}

		public BigDecimal getPreco_fechamento() {
			return preco_fechamento;
		}

		public void setPreco_fechamento(BigDecimal preco_fechamento) {
			this.preco_fechamento = preco_fechamento;
		}

		public BigDecimal getPreco_melhor_oferta_compra() {
			return preco_melhor_oferta_compra;
		}

		public void setPreco_melhor_oferta_compra(BigDecimal preco_melhor_oferta_compra) {
			this.preco_melhor_oferta_compra = preco_melhor_oferta_compra;
		}

		public BigDecimal getPreco_melhor_oferta_venda() {
			return preco_melhor_oferta_venda;
		}

		public void setPreco_melhor_oferta_venda(BigDecimal preco_melhor_oferta_venda) {
			this.preco_melhor_oferta_venda = preco_melhor_oferta_venda;
		}

		public String getTotal_negocios() {
			return total_negocios;
		}

		public void setTotal_negocios(String total_negocios) {
			this.total_negocios = total_negocios;
		}

		public String getTotal_titulos_negociados() {
			return total_titulos_negociados;
		}

		public void setTotal_titulos_negociados(String total_titulos_negociados) {
			this.total_titulos_negociados = total_titulos_negociados;
		}

		public BigDecimal getVolume_titulos_negociados() {
			return volume_titulos_negociados;
		}

		public void setVolume_titulos_negociados(BigDecimal volume_titulos_negociados) {
			this.volume_titulos_negociados = volume_titulos_negociados;
		}

		public BigDecimal getPreco_exercicio_mercado_opcoes() {
			return preco_exercicio_mercado_opcoes;
		}

		public void setPreco_exercicio_mercado_opcoes(BigDecimal preco_exercicio_mercado_opcoes) {
			this.preco_exercicio_mercado_opcoes = preco_exercicio_mercado_opcoes;
		}

		public String getIndicador_correcao_preco() {
			return indicador_correcao_preco;
		}

		public void setIndicador_correcao_preco(String indicador_correcao_preco) {
			this.indicador_correcao_preco = indicador_correcao_preco;
		}

		public String getData_vencimento_mercado_opcoes() {
			return data_vencimento_mercado_opcoes;
		}

		public void setData_vencimento_mercado_opcoes(String data_vencimento_mercado_opcoes) {
			this.data_vencimento_mercado_opcoes = data_vencimento_mercado_opcoes;
		}

		public String getFator_cotacao_papel() {
			return fator_cotacao_papel;
		}

		public void setFator_cotacao_papel(String fator_cotacao_papel) {
			this.fator_cotacao_papel = fator_cotacao_papel;
		}

		public BigDecimal getPreco_exercio_pontos() {
			return preco_exercio_pontos;
		}

		public void setPreco_exercio_pontos(BigDecimal preco_exercio_pontos) {
			this.preco_exercio_pontos = preco_exercio_pontos;
		}

		public String getCodigo_interno_papel() {
			return codigo_interno_papel;
		}

		public void setCodigo_interno_papel(String codigo_interno_papel) {
			this.codigo_interno_papel = codigo_interno_papel;
		}

		public String getNumero_distrib_papel() {
			return numero_distrib_papel;
		}

		public void setNumero_distrib_papel(String numero_distrib_papel) {
			this.numero_distrib_papel = numero_distrib_papel;
		}
		
		public String toCvs() {
			String cvs =
//					tipo_reg + ","
			data_cotacao + ","
			+ codigo_bdi + ","
			+ codigo_negociacao_papel + ","
			+ tipo_mercado + ","
			+ nome_resumido_empresa_emissora + ","
			+ especificacao_papel + ","
			+ prazo_dias_mercado_termo + ","
			+ moeda_referencia + ","
			+ preco_abertura + ","
			+ preco_maximo + ","
			+ preco_minimo + ","
			+ preco_medio + ","
			+ preco_fechamento + ","
			+ preco_melhor_oferta_compra + ","
			+ preco_melhor_oferta_venda + ","
			+ total_negocios + ","
			+ total_titulos_negociados + ","
			+ volume_titulos_negociados + ","
			+ preco_exercicio_mercado_opcoes + ","
			+ indicador_correcao_preco + ","
			+ data_vencimento_mercado_opcoes + ","
			+ fator_cotacao_papel + ","
			+ preco_exercio_pontos + ","
			+ codigo_interno_papel + ","
			+ numero_distrib_papel;
			
			return cvs;
		}

		@Override
		public String toString() {
			return "CotaHistB3 [data_pregao=" + data_cotacao + ", codigo_bdi=" + codigo_bdi
					+ ", codigo_negociacao_papel=" + codigo_negociacao_papel + ", tipo_mercado=" + tipo_mercado
					+ ", nome_resumido_empresa_emissora=" + nome_resumido_empresa_emissora + ", especificacao_papel="
					+ especificacao_papel + ", prazo_dias_mercado_termo=" + prazo_dias_mercado_termo
					+ ", moeda_referencia=" + moeda_referencia + ", preco_abertura=" + preco_abertura
					+ ", preco_maximo=" + preco_maximo + ", preco_minimo=" + preco_minimo + ", preco_medio="
					+ preco_medio + ", preco_fechamento=" + preco_fechamento + ", preco_melhor_oferta_compra="
					+ preco_melhor_oferta_compra + ", preco_melhor_oferta_venda=" + preco_melhor_oferta_venda
					+ ", total_negocios=" + total_negocios + ", total_titulos_negociados=" + total_titulos_negociados
					+ ", volume_titulos_negociados=" + volume_titulos_negociados + ", preco_exercicio_mercado_opcoes="
					+ preco_exercicio_mercado_opcoes + ", indicador_correcao_preco=" + indicador_correcao_preco
					+ ", data_vencimento_mercado_opcoes=" + data_vencimento_mercado_opcoes + ", fator_cotacao_papel="
					+ fator_cotacao_papel + ", preco_exercio_pontos=" + preco_exercio_pontos + ", codigo_interno_papel="
					+ codigo_interno_papel + ", numero_distrib_papel=" + numero_distrib_papel + "]";
		}
	}

	class PapelPregaoB3 {
		Date dtPregao;
		String codPapel;
		String nomeEmpresa;
		BigDecimal precoAbertura;
		BigDecimal precoMaximo;
		BigDecimal precoMinimo;
		BigDecimal precoMedio;
		BigDecimal precoFechamento;
		BigDecimal precoMelhorOfertaCompra;
		BigDecimal precoMelhorOfertaVenda;
		Long       numeroNegociosEfetuados;
		Long       quantidadeTotalTitulosNegociados;
		BigDecimal valorTotalTitulosNegociados;
		
		public PapelPregaoB3() {}
		
		public PapelPregaoB3(String dadosPregao) throws ParseException {
			this.setDtPregao(new SimpleDateFormat("yyyyMMdd").parse(dadosPregao.substring(2,10)));
			this.setCodPapel(dadosPregao.substring(12, 24).trim());
			this.setNomeEmpresa(dadosPregao.substring(27, 39));
			this.setPrecoAbertura(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(56, 69))),2));
			this.setPrecoMaximo(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(69, 82))),2));
			this.setPrecoMinimo(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(82, 95))),2));
			this.setPrecoMedio(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(95, 108))),2));
			this.setPrecoFechamento(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(108, 121))),2));
			this.setPrecoMelhorOfertaCompra(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(121, 134))),2));
			this.setPrecoMelhorOfertaVenda(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(134, 147))),2));
			this.setNumeroNegociosEfetuados(Long.parseLong(dadosPregao.substring(147, 152)));
			this.setQuantidadeTotalTitulosNegociados(Long.parseLong(dadosPregao.substring(152, 170)));
			this.setValorTotalTitulosNegociados(new BigDecimal(BigInteger.valueOf(Long.parseLong(dadosPregao.substring(170, 188))),2));
		}

		public Date getDtPregao() {
			return dtPregao;
		}

		public void setDtPregao(Date dtPregao) {
			this.dtPregao = dtPregao;
		}

		public String getCodPapel() {
			return codPapel;
		}

		public void setCodPapel(String codPapel) {
			this.codPapel = codPapel.trim();
		}

		public String getNomeEmpresa() {
			return nomeEmpresa;
		}

		public void setNomeEmpresa(String nomeEmpresa) {
			this.nomeEmpresa = nomeEmpresa.trim();
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

		public Long getNumeroNegociosEfetuados() {
			return numeroNegociosEfetuados;
		}

		public void setNumeroNegociosEfetuados(Long numeroNegociosEfetuados) {
			this.numeroNegociosEfetuados = numeroNegociosEfetuados;
		}

		public Long getQuantidadeTotalTitulosNegociados() {
			return quantidadeTotalTitulosNegociados;
		}

		public void setQuantidadeTotalTitulosNegociados(Long quantidadeTotalTitulosNegociados) {
			this.quantidadeTotalTitulosNegociados = quantidadeTotalTitulosNegociados;
		}
		
		public BigDecimal getValorTotalTitulosNegociados() {
			return valorTotalTitulosNegociados;
		}
		public void setValorTotalTitulosNegociados(BigDecimal valotTotalTitulosNegociados) {
			this.valorTotalTitulosNegociados = valotTotalTitulosNegociados;
		}

		@Override
		public String toString() {
			return "PapelPregaoB3 [dtPregao=" + dtPregao + ", codPapel=" + codPapel + ", nomeEmpresa=" + nomeEmpresa
					+ ", precoAbertura=" + precoAbertura + ", precoMaximo=" + precoMaximo + ", precoMinimo="
					+ precoMinimo + ", precoMedio=" + precoMedio + ", precoFechamento=" + precoFechamento
					+ ", precoMelhorOfertaCompra=" + precoMelhorOfertaCompra + ", precoMelhorOfertaVenda="
					+ precoMelhorOfertaVenda + ", numeroNegociosEfetuados=" + numeroNegociosEfetuados
					+ ", quantidadeTotalTitulosNegociados=" + quantidadeTotalTitulosNegociados
					+ ", valorTotalTitulosNegociados=" + valorTotalTitulosNegociados + "]";
		}
	}