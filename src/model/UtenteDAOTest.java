package model;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UtenteDAOTest extends TestCase {
	@Test
	void test() {
		System.out.print(UtenteDAO.getUtenteByEmail("asd"));
	}
}
