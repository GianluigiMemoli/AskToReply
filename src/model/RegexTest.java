package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexTest {
	
	
	@Test
	public void validateRegistrationFieldsShouldReturnTrue() {
		assertTrue(Validator.validateRegistationFields("Gianluigi",
				"D'arco",
				"g.memoli98@gmail.com", "gmemo123@", "passw123ord"));
	}
	
	
}
