create table r_media_categorie (media_id bigint not null, categorie_id bigint not null)
create table t_categorie (identifiant bigint not null auto_increment, libelle varchar(100), primary key (identifiant))
create table t_exemplaire (identifiant bigint not null auto_increment, etat varchar(255), reference varchar(20), media_id bigint, primary key (identifiant))
create table t_media (discriminant varchar(31) not null, identifiant bigint not null auto_increment, auteur varchar(100), titre varchar(100), version datetime, isbn varchar(12) not null, pages integer, primary key (identifiant))
alter table t_categorie add constraint UK_d03m87dpbrx1oexs7vocldbc4 unique (libelle)
alter table t_exemplaire add constraint UK_dphvhf4vy2ua0xtx0xjm6gd92 unique (reference)
alter table t_media add constraint UK_ntngtjyxyu8u8w388ybcj58nb unique (titre)
alter table t_media add constraint UK_625w614udjfa4yk2fvu7i03c5 unique (isbn)
alter table r_media_categorie add constraint FK_1behdy0ffejlehuvkda7o9hkf foreign key (categorie_id) references t_categorie (identifiant)
alter table r_media_categorie add constraint FK_r2x17ao4ooe32ajilvjfcu96d foreign key (media_id) references t_media (identifiant)
alter table t_exemplaire add constraint FK_f44u4kjwkd8amfyc8r59k39fq foreign key (media_id) references t_media (identifiant)