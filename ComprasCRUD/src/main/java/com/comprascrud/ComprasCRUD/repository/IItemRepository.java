package com.comprascrud.ComprasCRUD.repository;

import org.springframework.data.repository.CrudRepository;

import com.comprascrud.ComprasCRUD.model.Compra;
import com.comprascrud.ComprasCRUD.model.Item;

public interface IItemRepository extends CrudRepository<Item, String> {
	Item findByItemId(long itemId);
	Iterable<Item> findByCompra(Compra compra);
}
