# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_profile (
  profile_id                    bigint auto_increment not null,
  user_id                       bigint,
  profile_status                integer,
  user_country                  varchar(255) not null,
  user_city                     varchar(100) not null,
  phone_number                  varchar(100) not null,
  user_address                  varchar(255) not null,
  user_profile                  varchar(255) not null,
  user_image_path               varchar(255) not null,
  active                        tinyint(1) default 0,
  creation_date                 datetime(6),
  name                          varchar(255),
  constraint pk_user_profile primary key (profile_id)
);

create table stamp_categories (
  category_id                   bigint auto_increment not null,
  category_name                 varchar(100) not null,
  category_description_url      varchar(100) not null,
  active                        tinyint(1) default 0,
  creation_date                 datetime(6),
  name                          varchar(255),
  constraint pk_stamp_categories primary key (category_id)
);

create table user_orders (
  order_id                      bigint auto_increment not null,
  user_id                       bigint,
  order_status                  varchar(255) not null,
  order_date                    datetime(6),
  name                          varchar(255),
  active                        tinyint(1) default 0,
  creation_date                 datetime(6),
  constraint pk_user_orders primary key (order_id)
);

create table order_shirt (
  order_shirt_id                bigint auto_increment not null,
  order_id                      bigint,
  shirt_id                      bigint,
  shirt_quantity                integer,
  active                        tinyint(1) default 0,
  name                          varchar(255),
  creation_date                 datetime(6),
  constraint pk_order_shirt primary key (order_shirt_id)
);

create table shirt (
  shirt_id                      bigint auto_increment not null,
  user_id                       bigint,
  shirt_size                    varchar(255),
  shirt_color                   varchar(255),
  shirt_sex                     varchar(255),
  shirt_price                   double,
  active                        tinyint(1) default 0,
  name                          varchar(255),
  creation_date                 datetime(6),
  shirt_small_image_path        varchar(255),
  shirt_large_image_path        varchar(255),
  constraint pk_shirt primary key (shirt_id)
);

create table stamps (
  stamp_id                      bigint auto_increment not null,
  user_id                       bigint,
  category_id                   bigint,
  stamp_status                  integer,
  stamp_name                    varchar(20) not null,
  stamp_short_description       varchar(50) not null,
  stamp_long_description        varchar(255) not null,
  stamp_price                   double not null,
  stamp_small_image_path        varchar(255),
  stamp_large_image_path        varchar(255),
  name                          varchar(255),
  active                        tinyint(1) default 0,
  creation_date                 datetime(6),
  constraint pk_stamps primary key (stamp_id)
);

create table stamp_shirt (
  stamp_shirt_id                bigint auto_increment not null,
  shirt_id                      bigint,
  stamp_id                      bigint,
  stamp_size                    varchar(255),
  stamp_location                varchar(255),
  name                          varchar(255),
  creation_date                 datetime(6),
  active                        tinyint(1) default 0,
  constraint pk_stamp_shirt primary key (stamp_shirt_id)
);

create table users (
  user_id                       bigint auto_increment not null,
  user_mail                     varchar(100) not null,
  user_password                 varchar(128) not null,
  user_first_name               varchar(100) not null,
  user_last_name                varchar(100) not null,
  user_type                     integer,
  user_status                   integer,
  active                        tinyint(1) default 0,
  creation_date                 datetime(6),
  name                          varchar(255),
  username                      varchar(255),
  constraint uq_users_user_mail unique (user_mail),
  constraint pk_users primary key (user_id)
);

create table user_billing (
  billing_id                    bigint auto_increment not null,
  user_id                       bigint,
  billing_status                integer,
  user_country                  varchar(255) not null,
  user_city                     varchar(100) not null,
  phone_number                  varchar(100) not null,
  user_address                  varchar(255) not null,
  user_credit_card              varchar(100) not null,
  name_card                     varchar(128) not null,
  expiration_date               datetime(6) not null,
  cvv                           varchar(255) not null,
  constraint pk_user_billing primary key (billing_id)
);

