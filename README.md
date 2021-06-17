# Read Me
Ce projet a pour but de simuler un smartphone dans l'ordinateur. 
Le projet est réalisé par un groupe de 3 personnes de la classe 602_3 : Jonathan Bourquin, Milena Lonfat, Elias Borrajo.

Ce projet est indépendant de l'OS, il peut être installé sur WINDOWS / MACOS / LINUX. 
Pour cela, il faut créer une variable d'environnement directement sur le PC. Cette variable 
fait office de launcher / d'installeur pour l'application Smartphone.

## Delivrables
A la fin du projet, il faut déliver : 
- Un fichier pour chaque auteur contenant le journal de travail
- Une invitation au projet GitLab à @nafm & @jlbhevs en leur attribuant le rôle de developper

Dans le projet se trouvant sur le GIT, le README explique la procédure pour installer l'app sur le PC.

## Description
Second semester project at HEG - Sierre - FIG.
The goal is to simulate a smartphone with realistic behavior on a screen.
Three applications on the phone have been developed :
- Contacts 
- Gallery 
- Weather.

Contact app and a Gallery app which communicate together, 
and a weather app that retrieves information from an API.

## Comment installer l'application
Pour installer l'application sur le PC, et pouvoir executer le fichier .JAR, il faut 

## Futures améliorations
    - optimiser le smartphone, car encore lent sur certaines tâches.
    - Améliorer le refresh de nos app, peut être éviter de rebuild toute l'app ? 
    - Pouvoir déplacer le smartphone sur l'écran du PC
    - En cas de fichier corrompu, ne pas détruire/overwrite le fichier corrompu, et le sauvegarder dans un fichier JSON en parallèle, afin de pouvoir récuperer les données valides si bsoins 















# CAHIER DES CHARGES DONNE PAR LES PROFESSEURS 
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
