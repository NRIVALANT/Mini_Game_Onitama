# 🎮 MiniGames Morpion

Un jeu de morpion (Tic-Tac-Toe) en ligne de commande développé en Java avec une **architecture orientée objet avancée**. Ce projet illustre les principes fondamentaux de la POO et les design patterns les plus utilisés en développement professionnel.

---

## ✨ Fonctionnalités

### Modes de jeu
- 👥 **Joueur vs Joueur** : mode local pour deux joueurs
- 🤖 **Joueur vs IA** : trois niveaux de difficulté
  - **Facile** : coups aléatoires
  - **Moyen** : cherche à gagner et bloque l'adversaire
  - **Difficile** : algorithme Minimax (imbattable !)

### Expérience utilisateur
- 🎨 **Interface colorisée** : affichage avec codes ANSI pour une meilleure lisibilité
- 🖥️ **Cross-platform** : détection automatique Unicode/ASCII selon le terminal
- 📊 **Système de statistiques** : suivi des victoires, défaites et matchs nuls
- ❌ **Validation robuste** : gestion complète des erreurs de saisie
- 🔁 **Rejouabilité** : option pour enchaîner les parties

### Architecture technique
- 🏗️ **Pattern MVC** : séparation claire Model-View-Controller
- 🎯 **Design Patterns** : Strategy, Singleton, Factory Method, Utility Class
- 🧩 **Principes SOLID** : code maintenable et extensible
- 🌐 **WebSocket ready** : infrastructure préparée pour le mode en ligne

---

## 🧠 Concepts POO démontrés

### Principes fondamentaux
| Principe | Implémentation |
|----------|----------------|
| **Encapsulation** | Attributs privés avec getters/setters validés |
| **Héritage** | `AbstractJoueur` → `JoueurHumain`, `JoueurIA` |
| **Polymorphisme** | Interface `IJoueur` avec implémentations multiples |
| **Abstraction** | Interfaces `IPlateau`, `StrategieIA` |
| **Composition** | `JoueurIA` "a une" `StrategieIA` |

### Design Patterns utilisés

#### 🎯 Strategy Pattern
**Contexte** : `JoueurIA.java`
**Stratégies** : `StrategieFacile`, `StrategieMoyenne`, `StrategieDifficile`
**Bénéfice** : Changement d'algorithme à la volée sans modifier le code

```java
JoueurIA ia = new JoueurIA("Ordinateur", 'O', "difficile");
// Changement dynamique de stratégie
ia.setStrategie(new StrategieFacile());
```

#### 🔒 Singleton Pattern
**Implémentation** : `GestionnaireStatistiques.java`
**Technique** : Double-checked locking (thread-safe)
**Usage** : Instance unique centralisée des statistiques

```java
GestionnaireStatistiques gestionnaire = GestionnaireStatistiques.getInstance();
```

#### 🏭 Factory Method Pattern
**Localisation** : `JoueurIA.creerStrategie()`
**Rôle** : Création de la stratégie appropriée selon la difficulté

#### 🛠️ Utility Class Pattern
**Implémentation** : `Couleur.java`
**Caractéristiques** : Classe `final`, constructeur privé, méthodes statiques

### Principes SOLID appliqués

- **S** - Single Responsibility : Chaque classe a une responsabilité unique
- **O** - Open/Closed : Ouvert à l'extension (nouvelles stratégies), fermé à la modification
- **L** - Liskov Substitution : Toutes les implémentations de `IJoueur` sont interchangeables
- **I** - Interface Segregation : Interfaces petites et spécialisées
- **D** - Dependency Inversion : Dépendance aux abstractions, pas aux implémentations

---

## ⚙️ Prérequis

- **Java Development Kit (JDK) 21** ou supérieur
- **Apache Maven 3.6** ou supérieur
- Terminal supportant les codes ANSI (CMD/PowerShell/Unix)

### Vérifier les versions installées

