-- JOURNAL TABLE: ENTRY
DROP TABLE IF EXISTS entry;
CREATE TABLE entry (
ID BIGINT(20) NOT NULL AUTO_INCREMENT,
CREATED DATETIME DEFAULT NULL,
SUMMARY VARCHAR(255) DEFAULT NULL,
TITLE VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID)
);