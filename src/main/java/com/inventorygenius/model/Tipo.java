package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Tipo")
public class Tipo {
	@Id
	public int id_tip;
	public String tipo;

}
