# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table administrateur (
  id                            bigint auto_increment not null,
  prenom                        varchar(255),
  nom                           varchar(255),
  membre_id                     bigint,
  constraint uq_administrateur_membre_id unique (membre_id),
  constraint pk_administrateur primary key (id)
);

create table amitie (
  id                            bigint auto_increment not null,
  membre_source_id              bigint,
  membre_cible_id               bigint,
  accepte                       tinyint(1) default 0,
  constraint pk_amitie primary key (id)
);

create table commentaire (
  id                            bigint auto_increment not null,
  contenu                       longtext,
  publication_id                bigint,
  membre_id                     bigint,
  constraint pk_commentaire primary key (id)
);

create table competence (
  id                            bigint auto_increment not null,
  description                   varchar(255),
  constraint pk_competence primary key (id)
);

create table domaine (
  id                            bigint auto_increment not null,
  libelle                       varchar(255),
  etat                          tinyint(1) default 0,
  constraint pk_domaine primary key (id)
);

create table domaine_publication (
  domaine_id                    bigint not null,
  publication_id                bigint not null,
  constraint pk_domaine_publication primary key (domaine_id,publication_id)
);

create table entreprise (
  id                            bigint auto_increment not null,
  raison_social                 varchar(255),
  date_creation                 datetime(6),
  domaine                       varchar(255),
  membre_id                     bigint,
  constraint uq_entreprise_membre_id unique (membre_id),
  constraint pk_entreprise primary key (id)
);

create table experience (
  id                            bigint auto_increment not null,
  entreprise                    varchar(255),
  lieu                          varchar(255),
  titre                         varchar(255),
  moi_debut                     varchar(255),
  moi_fin                       varchar(255),
  annee_debut                   bigint,
  annee_fin                     bigint,
  etat                          tinyint(1) default 0,
  membre_id                     bigint,
  constraint pk_experience primary key (id)
);

create table formation (
  id                            bigint auto_increment not null,
  type                          varchar(255),
  etablissement                 varchar(255),
  annee_debut                   bigint,
  annee_fin                     bigint,
  diplome                       varchar(255),
  resultat                      varchar(255),
  particulier_id                bigint,
  constraint pk_formation primary key (id)
);

create table groupe (
  id                            bigint auto_increment not null,
  nom_groupe                    varchar(255),
  date_creation                 datetime(6),
  createur_id                   bigint,
  constraint pk_groupe primary key (id)
);

create table image (
  id                            bigint auto_increment not null,
  nom                           varchar(255),
  chemin                        varchar(255),
  profil                        tinyint(1) default 0,
  membre_id                     bigint,
  constraint pk_image primary key (id)
);

create table langue (
  id                            bigint auto_increment not null,
  libele                        varchar(255),
  constraint pk_langue primary key (id)
);

create table loisir (
  id                            bigint auto_increment not null,
  libele                        varchar(255),
  constraint pk_loisir primary key (id)
);

create table membre (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  mot_de_passe                  varchar(255),
  adresse                       varchar(255),
  telephone                     varchar(255),
  siteweb                       varchar(255),
  etat                          integer,
  salt                          varchar(255),
  date_creation                 datetime(6),
  type                          varchar(255),
  constraint pk_membre primary key (id)
);

create table membre_groupe (
  membre_id                     bigint not null,
  groupe_id                     bigint not null,
  constraint pk_membre_groupe primary key (membre_id,groupe_id)
);

create table membre_competence (
  membre_id                     bigint not null,
  competence_id                 bigint not null,
  constraint pk_membre_competence primary key (membre_id,competence_id)
);

create table message (
  id                            bigint auto_increment not null,
  contenu                       varchar(255),
  expediteur_id                 bigint,
  destinataire_id               bigint,
  constraint pk_message primary key (id)
);

create table particulier (
  id                            bigint auto_increment not null,
  nom                           varchar(255),
  prenom                        varchar(255),
  date_de_naissance             datetime(6),
  lieu_de_naissance             varchar(255),
  membre_id                     bigint,
  constraint uq_particulier_membre_id unique (membre_id),
  constraint pk_particulier primary key (id)
);

create table particulier_loisir (
  particulier_id                bigint not null,
  loisir_id                     bigint not null,
  constraint pk_particulier_loisir primary key (particulier_id,loisir_id)
);

create table particulier_langue (
  particulier_id                bigint not null,
  langue_id                     bigint not null,
  constraint pk_particulier_langue primary key (particulier_id,langue_id)
);

create table publication (
  type                          varchar(31) not null,
  id                            bigint auto_increment not null,
  titre                         varchar(255),
  contenu                       longtext,
  date_publication              datetime(6),
  url_image                     varchar(255),
  nom_image                     varchar(255),
  membre_id                     bigint,
  publie                        tinyint(1) default 0,
  etat                          tinyint(1) default 0,
  constraint pk_publication primary key (id)
);

