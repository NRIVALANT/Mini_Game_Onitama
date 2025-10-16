package model;

import java.util.*;

/**
 * Factory pour créer toutes les cartes officielles d'Onitama.
 * Le jeu original contient 16 cartes inspirées d'animaux et de concepts martiaux.
 *
 * PRINCIPE POO : Factory Pattern + Singleton
 * - Centralise la création des cartes
 * - Garantit la cohérence des cartes du jeu
 *
 * Chaque partie utilise 5 cartes tirées aléatoirement parmi les 16.
 */
public class DeckCartes {
    private static final List<CarteMouvement> CARTES_OFFICIELLES;

    // PATTERN SINGLETON : Initialisation statique du deck
    static {
        CARTES_OFFICIELLES = creerCartesOfficielles();
    }

    /**
     * Constructeur privé pour empêcher l'instanciation.
     * PRINCIPE : Factory statique
     */
    private DeckCartes() {
    }

    /**
     * Crée toutes les 16 cartes officielles d'Onitama.
     * PRINCIPE : Single Responsibility - méthode dédiée à la création
     *
     * @return liste des 16 cartes officielles
     */
    private static List<CarteMouvement> creerCartesOfficielles() {
        List<CarteMouvement> cartes = new ArrayList<>();

        // 1. TIGER (Tigre) - Puissant en ligne droite
        cartes.add(new CarteMouvement("Tiger", Arrays.asList(
                new Mouvement(-2, 0),  // 2 cases en avant
                new Mouvement(1, 0)    // 1 case en arrière
        ), "Bond puissant du tigre"));

        // 2. DRAGON (Dragon) - Diagonales arrière et avant latéral
        cartes.add(new CarteMouvement("Dragon", Arrays.asList(
                new Mouvement(-1, -2), // Diagonale avant-gauche loin
                new Mouvement(-1, 2),  // Diagonale avant-droite loin
                new Mouvement(1, -1),  // Diagonale arrière-gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Vol majestueux du dragon"));

        // 3. FROG (Grenouille) - Sauts asymétriques
        cartes.add(new CarteMouvement("Frog", Arrays.asList(
                new Mouvement(0, -2),  // 2 cases à gauche
                new Mouvement(-1, -1), // Diagonale avant-gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Bonds agiles de la grenouille"));

        // 4. RABBIT (Lapin) - Sauts asymétriques (miroir de Frog)
        cartes.add(new CarteMouvement("Rabbit", Arrays.asList(
                new Mouvement(0, 2),   // 2 cases à droite
                new Mouvement(-1, 1),  // Diagonale avant-droite
                new Mouvement(1, -1)   // Diagonale arrière-gauche
        ), "Bonds vifs du lapin"));

        // 5. CRAB (Crabe) - Horizontal et avant
        cartes.add(new CarteMouvement("Crab", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(0, -2),  // 2 cases à gauche
                new Mouvement(0, 2)    // 2 cases à droite
        ), "Marche latérale du crabe"));

        // 6. ELEPHANT (Éléphant) - Diagonales avant et côtés
        cartes.add(new CarteMouvement("Elephant", Arrays.asList(
                new Mouvement(-1, -1), // Diagonale avant-gauche
                new Mouvement(-1, 1),  // Diagonale avant-droite
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(0, 1)    // 1 case à droite
        ), "Pas lourds de l'éléphant"));

        // 7. GOOSE (Oie) - Vol en diagonale
        cartes.add(new CarteMouvement("Goose", Arrays.asList(
                new Mouvement(-1, -1), // Diagonale avant-gauche
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(0, 1),   // 1 case à droite
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Vol gracieux de l'oie"));

        // 8. ROOSTER (Coq) - Vol en diagonale (miroir de Goose)
        cartes.add(new CarteMouvement("Rooster", Arrays.asList(
                new Mouvement(-1, 1),  // Diagonale avant-droite
                new Mouvement(0, 1),   // 1 case à droite
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(1, -1)   // Diagonale arrière-gauche
        ), "Pas fiers du coq"));

        // 9. MONKEY (Singe) - Diagonales partout
        cartes.add(new CarteMouvement("Monkey", Arrays.asList(
                new Mouvement(-1, -1), // Diagonale avant-gauche
                new Mouvement(-1, 1),  // Diagonale avant-droite
                new Mouvement(1, -1),  // Diagonale arrière-gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Agilité du singe"));

        // 10. MANTIS (Mante) - Avant et diagonales arrière
        cartes.add(new CarteMouvement("Mantis", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(1, -1),  // Diagonale arrière-gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Attaque de la mante religieuse"));

        // 11. HORSE (Cheval) - Avant, arrière et gauche
        cartes.add(new CarteMouvement("Horse", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(1, 0)    // 1 case en arrière
        ), "Galop du cheval"));

        // 12. OX (Bœuf) - Avant, droite et arrière
        cartes.add(new CarteMouvement("Ox", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(0, 1),   // 1 case à droite
                new Mouvement(1, 0)    // 1 case en arrière
        ), "Force du bœuf"));

        // 13. CRANE (Grue) - Avant et diagonales arrière
        cartes.add(new CarteMouvement("Crane", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(1, -1),  // Diagonale arrière-gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Élégance de la grue"));

        // 14. BOAR (Sanglier) - Avant et côtés
        cartes.add(new CarteMouvement("Boar", Arrays.asList(
                new Mouvement(-1, 0),  // 1 case en avant
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(0, 1)    // 1 case à droite
        ), "Charge du sanglier"));

        // 15. EEL (Anguille) - Asymétrique
        cartes.add(new CarteMouvement("Eel", Arrays.asList(
                new Mouvement(-1, -1), // Diagonale avant-gauche
                new Mouvement(0, 1),   // 1 case à droite
                new Mouvement(1, -1)   // Diagonale arrière-gauche
        ), "Ondulation de l'anguille"));

        // 16. COBRA (Cobra) - Asymétrique (miroir de Eel)
        cartes.add(new CarteMouvement("Cobra", Arrays.asList(
                new Mouvement(-1, 1),  // Diagonale avant-droite
                new Mouvement(0, -1),  // 1 case à gauche
                new Mouvement(1, 1)    // Diagonale arrière-droite
        ), "Morsure du cobra"));

        return Collections.unmodifiableList(cartes);
    }

    /**
     * Retourne toutes les cartes officielles.
     * ENCAPSULATION : Retourne une copie pour préserver l'immuabilité
     *
     * @return liste de toutes les cartes
     */
    public static List<CarteMouvement> getToutesLesCartes() {
        return new ArrayList<>(CARTES_OFFICIELLES);
    }

    /**
     * Tire 5 cartes aléatoires pour une partie.
     * PRINCIPE : Randomization pour la rejouabilité
     *
     * @return liste de 5 cartes aléatoires
     */
    public static List<CarteMouvement> tirerCinqCartes() {
        List<CarteMouvement> cartesDisponibles = new ArrayList<>(CARTES_OFFICIELLES);
        Collections.shuffle(cartesDisponibles);
        return cartesDisponibles.subList(0, 5);
    }

    /**
     * Tire 5 cartes aléatoires avec un générateur de nombres aléatoires spécifique.
     * Utile pour les tests ou pour reproduire une partie.
     *
     * @param random générateur de nombres aléatoires
     * @return liste de 5 cartes aléatoires
     */
    public static List<CarteMouvement> tirerCinqCartes(Random random) {
        List<CarteMouvement> cartesDisponibles = new ArrayList<>(CARTES_OFFICIELLES);
        Collections.shuffle(cartesDisponibles, random);
        return cartesDisponibles.subList(0, 5);
    }

    /**
     * Trouve une carte par son nom.
     * PRINCIPE : Méthode utilitaire de recherche
     *
     * @param nom nom de la carte
     * @return la carte correspondante ou null si non trouvée
     */
    public static CarteMouvement getCarteParNom(String nom) {
        if (nom == null) {
            return null;
        }
        return CARTES_OFFICIELLES.stream()
                .filter(carte -> carte.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retourne le nombre total de cartes officielles.
     *
     * @return nombre de cartes (16)
     */
    public static int getNombreCartes() {
        return CARTES_OFFICIELLES.size();
    }

    /**
     * Affiche toutes les cartes avec leur grille de mouvements.
     * Utile pour le débogage ou l'apprentissage du jeu.
     *
     * @param couleur couleur du joueur (pour l'orientation)
     * @return représentation textuelle de toutes les cartes
     */
    public static String afficherToutesLesCartes(CouleurJoueur couleur) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("   LES 16 CARTES OFFICIELLES D'ONITAMA\n");
        sb.append("═══════════════════════════════════════\n\n");

        for (CarteMouvement carte : CARTES_OFFICIELLES) {
            sb.append(carte.afficherGrille(couleur));
            sb.append("\n");
            if (!carte.getDescription().isEmpty()) {
                sb.append("  ").append(carte.getDescription()).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
