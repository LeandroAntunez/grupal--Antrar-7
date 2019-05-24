package ar.edu.unq.eis.antrar.frontend.controller;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;
import ar.edu.unq.eis.antrar.backend.dao.ViajeDAO;
import ar.edu.unq.eis.antrar.backend.dao.impl.CrudDAOHibernate;
import ar.edu.unq.eis.antrar.backend.dao.impl.ViajeDAOHibernate;
import ar.edu.unq.eis.antrar.backend.model.Cliente;
import ar.edu.unq.eis.antrar.backend.model.excepciones.FechaNoIngresadaException;
import ar.edu.unq.eis.antrar.backend.model.excepciones.FechasIngresadasDesordenadasException;
import ar.edu.unq.eis.antrar.backend.model.Viaje;
import ar.edu.unq.eis.antrar.backend.model.excepciones.NombreDeClienteNoIngresadoException;
import ar.edu.unq.eis.antrar.backend.service.CrudService;
import ar.edu.unq.eis.antrar.backend.service.ViajeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FacturacionController implements Initializable{

    // COLABORADORES INTERNOS

    private ViajeDAO viajeDAO = new ViajeDAOHibernate(Viaje.class);
    private ViajeService viajeService = new ViajeService(viajeDAO);

    private CrudDAO clienteDAO = new CrudDAOHibernate<>(Cliente.class);
    private CrudService clienteService = new CrudService(clienteDAO);

    private ObservableList<Cliente> listaDeClientesRegistrados;
    private ObservableList<Viaje> listaDeViajesEncontrados;

    // TABLAS Y COLUMNAS

    @FXML
    private TableView<Viaje> tablaViajesEncontrados;

    @FXML
    private TableColumn<Viaje, Integer> columnaID;

    @FXML
    private TableColumn<Viaje, LocalDate> columnaFecha;

    // INTERFAZ

    @FXML
    private Button btnImprimir;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private ToggleGroup toggleGroupClienteRegistrado;

    @FXML
    private ComboBox<Cliente> comboBoxClienteRegistrado;

    @FXML
    private TextField textFieldClienteNoRegistrado;

    @FXML
    private DatePicker datePickerFechaDesde;

    @FXML
    private RadioButton radioButtonClienteNoRegistrado;

    @FXML
    private RadioButton radioButtonClienteRegistrado;

    @FXML
    private DatePicker datePickerFechaHasta;

    @FXML
    private Button btnBuscar;

    // INICIALIZACION

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Se inicializo el FacturacionController.");
        radioButtonClienteRegistrado.setSelected(true);
        textFieldClienteNoRegistrado.setVisible(false);
        btnImprimir.setVisible(false);

        // Inicializo la lista
        listaDeClientesRegistrados = FXCollections.observableArrayList();
        listaDeViajesEncontrados = FXCollections.observableArrayList();
        // Cargo la lista desde la base de datos
        listaDeClientesRegistrados.addAll(clienteService.retornarTodos());
        //enlazar  las  listas con  los elementos graficos
        comboBoxClienteRegistrado.setItems(listaDeClientesRegistrados);
        tablaViajesEncontrados.setItems(listaDeViajesEncontrados);

        //enlazar  colummnas  con  atributos
        columnaID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
    }

    // EVENTOS DE ACCIONAMIENTO

    @FXML
    void buscarViajes(ActionEvent event) {
        System.out.println("Se selecciono el boton de buscarViajes.");
        try {
            verificarInformacionIngresada();
            actualizarTablaDeViajes();
        } catch (FechaNoIngresadaException fechaNoIngresadaError) {
            System.out.println(fechaNoIngresadaError.getMessage());
            levantarAdvertenciaDeFechaNoIngresada();

        } catch (FechasIngresadasDesordenadasException fechaMalIngresadaError) {
            System.out.println(fechaMalIngresadaError.getMessage());
            levantarAdvertenciaDeFechaMalIngresada();
        } catch (NombreDeClienteNoIngresadoException e) {
            System.out.println(e.getMessage());
            levantarAdvertenciaDeClienteNoIngresado();
        }
    }

    @FXML
    void imprimirFactura(ActionEvent event) {
        System.out.println("Se selecciono el boton de imprimirFactura.");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("factura.txt", true))) {
            bw.write("Factura del Cliente: " + obtenerNombreDelCliente());
            bw.newLine();
            for (Viaje v : listaDeViajesEncontrados){
                bw.write("idViaje: " + v.getId() + " , Fecha: " + v.getFecha() +
                        " , Moto: " + v.getMoto() + " , Destino: " + v.getDestino());
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void habilitarTextFieldClienteRegistrado(ActionEvent event) {
        textFieldClienteNoRegistrado.setVisible(false);
        comboBoxClienteRegistrado.setVisible(true);
    }

    @FXML
    void habilitarTextFieldClienteNoRegistrado(ActionEvent event) {
        textFieldClienteNoRegistrado.setVisible(true);
        comboBoxClienteRegistrado.setVisible(false);
    }

    @FXML
    void limpiarBusqueda(ActionEvent event) {
        System.out.println("Se selecciono el boton de limpiarBusqueda.");
        textFieldClienteNoRegistrado.setText("");
        datePickerFechaDesde.setValue(null);
        datePickerFechaHasta.setValue(null);
        comboBoxClienteRegistrado.getSelectionModel().clearSelection();
        listaDeViajesEncontrados.clear();
        btnImprimir.setVisible(false);
    }

    // OTROS METODOS

    private void actualizarTablaDeViajes() {
        // Realiza una query para encontrar los viajes del cliente entre las fechas ingresadas,
        // y actualiza la tabla de viajes con el resultado.
        List<Viaje> viajesEncontrados = viajeService.buscarViajesDelClienteEntreFechas
                (obtenerNombreDelCliente(), datePickerFechaDesde.getValue(), datePickerFechaHasta.getValue());

        this.listaDeViajesEncontrados.addAll(viajesEncontrados);

        if (viajesEncontrados.isEmpty()) {
            levantarInformacionNoSeEncontraronViajes();
        }
        else {
            btnImprimir.setVisible(true);
        }
    }

    private String obtenerNombreDelCliente() {
        // Retorna el nombre del cliente, si este fue ingresado de forma valida.
        String nombreCliente;

        if (radioButtonClienteRegistrado.isSelected()) {
            try {
                verificarNombreIngresadoParaClienteRegistrado();
            } catch (NombreDeClienteNoIngresadoException e) {
                e.getMessage();
            }
            nombreCliente = comboBoxClienteRegistrado.getValue().getNombre();
        }
        else {
            try {
                verificarNombreIngresadoParaClienteNoRegistrado();
            } catch (NombreDeClienteNoIngresadoException e) {
                e.getMessage();
            }
            nombreCliente = textFieldClienteNoRegistrado.getText();
        }
        return nombreCliente;
    }

    // MENSAJES AL USUARIO

    private void levantarInformacionNoSeEncontraronViajes() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No se encontraron viajes.");
        alert.setHeaderText("No hay viajes para el cliente " + obtenerNombreDelCliente());
        alert.setContentText("Asegurese de ingresar correctamente el nombre del cliente," +
                " o que haya viajes encargados por este entre las fechas ingresadas.");

        alert.showAndWait();
    }

    private void levantarAdvertenciaDeFechaMalIngresada() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fechas mal Ingresadas");
        alert.setHeaderText("Las fechas ingresadas no son validas.");
        alert.setContentText("Asegurese de que esten en el orden correcto antes de buscar.");

        alert.showAndWait();
    }

    private void levantarAdvertenciaDeFechaNoIngresada() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fecha no Ingresada");
        alert.setHeaderText("Una o ambas fechas no fueron ingresadas.");
        alert.setContentText("Asegurese de que las dos sean seleccionadas antes de buscar.");

        alert.showAndWait();
    }

    private void levantarAdvertenciaDeClienteNoIngresado() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cliente no ingresado");
        alert.setHeaderText("El campo correspondiente al nombre del Cliente no fue llenado.");
        alert.setContentText("Asegurese de ingresar el cliente o razon social antes de buscar.");

        alert.showAndWait();
    }

    // VALIDACIONES

    private void verificarInformacionIngresada() throws FechasIngresadasDesordenadasException, FechaNoIngresadaException, NombreDeClienteNoIngresadoException {
        verificarFechasIngresadas();
        verificarNombreIngresado();
    }

    // VALIDACION DE NOMBRE DE CLIENTE

    private void verificarNombreIngresado() throws NombreDeClienteNoIngresadoException {
        if (radioButtonClienteRegistrado.isSelected()) { verificarNombreIngresadoParaClienteRegistrado(); }
        else { verificarNombreIngresadoParaClienteNoRegistrado(); }
    }

    private boolean elNombreDeClienteRegistradoNoFueIngresado() {
        return comboBoxClienteRegistrado.getValue() == null;
    }

    private boolean elNombreDeClienteNoRegistradoNoFueIngresado() {
        return textFieldClienteNoRegistrado.getText().trim().isEmpty();
    }

    private void verificarNombreIngresadoParaClienteRegistrado() throws NombreDeClienteNoIngresadoException {
        if (elNombreDeClienteRegistradoNoFueIngresado()) { throw new NombreDeClienteNoIngresadoException(); }
    }

    private void verificarNombreIngresadoParaClienteNoRegistrado() throws NombreDeClienteNoIngresadoException {
        if (elNombreDeClienteNoRegistradoNoFueIngresado()) { throw new NombreDeClienteNoIngresadoException(); }
    }

    // VALIDACION DE FECHAS

    private void verificarFechasIngresadas() throws FechaNoIngresadaException, FechasIngresadasDesordenadasException {
        // Levanta excepciones si las fechas ingresadas no son validas.
        if (!lasFechaDesdeFueIngresada())         { throw new FechaNoIngresadaException(); }
        if (!lasFechaHastaFueIngresada())         { throw new FechaNoIngresadaException(); }
        if (!lasFechasIngresadasEstanOrdenadas()) { throw new FechasIngresadasDesordenadasException(); }
    }

    private boolean lasFechaHastaFueIngresada() {
        // Retorna true si la fecha hasta fue seleccionada.
        return datePickerFechaHasta.getValue() != null;
    }

    private boolean lasFechaDesdeFueIngresada() {
        // Retorna true si la fecha desde fue seleccionada.
        return datePickerFechaDesde.getValue() != null;
    }

    private boolean lasFechasIngresadasEstanOrdenadas() {
        // Retorna true si las fechas estan en el orden correcto.
        return datePickerFechaDesde.getValue().isBefore(datePickerFechaHasta.getValue()) ||
                datePickerFechaDesde.getValue().isEqual(datePickerFechaHasta.getValue());
    }


}

