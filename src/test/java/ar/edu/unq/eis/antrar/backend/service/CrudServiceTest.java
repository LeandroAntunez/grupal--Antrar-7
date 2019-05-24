package ar.edu.unq.eis.antrar.backend.service;

import ar.edu.unq.eis.antrar.backend.dao.impl.ViajeDAOHibernate;
import ar.edu.unq.eis.antrar.backend.model.Viaje;
import ar.edu.unq.eis.antrar.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CrudServiceTest {

    private String nombreCliente;
    private String nombreMoto;
    private String destino;
    private Viaje unViaje;
    private Boolean esViajeConLluvia;

    private ViajeDAOHibernate daoViaje;
    private ViajeService serviceViaje;


    @Before
    public void setUp(){
        daoViaje = new ViajeDAOHibernate(Viaje.class);
        serviceViaje = new ViajeService(daoViaje);

        nombreCliente = "Estudio Lafuente";
        nombreMoto = "Silvio Serna";
        destino = "Calle Falsa 123, Springfield";
        esViajeConLluvia = false;
        unViaje = new Viaje(nombreCliente, nombreMoto, destino, esViajeConLluvia);
    }

    @After
    public void tearDown(){
        SessionFactoryProvider.destroy();
    }

    // Testea los metodos Create y Return del Service CRUD.
    @Test
    public void testGuardarYRetornar() {
        Viaje viajePersistido = serviceViaje.guardar(unViaje);

        Viaje viajeDeLaBase = serviceViaje.retornarPorId(viajePersistido.getId());

        Assert.assertEquals(viajePersistido.getCliente(), viajeDeLaBase.getCliente());
        Assert.assertEquals(viajePersistido.getMoto(), viajeDeLaBase.getMoto());
        Assert.assertEquals(viajePersistido.getDestino(), viajeDeLaBase.getDestino());
    }


    // Testea el metodo Update del Service CRUD.
    @Test
    public void testActualizarInformacion(){
        serviceViaje.guardar(unViaje);

        unViaje.setCliente("Juan Lopez");

        serviceViaje.actualizar(unViaje);

        Viaje viajeActualizado = serviceViaje.retornarPorId(unViaje.getId());

        Assert.assertEquals("Juan Lopez", viajeActualizado.getCliente());
    }

    // Testea el metodo Delete del Service CRUD.
    @Test
    public void testBorrar(){
        serviceViaje.guardar(unViaje);
        List<Viaje> listaConUnViaje = serviceViaje.retornarTodos();
        Assert.assertEquals(1, listaConUnViaje.size());

        serviceViaje.borrar(unViaje);
        List<Viaje> listaSinViajes = serviceViaje.retornarTodos();
        Assert.assertEquals(0, listaSinViajes.size());
    }
}
