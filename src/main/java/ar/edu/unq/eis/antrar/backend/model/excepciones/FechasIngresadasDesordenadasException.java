package ar.edu.unq.eis.antrar.backend.model.excepciones;

public class FechasIngresadasDesordenadasException extends Throwable {

    @Override
    public String getMessage() {
        return "<< FechasIngresadasDesordenadasException: Las fechas ingresadas no son validas." +
                " Asegurese de que esten en el orden correcto antes de buscar. >>";
    }
}
