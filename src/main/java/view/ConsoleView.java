package view;

import model.IPlateau;

/**
 * Classe responsable de l'affichage dans la console.
 *
 * PRINCIPE POO : Single Responsibility Principle (SRP)
 * Cette classe a UNE seule responsabilité : gérer l'affichage.
 * Elle ne gère pas la logique du jeu, seulement la présentation.
 */
public class ConsoleView {

    // ENCAPSULATION : Attribut privé pour détecter le support Unicode
    private final boolean supporteUnicode;

    /**
     * Constructeur qui détecte automatiquement le support Unicode.
     * PRINCIPE : Adaptation automatique selon l'environnement
     */
    public ConsoleView() {
        this.supporteUnicode = detecterSupportUnicode();
    }

    /**
     * Détecte si le terminal supporte l'Unicode.
     * PRINCIPE : Abstraction de la complexité système
     */
    private boolean detecterSupportUnicode() {
        String os = System.getProperty("os.name").toLowerCase();
        String encoding = System.getProperty("file.encoding");

        // Sur Windows avec CMD classique, l'Unicode peut poser problème
        if (os.contains("windows")) {
            return encoding.toLowerCase().contains("utf");
        }
        // Sur Linux/Mac, généralement OK
        return true;
    }

    /**
     * Affiche le plateau de jeu de manière colorée et stylée.
     * ENCAPSULATION : Méthode publique qui utilise des méthodes privées
     * POLYMORPHISME : Affichage différent selon le support Unicode
     *
     * @param plateau l'interface du plateau à afficher
     */
    public void afficherPlateau(IPlateau plateau) {
        char[][] etat = plateau.getEtatPlateau();

        if (supporteUnicode) {
            afficherPlateauUnicode(etat);
        } else {
            afficherPlateauSimple(etat);
        }
    }

    /**
     * Affichage avec caractères Unicode (moderne).
     */
    private void afficherPlateauUnicode(char[][] etat) {
        System.out.println();
        System.out.println(Couleur.colorer("     0   1   2", Couleur.CYAN));
        System.out.println(Couleur.colorer("   +---+---+---+", Couleur.CYAN));

        for (int i = 0; i < 3; i++) {
            System.out.print(Couleur.colorer(" " + i + " |", Couleur.CYAN));

            for (int j = 0; j < 3; j++) {
                String contenu = formaterCase(etat[i][j]);
                System.out.print(" " + contenu + " ");

                if (j < 2) {
                    System.out.print(Couleur.colorer("|", Couleur.CYAN));
                } else {
                    System.out.print(Couleur.colorer("|", Couleur.CYAN));
                }
            }

            System.out.println();

            if (i < 2) {
                System.out.println(Couleur.colorer("   +---+---+---+", Couleur.CYAN));
            }
        }

        System.out.println(Couleur.colorer("   +---+---+---+", Couleur.CYAN));
        System.out.println();
    }

    /**
     * Affichage simplifié ASCII (compatible partout).
     * PRINCIPE : Fallback pattern - solution de repli
     */
    private void afficherPlateauSimple(char[][] etat) {
        System.out.println();
        System.out.println(Couleur.colorer("     0   1   2", Couleur.CYAN));
        System.out.println(Couleur.colorer("   +-----------+", Couleur.CYAN));

        for (int i = 0; i < 3; i++) {
            System.out.print(Couleur.colorer(" " + i + " |", Couleur.CYAN));

            for (int j = 0; j < 3; j++) {
                String contenu = formaterCase(etat[i][j]);
                System.out.print(" " + contenu + " ");
                if (j < 2) System.out.print("|");
            }

            System.out.println(Couleur.colorer("|", Couleur.CYAN));

            if (i < 2) {
                System.out.println(Couleur.colorer("   +-----------+", Couleur.CYAN));
            }
        }

        System.out.println(Couleur.colorer("   +-----------+", Couleur.CYAN));
        System.out.println();
    }

