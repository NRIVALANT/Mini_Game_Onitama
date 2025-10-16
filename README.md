# 🎮 Mini Game Onitama

Projet de jeu de stratégie développé en Java avec une architecture orientée objet. Migration en cours de Tic-Tac-Toe vers Onitama.

---

## 🎯 À propos

Onitama est un jeu de stratégie abstrait pour 2 joueurs inspiré des arts martiaux. Chaque joueur contrôle 5 pions (1 Maître et 4 Élèves) et utilise des cartes de mouvement pour se déplacer sur un plateau 5x5.

### Règles du jeu
- **Objectif** : Capturer le Maître adverse OU amener son Maître sur le temple adverse
- **Cartes** : 5 cartes par partie (2 par joueur + 1 neutre), tirées parmi 16 cartes officielles
- **Tour** : Choisir un pion, une carte, puis se déplacer selon la carte
- **Échange** : Après chaque coup, la carte utilisée devient la carte neutre

---

## ⚙️ Prérequis

- **Java 21** ou supérieur
- **Maven 3.6** ou supérieur

```bash
java -version
mvn -version
```

---

## 🚀 Installation & Utilisation

```bash
# Compiler
mvn clean compile

# Créer le JAR
mvn clean package

# Lancer le jeu
java -jar target/MiniGamesMorpion-1.0-SNAPSHOT.jar
# ou
mvn exec:java
```

---

## 🏗️ Architecture

**Pattern MVC** avec principes SOLID :
- **Model** : Logique métier (Plateau, Pion, CarteMouvement, IA)
- **View** : Affichage console avec couleurs ANSI
- **Controller** : Orchestration du jeu

**Design Patterns** :
- Strategy (IA avec différents niveaux)
- Singleton (Gestionnaire de statistiques)
- Factory (Création des cartes officielles)
- Value Object (Position, Mouvement)

---

## 📋 Migration vers Onitama - Prochaines étapes

### ✅ Phase 1 : Classes du modèle (TERMINÉE)
- [x] `Position.java` - Coordonnées 5x5 avec notation algébrique
- [x] `CouleurJoueur.java` - Enum ROUGE/BLEU
- [x] `TypePion.java` - Enum MAITRE/ELEVE
- [x] `Pion.java` - Pièce de jeu avec type, couleur, position
- [x] `Mouvement.java` - Déplacement relatif avec inversion pour perspective
- [x] `CarteMouvement.java` - Carte avec 2-5 mouvements
- [x] `DeckCartes.java` - Factory des 16 cartes officielles

### 🔄 Phase 2 : Adapter le Plateau (EN COURS)
- [ ] Modifier `Plateau.java` pour plateau 5x5
- [ ] Ajouter gestion des pions (liste, placement initial)
- [ ] Implémenter détection des temples
- [ ] Créer `getMovementsPossibles(pion, carte)`
- [ ] Adapter conditions de victoire (capture Maître OU temple)
- [ ] Implémenter logique de capture

### 📝 Phase 3 : Système de cartes
- [ ] Créer `GestionnaireCartes.java` pour distribution
- [ ] Implémenter échange carte jouée ↔ carte neutre
- [ ] Ajouter validation des coups selon la carte

### 🎮 Phase 4 : Controller et View
- [ ] Adapter `JeuController.java` pour gérer les cartes
- [ ] Modifier tour de jeu : sélectionner pion → carte → destination
- [ ] Adapter `ConsoleView.java` pour afficher plateau 5x5
- [ ] Afficher cartes du joueur avec grilles de mouvements
- [ ] Améliorer affichage des pions (Maître vs Élève)

### 🤖 Phase 5 : Intelligence Artificielle
- [ ] Créer `StrategieOnitamaFacile` (coups aléatoires valides)
- [ ] Créer `StrategieOnitamaMoyenne` (heuristique tactique)
- [ ] Créer `StrategieOnitamaDifficile` (Minimax adapté)
- [ ] Adapter évaluation de position (contrôle centre, menace temple, etc.)

### ✅ Phase 6 : Tests et polish
- [ ] Tester tous les scénarios de victoire
- [ ] Valider l'échange de cartes
- [ ] Vérifier l'inversion des mouvements pour joueur BLEU
- [ ] Améliorer l'UX (aide, règles, visualisation)

---

## 📁 Structure actuelle

```
src/main/java/
├── Main.java                          # Point d'entrée
├── controller/
│   └── JeuController.java             # Orchestration
├── model/                             # Logique métier
│   ├── Position.java                  # ✅ Nouveau (Onitama)
│   ├── CouleurJoueur.java             # ✅ Nouveau (Onitama)
│   ├── TypePion.java                  # ✅ Nouveau (Onitama)
│   ├── Pion.java                      # ✅ Nouveau (Onitama)
│   ├── Mouvement.java                 # ✅ Nouveau (Onitama)
│   ├── CarteMouvement.java            # ✅ Nouveau (Onitama)
│   ├── DeckCartes.java                # ✅ Nouveau (16 cartes officielles)
│   ├── Plateau.java                   # 🔄 À adapter pour 5x5
│   ├── IJoueur.java                   # ♻️ Réutilisable
│   ├── AbstractJoueur.java            # ♻️ Réutilisable
│   ├── JoueurHumain.java              # 🔄 À adapter
│   ├── JoueurIA.java                  # 🔄 À adapter
│   ├── GestionnaireStatistiques.java  # ♻️ Réutilisable
│   ├── Statistiques.java              # ♻️ Réutilisable
│   └── ia/
│       ├── StrategieIA.java           # ♻️ Réutilisable
│       ├── StrategieFacile.java       # 🔄 À adapter
│       ├── StrategieMoyenne.java      # 🔄 À adapter
│       └── StrategieDifficile.java    # 🔄 À adapter
└── view/
    ├── ConsoleView.java               # 🔄 À adapter pour 5x5
    └── Couleur.java                   # ♻️ Réutilisable
```

**Légende** : ✅ Nouveau | 🔄 À adapter | ♻️ Réutilisable

---

## 👨‍💻 Auteur

**NRIVALANT**
- GitHub : [@NRIVALANT](https://github.com/NRIVALANT)

---

## 📄 Licence

Projet sous licence MIT.