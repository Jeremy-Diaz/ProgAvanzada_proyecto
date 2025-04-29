package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.tablas.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader<T extends Table> extends ReaderTemplate<T> {
    Scanner sc;


    public void openSource(String source) throws FileNotFoundException {
        String nombre = getClass().getClassLoader().getResource(source).getFile();
        sc = new Scanner(new File(nombre));
    }

    @Override
    public void processHeaders(String headers) {
        String[] header = headers.split(",");
        getTable().addHeaders(header);
    }

    @Override
    public void processData(String data) {
    }

    @Override
    public void closeSource() {
        sc.close();
    }
    @Override
    public boolean hasMoreData() {
        return sc.hasNextLine();
    }
    @Override
    public String getNextData() {
        return sc.nextLine();
    }
}
