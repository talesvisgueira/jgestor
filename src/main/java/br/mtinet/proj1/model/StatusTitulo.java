package br.mtinet.proj1.model;

public enum StatusTitulo {
	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusTitulo (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
