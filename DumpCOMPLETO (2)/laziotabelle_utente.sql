CREATE DATABASE  IF NOT EXISTS `laziotabelle` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `laziotabelle`;
-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: laziotabelle
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.22.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `idutente` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `data_di_nascita` date DEFAULT NULL,
  `creato_il` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modificato_il` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `categoria_lavori_id` int DEFAULT NULL,
  PRIMARY KEY (`idutente`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'romolo 2024','Fiorenza','fiorenzaromolo4@gmail.com',NULL,'1964-04-02',NULL,'2024-06-03 12:38:25',NULL),(2,'romolo','Fiorenza','fiorenzaromolo4@gmail.com',NULL,'1964-04-02',NULL,NULL,0),(3,'simone','Fiorenza','fiorenzaromolo4@gmail.com',NULL,'1999-02-05',NULL,NULL,0),(4,'magda','bazydlo','r.fiorenza@tiscalinet.it',NULL,'1990-02-09',NULL,NULL,0),(5,'romolo','Fiorenza','fiorenzaromolo4@gmail.com',NULL,'2024-05-26',NULL,NULL,0),(6,'mattia','devil','fiorenzaromolo4@gmail.com',NULL,'2024-02-26',NULL,NULL,0),(7,'mattia','devil','fiorenzaromolo4@gmail.com',NULL,'2024-02-26',NULL,NULL,0),(8,'Romolo','Fiorenza','r.fiorenza@tiscalinet.it',NULL,'2024-05-26',NULL,NULL,0),(9,'Stefano','gio','gtr@tr.it',NULL,'2024-05-26',NULL,NULL,0),(10,'marco','felice','fiorenzaromolo4@gmail.com',NULL,'2024-05-27',NULL,NULL,0),(11,'Romolo','Fiorenza','r.fiorenza@tiscalinet.it',NULL,'2024-05-27',NULL,NULL,0),(12,'michela','rossi','fiorenzaromolo4@gmail.com',NULL,'2024-05-27',NULL,'2024-05-27 15:35:03',NULL),(13,'Romolo','Fiorenza','r.fiorenza@tiscalinet.it',NULL,'2024-05-27',NULL,NULL,0),(14,'STEFY','CIU','R4Y5','THT','2024-05-27','2024-05-26 22:00:00','2024-05-26 22:00:00',NULL),(15,'marcolino','gfr','3rt4t','123456','2023-02-02','2024-05-27 14:46:10','2024-05-27 14:46:10',NULL),(16,'maria','giulia','gtghtht4h@rrrrrr',NULL,'2024-05-27',NULL,NULL,NULL),(17,'marina','lanzi','r.fiorenza@tiscalinet.it',NULL,'2024-05-27','2024-05-27 15:11:44',NULL,NULL),(18,'Romolo','Fiorenza','r.fiorenza@tiscalinet.it',NULL,'2024-05-25','2024-05-27 15:13:15',NULL,NULL),(19,'tiziano','dwevil','r.fiorenza@tiscalinet.it',NULL,'2024-05-28','2024-05-28 07:25:53',NULL,NULL),(20,'romolo2','Fiorenza','fiorenzaromolo4@gmail.com',NULL,'2024-05-28',NULL,'2024-05-28 10:30:34',NULL),(21,'mirco','pacerto','r.fiorenza@tiscalinet.it',NULL,'1777-02-09',NULL,'2024-05-28 10:52:01',NULL);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-13 18:08:10
