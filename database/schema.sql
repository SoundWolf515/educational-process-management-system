-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: cebe_gestion
-- ------------------------------------------------------
-- Server version	8.0.43

--
-- Table structure for table `alergias`
--

DROP TABLE IF EXISTS `alergias`;

CREATE TABLE `alergias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo_alergia` varchar(255) DEFAULT NULL,
  `id_estudiante` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58qae1nxrpl4q4d52co0xh9ov` (`id_estudiante`),
  CONSTRAINT `FK58qae1nxrpl4q4d52co0xh9ov` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `apoderado`
--

DROP TABLE IF EXISTS `apoderado`;
CREATE TABLE `apoderado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `distrito` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `esta_vivo` bit(1) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `grado_instruccion` varchar(255) DEFAULT NULL,
  `lengua_materna` varchar(255) DEFAULT NULL,
  `lugar_nacimiento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ocupacion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `segunda_lengua` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `vive_con_estudiante` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `desarrollo_psicomotor`
--

DROP TABLE IF EXISTS `desarrollo_psicomotor`;
CREATE TABLE `desarrollo_psicomotor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `edad` int DEFAULT NULL,
  `hito` varchar(255) DEFAULT NULL,
  `id_estudiante` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevpoarshpjysebe062bafgycc` (`id_estudiante`),
  CONSTRAINT `FKevpoarshpjysebe062bafgycc` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `expediente_clinico`
--

DROP TABLE IF EXISTS `expediente_clinico`;
CREATE TABLE `expediente_clinico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `certificado` bit(1) NOT NULL,
  `complicaciones` varchar(255) DEFAULT NULL,
  `informe_psicopedagogico` bit(1) NOT NULL,
  `lengua_materna` varchar(255) DEFAULT NULL,
  `numero_hermanos` int DEFAULT NULL,
  `plan_educativo_personalizado` bit(1) NOT NULL,
  `segunda_lengua` varchar(255) DEFAULT NULL,
  `tipo_discapacidad` varchar(255) DEFAULT NULL,
  `tipo_parto` varchar(255) DEFAULT NULL,
  `tipo_sangre` varchar(255) DEFAULT NULL,
  `trauma` varchar(255) DEFAULT NULL,
  `id_estudiante` bigint DEFAULT NULL,
  `discapacidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp9m3uuscknee90i1f4s9b5qgj` (`id_estudiante`),
  CONSTRAINT `FKsmi2aj78utrbeum7f6ggkwoub` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
CREATE TABLE `matricula` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_matricula` datetime(6) DEFAULT NULL,
  `nivel` varchar(255) DEFAULT NULL,
  `id_estudiante` bigint NOT NULL,
  `id_seccion` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9sh0baavmvrjs4jihfhe0vbrp` (`id_estudiante`),
  KEY `FK1ygpptwi451vsi05f5jao77kf` (`id_seccion`),
  CONSTRAINT `FK1ygpptwi451vsi05f5jao77kf` FOREIGN KEY (`id_seccion`) REFERENCES `seccion` (`id`),
  CONSTRAINT `FKepexnfqmsgtnf6n77bhpp9lnk` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
CREATE TABLE `mensajes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contacto` varchar(255) DEFAULT NULL,
  `leido` bit(1) NOT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcjw2tc1rmtr1diipqotsay1fb` (`usuario_id`),
  CONSTRAINT `FKcjw2tc1rmtr1diipqotsay1fb` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `notas`
--

DROP TABLE IF EXISTS `notas`;
CREATE TABLE `notas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nota` double DEFAULT NULL,
  `periodo` int DEFAULT NULL,
  `id_curso` bigint NOT NULL,
  `id_matricula` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKbv00lk79871k1ifauh2s7k2lk` (`id_matricula`,`id_curso`,`periodo`),
  KEY `FKhfthepgbg7lhg54e6tqk0r4mo` (`id_curso`),
  CONSTRAINT `FK5k2kdl9qaj2sp1s9t0h37ih1p` FOREIGN KEY (`id_matricula`) REFERENCES `matricula` (`id`),
  CONSTRAINT `FKhfthepgbg7lhg54e6tqk0r4mo` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `parentesco`
--

DROP TABLE IF EXISTS `parentesco`;
CREATE TABLE `parentesco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `es_principal` bit(1) NOT NULL,
  `relacion` varchar(255) DEFAULT NULL,
  `id_apoderado` bigint NOT NULL,
  `id_estudiante` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsmpfnr2bgrk0rrhvrfexjum3k` (`id_estudiante`,`id_apoderado`),
  KEY `FKae6x7uxwgai0cr36m004gyv67` (`id_apoderado`),
  CONSTRAINT `FK8ht0oi4m679s9khqdl39yqfca` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`),
  CONSTRAINT `FKae6x7uxwgai0cr36m004gyv67` FOREIGN KEY (`id_apoderado`) REFERENCES `apoderado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `seccion`
--

DROP TABLE IF EXISTS `seccion`;
CREATE TABLE `seccion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `anio` varchar(255) DEFAULT NULL,
  `letra` varchar(255) DEFAULT NULL,
  `nivel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `usuario`
--
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombres_apellidos` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `usuario_seccion`
--
DROP TABLE IF EXISTS `usuario_seccion`;
CREATE TABLE `usuario_seccion` (
  `usuario_id` bigint NOT NULL,
  `section_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`section_id`),
  KEY `FKisyqdoomqbd0ian4yb99wb5j2` (`section_id`),
  CONSTRAINT `FKay86yf3hqesgran8wr7245wsm` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKisyqdoomqbd0ian4yb99wb5j2` FOREIGN KEY (`section_id`) REFERENCES `seccion` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `vacunacion`
--

DROP TABLE IF EXISTS `vacunacion`;
CREATE TABLE `vacunacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vacuna` varchar(255) DEFAULT NULL,
  `id_estudiante` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c6i0iwqc95482nnisd80t2pj` (`id_estudiante`),
  CONSTRAINT `FK3c6i0iwqc95482nnisd80t2pj` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Dump completed on 2026-03-27 12:44:34