```bash
java -version    # Doit afficher 21 ou supérieur
mvn -version     # Doit afficher 3.6 ou supérieur
```

---

## 🚀 Installation

### Cloner le dépôt

```bash
git clone https://github.com/NRIVALANT/MiniGamesMorpion.git
cd MiniGamesMorpion
```

### Compiler le projet

```bash
mvn clean compile
```

### Créer le JAR exécutable

```bash
mvn clean package
```

Le JAR sera généré dans `target/MiniGamesMorpion-1.0-SNAPSHOT.jar`

---

## ▶️ Utilisation

### Lancement rapide

```bash
# Option 1 : Via JAR (recommandé)
java -jar target/MiniGamesMorpion-1.0-SNAPSHOT.jar

# Option 2 : Via Maven
mvn exec:java
```

### Déroulement d'une partie

1. **Sélection du mode** : Joueur vs Joueur ou Joueur vs IA
2. **Configuration** : Noms des joueurs et symboles (X/O)
3. **Choix de difficulté** (si IA) : facile, moyen ou difficile
4. **Partie** : Jouez en entrant les coordonnées (ligne, colonne)
5. **Résultat** : Affichage des statistiques et option pour rejouer

### Exemple de saisie

```
Entrez la ligne (0-2) : 1
Entrez la colonne (0-2) : 1
```

---

## 📁 Structure du projet

```text
MiniGamesMorpion/
├── src/main/java/
│   ├── Main.java                                  # Point d'entrée + config UTF-8
│   ├── controller/
│   │   └── JeuController.java                     # Orchestration du jeu (MVC Controller)
│   ├── model/                                     # Logique métier (MVC Model)
│   │   ├── AbstractJoueur.java                    # Classe abstraite de base
│   │   ├── EtatJeu.java                           # Enum : états du jeu
│   │   ├── GestionnaireStatistiques.java          # Singleton thread-safe
│   │   ├── IJoueur.java                           # Interface joueur
│   │   ├── IPlateau.java                          # Interface plateau
│   │   ├── JoueurHumain.java                      # Joueur contrôlé par utilisateur
│   │   ├── JoueurIA.java                          # Contexte Strategy Pattern
│   │   ├── Plateau.java                           # État du plateau 3x3
│   │   ├── Statistiques.java                      # Stats avec encapsulation avancée
│   │   ├── Symbole.java                           # Enum : X, O, VIDE
│   │   └── ia/                                    # Package IA (Strategy Pattern)
│   │       ├── StrategieIA.java                   # Interface Strategy
│   │       ├── StrategieFacile.java               # Algorithme aléatoire
│   │       ├── StrategieMoyenne.java              # Algorithme tactique
│   │       └── StrategieDifficile.java            # Algorithme Minimax
│   └── view/                                      # Affichage (MVC View)
│       ├── ConsoleView.java                       # Vue avec couleurs + Unicode
│       └── Couleur.java                           # Utility class pour ANSI
├── target/                                        # Build Maven
├── pom.xml                                        # Configuration Maven
└── README.md                                      # Documentation
```

---

## 🎨 Architecture détaillée

### Pattern MVC

```
┌─────────────┐         ┌──────────────┐         ┌─────────────┐
│    VIEW     │────────▶│  CONTROLLER  │────────▶│    MODEL    │
│ ConsoleView │         │JeuController │         │Plateau/IA   │
│  Couleur    │◀────────│              │◀────────│Statistiques │
└─────────────┘         └──────────────┘         └─────────────┘
```

### Hiérarchie des classes

```
IJoueur (interface)
    ├── AbstractJoueur (abstract)
    │   ├── JoueurHumain
    │   └── JoueurIA ──────┐
    │                      │
    │                      ▼
    └── StrategieIA (interface)
            ├── StrategieFacile
            ├── StrategieMoyenne
            └── StrategieDifficile (Minimax)
```

---

## 🧪 Algorithmes implémentés

### Minimax (StrategieDifficile)

