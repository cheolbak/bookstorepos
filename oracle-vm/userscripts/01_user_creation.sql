CREATE USER C##POS_USER
IDENTIFIED BY secret;

GRANT CONNECT, RESOURCE, DBA TO C##POS_USER;