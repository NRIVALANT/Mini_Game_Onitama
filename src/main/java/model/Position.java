package model;

import java.util.Objects;

/**
 * Représente une position sur le plateau Onitama (5x5).
 *
 * PRINCIPE POO : Value Object Pattern
 * - Immuable (thread-safe)
 * - Equals/HashCode basés sur les valeurs
 * - Peut être utilisé comme clé dans une Map
 */
public class Position {
    private final int ligne;
    private final int colonne;

    /**
     * Constructeur pour créer une position.
     *
     * @param ligne la ligne (0-4)
     * @param colonne la colonne (0-4)
     */
    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Crée une nouvelle position en ajoutant un déplacement relatif.
     * PRINCIPE : Immuabilité - retourne une nouvelle instance
     *
     * @param deltaLigne déplacement en ligne
     * @param deltaColonne déplacement en colonne
     * @return nouvelle position résultante
     */
    public Position deplacer(int deltaLigne, int deltaColonne) {
        return new Position(this.ligne + deltaLigne, this.colonne + deltaColonne);
    }

    /**
     * Vérifie si la position est dans les limites du plateau Onitama (5x5).
     *
     * @return true si la position est valide
     */
    public boolean estValide() {
        return ligne >= 0 && ligne < 5 && colonne >= 0 && colonne < 5;
    }

    /**
     * Vérifie si c'est une position de temple rouge (en haut au centre).
     *
     * @return true si c'est le temple rouge
     */
    public boolean estTempleRouge() {
        return ligne == 0 && colonne == 2;
    }

    /**
     * Vérifie si c'est une position de temple bleu (en bas au centre).
     *
     * @return true si c'est le temple bleu
     */
    public boolean estTempleBleu() {
        return ligne == 4 && colonne == 2;
    }

    // ENCAPSULATION : Getters seulement (pas de setters car immuable)
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    /**
     * PRINCIPE : Equals/HashCode contract
     * Deux positions sont égales si elles ont les mêmes coordonnées
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return ligne == position.ligne && colonne == position.colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }

    /**
     * Représentation textuelle de la position.
     * Utile pour le débogage et l'affichage
     */
    @Override
    public String toString() {
        return "(" + ligne + "," + colonne + ")";
    }

    /**
     * Retourne la notation algébrique (style échecs : a1, b2, etc.).
     * Utile pour l'affichage utilisateur
     *
     * @return notation algébrique
     */
    public String toNotationAlgebrique() {
        char col = (char) ('a' + colonne);
        int row = 5 - ligne; // Inverse pour avoir 1 en bas
        return "" + col + row;
    }

    /**
     * Crée une position depuis une notation algébrique.
     * PRINCIPE : Factory method pattern
     *
     * @param notation notation algébrique (ex: "c3")
     * @return Position correspondante ou null si invalide
     */
    public static Position depuisNotation(String notation) {
        if (notation == null || notation.length() != 2) {
            return null;
        }

        char col = notation.charAt(0);
        char row = notation.charAt(1);

        if (col < 'a' || col > 'e' || row < '1' || row > '5') {
            return null;
        }

        int colonne = col - 'a';
        int ligne = 5 - (row - '0');

        return new Position(ligne, colonne);
    }
}
