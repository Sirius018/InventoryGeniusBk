package com.inventorygenius.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @Column(name = "cod_order")
    private int codOrder;

    @Column(name = "Usuario")
    private String usuario;

    @Column(name = "password")
    private String password;
}
