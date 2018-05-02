CREATE DATABASE IF NOT EXISTS `NotebookShop`;

USE `NotebookShop`;

CREATE TABLE IF NOT EXISTS notebooks
(
  id           INT          NOT NULL
    PRIMARY KEY,
  date         DATE         NULL,
  model        VARCHAR(255) NULL,
  price        DOUBLE       NULL,
  serial       VARCHAR(255) NULL,
  vendor       VARCHAR(255) NULL,
  processor_id BIGINT       NULL
)
  ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS processors
(
  id        BIGINT       NOT NULL
    PRIMARY KEY,
  fraquency INT          NULL,
  title     VARCHAR(255) NULL
)
  ENGINE = MyISAM;