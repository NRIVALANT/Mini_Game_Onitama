package model;

/**
 * Énumération représentant les deux couleurs de joueurs dans Onitama.
 *
 * PRINCIPE POO : Type-Safe Enum Pattern
 * - Évite les "magic strings" ou "magic numbers"
 * - Compile-time safety
 * - Peut contenir de la logique métier
 */
public enum CouleurJoueur {
    ROUGE("Rouge", "🔴", 'R'),
    BLEU("Bleu", "🔵", 'B');

    // ENCAPSULATION : Attributs privés avec getters
    private final String nom;
    private final String emoji;
    private final char symbole;

    /**
     * Constructeur privé de l'enum.
     * PRINCIPE : Encapsulation des constantes
     *
     * @param nom nom lisible de la couleur
     * @param emoji emoji pour affichage visuel
     * @param symbole caractère pour affichage simplifié
     */
    CouleurJoueur(String nom, String emoji, char symbole) {
        this.nom = nom;
        this.emoji = emoji;
        this.symbole = symbole;
    }

    /**
     * Retourne la couleur adverse.
     * PRINCIPE : Single Responsibility - l'enum connaît sa logique métier
     *
     * @return la couleur opposée
     */
    public CouleurJoueur getAdversaire() {
        return this == ROUGE ? BLEU : ROUGE;
    }

    /**
     * Retourne la ligne de départ pour cette couleur.
     * ROUGE commence en haut (ligne 0), BLEU en bas (ligne 4)
     *
     * @return ligne de départ (0-4)
     */
    public int getLigneDepart() {
        return this == ROUGE ? 0 : 4;
    }

    /**
     * Retourne le temple de destination pour cette couleur.
     * ROUGE doit atteindre le temple bleu (ligne 4), et vice-versa
     *
     * @return position du temple adverse
     */
    public Position getTempleAdverse() {
        return this == ROUGE ? new Position(4, 2) : new Position(0, 2);
    }

    /**
     * Retourne le temple de départ pour cette couleur.
     *
     * @return position du temple de départ
     */
    public Position getTemplePropre() {
        return this == ROUGE ? new Position(0, 2) : new Position(4, 2);
    }

    // ENCAPSULATION : Getters
    public String getNom() {
        return nom;
    }

    public String getEmoji() {
        return emoji;
    }

    public char getSymbole() {
        return symbole;
    }

    /**
     * Méthode toString pour affichage convivial.
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Crée une CouleurJoueur depuis un symbole.
     * PRINCIPE : Factory method pattern
     *
     * @param symbole le symbole ('R' ou 'B')
     * @return la couleur correspondante ou null si invalide
     */
    public static CouleurJoueur depuisSymbole(char symbole) {
        for (CouleurJoueur couleur : values()) {
            if (couleur.symbole == symbole) {
                return couleur;
            }
        }
        return null;
    }
}
