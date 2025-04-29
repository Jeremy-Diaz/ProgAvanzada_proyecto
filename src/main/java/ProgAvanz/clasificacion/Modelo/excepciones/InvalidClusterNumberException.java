package ProgAvanz.clasificacion.Modelo.excepciones;

public class InvalidClusterNumberException extends Exception{

    private int numero_clusters;
    public InvalidClusterNumberException(int n_clusters){
        super("El número de grupos indicado K (clusters) es mayor que el número de de datos N de la tabla");
        numero_clusters = n_clusters;
    }
    public int getNumberOfCusters() {
        return numero_clusters;
    }
}
