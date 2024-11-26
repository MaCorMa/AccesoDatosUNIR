package model;

import java.util.ArrayList;

public interface CocheDao {
    boolean altaCoche(Coche coche);
    boolean borrarCoche(int id);
    Coche buscaCocheId(int id);
    ArrayList<Coche> listaTodo();
    void leeCoches(String path);
    boolean exportaDat(ArrayList<Coche>lista);
    boolean exportaCsv(ArrayList<Coche>lista);
}
