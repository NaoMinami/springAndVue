create table spring_session (
    PRIMARY_ID CHAR(36) not null
    , SESSION_ID CHAR(36) not null
    , CREATION_TIME NUMBER(19,0) not null
    , LAST_ACCESS_TIME NUMBER(19,0) not null
    , MAX_INACTIVE_INTERVAL INT not null
    , EXPIRY_TIME NUMBER(19,0) not null
    , PRINCIPAL_NAME VARCHAR(100)
    , CONSTRAINT PK_SPRING_SESSION PRIMARY KEY (PRIMARY_ID)
);

