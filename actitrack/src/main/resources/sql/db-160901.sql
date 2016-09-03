-- MySQL dump 10.16  Distrib 10.1.16-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: project_actitrack
-- ------------------------------------------------------
-- Server version	10.1.16-MariaDB

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
-- Table structure for table `actionitems`
--

DROP TABLE IF EXISTS `actionitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actionitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `summary` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `dueDate` date NOT NULL DEFAULT '0000-00-00',
  `assignedTo` int(11) NOT NULL,
  `priority` enum('High','Medium','Low') NOT NULL,
  `status` enum('New','In Progress','Completed','Suspended') NOT NULL,
  `createdBy` int(11) NOT NULL,
  `createdOn` int(11) NOT NULL,
  `modifiedBy` int(11) NOT NULL DEFAULT '0',
  `modifiedOn` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `ix_actionitem_priority` (`priority`),
  KEY `ix_actionitem_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actionitems`
--

LOCK TABLES `actionitems` WRITE;
/*!40000 ALTER TABLE `actionitems` DISABLE KEYS */;
INSERT INTO `actionitems` VALUES (1,'Add a readme to webapp','assdsdsdsdsd','2016-09-05',1,'High','New',1,1,0,0),(2,'Implement validation check for user creation','dsdsfdsfdsf','2016-09-01',2,'High','New',1,1,0,0),(3,'Add department field to user module','sdsdsadsadsad','2016-09-01',2,'Medium','In Progress',1,1,0,0);
/*!40000 ALTER TABLE `actionitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actionitem_id` int(11) NOT NULL,
  `note` text NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `pw` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user001','user001@email.com','$2a$10$IHdRfnhNgQesPFD5hrUcMOvyx5RrRcklkpXfs9YX4j1qXvouEeVIa'),(2,'user002','user002@email.com','$2a$10$NlU0bdBUiegZWZvl6CGpj.wV5YfbDGZ8lYznxWp2NNE4F9cYJJFOe'),(3,'user003','user003@email.com','$2a$10$gwEVdI6lSDrkIkGLrsHTIOzLEgnT3gDUDYMOfxvOdnoqvGpf275fm'),(4,'user004','user004@email.com','$2a$10$W2ZJXI00xhL03vwcy2Y/DeZe.BqMf4dUSP5lxEQFAqV.ocbUAYS4m'),(5,'user005','user005@email.com','$2a$10$Q209gpOY73eZM5/7ix8Hxua/d8cPiV0nhBF.cPgEmtoY.2WN3z/k6'),(6,'user006','user006@email.com','$2a$10$6njApozqiAlwamwi8oqgF.70eeTpgl4Z4SUpKK72AnIhHd3WXK/ei'),(7,'user007','user007@email.com','$2a$10$YQifsq3fEABCJNM.ebxlmuJTNSvtJR72jZWHxLoU8A6Lap1QV/WP6'),(8,'user008','user008@email.com','$2a$10$o0AykWkpgbKak8EyMM/J2.ntdjieJnf6.vgtcQVwnSW6n74YkjQMu'),(9,'user009','user009@email.com','$2a$10$rbDB/WLckQTAO4St9TpMVOzSlJDrx98r2jgt9crwtsy8hCv7wIZkm'),(10,'user010','user010@email.com','$2a$10$rXBPyVPkaD5u7MbDckemPutPT/m.8SylMrI3su26aaV0pxP34kDwa');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-01 16:50:19
