# Tasks schema

# --- !Ups

CREATE TABLE "task" (
  "id" BIGINT NOT NULL AUTO_INCREMENT,
  "title" VARCHAR(255),
  PRIMARY KEY ("id")
);

# --- !Downs

DROP TABLE IF EXISTS "task";