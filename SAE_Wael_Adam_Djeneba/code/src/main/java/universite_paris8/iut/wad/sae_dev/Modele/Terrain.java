// Cette classe définit la structure du terrain sous forme d'une grille de tuiles codées par des entiers.

package universite_paris8.iut.wad.sae_dev.Modele;

public class Terrain {

    private static final int TAILLE_TUILE = 59;

    private int[][] typesTuiles = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,6,2,2,5,1,6,2,2,5,1,1,1,1},
            {2,2,2,3,3,3,3,2,3,3,3,3,2,2,2,2},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,3,3,3,3,4,3,3,3,3,3,3,3,3,3,3},
            {3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
            {4,3,4,4,4,3,3,3,3,3,3,3,4,4,3,4},
    };

    public int typeTuile(int x, int y) {
        return this.typesTuiles[y][x];
    }

    public int hauteur() {
        return this.typesTuiles.length;
    }

    public int largeur() {
        return this.typesTuiles[0].length;
    }

    public int getLargeurPixels() {
        return largeur() * TAILLE_TUILE;
    }

    public int getHauteurPixels() {
        return hauteur() * TAILLE_TUILE;
    }

    public int getTailleTuile() {
        return TAILLE_TUILE;
    }

    public boolean collision(int tuileX, int tuileY) {
        int colonne = tuileX / TAILLE_TUILE;
        int ligne = tuileY / TAILLE_TUILE;

        if (colonne < 0 || colonne >= largeur() || ligne < 0 || ligne >= hauteur()) {
            return true;
        }

        int type = typeTuile(colonne, ligne);
        return type != 1 ;
    }

    public void modifierBloc(int x, int y, int nouveauType) {
        if (x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            typesTuiles[y][x] = nouveauType;
        }
    }
}