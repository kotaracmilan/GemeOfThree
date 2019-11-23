package com.kota.cc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kota.cc.domain.Move;
import com.kota.cc.services.MoveService;

@RestController
@RequestMapping(value="/api/log")
public class MoveController {

	@Autowired
	private MoveService moveService;
	
	@GetMapping("/listMoves")
	@CrossOrigin
	@ResponseBody
	public List<Move> listaAllMoves() {
		return moveService.getAllMoves();
	}
}
