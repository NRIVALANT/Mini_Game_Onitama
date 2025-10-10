package model;

import model.ia.*;

/**
 * Joueur contrôlé par l'IA.
 *
 * PATTERN STRATEGY : Context
 * Cette classe utilise une stratégie (StrategieIA) pour décider ses coups.
 * La stratégie peut être changée dynamiquement !
 *
 * PRINCIPE : Composition over Inheritance
 * Au lieu d'hériter de comportements, on compose avec une stratégie.
 */
public class JoueurIA extends AbstractJoueur {

    // COMPOSITION : L'IA "a une" stratégie (et non "est une" stratégie)
    private StrategieIA strategie;

    /**
     * Constructeur avec niveau de difficulté.
     * PRINCIPE : Factory Method pattern implicite
     *
     * @param nom le nom de l'IA
     * @param pion le symbole de l'IA
     * @param difficulte le niveau ("facile", "moyen", "difficile")
     */
    public JoueurIA(String nom, char pion, String difficulte) {
        super(nom, pion);
        // PATTERN STRATEGY : Choix de la stratégie selon la difficulté
        this.strategie = creerStrategie(difficulte);
    }

    /**
     * Constructeur avec stratégie directe.
     * PRINCIPE : Injection de dépendance
     *
     * @param nom le nom de l'IA
     * @param pion le symbole de l'IA
     * @param strategie la stratégie à utiliser
     */
    public JoueurIA(String nom, char pion, StrategieIA strategie) {
        super(nom, pion);
        this.strategie = strategie;
    }

    /**
     * FACTORY METHOD : Crée la stratégie appropriée.
     * PRINCIPE : Encapsulation de la logique de création
     *
     * @param difficulte le niveau de difficulté
     * @return la stratégie correspondante
     */
    private StrategieIA creerStrategie(String difficulte) {
        switch (difficulte.toLowerCase()) {
            case "moyen":
                return new StrategieMoyenne();
            case "difficile":
                return new StrategieDifficile();
            case "facile":
            default:
                return new StrategieFacile();
        }
    }

    /**
     * POLYMORPHISME : Implémentation de jouerTour.
     * PATTERN STRATEGY : Délègue le choix du coup à la stratégie.
     *
     * @param plateau l'état du plateau
     * @return le coup choisi par la stratégie
     */
    @Override
    public int[] jouerTour(IPlateau plateau) {
        // Affiche que l'IA réfléchit
        System.out.println(getNom() + " (" + strategie.getNom() + ") réfléchit...");

        // Simulation de réflexion (UX)
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // DÉLÉGATION : La stratégie décide du coup
        return strategie.choisirCoup(plateau, getPion());
    }

    /**
     * Change la stratégie dynamiquement.
     * PRINCIPE : Le Pattern Strategy permet de changer d'algorithme à la volée
     *
     * @param nouvelleStrategie la nouvelle stratégie à utiliser
     */
    public void setStrategie(StrategieIA nouvelleStrategie) {
        this.strategie = nouvelleStrategie;
    }

    /**
     * Obtient la stratégie actuelle.
     *
     * @return la stratégie utilisée
     */
    public StrategieIA getStrategie() {
        return strategie;
    }

    /**
     * Retourne une description du joueur IA.
     *
     * @return la description
     */
    @Override
    public String toString() {
        return String.format("%s (IA - %s)", getNom(), strategie.getNom());
    }
}
