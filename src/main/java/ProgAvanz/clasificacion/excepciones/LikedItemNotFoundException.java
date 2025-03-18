package ProgAvanz.clasificacion.excepciones;

public class LikedItemNotFoundException  extends  Exception{
    String item_invalido;
    public LikedItemNotFoundException(String item){
        super("El item "+item+" no se encuentra en la lista");
        item_invalido= item;
    }
    public String getItem(){
        return item_invalido;
    }
}
