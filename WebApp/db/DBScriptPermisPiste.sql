CREATE DATABASE IF NOT EXISTS permispiste
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE permispiste;

--
-- Base de données :  `permispiste`
--
--
-- Structure de la table `action`
--

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

CREATE TABLE IF NOT EXISTS `apprenant` (
  `NUMAPPRENANT` int(11) NOT NULL AUTO_INCREMENT,
  `NOMAPPRENANT` char(25) DEFAULT NULL,
  `PRENOMAPPRENANT` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `calendrier`
--

CREATE TABLE IF NOT EXISTS `calendrier` (
  `DATEJOUR` date NOT NULL,
  PRIMARY KEY (`DATEJOUR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `est_associe`
--

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

CREATE TABLE IF NOT EXISTS `indicateur` (
  `NUMINDIC` int(11) NOT NULL AUTO_INCREMENT,
  `NUMACTION` int(11) NOT NULL,
  `LIBINDIC` char(20) DEFAULT NULL,
  `POIDS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMINDIC`),
  KEY `EST_VALORISE_FK` (`NUMACTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `indicateur`
--

INSERT INTO `indicateur` (`NUMINDIC`, `NUMACTION`, `LIBINDIC`, `POIDS`) VALUES
(1, 1, 'indic_misetenue', 1),
(2, 2, 'indic_prepvehicule', 2),
(3, 3, 'indic_respecsecu', 5),
(4, 4, 'indic_manoeuvre', 4),
(5, 5, 'indic_hypothese', 1),
(6, 6, 'indic_propositionsol', 2),
(7, 7, 'indic_evalsol', 3),
(8, 8, 'indic_effectuersol', 2),
(9, 9, 'indic_accesmeca', 1),
(10, 10, 'indic_verifmeca', 2),
(11, 11, 'indic_analyserpanne', 3),
(12, 12, 'indic_effectuerniv', 1),
(13, 13, 'indic_reaction', 1),
(14, 14, 'indic_actiondanger', 3),
(15, 15, 'indic_priseinfo', 1),
(16, 16, 'indic_placement', 2),
(17, 17, 'indic_outilscomm', 1),
(18, 18, 'indic_respprotocole', 5),
(19, 19, 'indic_travailatemps', 1);

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

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

CREATE TABLE IF NOT EXISTS `mission` (
  `NUMMISSION` int(11) NOT NULL AUTO_INCREMENT,
  `NUMJEU` int(11) NOT NULL,
  `LIBMISSION` char(25) DEFAULT NULL,
  PRIMARY KEY (`NUMMISSION`),
  KEY `SE_COMPOSE_FK` (`NUMJEU`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

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

CREATE TABLE IF NOT EXISTS `obtient` (
  `NUMAPPRENANT` int(11) NOT NULL,
  `DATEJOUR` date NOT NULL,
  `NUMACTION` int(11) NOT NULL,
  `VALEURDEBUT` int(11) DEFAULT NULL,
  `VALEURFIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMAPPRENANT`,`DATEJOUR`,`NUMACTION`),
  KEY `OBTIENT_FK` (`NUMAPPRENANT`),
  KEY `OBTIENT3_FK` (`DATEJOUR`),
  KEY `OBTIENT3_FK2` (`NUMACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `possede`
--

CREATE TABLE IF NOT EXISTS `possede` (
  `NUMACTION` int(11) NOT NULL,
  `NUMREGLE` int(11) NOT NULL,
  PRIMARY KEY (`NUMACTION`,`NUMREGLE`),
  KEY `POSSEDE_FK` (`NUMACTION`),
  KEY `POSSEDE2_FK` (`NUMREGLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `regle`
--

CREATE TABLE IF NOT EXISTS `regle` (
  `NUMREGLE` int(11) NOT NULL AUTO_INCREMENT,
  `LIBREGLE` char(25) DEFAULT NULL,
  `SCOREMIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMREGLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


--
-- Structure de la table `regle`
--

CREATE TABLE IF NOT EXISTS `inscription` (
  `NUMAPPRENANT` INT(11) NOT NULL,
  `NUMJEU`       INT(11) NOT NULL,
  KEY (`NUMAPPRENANT`),
  KEY (`NUMJEU`),
  FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `apprenant` (`NUMAPPRENANT`),
  FOREIGN KEY (`NUMJEU`) REFERENCES `jeu` (`NUMJEU`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


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
  ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_OBJECTIF` FOREIGN KEY (`NUMOBJECTIF`) REFERENCES `objectif` (`NUMOBJECTIF`),
  ADD CONSTRAINT `FK_EST_ASSO_EST_ASSOC_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`);

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
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `FK_MISSION_SE_COMPOS_JEU` FOREIGN KEY (`NUMJEU`) REFERENCES `jeu` (`NUMJEU`);

--
-- Contraintes pour la table `obtient`
--
ALTER TABLE `obtient`
  ADD CONSTRAINT `FK_OBTIENT_OBTIENT3_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`),
  ADD CONSTRAINT `FK_OBTIENT_OBTIENT2_CALENDRI` FOREIGN KEY (`DATEJOUR`) REFERENCES `calendrier` (`DATEJOUR`),
  ADD CONSTRAINT `FK_OBTIENT_OBTIENT_APPRENAN` FOREIGN KEY (`NUMAPPRENANT`) REFERENCES `apprenant` (`NUMAPPRENANT`);

--
-- Contraintes pour la table `possede`
--
ALTER TABLE `possede`
  ADD CONSTRAINT `FK_POSSEDE_POSSEDE2_REGLE` FOREIGN KEY (`NUMREGLE`) REFERENCES `regle` (`NUMREGLE`),
  ADD CONSTRAINT `FK_POSSEDE_POSSEDE_ACTION` FOREIGN KEY (`NUMACTION`) REFERENCES `action` (`NUMACTION`);
