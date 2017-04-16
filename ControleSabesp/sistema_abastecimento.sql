SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA sistem_abastecimento DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE sistem_abastecimento ;

-- -----------------------------------------------------
-- Table `sistem_abastecimento`.`sistema_abastecimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistem_abastecimento`.`sistema_abastecimento` (
  `cod_sistema` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome_sistema` VARCHAR(255) NOT NULL COMMENT '',
  `volume` VARCHAR(255) NOT NULL COMMENT '',
  `historico` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`cod_sistema`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistem_abastecimento`.`municipios_sp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistem_abastecimento`.`municipios_sp` (
  `cod_municipios` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `habitantes` VARCHAR(255) NOT NULL COMMENT '',
  `municipios` VARCHAR(255) NOT NULL COMMENT '',
  `area` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`cod_municipios`)  COMMENT '')
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `sistem_abastecimento`.`represas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistem_abastecimento`.`represas` (
  `cod_represas` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome_represa` VARCHAR(255) NOT NULL COMMENT '',
  `sistema_abastecimento_cod_sistema` INT NOT NULL COMMENT '',
  `data_medicao` varchar(45) NOT NULL COMMENT '',
  `volume_armazenado` FLOAT(25) NOT NULL COMMENT '',
  `pluviometria` FLOAT(25) NOT NULL COMMENT '',
  PRIMARY KEY (`cod_represas`)  COMMENT '',
  INDEX `fk_represas_sistema_abastecimento1_idx` (`sistema_abastecimento_cod_sistema` ASC)  COMMENT '',
  CONSTRAINT `fk_represas_sistema_abastecimento1`
    FOREIGN KEY (`sistema_abastecimento_cod_sistema`)
    REFERENCES `sistem_abastecimento`.`sistema_abastecimento` (`cod_sistema`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistem_abastecimento`.`sistema_abastecimento_has_municipios_sp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistem_abastecimento`.`sistema_abastecimento_has_municipios_sp` (
  `sistema_abastecimento_cod_sistema` INT NOT NULL COMMENT '',
  `municipios_sp_cod_municipios` INT NOT NULL COMMENT '',
  PRIMARY KEY (`sistema_abastecimento_cod_sistema`, `municipios_sp_cod_municipios`)  COMMENT '',
  INDEX `fk_sistema_abastecimento_has_municipios_sp_municipios_sp1_idx` (`municipios_sp_cod_municipios` ASC)  COMMENT '',
  INDEX `fk_sistema_abastecimento_has_municipios_sp_sistema_abasteci_idx` (`sistema_abastecimento_cod_sistema` ASC)  COMMENT '',
  CONSTRAINT `fk_sistema_abastecimento_has_municipios_sp_sistema_abastecime1`
    FOREIGN KEY (`sistema_abastecimento_cod_sistema`)
    REFERENCES `sistem_abastecimento`.`sistema_abastecimento` (`cod_sistema`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sistema_abastecimento_has_municipios_sp_municipios_sp1`
    FOREIGN KEY (`municipios_sp_cod_municipios`)
    REFERENCES `sistem_abastecimento`.`municipios_sp` (`cod_municipios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'80,841 habitantes','Arujá','96.11km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'250,477 habitantes','Barueri','65.69km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'30.062 habitantes','Biritima Mirim','317,41km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'92.142 habitantes','Caieiras','96.10km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'69.649 habitantes','Cajamar','131.33km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'380.414 habitantes','Carapicuiba','34.55km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'219.888 habitantes','Cotia','324.01km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'394.131 habitantes','Diadema','30.80km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'252.729 habitantes','Embu das Artes','70.39km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'64.882 habitantes','Embu-Guaçu','155.63km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'178.60 habitantes','Ferraz de Vasconcelos','29.57km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'164.055 habitantes','Francisco Morato','49.07km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'139.981 habitantes','Franco da Rocha','134.16km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'27.248 habitantes','Guararema','270.82km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'1.274.528 habitantes','Guarulhos','318.68m²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'159.457 habitantes','Itapecerica da Serra','150.87km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'215.034 habitantes','Itapevi','82.66km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'340.751 habitantes','Itaquaquecetuba','82.61km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'114.431 habitantes','Jandira','17.45km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'29.348 habitantes','Juquitiba','522.18km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'88.150 habitantes','Mariporã','320.70km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'435.171 habitantes','Mauá','61.87km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'405.959 habitantes','Mogi das Cruzes','712.67km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'671.686 habitantes','Osasco','64.95km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'16.918 habitantes','Pirapora do Bom Jesus','108.52km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'110.001 habitantes','Poá','17.26km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'115.677 habitantes','Ribeirao Pires','99.12km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'46.326 habitantes','Rio Grande da Serra','36.34km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'16.105 habitantes','Salesópolis','425.00km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'52.369 habitantes','Santa Isabel','363.30km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'120.765 habitantes','Santana da Parnaiba','179.93km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'683.709 habitantes','Santo André','175.78km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'786.078 habitantes','São Bernado do Campo','409.48km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'150.319 habitantes','São Caetano do Sul','15.33km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'14.595 habitantes','São Lourenço da Serra','186.33km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'1.521.100 habitantes','São Paulo','11.513.836km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'206.200 habitantes','Suzano','273.854km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'20.390 habitantes','Taboão Da Serra','260.345km²');
insert into municipios_sp(cod_municipios, habitantes, municipios, area) VALUES (default,'46.921 habitantes','Vargem Grande da Serra','42.48km²');

select*from municipios_sp;

insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Alto Cotia','00,00%','00,00mm');
insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Guarapiranga','00,00%','00,00mm');
insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Alto Tietê','00,00%','00,00mm');
insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Cantareira','00,00%','00,00mm');
insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Rio Claro','00,00%','00,00mm');
insert into sistema_abastecimento(cod_sistema, nome_sistema, volume, historico) VALUES (default,'Rio Grande','00,00%','00,00mm');

select*from sistema_abastecimento;


-- segundo teste

CREATE TABLE tabelaCRUD(
  cod_represas INT NOT NULL AUTO_INCREMENT primary key,
  nome_represa VARCHAR(45) NOT NULL COMMENT '',
  nome_sistema VARCHAR(45) NOT NULL COMMENT '',
  volume_armazenado FLOAT(25) NOT NULL COMMENT '',
  pluviometria FLOAT(25) NOT NULL COMMENT '',
  data_atual varchar(45) NOT NULL);

select * from tabelaCRUD;


create table historico (

DataHistorico date not null,
Id_sistema int not null auto_increment primary key,
Volume double not null,
NomeSistema varchar(45) not null);
 

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;