    /**
     * ENCAPSULATION : Méthode privée pour formater une case.
     * Principe : Hide implementation details (cacher les détails d'implémentation)
     *
     * @param symbole le symbole dans la case
     * @return le symbole formaté et coloré
     */
    private String formaterCase(char symbole) {
        switch (symbole) {
            case 'X':
                return Couleur.colorerX("X");
            case 'O':
                return Couleur.colorerO("O");
            default:
                return " ";
        }
    }

    /**
     * Affiche un message simple.
     *
     * @param message le message à afficher
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    /**
     * Affiche le tour du joueur actuel de manière colorée.
     * COMPOSITION : Utilise la classe Couleur
     *
     * @param nomJoueur le nom du joueur
     * @param pion le symbole du joueur
     */
    public void afficherTour(String nomJoueur, char pion) {
        String symboleColore = pion == 'X' ?
                Couleur.colorerX("" + pion) :
                Couleur.colorerO("" + pion);

        String message = "▶️  C'est au tour de " +
                Couleur.styler(nomJoueur, Couleur.GRAS) +
                " (" + symboleColore + ")";

        System.out.println("\n" + message);
    }

    /**
     * Affiche le message de victoire avec animation.
     *
     * @param nomJoueur le nom du gagnant
     */
    public void afficherVictoire(String nomJoueur) {
        System.out.println();
        afficherLigneEtoiles();
        String message = "🎉  " + nomJoueur.toUpperCase() + " A GAGNÉ !  🎉";
        System.out.println(Couleur.styler(message, Couleur.VERT, Couleur.GRAS));
        afficherLigneEtoiles();
        System.out.println();
    }

    /**
     * Affiche le message de match nul.
     */
    public void afficherMatchNul() {
        System.out.println();
        afficherLigneEtoiles();
        String message = "🤝  MATCH NUL !  🤝";
        System.out.println(Couleur.styler(message, Couleur.JAUNE, Couleur.GRAS));
        afficherLigneEtoiles();
        System.out.println();
    }

    /**
     * Affiche le titre du jeu.
     * MÉTHODE PUBLIQUE : Peut être appelée depuis l'extérieur
     */
    public void afficherTitre() {
        if (supporteUnicode) {
            System.out.println(Couleur.titre("MORPION - TIC TAC TOE"));
        } else {
            // Version simplifiée ASCII
            System.out.println(Couleur.styler("\n==== MORPION - TIC TAC TOE ====\n", Couleur.CYAN, Couleur.GRAS));
        }
    }

    /**
     * Affiche un message d'erreur coloré.
     *
     * @param message le message d'erreur
     */
    public void afficherErreur(String message) {
        System.out.println(Couleur.erreur(message));
    }

    /**
     * Affiche un message d'information coloré.
     *
     * @param message le message d'information
     */
    public void afficherInfo(String message) {
        System.out.println(Couleur.info(message));
    }

    /**
     * ENCAPSULATION : Méthode privée helper pour afficher une ligne d'étoiles.
     * Principe : DRY (Don't Repeat Yourself)
     */
    private void afficherLigneEtoiles() {
        System.out.println(Couleur.colorer("✨ ════════════════════════════════ ✨", Couleur.CYAN));
    }

    /**
     * Efface l'écran (multi-plateforme).
     * ABSTRACTION : Cache la complexité de l'effacement d'écran
     */
    public void effacerEcran() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si l'effacement échoue, on affiche juste des lignes vides
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Affiche un menu avec des options numérotées.
     * COMPOSITION : Utilise plusieurs méthodes de cette classe
     *
     * @param titre le titre du menu
     * @param options les options du menu (varargs)
     */
    public void afficherMenu(String titre, String... options) {
        System.out.println("\n" + Couleur.styler(titre, Couleur.CYAN, Couleur.GRAS));
        for (int i = 0; i < options.length; i++) {
            System.out.println(Couleur.colorer((i + 1) + ". ", Couleur.JAUNE) + options[i]);
        }
        System.out.println();
    }
}