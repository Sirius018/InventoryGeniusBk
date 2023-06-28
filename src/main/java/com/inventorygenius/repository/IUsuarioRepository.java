package com.inventorygenius.repository;

import com.inventorygenius.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByUsuarioAndPassword(String userName, String password);

    Usuario findByUsuario(String userName);
}
