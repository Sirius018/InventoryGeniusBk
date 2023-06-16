package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Proveedores")
public class Proveedor {
	@Id
	private int cod_prov;
	
    private String cod_unico_prov;    
    private String nombre_prov;
    private String ruc_prov;
    private String descripcion_prov;
    private String correo_prov;
    private String direccion_prov;
    private int nacionalidad_prov;
    private int id_pais;
    private String telefono_one_prov;
    private String telefono_two_prov;
    
    @ManyToOne
    @JoinColumn(name="nacionalidad_prov", insertable = false, updatable = false)
    private Pais objProve;
    
    @ManyToOne
    @JoinColumn(name="id_pais", insertable = false, updatable = false)
    private Pais objPais;

}

