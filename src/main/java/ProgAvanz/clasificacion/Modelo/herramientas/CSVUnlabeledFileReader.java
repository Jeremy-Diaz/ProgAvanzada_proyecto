package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.tablas.Row;
import ProgAvanz.clasificacion.Modelo.tablas.Table;

public class CSVUnlabeledFileReader extends FileReader<Table>{
    public CSVUnlabeledFileReader(String ref) {
        table = new Table();
        super.ref = ref;
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
