-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 12. Mai 2015 um 22:12
-- Server Version: 5.6.19-0ubuntu0.14.04.1
-- PHP-Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Datenbank: `car_rental`
--
CREATE DATABASE IF NOT EXISTS `car_rental` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `car_rental`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `car`
--

CREATE TABLE IF NOT EXISTS `car` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `license_plate_number` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `supervision` date DEFAULT NULL,
  `purchase` date DEFAULT NULL,
  `car_model_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `license_plate_number` (`license_plate_number`),
  KEY `fk_c_car_model` (`car_model_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=15 ;

--
-- Daten für Tabelle `car`
--

INSERT INTO `car` (`ID`, `license_plate_number`, `mileage`, `supervision`, `purchase`, `car_model_id`) VALUES
(1, 'RV AB 335', 45000, '2001-05-20', '2001-05-20', 3),
(2, 'RV AB 336', 39000, '2001-05-20', '2001-05-20', 3),
(3, 'RV AB 337', 41000, '2001-05-20', '2001-05-20', 3),
(4, 'RV XY 245', 18000, '2001-04-20', '2001-04-20', 1),
(5, 'RV XY 246', 19000, '2001-04-20', '2001-04-20', 1),
(6, 'RV XY 247', 21000, '2001-04-20', '2001-04-20', 1),
(7, 'RV XY 248', 35000, '2001-04-20', '2001-04-20', 2),
(8, 'RV XY 249', 29050, '2001-04-20', '2001-04-20', 2),
(9, 'RV BQ 591', 65000, '2001-06-20', '2001-06-20', 4),
(10, 'RV BQ 592', 66000, '2001-06-20', '2001-06-20', 4),
(11, 'RV BQ 593', 64500, '2001-06-20', '2001-06-20', 4),
(12, 'RV C 45', 150000, '2001-04-20', '2001-04-20', 6),
(13, 'RV MM 999', 16000, '2001-04-20', '2001-04-20', 5),
(14, 'RV PF 23', 25000, '2001-04-20', '2001-04-20', 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `car_model`
--

CREATE TABLE IF NOT EXISTS `car_model` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `manufacturer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_type_id` int(10) unsigned DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `kw` int(11) DEFAULT NULL,
  `fuel` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price_per_day` float(5,2) DEFAULT NULL,
  `price_per_km` float(5,2) DEFAULT NULL,
  `axes` int(11) DEFAULT '2',
  `load_volume` int(11) DEFAULT NULL,
  `load_capacity` int(11) DEFAULT NULL,
  `driver_license` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_cm_car_type` (`car_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `car_model`
--

INSERT INTO `car_model` (`ID`, `description`, `manufacturer`, `car_type_id`, `seats`, `kw`, `fuel`, `price_per_day`, `price_per_km`, `axes`, `load_volume`, `load_capacity`, `driver_license`) VALUES
(1, 'Golf FSI', 'VW', 1, 5, 80, 'super', 54.70, 0.04, 2, 350, 400, 'A'),
(2, 'Golf Variant TDI', 'VW', 2, 5, 90, 'diesel', 62.30, 0.05, 2, 450, 500, 'A'),
(3, 'Golf', 'VW', 1, 5, 60, 'super', 45.00, 0.03, 2, 350, 400, 'A'),
(4, 'Astra', 'Opel', 1, 5, 70, 'super', 40.70, 0.04, 2, 330, 380, 'A'),
(5, '528i', 'BMW', 1, 5, 120, 'super', 83.55, 0.07, 2, 320, 440, 'A'),
(6, 'Taurus', 'Daimler-Chrysler', 6, 3, 340, 'diesel', 120.30, 0.09, 3, 20000, 4000, 'B'),
(7, 'Sharan', 'VW', 4, 7, 100, 'super', 85.60, 0.05, 2, 550, 500, 'A');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `car_type`
--

CREATE TABLE IF NOT EXISTS `car_type` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `car_type`
--

INSERT INTO `car_type` (`ID`, `type`) VALUES
(3, 'convertible'),
(2, 'estate'),
(1, 'limousine'),
(5, 'minibus'),
(7, 'pickup truck'),
(6, 'truck'),
(4, 'van');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `commodate`
--

CREATE TABLE IF NOT EXISTS `commodate` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) unsigned DEFAULT NULL,
  `car_id` int(10) unsigned DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `start_mileage` int(11) DEFAULT NULL,
  `end_mileage` int(11) DEFAULT NULL,
  `price` float(6,2) DEFAULT NULL,
  `payed` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_c_customer` (`customer_id`),
  KEY `fk_c_car` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `driver_license_id` int(10) NOT NULL,
  `forename` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surename` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `postcode` char(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `street` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` char(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `driver_license_id` (`driver_license_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `customer`
--

INSERT INTO `customer` (`ID`, `driver_license_id`, `forename`, `surename`, `postcode`, `city`, `street`, `email`, `phone_number`) VALUES
(1, 1, 'Bob', 'Johnson', '10178', 'Berlin', 'Torstraße', 'bob.johnson@email.com', '+4915112345678'),
(2, 1, 'Alice', 'Marrisen', '70173', 'Stuttgart', 'Dobelstraße', 'alice.marrisen@email.com', '+4915123456789');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `driver_license`
--

CREATE TABLE IF NOT EXISTS `driver_license` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `class` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `driver_license`
--

INSERT INTO `driver_license` (`ID`, `class`) VALUES
(1, 'B'),
(2, 'B');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `features`
--

CREATE TABLE IF NOT EXISTS `features` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Daten für Tabelle `features`
--

INSERT INTO `features` (`ID`, `description`) VALUES
(1, 'air conditioning'),
(2, 'coupling device'),
(3, 'navigation system'),
(4, 'cruise control');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `link_car_type_features`
--

CREATE TABLE IF NOT EXISTS `link_car_type_features` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `car_type_id` int(10) unsigned DEFAULT NULL,
  `features_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ctf_car_type` (`car_type_id`),
  KEY `fk_ctf_features` (`features_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `link_car_type_features`
--

INSERT INTO `link_car_type_features` (`ID`, `car_type_id`, `features_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 5, 1),
(4, 5, 3),
(5, 5, 4),
(6, 7, 1),
(7, 7, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) unsigned DEFAULT NULL,
  `car_model_id` int(10) unsigned DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_r_customer` (`customer_id`),
  KEY `fk_r_car_model` (`car_model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `fk_c_car_model` FOREIGN KEY (`car_model_id`) REFERENCES `car_model` (`ID`);

--
-- Constraints der Tabelle `car_model`
--
ALTER TABLE `car_model`
  ADD CONSTRAINT `fk_cm_car_type` FOREIGN KEY (`car_type_id`) REFERENCES `car_type` (`ID`);

--
-- Constraints der Tabelle `commodate`
--
ALTER TABLE `commodate`
  ADD CONSTRAINT `fk_c_car` FOREIGN KEY (`car_id`) REFERENCES `car` (`ID`),
  ADD CONSTRAINT `fk_c_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`);

--
-- Constraints der Tabelle `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_c_driver_license` FOREIGN KEY (`driver_license_id`) REFERENCES `driver_license` (`ID`);

--
-- Constraints der Tabelle `link_car_type_features`
--
ALTER TABLE `link_car_type_features`
  ADD CONSTRAINT `fk_ctf_car_type` FOREIGN KEY (`car_type_id`) REFERENCES `car_type` (`ID`),
  ADD CONSTRAINT `fk_ctf_features` FOREIGN KEY (`features_id`) REFERENCES `features` (`ID`);

--
-- Constraints der Tabelle `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_r_car_model` FOREIGN KEY (`car_model_id`) REFERENCES `car_model` (`ID`),
  ADD CONSTRAINT `fk_r_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`);
