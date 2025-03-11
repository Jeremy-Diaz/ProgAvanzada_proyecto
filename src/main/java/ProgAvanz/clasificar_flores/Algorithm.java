package ProgAvanz.clasificar_flores;

public interface Algorithm <T extends Table, S,R> {
    void  train(T datos)  throws AlgoritmoSinEntrenarException;
    R estimate(S dato);
}
