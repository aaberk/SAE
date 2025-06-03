// Cette classe gère les entrées clavier pour déplacer le joueur.

package universite_paris8.iut.wad.sae_dev.Controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.wad.sae_dev.Modele.Joueur;
import universite_paris8.iut.wad.sae_dev.Vue.JoueurVue;

public class Clavier implements EventHandler<KeyEvent> {

    private final Joueur joueur;
    private final JoueurVue joueurVue;
    private int derniereDirection = 1;

    public Clavier(Joueur joueur, JoueurVue joueurVue) {
        this.joueur = joueur;
        this.joueurVue = joueurVue;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (event.getCode()) {
                case LEFT -> {
                    joueur.setDirection(-1); // -1 pour gauche
                    derniereDirection = -1;
                }
                case RIGHT -> {
                    joueur.setDirection(1); // 1 pour droite
                    derniereDirection = 1;
                }
                case SPACE -> {
                    System.out.println("Touche saut pressée");
                    joueur.saut();
                }

            }
        }
        else if (event.getEventType() == KeyEvent.KEY_RELEASED &&
                (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)) {
            joueur.setDirection(0); // 0 pour immobile

            joueurVue.setDirectionImmobile(derniereDirection == -1);
        }
    }
}