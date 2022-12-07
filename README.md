# Rapport du labo SMTP par Jérémie Santoro et Théo Coutaudier
## Description brève du projet
Ce programme Java permet d'effectuer des pranks en envoyant des mails forgés.
Ces mails seront envoyés par UNE victime (faux auteur) à plusieurs (au moins 2) autres victimes qui elles seront
les récéptrices de ce fameux mail.
Pour mettre ça en place, nous avons utilisé des sockets puis effectuer les requêtes SMTP nécessaires à travers ceux-ci.
Le projet est composé en 3 principaux packages :
### config
Contient les outils nécessaires pour aller récupérer les informations de l'utilisateur puis les mettre à disposition
pour les autres packages ci-dessous
### model
Contient toute la logique métier du programme. Les différentes entités représentées sont: Groupe, Mail, Personne et Prank
### smtp
Contient la logique de communication entre notre programme (client) et le serveur SMTP. Cette partie va donc s'occuper
de :
- La connexion au serveur
- La communication avec le serveur
- L'envoi des mails

## Qu'est-ce que MockMock
MockMock est un serveur SMTP qui va permettre de tester nos différents clients SMTP sans que des erreurs sur ceux-ci
soient problématiques. Ce système permet notamment d'éviter les boulettes suivantes:
- Envoi d'un mail à une personne indésirable
- Charge trop grosse sur le serveur (ex: boucle infini sur une opération couteuse tel que l'envoi d'un mail)
- Code/communication qui ferait crasher le serveur, car mal formulée par exemple.
Cet outil ne va réellement envoyer aucun mail.

## Instructions pour mettre en place le serveur mock SMTP avec Docker
Commencer par installer Docker (lien pour téléchargable ici : https://www.docker.com/).

Ajouter un dossier Docker à la racine du projet et y ajouter les fichiers suivants :
- build-image.sh, ce script permet de lancer un build l'image locallement.
- run-container.sh, ce script permet de lancer le server dans docker (avec les 2 ports en parametre)
- MockMock.jar, ceci est le serveur .jar
- Dockerfile, ce fichier contient la commande cmd une fois que le Docker container à démarrer

Pour lancer le serveur Mock il faut :
Lancer Docker,
une fois que Docker run, executer les scripts build-image.sh et run-container.sh (dans cet ordre) dans un terminal.

Le serveur est prêt à l'emploi, pour le voir il suffit d'écrire "localhost:8282" dans la bar de recherche d'un navigateur.

## Instructions sur l'utilisation de notre projet (outil)
### Fichiers de configuration
Les fichiers devant être modifiés par l'utilisateur sont les suivants:
#### config.propreties
Pour choisir l'adresse ainsi que le port du serveur SMTP désiré.
Ainsi que le nombre de groupes qui seront créés.
Modifiez uniquement les valeurs à gauche des "=" !
#### messages.utf8
Contient les différents contenus de mail possibles.
Ceux-ci doivent être séparés par "\n==\n"
#### victims.utf8
Contient tous les emails des victimes.
Une ligne doit correspondre à une adresse mail de victime (veillez à bien respecter le format mail avec @)

## Description de l'implémentation (présentation de l'architecture)
Classes utilisées:

Ces classes sont très simples et contiennent très peu de logique si ce n'est ConfigurationManager qui doit lire les fichiers de configuration et vérifer que les informations soient cohérentes.

![UML](./figures/uml.png)

### Présenation du main
Toutes ces classes sont utilisées dans le *main* en plusieurs parties:

1. Récupération des configuration

   C'est la que nous récupérons les informations provenant des fichiers de configuration:

   - Adresse et port du serveur
   - Nombre de groupes désirés
   - Liste des messages (bodies)
   - Liste des emails des victimes

2. Génération des groupes

   Le nombre de groupes voulus est généré en attribuant à chaque fois 1 sender et 2 receiver. (Le nombre de receiver voulu peut être changé via la variable *nbReceiver*).

3. Création des mails

   Les mails sont crées pour chaque groupe en leur donnant un body aléatoire.

4. Envoi des mails

   La classe SmtpClient est utilisé pour communiquer avec le serveur SMTP en fonction des valeurs générées aux étapes précédentes.

### Communication SMTP sans erreur
c: EHLO xhaka
S: ... (infos sur le serveur)
S: 250 Ok
C: MAIL FROM: <sender email>
S: 250 Ok
Boucle sur (
C: MAIL TO: <receiver email>
S: 250 Ok
)
C: DATA
S: 250 Ok
C: Bonjour je suis un email CRLF.CRLF
S: 250 Ok
C: Quit
S: 221 Bye

   

   