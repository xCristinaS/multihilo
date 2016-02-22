package c.ejercicio40_listarep_boundservice;

public class Cancion {
    private String nombre;
    private String url;

    public Cancion(String nombre, String url){
        this.nombre = nombre;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

}
