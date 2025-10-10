package model;

/**
 * Énumération représentant les différents états possibles du jeu.
 *
 * CONCEPT POO : ÉNUMÉRATION
 * Une Enum est une classe spéciale qui définit un ensemble fixe de constantes.
 * Elle est type-safe (pas d'erreur de frappe possible) et peut avoir des méthodes.
 */
public enum EtatJeu {
    /**
     * Le jeu est en cours
     */
    EN_COURS("Le jeu continue..."),

    /**
     * Un joueur a gagné la partie
     */
    VICTOIRE("Partie terminée - Victoire!"),

    /**
     * Match nul (plateau plein sans gagnant)
     */
    MATCH_NUL("Partie terminée - Match nul!"),

    /**
     * Le jeu n'a pas encore commencé
     */
    NON_DEMARRE("En attente de démarrage");

    // ENCAPSULATION : attribut privé avec getter public
    private final String description;

    /**
     * Constructeur privé (les Enums ont toujours des constructeurs privés)
     * @param description Description textuelle de l'état
     */
    EtatJeu(String description) {
        this.description = description;
    }

    /**
     * Getter pour obtenir la description de l'état
     * @return la description de l'état
     */
    public String getDescription() {
        return description;
    }

    /**
     * POLYMORPHISME : Méthode qui retourne un comportement différent selon l'état
     * @return true si le jeu est terminé (victoire ou match nul)
     */
    public boolean estTermine() {
        return this == VICTOIRE || this == MATCH_NUL;
    }

    /**
     * MÉTHODE UTILITAIRE : Affiche un message formaté selon l'état
     * @return le message formaté
     */
    public String getMessage() {
        switch (this) {
            case VICTOIRE:
                return "🎉 " + description + " 🎉";
            case MATCH_NUL:
                return "🤝 " + description + " 🤝";
            case EN_COURS:
                return "▶️  " + description;
            case NON_DEMARRE:
                return "⏸️  " + description;
            default:
                return description;
        }
    }
}
