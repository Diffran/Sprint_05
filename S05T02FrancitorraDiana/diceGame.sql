CREATE SCHEMA IF NOT EXISTS `diceGame` DEFAULT CHARACTER SET utf8 ;
USE `diceGame` ;

-- -----------------------------------------------------
-- Table `diceGame`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGame`.`player` (
  `player_id` VARCHAR(45) NOT NULL UNIQUE,
  `nickname` VARCHAR(45) NOT NULL,
  `winrate` DOUBLE NOT NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `diceGame`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGame`.`game` (
  `pk_GameID` INT NOT NULL AUTO_INCREMENT,
  `dice_1` INT NOT NULL,
  `dice_2` INT NOT NULL,
  `result` ENUM('WIN', 'LOSE') NOT NULL,
  `player_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_GameID`),
  INDEX `fk_game_player_idx` (`player_id` ASC),
  CONSTRAINT `fk_game_player`
    FOREIGN KEY (`player_id`)
    REFERENCES `diceGame`.`player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
