CREATE DATABASE  IF NOT EXISTS `paf` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paf`;

DROP TABLE IF EXISTS `paymentnew`;
CREATE TABLE `paymentnew` (
  `paymentID` int(11) NOT NULL AUTO_INCREMENT,
  `connectionCode` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `amount` varchar(30) NOT NULL,
  `paymentType` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  PRIMARY KEY (`paymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `paymentnew` WRITE;
/*!40000 ALTER TABLE `paymentnew` DISABLE KEYS */;
INSERT INTO `paymentnew` VALUES ('0000000001','Anura','5000','Credit Card','2022-04-24');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;




