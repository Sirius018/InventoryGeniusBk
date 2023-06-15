package com.inventorygenius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Empresa")
public class Empresa {
	@Id
    private int id_emp;

    private int cod_unico_emp;
    private String nombre_emp;
    private String ruc_emp;
    private String descripcion_emp;
    private String correo_emp;
    private String direccion_emp;
    private int id_pais;
    private String telefono_one_emp;
    private String telefono_two_emp;


    @ManyToOne
    @JoinColumn(name="id_pais", insertable = false, updatable = false)
    private Pais objPais;
}
