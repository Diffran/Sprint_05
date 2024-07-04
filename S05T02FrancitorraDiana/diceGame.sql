
CREATE SCHEMA IF NOT EXISTS `diceGame` DEFAULT CHARACTER SET utf8 ;
USE `diceGame` ;

-- -----------------------------------------------------
-- Table `diceGame`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGame`.`player` (
  `pk_PlayerID` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `winrate` DOUBLE NOT NULL,
  `games` INT NOT NULL,
  PRIMARY KEY (`pk_PlayerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diceGame`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGame`.`game` (
  `pk_GameID` INT NOT NULL AUTO_INCREMENT,
  `dice_1` INT NOT NULL,
  `dice_2` INT NOT NULL,
  `result` ENUM('WIN', 'LOSE') NOT NULL,
  `playerID` INT NOT NULL,
  PRIMARY KEY (`pk_GameID`, `playerID`),
  INDEX `fk_game_player_idx` (`playerID` ASC) ,
  CONSTRAINT `fk_game_player`
    FOREIGN KEY (`playerID`)
    REFERENCES `diceGame`.`player` (`pk_PlayerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
