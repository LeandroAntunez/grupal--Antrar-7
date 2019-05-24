package ar.edu.unq.eis.antrar.frontend.controller;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;
import ar.edu.unq.eis.antrar.backend.dao.ViajeDAO;
import ar.edu.unq.eis.antrar.backend.dao.impl.CrudDAOHibernate;
import ar.edu.unq.eis.antrar.backend.dao.impl.ViajeDAOHibernate;
import ar.edu.unq.eis.antrar.backend.model.Moto;
import ar.edu.unq.eis.antrar.backend.model.Viaje;
import ar.edu.unq.eis.antrar.backend.service.CrudService;
import ar.edu.unq.eis.antrar.backend.service.ViajeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FormularioViajesController implements Initializable {

    // COLABORADORES INTERNOS

    private Stage stagePrincipal;

    //Servicios
    private ViajeDAO viajeDAO = new ViajeDAOHibernate(Viaje.class);
    private ViajeService viajeService = new ViajeService(viajeDAO);
    
    private CrudDAO motoDAO = new CrudDAOHibernate(Moto.class);
    private CrudService motoService = new CrudService(motoDAO);


    //colecciones
    private ObservableList<Moto> listaDeMotos;
    private ObservableList<Viaje> listaDeViajes;

    //listado de  viajes
    @FXML
    private TableView<Viaje> tablaViajes;

    // COLUMNAS
    @FXML
    private TableColumn<Viaje, String> columnaCliente;
    @FXML
    private TableColumn<Viaje, String> columnaMoto;
    @FXML
    private TableColumn<Viaje, String> columnaDestino;
    @FXML
    private TableColumn<Viaje, LocalDate> columnaFecha;

    // PESTAÑA NUEVO VIAJE

    @FXML
    private Tab tabNuevoViaje;
    @FXML
    private TextField txtCodigoViajeNuevo;
    @FXML
    private TextField txtClienteNuevo;
    @FXML
    private DatePicker dtePikerFechaNueva;
    @FXML
    private TextField txtDestinoNuevo;
    @FXML
    private ComboBox<Moto> cbomotoNueva;
    @FXML
    private Button btnGuardarNuevoViaje;
    @FXML
    private Button btnLimpiarNuevoViaje;

    // PESTAÑA MODIFICAR VIAJE

    @FXML
    private Tab tabModificarViaje;
    @FXML
    private TextField txtCodigoViajeModificado;
    @FXML
    private TextField txtClienteModificado;
    @FXML
    private DatePicker dtePikerFechaModificada;
    @FXML
    private TextField txtDestinoModificado;
    @FXML
    private ComboBox<Moto> cbomotoModificado;
    @FXML
    private Button btnModificarViaje;
    @FXML
    private Button btnLimpiarModificar;

    // PESTAÑA ELIMINAR VIAJE

    @FXML
    private Tab tabEliminarViaje;
    @FXML
    private Button btnEliminarViaje;

    // OTROS BOTONES


    // METODOS

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Se inicializo el FormularioViajesController.");
        
        //inicializo las  listas
        this.listaDeMotos = FXCollections.observableArrayList();
        this.listaDeViajes= FXCollections.observableArrayList();
     
        //cargo las listas desde la base de datos
        this.listaDeMotos.addAll(motoService.retornarTodos());
        this.listaDeViajes.addAll(viajeService.retornarTodos());
        
        //enlazar  las  listas con  los elementos graficos
        this.tablaViajes.setItems(listaDeViajes);
        this.cbomotoNueva.setItems(listaDeMotos);
        this.cbomotoModificado.setItems(listaDeMotos); // Setea la lista de motos en el comboBox de
                                                       // la pestaña de modificar moto.

        //enlazar  colummnas  con  atributos
        this.columnaCliente.setCellValueFactory(new PropertyValueFactory<Viaje, String>("cliente"));
        this.columnaDestino.setCellValueFactory(new PropertyValueFactory<Viaje,String>("destino"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<Viaje,LocalDate>("fecha"));
        this.columnaMoto.setCellValueFactory(new PropertyValueFactory<Viaje,String>("moto"));
    }
    
    @FXML
    void limpiarNuevoViaje(ActionEvent event) {
        System.out.println("Se selecciono el boton de limpiarNuevoViaje.");
        this.txtCodigoViajeNuevo.setText("");
        this.txtClienteNuevo.setText("");
        this.dtePikerFechaNueva.setValue(null);
        this.txtDestinoNuevo.setText("");
        this.cbomotoNueva.getSelectionModel().clearSelection();
    }

    @FXML
    void guardarViaje(ActionEvent event) {
        System.out.println("Se selecciono el boton de guardarViaje.");
        Viaje nuevoViaje = new Viaje(this.txtClienteNuevo.getText(),
                this.cbomotoNueva.getSelectionModel().getSelectedItem().toString(),
                this.txtDestinoNuevo.getText(),
                false, this.dtePikerFechaNueva.getValue());
        viajeService.guardar(nuevoViaje);
        this.listaDeViajes.add(nuevoViaje);



        System.out.println(nuevoViaje);
        System.out.println("Viaje guardado con exito.");
    }

    @FXML
    void limpiarModificadosViajes(ActionEvent event) {
        System.out.println("Se selecciono el boton de limpiarModificadosViajes.");
        this.txtCodigoViajeModificado.setText("");
        this.txtClienteModificado.setText("");
        this.dtePikerFechaModificada.setValue(null);
        this.txtDestinoModificado.setText("");
        this.cbomotoModificado.getSelectionModel().clearSelection();
    }

    @FXML
    void modificarViaje(ActionEvent event) {
        System.out.println("Se selecciono el boton de modificarViaje.");
    }

    @FXML
    void eliminarViaje(ActionEvent event) {
        System.out.println("Se selecciono el boton de eliminarViaje.");
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
}
