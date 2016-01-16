package fr.exemple.bibliotheque.tests;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.exemple.bibliotheque.Media;

public class ExempleValidationTest {

	private ValidatorFactory factory;
	private Validator validator;

	@Before
	public void setUp() throws Exception {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void tearDown() throws Exception {
		validator = null;
		factory = null;
	}

	@Test
	public void test() {
		Media m1 = new Media(0, "titre", "auteu");
		Set<ConstraintViolation<Media>> liste = validator.validate(m1);
		for (ConstraintViolation<Media> c : liste) {
			System.out.println(" erreur " + c.getMessage());
		}
		assertNotNull(liste);
		assertEquals(0, liste.size());
	}

}
