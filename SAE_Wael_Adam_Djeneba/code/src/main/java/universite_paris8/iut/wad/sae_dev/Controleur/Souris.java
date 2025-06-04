package universite_paris8.iut.wad.sae_dev.Controleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.wad.sae_dev.Vue.InventaireVue;
import universite_paris8.iut.wad.sae_dev.Vue.TerrainVue;
import universite_paris8.iut.wad.sae_dev.Modele.Joueur;
import universite_paris8.iut.wad.sae_dev.Modele.Terrain;
import universite_paris8.iut.wad.sae_dev.Modele.Inventaire;
import universite_paris8.iut.wad.sae_dev.Modele.BlocMapper;
import universite_paris8.iut.wad.sae_dev.Modele.TypeMateriaux;

public class Souris implements EventHandler<MouseEvent> {
    private InventaireVue inventaireVue;
    private TerrainVue terrainVue;
    private Terrain terrain;
    private Joueur joueur;
    private Inventaire inventaire; // Ajout de l'inventaire

    public Souris(InventaireVue inventaireVue, TerrainVue terrainVue, Terrain terrain, Joueur joueur, Inventaire inventaire) {
        this.inventaireVue = inventaireVue;
        this.joueur = joueur;
        this.terrainVue = terrainVue;
        this.terrain = terrain;
        this.inventaire = inventaire;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            double clicX = event.getX();
            double clicY = event.getY();

            if (clicX > 10 && clicX < 46 && clicY > 10 && clicY < 46 && inventaireVue.estFermer()) {
                System.out.println("clic inventaire");
                inventaireVue.ouvrirContenu();
                return;
            }
            else if (!inventaireVue.hitboxInventaire(clicX, clicY)) {
                inventaireVue.fermerContenue();
            }

            int tailleTuile = terrain.getTailleTuile();
            int x = (int)(clicX / tailleTuile);
            int y = (int)(clicY / tailleTuile);

            if (x >= 0 && x < terrain.largeur() && y >= 0 && y < terrain.hauteur()) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    int bloc = joueur.getBlocSelectionne();
                    TypeMateriaux materiauxRequis = BlocMapper.typeBlocVersMateriaux(bloc);

                    if (materiauxRequis != null && inventaire.contientMateriaux(materiauxRequis)) {
                        if (inventaire.retirerMateriaux(materiauxRequis, 1)) {
                            terrain.modifierBloc(x, y, bloc);
                            System.out.println("Bloc posé (" + bloc + ") en " + x + "," + y);
                            inventaireVue.rafraichirAffichage();
                        }
                    } else {
                        System.out.println("Pas assez de matériaux pour poser ce bloc !");
                    }

                } else if (event.getButton() == MouseButton.PRIMARY) {
                    int blocActuel = terrain.typeTuile(x, y);

                    if (BlocMapper.estBlocCollectable(blocActuel)) {
                        TypeMateriaux materiauxRecupere = BlocMapper.typeBlocVersMateriaux(blocActuel);
                        if (materiauxRecupere != null) {
                            inventaire.ajouterMateriaux(materiauxRecupere, 1);
                            terrain.modifierBloc(x, y, 1);
                            System.out.println("Bloc récupéré en " + x + "," + y);
                            inventaireVue.rafraichirAffichage();
                        }
                    } else {
                        terrain.modifierBloc(x, y, 1);
                        System.out.println("Bloc supprimé en " + x + "," + y);
                    }
                }
                terrainVue.majAffichage();
            }
        }
    }
}