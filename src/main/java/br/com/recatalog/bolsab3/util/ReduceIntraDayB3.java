package br.com.recatalog.bolsab3.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Scanner;

public class ReduceIntraDayB3 {

//	public Deque<IntradayTran> trans = new ArrayDeque<IntradayTran>();
	
	public static void main(String[] args) throws ParseException, IOException {
		
		/*
		 * Antes de  rodar com o arquivo ..TXT executar "replace all" no NotePad++
		 * com a seguinte regex [^-.$\n\r\t1234567890-QWERTYUIOPASDFGHJKLÇZXCVBNM/*' ]
		 * com replace de um caracter de espaço, onde a moeda for "CZ$"		 
		 */
		
		File file = new File("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\TradeIntraday_20200729_1.txt");
		
//		Path path = Paths.get("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\COTAHIST_D08072020.LOAD");
//		BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		
		long count = 0;
		
		IntradayTran intradayTranAnterior = null;
		
		Deque<IntradayTran> trans = new ArrayDeque<IntradayTran>();
		
		BigDecimal valorAbertura = new BigDecimal(0);
		
		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			sc.useDelimiter("\\n");
			sc.next();  // pula primeira linha de cabeçalho
			while (sc.hasNext()){
				String line = null;
				try {
				line = sc.next();
				count++;
				
				IntradayTran intradayTran = createTrans(line, "OIBR4");
//				IntradayTran intradayTran = createTrans(line);

				
				if (intradayTran == null) continue;
				
				if(trans.isEmpty()) {
					trans.push(intradayTran);
					valorAbertura = intradayTran.getValor();
				}
				else {
					intradayTranAnterior = trans.peek();
					if(intradayTranAnterior.getValor().compareTo(intradayTran.getValor()) == 0) {
						int qtAcoesAnt = intradayTranAnterior.getQuantidade_acoes();
						int qtAcoes = intradayTran.getQuantidade_acoes();
						intradayTranAnterior.setQuantidade_acoes(qtAcoesAnt + qtAcoes);
					}
					else {
						trans.push(intradayTran);
					}
				}
				
//				System.out.println(intradayTran.toString());
				
				} catch(Exception e) {
					System.err.println(count);
					e.printStackTrace();
				}
			}
			
			for(IntradayTran tran : trans) {
				System.out.println(tran);
			}
			
			System.out.println(trans.size() + " result: " + resultado(trans,valorAbertura));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public IntradayTran createTrans(String line, String ...stock) throws ParseException {
		String[] fields = line.split(";");
		if(stock.length > 0) {
			if(!fields[1].equalsIgnoreCase(stock[0])) return null;
			else {
				return new IntradayTran(line);
			}
		}
		return new IntradayTran(line);
	}
	
	static public float resultado(Deque<IntradayTran> trans, BigDecimal valorAbertura) {
		BigDecimal ultimoValor = trans.peek().getValor();
		return (ultimoValor.floatValue() / valorAbertura.floatValue() - 1)*100;
	}
	
	public static void printValor() {
		BigDecimal bd = new BigDecimal(BigInteger.valueOf(1234567890),4);
		System.out.println(bd);
	}
}

	class IntradayTran {
		Integer id;
		Date data;
		LocalTime hora;
		String codigo_acao;
		BigDecimal valor;
		Integer quantidade_acoes;
		
		public IntradayTran(String intraTran) throws ParseException {
			String[] fields = intraTran.split(";");
			
			this.id = Integer.parseInt(fields[6]);
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			this.data = formato.parse(fields[8]);
			
			String timeTran = fields[5];
			int hh = Integer.parseInt(timeTran.substring(0, 2));
			int mm = Integer.parseInt(timeTran.substring(2, 4));
			int ss = Integer.parseInt(timeTran.substring(4, 6));
			int ms = Integer.parseInt(timeTran.substring(6));
			this.hora = LocalTime.of(hh, mm, ss, ms*1000000);
			
			this.codigo_acao = fields[1];
			
			this.valor = new BigDecimal(BigInteger.valueOf(Integer.parseInt(fields[3].replace(",", ""))),3);
			
			this.quantidade_acoes = Integer.parseInt(fields[4]);
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public LocalTime getHora() {
			return hora;
		}
		public void setHora(LocalTime hora) {
			this.hora = hora;
		}
		public String getCodigo_acao() {
			return codigo_acao;
		}
		public void setCodigo_acao(String codigo_acao) {
			this.codigo_acao = codigo_acao;
		}
		public BigDecimal getValor() {
			return valor;
		}
		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}
		public Integer getQuantidade_acoes() {
			return quantidade_acoes;
		}
		public void setQuantidade_acoes(Integer quantidade_acoes) {
			this.quantidade_acoes = quantidade_acoes;
		}

		@Override
		public String toString() {
			return "IntradayTran [id=" + id + ", data=" + data + ", hora=" + hora + ", codigo_acao=" + codigo_acao
					+ ", valor=" + valor + ", quantidade_acoes=" + quantidade_acoes + "]";
		}
	}