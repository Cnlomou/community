create table TB_USER
(
    ID         INTEGER auto_increment,
    ACCOUNT_ID int
        constraint TB_USER_ACCOUNT_ID_UINDEX
            unique,
    NAME       VARCHAR(50),
    TOKEN      CHAR(36)
        constraint TB_USER_TOKEN_UINDEX
            unique,
    CREATE_AT  TIMESTAMP,
    UPDATE_AT  TIMESTAMP,
    constraint TB_USER_PK
        primary key (ID)
);

