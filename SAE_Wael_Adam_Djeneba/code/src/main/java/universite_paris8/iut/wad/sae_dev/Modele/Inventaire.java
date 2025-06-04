package universite_paris8.iut.wad.sae_dev.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Inventaire {
    private ObservableMap<TypeMateriaux, Integer> materiauxQuantites;

    public Inventaire() {
        this.materiauxQuantites = FXCollections.observableHashMap();
        this.materiauxQuantites.put(TypeMateriaux.COOKIE, 5);
        this.materiauxQuantites.put(TypeMateriaux.BROWNIE, 3);
        this.materiauxQuantites.put(TypeMateriaux.PELOUSE, 2);    }

    public ObservableMap<TypeMateriaux, Integer> getMateriauxQuantites() {
        return materiauxQuantites;
    }

    public void ajouterMateriaux(TypeMateriaux type, int quantite) {
        int quantiteActuelle = materiauxQuantites.getOrDefault(type, 0);
        materiauxQuantites.put(type, quantiteActuelle + quantite);
        System.out.println("Ajouté " + quantite + " " + type + " (total: " + materiauxQuantites.get(type) + ")");
    }

    public boolean retirerMateriaux(TypeMateriaux type, int quantite) {
        int quantiteActuelle = materiauxQuantites.getOrDefault(type, 0);
        if (quantiteActuelle >= quantite) {
            int nouvelleQuantite = quantiteActuelle - quantite;
            if (nouvelleQuantite == 0) {
                materiauxQuantites.remove(type);
            } else {
                materiauxQuantites.put(type, nouvelleQuantite);
            }
            System.out.println("Retiré " + quantite + " " + type + " (reste: " + nouvelleQuantite + ")");
            return true;
        }
        return false;
    }

    public int getQuantite(TypeMateriaux type) {
        return materiauxQuantites.getOrDefault(type, 0);
    }

    public boolean contientMateriaux(TypeMateriaux type) {
        return materiauxQuantites.containsKey(type) && materiauxQuantites.get(type) > 0;
    }
}
