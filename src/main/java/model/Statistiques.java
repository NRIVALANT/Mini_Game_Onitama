package model;

/**
 * Classe représentant les statistiques d'un joueur.
 *
 * CONCEPTS POO APPLIQUÉS :
 * 1. ENCAPSULATION : Tous les attributs sont privés
 * 2. GETTERS/SETTERS : Accès contrôlé aux données
 * 3. VALIDATION : Les setters valident les données
 * 4. CALCULS DÉRIVÉS : Méthodes calculant des stats à partir des données
 * 5. IMMUTABILITÉ PARTIELLE : Certaines données ne peuvent pas être modifiées directement
 */
public class Statistiques {

    // ==================== ATTRIBUTS PRIVÉS (ENCAPSULATION) ====================
    // PRINCIPE : Hide implementation details - Les données sont cachées

    private final String nomJoueur;  // final = immuable après construction
    private int partiesJouees;
    private int victoires;
    private int defaites;
    private int matchsNuls;
    private int coupsJoues;

    // ==================== CONSTRUCTEURS ====================

    /**
     * Constructeur principal.
     * PRINCIPE : Validation des données à la construction
     *
     * @param nomJoueur le nom du joueur (ne peut pas être null ou vide)
     * @throws IllegalArgumentException si le nom est invalide
     */
    public Statistiques(String nomJoueur) {
        // VALIDATION : On vérifie que le nom est valide
        if (nomJoueur == null || nomJoueur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du joueur ne peut pas être vide");
        }

        this.nomJoueur = nomJoueur;
        this.partiesJouees = 0;
        this.victoires = 0;
        this.defaites = 0;
        this.matchsNuls = 0;
        this.coupsJoues = 0;
    }

    /**
     * Constructeur de copie.
     * PRINCIPE : Permet de créer une copie d'un objet Statistiques
     *
     * @param autre les statistiques à copier
     */
    public Statistiques(Statistiques autre) {
        this.nomJoueur = autre.nomJoueur;
        this.partiesJouees = autre.partiesJouees;
        this.victoires = autre.victoires;
        this.defaites = autre.defaites;
        this.matchsNuls = autre.matchsNuls;
        this.coupsJoues = autre.coupsJoues;
    }

    // ==================== GETTERS (LECTURE SEULE) ====================
    // PRINCIPE : Accès en lecture aux données privées

    /**
     * @return le nom du joueur (immuable)
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @return le nombre total de parties jouées
     */
    public int getPartiesJouees() {
        return partiesJouees;
    }

    /**
     * @return le nombre de victoires
     */
    public int getVictoires() {
        return victoires;
    }

    /**
     * @return le nombre de défaites
     */
    public int getDefaites() {
        return defaites;
    }

    /**
     * @return le nombre de matchs nuls
     */
    public int getMatchsNuls() {
        return matchsNuls;
    }

    /**
     * @return le nombre total de coups joués
     */
    public int getCoupsJoues() {
        return coupsJoues;
    }

    // ==================== MÉTHODES DE MODIFICATION (LOGIQUE MÉTIER) ====================
    // PRINCIPE : Au lieu de setters bruts, on expose des méthodes métier

    /**
     * Enregistre une victoire.
     * PRINCIPE : Méthode métier plutôt que setter simple
     */
    public void enregistrerVictoire() {
        this.victoires++;
        this.partiesJouees++;
    }

    /**
     * Enregistre une défaite.
     */
    public void enregistrerDefaite() {
        this.defaites++;
        this.partiesJouees++;
    }

    /**
     * Enregistre un match nul.
     */
    public void enregistrerMatchNul() {
        this.matchsNuls++;
        this.partiesJouees++;
    }

    /**
     * Incrémente le compteur de coups joués.
     *
     * @param nombreCoups le nombre de coups à ajouter (doit être positif)
     * @throws IllegalArgumentException si le nombre est négatif
     */
    public void ajouterCoups(int nombreCoups) {
        // VALIDATION : On vérifie que le nombre est positif
        if (nombreCoups < 0) {
            throw new IllegalArgumentException("Le nombre de coups doit être positif");
        }
        this.coupsJoues += nombreCoups;
    }

