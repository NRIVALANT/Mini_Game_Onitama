package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente une carte de mouvement dans Onitama.
 * Chaque carte a un nom unique et définit 2 à 5 mouvements possibles.
 *
 * PRINCIPE POO : Encapsulation + Composition
 * - Encapsule une liste de Mouvements
 * - Immuable après création
 *
 * Dans Onitama, il existe 16 cartes officielles, chacune avec un nom
 * et des mouvements spécifiques inspirés des animaux ou concepts.
 */
public class CarteMouvement {
    private final String nom;
    private final List<Mouvement> mouvements;
    private final String description;

    /**
     * Constructeur pour créer une carte de mouvement.
     *
     * @param nom nom de la carte (ex: "Tiger", "Dragon", etc.)
     * @param mouvements liste des mouvements possibles
     * @param description description textuelle de la carte (optionnel)
     */
    public CarteMouvement(String nom, List<Mouvement> mouvements, String description) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom de la carte ne peut pas être vide");
        }
        if (mouvements == null || mouvements.isEmpty()) {
            throw new IllegalArgumentException("Une carte doit avoir au moins un mouvement");
        }

        this.nom = nom;
        // ENCAPSULATION : Copie défensive pour garantir l'immuabilité
        this.mouvements = new ArrayList<>(mouvements);
        this.description = description != null ? description : "";
    }

    /**
     * Constructeur simplifié sans description.
     *
     * @param nom nom de la carte
     * @param mouvements liste des mouvements
     */
    public CarteMouvement(String nom, List<Mouvement> mouvements) {
        this(nom, mouvements, "");
    }

    /**
     * Retourne tous les mouvements possibles avec cette carte.
     * ENCAPSULATION : Retourne une copie pour préserver l'immuabilité
     *
     * @return liste immuable des mouvements
     */
    public List<Mouvement> getMouvements() {
        return Collections.unmodifiableList(mouvements);
    }

    /**
     * Calcule toutes les positions atteignables depuis une position donnée.
     * PRINCIPE : Single Responsibility - la carte connaît ses mouvements
     *
     * @param positionDepart position de départ
     * @param couleur couleur du joueur (pour inverser les mouvements si nécessaire)
     * @param plateau le plateau de jeu (pour vérifier les limites)
     * @return liste des positions atteignables
     */
    public List<Position> getPositionsAtteignables(Position positionDepart, CouleurJoueur couleur) {
        List<Position> positions = new ArrayList<>();

        for (Mouvement mouvement : mouvements) {
            // Les mouvements sont définis pour le joueur ROUGE
            // Le joueur BLEU doit les inverser
            Mouvement mouvementAjuste = couleur == CouleurJoueur.BLEU ?
                    mouvement.inverser() : mouvement;

            Position destination = mouvementAjuste.appliquerA(positionDepart);

            // Vérifie que la destination est dans les limites du plateau
            if (destination.estValide()) {
                positions.add(destination);
            }
        }

        return positions;
    }

    /**
     * Vérifie si un déplacement est possible avec cette carte.
     *
     * @param depart position de départ
     * @param arrivee position d'arrivée
     * @param couleur couleur du joueur
     * @return true si le déplacement est autorisé par cette carte
     */
    public boolean peutDeplacer(Position depart, Position arrivee, CouleurJoueur couleur) {
        List<Position> atteignables = getPositionsAtteignables(depart, couleur);
        return atteignables.contains(arrivee);
    }

    /**
     * Retourne le nombre de mouvements possibles avec cette carte.
     *
     * @return nombre de mouvements
     */
    public int getNombreMouvements() {
        return mouvements.size();
    }

    // ENCAPSULATION : Getters
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Affiche la carte sous forme de grille 5x5 centrée sur le pion.
     * Utile pour visualiser les mouvements possibles.
     *
     * @param couleur couleur du joueur (pour ajuster l'orientation)
     * @return représentation visuelle de la carte
     */
    public String afficherGrille(CouleurJoueur couleur) {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────────────┐\n");
        sb.append("│ ").append(String.format("%-11s", nom)).append(" │\n");
        sb.append("├─────────────┤\n");

        // Grille 5x5 centrée (position actuelle au centre = 2,2)
        for (int ligne = 0; ligne < 5; ligne++) {
            sb.append("│ ");
            for (int col = 0; col < 5; col++) {
                // Position centrale = position du pion
                if (ligne == 2 && col == 2) {
                    sb.append("◉ ");
                    continue;
                }

                // Calcul du mouvement relatif depuis le centre
                int deltaLigne = ligne - 2;
                int deltaColonne = col - 2;

                // Ajustement pour la couleur
                Mouvement mouvementCherche = couleur == CouleurJoueur.BLEU ?
                        new Mouvement(-deltaLigne, -deltaColonne) :
                        new Mouvement(deltaLigne, deltaColonne);

                if (mouvements.contains(mouvementCherche)) {
                    sb.append("● ");
                } else {
                    sb.append("· ");
                }
            }
            sb.append("│\n");
        }

        sb.append("└─────────────┘");
        return sb.toString();
    }

    /**
     * PRINCIPE : Equals/HashCode contract
     * Deux cartes sont égales si elles ont le même nom
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteMouvement that = (CarteMouvement) o;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom + " (" + mouvements.size() + " mouvements)";
    }

    /**
     * Crée une copie de cette carte.
     * Utile pour simuler des scénarios de jeu.
     *
     * @return copie de la carte
     */
    public CarteMouvement copier() {
        return new CarteMouvement(this.nom, new ArrayList<>(this.mouvements), this.description);
    }
}
