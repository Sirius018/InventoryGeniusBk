package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name="Categoria")
public class Categoria {
	@Id
	public String cod_categoria;
	public String categoria;
	public String descripcion_cat;
}
