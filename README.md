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

Concernant l'implémentation du formulaire de création d'une action :
- Il y a un Spinner qui va rechercher dans la base de données les différentes parcelles et les propose à l'utilisateur. Nous avons du créer notre Spinner dans la requête à la base de données sinon cette dernière s'effectuait dans tout les cas à la fin, soit après la création du Spinner. Sans cette technique nous n'arrivions pas alors à afficher les différentes parcelles.
- On a 5 boutons cliquables, un pour chaque action disponible (arroser, désherber, remise à 0, récolter et planter). Lorsqu'on clique sur un bouton, il change de couleur pour indiquer qu'il est sélectionner. Si on clique alors sur un autre boutton, le premier bouton est desélectionné (il reprend son aspect initial) et le deuxième bon est alors sélectionné (il change de couleur). Cela permet de ne pouvoir sélectionner qu'une unique action
- On a ensuite une date à rentrer. Par défaut, on a la date du jour. Nous avons essayé d'implémenter une vue calendrier pour pouvoir sélectionner si besoin une autre date, nous avons cependant pas eu suffisamment de temps pour avoir une version fonctionnelle. Nous nous somme donc rabattu sur une zone de texte à remplir.
- Nous avons ensuite le bouton "valider" qui permet finaliser le questionnaire. Dans un premier temps, il vérifie que l'ensemble des éléments sont bien rentrés, sinon il envoit des toast pour indiquer les erreurs :
                    - s'il n'y a aucune action sélectionné
                    - si la date n'est pas rentrée au bon format
On ne fait pas de test pour vérifier la parcelle, car il y en a une sélectionnée par défaut et il n'est pas possible de faire des erreurs de saisie avec un Spinner. Si toutes les conditions sont remplies, on fait une requête dans la base de données pour ajouter l'action et on indique par un toast à l'utilisateur que l'action a bien été enregistrée
 
 


