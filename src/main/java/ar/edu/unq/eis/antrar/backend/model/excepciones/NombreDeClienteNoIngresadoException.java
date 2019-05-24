package ar.edu.unq.eis.antrar.backend.model.excepciones;

public class NombreDeClienteNoIngresadoException extends Throwable {

    @Override
    public String getMessage() {
        return "<< NombreDeClienteNoIngresadoException: El nombre del cliente no fue ingresado." +
                " Asegurese de ingresar su nombre o razon social antes de buscar. >>";
    }
}
