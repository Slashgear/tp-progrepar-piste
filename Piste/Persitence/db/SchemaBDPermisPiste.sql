/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g                            */
/* Date de création :  11/05/2015 17:20:14                      */
/*==============================================================*/

CREATE DATABASE IF NOT EXISTS permispiste
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE permispiste;

/*==============================================================*/
/* Table : ACTION                                               */
/*==============================================================*/
CREATE TABLE ACTION
(
  NUMACTION     INTEGER NOT NULL AUTO_INCREMENT,
  ACT_NUMACTION INTEGER,
  LIBACTION     CHAR(25),
  SCOREMIN      INTEGER,
  CONSTRAINT PK_ACTION PRIMARY KEY (NUMACTION)
);

/*==============================================================*/
/* Index : A_POUR_SUCCESSEUR_FK                                 */
/*==============================================================*/
CREATE INDEX A_POUR_SUCCESSEUR_FK ON ACTION (
  ACT_NUMACTION ASC
);

/*==============================================================*/
/* Table : APPARTIENT                                           */
/*==============================================================*/
CREATE TABLE APPARTIENT
(
  NUMJEU    INTEGER NOT NULL,
  NUMACTION INTEGER NOT NULL,
  CONSTRAINT PK_APPARTIENT PRIMARY KEY (NUMJEU, NUMACTION)
);

/*==============================================================*/
/* Index : APPARTIENT_FK                                        */
/*==============================================================*/
CREATE INDEX APPARTIENT_FK ON APPARTIENT (
  NUMJEU ASC
);

/*==============================================================*/
/* Index : APPARTIENT2_FK                                       */
/*==============================================================*/
CREATE INDEX APPARTIENT2_FK ON APPARTIENT (
  NUMACTION ASC
);

/*==============================================================*/
/* Table : APPRENANT                                            */
/*==============================================================*/
CREATE TABLE APPRENANT
(
  NUMAPPRENANT    INTEGER NOT NULL AUTO_INCREMENT,
  NOMAPPRENANT    CHAR(25),
  PRENOMAPPRENANT CHAR(25),
  CONSTRAINT PK_APPRENANT PRIMARY KEY (NUMAPPRENANT)
);

/*==============================================================*/
/* Table : CALENDRIER                                           */
/*==============================================================*/
CREATE TABLE CALENDRIER
(
  DATEJOUR DATE NOT NULL,
  CONSTRAINT PK_CALENDRIER PRIMARY KEY (DATEJOUR)
);

/*==============================================================*/
/* Table : EST_ASSOCIE                                          */
/*==============================================================*/
CREATE TABLE EST_ASSOCIE
(
  NUMACTION   INTEGER NOT NULL,
  NUMOBJECTIF INTEGER NOT NULL,
  CONSTRAINT PK_EST_ASSOCIE PRIMARY KEY (NUMACTION, NUMOBJECTIF)
);

/*==============================================================*/
/* Index : EST_ASSOCIE_FK                                       */
/*==============================================================*/
CREATE INDEX EST_ASSOCIE_FK ON EST_ASSOCIE (
  NUMACTION ASC
);

/*==============================================================*/
/* Index : EST_ASSOCIE2_FK                                      */
/*==============================================================*/
CREATE INDEX EST_ASSOCIE2_FK ON EST_ASSOCIE (
  NUMOBJECTIF ASC
);

/*==============================================================*/
/* Table : FIXE                                                 */
/*==============================================================*/
CREATE TABLE FIXE
(
  NUMMISSION  INTEGER NOT NULL,
  NUMOBJECTIF INTEGER NOT NULL,
  CONSTRAINT PK_FIXE PRIMARY KEY (NUMMISSION, NUMOBJECTIF)
);

/*==============================================================*/
/* Index : FIXE_FK                                              */
/*==============================================================*/
CREATE INDEX FIXE_FK ON FIXE (
  NUMMISSION ASC
);

/*==============================================================*/
/* Index : FIXE2_FK                                             */
/*==============================================================*/
CREATE INDEX FIXE2_FK ON FIXE (
  NUMOBJECTIF ASC
);

/*==============================================================*/
/* Table : INDICATEUR                                           */
/*==============================================================*/
CREATE TABLE INDICATEUR
(
  NUMINDIC  INTEGER NOT NULL AUTO_INCREMENT,
  NUMACTION INTEGER NOT NULL,
  LIBINDIC  CHAR(20),
  POIDS     INTEGER,
  CONSTRAINT PK_INDICATEUR PRIMARY KEY (NUMINDIC)
);

/*==============================================================*/
/* Index : EST_VALORISE_FK                                      */
/*==============================================================*/
CREATE INDEX EST_VALORISE_FK ON INDICATEUR (
  NUMACTION ASC
);

/*==============================================================*/
/* Table : JEU                                                  */
/*==============================================================*/
CREATE TABLE JEU
(
  NUMJEU     INTEGER NOT NULL AUTO_INCREMENT,
  LIBELLEJEU CHAR(25),
  CONSTRAINT PK_JEU PRIMARY KEY (NUMJEU)
);

/*==============================================================*/
/* Table : MISSION                                              */
/*==============================================================*/
CREATE TABLE MISSION
(
  NUMMISSION INTEGER NOT NULL AUTO_INCREMENT,
  NUMJEU     INTEGER NOT NULL,
  LIBMISSION CHAR(25),
  CONSTRAINT PK_MISSION PRIMARY KEY (NUMMISSION)
);

