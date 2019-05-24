package ar.edu.unq.eis.antrar.backend.model.excepciones;

public class FechaNoIngresadaException extends Throwable {

    @Override
    public String getMessage() {
        return "<< FechaNoIngresadaException: Una o ambas fechas no fueron ingresadas." +
                " Asegurese de que las dos sean seleccionadas antes de buscar. >>";
    }

}
