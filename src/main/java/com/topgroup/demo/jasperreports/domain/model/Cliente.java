package com.topgroup.demo.jasperreports.domain.model;

import java.io.Serializable;

public class Cliente implements Serializable {

	private Integer numero;

	private String denominacion;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
