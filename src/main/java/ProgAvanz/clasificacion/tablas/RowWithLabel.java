package ProgAvanz.clasificacion.tablas;

import java.util.List;

public class RowWithLabel extends Row {
    private String label;

    public RowWithLabel(){
        super();
    }
    public RowWithLabel(List<Double> data, String etiqueta){
        super(data);
        label = etiqueta;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

}