create table user_options (
  user_option_id                bigint auto_increment not null,
  user_type_option              integer(1) not null,
  user_object_option            integer(1) not null,
  user_option_name              varchar(100) not null,
  user_option_url               varchar(100) not null,
  constraint pk_user_options primary key (user_option_id)
);

alter table user_profile add constraint fk_user_profile_user_id foreign key (user_id) references users (user_id) on delete restrict on update restrict;
create index ix_user_profile_user_id on user_profile (user_id);

alter table user_orders add constraint fk_user_orders_user_id foreign key (user_id) references users (user_id) on delete restrict on update restrict;
create index ix_user_orders_user_id on user_orders (user_id);

alter table order_shirt add constraint fk_order_shirt_order_id foreign key (order_id) references user_orders (order_id) on delete restrict on update restrict;
create index ix_order_shirt_order_id on order_shirt (order_id);

alter table order_shirt add constraint fk_order_shirt_shirt_id foreign key (shirt_id) references shirt (shirt_id) on delete restrict on update restrict;
create index ix_order_shirt_shirt_id on order_shirt (shirt_id);

alter table shirt add constraint fk_shirt_user_id foreign key (user_id) references users (user_id) on delete restrict on update restrict;
create index ix_shirt_user_id on shirt (user_id);

alter table stamps add constraint fk_stamps_user_id foreign key (user_id) references users (user_id) on delete restrict on update restrict;
create index ix_stamps_user_id on stamps (user_id);

alter table stamps add constraint fk_stamps_category_id foreign key (category_id) references stamp_categories (category_id) on delete restrict on update restrict;
create index ix_stamps_category_id on stamps (category_id);

alter table stamp_shirt add constraint fk_stamp_shirt_shirt_id foreign key (shirt_id) references shirt (shirt_id) on delete restrict on update restrict;
create index ix_stamp_shirt_shirt_id on stamp_shirt (shirt_id);

alter table stamp_shirt add constraint fk_stamp_shirt_stamp_id foreign key (stamp_id) references stamps (stamp_id) on delete restrict on update restrict;
create index ix_stamp_shirt_stamp_id on stamp_shirt (stamp_id);

alter table user_billing add constraint fk_user_billing_user_id foreign key (user_id) references users (user_id) on delete restrict on update restrict;
create index ix_user_billing_user_id on user_billing (user_id);


# --- !Downs

alter table user_profile drop foreign key fk_user_profile_user_id;
drop index ix_user_profile_user_id on user_profile;

alter table user_orders drop foreign key fk_user_orders_user_id;
drop index ix_user_orders_user_id on user_orders;

alter table order_shirt drop foreign key fk_order_shirt_order_id;
drop index ix_order_shirt_order_id on order_shirt;

alter table order_shirt drop foreign key fk_order_shirt_shirt_id;
drop index ix_order_shirt_shirt_id on order_shirt;

alter table shirt drop foreign key fk_shirt_user_id;
drop index ix_shirt_user_id on shirt;

alter table stamps drop foreign key fk_stamps_user_id;
drop index ix_stamps_user_id on stamps;

alter table stamps drop foreign key fk_stamps_category_id;
drop index ix_stamps_category_id on stamps;

alter table stamp_shirt drop foreign key fk_stamp_shirt_shirt_id;
drop index ix_stamp_shirt_shirt_id on stamp_shirt;

alter table stamp_shirt drop foreign key fk_stamp_shirt_stamp_id;
drop index ix_stamp_shirt_stamp_id on stamp_shirt;

alter table user_billing drop foreign key fk_user_billing_user_id;
drop index ix_user_billing_user_id on user_billing;

drop table if exists user_profile;

drop table if exists stamp_categories;

drop table if exists user_orders;

drop table if exists order_shirt;

drop table if exists shirt;

drop table if exists stamps;

drop table if exists stamp_shirt;

drop table if exists users;

drop table if exists user_billing;

drop table if exists user_options;

