package model.ia;

import model.IPlateau;

/**
 * Stratégie IA Difficile : utilise l'algorithme Minimax.
 *
 * PATTERN STRATEGY : Implémentation la plus avancée
 * Algorithme : Minimax (théorie des jeux)
 *
 * PRINCIPE : Algorithme récursif
 * Explore toutes les possibilités pour trouver le meilleur coup.
 */
public class StrategieDifficile implements StrategieIA {

    private char pionIA;
    private char pionAdversaire;

    /**
     * POLYMORPHISME : Implémentation avec l'algorithme Minimax.
     * L'IA devient imbattable !
     *
     * @param plateau l'état du plateau
     * @param pion le symbole de l'IA
     * @return le meilleur coup possible
     */
    @Override
    public int[] choisirCoup(IPlateau plateau, char pion) {
        this.pionIA = pion;
        this.pionAdversaire = (pion == 'X') ? 'O' : 'X';

        char[][] etat = plateau.getEtatPlateau();

        // Priorité au centre si vide (heuristique)
        if (etat[1][1] == ' ') {
            return new int[]{1, 1};
        }

        int meilleurScore = Integer.MIN_VALUE;
        int[] meilleurCoup = null;

        // Teste chaque coup possible
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (etat[i][j] == ' ') {
                    // Simule le coup
                    etat[i][j] = pionIA;

                    // Évalue avec Minimax
                    int score = minimax(etat, 0, false);

                    // Annule la simulation
                    etat[i][j] = ' ';

                    // Garde le meilleur coup
                    if (score > meilleurScore) {
                        meilleurScore = score;
                        meilleurCoup = new int[]{i, j};
                    }
                }
            }
        }

        return meilleurCoup;
    }

    /**
     * ALGORITHME RÉCURSIF : Minimax
     * Évalue chaque position en supposant que les deux joueurs
     * jouent de manière optimale.
     *
     * @param etat l'état actuel du plateau
     * @param profondeur la profondeur de récursion
     * @param estMaximisant true si c'est le tour de l'IA
     * @return le score de la position (-1, 0, ou +1)
     */
    private int minimax(char[][] etat, int profondeur, boolean estMaximisant) {
        // CAS DE BASE : vérifier si partie terminée
        int score = evaluerPlateau(etat);
        if (score != 0) return score;
        if (estPlein(etat)) return 0; // Match nul

        if (estMaximisant) {
            // Tour de l'IA : maximise le score
            int meilleurScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (etat[i][j] == ' ') {
                        etat[i][j] = pionIA;
                        int scoreActuel = minimax(etat, profondeur + 1, false);
                        etat[i][j] = ' ';
                        meilleurScore = Math.max(meilleurScore, scoreActuel);
                    }
                }
            }
            return meilleurScore;

        } else {
            // Tour de l'adversaire : minimise le score
            int meilleurScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (etat[i][j] == ' ') {
                        etat[i][j] = pionAdversaire;
                        int scoreActuel = minimax(etat, profondeur + 1, true);
                        etat[i][j] = ' ';
                        meilleurScore = Math.min(meilleurScore, scoreActuel);
                    }
                }
            }
            return meilleurScore;
        }
    }

    /**
     * Évalue le plateau actuel.
     *
     * @param etat l'état du plateau
     * @return +1 si l'IA gagne, -1 si l'adversaire gagne, 0 sinon
     */
    private int evaluerPlateau(char[][] etat) {
        if (verifierVictoirePour(etat, pionIA)) return 1;
        if (verifierVictoirePour(etat, pionAdversaire)) return -1;
        return 0;
    }

    /**
     * Vérifie si un joueur a gagné.
     */
    private boolean verifierVictoirePour(char[][] etat, char pion) {
        // Lignes et colonnes
        for (int i = 0; i < 3; i++) {
            if ((etat[i][0] == pion && etat[i][1] == pion && etat[i][2] == pion) ||
                (etat[0][i] == pion && etat[1][i] == pion && etat[2][i] == pion)) {
                return true;
            }
        }

        // Diagonales
        if ((etat[0][0] == pion && etat[1][1] == pion && etat[2][2] == pion) ||
            (etat[0][2] == pion && etat[1][1] == pion && etat[2][0] == pion)) {
            return true;
        }

        return false;
    }

    /**
     * Vérifie si le plateau est plein.
     */
    private boolean estPlein(char[][] etat) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (etat[i][j] == ' ') return false;
            }
        }
        return true;
    }

    @Override
    public String getNom() {
        return "Difficile";
    }

    @Override
    public String getDescription() {
        return "Algorithme Minimax - imbattable !";
    }
}
