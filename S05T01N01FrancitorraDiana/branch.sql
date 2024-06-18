
CREATE SCHEMA IF NOT EXISTS `branch` DEFAULT CHARACTER SET utf8 ;
USE `branch` ;


CREATE TABLE IF NOT EXISTS `branch`.`branch` (
  `pk_branchid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_branchid`))
ENGINE = InnoDB;



