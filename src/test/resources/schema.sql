CREATE TABLE url_entity (
 url_id bigint NOT NULL AUTO_INCREMENT,
  original_url varchar(255) DEFAULT NULL,
  small_url varchar(255) DEFAULT NULL,
  user_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (url_id)
);