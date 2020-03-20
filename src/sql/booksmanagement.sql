/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : booksmanagement

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 20/03/2020 15:02:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '书籍编号',
  `category_id` bigint(20) NULL DEFAULT NULL,
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍名称',
  `book_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `book_gross` bigint(20) NULL DEFAULT NULL COMMENT '总册数',
  `book_inventory` bigint(20) NULL DEFAULT NULL COMMENT '库存',
  `publishing_house` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `publication_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '出版日期',
  `book_sign` tinyint(4) NULL DEFAULT NULL COMMENT '是否下架 0代表下架 1代表上架',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 1, '墨菲定律', '阿瑟·布洛赫', 100, 98, '山西人民出版社出版', '2020-03-16 05:03:30', 1);
INSERT INTO `books` VALUES (2, 2, '断舍离', '山下英子', 100, 98, '湖南文艺出版社', '2020-03-17 16:12:45', 0);
INSERT INTO `books` VALUES (3, 3, '皮囊', '蔡崇达', 100, 99, '天津人民出版社', '2019-06-01 00:00:00', 1);
INSERT INTO `books` VALUES (4, 3, '浮生六记', '沈复', 100, 99, '天津人民出版社', '2015-08-01 00:00:00', 1);
INSERT INTO `books` VALUES (5, 4, '邓小平时代', '杨继绳', 100, 99, '生活·读书·新知三联书店', '2013-01-01 00:00:00', 1);
INSERT INTO `books` VALUES (6, 4, '论中国', '亨利·艾尔弗雷德·基辛格', 1000, 1000, '中信出版社', '2020-03-14 15:42:51', 1);
INSERT INTO `books` VALUES (7, 4, '中央帝国的军事密码', '郭建龙', 100, 100, '鹭江出版社', '2019-09-01 00:00:00', 1);
INSERT INTO `books` VALUES (8, 5, '贸易的冲突：美国贸易政策200年', '道格拉斯·欧文', 100, 100, '中信出版社', '2019-07-01 00:00:00', 1);
INSERT INTO `books` VALUES (9, 5, '模型思维', '斯科特•佩奇', 100, 100, '浙江人民出版社', '2019-12-01 00:00:00', 1);
INSERT INTO `books` VALUES (10, 5, '游戏改变世界', '简·麦戈尼格尔', 100, 100, '北京联合出版公司', '2016-10-01 00:00:00', 1);
INSERT INTO `books` VALUES (11, 5, '纳什均衡与博弈论', '齐格弗里德', 100, 100, '化学工业出版社', '2011-06-01 00:00:00', 1);
INSERT INTO `books` VALUES (12, 6, '奇妙的科学', '威尔登·欧文', 100, 100, '武汉出版社', '2018-10-01 00:00:00', 1);
INSERT INTO `books` VALUES (13, 6, '从一到无穷大', '乔治·伽莫夫', 100, 100, '天津人民出版社', '2019-09-01 00:00:00', 0);
INSERT INTO `books` VALUES (14, 6, '趣味物理学', '别莱利曼', 100, 100, '武汉出版社', '2018-08-01 00:00:00', 1);
INSERT INTO `books` VALUES (15, 6, '这里是中国', '中国青藏高原研究会', 100, 100, '中信出版社', '2019-09-01 00:00:00', 1);
INSERT INTO `books` VALUES (16, 7, '拿得起放不下的中国史', '王汉周', 100, 100, '北方文艺出版社', '2017-05-01 00:00:00', 1);
INSERT INTO `books` VALUES (17, 7, '易中天品三国', '易中天', 100, 100, '上海文艺出版社', '2014-09-01 00:00:00', 1);
INSERT INTO `books` VALUES (18, 7, '历史的温度', '张玮', 100, 100, '中信出版社', '2019-10-01 00:00:00', 1);
INSERT INTO `books` VALUES (19, 7, '明朝那些事儿', '当年明月', 100, 100, '浙江人民出版社', '2017-05-01 00:00:00', 1);
INSERT INTO `books` VALUES (20, 7, '明朝那些事儿', '马伯庸', 100, 100, '湖南文艺出版社', '2020-03-14 14:23:51', 0);
INSERT INTO `books` VALUES (21, 7, '明朝那些事儿', '当年明月', 100, 100, '北京联合出版公司', '2017-05-01 00:00:00', 1);
INSERT INTO `books` VALUES (22, 8, '论法的精神', '查理·路易·孟德斯鸠', 100, 100, '商务印书馆', '2012-05-01 00:00:00', 1);
INSERT INTO `books` VALUES (23, 8, '刑法的私塾', '张明楷', 100, 100, '北京大学出版社', '2014-07-01 00:00:00', 1);
INSERT INTO `books` VALUES (24, 8, '法的门前', '彼得·博恩里科', 100, 99, '北京大学出版社', '2012-07-01 00:00:00', 1);
INSERT INTO `books` VALUES (25, 9, '以幽默的方式过一生', '琢磨先生', 100, 99, '中信出版集团', '2017-05-01 00:00:00', 1);
INSERT INTO `books` VALUES (26, 9, '非暴力沟通', '马歇尔·卢森堡', 100, 100, '华夏出版社', '2020-03-12 15:28:23', 0);
INSERT INTO `books` VALUES (27, 10, '民国大先生', '史飞翔', 100, 100, '中国文史出版社', '2014-12-01 00:00:00', 1);
INSERT INTO `books` VALUES (28, 10, '巨人三传', '罗曼·罗兰', 101, 101, '江苏文艺出版社', '2020-03-20 00:00:00', 0);
INSERT INTO `books` VALUES (50, 1, '测试', '测试', 1, 0, '测试', '2020-03-12 00:00:00', 1);

