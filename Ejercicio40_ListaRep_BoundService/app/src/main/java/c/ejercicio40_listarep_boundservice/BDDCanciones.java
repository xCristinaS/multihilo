package c.ejercicio40_listarep_boundservice;

import java.util.ArrayList;

/**
 * Created by Cristina on 15/02/2016.
 */
public class BDDCanciones {

    public static final ArrayList<Cancion> canciones = new ArrayList<>();

    static  {
        canciones.add(new Cancion("Morning Mood", "https://www.youtube.com/audiolibrary_download?vid=036500ffbf472dcc"));
        canciones.add(new Cancion("Brahms Lullaby", "https://www.youtube.com/audiolibrary_download?vid=9894a50b486c6136"));
        canciones.add(new Cancion("Triangles", "https://www.youtube.com/audiolibrary_download?vid=8c9219f54213cb4f"));
        canciones.add(new Cancion("From Russia With Love","https://www.youtube.com/audiolibrary_download?vid=4e8d1a0fdb3bbe12"));
        canciones.add(new Cancion("Les Toreadors from Carmen", "https://www.youtube.com/audiolibrary_download?vid=fafb35a907cd6e73"));
        canciones.add(new Cancion("Funeral March", "https://www.youtube.com/audiolibrary_download?vid=4a7d058f20d31cc4"));
    }
}

