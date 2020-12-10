package com.comprascrud.ComprasCRUD.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
public class Compra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long compraId;
	
	@NotEmpty
	private String descricao;
	
	@NotNull
	private Date data;
	
	@NotNull
	@JoinColumn(name="usuario_id")
	private long dono;
	
	@OneToMany(mappedBy="compra")
	private List<Item> itens;
	
	private double total = 0;

	public long getCompraId() {
		return compraId;
	}

	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getDono() {
		return dono;
	}

	public void setDono(long dono) {
		this.dono = dono;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
