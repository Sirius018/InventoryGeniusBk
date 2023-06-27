package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Productos")
public class Producto {
	@Id
	public String cod_unico_prod;
	public String nombre_prod;
	public String descripcion_prod;
	public int cod_prov;
	public String fecha_ingreso;
	public String fecha_salida;
	public int stk_prod;
	public String cod_categoria;

	@ManyToOne
	@JoinColumn(name="cod_categoria", insertable = false, updatable = false)
	public Categoria objCategoria;

	@ManyToOne
	@JoinColumn(name="cod_prov", insertable = false, updatable = false)
	public Proveedor objProveedor;


}
