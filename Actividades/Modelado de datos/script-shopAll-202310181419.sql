-- MariaDB dump 10.19  Distrib 10.11.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: shopAll
-- ------------------------------------------------------
-- Server version	5.7.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `TBL_Categorias`
--

DROP TABLE IF EXISTS `TBL_Categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Categorias` (
  `idCategoria` int(11) NOT NULL,
  `NombreCategoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Categorias`
--

LOCK TABLES `TBL_Categorias` WRITE;
/*!40000 ALTER TABLE `TBL_Categorias` DISABLE KEYS */;
INSERT INTO `TBL_Categorias` VALUES
(1,'Tecnologia');
/*!40000 ALTER TABLE `TBL_Categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Compras`
--

DROP TABLE IF EXISTS `TBL_Compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Compras` (
  `id_Compra` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad_productos` int(11) NOT NULL,
  `fecha_compra` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total` double NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `id_forma_pago` int(11) NOT NULL,
  PRIMARY KEY (`id_Compra`),
  KEY `TBL_Compras_FK` (`idUsuario`),
  KEY `TBL_Compras_FK_1` (`id_forma_pago`),
  CONSTRAINT `TBL_Compras_FK` FOREIGN KEY (`idUsuario`) REFERENCES `TBL_Usuarios` (`idUsuario`),
  CONSTRAINT `TBL_Compras_FK_1` FOREIGN KEY (`id_forma_pago`) REFERENCES `TBL_forma_pago` (`id_forma`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Compras`
--

LOCK TABLES `TBL_Compras` WRITE;
/*!40000 ALTER TABLE `TBL_Compras` DISABLE KEYS */;
INSERT INTO `TBL_Compras` VALUES
(1,1,'2023-10-14 08:15:10',100,1,1),
(2,1,'2023-10-14 08:17:06',100,1,1),
(3,1,'2023-10-14 08:46:50',100,1,1),
(4,1,'2023-10-14 08:48:20',100,1,1),
(5,1,'2023-10-14 08:53:28',900,1,1),
(6,1,'2023-10-17 18:57:07',200,1,1),
(7,1,'2023-10-17 18:57:14',200,1,1),
(8,1,'2023-10-17 18:57:24',200,1,1),
(9,1,'2023-10-17 18:57:25',200,1,1),
(10,1,'2023-10-17 18:57:26',200,1,1),
(11,1,'2023-10-18 06:46:30',100,1,1),
(12,1,'2023-10-18 06:46:48',200,1,1);
/*!40000 ALTER TABLE `TBL_Compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Inventario`
--

DROP TABLE IF EXISTS `TBL_Inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Inventario` (
  `idInventario` int(11) NOT NULL,
  `idVendedor` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInventario`),
  KEY `idVendedor2` (`idVendedor`),
  KEY `TBL_Inventario_FK` (`idProducto`),
  CONSTRAINT `TBL_Inventario_FK` FOREIGN KEY (`idProducto`) REFERENCES `TBL_Productos` (`idProducto`),
  CONSTRAINT `idVendedor2` FOREIGN KEY (`idVendedor`) REFERENCES `TBL_Tienda_Vendedor` (`idVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Inventario`
--

LOCK TABLES `TBL_Inventario` WRITE;
/*!40000 ALTER TABLE `TBL_Inventario` DISABLE KEYS */;
INSERT INTO `TBL_Inventario` VALUES
(1,1,5,10);
/*!40000 ALTER TABLE `TBL_Inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Lista_Compras`
--

DROP TABLE IF EXISTS `TBL_Lista_Compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Lista_Compras` (
  `id_compra` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` double NOT NULL,
  `total` double NOT NULL,
  KEY `TBL_Lista_Compras_FK_1` (`id_producto`),
  KEY `TBL_Lista_Compras_FK` (`id_compra`),
  CONSTRAINT `TBL_Lista_Compras_FK` FOREIGN KEY (`id_compra`) REFERENCES `TBL_Compras` (`id_Compra`),
  CONSTRAINT `TBL_Lista_Compras_FK_1` FOREIGN KEY (`id_producto`) REFERENCES `TBL_Productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Lista_Compras`
--

LOCK TABLES `TBL_Lista_Compras` WRITE;
/*!40000 ALTER TABLE `TBL_Lista_Compras` DISABLE KEYS */;
INSERT INTO `TBL_Lista_Compras` VALUES
(1,5,1,100,100),
(2,5,1,100,100),
(3,5,1,100,100),
(4,5,1,100,100),
(5,5,9,100,900),
(6,5,2,100,200),
(7,5,2,100,200),
(8,5,2,100,200),
(9,5,2,100,200),
(10,5,2,100,200),
(11,5,1,100,100),
(12,5,2,100,200);
/*!40000 ALTER TABLE `TBL_Lista_Compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Notificacion`
--

DROP TABLE IF EXISTS `TBL_Notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Notificacion` (
  `idNotificacion` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `Mensaje` varchar(110) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`idNotificacion`),
  KEY `TBL_Notificacion_FK` (`idUsuario`),
  CONSTRAINT `TBL_Notificacion_FK` FOREIGN KEY (`idUsuario`) REFERENCES `TBL_Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Notificacion`
--

LOCK TABLES `TBL_Notificacion` WRITE;
/*!40000 ALTER TABLE `TBL_Notificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `TBL_Notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Productos`
--

DROP TABLE IF EXISTS `TBL_Productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Productos` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `DetallesProducto` varchar(45) DEFAULT NULL,
  `idVendedor` int(11) DEFAULT NULL,
  `Precio` decimal(10,0) NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idCategoria` (`idCategoria`),
  KEY `idVendedor` (`idVendedor`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `TBL_Categorias` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idVendedor` FOREIGN KEY (`idVendedor`) REFERENCES `TBL_Tienda_Vendedor` (`idVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Productos`
--

LOCK TABLES `TBL_Productos` WRITE;
/*!40000 ALTER TABLE `TBL_Productos` DISABLE KEYS */;
INSERT INTO `TBL_Productos` VALUES
(5,'Mouse','Es nuevo',1,'Nuevo',1,100),
(6,'Teclado','Es nuevo',1,'Nuevo',1,100);
/*!40000 ALTER TABLE `TBL_Productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Resenas`
--

DROP TABLE IF EXISTS `TBL_Resenas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Resenas` (
  `id` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `Comentario` varchar(500) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL,
  `Calificación` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TBL_Resenas_FK` (`idUsuario`),
  KEY `TBL_Resenas_FK_1` (`idProducto`),
  CONSTRAINT `TBL_Resenas_FK` FOREIGN KEY (`idUsuario`) REFERENCES `TBL_Usuarios` (`idUsuario`),
  CONSTRAINT `TBL_Resenas_FK_1` FOREIGN KEY (`idProducto`) REFERENCES `TBL_Productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Resenas`
--

LOCK TABLES `TBL_Resenas` WRITE;
/*!40000 ALTER TABLE `TBL_Resenas` DISABLE KEYS */;
/*!40000 ALTER TABLE `TBL_Resenas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Tienda_Vendedor`
--

DROP TABLE IF EXISTS `TBL_Tienda_Vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Tienda_Vendedor` (
  `idVendedor` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `Correo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idVendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Tienda_Vendedor`
--

LOCK TABLES `TBL_Tienda_Vendedor` WRITE;
/*!40000 ALTER TABLE `TBL_Tienda_Vendedor` DISABLE KEYS */;
INSERT INTO `TBL_Tienda_Vendedor` VALUES
(1,'Tienda norte','Mexico','0000000000','norte@gmail.com');
/*!40000 ALTER TABLE `TBL_Tienda_Vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Transacciones`
--

DROP TABLE IF EXISTS `TBL_Transacciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Transacciones` (
  `idTransaccion` int(11) NOT NULL,
  `idComprador` int(11) DEFAULT NULL,
  `Fecha` varchar(45) DEFAULT NULL,
  `MontoTotal` float DEFAULT NULL,
  `DireccionEnvio` varchar(110) DEFAULT NULL,
  PRIMARY KEY (`idTransaccion`),
  KEY `TBL_Transacciones_FK` (`idComprador`),
  CONSTRAINT `TBL_Transacciones_FK` FOREIGN KEY (`idComprador`) REFERENCES `TBL_Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Transacciones`
--

LOCK TABLES `TBL_Transacciones` WRITE;
/*!40000 ALTER TABLE `TBL_Transacciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `TBL_Transacciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_Usuarios`
--

DROP TABLE IF EXISTS `TBL_Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_Usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) DEFAULT NULL,
  `ApMaterno` varchar(100) DEFAULT NULL,
  `ApPaterno` varchar(100) DEFAULT NULL,
  `Correo` varchar(45) DEFAULT NULL,
  `Direccion` varchar(110) DEFAULT NULL,
  `NoTelefono` varchar(45) DEFAULT NULL,
  `Tipo_usuario` varchar(45) DEFAULT NULL,
  `Password` varchar(65) DEFAULT NULL COMMENT 'Cifrado HASH',
  `Estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `TBL_Usuarios_UN` (`Correo`),
  UNIQUE KEY `TBL_Usuarios_Telefono` (`NoTelefono`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_Usuarios`
--

LOCK TABLES `TBL_Usuarios` WRITE;
/*!40000 ALTER TABLE `TBL_Usuarios` DISABLE KEYS */;
INSERT INTO `TBL_Usuarios` VALUES
(1,'Alejandro Rafael','Lozano','Guerrero','alejandro2@gmail.com','San felipe','4281176295','Normal','12345678',1),
(19,'Usuario prueba 1','Apellido materno','Apellido paterno','prueba123@gmail.com','San felipe','4281171254','Normal','123456789',1),
(31,'Usuario prueba 1','Apellido materno','Apellido paterno','prueba1234@gmail.com','San felipe','42811771254','Normal','123456789',1);
/*!40000 ALTER TABLE `TBL_Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TBL_forma_pago`
--

DROP TABLE IF EXISTS `TBL_forma_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_forma_pago` (
  `id_forma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_forma`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_forma_pago`
--

LOCK TABLES `TBL_forma_pago` WRITE;
/*!40000 ALTER TABLE `TBL_forma_pago` DISABLE KEYS */;
INSERT INTO `TBL_forma_pago` VALUES
(1,'PayPal','Pago con paypal');
/*!40000 ALTER TABLE `TBL_forma_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'shopAll'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-18 13:19:11