Algorithme récursif de théorie des jeux qui explore toutes les possibilités pour trouver le coup optimal. L'IA devient **mathématiquement imbattable**.

**Complexité** : O(3^9) dans le pire cas
**Optimisation** : Élagage alpha-bêta (à venir)

### Heuristique tactique (StrategieMoyenne)

1. Cherche un coup **gagnant**
2. Sinon, **bloque** l'adversaire s'il peut gagner
3. Sinon, joue un coup **aléatoire**

---

## 🛠️ Configuration Maven

### Dépendances

```xml
<dependency>
    <groupId>org.java-websocket</groupId>
    <artifactId>Java-WebSocket</artifactId>
    <version>1.5.7</version>
</dependency>
```

### Plugins

- **exec-maven-plugin** (3.5.0) : exécution directe
- **maven-jar-plugin** (3.4.2) : création du JAR
- **maven-shade-plugin** (3.6.1) : uber-JAR avec dépendances

### Commandes utiles

```bash
mvn clean              # Nettoie target/
mvn compile            # Compile les sources
mvn test               # Lance les tests (à venir)
mvn package            # Crée le JAR
mvn exec:java          # Exécute directement
```

---

## 🎯 Théorie des jeux : Le cas du Morpion

Le Tic-Tac-Toe est un jeu **mathématiquement résolu** : avec une stratégie parfaite des deux côtés, le résultat est **toujours un match nul**.

**Preuve** : L'algorithme Minimax explore tous les 255,168 états possibles et démontre qu'aucun joueur ne peut forcer une victoire si l'adversaire joue de manière optimale.

Cela explique pourquoi jouer en mode "Difficile" contre l'IA aboutit systématiquement à un match nul si vous jouez parfaitement.

---

## 🌟 Roadmap future

### Phase 1 : Jeux de stratégie plus complexes
- [ ] Migration vers **Pentago** (plateau rotatif)
- [ ] Ou **Onitama** (jeu de cartes abstrait)
- [ ] Ou **Hive** (jeu de tuiles sans plateau)

### Phase 2 : Interface graphique
- [ ] **Qt (Python/C++)** ou **JavaFX**
- [ ] Animations et effets visuels
- [ ] Thèmes clairs/sombres

### Phase 3 : Multijoueur en ligne
- [ ] **WebSocket** pour communication temps réel
- [ ] Backend **serverless** (AWS Lambda, Firebase)
- [ ] Système de matchmaking
- [ ] Classement ELO

### Phase 4 : Déploiement
- [ ] Site web vitrine
- [ ] Distribution sur stores
- [ ] API REST pour statistiques globales

---

## 🤝 Contribuer

Les contributions sont les bienvenues ! Pour contribuer :

1. **Fork** le projet
2. Créez une **branche** (`git checkout -b feature/AmazingFeature`)
3. **Committez** vos changements (`git commit -m 'Add AmazingFeature'`)
4. **Push** vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une **Pull Request**

### Guidelines

- Respectez les principes SOLID
- Documentez les nouvelles classes (Javadoc)
- Ajoutez des tests unitaires (JUnit 5)
- Suivez le style de code existant

---

## 📄 Licence

Ce projet est sous licence **MIT** - voir le fichier [LICENSE](LICENSE) pour plus de détails.

Vous êtes libre de l'utiliser, le modifier et le redistribuer, même à des fins commerciales.

---

## 👨‍💻 Auteur

**NRIVALANT**

- GitHub : [@NRIVALANT](https://github.com/NRIVALANT)
- Projet : [MiniGamesMorpion](https://github.com/NRIVALANT/MiniGamesMorpion)

---

## 🙏 Remerciements

- Communauté Java pour les ressources POO
- Algorithme Minimax inspiré de la théorie des jeux
- Pattern Strategy d'après le livre "Design Patterns" (Gang of Four)

---

**Développé avec ☕ et passion pour la programmation orientée objet**