/*==============================================================*/
/* Index : SE_COMPOSE_FK                                        */
/*==============================================================*/
CREATE INDEX SE_COMPOSE_FK ON MISSION (
  NUMJEU ASC
);

/*==============================================================*/
/* Table : OBJECTIF                                             */
/*==============================================================*/
CREATE TABLE OBJECTIF
(
  NUMOBJECTIF INTEGER NOT NULL AUTO_INCREMENT,
  LIBOBECTIF  CHAR(25),
  CONSTRAINT PK_OBJECTIF PRIMARY KEY (NUMOBJECTIF)
);

/*==============================================================*/
/* Table : OBTIENT                                              */
/*==============================================================*/
CREATE TABLE OBTIENT
(
  NUMAPPRENANT INTEGER NOT NULL,
  DATEJOUR     DATE    NOT NULL,
  NUMACTION    INTEGER NOT NULL,
  VALEURDEBUT  INTEGER,
  VALEURFIN    INTEGER,
  CONSTRAINT PK_OBTIENT PRIMARY KEY (NUMAPPRENANT, DATEJOUR, NUMACTION)
);

/*==============================================================*/
/* Index : OBTIENT_FK                                           */
/*==============================================================*/
CREATE INDEX OBTIENT_FK ON OBTIENT (
  NUMAPPRENANT ASC
);

/*==============================================================*/
/* Index : OBTIENT3_FK                                          */
/*==============================================================*/
CREATE INDEX OBTIENT3_FK ON OBTIENT (
  DATEJOUR ASC
);

/*==============================================================*/
/* Index : OBTIENT3_FK2                                         */
/*==============================================================*/
CREATE INDEX OBTIENT3_FK2 ON OBTIENT (
  NUMACTION ASC
);

/*==============================================================*/
/* Table : POSSEDE                                              */
/*==============================================================*/
CREATE TABLE POSSEDE
(
  NUMACTION INTEGER NOT NULL,
  NUMREGLE  INTEGER NOT NULL,
  CONSTRAINT PK_POSSEDE PRIMARY KEY (NUMACTION, NUMREGLE)
);

/*==============================================================*/
/* Index : POSSEDE_FK                                           */
/*==============================================================*/
CREATE INDEX POSSEDE_FK ON POSSEDE (
  NUMACTION ASC
);

/*==============================================================*/
/* Index : POSSEDE2_FK                                          */
/*==============================================================*/
CREATE INDEX POSSEDE2_FK ON POSSEDE (
  NUMREGLE ASC
);

/*==============================================================*/
/* Table : REGLE                                                */
/*==============================================================*/
CREATE TABLE REGLE
(
  NUMREGLE INTEGER NOT NULL AUTO_INCREMENT,
  LIBREGLE CHAR(25),
  SCOREMIN INTEGER,
  CONSTRAINT PK_REGLE PRIMARY KEY (NUMREGLE)
);

ALTER TABLE ACTION
ADD CONSTRAINT FK_ACTION_A_POUR_SU_ACTION FOREIGN KEY (ACT_NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE APPARTIENT
ADD CONSTRAINT FK_APPARTIE_APPARTIEN_JEU FOREIGN KEY (NUMJEU)
REFERENCES JEU (NUMJEU);

ALTER TABLE APPARTIENT
ADD CONSTRAINT FK_APPARTIE_APPARTIEN_ACTION FOREIGN KEY (NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE EST_ASSOCIE
ADD CONSTRAINT FK_EST_ASSO_EST_ASSOC_ACTION FOREIGN KEY (NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE EST_ASSOCIE
ADD CONSTRAINT FK_EST_ASSO_EST_ASSOC_OBJECTIF FOREIGN KEY (NUMOBJECTIF)
REFERENCES OBJECTIF (NUMOBJECTIF);

ALTER TABLE FIXE
ADD CONSTRAINT FK_FIXE_FIXE_MISSION FOREIGN KEY (NUMMISSION)
REFERENCES MISSION (NUMMISSION);

ALTER TABLE FIXE
ADD CONSTRAINT FK_FIXE_FIXE2_OBJECTIF FOREIGN KEY (NUMOBJECTIF)
REFERENCES OBJECTIF (NUMOBJECTIF);

ALTER TABLE INDICATEUR
ADD CONSTRAINT FK_INDICATE_EST_VALOR_ACTION FOREIGN KEY (NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE MISSION
ADD CONSTRAINT FK_MISSION_SE_COMPOS_JEU FOREIGN KEY (NUMJEU)
REFERENCES JEU (NUMJEU);

ALTER TABLE OBTIENT
ADD CONSTRAINT FK_OBTIENT_OBTIENT_APPRENAN FOREIGN KEY (NUMAPPRENANT)
REFERENCES APPRENANT (NUMAPPRENANT);

ALTER TABLE OBTIENT
ADD CONSTRAINT FK_OBTIENT_OBTIENT2_CALENDRI FOREIGN KEY (DATEJOUR)
REFERENCES CALENDRIER (DATEJOUR);

ALTER TABLE OBTIENT
ADD CONSTRAINT FK_OBTIENT_OBTIENT3_ACTION FOREIGN KEY (NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE POSSEDE
ADD CONSTRAINT FK_POSSEDE_POSSEDE_ACTION FOREIGN KEY (NUMACTION)
REFERENCES ACTION (NUMACTION);

ALTER TABLE POSSEDE
ADD CONSTRAINT FK_POSSEDE_POSSEDE2_REGLE FOREIGN KEY (NUMREGLE)
REFERENCES REGLE (NUMREGLE);