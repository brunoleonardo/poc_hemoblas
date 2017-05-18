CREATE DATABASE  IF NOT EXISTS `agendamento` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `agendamento`;
-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: agendamento
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_hora` datetime NOT NULL,
  `estabelecimento_saude_id` bigint(20) NOT NULL,
  `numero_protocolo` varchar(255) NOT NULL,
  `doador_cpf` varchar(255) DEFAULT NULL,
  `status_agenda_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9d4q45df7hsxn2uocc3fkedlr` (`doador_cpf`),
  KEY `FKskt8k2paltbleerg55i63nwd` (`status_agenda_id`),
  CONSTRAINT `FK9d4q45df7hsxn2uocc3fkedlr` FOREIGN KEY (`doador_cpf`) REFERENCES `doador` (`cpf`),
  CONSTRAINT `FKskt8k2paltbleerg55i63nwd` FOREIGN KEY (`status_agenda_id`) REFERENCES `status_agenda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
INSERT INTO `agenda` VALUES (1,'2017-05-18 09:00:00',3106702126443,'2017050900','014.272.206-54',1),(2,'2017-05-18 09:30:00',3106702126443,'2017050930',NULL,0),(3,'2017-05-18 10:00:00',3106702126443,'2017051000',NULL,0),(4,'2017-06-28 08:00:00',3106702126443,'2017051000',NULL,0),(5,'2017-06-28 08:15:00',3106702126443,'2017051000',NULL,0),(6,'2017-06-28 08:30:00',3106702126443,'2017051000',NULL,0),(7,'2017-06-28 08:45:00',3106702126443,'2017051000',NULL,0),(8,'2017-06-28 09:00:00',3106702126443,'2017051000',NULL,0),(9,'2017-06-28 09:15:00',3106702126443,'2017051000',NULL,0),(10,'2017-06-28 09:30:00',3106702126443,'2017051000',NULL,0),(11,'2017-06-28 09:45:00',3106702126443,'2017051000',NULL,0),(12,'2017-06-28 10:00:00',3106702126443,'2017051000',NULL,0),(13,'2017-06-28 10:15:00',3106702126443,'2017051000',NULL,0),(14,'2017-06-28 10:30:00',3106702126443,'2017051000',NULL,0);
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doador`
--

DROP TABLE IF EXISTS `doador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doador` (
  `cpf` varchar(14) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` varchar(45) DEFAULT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(60) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `genero_id` int(11) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE KEY `UK_e8l6iugeey6wrxfivqtfrx1f` (`rg`),
  KEY `FK7muq3bcfussd80rf4596atppb` (`genero_id`),
  CONSTRAINT `FK7muq3bcfussd80rf4596atppb` FOREIGN KEY (`genero_id`) REFERENCES `genero` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doador`
--

LOCK TABLES `doador` WRITE;
/*!40000 ALTER TABLE `doador` DISABLE KEYS */;
INSERT INTO `doador` VALUES ('014.272.206-54','32.604-140',NULL,'1983-08-09','brunoleonardo.as@gmail.com','Rua Conceição Rosa Lima, nº 50, Apto. 101, B. Angola','BRUNO LEONARDO ANDRADE SILVA','12.574.236','(31) 3532-5156',1),('219.491.235-53','32.604-140',NULL,'2011-11-18','gutopedrosa@gmail.com','Rua Conceição Rosa Lima, nº 50, Apto. 101, B. Angola','Augusto Andrade Pedrosa','12.574.222','(31) 3358-6325',1),('236.747.536-93','32.604-140',NULL,'1980-06-14','joyce@gmail.com','Rua Conceição Rosa Lima, nº 50, Apto. 101, B. Angola','JOYCE PEDROSA','12.574.235','(31) 99251-6402',2);
/*!40000 ALTER TABLE `doador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `id` int(11) NOT NULL,
  `descricao` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (0,'Não conhecido'),(1,'Masculino'),(2,'Feminino'),(3,'Não aplicável');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_agenda`
--

DROP TABLE IF EXISTS `status_agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_agenda` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_agenda`
--

LOCK TABLES `status_agenda` WRITE;
/*!40000 ALTER TABLE `status_agenda` DISABLE KEYS */;
INSERT INTO `status_agenda` VALUES (0,'Aberta'),(1,'Alocada'),(2,'Realizada'),(3,'Cancelada');
/*!40000 ALTER TABLE `status_agenda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-17 23:17:13
