-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: javatechie_Migration
-- Source Schemata: javatechie
-- Created: Mon Mar  6 09:54:59 2023
-- Workbench Version: 8.0.32
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema javatechie_Migration
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `javatechie_Migration` ;
CREATE SCHEMA IF NOT EXISTS `javatechie_Migration` ;

-- ----------------------------------------------------------------------------
-- Table javatechie_Migration.product_tbl
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `javatechie_Migration`.`product_tbl` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table javatechie_Migration.product_tbl_seq
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `javatechie_Migration`.`product_tbl_seq` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;
