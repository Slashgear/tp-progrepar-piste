
--
-- Base de données :  `permispiste`
--
CREATE DATABASE IF NOT EXISTS `permispiste`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `permispiste`;

-- --------------------------------------------------------

--
-- Structure de la table `Action`
--

DROP TABLE IF EXISTS `Action`;
CREATE TABLE IF NOT EXISTS `Action` (
  `NUMACTION` int(11) NOT NULL AUTO_INCREMENT,
  `ACT_NUMACTION` int(11) DEFAULT NULL,
  `LIBACTION` char(25) DEFAULT NULL,
  `SCOREMIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMACTION`),
  KEY `A_POUR_SUCCESSEUR_FK` (`ACT_NUMACTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `Action`
--

INSERT INTO `Action` (`NUMACTION`, `ACT_NUMACTION`, `LIBACTION`, `SCOREMIN`) VALUES
  (1, NULL, 'Se mettre en tenue', 7),
  (2, 1, 'Préparation véhicule', 7),
  (3, NULL, 'Respect sécurité', 11),
  (4, 3, 'Effectuer manoeuvre', 7),
  (5, NULL, 'Emettre hypothèse', 12),
  (6, 5, 'Proposer solution', 7),
  (7, 6, 'Evaluation solution', 11),
  (8, 7, 'Effectuer solution', 10),
  (9, NULL, 'Accéder mécanique', 7),
  (10, 9, 'Vérifications mécanique', 9),
  (11, 10, 'Analyser panne(s)', 11),
  (12, 10, 'Effectuer niveaux', 10),
  (13, NULL, 'Réaction', 7),
  (14, 13, 'Action face au danger', 13),
  (15, NULL, 'Prise d''informations', 7),
  (16, 15, 'Placement', 12),
  (17, 15, 'Utilisation outils comm', 9),
  (18, NULL, 'Respect protocole comm', 15),
  (19, NULL, 'Travail rendu à temps', 13);

-- --------------------------------------------------------

--
-- Structure de la table `Appartient`
--

DROP TABLE IF EXISTS `Appartient`;
CREATE TABLE IF NOT EXISTS `Appartient` (
  `NUMJEU` int(11) NOT NULL,
  `NUMACTION` int(11) NOT NULL,
  PRIMARY KEY (`NUMJEU`,`NUMACTION`),
  KEY `APPARTIENT_FK` (`NUMJEU`),
  KEY `APPARTIENT2_FK` (`NUMACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Apprenant`
--

DROP TABLE IF EXISTS `Apprenant`;
CREATE TABLE IF NOT EXISTS `Apprenant` (
  `NUMAPPRENANT` int(11) NOT NULL AUTO_INCREMENT,
  `NOMAPPRENANT` char(25) DEFAULT NULL,
  `PRENOMAPPRENANT` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 15;

--
-- Contenu de la table `Apprenant`
--

INSERT INTO `Apprenant` (`NUMAPPRENANT`, `NOMAPPRENANT`, `PRENOMAPPRENANT`) VALUES
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
-- Structure de la table `Est_associe`
--

DROP TABLE IF EXISTS `Est_associe`;
CREATE TABLE IF NOT EXISTS `Est_associe` (
  `NUMACTION` int(11) NOT NULL,
  `NUMOBJECTIF` int(11) NOT NULL,
  PRIMARY KEY (`NUMACTION`,`NUMOBJECTIF`),
  KEY `EST_ASSOCIE_FK` (`NUMACTION`),
  KEY `EST_ASSOCIE2_FK` (`NUMOBJECTIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Est_associe`
--

INSERT INTO `Est_associe` (`NUMACTION`, `NUMOBJECTIF`) VALUES
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
-- Structure de la table `Fixe`
--

DROP TABLE IF EXISTS `Fixe`;
CREATE TABLE IF NOT EXISTS `Fixe` (
  `NUMMISSION` int(11) NOT NULL,
  `NUMOBJECTIF` int(11) NOT NULL,
  PRIMARY KEY (`NUMMISSION`,`NUMOBJECTIF`),
  KEY `FIXE_FK` (`NUMMISSION`),
  KEY `FIXE2_FK` (`NUMOBJECTIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Fixe`
--

INSERT INTO `Fixe` (`NUMMISSION`, `NUMOBJECTIF`) VALUES
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
-- Structure de la table `Indicateur`
--

DROP TABLE IF EXISTS `Indicateur`;
CREATE TABLE IF NOT EXISTS `Indicateur` (
  `NUMINDIC` int(11) NOT NULL AUTO_INCREMENT,
  `NUMACTION` int(11) NOT NULL,
  `POIDS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMINDIC`),
  KEY `EST_VALORISE_FK` (`NUMACTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `Indicateur`
--

INSERT INTO `Indicateur` (`NUMINDIC`, `NUMACTION`, `POIDS`) VALUES
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
-- Structure de la table `Inscription`
--

DROP TABLE IF EXISTS `Inscription`;
CREATE TABLE IF NOT EXISTS `Inscription` (
  `NUMAPPRENANT` INT(11) NOT NULL,
  `NUMJEU`       INT(11) NOT NULL,
  PRIMARY KEY (`NUMAPPRENANT`, `NUMJEU`),
  KEY `NUMAPPRENANT` (`NUMAPPRENANT`),
  KEY `NUMJEU` (`NUMJEU`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Contenu de la table `Inscription`
--

INSERT INTO `Inscription` (`NUMAPPRENANT`, `NUMJEU`) VALUES
  (3, 2),
  (12, 1),
  (12, 2),
  (13, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Jeu`
--

DROP TABLE IF EXISTS `Jeu`;
CREATE TABLE IF NOT EXISTS `Jeu` (
  `NUMJEU` int(11) NOT NULL AUTO_INCREMENT,
  `LIBELLEJEU` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMJEU`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Jeu`
--

INSERT INTO `Jeu` (`NUMJEU`, `LIBELLEJEU`) VALUES
  (1, 'Jeu n°1'),
  (2, 'Jeu n°2');

-- --------------------------------------------------------

--
-- Structure de la table `Mission`
--

DROP TABLE IF EXISTS `Mission`;
CREATE TABLE IF NOT EXISTS `Mission` (
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
-- Contenu de la table `Mission`
--

INSERT INTO `Mission` (`NUMMISSION`, `NUMJEU`, `LIBMISSION`) VALUES
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
-- Structure de la table `Objectif`
--

DROP TABLE IF EXISTS `Objectif`;
CREATE TABLE IF NOT EXISTS `Objectif` (
  `NUMOBJECTIF` int(11) NOT NULL AUTO_INCREMENT,
  `LIBOBECTIF` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMOBJECTIF`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `Objectif`
--

INSERT INTO `Objectif` (`NUMOBJECTIF`, `LIBOBECTIF`) VALUES
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
-- Structure de la table `Obtient`
--

DROP TABLE IF EXISTS `Obtient`;
CREATE TABLE IF NOT EXISTS `Obtient` (
  `NUMAPPRENANT` int(11) NOT NULL,
  `NUMACTION` int(11) NOT NULL,
  `VALEUR` INT(11) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`, `NUMACTION`),
  KEY `OBTIENT_FK` (`NUMAPPRENANT`),
  KEY `OBTIENT3_FK2` (`NUMACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Obtient`
--

INSERT INTO `Obtient` (`NUMAPPRENANT`, `NUMACTION`, `VALEUR`) VALUES
  (3, 3, 12),
  (3, 4, 8),
  (12, 3, 12),
  (12, 4, 18);

-- --------------------------------------------------------



--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Action`
--
ALTER TABLE `Action`
ADD CONSTRAINT `FK_ACTION_A_POUR_SU_ACTION` FOREIGN KEY (`ACT_NUMACTION`) REFERENCES `Action` (`NUMACTION`);

--
-- Contraintes pour la table `Appartient`
--
ALTER TABLE `Appartient`
ADD CONSTRAINT `FK_APPARTIE_APPARTIEN_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `Action` (`NUMACTION`),
ADD CONSTRAINT `FK_APPARTIE_APPARTIEN_JEU` FOREIGN KEY (`NUMJEU`) REFERENCES `Jeu` (`NUMJEU`);

--
-- Contraintes pour la table `Est_associe`
--
ALTER TABLE `Est_associe`
ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `Action` (`NUMACTION`),
ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_OBJECTIF` FOREIGN KEY (`NUMOBJECTIF`) REFERENCES `Objectif` (`NUMOBJECTIF`);

--
-- Contraintes pour la table `Fixe`
--
ALTER TABLE `Fixe`
ADD CONSTRAINT `FK_FIXE_FIXE2_OBJECTIF` FOREIGN KEY (`NUMOBJECTIF`) REFERENCES `Objectif` (`NUMOBJECTIF`),
ADD CONSTRAINT `FK_FIXE_FIXE_MISSION` FOREIGN KEY (`NUMMISSION`) REFERENCES `Mission` (`NUMMISSION`);

--
-- Contraintes pour la table `Indicateur`
--
ALTER TABLE `Indicateur`
ADD CONSTRAINT `FK_INDICATE_EST_VALOR_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `Action` (`NUMACTION`);

--
-- Contraintes pour la table `Inscription`
--
ALTER TABLE `Inscription`
ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `Apprenant` (`NUMAPPRENANT`),
ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`NUMJEU`) REFERENCES `Jeu` (`NUMJEU`);

--
-- Contraintes pour la table `Mission`
--
ALTER TABLE `Mission`
ADD CONSTRAINT `FK_MISSION_SE_COMPOS_JEU` FOREIGN KEY (`NUMJEU`) REFERENCES `Jeu` (`NUMJEU`);

--
-- Contraintes pour la table `Obtient`
--
ALTER TABLE `Obtient`
ADD CONSTRAINT `FK_OBTIENT_OBTIENT3_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `Action` (`NUMACTION`),
ADD CONSTRAINT `FK_OBTIENT_OBTIENT_APPRENAN` FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `Apprenant` (`NUMAPPRENANT`);

