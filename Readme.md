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