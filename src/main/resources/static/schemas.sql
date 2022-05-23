create table article
(
    id             uuid                                                                     not null
        primary key,
    author         varchar                                                                  not null,
    title          varchar                                                                  not null,
    thumbnail      varchar,
    published_time timestamp generated always as (NULL::timestamp without time zone) stored not null,
    url            varchar                                                                  not null
);

create table rating
(
    id      uuid not null
        primary key,
    point   real not null,
    content text
);

create table note
(
    id      uuid not null
        primary key,
    content text not null,
    created time not null
);

create table schedule
(
    id         uuid      not null
        primary key,
    title      text      not null,
    status     text      not null,
    location   text,
    note       text,
    tag        text,
    event_time timestamp not null
);

create table role
(
    id    uuid not null
        primary key,
    value text not null
        unique
);

create table "user"
(
    id         uuid                 not null
        primary key,
    name       text                 not null,
    password   text                 not null,
    email      text                 not null,
    occupation text,
    birthday   date,
    avatar     text,
    enabled    boolean default true not null,
    role_id    uuid                 not null
        references role
            on update cascade on delete cascade
);

create table student
(
    id uuid not null
        primary key
        references "user"
            on update cascade on delete cascade
);

create table teacher
(
    id uuid not null
        primary key
        references "user"
            on update cascade on delete cascade
);

create table course
(
    id          uuid    not null
        primary key,
    title       text    not null,
    description text    not null,
    hours       integer not null,
    background  text    not null,
    icon        text,
    price       real    not null,
    teacher_id  uuid
        references teacher
            on update cascade on delete cascade
);

create table student_course
(
    student_id uuid not null
        references student,
    course_id  uuid not null
        references course,
    primary key (student_id, course_id)
);

create table section
(
    id    uuid not null
        primary key,
    title text not null
);

create table lession
(
    id    uuid not null
        primary key
        references section
            on update cascade on delete cascade,
    title text not null
);

create table test_lession
(
    id uuid not null
        constraint test_pkey
            primary key
        constraint test_id_fkey
            references lesson
            on update cascade on delete cascade
);

create table video_lession
(
    id        uuid not null
        primary key
        references lesson,
    video_url text not null
);

create table comment
(
    id          uuid                                                                     not null
        primary key,
    lession     uuid                                                                     not null
        references lesson
            on update cascade on delete cascade,
    create_time timestamp generated always as (NULL::timestamp without time zone) stored not null,
    content     text                                                                     not null,
    reply       uuid
        references comment
            on update cascade on delete cascade
);

create table test
(
    id    integer not null
        constraint test_pkey1
            primary key,
    title text
);

create table "TEST"
(
    id    serial
        primary key,
    title varchar
);

