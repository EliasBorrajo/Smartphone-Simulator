# Smartphone Simulator 📱

> Java desktop “smartphone” prototype with Contacts, Gallery & Weather apps (OOP semester project)

## Overview

### Tech Stack

| Layer                   | Technologies               |
| ----------------------- | -------------------------- |
| Language                | **Java 15**                |
| Build / Dependency Mgmt | **Maven 3.8+**             |
| UI                      | Swing / AWT                |
| JSON                    | Gson 2.8.6, Jackson 2.12.x |
| Testing                 | JUnit 5 (Jupiter)          |

### Project Structure

```
.
├── src/                      # Java packages (`ch.hevs.smartphone.*`)
│   └── ...                  
├── annex/                    # Sample JSON & media assets
├── pom.xml
└── README.md
```

### Build & Run

```bash
git clone https://github.com/EliasBorrajo/Smartphone-Simulator.git
cd Smartphone-Simulator
mvn clean package
java -jar target/Smartphone-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Requirements

* **JDK 15** or newer
* **Maven 3.6+**

### Status

`🗄️ Archived` – student project (no further maintenance).

### Authors

* Jonathan Bourquin
* Milena Lonfat
* Elias Borrajo

---

## Original README (untouched)

## Description

This project was carried out by a group of 3 people from class 602\_3: Jonathan Bourquin, Milena Lonfat and Elias Borrajo. It's the second semester project of Object‑Oriented Programming at HEG ‑ Sierre ‑ FIG.

The goal is to simulate a smartphone with realistic behavior on a screen. Three applications on the phone have been developed:

* Contacts
* Gallery
* Weather

The Contact and the Gallery application communicate together, and the weather application retrieves information from an API.

## How to install the app

This project is independent of the OS, it can be installed on Windows, macOS and Linux. To do this, you must create an environment variable directly on the PC. This variable acts as launcher / installer for the smartphone application. The name of the environment variable must be **SMARTPHONEPROJECT**. For example on Windows, the value of this variable must be something like `C:\Users\username\Desktop\SmartPhone`.

## Summary of results

We have 3 stand‑alone applications that meet the specifications requests.

* **Contact**

  * We have an address book
  * Adding contacts is done

    * Format of Swiss telephone numbers
  * Editing contacts
  * Delete contacts
  * The contact app communicates with the gallery app to change the contact's photo
* **Gallery**

  * Import photos from PC, alone or in groups
  * Show photo
  * Editing the name of the photo
  * Deleting the photo
* **Weather**

  * API recovery (openweathermap)
  * Display of the current, minimum and maximum temperature of the day

    * Humidity
    * Icon display
    * Displays the country
    * City change
    * Error handling in case of wrong city name, no connection, or wrong URL

The current date and time are displayed. The home button brings us back to the main screen same for the cardLayouts of the applications. The button to turn off the phone is working properly. The structure of the Smartphone is consistent with each application with its cardlayout management if there is one. The errors were handled with try and catch and JOptionPanes. The testing of the three applications has been carried out. All known bugs have been fixed.

All applications de‑serialize the data when opening the smartphone, and serialize the data when leaving the smartphone. Serialization saves JSON files on the PC, in the location given by the user when creating the environment variable. cf. how to install the app. If the JSON files are missing or corrupted, they are created or overwrite. The Javadoc was generated in HTML format in the `target` folder.

## Future improvements

* Optimization of the application, still too slow on some tasks.
* Add a lock screen
* Improved app refresh, maybe avoid rebuilding the whole app?
* Add additional masks for phone numbers from different countries
* Deepen the graphic aspect of the project
* Choose app language
* Being able to take snapshots like a virtual machine


# Specifications given by the teachers
# Projet - Simulateur de smartphone

## Directives générales

Le sujet du projet sera imposé afin de garantir un projet de taille raisonnable
pour le temps imparti.

Le sujet proposé permet de valider vos connaissances en programmation et en
gestion de projet. Démarquez-vous de vos collègues grâce à votre créativité et
votre esprit d’initiative.

Le projet se fait par groupe de 3. Le temps de travail minimum alloué au projet
est de 60 périodes par étudiant, mais selon votre rythme, vous pourrez être
amenés à faire plus.

Vous serez évalués sur une session de tests de 20 minutes par groupe. Vous
testerez les fonctionnalités de votre application selon le protocole de tests
imposé par des professeurs. Une partie des questions concernera
l’architecture et le code de l’application.

## Sujet

Le projet consiste à simuler un *smartphone* avec trois applications :

- un carnet d'adresses (ajout, édition, suppression);

- une galerie photographies (ajout, suppression, changement du nom du fichier);

- une application faisant appel à une API externe (agenda, météo, bourse, stockage
  de données, ...)

Au moins deux des applications développées doivent interagir. Par exemple :

- accéder aux contacts depuis l'agenda pour fixer un rendez-vous (le carnet
  d'adresses est lancé depuis l'agenda, on choisit un contact et le contact est
  ajouté au rendez-vous);

- associer une photo à un contact (la galerie de photos est ouverte depuis le
  carnet d'adresses, on choisit une photo qui est ensuite affichée à côté du nom
  du contact);

- sauvegarder les photos dans le cloud (par exemple avec
  [Pantry](https://getpantry.cloud)).

Remarques :

-	Lorsqu’on éteint le *smartphone* (i.e. on quitte l’application),
     les données sont sauvegardées dans un fichier JSON.

-	La taille des fenêtres de vos app. doit ressembler à ce qu'on retrouve sur un vrai smartphone.

-	Votre *smartphone* est utilisable à la souris (*combobox*, *scrollbar*, ... autorisés).

-	Gestion simplifiée de l’écran principal (écran d'accueil, *launcher*).

## Délivrables

- Invitation à rejoindre le projet **GitLab** (avec le rôle *developer*)
  (envoyer l'invitation à `@nafm` et `@jlbhevs`)

- Le journal de travail individuel

## Contraintes techniques

- Gestion de version : git et GitLab

- Projet Maven permettant de compiler un JAR contenant toutes les dépendances
  avec la commande

  ```bash
  mvn clean package
  ```

- Langage : Java 14 ou supérieur

    - Utiliser le paradigme objet vu en cours (composition, héritage, polymorphisme, classes abstraites et interfaces)

    - Définir des codes d'erreur ainsi qu'une exception

    - Le code est documenté ([javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html))

    - Utiliser le framework Swing pour l'interface graphique

    - Ecrire des tests avec JUnit 5 pour chaque application

- L'application peut ne pas démarrer si le fichier JSON contenant les données
  est corrompu ou manquant. Le programme retourne alors un code d'erreur
  décrivant le problème rencontré. C'est la seule étape où le programme
  peut se terminer automatiquement : dans tous les autres cas, d'éventuelles
  erreurs sont gérées par des exceptions et un message d'erreur est affiché à
  l'écran.

- Le programme fonctionne sous Windows, Linux et macOS.

