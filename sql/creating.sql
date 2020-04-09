-- MySQL Script generated by MySQL Workbench
-- Sun Apr  5 20:06:44 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema coffee_machine
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema coffee_machine
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coffee_machine` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `coffee_machine` ;

-- -----------------------------------------------------
-- Table `coffee_machine`.`additions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`additions` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`additions` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`drink_size`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`drink_size` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`drink_size` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `volume` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`drinks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`drinks` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`drinks` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NULL DEFAULT NULL,
  `drink_size_id` INT(10) NOT NULL,
  `serving_number` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_drinks_drink_size1_idx` (`drink_size_id` ASC) VISIBLE,
  CONSTRAINT `fk_drinks_drink_size1`
    FOREIGN KEY (`drink_size_id`)
    REFERENCES `coffee_machine`.`drink_size` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`card_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`card_account` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`card_account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(16) NULL DEFAULT NULL,
  `amount` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`role` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`user` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `is_active` TINYINT(1) NULL DEFAULT NULL,
  `card_account_id` INT(11) NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_card_account_idx` (`card_account_id` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_card_account`
    FOREIGN KEY (`card_account_id`)
    REFERENCES `coffee_machine`.`card_account` (`id`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `coffee_machine`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`drink_sales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`drink_sales` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`drink_sales` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `drinks_id` INT(10) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_drink_sales_drinks1_idx` (`drinks_id` ASC) VISIBLE,
  INDEX `fk_drink_sales_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_drink_sales_drinks1`
    FOREIGN KEY (`drinks_id`)
    REFERENCES `coffee_machine`.`drinks` (`id`),
  CONSTRAINT `fk_drink_sales_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `coffee_machine`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffee_machine`.`addition_sales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffee_machine`.`addition_sales` ;

CREATE TABLE IF NOT EXISTS `coffee_machine`.`addition_sales` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `additions_id` INT(10) NOT NULL,
  `drink_sales_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_addition_sales_additions1_idx` (`additions_id` ASC) VISIBLE,
  INDEX `fk_addition_sales_drink_sales1_idx` (`drink_sales_id` ASC) VISIBLE,
  CONSTRAINT `fk_addition_sales_additions1`
    FOREIGN KEY (`additions_id`)
    REFERENCES `coffee_machine`.`additions` (`id`),
  CONSTRAINT `fk_addition_sales_drink_sales1`
    FOREIGN KEY (`drink_sales_id`)
    REFERENCES `coffee_machine`.`drink_sales` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
