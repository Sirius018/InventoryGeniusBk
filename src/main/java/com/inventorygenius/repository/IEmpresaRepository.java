package com.inventorygenius.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inventorygenius.model.Empresa;

public interface IEmpresaRepository extends CrudRepository<com.inventorygenius.model.Empresa, Integer>{
	
	// Verificar que no se ingrese numeros unicos duplicados
	@Query("SELECT e FROM Empresa e WHERE e.cod_unico_emp = :codUnicoEmp")
    Empresa findByCodUnicoEmp(int codUnicoEmp);
	
	
	// Verificar que no se ingrese RUC duplicados
	@Query("SELECT e FROM Empresa e WHERE e.ruc_emp = :rucEmp")
    Empresa findByRucEmp(String rucEmp);
}
