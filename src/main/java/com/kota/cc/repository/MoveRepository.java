package com.kota.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.cc.domain.Move;

public interface MoveRepository extends CrudRepository<Move, Long> {
	
	List<Move> findAll();

}
