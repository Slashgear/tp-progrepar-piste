
--
-- Base de données :  `permispiste`
--
CREATE DATABASE IF NOT EXISTS `permispiste`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `permispiste`;

-- --------------------------------------------------------

--
-- Structure de la table `action`
--

DROP TABLE IF EXISTS `action`;
CREATE TABLE IF NOT EXISTS `action` (
  `NUMACTION` int(11) NOT NULL AUTO_INCREMENT,
  `ACT_NUMACTION` int(11) DEFAULT NULL,
  `LIBACTION` char(25) DEFAULT NULL,
  `SCOREMIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMACTION`),
  KEY `A_POUR_SUCCESSEUR_FK` (`ACT_NUMACTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `action`
--

INSERT INTO `action` (`NUMACTION`, `ACT_NUMACTION`, `LIBACTION`, `SCOREMIN`) VALUES
  (1, NULL, 'Se mettre en tenue', 10),
  (2, 1, 'Préparation véhicule', 10),
  (3, NULL, 'Respect sécurité', 14),
  (4, 3, 'Effectuer manoeuvre', 10),
  (5, NULL, 'Emettre hypothèse', 15),
  (6, 5, 'Proposer solution', 10),
  (7, 6, 'Evaluation solution', 14),
  (8, 7, 'Effectuer solution', 13),
  (9, NULL, 'Accéder mécanique', 10),
  (10, 9, 'Vérifications mécanique', 12),
  (11, 10, 'Analyser panne(s)', 14),
  (12, 10, 'Effectuer niveaux', 13),
  (13, NULL, 'Réaction', 10),
  (14, 13, 'Action face au danger', 16),
  (15, NULL, 'Prise d''informations', 10),
  (16, 15, 'Placement', 15),
  (17, 15, 'Utilisation outils comm', 12),
  (18, NULL, 'Respect protocole comm', 18),
  (19, NULL, 'Travail rendu à temps', 16);

-- --------------------------------------------------------

--
-- Structure de la table `appartient`
--

DROP TABLE IF EXISTS `appartient`;
CREATE TABLE IF NOT EXISTS `appartient` (
  `NUMJEU` int(11) NOT NULL,
  `NUMACTION` int(11) NOT NULL,
  PRIMARY KEY (`NUMJEU`,`NUMACTION`),
  KEY `APPARTIENT_FK` (`NUMJEU`),
  KEY `APPARTIENT2_FK` (`NUMACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

DROP TABLE IF EXISTS `apprenant`;
CREATE TABLE IF NOT EXISTS `apprenant` (
  `NUMAPPRENANT` int(11) NOT NULL AUTO_INCREMENT,
  `NOMAPPRENANT` char(25) DEFAULT NULL,
  `PRENOMAPPRENANT` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 15;

--
-- Contenu de la table `apprenant`
--

INSERT INTO `apprenant` (`NUMAPPRENANT`, `NOMAPPRENANT`, `PRENOMAPPRENANT`) VALUES
  (1, 'Caron', 'Antoine'),
  (2, 'Chauslende', 'Adrien'),
  (3, 'Galdeano', 'Alexandre'),
  (4, 'Reynaud', 'Pierre'),
  (11, 'Premilieu', 'Laura'),
  (12, 'Fagno', 'Corinne'),
  (13, 'Rodarie', 'Dimitri'),
  (14, 'Ferjani', 'Gael');


-- --------------------------------------------------------

--
-- Structure de la table `est_associe`
--

DROP TABLE IF EXISTS `est_associe`;
CREATE TABLE IF NOT EXISTS `est_associe` (
  `NUMACTION` int(11) NOT NULL,
  `NUMOBJECTIF` int(11) NOT NULL,
  PRIMARY KEY (`NUMACTION`,`NUMOBJECTIF`),
  KEY `EST_ASSOCIE_FK` (`NUMACTION`),
  KEY `EST_ASSOCIE2_FK` (`NUMOBJECTIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `est_associe`
--

INSERT INTO `est_associe` (`NUMACTION`, `NUMOBJECTIF`) VALUES
  (1, 5),
  (2, 5),
  (3, 1),
  (3, 5),
  (3, 6),
  (4, 1),
  (6, 2),
  (7, 2),
  (8, 2),
  (10, 3),
  (11, 3),
  (12, 3),
  (13, 4),
  (14, 4),
  (15, 6),
  (15, 7),
  (16, 6),
  (17, 7),
  (18, 7),
  (19, 8);

-- --------------------------------------------------------

--
-- Structure de la table `fixe`
--

DROP TABLE IF EXISTS `fixe`;
CREATE TABLE IF NOT EXISTS `fixe` (
  `NUMMISSION` int(11) NOT NULL,
  `NUMOBJECTIF` int(11) NOT NULL,
  PRIMARY KEY (`NUMMISSION`,`NUMOBJECTIF`),
  KEY `FIXE_FK` (`NUMMISSION`),
  KEY `FIXE2_FK` (`NUMOBJECTIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `fixe`
--

INSERT INTO `fixe` (`NUMMISSION`, `NUMOBJECTIF`) VALUES
  (1, 1),
  (1, 4),
  (1, 5),
  (1, 6),
  (2, 2),
  (2, 3),
  (2, 5),
  (3, 5),
  (3, 7),
  (3, 8),
  (4, 3),
  (4, 4),
  (5, 1),
  (5, 5),
  (5, 7),
  (5, 8),
  (6, 2),
  (7, 7),
  (8, 5);

-- --------------------------------------------------------

--
-- Structure de la table `indicateur`
--

DROP TABLE IF EXISTS `indicateur`;
CREATE TABLE IF NOT EXISTS `indicateur` (
  `NUMINDIC` int(11) NOT NULL AUTO_INCREMENT,
  `NUMACTION` int(11) NOT NULL,
  `POIDS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMINDIC`),
  KEY `EST_VALORISE_FK` (`NUMACTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `indicateur`
--

INSERT INTO `indicateur` (`NUMINDIC`, `NUMACTION`, `POIDS`) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 5),
  (4, 4, 4),
  (5, 5, 1),
  (6, 6, 2),
  (7, 7, 3),
  (8, 8, 2),
  (9, 9, 1),
  (10, 10, 2),
  (11, 11, 3),
  (12, 12, 1),
  (13, 13, 1),
  (14, 14, 3),
  (15, 15, 1),
  (16, 16, 2),
  (17, 17, 1),
  (18, 18, 5),
  (19, 19, 1);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `NUMAPPRENANT` INT(11) NOT NULL,
  `NUMJEU`       INT(11) NOT NULL,
  PRIMARY KEY (`NUMAPPRENANT`, `NUMJEU`),
  KEY `NUMAPPRENANT` (`NUMAPPRENANT`),
  KEY `NUMJEU` (`NUMJEU`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Contenu de la table `inscription`
--

INSERT INTO `inscription` (`NUMAPPRENANT`, `NUMJEU`) VALUES
  (3, 2),
  (12, 1),
  (12, 2),
  (13, 1);

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

DROP TABLE IF EXISTS `jeu`;
CREATE TABLE IF NOT EXISTS `jeu` (
  `NUMJEU` int(11) NOT NULL AUTO_INCREMENT,
  `LIBELLEJEU` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMJEU`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `jeu`
--

INSERT INTO `jeu` (`NUMJEU`, `LIBELLEJEU`) VALUES
  (1, 'Jeu n°1'),
  (2, 'Jeu n°2');

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE IF NOT EXISTS `mission` (
  `NUMMISSION` int(11) NOT NULL AUTO_INCREMENT,
  `NUMJEU` int(11) NOT NULL,
  `LIBMISSION` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMMISSION`),
  KEY `SE_COMPOSE_FK` (`NUMJEU`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 9;

--
-- Contenu de la table `mission`
--

INSERT INTO `mission` (`NUMMISSION`, `NUMJEU`, `LIBMISSION`) VALUES
  (1, 1, 'Mission A'),
  (2, 1, 'Mission B'),
  (3, 1, 'Mission C'),
  (4, 1, 'Mission 3'),
  (5, 2, 'Mission A2'),
  (6, 2, 'Mission B2'),
  (7, 2, 'Mission C2'),
  (8, 2, 'Mission D2');

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

DROP TABLE IF EXISTS `objectif`;
CREATE TABLE IF NOT EXISTS `objectif` (
  `NUMOBJECTIF` int(11) NOT NULL AUTO_INCREMENT,
  `LIBOBECTIF` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMOBJECTIF`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `objectif`
--

INSERT INTO `objectif` (`NUMOBJECTIF`, `LIBOBECTIF`) VALUES
  (1, 'Réussir manoeuvre'),
  (2, 'Analyse des problèmes'),
  (3, 'Vérifier mécanique'),
  (4, 'Réagir au danger'),
  (5, 'Prise de poste correcte'),
  (6, 'Placement sur la voie'),
  (7, 'Interaction avec pilote'),
  (8, 'Respect délai');

-- --------------------------------------------------------

--
-- Structure de la table `obtient`
--

DROP TABLE IF EXISTS `obtient`;
CREATE TABLE IF NOT EXISTS `obtient` (
  `NUMAPPRENANT` int(11) NOT NULL,
  `NUMACTION` int(11) NOT NULL,
  `VALEUR` INT(11) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`, `NUMACTION`),
  KEY `OBTIENT_FK` (`NUMAPPRENANT`),
  KEY `OBTIENT3_FK2` (`NUMACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `obtient`
--

INSERT INTO `obtient` (`NUMAPPRENANT`, `NUMACTION`, `VALEUR`) VALUES
  (3, 3, 12),
  (3, 4, 8),
  (12, 3, 12),
  (12, 4, 18);

-- --------------------------------------------------------



--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `action`
--
ALTER TABLE `action`
ADD CONSTRAINT `FK_ACTION_A_POUR_SU_ACTION` FOREIGN KEY (`ACT_NUMACTION`) REFERENCES `action` (`NUMACTION`);

--
-- Contraintes pour la table `appartient`
--
ALTER TABLE `appartient`
ADD CONSTRAINT `FK_APPARTIE_APPARTIEN_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`),
ADD CONSTRAINT `FK_APPARTIE_APPARTIEN_JEU` FOREIGN KEY (`NUMJEU`) REFERENCES `jeu` (`NUMJEU`);

--
-- Contraintes pour la table `est_associe`
--
ALTER TABLE `est_associe`
ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`),
ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_OBJECTIF` FOREIGN KEY (`NUMOBJECTIF`) REFERENCES `objectif` (`NUMOBJECTIF`);

--
-- Contraintes pour la table `fixe`
--
ALTER TABLE `fixe`
ADD CONSTRAINT `FK_FIXE_FIXE2_OBJECTIF` FOREIGN KEY (`NUMOBJECTIF`) REFERENCES `objectif` (`NUMOBJECTIF`),
ADD CONSTRAINT `FK_FIXE_FIXE_MISSION` FOREIGN KEY (`NUMMISSION`) REFERENCES `mission` (`NUMMISSION`);

--
-- Contraintes pour la table `indicateur`
--
ALTER TABLE `indicateur`
ADD CONSTRAINT `FK_INDICATE_EST_VALOR_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `apprenant` (`NUMAPPRENANT`),
ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`NUMJEU`) REFERENCES `jeu` (`NUMJEU`);

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
ADD CONSTRAINT `FK_MISSION_SE_COMPOS_JEU` FOREIGN KEY (`NUMJEU`) REFERENCES `jeu` (`NUMJEU`);

--
-- Contraintes pour la table `obtient`
--
ALTER TABLE `obtient`
ADD CONSTRAINT `FK_OBTIENT_OBTIENT3_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`),
ADD CONSTRAINT `FK_OBTIENT_OBTIENT_APPRENAN` FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `apprenant` (`NUMAPPRENANT`);

