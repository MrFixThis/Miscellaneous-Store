package com.store.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.store.model.Administrator;

/**
 * @author Bryan Baron
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdministratorControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldCreateAnAdministratorAndReturnsItsJsonRepresentation()
		throws Exception {
		Administrator administrator = Administrator.builder()
			.firstName("Dilan")
			.middleName("Andres")
			.paternalLastName("Baron")
			.maternalLastName("Murcia")
			.identificationNumber("1000714326")
			.identificationType("ID")
			.dateOfBirth(Date.valueOf("2002-05-13"))
			.dateOfHire(Date.valueOf("2022-08-16"))
			.phoneNumber("3004531235")
			.emailAddress("Dilan.baron79@gmail.com")
			.residenceAddress("Kra 14I 138 A 75 Sur")
			.role("Software Engineer")
			.basicSalary(5000000L)
			.build();

		assertThat(this.restTemplate.postForObject(
					String.format("http://localhost:%d/administrators/", port),
					administrator, Administrator.class));
	}

	// @Test
	// public void 
}
