package com.topgroup.demo.jasperreports.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.topgroup.demo.jasperreports.business.service.ProductoService;
import com.topgroup.demo.jasperreports.domain.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Override
	public List<Producto> findAll() {
		List<Producto> productos = new ArrayList<>();

		Producto p1 = new Producto();
		p1.setId(1);
		p1.setNombre("Notebook");
		p1.setPrecio(new BigDecimal("9121.99"));
		p1.setStock(2233);
		productos.add(p1);

		Producto p2 = new Producto();
		p2.setId(2);
		p2.setNombre("Mouse Genius");
		p2.setPrecio(new BigDecimal("191.99"));
		p2.setStock(9993);
		productos.add(p2);

		Producto p3 = new Producto();
		p3.setId(1);
		p3.setNombre("Monitor LG 23 pulgadas");
		p3.setPrecio(new BigDecimal("3121.99"));
		p3.setStock(987);
		productos.add(p3);

		Producto p4 = new Producto();
		p4.setId(1);
		p4.setNombre("Teclado inalambrico");
		p4.setPrecio(new BigDecimal("499.91"));
		p4.setStock(7888);
		productos.add(p4);

		Producto p5 = new Producto();
		p5.setId(1);
		p5.setNombre("Impresora laser jet HP 22322");
		p5.setPrecio(new BigDecimal("2399.99"));
		p5.setStock(5986);
		productos.add(p5);

		return productos;
	}

}
