package model;

import java.util.Objects;

/**
 * Représente un pion dans le jeu Onitama.
 * Chaque joueur possède 5 pions : 1 Maître et 4 Élèves.
 *
 * PRINCIPE POO : Composition
 * - Combine TypePion, CouleurJoueur et Position
 * - Encapsulation : l'objet gère son propre état
 */
public class Pion {
    private final TypePion type;
    private final CouleurJoueur couleur;
    private Position position;
    private boolean enJeu;

    /**
     * Constructeur pour créer un pion.
     *
     * @param type type du pion (MAITRE ou ELEVE)
     * @param couleur couleur du joueur propriétaire
     * @param position position initiale sur le plateau
     */
    public Pion(TypePion type, CouleurJoueur couleur, Position position) {
        this.type = type;
        this.couleur = couleur;
        this.position = position;
        this.enJeu = true;
    }

    /**
     * Déplace le pion vers une nouvelle position.
     * PRINCIPE : Encapsulation - la position est modifiée via une méthode
     *
     * @param nouvellePosition la nouvelle position
     */
    public void deplacer(Position nouvellePosition) {
        if (nouvellePosition == null) {
            throw new IllegalArgumentException("La position ne peut pas être nulle");
        }
        this.position = nouvellePosition;
    }

    /**
     * Capture ce pion (le retire du jeu).
     * PRINCIPE : Encapsulation - l'état est modifié de manière contrôlée
     */
    public void capturer() {
        this.enJeu = false;
    }

    /**
     * Vérifie si ce pion est un Maître.
     * PRINCIPE : Delegation - délègue à l'enum TypePion
     *
     * @return true si c'est un Maître
     */
    public boolean estMaitre() {
        return type == TypePion.MAITRE;
    }

    /**
     * Vérifie si ce pion est un Élève.
     *
     * @return true si c'est un Élève
     */
    public boolean estEleve() {
        return type == TypePion.ELEVE;
    }

    /**
     * Vérifie si ce pion appartient à la couleur spécifiée.
     *
     * @param couleur couleur à vérifier
     * @return true si le pion appartient à cette couleur
     */
    public boolean appartientA(CouleurJoueur couleur) {
        return this.couleur == couleur;
    }

    /**
     * Vérifie si ce pion peut gagner en atteignant le temple adverse.
     *
     * @return true si le pion est un Maître sur le temple adverse
     */
    public boolean aGagnéParTemple() {
        if (!estMaitre() || !enJeu) {
            return false;
        }
        Position templeAdverse = couleur.getTempleAdverse();
        return position.equals(templeAdverse);
    }

    /**
     * Retourne une représentation textuelle du pion pour l'affichage.
     * Format : emoji du type + emoji de la couleur
     *
     * @return représentation visuelle du pion
     */
    public String afficher() {
        if (!enJeu) {
            return "❌"; // Pion capturé
        }
        // Combine le type et la couleur
        return type.getEmoji() + couleur.getEmoji();
    }

    /**
     * Retourne une représentation simple (1 caractère) pour affichage compact.
     * Format : MR (Maître Rouge), EB (Élève Bleu), etc.
     *
     * @return représentation compacte
     */
    public String afficherCompact() {
        if (!enJeu) {
            return "XX";
        }
        return "" + type.getSymbole() + couleur.getSymbole();
    }

    // ENCAPSULATION : Getters (pas de setters sauf pour position via deplacer())
    public TypePion getType() {
        return type;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    public Position getPosition() {
        return position;
    }

    public boolean estEnJeu() {
        return enJeu;
    }

    /**
     * PRINCIPE : Equals/HashCode contract
     * Deux pions sont identiques s'ils ont le même type et la même couleur
     * (car il n'y a qu'un seul Maître par couleur, et les Élèves sont interchangeables)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pion pion = (Pion) o;
        return type == pion.type && couleur == pion.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, couleur);
    }

    @Override
    public String toString() {
        return type.getNom() + " " + couleur.getNom() +
                " à " + position +
                (enJeu ? "" : " (capturé)");
    }

    /**
     * Crée une copie profonde du pion.
     * PRINCIPE : Prototype pattern - utile pour simuler des coups
     *
     * @return une copie du pion
     */
    public Pion copier() {
        Pion copie = new Pion(this.type, this.couleur, this.position);
        copie.enJeu = this.enJeu;
        return copie;
    }
}
