package ar.edu.unq.eis.antrar.frontend.controller;
import java.net.URL;
import java.util.ResourceBundle;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;
import ar.edu.unq.eis.antrar.backend.dao.impl.CrudDAOHibernate;
import ar.edu.unq.eis.antrar.backend.model.Moto;
import ar.edu.unq.eis.antrar.backend.service.CrudService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FormularioMotoController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCodigoMoto;

    @FXML
    private DatePicker datPkrFec_nac;

    @FXML
    private Button btnActualizarMoto;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtApellido;

    @FXML
    private Button btnGuardarMoto;

    @FXML
    private Button btnNuevaMoto;

    @FXML
    private TextField txtDni;

    @FXML
    private Button btnEliminarMoto;

    @FXML
    void guardarMoto(ActionEvent event) {
    	CrudDAO motoDao= new CrudDAOHibernate(Moto.class);
    	CrudService<Moto> servicioMoto= new CrudService<>(motoDao);
    	
    	//validacion ok
    	
    	Moto nuevaMoto= new Moto(this.txtNombre.getText(),
    								this.txtApellido.getText(),	
    								Integer.parseInt(this.txtDni.getText())
    								);
    	
    	servicioMoto.guardar(nuevaMoto);
    	System.out.println(nuevaMoto.getCodigo());
    }

    @FXML
    void limpiarFormularioMoto(ActionEvent event) {

    }
    
    public void validarFormulario()
    {
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}


