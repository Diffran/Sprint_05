 -----------------------------------------------------
-- Schema flowers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema flowers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flowers` DEFAULT CHARACTER SET utf8 ;
USE `flowers` ;

-- -----------------------------------------------------
-- Table `flowers`.`flower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers`.`flower` (
  `pk_flowerid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_flowerid`))
ENGINE = InnoDB;



