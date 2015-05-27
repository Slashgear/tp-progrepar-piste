/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g                            */
/* Date de création :  11/05/2015 17:20:14                      */
/*==============================================================*/

create database if not exists permispiste 
   DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

use permispiste;

/*==============================================================*/
/* Table : ACTION                                               */
/*==============================================================*/
create table ACTION 
(
   NUMACTION            INTEGER              not null,
   ACT_NUMACTION        INTEGER,
   LIBACTION            CHAR(25),
   SCOREMIN             INTEGER,
   constraint PK_ACTION primary key (NUMACTION)
);

/*==============================================================*/
/* Index : A_POUR_SUCCESSEUR_FK                                 */
/*==============================================================*/
create index A_POUR_SUCCESSEUR_FK on ACTION (
   ACT_NUMACTION ASC
);

/*==============================================================*/
/* Table : APPARTIENT                                           */
/*==============================================================*/
create table APPARTIENT 
(
   NUMJEU               INTEGER              not null,
   NUMACTION            INTEGER              not null,
   constraint PK_APPARTIENT primary key (NUMJEU, NUMACTION)
);

/*==============================================================*/
/* Index : APPARTIENT_FK                                        */
/*==============================================================*/
create index APPARTIENT_FK on APPARTIENT (
   NUMJEU ASC
);

/*==============================================================*/
/* Index : APPARTIENT2_FK                                       */
/*==============================================================*/
create index APPARTIENT2_FK on APPARTIENT (
   NUMACTION ASC
);

/*==============================================================*/
/* Table : APPRENANT                                            */
/*==============================================================*/
create table APPRENANT 
(
   NUMAPPRENANT         INTEGER              not null,
   NOMAPPRENANT         CHAR(25),
   PRENOMAPPRENANT      CHAR(25),
   constraint PK_APPRENANT primary key (NUMAPPRENANT)
);

/*==============================================================*/
/* Table : CALENDRIER                                           */
/*==============================================================*/
create table CALENDRIER 
(
   DATEJOUR             DATE                 not null,
   constraint PK_CALENDRIER primary key (DATEJOUR)
);

/*==============================================================*/
/* Table : EST_ASSOCIE                                          */
/*==============================================================*/
create table EST_ASSOCIE 
(
   NUMACTION            INTEGER              not null,
   NUMOBJECTIF          INTEGER              not null,
   constraint PK_EST_ASSOCIE primary key (NUMACTION, NUMOBJECTIF)
);

/*==============================================================*/
/* Index : EST_ASSOCIE_FK                                       */
/*==============================================================*/
create index EST_ASSOCIE_FK on EST_ASSOCIE (
   NUMACTION ASC
);

/*==============================================================*/
/* Index : EST_ASSOCIE2_FK                                      */
/*==============================================================*/
create index EST_ASSOCIE2_FK on EST_ASSOCIE (
   NUMOBJECTIF ASC
);

/*==============================================================*/
/* Table : FIXE                                                 */
/*==============================================================*/
create table FIXE 
(
   NUMMISSION           INTEGER              not null,
   NUMOBJECTIF          INTEGER              not null,
   constraint PK_FIXE primary key (NUMMISSION, NUMOBJECTIF)
);

/*==============================================================*/
/* Index : FIXE_FK                                              */
/*==============================================================*/
create index FIXE_FK on FIXE (
   NUMMISSION ASC
);

/*==============================================================*/
/* Index : FIXE2_FK                                             */
/*==============================================================*/
create index FIXE2_FK on FIXE (
   NUMOBJECTIF ASC
);

/*==============================================================*/
/* Table : INDICATEUR                                           */
/*==============================================================*/
create table INDICATEUR 
(
   NUMINDIC             INTEGER              not null,
   NUMACTION            INTEGER              not null,
   LIBINDIC             CHAR(20),
   POIDS                INTEGER,
   constraint PK_INDICATEUR primary key (NUMINDIC)
);

/*==============================================================*/
/* Index : EST_VALORISE_FK                                      */
/*==============================================================*/
create index EST_VALORISE_FK on INDICATEUR (
   NUMACTION ASC
);

/*==============================================================*/
/* Table : JEU                                                  */
/*==============================================================*/
create table JEU 
(
   NUMJEU               INTEGER              not null,
   LIBELLEJEU           CHAR(25),
   constraint PK_JEU primary key (NUMJEU)
);

/*==============================================================*/
/* Table : MISSION                                              */
/*==============================================================*/
create table MISSION 
(
   NUMMISSION           INTEGER              not null,
   NUMJEU               INTEGER              not null,
   LIBMISSION           CHAR(25),
   constraint PK_MISSION primary key (NUMMISSION)
);

/*==============================================================*/
/* Index : SE_COMPOSE_FK                                        */
/*==============================================================*/
create index SE_COMPOSE_FK on MISSION (
   NUMJEU ASC
);

