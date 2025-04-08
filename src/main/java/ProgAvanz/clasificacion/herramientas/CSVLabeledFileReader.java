package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.tablas.RowWithLabel;
import ProgAvanz.clasificacion.tablas.Table;
import ProgAvanz.clasificacion.tablas.TableWithLabels;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    public CSVLabeledFileReader() {
        table = new TableWithLabels();
    }
    @Override
    public void processData(String data){
        RowWithLabel fila = new RowWithLabel();
        String[] datos = data.split(",");
        String etiqueta = datos[datos.length-1];
        for(int i = 0; i < datos.length -1 ; i++){
            double numero = Double.parseDouble(datos[i]);
            fila.addData(numero);
        }
        fila.setLabel(etiqueta);
        getTable().addRow(fila);
    }
}
