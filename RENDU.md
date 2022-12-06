# Rapport du labo SMTP par Jérémie Santoro et Théo Coutaudier
## Description brève du projet
Ce programme Java permet d'effectuer des pranks en envoyant des mails forgés.
Ces mails seront envoyés par UNE victime (faux auteur) à plusieurs (au moins 2) autres victimes qui elles seront
les récéptrices de ce fameux mail.
Pour mettre ça en place nous avons utilisé des sockets puis effectuer les requêtes SMTP nécessaires à travers ceux-ci.
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
- Envoi d'un mail à un personne indésirable
- Charge trop grosse sur le serveur (ex: boucle infini sur un opération couteuse tel que l'envoi d'un mail)
- Code/communication qui ferait crasher le serveur car mal formulée par exemple

## Instructions pour mettre en place le serveur mock SMTP avec Docker
TODO 

## Instructions sur l'utilisation de notre projet (outil)
TODO

## Description de l'implémentation (présentation de l'architecture)
TODO