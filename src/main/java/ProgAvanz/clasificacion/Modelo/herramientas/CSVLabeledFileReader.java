package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.tablas.RowWithLabel;
import ProgAvanz.clasificacion.Modelo.tablas.TableWithLabels;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    public CSVLabeledFileReader(String ref) {
        table = new TableWithLabels();
        super.ref = ref;
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
