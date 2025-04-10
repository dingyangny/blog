DROP DATABASE IF EXISTS apitrail;
CREATE DATABASE apitrail DEFAULT CHARACTER SET utf8;
USE apitrail;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- user表
-- user id 用户名 密码
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- blog表
-- blog id 用户id 内容 点赞数
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `user_id` int(11) NOT NULL,
                         `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `likes` int(11) NOT NULL DEFAULT 0,
                         `topics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- comment表
-- comment id 博客id 用户id 评论内容
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `blog_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `likes` int(11) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- like表
-- like id 博客id 用户id
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `target_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `type` int(1) NOT NULL, -- 1表示blog like，2表示comment like
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- topic表
-- topic id 话题名称 话题描述
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;




