package universite_paris8.iut.wad.sae_dev.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.wad.sae_dev.Modele.Inventaire;
import universite_paris8.iut.wad.sae_dev.Modele.Materiaux;
import universite_paris8.iut.wad.sae_dev.Modele.Role;
import universite_paris8.iut.wad.sae_dev.Modele.TypeMateriaux;


public class InventaireVue {

    private Pane pane;
    private Inventaire inventaire;
    private Pane paneInventaire;

    private final int TAILLECASE = 56;
    private final int TAILLEITEM = 42;
    private final int APPARITIONITEM = (TAILLECASE - TAILLEITEM)/2;

    private boolean fermer;


    public InventaireVue(Pane pane, Inventaire inventaire, Pane paneInventaire) {
        fermer = true;

        this.pane = pane;
        this.inventaire=inventaire;
        afficherInventaire();

        this.paneInventaire = paneInventaire;

        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.COOKIE, Role.CONSTRUCTION));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.BROWNIE, Role.CONSTRUCTION));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.CARAMEL, Role.CONSTRUCTION));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.PELLE, Role.OUTIL));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.CUILLERE, Role.OUTIL));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.SUCRE_ORGE, Role.ARME));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.BONBON_COCA, Role.SOIN));
        inventaire.ajouterMateriaux(new Materiaux(TypeMateriaux.BONBON_CLE, Role.MONNAIE));
    }

    public boolean estFermer() {
        return fermer;
    }//TODO a priori devrait se toruver côté modèle dans Inventaire


    private void afficherInventaire() {
        Image sac = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/ciel1.png").toExternalForm());
        ImageView sacView = new ImageView(sac);
        sacView.setFitHeight(46);
        sacView.setFitWidth(46);
        sacView.setTranslateX(10);
        sacView.setTranslateY(10);
        this.pane.getChildren().add(sacView);
    }

    public void afficherContenu (){
        fermer = false;
        //TODO à charger une seule fois
        Image cookie = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image cle = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image pelle = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image cuillere = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image sucreOrge = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image bonbonCoca = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image brownie = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image caramel = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());
        Image bonbonCle = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/cookie.png").toExternalForm());

        Image inventaireVide = new Image(getClass().getResource("/universite_paris8/iut/wad/sae_dev/images/ciel1.png").toExternalForm());
        ImageView inventaireVideVue;

        int x = 200;
        int y = 40;

        if (!inventaire.getMateriauxList().isEmpty()) {

            for (Materiaux materiaux : inventaire.getMateriauxList()) {

                if(x>500){
                    x = 200;
                    y = y + TAILLECASE;
                }

                inventaireVideVue = new ImageView(inventaireVide);

                inventaireVideVue.setFitHeight(TAILLECASE);
                inventaireVideVue.setFitWidth(TAILLECASE);
                inventaireVideVue.setTranslateY(y);
                inventaireVideVue.setTranslateX(x);
                paneInventaire.getChildren().add(inventaireVideVue);

                ImageView img = new ImageView();
                switch (materiaux.getNom()) {
                    case COOKIE:
                        img = new ImageView(cookie);
                        break;
                    case CLE:
                        img = new ImageView(cle);
                        break;
                    case PELLE:
                        img = new ImageView(pelle);
                        break;
                    case CUILLERE:
                        img = new ImageView(cuillere);
                        break;
                    case SUCRE_ORGE:
                        img = new ImageView(sucreOrge);
                        break;
                    case BONBON_COCA:
                        img = new ImageView(bonbonCoca);
                        break;
                    case BROWNIE:
                        img = new ImageView(brownie);
                        break;
                    case CARAMEL:
                        img = new ImageView(caramel);
                        break;
                    case BONBON_CLE:
                        img = new ImageView(bonbonCle);
                        break;
                }

                img.setTranslateX(x+ APPARITIONITEM);
                img.setTranslateY(y+ APPARITIONITEM);
                img.setFitHeight(TAILLEITEM);
                img.setFitWidth(TAILLEITEM);
                paneInventaire.getChildren().add(img);

                x=x+ TAILLECASE;
            }
        }

        while (x<500){
            inventaireVideVue = new ImageView(inventaireVide);
            inventaireVideVue.setFitHeight(TAILLECASE);
            inventaireVideVue.setFitWidth(TAILLECASE);
            inventaireVideVue.setTranslateY(y);
            inventaireVideVue.setTranslateX(x);
            paneInventaire.getChildren().add(inventaireVideVue);
            x=x+ TAILLECASE;
        }
    }

    public void ouvrirContenu(){
        paneInventaire.setVisible(true);
        afficherContenu();
    }

    public void fermerContenue() {
        fermer = true;
        paneInventaire.setVisible(false);
        paneInventaire.getChildren().removeAll();
    }

    public boolean hitboxInventaire(double x, double y){
        for (int i = 0; i < paneInventaire.getChildren().size(); i++){
            if( x >= paneInventaire.getChildren().get(i).getTranslateX() && x <= paneInventaire.getChildren().get(i).getTranslateX() + TAILLECASE
                    && y >= paneInventaire.getChildren().get(i).getTranslateY() && y <= paneInventaire.getChildren().get(i).getTranslateY() + TAILLECASE){
                System.out.println("dans l'inventaire");
                return true;
            }
        }
        return false;
    }
}
