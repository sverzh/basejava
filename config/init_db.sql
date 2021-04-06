create table resume
(
  uuid      char(36) not null primary key,
  full_name text     NOT NULL
);

alter table resume
  owner to postgres;

create table contact
(
  id          serial,
  resume_uuid char(36) not null references resume on delete cascade,
  type        text     not null,
  value       text     not null
);

create unique index contact_uuid_type_index
  on contact (resume_uuid, type);

create table section
(
  id          serial not null
    constraint section_pk
      primary key,
  type        text   not null,
  value       text   not null,
  resume_uuid char(36)
    constraint section_resume_uuid_fk
      references resume
      on delete cascade
);

alter table section
  owner to postgres;

create unique index section_id_uindex
  on section (id);



