package model;

import java.util.Objects;

/**
 * Représente un déplacement relatif dans une carte de mouvement Onitama.
 * Les mouvements sont définis par rapport à la position actuelle du pion.
 *
 * PRINCIPE POO : Value Object Pattern
 * - Immuable
 * - Représente un déplacement relatif (deltaLigne, deltaColonne)
 *
 * Exemple : Mouvement(1, 0) = avancer d'une case vers le bas
 *           Mouvement(-1, 1) = monter d'une case et aller à droite
 */
public class Mouvement {
    private final int deltaLigne;
    private final int deltaColonne;

    /**
     * Constructeur pour créer un mouvement relatif.
     *
     * @param deltaLigne déplacement vertical (-2 à +2 généralement)
     * @param deltaColonne déplacement horizontal (-2 à +2 généralement)
     */
    public Mouvement(int deltaLigne, int deltaColonne) {
        this.deltaLigne = deltaLigne;
        this.deltaColonne = deltaColonne;
    }

    /**
     * Applique ce mouvement à une position donnée.
     * PRINCIPE : Composition avec Position
     *
     * @param positionDepart position de départ
     * @return nouvelle position après application du mouvement
     */
    public Position appliquerA(Position positionDepart) {
        return positionDepart.deplacer(deltaLigne, deltaColonne);
    }

    /**
     * Inverse le mouvement pour l'adapter à l'autre joueur.
     * Dans Onitama, les cartes sont vues depuis la perspective de chaque joueur.
     * Le joueur BLEU voit les cartes à l'envers par rapport au joueur ROUGE.
     *
     * PRINCIPE : Transformation de données
     *
     * Exemple : Si ROUGE a mouvement (1, 0) = "avancer vers BLEU"
     *           Alors BLEU voit (-1, 0) = "avancer vers ROUGE"
     *
     * @return mouvement inversé
     */
    public Mouvement inverser() {
        return new Mouvement(-deltaLigne, -deltaColonne);
    }

    /**
     * Vérifie si ce mouvement est diagonal.
     *
     * @return true si le mouvement est diagonal
     */
    public boolean estDiagonal() {
        return deltaLigne != 0 && deltaColonne != 0;
    }

    /**
     * Vérifie si ce mouvement est horizontal.
     *
     * @return true si le mouvement est horizontal
     */
    public boolean estHorizontal() {
        return deltaLigne == 0 && deltaColonne != 0;
    }

    /**
     * Vérifie si ce mouvement est vertical.
     *
     * @return true si le mouvement est vertical
     */
    public boolean estVertical() {
        return deltaLigne != 0 && deltaColonne == 0;
    }

    /**
     * Calcule la distance Manhattan (taxicab) de ce mouvement.
     * Utile pour évaluer la "puissance" d'un mouvement.
     *
     * @return distance Manhattan
     */
    public int distanceManhattan() {
        return Math.abs(deltaLigne) + Math.abs(deltaColonne);
    }

    // ENCAPSULATION : Getters
    public int getDeltaLigne() {
        return deltaLigne;
    }

    public int getDeltaColonne() {
        return deltaColonne;
    }

    /**
     * PRINCIPE : Equals/HashCode contract
     * Deux mouvements sont égaux s'ils ont les mêmes deltas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouvement mouvement = (Mouvement) o;
        return deltaLigne == mouvement.deltaLigne && deltaColonne == mouvement.deltaColonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaLigne, deltaColonne);
    }

    /**
     * Représentation textuelle du mouvement.
     * Format : (deltaLigne, deltaColonne)
     */
    @Override
    public String toString() {
        return "(" + deltaLigne + "," + deltaColonne + ")";
    }

    /**
     * Représentation visuelle avec flèches directionnelles.
     * Utile pour l'affichage des cartes
     *
     * @return symbole directionnel
     */
    public String toSymbole() {
        if (deltaLigne == 0 && deltaColonne == 0) return "•";

        // Détermination de la direction principale
        if (estVertical()) {
            return deltaLigne > 0 ? "↓" : "↑";
        }
        if (estHorizontal()) {
            return deltaColonne > 0 ? "→" : "←";
        }

        // Diagonales
        if (deltaLigne > 0 && deltaColonne > 0) return "↘";
        if (deltaLigne > 0 && deltaColonne < 0) return "↙";
        if (deltaLigne < 0 && deltaColonne > 0) return "↗";
        if (deltaLigne < 0 && deltaColonne < 0) return "↖";

        return "•";
    }

    /**
     * Crée un mouvement depuis une description textuelle.
     * PRINCIPE : Factory method pattern
     *
     * @param description ex: "1,0" ou "(1,0)"
     * @return Mouvement correspondant ou null si invalide
     */
    public static Mouvement depuisDescription(String description) {
        if (description == null || description.isEmpty()) {
            return null;
        }

        // Enlever les parenthèses si présentes
        description = description.replace("(", "").replace(")", "").trim();

        String[] parts = description.split(",");
        if (parts.length != 2) {
            return null;
        }

        try {
            int deltaLigne = Integer.parseInt(parts[0].trim());
            int deltaColonne = Integer.parseInt(parts[1].trim());
            return new Mouvement(deltaLigne, deltaColonne);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
