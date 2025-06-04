package universite_paris8.iut.wad.sae_dev.Modele;

public class BlocMapper {

    public static TypeMateriaux typeBlocVersMateriaux(int typeBloc) {
        switch (typeBloc) {
            case 2: return TypeMateriaux.PELOUSE;
            case 4: return TypeMateriaux.COOKIE;
            case 3: return TypeMateriaux.BROWNIE;
            default: return null;
        }
    }

    public static int materiauxVersTypeBloc(TypeMateriaux materiaux) {
        switch (materiaux) {
            case PELOUSE: return 2;
            case COOKIE: return 4;
            case BROWNIE: return 3;
            default: return 1;
        }
    }

    public static boolean estBlocCollectable(int typeBloc) {
        return typeBloc == 2 || typeBloc == 4 || typeBloc == 3;
    }
}
