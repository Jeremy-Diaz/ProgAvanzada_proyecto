package ProgAvanz.clasificar_flores;
import java.util.LinkedList;
import java.util.List;
public class TableWithLabels extends Table{

    List<RowWithLabel> filasConEtiquetas;
    public TableWithLabels(){
        super();
        filasConEtiquetas = new LinkedList<>();
    }
    @Override
    public RowWithLabel getRowAt( int i){
        return filasConEtiquetas.get(i);
    }

    public List<RowWithLabel> getRowsWithLabels() {
        return filasConEtiquetas;
    }

    public List<Double> getColumAt(int num_atributo){
        List<Double> datos_columna = new LinkedList<>();
        for(RowWithLabel fila: filasConEtiquetas) {
            datos_columna.add(fila.getDatos().get(num_atributo));
        }
        return datos_columna;
    }
    public void addRow(RowWithLabel fila) {
        filasConEtiquetas.add(fila);
    }
}
