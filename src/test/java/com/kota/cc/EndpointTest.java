package com.kota.cc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kota.cc.controllers.GameController;
import com.kota.cc.domain.Player;
import com.kota.cc.services.GameService;
import com.kota.cc.services.MoveService;


@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EndpointTest {

	
	private MockMvc mockMvc;

	@InjectMocks
	private GameController gc;
	 
	@Mock
	private GameService gameService;
	@Mock
	private MoveService moveService;
	
	
	@Test
	public void testDefineGame() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(gc)
	            .build();
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/api/game/start/"+Player.HUMAN))
			.andReturn();
		int status = r.getResponse().getStatus();
		   Assertions.assertEquals(200, status);
	}
	
	@Test
	public void testPlay() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(gc)
	            .build();

		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/api/game/play/human/15"))
			.andReturn();
		int status = r.getResponse().getStatus();
		   Assertions.assertEquals(200, status);
	}
}
