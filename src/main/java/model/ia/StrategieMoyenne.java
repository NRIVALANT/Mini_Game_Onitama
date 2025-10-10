package model.ia;

import model.IPlateau;

/**
 * Stratégie IA Moyenne : bloque l'adversaire et tente de gagner.
 *
 * PATTERN STRATEGY : Implémentation concrète
 * Hérite du comportement aléatoire et ajoute de la logique.
 *
 * PRINCIPE : Code Reuse (réutilisation de code)
 * Utilise StrategieFacile comme fallback.
 */
public class StrategieMoyenne implements StrategieIA {

    // COMPOSITION : Utilise la stratégie facile comme fallback
    private final StrategieFacile strategieFallback;

    public StrategieMoyenne() {
        this.strategieFallback = new StrategieFacile();
    }

    /**
     * POLYMORPHISME : Implémentation avec logique tactique.
     * Algorithme :
     * 1. Cherche un coup gagnant
     * 2. Sinon, bloque l'adversaire s'il peut gagner
     * 3. Sinon, joue aléatoirement
     *
     * @param plateau l'état du plateau
     * @param pion le symbole de l'IA
     * @return le meilleur coup tactique
     */
    @Override
    public int[] choisirCoup(IPlateau plateau, char pion) {
        char[][] etat = plateau.getEtatPlateau();

        // Étape 1 : Chercher un coup gagnant pour l'IA
        int[] coupGagnant = chercherCoupGagnant(etat, pion);
        if (coupGagnant != null) {
            return coupGagnant;
        }

        // Étape 2 : Bloquer l'adversaire s'il peut gagner
        char adversaire = (pion == 'X') ? 'O' : 'X';
        int[] coupBloquant = chercherCoupGagnant(etat, adversaire);
        if (coupBloquant != null) {
            return coupBloquant;
        }

        // Étape 3 : Fallback vers stratégie aléatoire
        return strategieFallback.choisirCoup(plateau, pion);
    }

    /**
     * ENCAPSULATION : Méthode privée helper.
     * Cherche un coup qui permettrait de gagner immédiatement.
     *
     * @param etat l'état du plateau
     * @param pion le symbole à tester
     * @return les coordonnées du coup gagnant, ou null
     */
    private int[] chercherCoupGagnant(char[][] etat, char pion) {
        // Teste chaque case vide
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (etat[i][j] == ' ') {
                    // Simule le coup
                    etat[i][j] = pion;

                    // Vérifie si ce coup gagne
                    boolean gagne = verifierVictoirePour(etat, pion);

                    // Annule la simulation
                    etat[i][j] = ' ';

                    if (gagne) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * ENCAPSULATION : Copie de la logique de vérification.
     * Vérifie si un pion a gagné.
     *
     * @param etat l'état du plateau
     * @param pion le symbole à vérifier
     * @return true si victoire
     */
    private boolean verifierVictoirePour(char[][] etat, char pion) {
        // Vérifie lignes et colonnes
        for (int i = 0; i < 3; i++) {
            if ((etat[i][0] == pion && etat[i][1] == pion && etat[i][2] == pion) ||
                (etat[0][i] == pion && etat[1][i] == pion && etat[2][i] == pion)) {
                return true;
            }
        }

        // Vérifie diagonales
        if ((etat[0][0] == pion && etat[1][1] == pion && etat[2][2] == pion) ||
            (etat[0][2] == pion && etat[1][1] == pion && etat[2][0] == pion)) {
            return true;
        }

        return false;
    }

    @Override
    public String getNom() {
        return "Moyen";
    }

    @Override
    public String getDescription() {
        return "Cherche à gagner et bloque l'adversaire";
    }
}
