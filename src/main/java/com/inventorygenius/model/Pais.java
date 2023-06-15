package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Pais")
public class Pais {
	@Id
	private int id_pais;
	private String tipo;
}