    /**
     * Réinitialise toutes les statistiques à zéro.
     * PRINCIPE : Méthode métier pour un cas d'usage spécifique
     */
    public void reinitialiser() {
        this.partiesJouees = 0;
        this.victoires = 0;
        this.defaites = 0;
        this.matchsNuls = 0;
        this.coupsJoues = 0;
    }

    // ==================== MÉTHODES CALCULÉES (GETTERS DÉRIVÉS) ====================
    // PRINCIPE : Calculs à partir des données, pas de stockage redondant

    /**
     * Calcule le taux de victoire en pourcentage.
     * PRINCIPE : Données dérivées calculées à la demande
     *
     * @return le taux de victoire (0-100), ou 0 si aucune partie jouée
     */
    public double getTauxVictoire() {
        if (partiesJouees == 0) {
            return 0.0;
        }
        return (victoires * 100.0) / partiesJouees;
    }

    /**
     * Calcule le ratio victoires/défaites.
     *
     * @return le ratio V/D, ou 0 si aucune défaite
     */
    public double getRatioVD() {
        if (defaites == 0) {
            return victoires > 0 ? Double.POSITIVE_INFINITY : 0.0;
        }
        return (double) victoires / defaites;
    }

    /**
     * Calcule la moyenne de coups par partie.
     *
     * @return la moyenne, ou 0 si aucune partie jouée
     */
    public double getMoyenneCoupsParPartie() {
        if (partiesJouees == 0) {
            return 0.0;
        }
        return (double) coupsJoues / partiesJouees;
    }

    /**
     * Vérifie si le joueur a un bilan positif (plus de victoires que de défaites).
     * PRINCIPE : Méthode de commodité (convenience method)
     *
     * @return true si victoires > défaites
     */
    public boolean aBilanPositif() {
        return victoires > defaites;
    }

    // ==================== MÉTHODES UTILITAIRES ====================

    /**
     * Retourne une représentation textuelle des statistiques.
     * PRINCIPE : Override de toString() pour un affichage personnalisé
     *
     * @return les statistiques formatées
     */
    @Override
    public String toString() {
        return String.format(
            "Statistiques de %s:\n" +
            "  Parties jouées: %d\n" +
            "  Victoires: %d\n" +
            "  Défaites: %d\n" +
            "  Matchs nuls: %d\n" +
            "  Taux de victoire: %.1f%%\n" +
            "  Coups joués: %d\n" +
            "  Moyenne coups/partie: %.1f",
            nomJoueur, partiesJouees, victoires, defaites, matchsNuls,
            getTauxVictoire(), coupsJoues, getMoyenneCoupsParPartie()
        );
    }

    /**
     * Compare deux objets Statistiques pour l'égalité.
     * PRINCIPE : Override de equals() pour comparaison personnalisée
     *
     * @param obj l'objet à comparer
     * @return true si les statistiques sont identiques
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Statistiques autre = (Statistiques) obj;
        return partiesJouees == autre.partiesJouees &&
               victoires == autre.victoires &&
               defaites == autre.defaites &&
               matchsNuls == autre.matchsNuls &&
               coupsJoues == autre.coupsJoues &&
               nomJoueur.equals(autre.nomJoueur);
    }

    /**
     * Génère un hash code pour cet objet.
     * PRINCIPE : Override de hashCode() (doit être cohérent avec equals())
     *
     * @return le hash code
     */
    @Override
    public int hashCode() {
        int result = nomJoueur.hashCode();
        result = 31 * result + partiesJouees;
        result = 31 * result + victoires;
        result = 31 * result + defaites;
        result = 31 * result + matchsNuls;
        result = 31 * result + coupsJoues;
        return result;
    }

    /**
     * Crée une copie profonde de cet objet.
     * PRINCIPE : Pattern Prototype - clonage d'objet
     *
     * @return une copie des statistiques
     */
    public Statistiques copier() {
        return new Statistiques(this);
    }
}
