package ejercicio22;

import java.util.Date;
import java.util.concurrent.Callable;

public class Tarea implements Callable<String> {

    // Variables miembro.
    private String nombre;

    // Constructor.
    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    // Escribe la fecha/hora actual y retorna Hola Mundo.
    @Override
    public String call() throws Exception {
        System.out.printf("%s -> Iniciada en: %s\n", nombre, new Date());
        return "Hola Mundo";
    }
}