create table vue_commentaire (
  id                            bigint auto_increment not null,
  membre_id                     bigint,
  commentaire_id                bigint,
  vue                           integer,
  jaime                         integer,
  constraint pk_vue_commentaire primary key (id)
);

create table vue_publication (
  id                            bigint auto_increment not null,
  membre_id                     bigint,
  publication_id                bigint,
  vue                           integer,
  jaime                         integer,
  constraint pk_vue_publication primary key (id)
);

alter table administrateur add constraint fk_administrateur_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;

alter table amitie add constraint fk_amitie_membre_source_id foreign key (membre_source_id) references membre (id) on delete restrict on update restrict;
create index ix_amitie_membre_source_id on amitie (membre_source_id);

alter table amitie add constraint fk_amitie_membre_cible_id foreign key (membre_cible_id) references membre (id) on delete restrict on update restrict;
create index ix_amitie_membre_cible_id on amitie (membre_cible_id);

alter table commentaire add constraint fk_commentaire_publication_id foreign key (publication_id) references publication (id) on delete restrict on update restrict;
create index ix_commentaire_publication_id on commentaire (publication_id);

alter table commentaire add constraint fk_commentaire_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_commentaire_membre_id on commentaire (membre_id);

alter table domaine_publication add constraint fk_domaine_publication_domaine foreign key (domaine_id) references domaine (id) on delete restrict on update restrict;
create index ix_domaine_publication_domaine on domaine_publication (domaine_id);

alter table domaine_publication add constraint fk_domaine_publication_publication foreign key (publication_id) references publication (id) on delete restrict on update restrict;
create index ix_domaine_publication_publication on domaine_publication (publication_id);

alter table entreprise add constraint fk_entreprise_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;

alter table experience add constraint fk_experience_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_experience_membre_id on experience (membre_id);

alter table formation add constraint fk_formation_particulier_id foreign key (particulier_id) references particulier (id) on delete restrict on update restrict;
create index ix_formation_particulier_id on formation (particulier_id);

alter table groupe add constraint fk_groupe_createur_id foreign key (createur_id) references membre (id) on delete restrict on update restrict;
create index ix_groupe_createur_id on groupe (createur_id);

alter table image add constraint fk_image_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_image_membre_id on image (membre_id);

alter table membre_groupe add constraint fk_membre_groupe_membre foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_membre_groupe_membre on membre_groupe (membre_id);

alter table membre_groupe add constraint fk_membre_groupe_groupe foreign key (groupe_id) references groupe (id) on delete restrict on update restrict;
create index ix_membre_groupe_groupe on membre_groupe (groupe_id);

alter table membre_competence add constraint fk_membre_competence_membre foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_membre_competence_membre on membre_competence (membre_id);

alter table membre_competence add constraint fk_membre_competence_competence foreign key (competence_id) references competence (id) on delete restrict on update restrict;
create index ix_membre_competence_competence on membre_competence (competence_id);

alter table message add constraint fk_message_expediteur_id foreign key (expediteur_id) references membre (id) on delete restrict on update restrict;
create index ix_message_expediteur_id on message (expediteur_id);

alter table message add constraint fk_message_destinataire_id foreign key (destinataire_id) references membre (id) on delete restrict on update restrict;
create index ix_message_destinataire_id on message (destinataire_id);

alter table particulier add constraint fk_particulier_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;

alter table particulier_loisir add constraint fk_particulier_loisir_particulier foreign key (particulier_id) references particulier (id) on delete restrict on update restrict;
create index ix_particulier_loisir_particulier on particulier_loisir (particulier_id);

alter table particulier_loisir add constraint fk_particulier_loisir_loisir foreign key (loisir_id) references loisir (id) on delete restrict on update restrict;
create index ix_particulier_loisir_loisir on particulier_loisir (loisir_id);

alter table particulier_langue add constraint fk_particulier_langue_particulier foreign key (particulier_id) references particulier (id) on delete restrict on update restrict;
create index ix_particulier_langue_particulier on particulier_langue (particulier_id);

alter table particulier_langue add constraint fk_particulier_langue_langue foreign key (langue_id) references langue (id) on delete restrict on update restrict;
create index ix_particulier_langue_langue on particulier_langue (langue_id);

alter table publication add constraint fk_publication_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_publication_membre_id on publication (membre_id);

alter table vue_commentaire add constraint fk_vue_commentaire_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_vue_commentaire_membre_id on vue_commentaire (membre_id);

