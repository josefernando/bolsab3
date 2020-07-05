package br.com.recatalog.bolsab3.service;

import java.util.List;

public class Data {
	List<String> data;
	public Data(List<String> lista) {
		this.data = lista;
	}
	
	public List<String> getData(){
		return data;
	}
}