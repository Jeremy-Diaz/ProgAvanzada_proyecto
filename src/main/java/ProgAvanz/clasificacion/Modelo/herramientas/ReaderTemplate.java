package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.tablas.Table;

import java.io.FileNotFoundException;

public abstract class  ReaderTemplate<T extends Table> {
    protected T table;
    protected String ref;

    public abstract void  openSource(String source) throws FileNotFoundException;
    public abstract void processHeaders(String headers);
    public abstract void processData(String data);
    public abstract void closeSource();
    public abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay mas línea(s) en el fichero CSV
    public abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso
    public final T readTableFromSource() throws FileNotFoundException {
        openSource(ref);
        String headers = getNextData();
        processHeaders(headers);
        while(hasMoreData()){
            String linea = getNextData();
            processData(linea);
        }
        closeSource();
        return table;
    }
    public T getTable() {
        return table;
    }
}
