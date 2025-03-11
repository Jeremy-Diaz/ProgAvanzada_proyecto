package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.tablas.Row;
import ProgAvanz.clasificacion.tablas.RowWithLabel;
import ProgAvanz.clasificacion.tablas.Table;
import ProgAvanz.clasificacion.tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    public Table readTable(String nombre) throws FileNotFoundException{
        String ref = getClass().getClassLoader().getResource(nombre).getFile();
        Scanner sc = new Scanner(new File(ref));
        Table tabla = new Table();
        String linea = sc.nextLine();
        String[] headers = linea.split(",");
        tabla.addHeaders(headers);
        while(sc.hasNextLine()){
            Row fila = new Row();
            linea = sc.nextLine();
            String[] datos = linea.split(",");
            for (String campo: datos){
                double numero = Double.parseDouble(campo);
                fila.addData(numero);
            }
            tabla.addRow(fila);
        }
        return tabla;
    }

    public TableWithLabels readTableWithLabels(String nombre) throws FileNotFoundException {
        String ref = getClass().getClassLoader().getResource(nombre).getFile();
        Scanner sc = new Scanner(new File(ref));
        TableWithLabels tabla = new TableWithLabels();
        String linea = sc.nextLine();
        String[] headers = linea.split(",");
        for (int i = 0; i < headers.length-1; i++ )
            tabla.addHeader(headers[i]);

        while(sc.hasNextLine()){
            RowWithLabel fila = new RowWithLabel();
            linea = sc.nextLine();
            String[] datos = linea.split(",");
            String etiqueta = datos[datos.length-1];
            fila.setLabel(etiqueta);
            if(!tabla.containsLabel(etiqueta)) {tabla.getLabelAsInteger(etiqueta);}
            for(int i = 0; i < datos.length -1 ; i++){
                double numero = Double.parseDouble(datos[i]);
                fila.addData(numero);
            }
            tabla.addRow(fila);
        }
        return tabla;
    }
}
