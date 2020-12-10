package com.comprascrud.ComprasCRUD.repository;

import org.springframework.data.repository.CrudRepository;

import com.comprascrud.ComprasCRUD.model.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findById(long id);
	Usuario findByLogin(String login);
	boolean existsByLogin(String login);
	boolean existsByLoginAndSenha(String login, String senha);
}