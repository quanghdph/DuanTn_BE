-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: new_schema
-- ------------------------------------------------------
-- Server version	8.0.34

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

CREATE DATABASE IF NOT EXISTS `duantn`;
USE `duantn`;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `custome_id` bigint DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `district` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `ward` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `address_code` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `address_detail` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `addresses_FK` (`custome_id`),
  CONSTRAINT `addresses_FK` FOREIGN KEY (`custome_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,1,'Hà Nội','Hoàn Kiếm','Hàng Gai','51234','1 Hàng Gai, Hoàn Kiếm, Hà Nội','2023-10-27 13:15:11','2023-10-27 13:15:11'),(2,2,'Hồ Chí Minh','Quận 1','Phường Bến Nghé','62345','2 Phường Bến Nghé, Quận 1, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(3,3,'Đà Nẵng','Quận Hải Châu','Phường Thanh Khê Tây','73456','3 Phường Thanh Khê Tây, Quận Hải Châu, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(4,4,'Hải Phòng','Quận Ngô Quyền','Phường Máy Tơ','84567','4 Phường Máy Tơ, Quận Ngô Quyền, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(5,5,'Nha Trang','Thành phố Nha Trang','Phường Phương Sài','95678','5 Phường Phương Sài, Thành phố Nha Trang, Khánh Hòa','2023-10-27 13:15:11','2023-10-27 13:15:11'),(6,6,'Huế','Thành phố Huế','Phường Phú Hội','10678','6 Phường Phú Hội, Thành phố Huế, Thừa Thiên-Huế','2023-10-27 13:15:11','2023-10-27 13:15:11'),(7,7,'Cần Thơ','Quận Ninh Kiều','Phường An Bình','11789','7 Phường An Bình, Quận Ninh Kiều, Cần Thơ','2023-10-27 13:15:11','2023-10-27 13:15:11'),(8,8,'Hà Giang','Huyện Đồng Văn','Xã Phố Là','12890','8 Xã Phố Là, Huyện Đồng Văn, Hà Giang','2023-10-27 13:15:11','2023-10-27 13:15:11'),(9,9,'Vũng Tàu','Thành phố Vũng Tàu','Phường Bà Rịa','13901','9 Phường Bà Rịa, Thành phố Vũng Tàu, Bà Rịa-Vũng Tàu','2023-10-27 13:15:11','2023-10-27 13:15:11'),(10,10,'Cà Mau','Thành phố Cà Mau','Phường 5','14012','10 Phường 5, Thành phố Cà Mau, Cà Mau','2023-10-27 13:15:11','2023-10-27 13:15:11'),(11,11,'Hà Nội','Cầu Giấy','Dịch Vọng','15123','11 Dịch Vọng, Cầu Giấy, Hà Nội','2023-10-27 13:15:11','2023-10-27 13:15:11'),(12,12,'Hồ Chí Minh','Quận 3','Phường 7','16234','12 Phường 7, Quận 3, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(13,13,'Đà Nẵng','Quận Sơn Trà','Phường Mỹ An','17345','13 Phường Mỹ An, Quận Sơn Trà, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(14,14,'Hải Phòng','Quận Lê Chân','Phường Dư Hàng','18456','14 Phường Dư Hàng, Quận Lê Chân, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(15,15,'Nha Trang','Thành phố Nha Trang','Phường Vĩnh Hòa','19567','15 Phường Vĩnh Hòa, Thành phố Nha Trang, Khánh Hòa','2023-10-27 13:15:11','2023-10-27 13:15:11'),(16,16,'Huế','Thành phố Huế','Phường Vỹ Dạ','20678','16 Phường Phú Hội, Thành phố Huế, Thừa Thiên-Huế','2023-10-27 13:15:11','2023-10-27 13:15:11'),(17,17,'Cần Thơ','Quận Bình Thủy','Phường An Thới','21789','17 Phường An Thới, Quận Bình Thủy, Cần Thơ','2023-10-27 13:15:11','2023-10-27 13:15:11'),(18,18,'Hà Giang','Huyện Mèo Vạc','Xã Mèo Vạc','22890','18 Xã Mèo Vạc, Huyện Mèo Vạc, Hà Giang','2023-10-27 13:15:11','2023-10-27 13:15:11'),(19,19,'Vũng Tàu','Thành phố Vũng Tàu','Phường Long Hương','23901','19 Phường Long Hương, Thành phố Vũng Tàu, Bà Rịa-Vũng Tàu','2023-10-27 13:15:11','2023-10-27 13:15:11'),(20,20,'Cà Mau','Thành phố Cà Mau','Phường 6','24012','20 Phường 6, Thành phố Cà Mau, Cà Mau','2023-10-27 13:15:11','2023-10-27 13:15:11'),(21,21,'Hà Nội','Ba Đình','Phúc Xá','25123','21 Phúc Xá, Ba Đình, Hà Nội','2023-10-27 13:15:11','2023-10-27 13:15:11'),(22,22,'Hồ Chí Minh','Quận 10','Phường 13','26234','22 Phường 13, Quận 10, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(23,23,'Đà Nẵng','Quận Hòa Vang','Phường Hòa Phát','27345','23 Phường Hòa Phát, Quận Hòa Vang, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(24,24,'Hải Phòng','Quận An Dương','Phường Đặng Cương','28456','24 Phường Đặng Cương, Quận An Dương, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(25,25,'Hồ Chí Minh','Quận 5','Phường 10','29567','25 Phường 10, Quận 5, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(26,26,'Đà Nẵng','Quận Thanh Khê','Phường An Khê','30678','26 Phường An Khê, Quận Thanh Khê, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(27,27,'Hải Phòng','Quận Hải An','Phường Đằng Lâm','31789','27 Phường Đằng Lâm, Quận Hải An, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(28,28,'Nha Trang','Thành phố Nha Trang','Phường Phước Long','32890','28 Phường Phước Long, Thành phố Nha Trang, Khánh Hòa','2023-10-27 13:15:11','2023-10-27 13:15:11'),(29,29,'Huế','Thành phố Huế','Phường Phú Bài','33901','29 Phường Phú Bài, Thành phố Huế, Thừa Thiên-Huế','2023-10-27 13:15:11','2023-10-27 13:15:11'),(30,30,'Cần Thơ','Quận Cái Răng','Phường Lê Bình','34012','30 Phường Lê Bình, Quận Cái Răng, Cần Thơ','2023-10-27 13:15:11','2023-10-27 13:15:11'),(31,31,'Hà Giang','Huyện Yên Minh','Xã Yên Minh','35123','31 Xã Yên Minh, Huyện Yên Minh, Hà Giang','2023-10-27 13:15:11','2023-10-27 13:15:11'),(32,32,'Vũng Tàu','Thành phố Vũng Tàu','Phường Rạch Dừa','36234','32 Phường Rạch Dừa, Thành phố Vũng Tàu, Bà Rịa-Vũng Tàu','2023-10-27 13:15:11','2023-10-27 13:15:11'),(33,33,'Cà Mau','Thành phố Cà Mau','Phường 7','37345','33 Phường 7, Thành phố Cà Mau, Cà Mau','2023-10-27 13:15:11','2023-10-27 13:15:11'),(34,34,'Hà Nội','Tây Hồ','Quảng An','38456','34 Quảng An, Tây Hồ, Hà Nội','2023-10-27 13:15:11','2023-10-27 13:15:11'),(35,35,'Hồ Chí Minh','Quận Bình Thanh','Phường 14','39567','35 Phường 14, Quận Bình Thạnh, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(36,36,'Đà Nẵng','Quận Liên Chiểu','Phường Thanh Khê Đông','40678','36 Phường Thanh Khê Đông, Quận Liên Chiểu, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(37,37,'Hải Phòng','Quận Kiến An','Phường Lạc Viên','41789','37 Phường Lạc Viên, Quận Kiến An, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(38,38,'Nha Trang','Thành phố Nha Trang','Phường Vĩnh Hải','42890','38 Phường Vĩnh Hải, Thành phố Nha Trang, Khánh Hòa','2023-10-27 13:15:11','2023-10-27 13:15:11'),(39,39,'Huế','Thành phố Huế','Phường Phú Cát','123452','39 Phường Phú Cát, Thành phố Huế, Thừa Thiên-Huế','2023-10-27 13:15:11','2023-10-27 13:15:11'),(40,40,'Cần Thơ','Quận Thốt Nốt','Phường Thốt Nốt','1A234','40 Phường Thốt Nốt, Quận Thốt Nốt, Cần Thơ','2023-10-27 13:15:11','2023-10-27 13:15:11'),(41,41,'Hà Giang','Huyện Quản Bạ','Xã Sủng Là','2B345','41 Xã Sủng Là, Huyện Quản Bạ, Hà Giang','2023-10-27 13:15:11','2023-10-27 13:15:11'),(42,42,'Vũng Tàu','Thành phố Vũng Tàu','Phường Bà Rịa','3C456','42 Phường Bà Rịa, Thành phố VŽúng Tàu, Bà Ríä-VŽúng Tàu','2023-10-27 13:15:11','2023-10-27 13:15:11'),(43,43,'Cà Mau','Thành phố Cà Mau','Phường 8','4D567','43 Phường 8, Thành phố Cà Mau, Cà Mau','2023-10-27 13:15:11','2023-10-27 13:15:11'),(44,44,'Hà Nội','Hoàng Mai','Trương Định','5E678','44 Trương Định, Hoàng Mai, Hà Nội','2023-10-27 13:15:11','2023-10-27 13:15:11'),(45,45,'Hồ Chí Minh','Quận 11','Phường 6','6F789','45 Phường 6, Quận 11, Hồ Chí Minh','2023-10-27 13:15:11','2023-10-27 13:15:11'),(46,46,'Đà Nẵng','Quận Cẩm Lệ','Phường Hoà Minh','7G890','46 Phường Hoà Minh, Quận Cẩm Lệ, Đà Nẵng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(47,47,'Hải Phòng','Quận Dương Kinh','Phường Lãm Hà','8H012','47 Phường Lãm Hà, Quận Dương Kinh, Hải Phòng','2023-10-27 13:15:11','2023-10-27 13:15:11'),(48,48,'Nha Trang','Thành phố Nha Trang','Phường Vĩnh Nguyên','9I123','48 Phường Vĩnh Nguyên, Thành phố Nha Trang, Khánh Hòa','2023-10-27 13:15:11','2023-10-27 13:15:11'),(49,49,'Huế','Thành phố Huế','Phường Thủy Xuân','0J234','49 Phường Thủy Xuân, Thành phố Huế, Thừa Thiên-Huế','2023-10-27 13:15:11','2023-10-27 13:15:11'),(50,50,'Cần Thơ','Quận Ninh Kiều','Phường An Khánh','1A345','50 Phường An Khánh, Quận Ninh Kiều, Cần Thơ','2023-10-27 13:15:11','2023-10-27 13:15:11');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `bill_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `transport_fee` decimal(10,0) DEFAULT NULL,
  `total_amount` decimal(10,0) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bill_FK_3` (`employee_id`),
  KEY `bill_FK_4` (`customer_id`),
  CONSTRAINT `bill_FK_3` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `bill_FK_4` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,1,'HDB0001','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','123 Đường Lê Lợi, Quận 1, TP Hồ Chí Minh','Nguyễn Văn A','0901234567',50000,200000,'Giao hàng trong 1 ngày',1),(2,2,2,'HDB0002','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','456 Đường Lý Tự Trọng, Quận 3, TP Hồ Chí Minh','Trần Thị B','0912345678',60000,300000,'Giao hàng sau 2 ngày',1),(3,3,3,'HDB0003','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','789 Đường Hàm Nghi, Quận 7, TP Hồ Chí Minh','Lê Đức C','0976543210',70000,400000,'Giao hàng nhanh',1),(4,4,4,'HDB0004','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','101 Đường Trần Phú, Quận 10, TP Hồ Chí Minh','Phạm Thị D','0987654321',55000,250000,'Giao hàng sau 3 ngày',1),(5,5,5,'HDB0005','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','111 Đường Võ Văn Tần, Quận Bình Thạnh, TP Hồ Chí Minh','Hoàng Minh E','0901234567',65000,350000,'Giao hàng trong 2 ngày',1),(6,6,6,'HDB0006','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','222 Đường Nguyễn Huệ, Quận 5, TP Hồ Chí Minh','Vũ Thị F','0912345678',75000,450000,'Giao hàng vào buổi sáng',1),(7,7,7,'HDB0007','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','333 Đường Bến Vân Đồn, Quận 4, TP Hồ Chí Minh','Trịnh Duy G','0976543210',70000,400000,'Giao hàng vào buổi chiều',1),(8,8,8,'HDB0008','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','444 Đường Tôn Đức Thắng, Quận 11, TP Hồ Chí Minh','Mai Ngọc H','0987654321',60000,300000,'Giao hàng nhanh',1),(9,9,9,'HDB0009','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','555 Đường Cách Mạng Tháng Tám, Quận 10, TP Hồ Chí Minh','Lý Thu I','0901234567',55000,250000,'Giao hàng sau 1 ngày',1),(10,10,10,'HDB0010','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','666 Đường Võ Thị Sáu, Quận 3, TP Hồ Chí Minh','Nguyễn Văn J','0912345678',80000,450000,'Giao hàng nhanh',1),(11,11,11,'HDB0011','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','777 Đường Hồ Bieu Chanh, Quận 1, TP Hồ Chí Minh','Trần Thu K','0976543210',60000,300000,'Giao hàng sau 2 ngày',1),(12,12,12,'HDB0012','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','888 Đường Trần Quang Khải, Quận 5, TP Hồ Chí Minh','Lê Đức L','0987654321',55000,250000,'Giao hàng vào buổi sáng',1),(13,13,13,'HDB0013','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','999 Đường Đinh Công Tráng, Quận Bình Thạnh, TP Hồ Chí Minh','Phạm Văn M','0901234567',75000,450000,'Giao hàng nhanh',1),(14,14,14,'HDB0014','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1010 Đường Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh','Hoàng Thị N','0912345678',70000,400000,'Giao hàng vào buổi chiều',1),(15,15,15,'HDB0015','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1111 Đường Lê Đại Hành, Quận 11, TP Hồ Chí Minh','Vũ Quốc O','0976543210',80000,450000,'Giao hàng trong 1 ngày',1),(16,16,16,'HDB0016','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1212 Đường Huỳnh Tấn Phát, Quận 7, TP Hồ Chí Minh','Trịnh Minh P','0987654321',55000,250000,'Giao hàng sau 3 ngày',1),(17,17,17,'HDB0017','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1313 Đường Bà Huyện Thanh Quan, Quận 3, TP Hồ Chí Minh','Mai Thị Q','0901234567',60000,300000,'Giao hàng vào buổi sáng',1),(18,18,18,'HDB0018','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1414 Đường Bùi Viện, Quận 1, TP Hồ Chí Minh','Lý Văn R','0912345678',75000,450000,'Giao hàng sau 2 ngày',1),(19,19,19,'HDB0019','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1515 Đường Võ Thị Sáu, Quận 3, TP Hồ Chí Minh','Nguyễn Thị S','0976543210',70000,400000,'Giao hàng nhanh',1),(20,20,20,'HDB0020','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1616 Đường Hai Bà Trưng, Quận 1, TP Hồ Chí Minh','Trần Văn T','0987654321',80000,450000,'Giao hàng vào buổi sáng',1),(21,21,21,'HDB0021','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1717 Đường Nguyễn Du, Quận 5, TP Hồ Chí Minh','Lê Thị U','0901234567',55000,250000,'Giao hàng trong 1 ngày',1),(22,22,22,'HDB0022','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1818 Đường Lý Thường Kiệt, Quận 3, TP Hồ Chí Minh','Phạm Đức V','0912345678',70000,350000,'Giao hàng nhanh',1),(23,23,23,'HDB0023','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1919 Đường Hải Triều, Quận 1, TP Hồ Chí Minh','Hoàng Thị X','0976543210',60000,300000,'Giao hàng sau 2 ngày',1),(24,24,24,'HDB0024','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2020 Đường Phan Kế Bính, Quận 7, TP Hồ Chí Minh','Vũ Minh Y','0987654321',75000,450000,'Giao hàng vào buổi chiều',1),(25,25,25,'HDB0025','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2121 Đường Nguyễn Thị Nghĩa, Quận 5, TP Hồ Chí Minh','Trịnh Thị Z','0901234567',80000,400000,'Giao hàng nhanh',1),(26,26,26,'HDB0026','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','222 Đường Bạch Đằng, Quận Hai Phòng','Nguyễn Văn A','0901234567',50000,200000,'Giao hàng trong 1 ngày',1),(27,27,27,'HDB0027','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','333 Đường Quang Trung, Quận Hà Nội','Trần Thị B','0912345678',60000,300000,'Giao hàng sau 2 ngày',1),(28,28,28,'HDB0028','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','444 Đường Phan Đình Phùng, Quận Đà Nẵng','Lê Đức C','0976543210',70000,400000,'Giao hàng nhanh',1),(29,29,29,'HDB0029','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','555 Đường Hoàng Sa, Quận Cần Thơ','Phạm Thị D','0987654321',55000,250000,'Giao hàng sau 3 ngày',1),(30,30,30,'HDB0030','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','666 Đường Cao Thắng, Quận Hải Phòng','Hoàng Minh E','0901234567',65000,350000,'Giao hàng trong 2 ngày',1),(31,31,31,'HDB0031','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','777 Đường Nguyễn Đình Chieu, Quận Nha Trang','Vũ Thị F','0912345678',75000,450000,'Giao hàng vào buổi sáng',1),(32,32,32,'HDB0032','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','888 Đường Phạm Hồng Thái, Quận Bà Rịa - Vũng Tàu','Trịnh Duy G','0976543210',70000,400000,'Giao hàng vào buổi chiều',1),(33,33,33,'HDB0033','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','999 Đường Lê Lai, Quận Sài Gòn','Mai Ngọc H','0987654321',60000,300000,'Giao hàng nhanh',1),(34,34,34,'HDB0034','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1010 Đường Lý Thường Kiệt, Quận Bình Dương','Lý Thu I','0901234567',55000,250000,'Giao hàng sau 1 ngày',1),(35,35,35,'HDB0035','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1111 Đường Lê Thánh Tôn, Quận Hà Nội','Nguyễn Văn J','0912345678',80000,450000,'Giao hàng nhanh',1),(36,36,36,'HDB0036','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1212 Đường Hai Bà Trưng, Quận Cần Thơ','Trần Thu K','0976543210',60000,300000,'Giao hàng sau 2 ngày',1),(37,37,37,'HDB0037','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1313 Đường Phan Kế Bính, Quận Hải Phòng','Lê Đức L','0987654321',55000,250000,'Giao hàng vào buổi sáng',1),(38,38,38,'HDB0038','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1414 Đường Đinh Công Tráng, Quận Đà Nẵng','Phạm Văn M','0901234567',75000,450000,'Giao hàng nhanh',1),(39,39,39,'HDB0039','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1515 Đường Nguyễn Văn Tráng, Quận Nha Trang','Hoàng Thị N','0912345678',70000,400000,'Giao hàng vào buổi chiều',1),(40,40,40,'HDB0040','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1616 Đường Trần Phú, Quận Hồ Chí Minh','Vũ Quốc O','0976543210',80000,450000,'Giao hàng trong 1 ngày',1),(41,41,41,'HDB0041','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1717 Đường Nguyễn Bình Khiêm, Quận Sài Gòn','Trịnh Minh P','0987654321',55000,250000,'Giao hàng sau 3 ngày',1),(42,42,42,'HDB0042','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1818 Đường Võ Văn Kiệt, Quận Hải Phòng','Mai Thị Q','0901234567',60000,300000,'Giao hàng vào buổi sáng',1),(43,43,43,'HDB0043','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','1919 Đường Trần Hưng Đạo, Quận Nha Trang','Lý Văn R','0912345678',75000,450000,'Giao hàng sau 2 ngày',1),(44,44,44,'HDB0044','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2020 Đường Lý Tự Trọng, Quận Hồ Chí Minh','Nguyễn Thị S','0976543210',70000,400000,'Giao hàng nhanh',1),(45,45,45,'HDB0045','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2121 Đường Nguyễn Cư Trinh, Quận Hà Nội','Trần Văn T','0987654321',80000,450000,'Giao hàng vào buổi sáng',1),(46,46,46,'HDB0046','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2222 Đường Trần Hữu Trang, Quận Đà Nẵng','Phạm Đức V','0901234567',55000,250000,'Giao hàng trong 1 ngày',1),(47,47,47,'HDB0047','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2323 Đường Bà Huyện Thanh Quan, Quận Nha Trang','Hoàng Thị X','0912345678',70000,350000,'Giao hàng nhanh',1),(48,48,48,'HDB0048','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2424 Đường Hồ Bieu Chanh, Quận Bình Dương','Vũ Minh Y','0976543210',60000,300000,'Giao hàng sau 2 ngày',1),(49,49,49,'HDB0049','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2525 Đường Phan Đình Phùng, Quận Hải Phòng','Trịnh Thị Z','0987654321',75000,450000,'Giao hàng vào buổi chiều',1),(50,50,50,'HDB0050','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2023-10-27 13:16:07','2626 Đường Lê Đại Hành, Quận Sài Gòn','Lê Thị U','0901234567',80000,400000,'Giao hàng nhanh',1);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_detail`
--

DROP TABLE IF EXISTS `bill_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bill_id` bigint DEFAULT NULL,
  `product_detail_id` bigint DEFAULT NULL,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `color` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `size` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `default_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bill_detail_FK` (`bill_id`),
  KEY `bill_detail_FK_1` (`product_detail_id`),
  CONSTRAINT `bill_detail_FK` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `bill_detail_FK_1` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_detail`
--

LOCK TABLES `bill_detail` WRITE;
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
INSERT INTO `bill_detail` VALUES (1,1,1,'Áo sơ mi nam','Trắng','M',2,250000,200000,'2023-11-06 08:30:00','2023-11-06 08:30:00',1),(2,2,2,'Quần jean nữ','Xanh','S',1,350000,300000,'2023-11-06 08:31:00','2023-11-06 08:31:00',1),(3,3,3,'Áo thun nam','Đen','L',3,150000,120000,'2023-11-06 08:32:00','2023-11-06 08:32:00',1),(4,4,4,'Váy dạo phố nữ','Hồng','M',1,280000,240000,'2023-11-06 08:33:00','2023-11-06 08:33:00',1),(5,5,5,'Áo khoác nam','Xám','XL',2,420000,380000,'2023-11-06 08:34:00','2023-11-06 08:34:00',1),(6,6,6,'Áo len nữ','Trắng','M',1,200000,170000,'2023-11-06 08:35:00','2023-11-06 08:35:00',1),(7,7,7,'Áo thun nam','Xanh','L',2,150000,130000,'2023-11-06 08:36:00','2023-11-06 08:36:00',1),(8,8,8,'Quần lửng nữ','Đen','S',1,180000,150000,'2023-11-06 08:37:00','2023-11-06 08:37:00',1),(9,9,9,'Áo khoác nam','Đỏ','M',1,350000,300000,'2023-11-06 08:38:00','2023-11-06 08:38:00',1),(10,10,10,'Váy đầm nữ','Hồng','L',1,250000,220000,'2023-11-06 08:39:00','2023-11-06 08:39:00',1),(11,11,11,'Áo thun nam','Trắng','XL',3,160000,130000,'2023-11-06 08:40:00','2023-11-06 08:40:00',1),(12,12,12,'Quần short nữ','Xanh','S',1,120000,100000,'2023-11-06 08:41:00','2023-11-06 08:41:00',1),(13,13,13,'Áo khoác nam','Đen','L',2,380000,340000,'2023-11-06 08:42:00','2023-11-06 08:42:00',1),(14,14,14,'Áo len nữ','Xám','M',1,220000,190000,'2023-11-06 08:43:00','2023-11-06 08:43:00',1),(15,15,15,'Áo thun nam','Đỏ','M',2,170000,140000,'2023-11-06 08:44:00','2023-11-06 08:44:00',1),(16,16,16,'Quần bò nữ','Trắng','L',1,280000,240000,'2023-11-06 08:45:00','2023-11-06 08:45:00',1),(17,17,17,'Áo khoác nam','Hồng','S',1,300000,260000,'2023-11-06 08:46:00','2023-11-06 08:46:00',1),(18,18,18,'Váy hoa nữ','Xanh','XL',2,240000,210000,'2023-11-06 08:47:00','2023-11-06 08:47:00',1),(19,19,19,'Áo thun nam','Xám','L',2,130000,100000,'2023-11-06 08:48:00','2023-11-06 08:48:00',1),(20,20,20,'Quần short nữ','Đen','M',1,110000,90000,'2023-11-06 08:49:00','2023-11-06 08:49:00',1),(21,21,21,'Áo sơ mi nam','Trắng','M',2,250000,200000,'2023-11-06 08:50:00','2023-11-06 08:50:00',1),(22,22,22,'Quần jean nữ','Xanh','S',1,350000,300000,'2023-11-06 08:51:00','2023-11-06 08:51:00',1),(23,23,23,'Áo thun nam','Đen','L',3,150000,120000,'2023-11-06 08:52:00','2023-11-06 08:52:00',1),(24,24,24,'Váy dạo phố nữ','Hồng','M',1,280000,240000,'2023-11-06 08:53:00','2023-11-06 08:53:00',1),(25,25,25,'Áo khoác nam','Xám','XL',2,420000,380000,'2023-11-06 08:54:00','2023-11-06 08:54:00',1),(26,26,26,'Áo len nữ','Trắng','M',1,200000,170000,'2023-11-06 08:55:00','2023-11-06 08:55:00',1),(27,27,27,'Áo thun nam','Xanh','L',2,150000,130000,'2023-11-06 08:56:00','2023-11-06 08:56:00',1),(28,28,28,'Quần lửng nữ','Đen','S',1,180000,150000,'2023-11-06 08:57:00','2023-11-06 08:57:00',1),(29,29,29,'Áo khoác nam','Đỏ','M',1,350000,300000,'2023-11-06 08:58:00','2023-11-06 08:58:00',1),(30,30,30,'Váy đầm nữ','Hồng','L',1,250000,220000,'2023-11-06 08:59:00','2023-11-06 08:59:00',1),(31,31,31,'Áo thun nam','Trắng','XL',3,160000,130000,'2023-11-06 09:00:00','2023-11-06 09:00:00',1),(32,32,32,'Quần short nữ','Xanh','S',1,120000,100000,'2023-11-06 09:01:00','2023-11-06 09:01:00',1),(33,33,33,'Áo khoác nam','Đen','L',2,380000,340000,'2023-11-06 09:02:00','2023-11-06 09:02:00',1),(34,34,34,'Áo len nữ','Xám','M',1,220000,190000,'2023-11-06 09:03:00','2023-11-06 09:03:00',1),(35,35,35,'Áo thun nam','Đỏ','M',2,170000,140000,'2023-11-06 09:04:00','2023-11-06 09:04:00',1),(36,36,36,'Quần bò nữ','Trắng','L',1,280000,240000,'2023-11-06 09:05:00','2023-11-06 09:05:00',1),(37,37,37,'Áo khoác nam','Hồng','S',1,300000,260000,'2023-11-06 09:06:00','2023-11-06 09:06:00',1),(38,38,38,'Váy hoa nữ','Xanh','XL',2,240000,210000,'2023-11-06 09:07:00','2023-11-06 09:07:00',1),(39,39,39,'Áo thun nam','Xám','L',2,130000,100000,'2023-11-06 09:08:00','2023-11-06 09:08:00',1),(40,40,40,'Quần short nữ','Đen','M',1,110000,90000,'2023-11-06 09:09:00','2023-11-06 09:09:00',1),(41,41,41,'Áo sơ mi nam','Trắng','M',2,250000,200000,'2023-11-06 09:10:00','2023-11-06 09:10:00',1),(42,42,42,'Quần jean nữ','Xanh','S',1,350000,300000,'2023-11-06 09:11:00','2023-11-06 09:11:00',1),(43,43,43,'Áo thun nam','Đen','L',3,150000,120000,'2023-11-06 09:12:00','2023-11-06 09:12:00',1),(44,44,44,'Váy dạo phố nữ','Hồng','M',1,280000,240000,'2023-11-06 09:13:00','2023-11-06 09:13:00',1),(45,45,45,'Áo khoác nam','Xám','XL',2,420000,380000,'2023-11-06 09:14:00','2023-11-06 09:14:00',1),(46,46,46,'Áo len nữ','Trắng','M',1,200000,170000,'2023-11-06 09:15:00','2023-11-06 09:15:00',1),(47,47,47,'Áo thun nam','Xanh','L',2,150000,130000,'2023-11-06 09:16:00','2023-11-06 09:16:00',1);
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'VIET001','Zara Vietnam','2023-10-24 10:30:00','2023-10-24 10:30:00',0),(2,'VIET002','H&M Vietnam','2023-10-24 10:35:00','2023-10-24 10:35:00',0),(3,'VIET003','Uniqlo Vietnam','2023-10-24 10:40:00','2023-10-24 10:40:00',0),(4,'VIET004','GAP Vietnam','2023-10-24 10:45:00','2023-10-24 10:45:00',0),(5,'VIET005','Polo Ralph Lauren Vietnam','2023-10-24 10:50:00','2023-10-24 10:50:00',0),(6,'VIET006','Vascara','2023-10-24 10:55:00','2023-10-24 10:55:00',0),(7,'VIET007','FPT Shop Fashion','2023-10-24 11:00:00','2023-10-24 11:00:00',0),(8,'VIET008','Novaland Fashion','2023-10-24 11:05:00','2023-10-24 11:05:00',0),(9,'VIET009','Bitis Hunter','2023-10-24 11:10:00','2023-10-24 11:10:00',0),(10,'VIET010','Zalo Shop','2023-10-24 11:15:00','2023-10-24 11:15:00',0),(11,'VIET011','TopShop Vietnam','2023-10-24 11:20:00','2023-10-24 11:20:00',0),(12,'VIET012','Mango Vietnam','2023-10-24 11:25:00','2023-10-24 11:25:00',0),(13,'VIET013','Pull & Bear Vietnam','2023-10-24 11:30:00','2023-10-24 11:30:00',0),(14,'VIET014','OVS Vietnam','2023-10-24 11:35:00','2023-10-24 11:35:00',0),(15,'VIET015','Valentino Vietnam','2023-10-24 11:40:00','2023-10-24 11:40:00',0),(16,'VIET016','Famiana','2023-10-24 11:45:00','2023-10-24 11:45:00',0),(17,'VIET017','Xara Fashion','2023-10-24 11:50:00','2023-10-24 11:50:00',0),(18,'VIET018','Calvin Klein Vietnam','2023-10-24 11:55:00','2023-10-24 11:55:00',0),(19,'VIET019','Vans Vietnam','2023-10-24 12:00:00','2023-10-24 12:00:00',0),(20,'VIET020','Supreme Vietnam','2023-10-24 12:05:00','2023-10-24 12:05:00',0),(21,'VIET021','Champion Vietnam','2023-10-24 12:10:00','2023-10-24 12:10:00',0),(22,'VIET022','Crocs Vietnam','2023-10-24 12:15:00','2023-10-24 12:15:00',0),(23,'VIET023','Puma Vietnam','2023-10-24 12:20:00','2023-10-24 12:20:00',0),(24,'VIET024','Converse Vietnam','2023-10-24 12:25:00','2023-10-24 12:25:00',0),(25,'VIET025','Skechers Vietnam','2023-10-24 12:30:00','2023-10-24 12:30:00',0),(26,'VIET026','New Balance Vietnam','2023-10-24 12:35:00','2023-10-24 12:35:00',0),(27,'VIET027','Adidas Vietnam','2023-10-24 12:40:00','2023-10-24 12:40:00',0),(28,'VIET028','Nike Vietnam','2023-10-24 12:45:00','2023-10-24 12:45:00',0),(29,'VIET029','Under Armour Vietnam','2023-10-24 12:50:00','2023-10-24 12:50:00',0),(30,'VIET030','Fila Vietnam','2023-10-24 12:55:00','2023-10-24 12:55:00',0),(31,'VIET031','Reebok Vietnam','2023-10-24 13:00:00','2023-10-24 13:00:00',0),(32,'VIET032','Lacoste Vietnam','2023-10-24 13:05:00','2023-10-24 13:05:00',0),(33,'VIET033','Balenciaga Vietnam','2023-10-24 13:10:00','2023-10-24 13:10:00',0),(34,'VIET034','Tommy Hilfiger Vietnam','2023-10-24 13:15:00','2023-10-24 13:15:00',0),(35,'VIET035','Levi\'s Vietnam','2023-10-24 13:20:00','2023-10-24 13:20:00',0),(36,'VIET036','Diesel Vietnam','2023-10-24 13:25:00','2023-10-24 13:25:00',0),(37,'VIET037','Guess Vietnam','2023-10-24 13:30:00','2023-10-24 13:30:00',0),(38,'VIET038','Armani Exchange Vietnam','2023-10-24 13:35:00','2023-10-24 13:35:00',0),(39,'VIET039','Burberry Vietnam','2023-10-24 13:40:00','2023-10-24 13:40:00',0),(40,'VIET040','Versace Vietnam','2023-10-24 13:45:00','2023-10-24 13:45:00',0),(41,'VIET041','Fendi Vietnam','2023-10-24 13:50:00','2023-10-24 13:50:00',0),(42,'VIET042','Prada Vietnam','2023-10-24 13:55:00','2023-10-24 13:55:00',0),(43,'VIET043','Dolce & Gabbana Vietnam','2023-10-24 14:00:00','2023-10-24 14:00:00',0),(44,'VIET044','Gucci Vietnam','2023-10-24 14:05:00','2023-10-24 14:05:00',0),(45,'VIET045','Chanel Vietnam','2023-10-24 14:10:00','2023-10-24 14:10:00',0),(46,'VIET046','Louis Vuitton Vietnam','2023-10-24 14:15:00','2023-10-24 14:15:00',0),(47,'VIET047','Bvlgari Vietnam','2023-10-24 14:20:00','2023-10-24 14:20:00',0),(48,'VIET048','Hermès Vietnam','2023-10-24 14:25:00','2023-10-24 14:25:00',0),(49,'VIET049','Jimmy Choo Vietnam','2023-10-24 14:30:00','2023-10-24 14:30:00',0),(50,'VIET050','Dior Vietnam','2023-10-24 14:35:00','2023-10-24 14:35:00',0);
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_FK` (`employee_id`),
  KEY `cart_FK_1` (`customer_id`),
  CONSTRAINT `cart_FK` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `cart_FK_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'CART001',1,'2023-10-01 08:30:00','2023-10-01 10:45:00',1,1),(2,'CART002',2,'2023-10-01 09:15:00','2023-10-01 11:30:00',1,2),(3,'CART003',3,'2023-10-01 10:00:00','2023-10-01 12:15:00',1,3),(4,'CART004',4,'2023-10-01 11:30:00','2023-10-01 13:45:00',1,4),(5,'CART005',5,'2023-10-01 12:15:00','2023-10-01 15:00:00',1,5),(6,'CART006',6,'2023-10-01 14:00:00','2023-10-01 16:30:00',1,6),(7,'CART007',7,'2023-10-01 15:30:00','2023-10-01 17:45:00',1,7),(8,'CART008',8,'2023-10-01 16:45:00','2023-10-01 19:00:00',1,8),(9,'CART009',9,'2023-10-01 18:00:00','2023-10-01 20:15:00',1,9),(10,'CART010',10,'2023-10-01 19:30:00','2023-10-01 21:45:00',1,10),(11,'CART011',11,'2023-10-02 08:30:00','2023-10-02 10:45:00',1,11),(12,'CART012',12,'2023-10-02 09:15:00','2023-10-02 11:30:00',1,12),(13,'CART013',13,'2023-10-02 10:00:00','2023-10-02 12:15:00',1,13),(14,'CART014',14,'2023-10-02 11:30:00','2023-10-02 13:45:00',1,14),(15,'CART015',15,'2023-10-02 12:15:00','2023-10-02 15:00:00',1,15),(16,'CART016',16,'2023-10-02 14:00:00','2023-10-02 16:30:00',1,16),(17,'CART017',17,'2023-10-02 15:30:00','2023-10-02 17:45:00',1,17),(18,'CART018',18,'2023-10-02 16:45:00','2023-10-02 19:00:00',1,18),(19,'CART019',19,'2023-10-02 18:00:00','2023-10-02 20:15:00',1,19),(20,'CART020',20,'2023-10-02 19:30:00','2023-10-02 21:45:00',1,20),(21,'CART021',21,'2023-10-03 08:30:00','2023-10-03 10:45:00',1,21),(22,'CART022',22,'2023-10-03 09:15:00','2023-10-03 11:30:00',1,22),(23,'CART023',23,'2023-10-03 10:00:00','2023-10-03 12:15:00',1,23),(24,'CART024',24,'2023-10-03 11:30:00','2023-10-03 13:45:00',1,24),(25,'CART025',25,'2023-10-03 12:15:00','2023-10-03 15:00:00',1,25),(26,'CART026',26,'2023-10-03 14:00:00','2023-10-03 16:30:00',1,26),(27,'CART027',27,'2023-10-03 15:30:00','2023-10-03 17:45:00',1,27),(28,'CART028',28,'2023-10-03 16:45:00','2023-10-03 19:00:00',1,28),(29,'CART029',29,'2023-10-03 18:00:00','2023-10-03 20:15:00',1,29),(30,'CART030',30,'2023-10-03 19:30:00','2023-10-03 21:45:00',1,30),(31,'CART031',31,'2023-10-04 08:30:00','2023-10-04 10:45:00',1,31),(32,'CART032',32,'2023-10-04 09:15:00','2023-10-04 11:30:00',1,32),(33,'CART033',33,'2023-10-04 10:00:00','2023-10-04 12:15:00',1,33),(34,'CART034',34,'2023-10-04 11:30:00','2023-10-04 13:45:00',1,34),(35,'CART035',35,'2023-10-04 12:15:00','2023-10-04 15:00:00',1,35),(36,'CART036',36,'2023-10-04 14:00:00','2023-10-04 16:30:00',1,36),(37,'CART037',37,'2023-10-04 15:30:00','2023-10-04 17:45:00',1,37),(38,'CART038',38,'2023-10-04 16:45:00','2023-10-04 19:00:00',1,38),(39,'CART039',39,'2023-10-04 18:00:00','2023-10-04 20:15:00',1,39),(40,'CART040',40,'2023-10-04 19:30:00','2023-10-04 21:45:00',1,40),(41,'CART041',41,'2023-10-05 08:30:00','2023-10-05 10:45:00',1,41),(42,'CART042',42,'2023-10-05 09:15:00','2023-10-05 11:30:00',1,42),(43,'CART043',43,'2023-10-05 10:00:00','2023-10-05 12:15:00',1,43),(44,'CART044',44,'2023-10-05 11:30:00','2023-10-05 13:45:00',1,44),(45,'CART045',45,'2023-10-05 12:15:00','2023-10-05 15:00:00',1,45),(46,'CART046',46,'2023-10-05 14:00:00','2023-10-05 16:30:00',1,46),(47,'CART047',47,'2023-10-05 15:30:00','2023-10-05 17:45:00',1,47),(48,'CART048',48,'2023-10-05 16:45:00','2023-10-05 19:00:00',1,48),(49,'CART049',49,'2023-10-05 18:00:00','2023-10-05 20:15:00',1,49),(50,'CART050',50,'2023-10-05 19:30:00','2023-10-05 21:45:00',1,50);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_detail_id` bigint DEFAULT NULL,
  `bill_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_detail_FK_4` (`bill_id`),
  KEY `cart_detail_FK_5` (`product_detail_id`),
  KEY `cart_detail_FK_6` (`cart_id`),
  CONSTRAINT `cart_detail_FK_4` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `cart_detail_FK_5` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`),
  CONSTRAINT `cart_detail_FK_6` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` VALUES (1,1,1,1,50000,2,'2023-11-01 08:00:00','2023-11-01 08:10:00',1),(2,2,2,2,60000,3,'2023-11-02 09:00:00','2023-11-02 09:20:00',1),(3,3,3,3,75000,1,'2023-11-03 10:00:00','2023-11-03 10:15:00',1),(4,4,4,4,55000,4,'2023-11-04 11:00:00','2023-11-04 11:30:00',1),(5,5,5,5,45000,5,'2023-11-05 12:00:00','2023-11-05 12:45:00',1),(6,6,6,6,65000,2,'2023-11-06 13:00:00','2023-11-06 13:20:00',1),(7,7,7,7,70000,3,'2023-11-07 14:00:00','2023-11-07 14:30:00',1),(8,8,8,8,48000,4,'2023-11-08 15:00:00','2023-11-08 15:25:00',1),(9,9,9,9,60000,2,'2023-11-09 16:00:00','2023-11-09 16:15:00',1),(10,10,10,10,72000,1,'2023-11-10 17:00:00','2023-11-10 17:10:00',1),(11,11,11,11,65000,4,'2023-11-11 18:00:00','2023-11-11 18:30:00',1),(12,12,12,12,58000,3,'2023-11-12 19:00:00','2023-11-12 19:20:00',1),(13,13,13,13,52000,2,'2023-11-13 20:00:00','2023-11-13 20:15:00',1),(14,14,14,14,45000,1,'2023-11-14 21:00:00','2023-11-14 21:05:00',1),(15,15,15,15,55000,3,'2023-11-15 22:00:00','2023-11-15 22:15:00',1),(16,16,16,16,70000,5,'2023-11-16 23:00:00','2023-11-16 23:25:00',1),(17,17,17,17,62000,2,'2023-11-17 00:00:00','2023-11-17 00:10:00',1),(18,18,18,18,68000,1,'2023-11-18 01:00:00','2023-11-18 01:05:00',1),(19,19,19,19,48000,4,'2023-11-19 02:00:00','2023-11-19 02:20:00',1),(20,20,20,20,60000,3,'2023-11-20 03:00:00','2023-11-20 03:15:00',1),(21,21,21,21,52000,1,'2023-11-21 04:00:00','2023-11-21 04:10:00',1),(22,22,22,22,67000,2,'2023-11-22 05:00:00','2023-11-22 05:20:00',1),(23,23,23,23,64000,5,'2023-11-23 06:00:00','2023-11-23 06:25:00',1),(24,24,24,24,72000,4,'2023-11-24 07:00:00','2023-11-24 07:30:00',1),(25,25,25,25,56000,2,'2023-11-25 08:00:00','2023-11-25 08:15:00',1),(26,26,26,26,48000,3,'2023-11-26 09:00:00','2023-11-26 09:20:00',1),(27,27,27,27,70000,1,'2023-11-27 10:00:00','2023-11-27 10:10:00',1),(28,28,28,28,75000,2,'2023-11-28 11:00:00','2023-11-28 11:15:00',1),(29,29,29,29,60000,4,'2023-11-29 12:00:00','2023-11-29 12:30:00',1),(30,30,30,30,68000,5,'2023-11-30 13:00:00','2023-11-30 13:45:00',1),(31,31,31,31,52000,2,'2023-12-01 14:00:00','2023-12-01 14:20:00',1),(32,32,32,32,55000,1,'2023-12-02 15:00:00','2023-12-02 15:05:00',1),(33,33,33,33,65000,3,'2023-12-03 16:00:00','2023-12-03 16:15:00',1),(34,34,34,34,70000,2,'2023-12-04 17:00:00','2023-12-04 17:20:00',1),(35,35,35,35,60000,4,'2023-12-05 18:00:00','2023-12-05 18:30:00',1),(36,36,36,36,75000,5,'2023-12-06 19:00:00','2023-12-06 19:45:00',1),(37,37,37,37,62000,2,'2023-12-07 20:00:00','2023-12-07 20:15:00',1),(38,38,38,38,48000,1,'2023-12-08 21:00:00','2023-12-08 21:10:00',1),(39,39,39,39,56000,3,'2023-12-09 22:00:00','2023-12-09 22:15:00',1),(40,40,40,40,58000,4,'2023-12-10 23:00:00','2023-12-10 23:30:00',1),(41,41,41,41,72000,2,'2023-12-11 00:00:00','2023-12-11 00:20:00',1),(42,42,42,42,67000,1,'2023-12-12 01:00:00','2023-12-12 01:05:00',1),(43,43,43,43,52000,3,'2023-12-13 02:00:00','2023-12-13 02:15:00',1),(44,44,44,44,68000,5,'2023-12-14 03:00:00','2023-12-14 03:45:00',1),(45,45,45,45,72000,2,'2023-12-15 04:00:00','2023-12-15 04:20:00',1),(46,46,46,46,60000,4,'2023-12-16 05:00:00','2023-12-16 05:30:00',1),(47,47,47,47,52000,1,'2023-12-17 06:00:00','2023-12-17 06:10:00',1),(48,48,48,48,58000,3,'2023-12-18 07:00:00','2023-12-18 07:15:00',1),(49,49,49,49,62000,2,'2023-12-19 08:00:00','2023-12-19 08:20:00',1),(50,50,50,50,70000,5,'2023-12-20 09:00:00','2023-12-20 09:45:00',1);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

-- DROP TABLE IF EXISTS `departments`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE table `departments` (
-- 	`id` bigint NOT NULL AUTO_INCREMENT,
--     `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
--     `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
-- 	`create_date` datetime DEFAULT CURRENT_TIMESTAMP,
-- 	`update_date` datetime DEFAULT CURRENT_TIMESTAMP,
--     `status` int DEFAULT NULL,
--     )

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  `product_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categories_FK` (`product_type_id`),
  CONSTRAINT `categories_FK` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'CAT001','Áo thun nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(2,'CAT002','Áo sơ mi nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(3,'CAT003','Áo khoác nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(4,'CAT004','Quần jean nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(5,'CAT005','Quần kaki nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(6,'CAT006','Áo thun nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(7,'CAT007','Áo sơ mi nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(8,'CAT008','Áo khoác nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(9,'CAT009','Quần jean nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(10,'CAT010','Quần váy nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(11,'CAT011','Áo thể thao','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(12,'CAT012','Quần thể thao','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(13,'CAT013','Đồ ngủ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,3),(14,'CAT014','Đồ lót','2023-11-11 15:55:26','2023-11-11 15:55:26',1,3),(15,'CAT015','Áo mặc nhà','2023-11-11 15:55:26','2023-11-11 15:55:26',1,3),(16,'CAT016','Áo nỉ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(17,'CAT017','Áo len','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(18,'CAT018','Quần dài','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(19,'CAT019','Quần lửng','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(20,'CAT020','Áo sơ mi công sở','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(21,'CAT021','Áo khoác phao','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(22,'CAT022','Áo khoác phông','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(23,'CAT023','Áo khoác dù','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(24,'CAT024','Áo khoác nỉ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(25,'CAT025','Áo khoác cardigan','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(26,'CAT026','Áo khoác da','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(27,'CAT027','Áo khoác khoá kéo','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(28,'CAT028','Áo khoác jean','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(29,'CAT029','Áo khoác dù nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(30,'CAT030','Áo khoác dù nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(31,'CAT031','Áo khoác phông nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(32,'CAT032','Áo khoác phông nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(33,'CAT033','Áo khoác da nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(34,'CAT034','Áo khoác da nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(35,'CAT035','Áo khoác khoá kéo nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(36,'CAT036','Áo khoác khoá kéo nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(37,'CAT037','Áo khoác jean nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(38,'CAT038','Áo khoác jean nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(39,'CAT039','Áo sơ mi công sở nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(40,'CAT040','Áo sơ mi công sở nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(41,'CAT041','Áo khoác phao nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(42,'CAT042','Áo khoác phao nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(43,'CAT043','Áo mặc nhà nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(44,'CAT044','Áo mặc nhà nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(45,'CAT045','Áo nỉ nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(46,'CAT046','Áo nỉ nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(47,'CAT047','Áo len nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(48,'CAT048','Áo len nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,1),(49,'CAT049','Quần dài nam','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2),(50,'CAT050','Quần dài nữ','2023-11-11 15:55:26','2023-11-11 15:55:26',1,2);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `color_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `color_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'Màu Đỏ','FF0000'),(2,'Màu Xanh Dương','0000FF'),(3,'Màu Xanh Lam','00FF00'),(4,'Màu Hồng','FF00FF'),(5,'Màu Cyan','00FFFF'),(6,'Màu Cam','FF4500'),(7,'Màu Chocolate','D2691E'),(8,'Màu SaddleBrown','8B4513'),(9,'Màu Peru','CD853F'),(10,'Màu Siena','A0522D'),(11,'Màu Brown','A52A2A'),(12,'Màu BurlyWood','DEB887'),(13,'Màu Wheat','F5DEB3'),(14,'Màu Tan','D2B48C'),(15,'Màu RosyBrown','BC8F8F'),(16,'Màu Gold','FFD700'),(17,'Màu Yellow','FFFF00'),(18,'Màu GreenYellow','ADFF2F'),(19,'Màu LimeGreen','32CD32'),(20,'Màu PaleGreen','98FB98'),(21,'Màu Green','228B22'),(22,'Màu DarkGreen','008000'),(23,'Màu DarkOliveGreen','556B2F'),(24,'Màu Olive','808000'),(25,'Màu OliveDrab','6B8E23'),(26,'Màu DarkKhaki','BDB76B'),(27,'Màu LightGoldenrodYellow','FAFAD2'),(28,'Màu LemonChiffon','FFFACD'),(29,'Màu PapayaWhip','FFEFD5'),(30,'Màu PeachPuff','FFDAB9'),(31,'Màu NavajoWhite','FFDEAD'),(32,'Màu Moccasin','FFE4B5'),(33,'Màu Bisque','FFE4C4'),(34,'Màu MistyRose','FFE4E1'),(35,'Màu Linen','FAF0E6'),(36,'Màu AntiqueWhite','FAEBD7'),(37,'Màu OldLace','FDF5E6'),(38,'Màu Seashell','FFF5EE'),(39,'Màu LavenderBlush','FFF0F5'),(40,'Màu Thistle','D8BFD8'),(41,'Màu Plum','DDA0DD'),(42,'Màu Violet','EE82EE'),(43,'Màu Orchid','DA70D6'),(44,'Màu MediumOrchid','8B008B'),(45,'Màu DarkOrchid','9932CC'),(46,'Màu Indigo','4B0082'),(47,'Màu BlueViolet','8A2BE2');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email_verification_status` bit(1) NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email_verification_token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `encrypted_password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `gender` int NOT NULL,
  `dateof_birth` date NOT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `customer_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,_binary '','Nguyễn','Văn A','nguyenvana@gmail.com','token1','hashed_password1',1,'1990-01-01','0987654321','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0001'),(2,_binary '','Trần','Thị B','tranthib@gmail.com','token2','hashed_password2',0,'1992-03-15','0901234567','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0002'),(3,_binary '','Lê','Đức C','leducc@gmail.com','token3','hashed_password3',1,'1985-07-20','0912345678','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0003'),(4,_binary '','Phạm','Thị D','phamthid@gmail.com','token4','hashed_password4',0,'1998-12-05','0976543210','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0004'),(5,_binary '','Hoàng','Minh E','hoangminhe@gmail.com','token5','hashed_password5',1,'1996-11-10','0923456789','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0005'),(6,_binary '','Vũ','Thị F','vuthif@gmail.com','token6','hashed_password6',0,'1980-04-30','0943215678','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0006'),(7,_binary '','Trịnh','Duy G','trinhduyg@gmail.com','token7','hashed_password7',1,'1987-08-18','0998765432','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0007'),(8,_binary '','Mai','Ngọc H','maingoch@gmail.com','token8','hashed_password8',0,'1993-06-25','0967890123','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0008'),(9,_binary '','Lý','Thu I','lythui@gmail.com','token9','hashed_password9',1,'1994-09-03','0934567890','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0009'),(10,_binary '','Nguyễn','Văn J','nguyenvanj@gmail.com','token10','hashed_password10',1,'1991-02-28','0954321098','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0010'),(11,_binary '','Trần','Thu K','tranthuk@gmail.com','token11','hashed_password11',0,'1997-05-12','0912345678','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0011'),(12,_binary '','Lê','Đức L','leducl@gmail.com','token12','hashed_password12',1,'1989-10-09','0932109876','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0012'),(13,_binary '','Phạm','Văn M','phamvanm@gmail.com','token13','hashed_password13',1,'1995-03-14','0976543210','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0013'),(14,_binary '','Hoàng','Thị N','hoangthin@gmail.com','token14','hashed_password14',0,'1993-04-27','0987654321','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0014'),(15,_binary '','Vũ','Quốc O','vuquooc@gmail.com','token15','hashed_password15',1,'1992-12-30','0923456789','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0015'),(16,_binary '','Trịnh','Minh P','trinhminhp@gmail.com','token16','hashed_password16',1,'1988-09-15','0967890123','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0016'),(17,_binary '','Mai','Thị Q','maithiq@gmail.com','token17','hashed_password17',0,'1991-08-23','0934567890','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0017'),(18,_binary '','Lý','Văn R','lyvanr@gmail.com','token18','hashed_password18',1,'1984-07-07','0954321098','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0018'),(19,_binary '','Nguyễn','Thị S','nguyenthis@gmail.com','token19','hashed_password19',0,'1986-06-01','0912345678','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0019'),(20,_binary '','Trần','Văn T','tranvant@gmail.com','token20','hashed_password20',1,'1990-11-05','0932109876','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0020'),(21,_binary '','Lê','Thị U','lethiu@gmail.com','token21','hashed_password21',0,'1995-01-16','0987654321','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0021'),(22,_binary '','Phạm','Đức V','phamducv@gmail.com','token22','hashed_password22',1,'1983-02-14','0923456789','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0022'),(23,_binary '','Hoàng','Thị X','hoangthix@gmail.com','token23','hashed_password23',0,'1998-03-07','0967890123','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0023'),(24,_binary '','Vũ','Minh Y','vuminhy@gmail.com','token24','hashed_password24',1,'1991-07-19','0934567890','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0024'),(25,_binary '','Trịnh','Thị Z','trinhthiz@gmail.com','token25','hashed_password25',0,'1994-05-30','0954321098','2023-10-27 13:14:52','2023-10-27 13:14:52','CUST0025'),(26,_binary '','Nguyễn','Thị L','nguyenthil@gmail.com','token26','hashed_password26',0,'1996-04-03','0987654321','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0026'),(27,_binary '','Trần','Văn M','tranvanm@gmail.com','token27','hashed_password27',1,'1985-12-12','0901234567','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0027'),(28,_binary '','Lê','Đức N','leducn@gmail.com','token28','hashed_password28',0,'1994-11-05','0912345678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0028'),(29,_binary '','Phạm','Thị O','phamthio@gmail.com','token29','hashed_password29',1,'1992-06-18','0976543210','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0029'),(30,_binary '','Hoàng','Minh P','hoangminhp@gmail.com','token30','hashed_password30',0,'1987-09-22','0923456789','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0030'),(31,_binary '','Vũ','Thị Q','vuthiq@gmail.com','token31','hashed_password31',1,'1988-03-20','0967890123','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0031'),(32,_binary '','Trịnh','Duy R','trinhduyr@gmail.com','token32','hashed_password32',0,'1998-02-15','0954321098','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0032'),(33,_binary '','Mai','Ngọc S','maingocs@gmail.com','token33','hashed_password33',1,'1993-07-10','0943215678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0033'),(34,_binary '','Lý','Thu T','lythut@gmail.com','token34','hashed_password34',0,'1989-01-25','0912345678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0034'),(35,_binary '','Nguyễn','Văn U','nguyenvanu@gmail.com','token35','hashed_password35',1,'1995-08-28','0976543210','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0035'),(36,_binary '','Trần','Thị V','tranthiv@gmail.com','token36','hashed_password36',0,'1986-10-30','0987654321','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0036'),(37,_binary '','Lê','Đức X','leducx@gmail.com','token37','hashed_password37',1,'1997-06-05','0923456789','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0037'),(38,_binary '','Phạm','Thị Y','phamthiy@gmail.com','token38','hashed_password38',0,'1991-04-14','0967890123','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0038'),(39,_binary '','Hoàng','Minh Z','hoangminhz@gmail.com','token39','hashed_password39',1,'1994-03-21','0954321098','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0039'),(40,_binary '','Vũ','Thị AA','vuthiaa@gmail.com','token40','hashed_password40',0,'1992-11-15','0934567890','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0040'),(41,_binary '','Trịnh','Duy BB','trinhduybb@gmail.com','token41','hashed_password41',1,'1983-12-10','0912345678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0041'),(42,_binary '','Mai','Ngọc CC','maingoccc@gmail.com','token42','hashed_password42',0,'1984-05-28','0987654321','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0042'),(43,_binary '','Lý','Thu DD','lythudd@gmail.com','token43','hashed_password43',1,'1993-09-20','0943215678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0043'),(44,_binary '','Nguyễn','Văn EE','nguyenvanee@gmail.com','token44','hashed_password44',0,'1998-01-16','0923456789','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0044'),(45,_binary '','Trần','Thị FF','tranthiff@gmail.com','token45','hashed_password45',1,'1990-07-07','0967890123','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0045'),(46,_binary '','Lê','Đức GG','leducgg@gmail.com','token46','hashed_password46',0,'1996-06-23','0912345678','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0046'),(47,_binary '','Phạm','Thị HH','phamthihh@gmail.com','token47','hashed_password47',1,'1995-05-09','0987654321','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0047'),(48,_binary '','Hoàng','Minh II','hoangminhii@gmail.com','token48','hashed_password48',0,'1987-08-30','0934567890','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0048'),(49,_binary '','Vũ','Thị JJ','vuthijj@gmail.com','token49','hashed_password49',1,'1992-04-12','0976543210','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0049'),(50,_binary '','Trịnh','Duy KK','trinhduykk@gmail.com','token50','hashed_password50',0,'1991-02-14','0967890123','2023-10-27 13:15:00','2023-10-27 13:15:00','CUST0050');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employees (
    id bigint NOT NULL AUTO_INCREMENT,
    first_name varchar(50) COLLATE utf8mb4_unicode_520_ci NOT NULL,
    last_name varchar(50) COLLATE utf8mb4_unicode_520_ci NOT NULL,
    gender int NOT NULL,
    dateof_birth date NOT NULL,
    email varchar(120) COLLATE utf8mb4_unicode_520_ci NOT NULL,
    phone_number varchar(20) COLLATE utf8mb4_unicode_520_ci NOT NULL,
    encrypted_password varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
    create_date datetime DEFAULT NULL,
    update_date datetime DEFAULT NULL,
    image longblob,
    employee_code varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
    status int DEFAULT NULL,
    department_id bigint, -- Thêm cột department_id
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES departments(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;INSERT INTO `employees` (
    `first_name`, `last_name`, `gender`, `dateof_birth`,
    `email`, `phone_number`, `encrypted_password`, `create_date`,
    `update_date`, `image`, `employee_code`, `status`, `department_id`
) VALUES
  ('Nguyễn', 'Văn A', 1, '1985-01-15', 'nguyenvana@gmail.com', '0987654321', 'hashed_password1', '2021-09-22 00:00:00', '2023-02-25 00:00:00', NULL, 'EMP0001', 1, 1),
  ('Trần', 'Thị B', 0, '1988-03-25', 'tranthib@gmail.com', '0901234567', 'hashed_password2', '2018-08-04 00:00:00', '2022-07-09 00:00:00', NULL, 'EMP0002', 1, 2),
  ('Lê', 'Đức C', 1, '1990-07-10', 'leducc@gmail.com', '0912345678', 'hashed_password3', '2023-08-16 00:00:00', '2023-08-16 00:00:00', NULL, 'EMP0003', 1, 3),
  ('Phạm', 'Thị D', 0, '1986-12-05', 'phamthid@gmail.com', '0976543210', 'hashed_password4', '2018-05-17 00:00:00', '2023-07-01 00:00:00', NULL, 'EMP0004', 1, 4),
  ('Hoàng', 'Minh E', 1, '1992-08-30', 'hoangminhe@gmail.com', '0923456789', 'hashed_password5', '2023-07-04 00:00:00', '2023-07-04 00:00:00', NULL, 'EMP0005', 1, 5),
  ('Vũ', 'Thị F', 0, '1989-11-20', 'vuthif@gmail.com', '0943215678', 'hashed_password6', '2023-06-19 00:00:00', '2023-12-28 00:00:00', NULL, 'EMP0006', 1, 6),
  ('Trịnh', 'Duy G', 1, '1987-06-15', 'trinhduyg@gmail.com', '0998765432', 'hashed_password7', '2023-07-26 00:00:00', '2023-07-26 00:00:00', NULL, 'EMP0007', 1, 7),
  ('Mai', 'Ngọc H', 0, '1993-04-03', 'maingoch@gmail.com', '0967890123', 'hashed_password8', '2020-06-05 00:00:00', '2020-08-26 00:00:00', NULL, 'EMP0008', 1, 8),
  ('Lý', 'Thu I', 1, '1995-01-08', 'lythui@gmail.com', '0934567890', 'hashed_password9', '2022-08-27 00:00:00', '2023-02-15 00:00:00', NULL, 'EMP0009', 1, 9),
  ('Nguyễn', 'Văn J', 1, '1984-02-18', 'nguyenvanj@gmail.com', '0954321098', 'hashed_password10', '2020-02-18 00:00:00', '2022-07-18 00:00:00', NULL, 'EMP0010', 1, 10),
  ('Trần', 'Thu K', 0, '1991-05-22', 'tranthuk@gmail.com', '0912345678', 'hashed_password11', '2021-09-20 00:00:00', '2021-09-20 00:00:00', NULL, 'EMP0011', 1, 11),
  ('Lê', 'Đức L', 1, '1983-07-09', 'leducl@gmail.com', '0932109876', 'hashed_password12', '2018-11-22 00:00:00', '2019-02-26 00:00:00', NULL, 'EMP0012', 1, 12),
  ('Phạm', 'Văn M', 1, '1997-09-14', 'phamvanm@gmail.com', '0976543210', 'hashed_password13', '2020-07-14 00:00:00', '2023-12-01 00:00:00', NULL, 'EMP0013', 1, 13),
  ('Hoàng', 'Thị N', 0, '1998-06-17', 'hoangthin@gmail.com', '0987654321', 'hashed_password14', '2019-04-21 00:00:00', '2022-06-05 00:00:00', NULL, 'EMP0014', 1, 14),
  ('Vũ', 'Quốc O', 1, '1986-03-20', 'vuquooc@gmail.com', '0923456789', 'hashed_password15', '2020-08-27 00:00:00', '2022-11-01 00:00:00', NULL, 'EMP0015', 1, 15),
  ('Trịnh', 'Minh P', 1, '1985-04-15', 'trinhminhp@gmail.com', '0967890123', 'hashed_password16', '2018-09-04 00:00:00', '2021-11-15 00:00:00', NULL, 'EMP0016', 1, 16),
  ('Mai', 'Thị Q', 0, '1992-08-25', 'maithiq@gmail.com', '0934567890', 'hashed_password17', '2023-01-09 00:00:00', '2023-01-09 00:00:00', NULL, 'EMP0017', 1, 17),
  ('Lý', 'Văn R', 1, '1994-10-28', 'lyvanr@gmail.com', '0954321098', 'hashed_password18', '2019-06-05 00:00:00', '2021-03-15 00:00:00', NULL, 'EMP0018', 1, 18),
  ('Nguyễn', 'Thị S', 0, '1989-06-07', 'nguyenthis@gmail.com', '0912345678', 'hashed_password19', '2020-08-21 00:00:00', '2023-07-27 00:00:00', NULL, 'EMP0019', 1, 19),
  ('Trần', 'Văn T', 1, '1987-07-15', 'tranvant@gmail.com', '0932109876', 'hashed_password20', '2018-04-07 00:00:00', '2019-08-15 00:00:00', NULL, 'EMP0020', 1, 20),
  ('Lê', 'Thị U', 0, '1991-02-16', 'lethiu@gmail.com', '0987654321', 'hashed_password21', '2021-02-01 00:00:00', '2022-09-07 00:00:00', NULL, 'EMP0021', 1, 21),
  ('Phạm', 'Đức V', 1, '1996-01-25', 'phamducv@gmail.com', '0923456789', 'hashed_password22', '2018-12-11 00:00:00', '2023-08-10 00:00:00', NULL, 'EMP0022', 1, 22),
  ('Hoàng', 'Thị X', 0, '1997-03-01', 'hoangthix@gmail.com', '0967890123', 'hashed_password23', '2022-10-19 00:00:00', '2023-11-11 00:00:00', NULL, 'EMP0023', 1, 23),
  ('Vũ', 'Minh Y', 1, '1994-05-08', 'vuminhy@gmail.com', '0934567890', 'hashed_password24', '2018-08-27 00:00:00', '2023-04-03 00:00:00', NULL, 'EMP0024', 1, 24),
  ('Trịnh', 'Thị Z', 0, '1993-11-30', 'trinhthiz@gmail.com', '0954321098', 'hashed_password25', '2021-05-09 00:00:00', '2021-05-09 00:00:00', NULL, 'EMP0025', 1, 25),
  ('Nguyễn', 'Thị AA', 0, '1991-04-05', 'nguyenthi_aa@gmail.com', '0987654321', 'hashed_password26', '2022-05-16 00:00:00', '2022-10-26 00:00:00', NULL, 'EMP0026', 1, 26),
  ('Trần', 'Văn BB', 1, '1990-02-15', 'tranvan_bb@gmail.com', '0901234567', 'hashed_password27', '2018-12-07 00:00:00', '2020-07-06 00:00:00', NULL, 'EMP0027', 1, 27),
  ('Lê', 'Đức CC', 0, '1985-12-20', 'leduc_cc@gmail.com', '0912345678', 'hashed_password28', '2019-02-23 00:00:00', '2021-07-23 00:00:00', NULL, 'EMP0028', 1, 28),
  ('Phạm', 'Thị DD', 1, '1992-06-10', 'phamthi_dd@gmail.com', '0976543210', 'hashed_password29', '2020-08-02 00:00:00', '2020-09-14 00:00:00', NULL, 'EMP0029', 1, 29),
  ('Hoàng', 'Minh EE', 0, '1994-08-30', 'hoangminh_ee@gmail.com', '0923456789', 'hashed_password30', '2019-09-21 00:00:00', '2021-09-22 00:00:00', NULL, 'EMP0030', 1, 30),
  ('Trịnh', 'Duy GG', 0, '1986-09-15', 'trinhduy_gg@gmail.com', '0998765432', 'hashed_password32', '2020-11-27 00:00:00', '2020-11-27 00:00:00', NULL, 'EMP0032', 1, 31),
  ('Mai', 'Ngọc HH', 1, '1993-02-10', 'maingoc_hh@gmail.com', '0967890123', 'hashed_password33', '2020-04-13 00:00:00', '2020-06-08 00:00:00', NULL, 'EMP0033', 1, 32),
  ('Lý', 'Thu II', 0, '1997-01-25', 'lythu_ii@gmail.com', '0934567890', 'hashed_password34', '2018-09-04 00:00:00', '2021-04-20 00:00:00', NULL, 'EMP0034', 1, 33),
  ('Nguyễn', 'Văn JJ', 1, '1984-06-07', 'nguyenvan_jj@gmail.com', '0954321098', 'hashed_password35', '2022-09-06 00:00:00', '2023-01-15 00:00:00', NULL, 'EMP0035', 1, 34),
  ('Trần', 'Thu KK', 0, '1991-07-15', 'tran_thu_kk@gmail.com', '0912345678', 'hashed_password36', '2020-04-13 00:00:00', '2020-04-13 00:00:00', NULL, 'EMP0036', 1, 35),
  ('Lê', 'Đức LL', 1, '1983-08-09', 'leduc_ll@gmail.com', '0932109876', 'hashed_password37', '2019-04-09 00:00:00', '2022-01-21 00:00:00', NULL, 'EMP0037', 1, 36),
  ('Phạm', 'Văn MM', 1, '1998-09-14', 'pham_van_mm@gmail.com', '0976543210', 'hashed_password38', '2021-01-12 00:00:00', '2022-08-27 00:00:00', NULL, 'EMP0038', 1, 37),
  ('Hoàng', 'Thị NN', 0, '1998-10-17', 'hoang_thi_nn@gmail.com', '0987654321', 'hashed_password39', '2021-04-17 00:00:00', '2021-04-17 00:00:00', NULL, 'EMP0039', 1, 38),
  ('Vũ', 'Quốc OO', 1, '1986-03-20', 'vu_quoc_oo@gmail.com', '0923456789', 'hashed_password40', '2020-01-27 00:00:00', '2021-12-05 00:00:00', NULL, 'EMP0040', 1, 39),
  ('Trịnh', 'Minh PP', 1, '1987-04-15', 'trinh_minh_pp@gmail.com', '0967890123', 'hashed_password41', '2019-01-14 00:00:00', '2019-01-14 00:00:00', NULL, 'EMP0041', 1, 40),
  ('Mai', 'Thị QQ', 0, '1992-08-25', 'mai_thi_qq@gmail.com', '0934567890', 'hashed_password42', '2018-07-06 00:00:00', '2020-10-12 00:00:00', NULL, 'EMP0042', 1, 41),
  ('Lý', 'Văn RR', 1, '1994-10-28', 'ly_van_rr@gmail.com', '0954321098', 'hashed_password43', '2022-07-09 00:00:00', '2023-12-22 00:00:00', NULL, 'EMP0043', 1, 42),
  ('Nguyễn', 'Thị SS', 0, '1989-06-07', 'nguyen_thi_ss@gmail.com', '0912345678', 'hashed_password44', '2018-11-15 00:00:00', '2022-01-09 00:00:00', NULL, 'EMP0044', 1, 43),
  ('Trần', 'Văn TT', 1, '1987-07-15', 'tran_van_tt@gmail.com', '0932109876', 'hashed_password45', '2021-08-15 00:00:00', '2022-08-15 00:00:00', NULL, 'EMP0045', 1, 44),
  ('Lê', 'Thị UU', 0, '1991-02-16', 'le_thi_uu@gmail.com', '0987654321', 'hashed_password46', '2019-10-27 00:00:00', '2021-09-02 00:00:00', NULL, 'EMP0046', 1, 45),
  ('Phạm', 'Đức VV', 1, '1996-01-25', 'pham_duc_vv@gmail.com', '0923456789', 'hashed_password47', '2022-12-10 00:00:00', '2022-12-10 00:00:00', NULL, 'EMP0047', 1, 46),
  ('Hoàng', 'Thị XX', 0, '1997-03-01', 'hoang_thi_xx@gmail.com', '0967890123', 'hashed_password48', '2019-03-09 00:00:00', '2021-05-20 00:00:00', NULL, 'EMP0048', 1, 47),
  ('Vũ', 'Minh YY', 1, '1994-05-08', 'vu_minh_yy@gmail.com', '0934567890', 'hashed_password49', '2023-02-14 00:00:00', '2023-02-14 00:00:00', NULL, 'EMP0049', 1, 48),
  ('Trịnh', 'Thị ZZ', 0, '1993-11-30', 'trinh_thi_zz@gmail.com', '0954321098', 'hashed_password50', '2019-11-15 00:00:00', '2019-11-15 00:00:00', NULL, 'EMP0050', 1, 49),
  ('Nguyễn', 'Thị AAA', 0, '1991-04-05', 'nguyen_thi_aaa@gmail.com', '0987654321', 'hashed_password51', '2021-02-26 00:00:00', '2021-02-26 00:00:00', NULL, 'EMP0051', 1, 50);
  /*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `image_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `images_FK` (`product_id`),
  CONSTRAINT `images_FK` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'https://example.com/image1.jpg',1,'Image 1'),(2,'https://example.com/image2.jpg',2,'Image 2'),(3,'https://example.com/image3.jpg',3,'Image 3'),(4,'https://example.com/image4.jpg',4,'Image 4'),(5,'https://example.com/image5.jpg',5,'Image 5'),(6,'https://example.com/image6.jpg',6,'Image 6'),(7,'https://example.com/image7.jpg',7,'Image 7'),(8,'https://example.com/image8.jpg',8,'Image 8'),(9,'https://example.com/image9.jpg',9,'Image 9'),(10,'https://example.com/image10.jpg',10,'Image 10'),(11,'https://example.com/image11.jpg',11,'Image 11'),(12,'https://example.com/image12.jpg',12,'Image 12'),(13,'https://example.com/image13.jpg',13,'Image 13'),(14,'https://example.com/image14.jpg',14,'Image 14'),(15,'https://example.com/image15.jpg',15,'Image 15'),(16,'https://example.com/image16.jpg',16,'Image 16'),(17,'https://example.com/image17.jpg',17,'Image 17'),(18,'https://example.com/image18.jpg',18,'Image 18'),(19,'https://example.com/image19.jpg',19,'Image 19'),(20,'https://example.com/image20.jpg',20,'Image 20'),(21,'https://example.com/image21.jpg',21,'Image 21'),(22,'https://example.com/image22.jpg',22,'Image 22'),(23,'https://example.com/image23.jpg',23,'Image 23'),(24,'https://example.com/image24.jpg',24,'Image 24'),(25,'https://example.com/image25.jpg',25,'Image 25'),(26,'https://example.com/image26.jpg',26,'Image 26'),(27,'https://example.com/image27.jpg',27,'Image 27'),(28,'https://example.com/image28.jpg',28,'Image 28'),(29,'https://example.com/image29.jpg',29,'Image 29'),(30,'https://example.com/image30.jpg',30,'Image 30'),(31,'https://example.com/image31.jpg',31,'Image 31'),(32,'https://example.com/image32.jpg',32,'Image 32'),(33,'https://example.com/image33.jpg',33,'Image 33'),(34,'https://example.com/image34.jpg',34,'Image 34'),(35,'https://example.com/image35.jpg',35,'Image 35'),(36,'https://example.com/image36.jpg',36,'Image 36'),(37,'https://example.com/image37.jpg',37,'Image 37'),(38,'https://example.com/image38.jpg',38,'Image 38'),(39,'https://example.com/image39.jpg',39,'Image 39'),(40,'https://example.com/image40.jpg',40,'Image 40'),(41,'https://example.com/image41.jpg',41,'Image 41'),(42,'https://example.com/image42.jpg',42,'Image 42'),(43,'https://example.com/image43.jpg',43,'Image 43'),(44,'https://example.com/image44.jpg',44,'Image 44'),(45,'https://example.com/image45.jpg',45,'Image 45'),(46,'https://example.com/image46.jpg',46,'Image 46'),(47,'https://example.com/image47.jpg',47,'Image 47'),(48,'https://example.com/image48.jpg',48,'Image 48'),(49,'https://example.com/image49.jpg',49,'Image 49'),(50,'https://example.com/image50.jpg',50,'Image 50');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `material_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'VNMA001','Bamboo'),(2,'VNMA002','Silk'),(3,'VNMA003','Rattan'),(4,'VNMA004','Lacquer'),(5,'VNMA005','Terracotta'),(6,'VNMA006','Basketwork'),(7,'VNMA007','Ceramic'),(8,'VNMA008','Wood'),(9,'VNMA009','Leather'),(10,'VNMA010','Coconut Shell'),(11,'VNMA011','Bronze'),(12,'VNMA012','Brass'),(13,'VNMA013','Stone'),(14,'VNMA014','Mother of Pearl'),(15,'VNMA015','Porcelain'),(16,'VNMA016','Paper'),(17,'VNMA017','Marble'),(18,'VNMA018','Clay'),(19,'VNMA019','Glass'),(20,'VNMA020','Sandalwood'),(21,'VNMA021','Cotton'),(22,'VNMA022','Hemp'),(23,'VNMA023','Bamboo Fiber'),(24,'VNMA024','Silk Thread'),(25,'VNMA025','Bamboo Leaves'),(26,'VNMA026','Coconut Fiber'),(27,'VNMA027','Rattan Reed'),(28,'VNMA028','Palm Leaves'),(29,'VNMA029','Bamboo Shoots'),(30,'VNMA030','Rice Straw'),(31,'VNMA031','Mulberry Paper'),(32,'VNMA032','Lacquerware'),(33,'VNMA033','Woven Mats'),(34,'VNMA034','Terracotta Pots'),(35,'VNMA035','Wood Carvings'),(36,'VNMA036','Leather Wallets'),(37,'VNMA037','Coconut Shell Crafts'),(38,'VNMA038','Bronze Sculptures'),(39,'VNMA039','Brass Utensils'),(40,'VNMA040','Stone Statues'),(41,'VNMA041','Mother of Pearl Inlay'),(42,'VNMA042','Porcelain Plates'),(43,'VNMA043','Paper Lanterns'),(44,'VNMA044','Marble Sculptures'),(45,'VNMA045','Clay Figurines'),(46,'VNMA046','Glass Vases'),(47,'VNMA047','Sandalwood Incense'),(48,'VNMA048','Cotton Fabrics'),(49,'VNMA049','Hemp Clothing'),(50,'VNMA050','Bamboo Fiber Products');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_tokens`
--

DROP TABLE IF EXISTS `password_reset_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `password_reset_tokens_FK` (`customer_id`),
  CONSTRAINT `password_reset_tokens_FK` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_tokens`
--

LOCK TABLES `password_reset_tokens` WRITE;
/*!40000 ALTER TABLE `password_reset_tokens` DISABLE KEYS */;
INSERT INTO `password_reset_tokens` VALUES (1,1,'token_1'),(2,2,'token_2'),(3,3,'token_3'),(4,4,'token_4'),(5,5,'token_5'),(6,6,'token_6'),(7,7,'token_7'),(8,8,'token_8'),(9,9,'token_9'),(10,10,'token_10'),(11,11,'token_11'),(12,12,'token_12'),(13,13,'token_13'),(14,14,'token_14'),(15,15,'token_15'),(16,16,'token_16'),(17,17,'token_17'),(18,18,'token_18'),(19,19,'token_19'),(20,20,'token_20'),(21,21,'token_21'),(22,22,'token_22'),(23,23,'token_23'),(24,24,'token_24'),(25,25,'token_25'),(26,26,'token_26'),(27,27,'token_27'),(28,28,'token_28'),(29,29,'token_29'),(30,30,'token_30'),(31,31,'token_31'),(32,32,'token_32'),(33,33,'token_33'),(34,34,'token_34'),(35,35,'token_35'),(36,36,'token_36'),(37,37,'token_37'),(38,38,'token_38'),(39,39,'token_39'),(40,40,'token_40'),(41,41,'token_41'),(42,42,'token_42'),(43,43,'token_43'),(44,44,'token_44'),(45,45,'token_45'),(46,46,'token_46'),(47,47,'token_47'),(48,48,'token_48'),(49,49,'token_49'),(50,50,'token_50');
/*!40000 ALTER TABLE `password_reset_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `color_id` bigint DEFAULT NULL,
  `size_id` bigint DEFAULT NULL,
  `material_id` bigint DEFAULT NULL,
  `waistband_id` bigint DEFAULT NULL,
  `default_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  `product_detail_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_detail_FK` (`color_id`),
  KEY `product_detail_FK_22` (`size_id`),
  KEY `product_detail_FK_14` (`waistband_id`),
  KEY `product_detail_FK_15` (`product_id`),
  KEY `product_detail_FK_17` (`material_id`),
  CONSTRAINT `product_detail_FK` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `product_detail_FK_14` FOREIGN KEY (`waistband_id`) REFERENCES `waistbands` (`id`),
  CONSTRAINT `product_detail_FK_15` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `product_detail_FK_17` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `product_detail_FK_22` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,1,1,1,1,1,500000,650000,50,'2023-07-28 00:00:00','2023-07-28 00:00:00',1,'PD10001'),(2,2,2,2,2,2,600000,750000,60,'2019-11-28 00:00:00','2019-11-28 00:00:00',0,'PD10002'),(3,3,3,3,3,3,700000,850000,70,'2022-09-09 00:00:00','2022-09-09 00:00:00',1,'PD10003'),(4,4,4,4,4,4,800000,950000,80,'2022-11-28 00:00:00','2022-11-28 00:00:00',0,'PD10004'),(5,5,5,5,5,5,900000,1050000,90,'2022-08-20 00:00:00','2022-08-20 00:00:00',1,'PD10005'),(6,6,6,6,6,6,1000000,1150000,100,'2023-04-12 00:00:00','2023-04-12 00:00:00',0,'PD10006'),(7,7,7,7,7,7,1100000,1250000,110,'2022-07-05 00:00:00','2022-07-05 00:00:00',1,'PD10007'),(8,8,8,8,8,8,1200000,1350000,120,'2020-03-20 00:00:00','2020-03-20 00:00:00',0,'PD10008'),(9,9,9,9,9,9,1300000,1450000,130,'2020-06-06 00:00:00','2020-08-22 00:00:00',1,'PD10009'),(10,10,10,10,10,10,1400000,1550000,140,'2018-10-21 00:00:00','2020-01-28 00:00:00',0,'PD10010'),(11,11,11,11,11,11,1500000,1650000,150,'2021-05-21 00:00:00','2022-03-05 00:00:00',1,'PD10011'),(12,12,12,12,12,12,1600000,1750000,160,'2018-09-15 00:00:00','2020-08-26 00:00:00',0,'PD10012'),(13,13,13,13,13,13,1700000,1850000,170,'2021-01-13 00:00:00','2021-01-13 00:00:00',1,'PD10013'),(14,14,14,14,14,14,1800000,1950000,180,'2021-03-07 00:00:00','2021-03-07 00:00:00',0,'PD10014'),(15,15,15,15,15,15,1900000,2050000,190,'2021-03-14 00:00:00','2022-06-23 00:00:00',1,'PD10015'),(16,16,16,16,16,16,2000000,2150000,200,'2022-03-22 00:00:00','2022-03-22 00:00:00',0,'PD10016'),(17,17,17,17,17,17,2100000,2250000,210,'2023-05-03 00:00:00','2023-05-03 00:00:00',1,'PD10017'),(18,18,18,18,18,18,2200000,2350000,220,'2023-07-07 00:00:00','2023-07-07 00:00:00',0,'PD10018'),(19,19,19,19,19,19,2300000,2450000,230,'2018-06-06 00:00:00','2021-12-13 00:00:00',1,'PD10019'),(20,20,20,20,20,20,2400000,2550000,240,'2019-10-06 00:00:00','2021-08-02 00:00:00',0,'PD10020'),(21,21,21,21,21,21,2500000,2650000,250,'2021-08-09 00:00:00','2022-07-15 00:00:00',1,'PD10021'),(22,22,22,22,22,22,2600000,2750000,260,'2018-01-25 00:00:00','2018-03-16 00:00:00',0,'PD10022'),(23,23,23,23,23,23,2700000,2850000,270,'2018-11-27 00:00:00','2019-05-02 00:00:00',1,'PD10023'),(24,24,24,24,24,24,2800000,2950000,280,'2019-11-19 00:00:00','2022-11-26 00:00:00',0,'PD10024'),(25,25,25,25,25,25,2900000,3050000,290,'2023-02-18 00:00:00','2023-02-18 00:00:00',1,'PD10025'),(26,26,26,26,26,26,2600000,2750000,260,'2018-10-19 00:00:00','2018-10-19 00:00:00',0,'PD10026'),(27,27,27,27,27,27,2700000,2850000,270,'2021-04-22 00:00:00','2023-09-12 00:00:00',1,'PD10027'),(28,28,28,28,28,28,2800000,2950000,280,'2018-12-19 00:00:00','2020-12-20 00:00:00',0,'PD10028'),(29,29,29,29,29,29,2900000,3050000,290,'2021-01-12 00:00:00','2023-02-03 00:00:00',1,'PD10029'),(30,30,30,30,30,30,3000000,3150000,300,'2023-08-02 00:00:00','2023-08-02 00:00:00',0,'PD10030'),(31,31,31,31,31,31,3100000,3250000,310,'2022-10-19 00:00:00','2022-10-19 00:00:00',1,'PD10031'),(32,32,32,32,32,32,3200000,3350000,320,'2020-05-17 00:00:00','2023-08-11 00:00:00',0,'PD10032'),(33,33,33,33,33,33,3300000,3450000,330,'2018-01-01 00:00:00','2018-03-27 00:00:00',1,'PD10033'),(34,34,34,34,34,34,3400000,3550000,340,'2018-09-02 00:00:00','2019-05-22 00:00:00',0,'PD10034'),(35,35,35,35,35,35,3500000,3650000,350,'2023-11-26 00:00:00','2023-11-26 00:00:00',1,'PD10035'),(36,36,36,36,36,36,3600000,3750000,360,'2018-07-08 00:00:00','2022-04-03 00:00:00',0,'PD10036'),(37,37,37,37,37,37,3700000,3850000,370,'2020-03-14 00:00:00','2023-11-21 00:00:00',1,'PD10037'),(38,38,38,38,38,38,3800000,3950000,380,'2023-10-03 00:00:00','2023-10-03 00:00:00',0,'PD10038'),(39,39,39,39,39,39,3900000,4050000,390,'2023-07-20 00:00:00','2023-07-20 00:00:00',1,'PD10039'),(40,40,40,40,40,40,4000000,4150000,400,'2020-07-06 00:00:00','2020-07-06 00:00:00',0,'PD10040'),(41,41,41,41,41,41,4100000,4250000,410,'2022-11-08 00:00:00','2022-11-08 00:00:00',1,'PD10041'),(42,42,42,42,42,42,4200000,4350000,420,'2021-03-11 00:00:00','2021-03-11 00:00:00',0,'PD10042'),(43,43,43,43,43,43,4300000,4450000,430,'2023-02-20 00:00:00','2023-02-20 00:00:00',1,'PD10043'),(44,44,44,44,44,44,4400000,4550000,440,'2022-07-20 00:00:00','2022-07-20 00:00:00',0,'PD10044'),(45,45,45,45,45,45,4500000,4650000,450,'2018-10-27 00:00:00','2019-08-03 00:00:00',1,'PD10045'),(46,46,46,46,46,46,4600000,4750000,460,'2021-01-10 00:00:00','2021-01-10 00:00:00',0,'PD10046'),(47,47,47,47,47,47,4700000,4850000,470,'2021-06-18 00:00:00','2022-10-25 00:00:00',1,'PD10047'),(48,48,48,48,48,48,4800000,4950000,480,'2023-09-01 00:00:00','2023-09-01 00:00:00',0,'PD10048'),(49,49,49,49,49,49,4900000,5050000,490,'2022-10-11 00:00:00','2022-10-11 00:00:00',1,'PD10049'),(50,50,50,50,50,50,5000000,5150000,500,'2021-12-23 00:00:00','2021-12-23 00:00:00',0,'PD10050');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_type_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Quần','Quan'),(2,'Áo','Ao'),(3,'Quần dài','QuDa'),(4,'Áo len','AoLe'),(5,'Áo sơ mi','AoSMi'),(6,'Quần jeans','QuJe'),(7,'Áo phông','AoPho'),(8,'Áo khoác nỉ','AoKNi'),(9,'Áo thun','AoThu'),(10,'Quần short','QuSh'),(11,'Váy đầm','VaDa'),(12,'Áo dạ','AoDa'),(13,'Áo vest','AoVe'),(14,'Áo ba lỗ','AoBL'),(15,'Quần nỉ','QuNi'),(16,'Áo dài','AoDa2'),(17,'Quần tây','QuTa'),(18,'Quần kaki','QuKa'),(19,'Áo thể thao','AoTTh'),(20,'Quần lửng','QuLung'),(21,'Áo sơ mi nữ','AoSMiN'),(22,'Áo kiểu','AoKie'),(23,'Quần áo bé trai','QuBeT'),(24,'Áo bé gái','AoBeG'),(25,'Áo dài nữ','AoDaN'),(26,'Quần áo bé gái','QuBeG'),(27,'Áo khoác bé trai','AoKoBT'),(28,'Áo khoác bé gái','AoKoBG'),(29,'Áo thun bé trai','AoThuBT'),(30,'Áo thun bé gái','AoThuBG'),(31,'Áo ba lỗ bé trai','AoBLBT'),(32,'Áo ba lỗ bé gái','AoBLBG'),(33,'Quần bé trai','QuBT'),(34,'Quần bé gái','QuBG'),(35,'Áo sơ mi bé trai','AoSMiBT'),(36,'Áo sơ mi bé gái','AoSMiBG'),(37,'Áo kiểu bé trai','AoKieBT'),(38,'Áo kiểu bé gái','AoKieBG'),(39,'Váy bé gái','VaBG'),(40,'Áo khoác bé trai nữ','AoKoBTN'),(41,'Áo khoác bé gái nữ','AoKoBGN'),(42,'Áo thun bé trai nữ','AoThuBTN'),(43,'Áo thun bé gái nữ','AoThuBGN'),(44,'Áo ba lỗ bé trai nữ','AoBLBTN'),(45,'Áo ba lỗ bé gái nữ','AoBLBGN'),(46,'Quần bé trai nữ','QuBTN'),(47,'Quần bé gái nữ','QuBGN'),(48,'Áo sơ mi bé trai nữ','AoSMiBTN'),(49,'Áo sơ mi bé gái nữ','AoSMiBGN'),(50,'Áo kiểu bé trai nữ','AoKieBTN'),(51,'Áo kiểu bé gái nữ','AoKieBGN'),(52,'Giày dép','GiaD'),(53,'Áo khoác','AoKo');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  `main_image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products_FK` (`category_id`),
  KEY `products_FK_1` (`brand_id`),
  CONSTRAINT `products_FK` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `products_FK_1` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Áo khoác nam A1',1,1,'https://lzd-img-global.slatic.net/g/p/353bd4baf65ec3b997418fc39a1753f1.jpg_720x720q80.jpg','Áo khoác nam thời trang','2021-07-13 00:00:00','2021-07-13 00:00:00',1,'A1',100,750000),(2,'Quần jeans nam Q1',2,2,'https://file.hstatic.net/1000192516/file/shop_quan_jean_nam_dep_o_tphcm___thoi_trang_sea_collection_-_hinh1_fc19bb9e0cf44a2c94ba7c02d826ba9d_grande.jpg','Quần jeans nam đẹp','2020-07-04 00:00:00','2020-07-04 00:00:00',1,'Q1',150,850000),(3,'Áo thun nam T1',3,3,'https://pos.nvncdn.net/8ca22b-20641/ps/20230508_WnLQT1gqir.jpeg','Áo thun nam thoải mái','2022-01-13 00:00:00','2023-03-24 00:00:00',1,'T1',200,350000),(4,'Áo sơ mi nam S1',4,4,'https://img.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/_/ao_so_mi_nam_trung_nien_onymax_lich_lam_91d7.jpg','Áo sơ mi nam lịch lãm','2021-04-18 00:00:00','2022-10-03 00:00:00',1,'S1',80,650000),(5,'Quần tây nam QT1',5,5,'https://pos.nvncdn.net/5048a3-93414/ps/20211104_Qrc838JJQCFbPNeFsh1b7ovk.jpg','Quần tây nam cao cấp','2023-10-01 00:00:00','2023-10-01 00:00:00',1,'QT1',120,950000),(6,'Áo khoác nữ A2',6,6,'http://gaugaushop.com/plugins/responsive_filemanager/source/san%20pham/AoKhoacNu/GAK437-1/O1CN013ZjBuo1fP7wiSRF3z_!!4043313998.jpg','Áo khoác nữ đẹp','2023-11-01 00:00:00','2023-11-01 00:00:00',1,'A2',90,850000),(7,'Quần jeans nữ Q2',7,7,'http://thoitrangbanmai.vn/upload/baiviet/dx58-8570.jpg','Quần jeans nữ thời trang','2023-06-24 00:00:00','2023-06-24 00:00:00',1,'Q2',130,750000),(8,'Áo thun nữ T2',8,8,'https://product.hstatic.net/200000520507/product/t_8cd6ed2af63342e1af37124702e33885_0db49a23d9414e92b56ad50229e62669_30b2e11b93a245da92e99270cbafe272_master.jpg','Áo thun nữ thoải mái','2020-11-03 00:00:00','2020-11-03 00:00:00',1,'T2',180,350000),(9,'Áo sơ mi nữ S2',9,9,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy8UVh7FCyvwJwwkB2CuTypAv9s_Thqkl0s8Uvx8sShlKSfvY3gZPoPw6SLhO9ScMkRjc&usqp=CAU','Áo sơ mi nữ lịch lãm','2019-02-20 00:00:00','2023-08-24 00:00:00',1,'S2',70,650000),(10,'Quần tây nữ QT2',10,10,'https://i.pinimg.com/736x/3f/4a/2b/3f4a2bc9b533041074660b8108679b88.jpg','Quần tây nữ cao cấp','2022-08-17 00:00:00','2022-08-17 00:00:00',1,'QT2',110,950000),(11,'Áo khoác bé trai ABT1',11,11,'https://cdn-v2.kidsplaza.vn/media/catalog/product/a/o/ao-khoac-phao-in-hinh-mat-gau-dang-yeu-cho-be-trai-3.jpg','Áo khoác bé trai đáng yêu','2023-09-04 00:00:00','2023-09-04 00:00:00',1,'ABT1',50,450000),(12,'Quần áo bé trai QABT1',12,12,'https://c4img.onlinefriday.vn/r600x600/11_2022/e/fc/d/2707251552_1668614621.jpg','Quần áo bé trai dễ thương','2020-10-06 00:00:00','2020-10-06 00:00:00',1,'QABT1',60,550000),(13,'Áo khoác bé gái ABG1',13,13,'https://bizweb.dktcdn.net/thumb/1024x1024/100/355/711/products/51975d0e-18e5-4fa2-be81-528edf6b22b4.jpg?v=1641880222003','Áo khoác bé gái xinh xắn','2021-10-19 00:00:00','2021-10-19 00:00:00',1,'ABG1',40,450000),(14,'Quần áo bé gái QABG1',14,14,'https://cdn-v2.kidsplaza.vn/media/catalog/product/b/o/bo-quan-ao-dai-tay-be-gai-cute-in-meo-hong-hn22d-1.jpg','Quần áo bé gái dễ thương','2019-01-13 00:00:00','2023-02-07 00:00:00',1,'QABG1',55,550000),(15,'Áo ba lỗ bé trai ABLT1',15,15,'https://vn-test-11.slatic.net/p/534e1419f82479f1fa11db3eb3f45345.jpg','Áo ba lỗ bé trai thể thao','2019-11-13 00:00:00','2020-03-23 00:00:00',1,'ABLT1',30,350000),(16,'Quần bé trai QB1',16,16,'https://bizweb.dktcdn.net/100/191/440/products/03ff62cd-e03b-484f-8602-f234cad593d0.jpg?v=1656023445360','Quần bé trai thoải mái','2021-02-02 00:00:00','2021-02-02 00:00:00',1,'QB1',70,450000),(17,'Áo ba lỗ bé gái ABLG1',17,17,'https://lzd-img-global.slatic.net/g/ff/kf/S7876e9089e5e4f1b8ee63fa09069dc36E.jpg_720x720q80.jpg','Áo ba lỗ bé gái thể thao','2020-08-02 00:00:00','2022-09-09 00:00:00',1,'ABLG1',25,350000),(18,'Quần bé gái QBG1',18,18,'https://bevadochoi.com/Images/Editor/images/quan%20jean/bo%20be%20gai%208.jpg','Quần bé gái dễ thương','2021-04-28 00:00:00','2022-02-02 00:00:00',1,'QBG1',60,450000),(19,'Áo thể thao nam ATH1',19,19,'https://peaksport.vn/wp-content/uploads/2023/07/DF632921-1-768x768.jpg','Áo thể thao nam thời trang','2019-01-22 00:00:00','2020-04-03 00:00:00',1,'ATH1',120,550000),(20,'Quần lửng nam QL1',20,20,'https://mcdn.coolmate.me/image/March2023/danh-sach-10-mau-quan-short-nam-mac-o-nha-cuc-thoai-mai-8.jpgproductsemployees','Quần lửng nam thoải mái','2020-04-08 00:00:00','2021-08-21 00:00:00',1,'QL1',90,450000),(21,'Áo dài nữ AD1',21,21,'https://nvhphunu.vn/wp-content/uploads/2023/10/AO-DAI-2.jpg','Áo dài nữ truyền thống','2019-10-19 00:00:00','2019-12-14 00:00:00',1,'AD1',70,650000),(22,'Quần lửng nữ QL2',22,22,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRxLGJkAi4S7qFewuc5552zoQoMgak3woyfgd1TgO17a8p3cIOIVRN28ff_cClYY-YuHw&usqp=CAU','Quần lửng nữ thời trang','2021-12-12 00:00:00','2023-03-20 00:00:00',1,'QL2',110,550000),(23,'Áo sơ mi bé trai ASMBT1',23,23,'https://bizweb.dktcdn.net/thumb/1024x1024/100/399/895/products/2e3bd391-28ed-4ed3-924e-888525a16869.jpg?v=1639285470670','Áo sơ mi bé trai đáng yêu','2019-04-25 00:00:00','2021-11-28 00:00:00',1,'ASMBT1',60,450000),(24,'Quần áo bé trai QB2',24,24,'https://c4img.onlinefriday.vn/r600x600/11_2022/e/fc/d/2707251552_1668614621.jpg','Quần áo bé trai dễ thương','2023-12-03 00:00:00','2023-12-03 00:00:00',1,'QB2',50,550000),(25,'Áo sơ mi bé gái ASM1',25,25,'https://cf.shopee.vn/file/2e82b25b512ebabcee72b5ff96d756ac','Áo sơ mi bé gái xinh xắn','2022-07-24 00:00:00','2022-07-24 00:00:00',1,'ASM1',40,650000),(26,'Quần áo bé gái QB3',26,26,'https://down-vn.img.susercontent.com/file/633732e33c5c07215242fdb02cf0c537','Quần áo bé gái dễ thương','2020-06-01 00:00:00','2020-09-07 00:00:00',1,'QB3',30,750000),(27,'Áo khoác bé trai AKBTN1',27,27,'https://media3.scdn.vn/img4/2022/08_01/N8tYvK6Kd9nupXBBl5NG_simg_de2fe0_500x500_maxb.jpg','Áo khoác bé trai đẹp','2023-11-02 00:00:00','2023-11-02 00:00:00',1,'AKBTN1',25,850000),(28,'Áo khoác bé gái AKBN1',28,28,'https://cf.shopee.vn/file/54b50823ff23e3c3691e990cfa2df519','Áo khoác bé gái thoải mái','2019-04-03 00:00:00','2020-08-16 00:00:00',1,'AKBN1',35,750000),(29,'Áo thun bé trai ATBTN2',29,29,'https://scontent.webpluscnd.net/photos-df/a-0/2586-2113169-1/ao-thun-tay-ngan-cho-be-trai-apb03.png?atk=4a5908b306429b9342163bd761fb141b','Áo thun bé trai thoải mái','2020-01-28 00:00:00','2020-01-28 00:00:00',1,'ATBTN2',45,350000),(30,'Áo thun bé gái ATBG1',30,30,'https://cf.shopee.vn/file/43190b64195bd147c27d27bfc59f5624','Áo thun bé gái thời trang','2022-02-14 00:00:00','2023-02-09 00:00:00',1,'ATBG1',55,350000),(31,'Áo ba lỗ bé trai ABLBTN1',31,31,'https://www.besanhdieu.com/images/stories/virtuemart/product/AS122-12.jpg','Áo ba lỗ bé trai thể thao','2023-10-13 00:00:00','2023-10-13 00:00:00',1,'ABLBTN1',60,450000),(32,'Quần Áo ba lỗ bé gái ABLBG1',32,32,'https://phucankids.com/wp-content/uploads/2020/06/B%E1%BB%98-BA-L%E1%BB%96-TH%E1%BB%82-THAO-B%C3%89-G%C3%81I-3.jpg','Quần Áo ba lỗ bé gái thể thao','2020-04-20 00:00:00','2021-04-01 00:00:00',1,'ABLBG1',40,450000),(33,'Quần bé trai QBTN1',33,33,'https://salt.tikicdn.com/cache/750x750/ts/product/4d/9e/af/46f8faa05b6c10a8d2194ad5ed57338c.jpg.webp','Quần bé trai thoải mái','2022-01-19 00:00:00','2023-04-05 00:00:00',1,'QBTN1',35,350000),(34,'Quần bé gái QBGN1',34,34,'https://my-test-11.slatic.net/p/cedf431713e00b18f4d5b0ae499c1a11.jpg','Quần bé gái thời trang','2023-09-12 00:00:00','2023-09-12 00:00:00',1,'QBGN1',45,550000),(35,'Áo sơ mi bé trai ASMBTN1',35,35,'https://salt.tikicdn.com/cache/750x750/ts/product/e2/dd/87/18600d896a06f7d88375db42853706a6.jpg.webp','Áo sơ mi bé trai lịch lãm','2023-03-19 00:00:00','2023-03-19 00:00:00',1,'ASMBTN1',55,650000),(36,'Áo sơ mi bé gái ASMGN1',36,36,'https://cf.shopee.vn/file/5cf828b0b1af22c840337d55cc5ef923','Áo sơ mi bé gái lịch lãm','2019-06-28 00:00:00','2021-01-24 00:00:00',1,'ASMGN1',65,750000),(37,'Áo kiểu bé trai AKBTN2',37,37,'https://pos.nvncdn.net/631e73-58863/art/artCT/20210526_FzMEfDPMk6xnonHDktpIwiSE.png','Áo kiểu bé trai thời trang','2022-09-24 00:00:00','2023-12-15 00:00:00',1,'AKBTN2',40,350000),(38,'Áo kiểu bé gái AKGN1',38,38,'https://salt.tikicdn.com/cache/750x750/ts/product/43/89/ff/aaeab2a980f46153837b8249462089d0.jpg.webp','Áo kiểu bé gái xinh xắn','2020-02-08 00:00:00','2023-08-26 00:00:00',1,'AKGN1',50,350000),(39,'Quần áo nữ G1',39,39,'https://cf.shopee.vn/file/b8b9ecde88233224ca1dfc59cbef1b89','Quần áo nữ thời trang','2020-08-27 00:00:00','2020-08-27 00:00:00',1,'G1',100,950000),(40,'Áo khoác AK1',40,40,'https://salt.tikicdn.com/cache/750x750/ts/product/a8/c6/73/7944761f7718cc120cfdc80349eb2586.jpg.webp','Áo khoác đẹp','2021-03-05 00:00:00','2022-03-24 00:00:00',1,'AK1',90,850000),(41,'Quần dài QD1',41,41,'https://gymfashion.vn/wp-content/uploads/2021/07/BB578.jpg','Quần dài thoải mái','2020-10-23 00:00:00','2020-10-23 00:00:00',1,'QD1',110,650000),(42,'Quần jeans nữ Q3',42,42,'https://prices.vn/storage/photo/product/quan-jeans-nu-cap-cao-ong-rong-rach-dui-ulzzang-thoi-trang-phong-cach-tre-1626259808956.png','Quần jeans nữ thời trang','2019-09-02 00:00:00','2023-05-12 00:00:00',1,'Q3',150,750000),(43,'Áo thun nữ T3',43,43,'https://ixi.vn/cdn/shop/files/ao-thun-van-phong-nu_1.jpg?v=1696780569&width=493','Áo thun nữ thoải mái','2019-08-04 00:00:00','2019-12-07 00:00:00',1,'T3',200,350000),(44,'Áo sơ mi nữ S3',44,44,'https://product.hstatic.net/1000205116/product/99e157d3-0508-46a5-a37d-fd685732c6b3_73455e7cff5b4625bee94101e3a40166_1024x1024.jpeg','Áo sơ mi nữ lịch lãm','2022-08-10 00:00:00','2022-08-10 00:00:00',1,'S3',80,650000),(45,'Quần tây nữ QT3',45,45,'https://thomasnguyen.vn/wp-content/uploads/2022/08/quan-tay-nu-dong-phuc-thomas-nguyen-uniform-2-768x768.jpg','Quần tây nữ cao cấp','2023-07-10 00:00:00','2023-07-10 00:00:00',1,'QT3',120,950000),(46,'Quần Áo thể thao nam ATH2',46,46,'https://salt.tikicdn.com/cache/750x750/ts/product/18/90/3d/9da6226ea3b1f7c5ff47a88d44d05bc5.jpg.webp','Quần Áo thể thao nam thời trang','2022-07-16 00:00:00','2022-10-03 00:00:00',1,'ATH2',180,550000),(47,'Quần lửng nam QL3',47,47,'https://product.hstatic.net/1000360022/product/abc03692_f4cc2853a1c44d078ca88d655ce8c195_large.jpg','Quần lửng nam thoải mái','2023-02-14 00:00:00','2023-08-28 00:00:00',1,'QL3',160,450000),(48,'Áo dài nữ AD2',48,48,'https://nvhphunu.vn/wp-content/uploads/2023/10/AO-DAI-2.jpg','Áo dài nữ truyền thống','2023-05-25 00:00:00','2023-11-27 00:00:00',1,'AD2',120,650000),(49,'Quần lửng nữ QL4',49,49,'https://salt.tikicdn.com/cache/w1200/ts/product/9f/f6/93/508638a9341f79459521ce7fbf91c54d.jpg','Quần lửng nữ thời trang','2023-01-15 00:00:00','2023-01-15 00:00:00',1,'QL4',80,550000),(50,'Áo sơ mi bé gái nữ ASMGN2',50,50,'https://cf.shopee.vn/file/e777b4328528c5df540f66d39eb21f02','Áo sơ mi bé gái nữ lịch lãm','2022-12-06 00:00:00','2022-12-06 00:00:00',1,'ASMGN2',50,750000);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `size_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `size_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (6,'Size S','S'),(7,'Size M','M'),(8,'Size L','L'),(9,'Size XL','XL'),(10,'Size XXL','XXL'),(11,'Size XXXL','XXXL'),(12,'Size 28','28'),(13,'Size 30','30'),(14,'Size 32','32'),(15,'Size 34','34'),(16,'Size 36','36'),(17,'Size 38','38'),(18,'Size 40','40'),(19,'Size 42','42'),(20,'Size 44','44'),(21,'Size 46','46'),(22,'Size 48','48'),(23,'Size 50','50'),(24,'Size 52','52'),(25,'Size 54','54'),(26,'Size 56','56'),(27,'Size 58','58'),(28,'Size 60','60'),(29,'Size 62','62'),(30,'Size 64','64'),(31,'Size 66','66'),(32,'Size 68','68'),(33,'Size 70','70'),(34,'Size 72','72'),(35,'Size 74','74'),(36,'Size 76','76'),(37,'Size 78','78'),(38,'Size 80','80'),(39,'Size 82','82'),(40,'Size 84','84'),(41,'Size 86','86'),(42,'Size 88','88'),(43,'Size 90','90'),(44,'Size 92','92'),(45,'Size 94','94'),(46,'Size 96','96'),(47,'Size 98','98'),(48,'Size 100','100'),(49,'Size 102','102'),(50,'Size 104','104'),(51,'Size 106','106'),(52,'Size 108','108'),(53,'Size 110','110'),(54,'Size 112','112'),(55,'Size 114','114'),(56,'Size 116','116');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE managers (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    manager_name VARCHAR(255) NOT NULL,
    email VARCHAR(120),
    phone_number VARCHAR(20),
    position VARCHAR(50),
    username VARCHAR(50),
    password VARCHAR(255),
    description TEXT,
    department_id bigint,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

INSERT INTO `managers` (
    `manager_name`, `email`, `phone_number`, `position`,
    `username`, `password`, `description`, `department_id`
) VALUES
  ('Nguyễn Văn A', 'nguyenvana@example.com', '0123456789', 'Quản lý Kế toán', 'nguyenvana', 'hashed_password_A', 'Mô tả quản lý A', 1),
  ('Trần Thị B', 'tranthib@example.com', '0987654321', 'Quản lý Nhân sự', 'tranthib', 'hashed_password_B', 'Mô tả quản lý B', 2),
  ('Lê Minh C', 'leminhc@example.com', '0123456789', 'Quản lý Kỹ thuật', 'leminhc', 'hashed_password_C', 'Mô tả quản lý C', 3),
  ('Phạm Thị D', 'phamthid@example.com', '0987654321', 'Quản lý Marketing', 'phamthid', 'hashed_password_D', 'Mô tả quản lý D', 4),
  ('Hoàng Xuân E', 'hoangxuane@example.com', '0123456789', 'Quản lý Tài chính', 'hoangxuane', 'hashed_password_E', 'Mô tả quản lý E', 5),
  ('Vũ Thị F', 'vuthif@example.com', '0123456789', 'Quản lý Bán hàng', 'vuthif', 'hashed_password_F', 'Mô tả quản lý F', 6),
  ('Trần Văn G', 'tranvang@example.com', '0987654321', 'Quản lý Pháp lý', 'tranvang', 'hashed_password_G', 'Mô tả quản lý G', 7),
  ('Nguyễn Thị H', 'nguyenthih@example.com', '0123456789', 'Quản lý Nghiên cứu và Phát triển', 'nguyenthih', 'hashed_password_H', 'Mô tả quản lý H', 8),
  ('Lê Văn I', 'levani@example.com', '0987654321', 'Quản lý Hậu cần', 'levani', 'hashed_password_I', 'Mô tả quản lý I', 9),
  ('Phạm Thị K', 'phamthik@example.com', '0123456789', 'Quản lý Điều hành', 'phamthik', 'hashed_password_K', 'Mô tả quản lý K', 10),
  ('Hoàng Văn L', 'hoangvanl@example.com', '0123456789', 'Quản lý Quản lý chất lượng', 'hoangvanl', 'hashed_password_L', 'Mô tả quản lý L', 11),
  ('Võ Thị M', 'vothim@example.com', '0987654321', 'Quản lý Quản lý rủi ro', 'vothim', 'hashed_password_M', 'Mô tả quản lý M', 12),
  ('Trần Văn N', 'tranvann@example.com', '0123456789', 'Quản lý Thiết kế', 'tranvann', 'hashed_password_N', 'Mô tả quản lý N', 13),
  ('Nguyễn Thị O', 'nguyenthio@example.com', '0987654321', 'Quản lý Hỗ trợ kỹ thuật', 'nguyenthio', 'hashed_password_O', 'Mô tả quản lý O', 14),
  ('Lê Văn P', 'levanp@example.com', '0123456789', 'Quản lý Quản lý sản phẩm', 'levanp', 'hashed_password_P', 'Mô tả quản lý P', 15),
  ('Trần Thị Q', 'tranthiq@example.com', '0987654321', 'Quản lý Dịch vụ khách hàng', 'tranthiq', 'hashed_password_Q', 'Mô tả quản lý Q', 16),
  ('Phạm Văn R', 'phamvanr@example.com', '0123456789', 'Quản lý Kiểm soát nội bộ', 'phamvanr', 'hashed_password_R', 'Mô tả quản lý R', 17),
  ('Nguyễn Thị S', 'nguyenthas@example.com', '0987654321', 'Quản lý Quản lý dự án', 'nguyenthas', 'hashed_password_S', 'Mô tả quản lý S', 18),
  ('Lê Văn T', 'levant@example.com', '0123456789', 'Quản lý An toàn lao động', 'levant', 'hashed_password_T', 'Mô tả quản lý T', 19),
  ('Hoàng Thị U', 'hoangthiu@example.com', '0987654321', 'Quản lý Quản trị rủi ro', 'hoangthiu', 'hashed_password_U', 'Mô tả quản lý U', 20),
  ('Võ Văn V', 'vovanv@example.com', '0123456789', 'Quản lý Đào tạo', 'vovanv', 'hashed_password_V', 'Mô tả quản lý V', 21),
  ('Trần Thị X', 'tranthix@example.com', '0987654321', 'Quản lý Tư vấn', 'tranthix', 'hashed_password_X', 'Mô tả quản lý X', 22),
  ('Nguyễn Văn Y', 'nguyenvany@example.com', '0123456789', 'Quản lý Hành chính', 'nguyenvany', 'hashed_password_Y', 'Mô tả quản lý Y', 23),
  ('Lê Thị Z', 'lethiz@example.com', '0987654321', 'Quản lý Tiếp thị', 'lethiz', 'hashed_password_Z', 'Mô tả quản lý Z', 24),
  ('Phạm Văn A1', 'phamvana1@example.com', '0123456789', 'Quản lý Phát triển kinh doanh', 'phamvana1', 'hashed_password_A1', 'Mô tả quản lý A1', 25),
  ('Trần Thị B1', 'tranthib1@example.com', '0987654321', 'Quản lý Nghiên cứu thị trường', 'tranthib1', 'hashed_password_B1', 'Mô tả quản lý B1', 26),
  ('Lê Văn C1', 'levanc1@example.com', '0123456789', 'Quản lý Đối ngoại', 'levanc1', 'hashed_password_C1', 'Mô tả quản lý C1', 27),
  ('Nguyễn Văn D1', 'nguyenvand1@example.com', '0987654321', 'Quản lý Tuyển dụng', 'nguyenvand1', 'hashed_password_D1', 'Mô tả quản lý D1', 28),
  ('Hoàng Thị E1', 'hoangthie1@example.com', '0123456789', 'Quản lý Chăm sóc khách hàng', 'hoangthie1', 'hashed_password_E1', 'Mô tả quản lý E1', 29),
  ('Võ Văn F1', 'vovanf1@example.com', '0987654321', 'Quản lý Phát triển cơ sở hạ tầng', 'vovanf1', 'hashed_password_F1', 'Mô tả quản lý F1', 30),
  ('Trần Thị X1', 'tranthix1@example.com', '0123456789', 'Quản lý Phát triển ứng dụng', 'tranthix1', 'hashed_password_X1', 'Mô tả quản lý X1', 31),
  ('Nguyễn Văn Y1', 'nguyenvany1@example.com', '0987654321', 'Quản lý Kiểm toán', 'nguyenvany1', 'hashed_password_Y1', 'Mô tả quản lý Y1', 32),
  ('Lê Thị Z1', 'lethiz1@example.com', '0123456789', 'Quản lý Hỗ trợ kỹ thuật', 'lethiz1', 'hashed_password_Z1', 'Mô tả quản lý Z1', 33),
  ('Phạm Văn A2', 'phamvana2@example.com', '0987654321', 'Quản lý Phân tích dữ liệu', 'phamvana2', 'hashed_password_A2', 'Mô tả quản lý A2', 34),
  ('Trần Thị B2', 'tranthib2@example.com', '0123456789', 'Quản lý Quản lý chuỗi cung ứng', 'tranthib2', 'hashed_password_B2', 'Mô tả quản lý B2', 35),
  ('Lê Văn C2', 'levanc2@example.com', '0987654321', 'Quản lý Tư pháp', 'levanc2', 'hashed_password_C2', 'Mô tả quản lý C2', 36),
  ('Nguyễn Văn D2', 'nguyenvand2@example.com', '0123456789', 'Quản lý Quản lý dự án IT', 'nguyenvand2', 'hashed_password_D2', 'Mô tả quản lý D2', 37),
  ('Hoàng Thị E2', 'hoangthie2@example.com', '0987654321', 'Quản lý Kiểm soát chất lượng', 'hoangthie2', 'hashed_password_E2', 'Mô tả quản lý E2', 38),
  ('Võ Văn F2', 'vovanf2@example.com', '0123456789', 'Quản lý Phát triển hệ thống', 'vovanf2', 'hashed_password_F2', 'Mô tả quản lý F2', 39),
  ('Trần Thị G2', 'tranthig2@example.com', '0987654321', 'Quản lý Tài trợ', 'tranthig2', 'hashed_password_G2', 'Mô tả quản lý G2', 40),
  ('Phạm Văn H', 'phamvanh@example.com', '0123456789', 'Quản lý Thiết kế đồ họa', 'phamvanh', 'hashed_password_H', 'Mô tả quản lý H', 41),
  ('Trần Thị I', 'tranthii@example.com', '0987654321', 'Quản lý Phát triển sản phẩm mới', 'tranthii', 'hashed_password_I', 'Mô tả quản lý I', 42),
  ('Lê Văn J', 'levanj@example.com', '0123456789', 'Quản lý Nghiên cứu vật liệu', 'levanj', 'hashed_password_J', 'Mô tả quản lý J', 43),
  ('Nguyễn Thị K', 'nguyenthik@example.com', '0987654321', 'Quản lý Hỗ trợ khách hàng', 'nguyenthik', 'hashed_password_K', 'Mô tả quản lý K', 44),
  ('Hoàng Văn L', 'hoangvanl1@example.com', '0123456789', 'Quản lý Quản lý rủi ro tài chính', 'hoangvanl1', 'hashed_password_L', 'Mô tả quản lý L', 45),
  ('Võ Thị M', 'vothim1@example.com', '0987654321', 'Quản lý Phân tích thị trường', 'vothim1', 'hashed_password_M', 'Mô tả quản lý M', 46),
  ('Trần Văn N', 'tranvann1@example.com', '0123456789', 'Quản lý Quản lý chất lượng sản phẩm', 'tranvann1', 'hashed_password_N', 'Mô tả quản lý N', 47),
  ('Nguyễn Thị O', 'nguyentho1@example.com', '0987654321', 'Quản lý Quảng bá', 'nguyentho1', 'hashed_password_O', 'Mô tả quản lý O', 48),
  ('Lê Văn P', 'levanp1@example.com', '0123456789', 'Quản lý Phát triển ứng dụng', 'levanp1', 'hashed_password_P', 'Mô tả quản lý P', 49),
  ('Trần Thị Q', 'tranthiq1@example.com', '0987654321', 'Quản lý Kiểm toán', 'tranthiq1', 'hashed_password_Q', 'Mô tả quản lý Q', 50);


-- Tạo bảng departments
CREATE TABLE departments (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL,
    manager_id bigint,
    FOREIGN KEY (manager_id) REFERENCES managers(id)
);

INSERT INTO `departments` (`department_name`, `manager_id`) VALUES
  ('Phòng Kế toán', 1),
  ('Phòng Nhân sự', 2),
  ('Phòng Kỹ thuật', 3),
  ('Phòng Marketing', 4),
  ('Phòng Tài chính', 5),
  ('Phòng Bán hàng', 6),
  ('Phòng Pháp lý', 7),
  ('Phòng Nghiên cứu và Phát triển', 8),
  ('Phòng Hậu cần', 9),
  ('Phòng Điều hành', 10),
  ('Phòng Quản lý chất lượng', 11),
  ('Phòng Quản lý rủi ro', 12),
  ('Phòng Thiết kế', 13),
  ('Phòng Hỗ trợ kỹ thuật', 14),
  ('Phòng Quản lý sản phẩm', 15),
  ('Phòng Dịch vụ khách hàng', 16),
  ('Phòng Kiểm soát nội bộ', 17),
  ('Phòng Quản lý dự án', 18),
  ('Phòng An toàn lao động', 19),
  ('Phòng Quản trị rủi ro', 20),
  ('Phòng Đào tạo', 21),
  ('Phòng Tư vấn', 22),
  ('Phòng Hành chính', 23),
  ('Phòng Tiếp thị', 24),
  ('Phòng Phát triển kinh doanh', 25),
  ('Phòng Nghiên cứu thị trường', 26),
  ('Phòng Đối ngoại', 27),
  ('Phòng Tuyển dụng', 28),
  ('Phòng Chăm sóc khách hàng', 29),
  ('Phòng Phát triển cơ sở hạ tầng', 30),
  ('Phòng Quản lý tài sản', 31),
  ('Phòng Quảng cáo', 32),
  ('Phòng Phát triển ứng dụng', 33),
  ('Phòng Kiểm toán', 34),
  ('Phòng Hỗ trợ kỹ thuật', 35),
  ('Phòng Phân tích dữ liệu', 36),
  ('Phòng Quản lý chuỗi cung ứng', 37),
  ('Phòng Tư pháp', 38),
  ('Phòng Quản lý dự án IT', 39),
  ('Phòng Kiểm soát chất lượng', 40),
  ('Phòng Phát triển hệ thống', 41),
  ('Phòng Tài trợ', 42),
  ('Phòng Thiết kế đồ họa', 43),
  ('Phòng Phát triển sản phẩm mới', 44),
  ('Phòng Nghiên cứu vật liệu', 45),
  ('Phòng Hỗ trợ khách hàng', 46),
  ('Phòng Quản lý rủi ro tài chính', 47),
  ('Phòng Phân tích thị trường', 48),
  ('Phòng Quản lý chất lượng sản phẩm', 49),
  ('Phòng Quảng bá', 50);




--
-- Table structure for table `waistbands`
--

DROP TABLE IF EXISTS `waistbands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waistbands` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `waistband_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `waistband_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waistbands`
--

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(120) NOT NULL
);
CREATE UNIQUE INDEX UK_username ON users (username);
CREATE UNIQUE INDEX UK_email ON users (email);

CREATE TABLE roles (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO `roles` (`name`) VALUES ('ADMIN');
INSERT INTO `roles` (`name`) VALUES ('EMPLOYEE');
INSERT INTO `roles` (`name`) VALUES ('CUSTOMER');

INSERT INTO `users` (`username`, `email`, `password`) VALUES ('admin', 'admin@gmail.com',  '$2a$10$52Obnmi.L3JhxVkyyiSagOO0MeK7BqIPGVVVAl5JeQoJCNpLUxC7.');

INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES ('1','1');

INSERT INTO `users` (`username`, `email`, `password`) VALUES ('employee', 'employee@gmail.com' , '$2a$10$16dudLfMw6vNW34pN1RTzuDk6meeuYsgXdchFDyk5iZUeW/G4mBi.');

INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES ('2','2');

INSERT INTO `users` (`username`, `email`, `password`) VALUES ('customer', 'cusomter@gmail.com', '$2a$10$Z/tz/6tx4009iOcUs1nZLObxjp63MBI5WjI1bULcDVwl9o27pME3q');

INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES ('3','3');



LOCK TABLES `waistbands` WRITE;
/*!40000 ALTER TABLE `waistbands` DISABLE KEYS */;
INSERT INTO `waistbands` VALUES (1,'WAISTBAND001','Cạp quần co dãn'),(2,'WAISTBAND002','Cạp quần thun'),(3,'WAISTBAND003','Cạp quần dệt'),(4,'WAISTBAND004','Cạp quần bản to'),(5,'WAISTBAND005','Cạp quần đứng'),(6,'WAISTBAND006','Cạp quần đúc'),(7,'WAISTBAND007','Cạp quần rộng'),(8,'WAISTBAND008','Cạp quần dày'),(9,'WAISTBAND009','Cạp quần mỏng'),(10,'WAISTBAND010','Cạp quần nhỏ'),(11,'WAISTBAND011','Cạp quần lớn'),(12,'WAISTBAND012','Cạp quần mềm'),(13,'WAISTBAND013','Cạp quần bền'),(14,'WAISTBAND014','Cạp quần linh hoạt'),(15,'WAISTBAND015','Cạp quần êm ái'),(16,'WAISTBAND016','Cạp quần an toàn'),(17,'WAISTBAND017','Cạp quần phong cách'),(18,'WAISTBAND018','Cạp quần trẻ trung'),(19,'WAISTBAND019','Cạp quần năng động'),(20,'WAISTBAND020','Cạp quần thoải mái'),(21,'WAISTBAND021','Cạp quần thời trang'),(22,'WAISTBAND022','Cạp quần cá tính'),(23,'WAISTBAND023','Cạp quần hợp thời'),(24,'WAISTBAND024','Cạp quần độc đáo'),(25,'WAISTBAND025','Cạp quần nổi bật'),(26,'WAISTBAND026','Cạp quần dễ phối đồ'),(27,'WAISTBAND027','Cạp quần thể thao'),(28,'WAISTBAND028','Cạp quần du lịch'),(29,'WAISTBAND029','Cạp quần công sở'),(30,'WAISTBAND030','Cạp quần đời thường'),(31,'WAISTBAND031','Cạp quần tròn'),(32,'WAISTBAND032','Cạp quần mịn'),(33,'WAISTBAND033','Cạp quần gập'),(34,'WAISTBAND034','Cạp quần sọc'),(35,'WAISTBAND035','Cạp quần cài'),(36,'WAISTBAND036','Cạp quần thun co dãn'),(37,'WAISTBAND037','Cạp quần dây kéo'),(38,'WAISTBAND038','Cạp quần đinh'),(39,'WAISTBAND039','Cạp quần bấm'),(40,'WAISTBAND040','Cạp quần nơ'),(41,'WAISTBAND041','Cạp quần chữ'),(42,'WAISTBAND042','Cạp quần trang trí'),(43,'WAISTBAND043','Cạp quần đường viền'),(44,'WAISTBAND044','Cạp quần viền nhỏ'),(45,'WAISTBAND045','Cạp quần viền to'),(46,'WAISTBAND046','Cạp quần đan'),(47,'WAISTBAND047','Cạp quần vải'),(48,'WAISTBAND048','Cạp quần da'),(49,'WAISTBAND049','Cạp quần lụa'),(50,'WAISTBAND050','Cạp quần vải dệt');
/*!40000 ALTER TABLE `waistbands` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 23:18:02
