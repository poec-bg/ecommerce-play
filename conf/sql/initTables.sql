INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `image`)
VALUES ("0cab348e-fd8b-42bb-b09a-2d19b44c4f12", "Calculatrice Scientifique.", "model TX458", 125.49, "calculatrice.jpg");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `image`)
VALUES ("b6c946e8-539e-499e-9f32-1495a8876a37", "Règle à calcule", "Authentique règle à calcule de 1975.", 15.68, "sliderule.jpg");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `image`)
VALUES ("55881e8-539e-499e-9f32-1495a8876a37", "Alienware M17W", "Un an après le M17x-R3, voici la nouvelle génération du M17x. Ce notebook de 17,3 pouces signé Alienware intègre toujours des composants puissants dans un châssis qui brille de mille feux en différentes couleurs.", 2650.00, "alienware-m17x.jpg");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `image`)
VALUES ("775533e8-539e-499e-9f32-1495a8876a37", "Mac", "Model de luxe avec pomme plaqué or.", 3249.99, "mac.png");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `image`)
VALUES ("001144e8-539e-499e-9f32-1495a8876a37", "Boulier", "En bois de bouleau.", 4.35, "boulierchinoisbig.jpg");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `categorie`, `image`)
VALUES ("001144e8-6688-499e-9f32-1495a8876a37", "Tablette Samsung Galaxy Tab A6", "Tablette Samsung Galaxy Tab A6 10.1\" 32 Go Blanc. \nProcesseur: Octacore \nSystème d'exploitation:	Android 6.0 Marshmallow.", 299.99, "Tablette", "Samsung-Galaxy-Tab-A6.jpg");

INSERT INTO Produit (`id`, `nom`, `description`, `prixUnitaire`, `categorie`, `image`)
VALUES ("001144e8-1592-499e-9f32-1495a8876a37", "Tablette Asus Z300M-6B059A", "Tablette Asus Z300M-6B059A 10.1\" 64 Go Blanc. \nProcesseur	Mediatek 8163 – Quad core \nSystème d'exploitation	Android 6.0 Marshmallow", 249.99, "Tablette", "asus-z300M.jpg");

INSERT INTO Client (`id`, `nom`, `prenom`, `email`, `motDePasse`, `role`)
VALUES ("00000000-00A1-499e-9f32-1495a8876a37", "Hadock", "...", "hadock@edition-dupuis.be", "$2a$12$oiqAsSTl2AYh2o5hYHPMV.B95L5hs/XkKAkLRDjbMVK6q2g9lNmkO", "CLIENT");
#mdp: 000000

INSERT INTO Client (`id`, `nom`, `prenom`, `email`, `motDePasse`, `role`)
VALUES ("00000000-00ZD-499e-9f32-1495a8876a37", "Luky", "Luke", "ll@warwest.com", "$12$5CFuvPAEdgtyvH8CtOjCHOZlcYeN6d78K0yMRRN5Uo4g1LxLa95/m", "CLIENT");
#mdp: 777

INSERT INTO Client (`id`, `nom`, `prenom`, `email`, `motDePasse`, `role`)
VALUES ("00000000-AASS-499e-9f32-1495a8876a37", "Pheles", "Mephisto", "mephis@fallen.god", "$2a$12$iViyHMjci5RW2CqruhQ7LOiQZYjGMYFJIMwI/RuATZZefWLXMH7w6", "ADMIN");
#mdp: 666