# tp-devmo-grandjean_coutier_potager
Pour ce TP, nous avons choisi d'implémenter les éléments suivants :
  - la vue Historique Global de la page d'accueil, listant l'ensemble des actions ayant été effectuées sur le potager
  - la vue Ajouter une action, contenant un formulaire pour ajouter une action sur une parcelle dans la base de données
  - une barre de navigation entre les deux vues ci-dessus
  - une base de données pour les Actions et les parcelles

Plus précisément, nous avons effectué quelques modifications par rapport à nos maquettes et au fonctionnement théorique de l'application.

Sur le système de navigation :
    - pour plus de simplicité, la toolbar ne contient que le nom de l'application (et pas le nom de la page affichée), ainsi qu'un bouton pour ouvrir le tiroir de navigation. Elle ne contient pas l'avatar de l'usager (la notion d'usager n'a pas été implémentée), ni le bouton permettant d'accéder aux paramètres, ni le bouton de notifications.
    - Dans le tiroir de navigation, seules les deux fragments implantés sont disponibles.

Sur la page d'accueil :
 - La vue Parcelle a été abandonnée pour plus de simplicité. 
 - Dans la liste des actions, l'auteur de l'action a aussi été abandonné, car il est lié à l'utilisateur connecté à l'application, et nous n'avons choisi de ne pas implémenter l'authentification sur l'application, pour plus de simplicité. Ainsi, nous n'avons pas non plus créé d'entité de base de données pour la gestion des Personnes.
 - Dans la liste des actions, les actions ne sont pas cliquables, encore une fois pour plus de simplicité. Si les descriptions d'actions sont donc bien implémentées dans la base de données, elles ne sont donc affichées nulle part.

 Sur le formulaire d'ajout d'action :
 - Pour plus de simplicité, le type d'action Autre n'a pas été implémenté
 - La possibilité de signaler un problème a aussi été retirée, car c'est une instance au fonctionnement différent de celui d'une simple Action
 - La possibilité de joindre une image ainsi que de rentrer une description n'a pas non plus été implémentée, encore une fois pour plus de simplicité
 - Seul le bouton 'valider' (et pas le bouton annuler) a été implémenté.

 Dans la base de données :
 - Les utilisateurs n'ont donc pas été pris en compte
 
 


