CREATE DATABASE  IF NOT EXISTS `laziotabelle` ;
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
-- Table structure for table `Preventivo_Lavorazione`
--

DROP TABLE IF EXISTS `Preventivo_Lavorazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Preventivo_Lavorazione` (
  `id_preventivo` int NOT NULL,
  `id_lavorazione` int NOT NULL,
  PRIMARY KEY (`id_preventivo`,`id_lavorazione`),
  KEY `id_lavorazione` (`id_lavorazione`),
  CONSTRAINT `Preventivo_Lavorazione_ibfk_1` FOREIGN KEY (`id_preventivo`) REFERENCES `Preventivo` (`id_preventivo`),
  CONSTRAINT `Preventivo_Lavorazione_ibfk_2` FOREIGN KEY (`id_lavorazione`) REFERENCES `Lavorazione` (`id_lavorazione`)
) ENGINE=InnoDB ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Preventivo_Lavorazione`
--

LOCK TABLES `Preventivo_Lavorazione` WRITE;
/*!40000 ALTER TABLE `Preventivo_Lavorazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `Preventivo_Lavorazione` ENABLE KEYS */;
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
