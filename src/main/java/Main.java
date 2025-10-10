import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import controller.JeuController;

public class Main {
    public static void main(String[] args) {
        // CONCEPT POO : Configuration de l'environnement
        // Configure l'encodage UTF-8 pour supporter les caractères spéciaux
        configureEncodage();

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        try {
            JeuController jeu = new JeuController(scanner);
            jeu.jouer();
            System.out.println("Merci d'avoir joué !");
        } finally {
            scanner.close();
        }
    }

    /**
     * Configure l'encodage UTF-8 pour la console Windows.
     * PRINCIPE : Abstraction - Cache la complexité de configuration
     */
    private static void configureEncodage() {
        try {
            // Configure System.out pour utiliser UTF-8
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

            // Sur Windows, active le support UTF-8 dans la console
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                // Change le code page de la console vers UTF-8
                new ProcessBuilder("cmd", "/c", "chcp", "65001").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Si ça échoue, on continue avec l'encodage par défaut
            System.err.println("Avertissement : Configuration UTF-8 impossible, affichage simplifié");
        }
    }
}