-- ----------------------------
-- Table structure for books_category
-- ----------------------------
DROP TABLE IF EXISTS `books_category`;
CREATE TABLE `books_category`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books_category
-- ----------------------------
INSERT INTO `books_category` VALUES (1, '心理学');
INSERT INTO `books_category` VALUES (2, '家庭生活');
INSERT INTO `books_category` VALUES (3, '文学');
INSERT INTO `books_category` VALUES (4, '政治/军事');
INSERT INTO `books_category` VALUES (5, '经济学');
INSERT INTO `books_category` VALUES (6, '科普读物');
INSERT INTO `books_category` VALUES (7, '历史');
INSERT INTO `books_category` VALUES (8, '法律');
INSERT INTO `books_category` VALUES (9, '经典著作');
INSERT INTO `books_category` VALUES (10, '创业必修');
INSERT INTO `books_category` VALUES (52, 'ceshi');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级菜单',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '数据展示', '/Admin/Charts');
INSERT INTO `menu` VALUES (2, 0, '书籍管理', '/Admin/Books');
INSERT INTO `menu` VALUES (3, 0, '类别管理', '/Admin/Category');
INSERT INTO `menu` VALUES (4, 0, '用户管理', '/Admin/Users');
INSERT INTO `menu` VALUES (5, 0, '我的书籍', '/Ordinary/Books');
INSERT INTO `menu` VALUES (6, 0, '个人信息', '/Ordinary/Users');
INSERT INTO `menu` VALUES (7, 1, '书籍信息', '/Charts/BooksCharts');
INSERT INTO `menu` VALUES (8, 1, '用户信息', '/Charts/UsersCharts');
INSERT INTO `menu` VALUES (9, 2, '全部书籍', '/Books/BooksAll');
INSERT INTO `menu` VALUES (10, 2, '添加书籍', '/Books/BooksAdd');
INSERT INTO `menu` VALUES (11, 3, '全部类别', '/Category/CategoriesAll');
INSERT INTO `menu` VALUES (12, 4, '全部用户', '/Users/UsersAll');
INSERT INTO `menu` VALUES (13, 5, '借阅书籍', '/Books/BorrowBooks');
INSERT INTO `menu` VALUES (14, 5, '我的书籍', '/Books/MyBooks');
INSERT INTO `menu` VALUES (15, 6, '我的信息', '/Users/PersonageMessage');

