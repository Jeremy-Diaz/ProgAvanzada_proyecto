package ProgAvanz.clasificacion.tablas;

import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table {

    // atributos
    private Map<String, Integer> labelsToIndex;

    // contructores
    public TableWithLabels() {
        super();
        labelsToIndex = new HashMap<>();
    }

    // metodos
    /*public void addLabel(String etiqueta) {
        if(!labelsToIndex.containsKey(etiqueta)){
            labelsToIndex.put(etiqueta,labelsToIndex.size());
        }
    }*/
    public boolean containsLabel( String etiqueta){
        return labelsToIndex.containsKey(etiqueta);
    }
    public int getLabelAsInteger(String etiqueta) {
        if(!labelsToIndex.containsKey(etiqueta)){
            labelsToIndex.put(etiqueta,labelsToIndex.size());
        }
        return labelsToIndex.get(etiqueta);
    }
    @Override
    public RowWithLabel getRowAt(int i) {
        return (RowWithLabel) super.getRowAt(i);
    }

    public int getNumRows(){
        return super.getRows().size();
    }

}
