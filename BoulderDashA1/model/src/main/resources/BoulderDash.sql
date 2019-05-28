SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE DATABASE `Boulder_Dash`;
USE `Boulder_Dash`;
CREATE TABLE `level` (
    `id` int(11) NOT NULL,
    `time` int(11) NOT NULL,
    `number_diamond` int(11) NOT NULL,
    `level_map` TEXT NOT NULL,
    PRIMARY KEY(id)
)ENGINE=Innodb DEFAULT CHARSET=latin1;





CREATE DEFINER=`root`@`localhost` PROCEDURE `SelectID` (IN `i` INT)  READS SQL DATA
    SQL SECURITY INVOKER
SELECT * FROM level WHERE id = i;


