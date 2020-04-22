CREATE DATABASE  IF NOT EXISTS `student_ams` ;
USE `student_ams`;



CREATE TABLE `credentials` (
  `iduser` varchar(6) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mode` varchar(8) NOT NULL,
  PRIMARY KEY (`iduser`)
);


INSERT INTO `credentials` VALUES ('011011','passadmin','Admin');

DROP TABLE IF EXISTS `student_details`;

CREATE TABLE `student_details` (
  `iduser` varchar(6) NOT NULL,
  `name` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  `branch` varchar(3) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`iduser`)
) ;




DROP TABLE IF EXISTS `teacher_details`;

CREATE TABLE `teacher_details` (
  `iduser` varchar(6) NOT NULL,
  `name` varchar(30) NOT NULL,
  `branch` varchar(3) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`iduser`)
);





DROP TABLE IF EXISTS `courses_list`;

CREATE TABLE `courses_list` (
  `idcourse` varchar(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `branch` varchar(3) DEFAULT NULL,
  `idteacher` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`idcourse`),
  KEY `teacher_check_idx` (`idteacher`),
  CONSTRAINT `teacher_check` FOREIGN KEY (`idteacher`) REFERENCES `teacher_details` (`iduser`)
);


