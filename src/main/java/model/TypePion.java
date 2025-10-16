package model;

/**
 * Énumération représentant les types de pions dans Onitama.
 *
 * PRINCIPE POO : Type-Safe Enum Pattern
 * - Chaque joueur a 1 Maître et 4 Élèves
 * - Le Maître est crucial pour la victoire
 */
public enum TypePion {
    MAITRE("Maître", "👑", 'M'),
    ELEVE("Élève", "⚔️", 'E');

    // ENCAPSULATION : Attributs privés
    private final String nom;
    private final String emoji;
    private final char symbole;

    /**
     * Constructeur privé de l'enum.
     *
     * @param nom nom du type de pion
     * @param emoji emoji pour affichage visuel
     * @param symbole caractère pour affichage simplifié
     */
    TypePion(String nom, String emoji, char symbole) {
        this.nom = nom;
        this.emoji = emoji;
        this.symbole = symbole;
    }

    /**
     * Vérifie si ce type de pion peut gagner en atteignant le temple adverse.
     * Seul le Maître peut gagner de cette façon.
     *
     * @return true si c'est un Maître
     */
    public boolean peutGagnerParTemple() {
        return this == MAITRE;
    }

    /**
     * Vérifie si la capture de ce pion entraîne la victoire.
     * Capturer le Maître adverse = victoire immédiate
     *
     * @return true si c'est un Maître
     */
    public boolean captureEntraineVictoire() {
        return this == MAITRE;
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

    @Override
    public String toString() {
        return nom;
    }

    /**
     * Crée un TypePion depuis un symbole.
     * PRINCIPE : Factory method pattern
     *
     * @param symbole le symbole ('M' ou 'E')
     * @return le type correspondant ou null si invalide
     */
    public static TypePion depuisSymbole(char symbole) {
        for (TypePion type : values()) {
            if (type.symbole == symbole) {
                return type;
            }
        }
        return null;
    }
}
