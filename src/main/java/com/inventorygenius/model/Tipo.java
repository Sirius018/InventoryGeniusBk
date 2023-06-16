package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Empresa")
public class Tipo {
	@Id
	private int id_tip;
	
	private String tipo;

}
