package pl.fastresponse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimpleCrmApplication.class)
@WebAppConfiguration
public class UserServiceTest {

	@Test
	public void testShowUser(){
		
		UserService us = new UserService();
		
		assertEquals("Should have same value", "XXX", us.showUser());
	}
}