alter table vue_commentaire add constraint fk_vue_commentaire_commentaire_id foreign key (commentaire_id) references commentaire (id) on delete restrict on update restrict;
create index ix_vue_commentaire_commentaire_id on vue_commentaire (commentaire_id);

alter table vue_publication add constraint fk_vue_publication_membre_id foreign key (membre_id) references membre (id) on delete restrict on update restrict;
create index ix_vue_publication_membre_id on vue_publication (membre_id);

alter table vue_publication add constraint fk_vue_publication_publication_id foreign key (publication_id) references publication (id) on delete restrict on update restrict;
create index ix_vue_publication_publication_id on vue_publication (publication_id);


# --- !Downs

alter table administrateur drop foreign key fk_administrateur_membre_id;

alter table amitie drop foreign key fk_amitie_membre_source_id;
drop index ix_amitie_membre_source_id on amitie;

alter table amitie drop foreign key fk_amitie_membre_cible_id;
drop index ix_amitie_membre_cible_id on amitie;

alter table commentaire drop foreign key fk_commentaire_publication_id;
drop index ix_commentaire_publication_id on commentaire;

alter table commentaire drop foreign key fk_commentaire_membre_id;
drop index ix_commentaire_membre_id on commentaire;

alter table domaine_publication drop foreign key fk_domaine_publication_domaine;
drop index ix_domaine_publication_domaine on domaine_publication;

alter table domaine_publication drop foreign key fk_domaine_publication_publication;
drop index ix_domaine_publication_publication on domaine_publication;

alter table entreprise drop foreign key fk_entreprise_membre_id;

alter table experience drop foreign key fk_experience_membre_id;
drop index ix_experience_membre_id on experience;

alter table formation drop foreign key fk_formation_particulier_id;
drop index ix_formation_particulier_id on formation;

alter table groupe drop foreign key fk_groupe_createur_id;
drop index ix_groupe_createur_id on groupe;

alter table image drop foreign key fk_image_membre_id;
drop index ix_image_membre_id on image;

alter table membre_groupe drop foreign key fk_membre_groupe_membre;
drop index ix_membre_groupe_membre on membre_groupe;

alter table membre_groupe drop foreign key fk_membre_groupe_groupe;
drop index ix_membre_groupe_groupe on membre_groupe;

alter table membre_competence drop foreign key fk_membre_competence_membre;
drop index ix_membre_competence_membre on membre_competence;

alter table membre_competence drop foreign key fk_membre_competence_competence;
drop index ix_membre_competence_competence on membre_competence;

alter table message drop foreign key fk_message_expediteur_id;
drop index ix_message_expediteur_id on message;

alter table message drop foreign key fk_message_destinataire_id;
drop index ix_message_destinataire_id on message;

alter table particulier drop foreign key fk_particulier_membre_id;

alter table particulier_loisir drop foreign key fk_particulier_loisir_particulier;
drop index ix_particulier_loisir_particulier on particulier_loisir;

alter table particulier_loisir drop foreign key fk_particulier_loisir_loisir;
drop index ix_particulier_loisir_loisir on particulier_loisir;

alter table particulier_langue drop foreign key fk_particulier_langue_particulier;
drop index ix_particulier_langue_particulier on particulier_langue;

alter table particulier_langue drop foreign key fk_particulier_langue_langue;
drop index ix_particulier_langue_langue on particulier_langue;

alter table publication drop foreign key fk_publication_membre_id;
drop index ix_publication_membre_id on publication;

alter table vue_commentaire drop foreign key fk_vue_commentaire_membre_id;
drop index ix_vue_commentaire_membre_id on vue_commentaire;

alter table vue_commentaire drop foreign key fk_vue_commentaire_commentaire_id;
drop index ix_vue_commentaire_commentaire_id on vue_commentaire;

alter table vue_publication drop foreign key fk_vue_publication_membre_id;
drop index ix_vue_publication_membre_id on vue_publication;

alter table vue_publication drop foreign key fk_vue_publication_publication_id;
drop index ix_vue_publication_publication_id on vue_publication;

drop table if exists administrateur;

drop table if exists amitie;

drop table if exists commentaire;

drop table if exists competence;

drop table if exists domaine;

drop table if exists domaine_publication;

drop table if exists entreprise;

drop table if exists experience;

drop table if exists formation;

drop table if exists groupe;

drop table if exists image;

drop table if exists langue;

drop table if exists loisir;

drop table if exists membre;

drop table if exists membre_groupe;

drop table if exists membre_competence;

drop table if exists message;

drop table if exists particulier;

drop table if exists particulier_loisir;

drop table if exists particulier_langue;

drop table if exists publication;

drop table if exists vue_commentaire;

drop table if exists vue_publication;

