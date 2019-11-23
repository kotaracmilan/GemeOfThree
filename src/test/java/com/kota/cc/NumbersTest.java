package com.kota.cc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kota.cc.domain.Player;
import com.kota.cc.exceptions.BadMoveException;
import com.kota.cc.services.GameService;
import com.kota.cc.services.MoveService;

import junit.framework.Assert;



@SpringBootTest(classes=TestConfig.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ContextConfiguration(classes = {TestConfig.class})
public class NumbersTest {

	
	@InjectMocks
	private GameService gameService;
	
	@Mock
	private MoveService moveService;

	
	
	
	@Test
	public void testCase1() {
		int i = gameService.solution(16);
		Assert.assertEquals(i, 5);
	}
	
	@Test
	public void testCase2() {
		int i = gameService.solution(2);
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void testCase3() {
		int i = gameService.solution(1);
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void testCase4() {
		Assertions.assertThrows(BadMoveException.class, () -> { gameService.makeMove(15);
		});
	}
	
	@Test
	public void testCase5() {
		gameService.defineGame(Player.MACHINE);
			Assertions.assertThrows(BadMoveException.class, () -> { gameService.makeMove(15);
		});
	}
}
