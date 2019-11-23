package com.kota.cc;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import com.kota.cc.repository.MoveRepository;
import com.kota.cc.services.GameService;
import com.kota.cc.services.MoveService;

public class TestConfig {

	@Bean
	public MoveRepository dataSource() {
	    return Mockito.mock(MoveRepository.class);
	}
	
	@Bean
	public GameService gameService() {
	    return Mockito.mock(GameService.class);
	}
	
	@Bean
	public MoveService moveService() {
	    return Mockito.mock(MoveService.class);
	}
}
