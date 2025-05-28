// Cette classe crée et affiche le joueur à l’écran.

package universite_paris8.iut.wad.sae_dev.Vue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.wad.sae_dev.Modele.Joueur;

public class JoueurVue {

    private Joueur joueur;
    private Pane pane;
    private ImageView joueurView;
    private Image joueurImageD, joueurImageG, joueurImageI;
    private StringProperty direction;

    public JoueurVue(Joueur joueur, Pane pane) {
        this.joueur = joueur;
        this.pane = pane;
        this.direction = new SimpleStringProperty("immobile");

        this.joueurImageD = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/joueurD.gif").toExternalForm());
        this.joueurImageG = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/joueurG.gif").toExternalForm());
        this.joueurImageI = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/joueurI.png").toExternalForm());
        this.joueurView = new ImageView(joueurImageI);
        bindPosition();
        pane.getChildren().add(joueurView);
    }

    private void bindPosition() {
        joueurView.translateXProperty().bind(joueur.xProperty());
        joueurView.translateYProperty().bind(joueur.yProperty());
    }

    public void setDirection(String newDirection) {
        direction.set(newDirection);
        modifierImage();
    }

    private void modifierImage() {
        switch (direction.get()) {
            case "immobile" -> {
                joueurView.setImage(joueurImageI);
            }
            case "droite" -> {
                joueurView.setImage(joueurImageD);
            }
            case "gauche" -> {
                joueurView.setImage(joueurImageG);
            }
        }
    }
}
