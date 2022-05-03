-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projeto-vacinacao
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projeto-vacinacao
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto-vacinacao` DEFAULT CHARACTER SET utf8 ;
USE `projeto-vacinacao` ;

-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`endereco` (
  `endereco` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(50) NOT NULL,
  `cidade` VARCHAR(30) NOT NULL,
  `estado` CHAR(2) NOT NULL,
  `numero` INT NOT NULL,
  `bairro` VARCHAR(50) NOT NULL,
  `complemento` VARCHAR(50) NULL,
  `cep` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`endereco`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`cadastro_unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`cadastro_unidade` (
  `unidade` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `centro` TINYINT NOT NULL,
  `endereco` INT NOT NULL,
  PRIMARY KEY (`unidade`),
  INDEX `fk_unidade_endereco_idx` (`endereco` ASC) VISIBLE,
  CONSTRAINT `fk_unidade_endereco`
    FOREIGN KEY (`endereco`)
    REFERENCES `projeto-vacinacao`.`endereco` (`endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`cadastro_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`cadastro_pessoa` (
  `pessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(20) NOT NULL,
  `endereco` INT NOT NULL,
  PRIMARY KEY (`pessoa`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `fk_pessoa_endereco_idx` (`endereco` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_endereco`
    FOREIGN KEY (`endereco`)
    REFERENCES `projeto-vacinacao`.`endereco` (`endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`lote` (
  `lote` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `data_vencimento` DATETIME NOT NULL,
  PRIMARY KEY (`lote`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`estoque_vacinas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`estoque_vacinas` (
  `unidade` INT NOT NULL,
  `lote` INT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`unidade`, `lote`),
  INDEX `fk_lote_estoque_idx` (`lote` ASC) VISIBLE,
  CONSTRAINT `fk_lote_estoque`
    FOREIGN KEY (`lote`)
    REFERENCES `projeto-vacinacao`.`lote` (`lote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_unidade_estoque`
    FOREIGN KEY (`unidade`)
    REFERENCES `projeto-vacinacao`.`cadastro_unidade` (`unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`movimento_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`movimento_estoque` (
  `movimento` INT NOT NULL AUTO_INCREMENT,
  `unidade` INT NOT NULL,
  `lote` INT NOT NULL,
  `pessoa` INT NULL,
  `quantidade` INT NOT NULL,
  `tipo_movimento` ENUM('Entrada', 'Saida') NOT NULL,
  `tipo_transacao` ENUM('rec', 'tra', 'apl') NOT NULL,
  `data_movimento` DATETIME NOT NULL,
  `unidade_transfer` INT NULL,
  PRIMARY KEY (`movimento`, `unidade`, `lote`),
  INDEX `fk_cadastro_unidade_movimento_idx` (`unidade` ASC) VISIBLE,
  INDEX `fk_cadastro_populacao_movimento_idx` (`pessoa` ASC) VISIBLE,
  INDEX `fk_movimento_estoque_idx` (`unidade_transfer` ASC) VISIBLE,
  INDEX `fk_lote_movimento_idx` (`lote` ASC) VISIBLE,
  CONSTRAINT `fk_cadastro_unidade_movimento`
    FOREIGN KEY (`unidade`)
    REFERENCES `projeto-vacinacao`.`cadastro_unidade` (`unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cadastro_pessoa_movimento`
    FOREIGN KEY (`pessoa`)
    REFERENCES `projeto-vacinacao`.`cadastro_pessoa` (`pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lote_movimento`
    FOREIGN KEY (`lote`)
    REFERENCES `projeto-vacinacao`.`lote` (`lote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimento_estoque`
    FOREIGN KEY (`unidade_transfer`)
    REFERENCES `projeto-vacinacao`.`estoque_vacinas` (`unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto-vacinacao`.`vacinados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto-vacinacao`.`vacinados` (
  `dose` INT NOT NULL,
  `pessoa` INT NOT NULL,
  `unidade` INT NOT NULL,
  `lote` INT NOT NULL,
  `movimento` INT NOT NULL,
  `data_aplicacao` DATETIME NOT NULL,
  PRIMARY KEY (`dose`, `pessoa`),
  INDEX `fk_unidade_vacinados_idx` (`unidade` ASC) VISIBLE,
  INDEX `fk_movimento_vacinados_idx` (`movimento` ASC) VISIBLE,
  INDEX `fk_pessoa_vacinados_idx` (`pessoa` ASC) VISIBLE,
  INDEX `fk_lote_vacinados_idx` (`lote` ASC) VISIBLE,
  CONSTRAINT `fk_unidade_vacinados`
    FOREIGN KEY (`unidade`)
    REFERENCES `projeto-vacinacao`.`movimento_estoque` (`unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lote_vacinados`
    FOREIGN KEY (`lote`)
    REFERENCES `projeto-vacinacao`.`movimento_estoque` (`lote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimento_vacinados`
    FOREIGN KEY (`movimento`)
    REFERENCES `projeto-vacinacao`.`movimento_estoque` (`movimento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_vacinados`
    FOREIGN KEY (`pessoa`)
    REFERENCES `projeto-vacinacao`.`cadastro_pessoa` (`pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
