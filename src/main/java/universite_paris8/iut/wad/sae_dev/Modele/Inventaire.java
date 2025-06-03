package universite_paris8.iut.wad.sae_dev.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventaire {
    private ObservableList<Materiaux> materiauxList;

    public Inventaire() {
        this.materiauxList = FXCollections.observableArrayList();
    }

    public ObservableList<Materiaux> getMateriauxList() {
        return materiauxList;
    }

    public void ajouterMateriaux(Materiaux m){
        materiauxList.add(m);
    }

    public void retirerMateriaux(Materiaux m) {
        materiauxList.remove(m);
    }

    public boolean contientMateriaux(Materiaux m) {
        return materiauxList.contains(m);
    }

    public int getNombreMateriaux() {
        return materiauxList.size();
    }
}