/*==============================================================*/
/* Table : OBJECTIF                                             */
/*==============================================================*/
create table OBJECTIF 
(
   NUMOBJECTIF          INTEGER              not null,
   LIBOBECTIF           CHAR(25),
   constraint PK_OBJECTIF primary key (NUMOBJECTIF)
);

/*==============================================================*/
/* Table : OBTIENT                                              */
/*==============================================================*/
create table OBTIENT 
(
   NUMAPPRENANT         INTEGER              not null,
   DATEJOUR             DATE                 not null,
   NUMACTION            INTEGER              not null,
   VALEURDEBUT          INTEGER,
   VALEURFIN            INTEGER,
   constraint PK_OBTIENT primary key (NUMAPPRENANT, DATEJOUR, NUMACTION)
);

/*==============================================================*/
/* Index : OBTIENT_FK                                           */
/*==============================================================*/
create index OBTIENT_FK on OBTIENT (
   NUMAPPRENANT ASC
);

/*==============================================================*/
/* Index : OBTIENT3_FK                                          */
/*==============================================================*/
create index OBTIENT3_FK on OBTIENT (
   DATEJOUR ASC
);

/*==============================================================*/
/* Index : OBTIENT3_FK2                                         */
/*==============================================================*/
create index OBTIENT3_FK2 on OBTIENT (
   NUMACTION ASC
);

/*==============================================================*/
/* Table : POSSEDE                                              */
/*==============================================================*/
create table POSSEDE 
(
   NUMACTION            INTEGER              not null,
   NUMREGLE             INTEGER              not null,
   constraint PK_POSSEDE primary key (NUMACTION, NUMREGLE)
);

/*==============================================================*/
/* Index : POSSEDE_FK                                           */
/*==============================================================*/
create index POSSEDE_FK on POSSEDE (
   NUMACTION ASC
);

/*==============================================================*/
/* Index : POSSEDE2_FK                                          */
/*==============================================================*/
create index POSSEDE2_FK on POSSEDE (
   NUMREGLE ASC
);

/*==============================================================*/
/* Table : REGLE                                                */
/*==============================================================*/
create table REGLE 
(
   NUMREGLE             INTEGER              not null,
   LIBREGLE             CHAR(25),
   SCOREMIN             INTEGER,
   constraint PK_REGLE primary key (NUMREGLE)
);

alter table ACTION
   add constraint FK_ACTION_A_POUR_SU_ACTION foreign key (ACT_NUMACTION)
      references ACTION (NUMACTION);

alter table APPARTIENT
   add constraint FK_APPARTIE_APPARTIEN_JEU foreign key (NUMJEU)
      references JEU (NUMJEU);

alter table APPARTIENT
   add constraint FK_APPARTIE_APPARTIEN_ACTION foreign key (NUMACTION)
      references ACTION (NUMACTION);

alter table EST_ASSOCIE
   add constraint FK_EST_ASSO_EST_ASSOC_ACTION foreign key (NUMACTION)
      references ACTION (NUMACTION);

alter table EST_ASSOCIE
   add constraint FK_EST_ASSO_EST_ASSOC_OBJECTIF foreign key (NUMOBJECTIF)
      references OBJECTIF (NUMOBJECTIF);

alter table FIXE
   add constraint FK_FIXE_FIXE_MISSION foreign key (NUMMISSION)
      references MISSION (NUMMISSION);

alter table FIXE
   add constraint FK_FIXE_FIXE2_OBJECTIF foreign key (NUMOBJECTIF)
      references OBJECTIF (NUMOBJECTIF);

alter table INDICATEUR
   add constraint FK_INDICATE_EST_VALOR_ACTION foreign key (NUMACTION)
      references ACTION (NUMACTION);

alter table MISSION
   add constraint FK_MISSION_SE_COMPOS_JEU foreign key (NUMJEU)
      references JEU (NUMJEU);

alter table OBTIENT
   add constraint FK_OBTIENT_OBTIENT_APPRENAN foreign key (NUMAPPRENANT)
      references APPRENANT (NUMAPPRENANT);

alter table OBTIENT
   add constraint FK_OBTIENT_OBTIENT2_CALENDRI foreign key (DATEJOUR)
      references CALENDRIER (DATEJOUR);

alter table OBTIENT
   add constraint FK_OBTIENT_OBTIENT3_ACTION foreign key (NUMACTION)
      references ACTION (NUMACTION);

alter table POSSEDE
   add constraint FK_POSSEDE_POSSEDE_ACTION foreign key (NUMACTION)
      references ACTION (NUMACTION);

alter table POSSEDE
   add constraint FK_POSSEDE_POSSEDE2_REGLE foreign key (NUMREGLE)
      references REGLE (NUMREGLE);
