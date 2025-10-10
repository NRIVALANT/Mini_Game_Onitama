package model.ia;

import model.IPlateau;
import java.util.Random;

/**
 * Stratégie IA Facile : joue aléatoirement.
 *
 * PATTERN STRATEGY : Implémentation concrète
 * Cette classe encapsule l'algorithme "Facile".
 *
 * PRINCIPE : Single Responsibility
 * Cette classe a UNE responsabilité : jouer de façon aléatoire.
 */
public class StrategieFacile implements StrategieIA {

    // ENCAPSULATION : Random privé pour générer les coups
    private final Random random;

    /**
     * Constructeur.
     */
    public StrategieFacile() {
        this.random = new Random();
    }

    /**
     * POLYMORPHISME : Implémentation spécifique de choisirCoup.
     * Algorithme : Choix complètement aléatoire parmi les cases vides.
     *
     * @param plateau l'état du plateau
     * @param pion le symbole de l'IA
     * @return un coup aléatoire valide
     */
    @Override
    public int[] choisirCoup(IPlateau plateau, char pion) {
        char[][] etat = plateau.getEtatPlateau();
        int ligne, colonne;

        // Boucle jusqu'à trouver une case vide
        do {
            ligne = random.nextInt(3);
            colonne = random.nextInt(3);
        } while (etat[ligne][colonne] != ' ');

        return new int[]{ligne, colonne};
    }

    @Override
    public String getNom() {
        return "Facile";
    }

    @Override
    public String getDescription() {
        return "Joue aléatoirement sans stratégie";
    }
}
