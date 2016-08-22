# Installation

- https://www.playframework.com/download#older-versions
- unzip
- Création de la variable d'environnement PLAY_HOME
- Modification de la variable d'environnement PATH pour ajouter %PLAY_HOME%

Ouvrir un powershell et taper
```
play
~        _            _
~  _ __ | | __ _ _  _| |
~ | '_ \| |/ _' | || |_|
~ |  __/|_|\____|\__ (_)
~ |_|            |__/
~
~ play! 1.4.2, https://www.playframework.com
~
~ Usage: play cmd [app_path] [--options]
~
~ with,  new      Create a new application
~        run      Run the application in the current shell
~        help     Show play help
~
```

# Prérequis

Il est nécessaire de configurer ivy pour accéder au repository local maven.
Copier le fichier ./conf/ivysettings.xml dans ~/.ivy2/

Ensuite il est possible de résoudre les dépendances :
Dans le projet ecommerce-maven faire :
```
ecommerce-maven/mvn clean install
```

# Premier usage pour ECommerce
Récupération des dépendances configurées dans le fichier conf/dependecies.yml
```
play deps
```
Création des fichiers Intellij (pour ouvrir le projet dans l'IDE)
```
play idealize
```
Lancement de l'application
```
play run
```
Ouvrir un navigateur sur http://localhost:9000

# Nodejs / Grunt

## Prérequis

- Installer et ajouter au path nodejs (automatique sur Windows via l'installer '.msi') :  https://nodejs.org/en/download/ (version 4.5.x)
- Ouvrir une invit de commande et taper
...
npm
...
Ceci afin de vérifier la disponibilité de la commande.

- Ajouter la commande Grunt au niveau global :
...
npm install -g grunt-cli
...

## Configuration dans le projet
- Via l'invit de commande, aller dans le dossier du projet
- taper
...
npm i
...
Ceci afin de télécharger l'ensemble des dépendances référencées dans le fichier package.json

## Utilisation de Grunt
- En mode dev, lancer :
...
grunt dev
...
Et laisser tourner la fenêtre. A chaque modification de fichier css ou js, le module watch va générer les fichiers compilés correpondant

- Pour lancer browser-sync :
...
grunt test-responsive
...
Et laisser tourner la fenêtre. Il suffit de connecter tous les appareils sur le port :9001

- Pour générer les fichiers minifiés :
...
grunt
...

- Pour lancer les tests fonctionnels (e2e testing) :
...
grunt nightwatch
...
Il est possible de modifier le navigateur via la sous commande dans le fichier Gruntfile.js, exemple (Attention de bien vérifier que les drivers correspondant sont téléchargés):
...
grunt.registerTask('test', ['nightwatch:chrome']);
...