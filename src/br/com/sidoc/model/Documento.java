package br.com.sidoc.model;

import java.util.Calendar;

public class Documento {
	private long id;
	private String titulo;
	private String refDocFisico;
	private String descricao;
	private String ativo;
	private Calendar dtCadastro;
	private Calendar dtValidade;
	private Departamento departamento;
	private Usuario usuario;
	private Categoria categoria;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRefDocFisico() {
		return refDocFisico;
	}
	public void setRefDocFisico(String refDocFisico) {
		this.refDocFisico = refDocFisico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public Calendar getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Calendar getDtValidade() {
		return dtValidade;
	}
	public void setDtValidade(Calendar dtValidade) {
		this.dtValidade = dtValidade;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
