package universite_paris8.iut.wad.sae_dev.Controleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.wad.sae_dev.Vue.InventaireVue;

/*
    Cette classe gère tous les événements de la souris
 */
public class Souris implements EventHandler<MouseEvent> {
    private InventaireVue inventaire;

    public Souris(InventaireVue inventaire) {
        this.inventaire = inventaire;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (mouseEvent.getX() > 10 && mouseEvent.getX() < 46 &&
                    mouseEvent.getY() > 10 && mouseEvent.getY() < 46 && inventaire.estFermer()) {
                System.out.println("clic inventaire");
                inventaire.ouvrirContenu();
            }
            else if (!inventaire.hitboxInventaire(mouseEvent.getX(), mouseEvent.getY())) {
                inventaire.fermerContenue();
            }
        }
    }
}