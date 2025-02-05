CREATE TABLE users
(
    id        varchar2(64) NOT NULL primary key,
    username  varchar2(32),
    firstname varchar2(32),
    lastname  varchar2(32)
);

CREATE TABLE documents
(
    id          varchar2(64) NOT NULL primary key,
    document_id varchar2(64) NOT NULL
);

CREATE TABLE user_permissions
(
    user_id            varchar2(64) NOT NULL,
    document_id        varchar2(64) NOT NULL,
    user_permission_id varchar2(64) NOT NULL,
    permission_type    varchar2(32),
    CONSTRAINT document_fk FOREIGN KEY (document_id) REFERENCES documents (id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX INDX_USER_FK ON user_permissions (user_id) ;

CREATE INDEX INDX_document_fk ON user_permissions (document_id);