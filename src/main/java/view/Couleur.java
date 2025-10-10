package view;

/**
 * Classe utilitaire pour gérer les couleurs ANSI dans la console.
 *
 * CONCEPT POO : UTILITY CLASS (Classe Utilitaire)
 * - Classe finale (final) : ne peut pas être héritée
 * - Constructeur privé : ne peut pas être instanciée
 * - Méthodes statiques uniquement : accessibles sans créer d'objet
 * - Constantes statiques finales : valeurs immuables partagées
 *
 * Pattern de conception : UTILITY CLASS
 */
public final class Couleur {

    // ENCAPSULATION : Constructeur privé empêche l'instanciation
    private Couleur() {
        throw new AssertionError("Cette classe ne peut pas être instanciée");
    }

    // ==================== CODES ANSI ====================
    // ENCAPSULATION : constantes publiques, static et final (immuables)

    // Couleurs de texte
    public static final String RESET = "\u001B[0m";
    public static final String NOIR = "\u001B[30m";
    public static final String ROUGE = "\u001B[31m";
    public static final String VERT = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANC = "\u001B[37m";

    // Couleurs de fond
    public static final String FOND_NOIR = "\u001B[40m";
    public static final String FOND_ROUGE = "\u001B[41m";
    public static final String FOND_VERT = "\u001B[42m";
    public static final String FOND_JAUNE = "\u001B[43m";
    public static final String FOND_BLEU = "\u001B[44m";
    public static final String FOND_MAGENTA = "\u001B[45m";
    public static final String FOND_CYAN = "\u001B[46m";
    public static final String FOND_BLANC = "\u001B[47m";

    // Styles de texte
    public static final String GRAS = "\u001B[1m";
    public static final String SOULIGNE = "\u001B[4m";
    public static final String INVERSE = "\u001B[7m";

    // ==================== MÉTHODES STATIQUES ====================

    /**
     * Colore un texte avec la couleur spécifiée.
     * PRINCIPE : Single Responsibility - une méthode fait une seule chose
     *
     * @param texte le texte à colorer
     * @param couleur la couleur ANSI à appliquer
     * @return le texte coloré avec reset automatique
     */
    public static String colorer(String texte, String couleur) {
        return couleur + texte + RESET;
    }

    /**
     * Applique plusieurs styles à un texte.
     * PRINCIPE : Composition de fonctionnalités
     *
     * @param texte le texte à styler
     * @param styles les styles à appliquer (varargs)
     * @return le texte stylé
     */
    public static String styler(String texte, String... styles) {
        StringBuilder sb = new StringBuilder();
        for (String style : styles) {
            sb.append(style);
        }
        sb.append(texte).append(RESET);
        return sb.toString();
    }

    /**
     * Crée une ligne de séparation colorée.
     *
     * @param longueur la longueur de la ligne
     * @param couleur la couleur de la ligne
     * @return la ligne colorée
     */
    public static String ligne(int longueur, String couleur) {
        return colorer("─".repeat(longueur), couleur);
    }

    /**
     * Affiche un message d'erreur en rouge.
     *
     * @param message le message d'erreur
     * @return le message formaté en rouge
     */
    public static String erreur(String message) {
        return styler("❌ " + message, ROUGE, GRAS);
    }

    /**
     * Affiche un message de succès en vert.
     *
     * @param message le message de succès
     * @return le message formaté en vert
     */
    public static String succes(String message) {
        return styler("✅ " + message, VERT, GRAS);
    }

    /**
     * Affiche un message d'information en bleu.
     *
     * @param message le message d'information
     * @return le message formaté en bleu
     */
    public static String info(String message) {
        return styler("ℹ️  " + message, CYAN);
    }

    /**
     * Affiche un avertissement en jaune.
     *
     * @param message le message d'avertissement
     * @return le message formaté en jaune
     */
    public static String avertissement(String message) {
        return styler("⚠️  " + message, JAUNE, GRAS);
    }

    /**
     * Colore le symbole X en rouge.
     *
     * @param symbole le symbole à colorer
     * @return le symbole coloré
     */
    public static String colorerX(String symbole) {
        return styler(symbole, ROUGE, GRAS);
    }

    /**
     * Colore le symbole O en bleu.
     *
     * @param symbole le symbole à colorer
     * @return le symbole coloré
     */
    public static String colorerO(String symbole) {
        return styler(symbole, BLEU, GRAS);
    }

    /**
     * Colore un symbole selon son type (X ou O).
     * POLYMORPHISME : Comportement différent selon le paramètre
     *
     * @param symbole le caractère à colorer
     * @return le symbole coloré approprié
     */
    public static String colorerSymbole(char symbole) {
        switch (symbole) {
            case 'X':
                return colorerX("X");
            case 'O':
                return colorerO("O");
            default:
                return " ";
        }
    }

    /**
     * Crée un titre encadré et coloré.
     *
     * @param titre le texte du titre
     * @return le titre formaté
     */
    public static String titre(String titre) {
        int longueur = titre.length() + 4;
        String ligne = "═".repeat(longueur);

        return styler("\n╔" + ligne + "╗\n", CYAN, GRAS) +
               styler("║  " + titre + "  ║\n", CYAN, GRAS) +
               styler("╚" + ligne + "╝\n", CYAN, GRAS);
    }
}
