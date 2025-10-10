package controller;

import java.util.Scanner;

import model.*;
import view.ConsoleView;

public class JeuController {
    private IPlateau plateau;
    private IJoueur joueur1;
    private IJoueur joueur2;
    private IJoueur joueurActuel;
    private ConsoleView vue;
    private boolean jeuTermine;
    private Scanner scanner;
    // COMPOSITION : Utilise le Singleton GestionnaireStatistiques
    private GestionnaireStatistiques gestStats;

    public JeuController(Scanner scanner) {
        this.scanner = scanner;
        this.plateau = new Plateau();
        this.vue = new ConsoleView();
        this.jeuTermine = false;
        // PATTERN SINGLETON : Récupère l'instance unique
        this.gestStats = GestionnaireStatistiques.getInstance();
    }

    public void initialiserJeu() {
        vue.afficherTitre();

        creerJoueurs();

        joueurActuel = joueur1;
        plateau.initialiserPlateau();
    }

    private void creerJoueurs() {
        vue.afficherMenu("Mode de jeu", "Joueur vs Joueur", "Joueur vs IA");

        int choix = 0;
        boolean choixValide = false;

        while(!choixValide) {
            try {
                System.out.print("Votre choix: ");
                choix = Integer.parseInt(scanner.nextLine());
                if (choix == 1 || choix == 2) {
                    choixValide = true;
                } else {
                    vue.afficherErreur("Choix invalide. Veuillez entrer 1 ou 2.");
                }
            } catch (NumberFormatException e) {
                vue.afficherErreur("Veuillez entrer un nombre valide.");
            }
        }

        System.out.print("Nom du joueur 1(X): ");
        String nom1 = scanner.nextLine();
        joueur1 = new JoueurHumain(nom1.isEmpty() ? "Joueur 1" : nom1, 'X', scanner);
        
        if (choix == 1) { 
            System.out.print("Nom du joueur 2 (O): ");
            String nom2 = scanner.nextLine();
            joueur2 = new JoueurHumain(nom2.isEmpty() ? "Joueur 2" : nom2, 'O', scanner);
        } else {
            vue.afficherMenu("Niveau de difficulté de l'IA", "Facile", "Moyen", "Difficile");

            int niveauIA = 0;
            choixValide = false;

            while(!choixValide) {
                try {
                    System.out.print("Votre choix: ");
                    niveauIA = Integer.parseInt(scanner.nextLine());
                    if (niveauIA >= 1 && niveauIA <= 3) {
                        choixValide = true;
                    } else {
                        vue.afficherErreur("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                    }
                } catch (NumberFormatException e) {
                    vue.afficherErreur("Veuillez entrer un nombre valide.");
                }
            }

            String difficulte;
            switch (niveauIA) {
                case 1:
                    difficulte = "facile";
                    break;
                case 2:
                    difficulte = "moyen";
                    break;
                case 3:
                    difficulte = "difficile";
                    break;
                default:
                    difficulte = "facile"; // Valeur par défaut
            }
            joueur2 = new JoueurIA("IA", 'O', difficulte);
        }
    }

    public void jouer() {
        initialiserJeu();

        while (!jeuTermine) {
            tour();
        }

        vue.afficherMessage("Voulez-vous rejouer ? (O/N)");
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("o")) {
            plateau.initialiserPlateau();
            jeuTermine = false;
            jouer();
        }
    }

    private void tour() { 
        vue.afficherPlateau(plateau);
        vue.afficherTour(joueurActuel.getNom(), joueurActuel.getPion());

        boolean coupValide = false;
        while(!coupValide) {
            int[] coup = joueurActuel.jouerTour(plateau);
            coupValide = plateau.placerPion(coup[0], coup[1], joueurActuel.getPion());
        }

        if (plateau.verifierVictoire(joueurActuel.getPion())) {
            vue.afficherPlateau(plateau);
            vue.afficherVictoire(joueurActuel.getNom());

            // ENCAPSULATION : Enregistre les statistiques via le gestionnaire
            gestStats.enregistrerVictoire(joueurActuel.getNom());
            // L'adversaire perd
            IJoueur adversaire = (joueurActuel == joueur1) ? joueur2 : joueur1;
            gestStats.enregistrerDefaite(adversaire.getNom());

            // Affiche les stats du gagnant
            afficherStatistiquesJoueur(joueurActuel.getNom());

            jeuTermine = true;
        } else if (plateau.estPlein()) {
            vue.afficherPlateau(plateau);
            vue.afficherMatchNul();

            // ENCAPSULATION : Enregistre le match nul pour les deux joueurs
            gestStats.enregistrerMatchNul(joueur1.getNom());
            gestStats.enregistrerMatchNul(joueur2.getNom());

            jeuTermine = true;
        } else {
            joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        }
    }

    /**
     * Affiche les statistiques d'un joueur.
     * PRINCIPE : Méthode privée helper (encapsulation)
     *
     * @param nomJoueur le nom du joueur
     */
    private void afficherStatistiquesJoueur(String nomJoueur) {
        Statistiques stats = gestStats.getStatistiques(nomJoueur);
        vue.afficherInfo("\n" + stats.toString());
    }
}
