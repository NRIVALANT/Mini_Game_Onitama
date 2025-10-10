package model;

/**
 * Énumération représentant les symboles du jeu (X, O, ou case vide).
 *
 * CONCEPT POO : ÉNUMÉRATION AVEC COMPORTEMENT
 * Chaque constante peut avoir ses propres attributs et méthodes.
 */
public enum Symbole {
    X('X', "❌"),
    O('O', "⭕"),
    VIDE(' ', "⬜");

    private final char caractere;
    private final String emoji;

    /**
     * Constructeur de l'énumération
     * @param caractere Le caractère classique (X, O ou espace)
     * @param emoji L'emoji correspondant pour un affichage moderne
     */
    Symbole(char caractere, String emoji) {
        this.caractere = caractere;
        this.emoji = emoji;
    }

    public char getCaractere() {
        return caractere;
    }

    public String getEmoji() {
        return emoji;
    }

    /**
     * MÉTHODE STATIQUE : Permet de créer un Symbole à partir d'un char
     * @param c le caractère à convertir
     * @return le Symbole correspondant
     */
    public static Symbole fromChar(char c) {
        for (Symbole symbole : values()) {
            if (symbole.caractere == c) {
                return symbole;
            }
        }
        return VIDE;
    }

    /**
     * Retourne le symbole opposé (utile pour l'IA)
     * @return O si this==X, X si this==O, VIDE sinon
     */
    public Symbole opposé() {
        if (this == X) return O;
        if (this == O) return X;
        return VIDE;
    }

    @Override
    public String toString() {
        return String.valueOf(caractere);
    }
}
