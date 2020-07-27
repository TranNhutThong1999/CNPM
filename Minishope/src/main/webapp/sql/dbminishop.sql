-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: dbminishop
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;



DROP TABLE IF EXISTS `chucvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chucvu` (
  `idChucVu` int(11) NOT NULL AUTO_INCREMENT,
  `tenChucVu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`idChucVu`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chucvu`
--

LOCK TABLES `chucvu` WRITE;
/*!40000 ALTER TABLE `chucvu` DISABLE KEYS */;
INSERT INTO `chucvu` VALUES (1,'ROLE_admin'),(2,'ROLE_manager'),(3,'ROLE_user');
/*!40000 ALTER TABLE `chucvu` ENABLE KEYS */;
UNLOCK TABLES;





DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nhanvien` (
  `idNhanVien` int(11) NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `diaChi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gioiTinh` varchar(10) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `CMND` char(12) DEFAULT NULL,
  `soDT` varchar(10) DEFAULT NULL,
  `idChucVu` int(11) DEFAULT NULL,
  `tenDangNhap` varchar(20) DEFAULT NULL,
  `matKhau` varchar(255) DEFAULT NULL,
  `isEnabled` bit(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `timeToken` timestamp(1) NULL DEFAULT NULL,
  `isNonBanned` bit(1) DEFAULT NULL,
  `ToKenFB` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idNhanVien`),
  KEY `idChucVu` (`idChucVu`),
  CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`idChucVu`) REFERENCES `chucvu` (`idChucVu`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Thông Trần',NULL,NULL,'thongdeptrai9x@gmail.com',NULL,NULL,3,'960867821011287',NULL,_binary '','4387a1d5-036c-4ae2-b9f8-ec9313fbd76a',NULL,_binary '','EAAWhnupRIcYBAMIP7ylQdbZCsN1oYQgMH24hiNOI1ax5fbNTZAZBtPSa0qJmZBhPeHbg6NLeH6up6yppjIjEzyHcSTxZALYpj69PBZBFvjG3dBAPdLIBeiUSn2RMrm4ojv7C1sHN3fvO3U3LcfnVHAjAOZA52s1yb8PRoOZBSNJlWeaR3255x9kvBJZCGwqmxFwpROuJWsN9CVwZDZD'),(3,NULL,NULL,NULL,'thongmap0909310872@gmail.com',NULL,NULL,1,'thong999','$2a$10$gK4WmT2kB9EsPvJ4Ryy6guEpJ5HX0EpZjZmD4GxheMObw5N.2dk.6',_binary '','942b48b6-cbdd-458c-9782-677611983ef4',NULL,_binary '',NULL),(6,NULL,NULL,NULL,'thongmap0909310872@gmail.com',NULL,NULL,3,'thong998','$2a$10$32lSHRllixHtWTN0Qd/xIO6QjdwpYwSVt9Pt4/fWPb6vya9Qqo8Ha',_binary '',NULL,NULL,_binary '',NULL);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

