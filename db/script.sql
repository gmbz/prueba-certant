DROP DATABASE IF EXISTS `pruebacertantdb`;

CREATE DATABASE `pruebacertantdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pruebacertantdb`;

DROP TABLE IF EXISTS `tipo_cliente`;
CREATE TABLE `tipo_cliente` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLE `tipo_cliente` WRITE;

INSERT INTO `tipo_cliente` (`id_tipo`,`descripcion`) VALUES (1,'Comun');
INSERT INTO `tipo_cliente` (`id_tipo`,`descripcion`) VALUES (2,'Extento');

UNLOCK TABLES;

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
    `id_cliente` INT NOT NULL AUTO_INCREMENT,
    `dni` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `apellido` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `telefono` VARCHAR(255) NOT NULL,
    `id_tipo` INT NOT NULL,
    PRIMARY KEY (`id_cliente`),
    CONSTRAINT `cliente_tipo_cliente_fk` FOREIGN KEY (`id_tipo`)
        REFERENCES `tipo_cliente` (`id_tipo`)
);

LOCK TABLE `cliente` WRITE;

INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (1,'11111111','Dibu','Martinez','eluno@gmail.com','0101010101',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (2,'11222111','Lucas','Martinez Quarta','mquarta@gmail.com','0202020202',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (3,'11333111','Nicolas','Tagliafico','tagliafico@gmail.com','0303030303',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (4,'11444111','Gonzalo','Montiel','montiel@gmail.com','0404040404',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (5,'11555111','Leandro','Paredes','rdepaul7@gmail.com','0505050505',2);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (6,'11666111','German','Pezzella','gpezzella@gmail.com','0606060606',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (7,'11777111','Rodrigo','De paul','rdepaul7@gmail.com','0707070707',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (8,'11888111','Marcos','Acuña','marcos08@gmail.com','0808080808',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (9,'11999111','Sergio','Agüero','slakun@gmail.com','0909090909',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (10,'10111000','Lionel','Messi','messi10@gmail.com','1010101010',2);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (11,'22111222','Angelito','Di Maria','angel11@gmail.com','1111111111',2);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (12,'12111222','Agustin','Marchesin','marchesin01@gmail.com','1212121212',1);
INSERT INTO `cliente` (`id_cliente`,`dni`,`nombre`,`apellido`,`email`,`telefono`,`id_tipo`) VALUES (13,'13111333','Cristian','Romero','cuti13@gmail.com','1313131313',1);

UNLOCK TABLES;

DROP TABLE IF EXISTS `oblea`;
CREATE TABLE `oblea` (
  `nro_oblea` int NOT NULL AUTO_INCREMENT,
  `fecha_vencimiento` datetime NOT NULL,
  PRIMARY KEY (`nro_oblea`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLE `oblea` WRITE;

INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (1,'2022-02-12 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (2,'2021-12-25 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (3,'2022-08-08 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (4,'2022-12-03 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (5,'2023-02-20 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (6,'2021-03-12 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (7,'2022-09-11 00:00:00');
INSERT INTO `oblea` (`nro_oblea`,`fecha_vencimiento`) VALUES (8,'2022-05-16 00:00:00');

UNLOCK TABLES;

DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE `vehiculo` (
  `patente` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `year` varchar(255) NOT NULL,
  `id_cliente` int NOT NULL,
  `nro_oblea` int DEFAULT NULL,
  PRIMARY KEY (`patente`),
  KEY `vehiculo_cliente_fk` (`id_cliente`),
  KEY `vehiculo_oblea_fk` (`nro_oblea`),
  CONSTRAINT `vehiculo_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `vehiculo_oblea_fk` FOREIGN KEY (`nro_oblea`) REFERENCES `oblea` (`nro_oblea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLE `vehiculo` WRITE;

INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('AJX 03 HO','Audi','A3','Negro','2020',3,5);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ASV 01 EN','Audi','TT','Rojo','2021',1,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ASV 11 EN','Toyota','Etios','Negro','2020',1,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ATA 13 AR','Volkswagen','Golf','Negro','2019',13,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ATA 13 IT','Renault','CAB','Azul','2021',13,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ATM 07 ES','Ferrari','812 GTS','Negro','2020',7,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('ATM 77 ES','Opel','Corsa','Negro','2020',7,7);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('FIO 02 IT','Toyota','Etios','Gris','2019',2,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('FIO 06 IT','Volkswagen','Golf','Blanco','2021',6,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('MCY 09 EN','Mclaren','Senna','Rojo','2020',9,1);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('MCY 90 EN','Ferrari','296 GTB','Negro','2020',9,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('MCY 99 EN','Audi','TT','Rojo','2021',9,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('OPO 04 PO','Renault','Privilege','Blanco','2020',12,3);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('OPO 12 PO','Renault','Intens','Rojo','2022',12,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 00 FR','Audi','TT','Negro','2022',10,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 05 FR','Ferrari','SPIDER','Amarillo','2021',5,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 10 AR','BMW','Serie 2','Azul','2021',10,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 11 FR','Alpine','A110','Rojo','2021',11,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 12 AR','Ferrari','488 GTB','Gris','2020',11,2);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 30 FR','Bugatti','Chiron','Azul','2021',10,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 31 FR','Pagani','Huayra','Negro','2022',10,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('PSG 55 FR','Volkswagen','Gol Trend','Gris','2020',5,6);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('RIV 04 AR','Volkswagen','Golf','Negro','2020',4,4);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('SEV 08 ES','Opel','Corsa','Blanco','2020',8,NULL);
INSERT INTO `vehiculo` (`patente`,`marca`,`modelo`,`color`,`year`,`id_cliente`,`nro_oblea`) VALUES ('SEV 88 ES','BMW','Serie 1','Negro','2020',8,8);

UNLOCK TABLES;


DROP TABLE IF EXISTS `inspector`;
CREATE TABLE `inspector` (
  `legajo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  PRIMARY KEY (`legajo`)
) ENGINE=InnoDB AUTO_INCREMENT=1041 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLE `inspector` WRITE;

INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1022,'Sergio','Romero','sergioromero@gmail.com','0101010101');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1023,'Ezequiel','Garay','ezegaray@gmail.com','0202020202');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1024,'Marcos','Rojo','rojito@gmail.com','0303030303');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1025,'Pablo','Zabaleta','pzabaleta@gmail.com','0404040404');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1026,'Fernando','Gago','pintita@gmail.com','0505050505');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1027,'Lucas','Biglia','biglia@gmail.com','0606060606');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1028,'Enzo','Perez','enzop@gmail.com','0707070707');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1029,'Javier','Mascherano','masche05@gmail.com','0808080808');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1030,'Gonzalo','Higuain','gonzalo09@gmail.com','0909090909');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1031,'Agustin','Orion','orion01@gmail.com','1010101010');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1032,'Martin','Demichelis','martindem@gmail.com','1111111111');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1033,'Augusto','Fernandez','augustof@gmail.com','1212121212');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1035,'Rodrigo','Palacio','eraporabajo@gmail.com','1313131313');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1036,'Mariano','Andujar','pincha@gmail.com','1313131313');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1037,'Ezequiel','Lavezzi','lavezzi@gmail.com','1313131313');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1038,'Ricardo','Alvarez','ralvarez@gmail.com','1313131313');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1039,'Jose','Basanta','josebasanta@gmail.com','1313131313');
INSERT INTO `inspector` (`legajo`,`nombre`,`apellido`,`email`,`telefono`) VALUES (1040,'Federico','Fernandez','fedefer@gmail.com','1313131313');

UNLOCK TABLES;

DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLE `estado` WRITE;

INSERT INTO `estado` (`id_estado`,`descripcion`) VALUES (1,'APTO');
INSERT INTO `estado` (`id_estado`,`descripcion`) VALUES (2,'CONDICIONAL');
INSERT INTO `estado` (`id_estado`,`descripcion`) VALUES (3,'RECHAZADO');
INSERT INTO `estado` (`id_estado`,`descripcion`) VALUES (4,'EN CURSO');

UNLOCK TABLES;

DROP TABLE IF EXISTS `medicion`;
CREATE TABLE `medicion` (
  `nro_medicion` int NOT NULL AUTO_INCREMENT,
  `suspencion` varchar(255) DEFAULT NULL,
  `tren_delantero` varchar(255) DEFAULT NULL,
  `frenos` varchar(255) DEFAULT NULL,
  `contaminacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nro_medicion`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLE `medicion` WRITE;

INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (1,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (2,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (3,'APTO','APTO','APTO','RECHAZADO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (4,'APTO','APTO','APTO','CONDICIONAL');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (5,'APTO','APTO','CONDICIONAL','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (6,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (7,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (8,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (9,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (10,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (11,'APTO','RECHAZADO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (12,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (13,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (14,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (15,'CONDICIONAL','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (16,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (17,'APTO','APTO','APTO','APTO');
INSERT INTO `medicion` (`nro_medicion`,`suspencion`,`tren_delantero`,`frenos`,`contaminacion`) VALUES (18,'APTO','RECHAZADO','APTO','APTO');

UNLOCK TABLE;

DROP TABLE IF EXISTS `observacion`;
CREATE TABLE `observacion` (
  `nro_observacion` int NOT NULL AUTO_INCREMENT,
  `luces` varchar(255) DEFAULT NULL,
  `patente` varchar(255) DEFAULT NULL,
  `espejos` varchar(255) DEFAULT NULL,
  `chasis` varchar(255) DEFAULT NULL,
  `vidrios_seguridad` varchar(255) DEFAULT NULL,
  `emergencia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nro_observacion`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLE `observacion` WRITE;

INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (1,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (2,'APTO','APTO','APTO','APTO','APTO','RECHAZADO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (3,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (4,'APTO','APTO','APTO','APTO','CONDICIONAL','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (5,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (6,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (7,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (8,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (9,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (10,'APTO','APTO','APTO','APTO','APTO','CONDICIONAL');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (11,'APTO','APTO','APTO','APTO','RECHAZADO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (12,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (13,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (14,'APTO','APTO','APTO','APTO','APTO','CONDICIONAL');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (15,'APTO','APTO','APTO','APTO','APTO','CONDICIONAL');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (16,'APTO','APTO','APTO','APTO','APTO','APTO');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (17,'APTO','APTO','APTO','APTO','APTO','CONDICIONAL');
INSERT INTO `observacion` (`nro_observacion`,`luces`,`patente`,`espejos`,`chasis`,`vidrios_seguridad`,`emergencia`) VALUES (18,'APTO','APTO','APTO','APTO','APTO','APTO');

UNLOCK TABLE;


DROP TABLE IF EXISTS `inspeccion`;
CREATE TABLE `inspeccion` (
  `nro_inspeccion` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `debe_pagar` tinyint(1) DEFAULT NULL,
  `legajo` int DEFAULT NULL,
  `id_estado` int DEFAULT NULL,
  `nro_observacion` int DEFAULT NULL,
  `nro_medicion` int DEFAULT NULL,
  `patente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nro_inspeccion`),
  KEY `inspeccion_inspector_fk` (`legajo`),
  KEY `inspeccion_estado_fk` (`id_estado`),
  KEY `inspeccion_observacion_fk` (`nro_observacion`),
  KEY `inspeccion_medicion_fk` (`nro_medicion`),
  KEY `inspeccion_vehiculo_fk` (`patente`),
  CONSTRAINT `inspeccion_estado_fk` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `inspeccion_inspector_fk` FOREIGN KEY (`legajo`) REFERENCES `inspector` (`legajo`),
  CONSTRAINT `inspeccion_medicion_fk` FOREIGN KEY (`nro_medicion`) REFERENCES `medicion` (`nro_medicion`),
  CONSTRAINT `inspeccion_observacion_fk` FOREIGN KEY (`nro_observacion`) REFERENCES `observacion` (`nro_observacion`),
  CONSTRAINT `inspeccion_vehiculo_fk` FOREIGN KEY (`patente`) REFERENCES `vehiculo` (`patente`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLE `inspeccion` WRITE;

INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (1,'2021-02-12 12:30:00',1,1033,1,1,1,'MCY 09 EN');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (2,'2021-11-20 12:30:00',1,1031,3,2,2,'ATM 77 ES');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (3,'2022-02-22 12:30:00',1,1037,3,3,3,'ASV 11 EN');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (4,'2020-12-25 10:30:00',0,1022,2,4,4,'PSG 12 AR');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (5,'2022-03-02 12:30:00',1,1025,2,5,5,'ATA 13 AR');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (6,'2020-12-25 12:30:00',0,1025,1,6,6,'PSG 12 AR');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (7,'2021-08-08 12:30:00',1,1040,1,7,7,'OPO 04 PO');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (8,'2021-12-03 12:30:00',1,1028,1,8,8,'RIV 04 AR');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (9,'2022-02-20 12:30:00',1,1023,1,9,9,'AJX 03 HO');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (10,'2022-03-12 12:30:00',1,1029,2,10,10,'FIO 02 IT');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (11,'2022-02-24 12:30:00',1,1025,3,11,11,'MCY 09 EN');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (12,'2020-03-12 12:30:00',0,1036,1,12,12,'PSG 55 FR');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (13,'2021-09-11 12:30:00',1,1024,1,13,13,'ATM 77 ES');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (14,'2021-11-27 12:30:00',1,1037,2,14,14,'FIO 06 IT');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (15,'2021-05-16 10:30:00',1,1031,2,15,15,'SEV 88 ES');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (16,'2021-05-16 12:30:00',0,1026,1,16,16,'SEV 88 ES');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (17,'2022-03-04 12:30:00',1,1022,2,17,17,'ATM 07 ES');
INSERT INTO `inspeccion` (`nro_inspeccion`,`fecha`,`debe_pagar`,`legajo`,`id_estado`,`nro_observacion`,`nro_medicion`,`patente`) VALUES (18,'2022-03-05 12:30:00',0,1022,3,18,18,'ATA 13 AR');

UNLOCK TABLE;
