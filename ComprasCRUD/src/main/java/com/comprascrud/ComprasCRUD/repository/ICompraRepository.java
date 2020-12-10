package com.comprascrud.ComprasCRUD.repository;

import org.springframework.data.repository.CrudRepository;

import com.comprascrud.ComprasCRUD.model.Compra;
import com.comprascrud.ComprasCRUD.model.Usuario;

public interface ICompraRepository extends CrudRepository<Compra, String> {
	Compra findByCompraId(long compraId);
	Iterable<Compra> findByDono(long dono);
}
