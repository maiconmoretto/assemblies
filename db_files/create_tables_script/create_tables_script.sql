-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema assemblies
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema assemblies
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `assemblies` ;
USE `assemblies` ;

-- -----------------------------------------------------
-- Table `assemblies`.`agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assemblies`.`agenda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `duration` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `assemblies`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assemblies`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `created_at` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `assemblies`.`voting_agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assemblies`.`voting_agenda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_agenda` INT NOT NULL,
  `id_user` INT NOT NULL,
  `created_at` VARCHAR(45) NULL,
  `vote` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_user_idx` (`id_user` ASC) ,
  INDEX `id_agenda_idx` (`id_agenda` ASC) ,
  CONSTRAINT `id_agenda`
    FOREIGN KEY (`id_agenda`)
    REFERENCES `assemblies`.`agenda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `assemblies`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
