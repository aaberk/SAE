package universite_paris8.iut.wad.sae_dev.Controleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.wad.sae_dev.Vue.InventaireVue;
import universite_paris8.iut.wad.sae_dev.Vue.TerrainVue;
import universite_paris8.iut.wad.sae_dev.Modele.Joueur;
import universite_paris8.iut.wad.sae_dev.Modele.Terrain;

/*
    Cette classe gère tous les événements de la souris
 */
public class Souris implements EventHandler<MouseEvent> {
    private InventaireVue inventaire;
    private TerrainVue terrainVue;
    private Terrain terrain;
    private Joueur joueur;

    public Souris(InventaireVue inventaire, TerrainVue terrainVue, Terrain terrain, Joueur joueur) {
        this.inventaire = inventaire;
        this.joueur = joueur;
        this.terrainVue = terrainVue;
        this.terrain = terrain;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            double clicX = event.getX();
            double clicY = event.getY();

            if (clicX > 10 && clicX < 46 && clicY > 10 && clicY < 46 && inventaire.estFermer()) {
                System.out.println("clic inventaire");
                inventaire.ouvrirContenu();
                return;
            }
            else if (!inventaire.hitboxInventaire(clicX, clicY)) {
                inventaire.fermerContenue();
            }

            // Gestion du clic sur le terrain (terraforming)
            int tailleTuile = terrain.getTailleTuile();
            int x = (int)(clicX / tailleTuile);
            int y = (int)(clicY / tailleTuile);

            if (x >= 0 && x < terrain.largeur() && y >= 0 && y < terrain.hauteur()) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    // Clic droit : poser un bloc
                    int bloc = joueur.getBlocSelectionne();
                    terrain.modifierBloc(x, y, bloc);
                    System.out.println("Bloc posé (" + bloc + ") en " + x + "," + y);
                } else if (event.getButton() == MouseButton.PRIMARY) {
                    // Clic gauche : supprimer un bloc
                    terrain.modifierBloc(x, y, 1);
                    System.out.println("Bloc supprimé en " + x + "," + y);
                }
                terrainVue.majAffichage();
            }
        }
    }
}