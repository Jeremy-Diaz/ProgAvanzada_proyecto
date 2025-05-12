package ProgAvanz.clasificacion.Vista;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;

import java.util.List;
public interface InterrogaModelo {
    List<String> tituloCanciones();
    int getCancionesGenero() throws LikedItemNotFoundException;
}
