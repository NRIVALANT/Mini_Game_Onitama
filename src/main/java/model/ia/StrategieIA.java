package model.ia;

import model.IPlateau;

/**
 * Interface définissant une stratégie de jeu pour l'IA.
 *
 * PATTERN : STRATEGY
 * Définit une famille d'algorithmes (stratégies de jeu),
 * encapsule chacun, et les rend interchangeables.
 *
 * PRINCIPE POO : Polymorphisme
 * Chaque stratégie concrète implémente cette interface
 * de manière différente.
 */
public interface StrategieIA {

    /**
     * Choisit un coup à jouer selon la stratégie.
     *
     * PRINCIPE : Abstraction
     * Chaque implémentation décide COMMENT choisir,
     * mais toutes retournent un coup au même format.
     *
     * @param plateau l'état actuel du plateau
     * @param pion le symbole de l'IA ('X' ou 'O')
     * @return un tableau [ligne, colonne] représentant le coup
     */
    int[] choisirCoup(IPlateau plateau, char pion);

    /**
     * Retourne le nom de la stratégie.
     *
     * @return le nom de la stratégie (ex: "Facile", "Moyen")
     */
    String getNom();

    /**
     * Retourne une description de la stratégie.
     *
     * @return la description de l'algorithme utilisé
     */
    String getDescription();
}
