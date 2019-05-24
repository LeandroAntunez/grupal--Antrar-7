package ar.edu.unq.eis.antrar.frontend.controller;

import ar.edu.unq.eis.antrar.frontend.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {

	private Main menuPrincipal;

    @FXML
    private Button btnFactura;
	
	@FXML
	private Button btnViajes;

    @FXML
    private Button btnMoto;


	@FXML
	private void irAlFormularioDeViajes(ActionEvent event) throws IOException {
        System.out.println("Se selecciono el boton de irAlFormularioDeViajes.");
        Parent viajeLoader = FXMLLoader.load(getClass().getResource("/view/formularioViajesView.fxml"));
        Stage stagePrincipal = new Stage();
        stagePrincipal.setTitle("ANTRAR - Formulario de Viajes");
        Scene viajeScene = new Scene(viajeLoader);
        stagePrincipal.setScene(viajeScene);
        stagePrincipal.show();
    }

    @FXML
    void irAlMenuDeFacturacion(ActionEvent event) throws IOException {
        System.out.println("Se selecciono el boton de irAlMenuDeFacturacion.");
        Parent facturacionLoader = FXMLLoader.load(getClass().getResource("/view/facturacionView.fxml"));
        Stage stagePrincipal = new Stage();
        stagePrincipal.setTitle("ANTRAR - Facturacion");
        Scene facturacionScene = new Scene(facturacionLoader);
        stagePrincipal.setScene(facturacionScene);
        stagePrincipal.show();
    }

    @FXML
    void abrirFormularioParaMoto(ActionEvent event) {
        System.out.println("Se selecciono el boton de abrirFormularioParaMoto.");
        Parent motosParent = null;
        try {
            motosParent = FXMLLoader.load(getClass().getResource("/view/formularioMotos.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stagePrincipal = new Stage();
        stagePrincipal.setTitle("ANTRAR - Formulario de Motos");
        Scene motoScene = new Scene(motosParent);
        stagePrincipal.setScene(motoScene);
        stagePrincipal.show();
    }

	public void setMenuPrincipal(Main mPrincipal) {
		this.menuPrincipal = mPrincipal;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
}