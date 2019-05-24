/**
 * 
 */
package ar.edu.unq.eis.antrar.backend.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.eis.antrar.backend.model.Cliente;

/**
 * @author Peter
 *
 */
public class ClienteTest {
	
	
	private Cliente cliente;
	private String razonSocial = "juan carlos s.a.";
	private long cuit = 333333;
	private String domicilio;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.cliente= new Cliente(this.razonSocial, this.cuit, this.domicilio);
	}

	@Test
	public void testClienteCreado() {
		
		assertNotNull(this.cliente);
	}

	
	
}
