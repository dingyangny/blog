DROP DATABASE IF EXISTS apitrail;
CREATE DATABASE apitrail DEFAULT CHARACTER SET utf8;
USE apitrail;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

--blog表
--blog id 内容
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;