-- ----------------------------
-- Table structure for user_book
-- ----------------------------
DROP TABLE IF EXISTS `user_book`;
CREATE TABLE `user_book`  (
  `borrow_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '借阅编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户编号',
  `book_id` bigint(20) NULL DEFAULT NULL COMMENT '图书编号',
  `borrow_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '借阅时间',
  PRIMARY KEY (`borrow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_book
-- ----------------------------
INSERT INTO `user_book` VALUES (22, 3, 1, '2020-03-16 05:06:15');
INSERT INTO `user_book` VALUES (27, 3, 2, '2020-03-16 07:16:36');
INSERT INTO `user_book` VALUES (33, 3, 24, '2020-03-16 07:26:25');
INSERT INTO `user_book` VALUES (34, 3, 25, '2020-03-16 07:26:25');
INSERT INTO `user_book` VALUES (56, 7, 50, '2020-03-17 16:09:52');
INSERT INTO `user_book` VALUES (57, 7, 1, '2020-03-17 16:09:56');
INSERT INTO `user_book` VALUES (58, 7, 2, '2020-03-17 16:09:57');
INSERT INTO `user_book` VALUES (59, 7, 3, '2020-03-17 16:09:57');
INSERT INTO `user_book` VALUES (60, 7, 4, '2020-03-17 16:09:58');
INSERT INTO `user_book` VALUES (61, 7, 5, '2020-03-17 16:09:59');

-- ----------------------------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu`  (
  `user_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户菜单编号',
  `user_authority` tinyint(4) NULL DEFAULT NULL COMMENT '用户权限',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`user_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
INSERT INTO `user_menu` VALUES (1, 0, 5);
INSERT INTO `user_menu` VALUES (2, 0, 6);
INSERT INTO `user_menu` VALUES (3, 1, 1);
INSERT INTO `user_menu` VALUES (4, 1, 2);
INSERT INTO `user_menu` VALUES (5, 1, 3);
INSERT INTO `user_menu` VALUES (6, 1, 4);

-- ----------------------------
-- Table structure for user_visit
-- ----------------------------
DROP TABLE IF EXISTS `user_visit`;
CREATE TABLE `user_visit`  (
  `visit_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问编号',
  `visit_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '访问日期',
  `visit_people` int(11) NULL DEFAULT NULL COMMENT '访问人数',
  `visit_borrow` int(11) NULL DEFAULT NULL COMMENT '借阅数',
  PRIMARY KEY (`visit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_visit
-- ----------------------------
INSERT INTO `user_visit` VALUES (1, '2020-03-03 00:00:00', 5, 60);
INSERT INTO `user_visit` VALUES (2, '2020-03-04 00:00:00', 12, 35);
INSERT INTO `user_visit` VALUES (3, '2020-03-05 00:00:00', 11, 20);
INSERT INTO `user_visit` VALUES (4, '2020-03-06 00:00:00', 18, 45);
INSERT INTO `user_visit` VALUES (5, '2020-03-07 00:00:00', 19, 20);
INSERT INTO `user_visit` VALUES (6, '2020-03-08 00:00:00', 14, 20);
INSERT INTO `user_visit` VALUES (7, '2020-03-09 00:00:00', 17, 23);
INSERT INTO `user_visit` VALUES (8, '2020-03-10 00:00:00', 29, 40);
INSERT INTO `user_visit` VALUES (9, '2020-03-11 00:00:00', 18, 60);
INSERT INTO `user_visit` VALUES (10, '2020-03-12 00:00:00', 46, 94);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `user_authority` tinyint(4) NULL DEFAULT NULL COMMENT '权限 0代表用户 1代表管理员',
  `user_sign` tinyint(4) NULL DEFAULT NULL COMMENT '状态 0代表禁用 1代表正常',
  `user_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近一次登录时间',
  `create_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', '17639337003', 1, 1, '2020-03-17 16:33:27', '2020-03-02 11:55:32');
INSERT INTO `users` VALUES (2, 'zhangsan', '202cb962ac59075b964b07152d234b70', '13461701950', 0, 1, '2020-03-17 16:16:02', '2020-03-02 11:55:32');
INSERT INTO `users` VALUES (3, 'zhangsana', '202cb962ac59075b964b07152d234b70', '17639337003', 0, 1, '2020-03-16 07:39:37', '2020-03-06 04:51:17');
INSERT INTO `users` VALUES (4, 'zhangsanaaaaaaaa', '68053af2923e00204c3ca7c6a3150cf7', '13461701950', 0, 1, '2020-03-08 19:21:08', '2020-03-06 05:03:11');
INSERT INTO `users` VALUES (5, 'zhangsancccc', '250cf8b51c773f3f8dc8b4be867a9a02', '17639337003', 0, 1, '2020-03-06 05:04:05', '2020-03-06 05:04:05');
INSERT INTO `users` VALUES (6, 'wangwu', '202cb962ac59075b964b07152d234b70', '13461701950', 0, 1, '2020-03-06 13:55:58', '2020-03-06 05:04:51');
INSERT INTO `users` VALUES (7, 'zhangsan1', '202cb962ac59075b964b07152d234b70', '17639337003', 0, 1, '2020-03-17 16:09:47', '2020-03-17 16:07:20');
INSERT INTO `users` VALUES (8, 'lisi', '202cb962ac59075b964b07152d234b70', '13461701950', 0, 1, '2020-03-17 16:33:48', '2020-03-17 16:33:48');

SET FOREIGN_KEY_CHECKS = 1;
