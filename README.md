# Read Me
## Description
This project was carried out by a group of 3 people from class 602_3: Jonathan Bourquin, Milena Lonfat and Elias Borrajo.
It's the second semester project at HEG - Sierre - FIG.

The goal is to simulate a smartphone with realistic behavior on a screen.
Three applications on the phone have been developed :
- Contacts
- Gallery
- Weather

The Contact and the Gallery application communicate together,
and the weather application retrieves information from an API.

## How to install the app
This project is independent of the OS, it can be installed on Windows, MacOS and Linux.
To do this, you must create an environment variable directly on the PC. This variable
acts as launcher / installer for the smartphone application.

## Future improvements
    - Optimization of the application, still too slow on some tasks.
    - Improved app refresh, maybe avoid rebuilding the whole app?
    - In case of corrupted file, do not destroy / overwrite the corrupted file, and save it in a JSON file in parallel, in order to be able to recover the valid data if necessary 

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
