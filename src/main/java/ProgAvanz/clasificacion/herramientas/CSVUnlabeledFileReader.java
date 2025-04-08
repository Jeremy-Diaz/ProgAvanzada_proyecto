package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.tablas.Row;
import ProgAvanz.clasificacion.tablas.Table;

public class CSVUnlabeledFileReader extends FileReader<Table>{
    public CSVUnlabeledFileReader() {
        table = new Table();
    }
    @Override
    public void processData(String data) {
        Row fila = new Row();
        String[] datos = data.split(",");
        for (String campo: datos){
            double numero = Double.parseDouble(campo);
            fila.addData(numero);
        }
        getTable().addRow(fila);
    }
}
