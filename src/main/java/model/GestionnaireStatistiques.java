package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Gestionnaire centralisé des statistiques de tous les joueurs.
 *
 * PATTERN : SINGLETON
 * Une seule instance de cette classe existe dans toute l'application.
 * Permet de centraliser la gestion des statistiques.
 *
 * CONCEPTS POO :
 * - Singleton Pattern (instance unique)
 * - Encapsulation (données privées)
 * - Collections (HashMap pour stocker les stats)
 * - Lazy Initialization (création à la demande)
 */
public class GestionnaireStatistiques {

    // ==================== SINGLETON ====================
    // PRINCIPE : Une seule instance dans toute l'application

    /**
     * L'unique instance du gestionnaire (volatile pour thread-safety).
     */
    private static volatile GestionnaireStatistiques instance;

    /**
     * Obtient l'instance unique du gestionnaire.
     * PATTERN : Singleton avec Double-Checked Locking
     *
     * @return l'instance unique
     */
    public static GestionnaireStatistiques getInstance() {
        // Premier check (sans synchronisation pour performance)
        if (instance == null) {
            // Synchronisation uniquement si nécessaire
            synchronized (GestionnaireStatistiques.class) {
                // Second check (dans le bloc synchronisé)
                if (instance == null) {
                    instance = new GestionnaireStatistiques();
                }
            }
        }
        return instance;
    }

    /**
     * Constructeur privé - empêche l'instanciation directe.
     * PRINCIPE : Seule la classe elle-même peut créer une instance
     */
    private GestionnaireStatistiques() {
        this.statistiquesJoueurs = new HashMap<>();
    }

    // ==================== ATTRIBUTS ====================

    /**
     * Map stockant les statistiques par nom de joueur.
     * ENCAPSULATION : Private + final (la référence ne change pas)
     * COLLECTION : HashMap pour un accès rapide O(1)
     */
    private final Map<String, Statistiques> statistiquesJoueurs;

    // ==================== MÉTHODES PUBLIQUES ====================

    /**
     * Obtient les statistiques d'un joueur.
     * PRINCIPE : Lazy creation - crée les stats si elles n'existent pas
     *
     * @param nomJoueur le nom du joueur
     * @return les statistiques du joueur
     */
    public Statistiques getStatistiques(String nomJoueur) {
        // Crée les stats seulement si elles n'existent pas déjà
        return statistiquesJoueurs.computeIfAbsent(
            nomJoueur,
            nom -> new Statistiques(nom)
        );
    }

    /**
     * Enregistre une victoire pour un joueur.
     * PRINCIPE : Méthode de commodité (facade pattern)
     *
     * @param nomJoueur le nom du joueur
     */
    public void enregistrerVictoire(String nomJoueur) {
        getStatistiques(nomJoueur).enregistrerVictoire();
    }

    /**
     * Enregistre une défaite pour un joueur.
     *
     * @param nomJoueur le nom du joueur
     */
    public void enregistrerDefaite(String nomJoueur) {
        getStatistiques(nomJoueur).enregistrerDefaite();
    }

    /**
     * Enregistre un match nul pour un joueur.
     *
     * @param nomJoueur le nom du joueur
     */
    public void enregistrerMatchNul(String nomJoueur) {
        getStatistiques(nomJoueur).enregistrerMatchNul();
    }

    /**
     * Ajoute des coups joués pour un joueur.
     *
     * @param nomJoueur le nom du joueur
     * @param nombreCoups le nombre de coups
     */
    public void ajouterCoups(String nomJoueur, int nombreCoups) {
        getStatistiques(nomJoueur).ajouterCoups(nombreCoups);
    }

    /**
     * Vérifie si un joueur a des statistiques enregistrées.
     *
     * @param nomJoueur le nom du joueur
     * @return true si le joueur a des stats
     */
    public boolean joueurExiste(String nomJoueur) {
        return statistiquesJoueurs.containsKey(nomJoueur);
    }

    /**
     * Supprime les statistiques d'un joueur.
     *
     * @param nomJoueur le nom du joueur
     * @return true si le joueur existait et a été supprimé
     */
    public boolean supprimerJoueur(String nomJoueur) {
        return statistiquesJoueurs.remove(nomJoueur) != null;
    }

    /**
     * Réinitialise les statistiques d'un joueur.
     *
     * @param nomJoueur le nom du joueur
     */
    public void reinitialiserJoueur(String nomJoueur) {
        if (joueurExiste(nomJoueur)) {
            getStatistiques(nomJoueur).reinitialiser();
        }
    }

    /**
     * Réinitialise toutes les statistiques de tous les joueurs.
     */
    public void reinitialiserTout() {
        statistiquesJoueurs.clear();
    }

    /**
     * Obtient toutes les statistiques.
     * PRINCIPE : Retourne une copie pour protéger les données internes
     *
     * @return une collection de toutes les statistiques
     */
    public Collection<Statistiques> getToutesLesStatistiques() {
        return statistiquesJoueurs.values();
    }

    /**
     * Obtient le nombre de joueurs enregistrés.
     *
     * @return le nombre de joueurs
     */
    public int getNombreJoueurs() {
        return statistiquesJoueurs.size();
    }

    /**
     * Trouve le joueur avec le plus de victoires.
     * PRINCIPE : Algorithme de recherche du maximum
     *
     * @return les statistiques du meilleur joueur, ou null si aucun joueur
     */
    public Statistiques getMeilleurJoueur() {
        return statistiquesJoueurs.values().stream()
            .max((s1, s2) -> Integer.compare(s1.getVictoires(), s2.getVictoires()))
            .orElse(null);
    }

    /**
     * Trouve le joueur avec le meilleur taux de victoire.
     *
     * @return les statistiques du joueur avec le meilleur taux, ou null
     */
    public Statistiques getJoueurMeilleurTaux() {
        return statistiquesJoueurs.values().stream()
            .filter(s -> s.getPartiesJouees() > 0)
            .max((s1, s2) -> Double.compare(s1.getTauxVictoire(), s2.getTauxVictoire()))
            .orElse(null);
    }

    /**
     * Affiche un classement des joueurs par victoires.
     * PRINCIPE : Tri et formatage des données
     *
     * @return le classement formaté
     */
    public String getClassement() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== CLASSEMENT DES JOUEURS ===\n\n");

        statistiquesJoueurs.values().stream()
            .sorted((s1, s2) -> Integer.compare(s2.getVictoires(), s1.getVictoires()))
            .forEach(stats -> {
                sb.append(String.format("%-20s | V:%3d  D:%3d  N:%3d | Taux: %.1f%%\n",
                    stats.getNomJoueur(),
                    stats.getVictoires(),
                    stats.getDefaites(),
                    stats.getMatchsNuls(),
                    stats.getTauxVictoire()
                ));
            });

        if (statistiquesJoueurs.isEmpty()) {
            sb.append("Aucun joueur enregistré.\n");
        }

        return sb.toString();
    }

    /**
     * Retourne une représentation textuelle du gestionnaire.
     *
     * @return les informations du gestionnaire
     */
    @Override
    public String toString() {
        return String.format("GestionnaireStatistiques [%d joueurs enregistrés]",
            getNombreJoueurs());
    }
}