# UniExo, Exercice technique UNICO

Application Android (Kotlin + Jetpack Compose) affichant les contenants de déchets sur une carte, avec une fiche de détail au clic sur chaque marqueur.

## Prérequis

- Android Studio (dernière version stable)
- Un compte Google Cloud avec une clé API Google Maps

## Installation

### 1. Cloner le projet

```bash
git clone git@github.com:gab-hono/uniexo-android.git
cd uniexo-android
```

### 2. Configurer la clé API Google Maps

1. Créer un projet sur [Google Cloud Console](https://console.cloud.google.com/) et activer **Maps SDK for Android**.
2. Créer une clé API, restreinte à l'application Android (package `com.unicofrance.uniexo`) avec le SHA-1 du certificat de debug de votre machine :
   ```bash
   ./gradlew signingReport
   ```
3. Créer un fichier `local.properties` à la racine du projet (non versionné) et y ajouter :
   ```
   MAPS_API_KEY=votre_clé_api_ici
   ```

### 3. Ajouter le fichier de données

Le fichier `containers.csv` n'est pas inclus dans ce repo (fourni séparément dans la consigne de l'exercice). Le placer à :
```
app/src/main/assets/containers.csv
```

### 4. Lancer l'application

Ouvrir le projet dans Android Studio et lancer sur un émulateur configuré avec une image système **Google Play** (requis pour Google Maps / Play Services). Attendre la fin du Gradle Sync avant de lancer.

Au premier lancement, l'application demande la permission de localisation et charge automatiquement les contenants du CSV dans la base de données locale (Room). Ce chargement initial ne se refait pas aux lancements suivants.

## Notes techniques

- **Compatibilité des dates** : le projet utilise l'API `java.time` avec Core Library Desugaring activé (déjà configuré dans `app/build.gradle.kts`), pour permettre son usage malgré un `minSdk` de 24.
- **Parsing CSV** : réalisé manuellement (`CsvParser.kt`), sans librairie externe, avec gestion des champs contenant des virgules entre guillemets.
- **Marqueurs de la carte** : icône générée par code à partir d'un vecteur Material Design, pour correspondre à la maquette Figma.

## Périmètre de l'exercice

Les fonctionnalités demandées dans la consigne sont implémentées : permission de localisation, affichage carte + position utilisateur, parsing et persistance du CSV, marqueurs par contenant, fiche de détail conforme à la maquette Figma.