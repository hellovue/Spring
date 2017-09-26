-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sampledb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'第一个相册','../image_upload/1.jpg','相册1',NULL,NULL),(2,'第二个相册','../image_upload/002.jpg','相册2',NULL,NULL);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(5535) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `editer` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `count1` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'鼠标悬停图片、文字css3效果','<p><span style=\"color:rgb(102, 102, 102); font-family:宋体,arial,helvetica,sans-serif; font-size:14px\">&nbsp; &nbsp; 其实这样的效果也很常见，但是我平时用到很少，在其他网站看到这样的效果，第一反应就是查看源代码，看他怎么运用的，结果用了很多的div，最主要的是用js来实现。目前很多浏览器也都兼容css3新属性，这个例子就可以使用rgba(0,0,0,0.5)、opacity、当然关键也要看怎么定位，还是会用到position属性。</span></p>\r\n\r\n<p><span style=\"color:rgb(102, 102, 102); font-family:宋体,arial,helvetica,sans-serif; font-size:14px\">&nbsp;&nbsp;&nbsp;</span></p>\r\n\r\n<p>Html：</p>\r\n\r\n<div class=\"shili\" style=\"margin: 0px; padding: 10px; background-color: rgb(245, 245, 245); border: 1px dotted rgb(119, 136, 85); word-wrap: break-word;\">\r\n<p>&nbsp;&lt;div&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;h2&gt;1、鼠标悬停图片变大文字消失&lt;/h2&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;ul class=&quot;ex01&quot;&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot; &gt;&lt;img src=&quot;images/1.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot; &gt;&lt;img src=&quot;images/2.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot;&gt;&lt;img src=&quot;images/3.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot; &gt;&lt;img src=&quot;images/4.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;/ul&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp;&lt;h2&gt;2、鼠标悬停文字出现&lt;/h2&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;ul class=&quot;ex02&quot;&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot; &gt;&lt;img src=&quot;images/1.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot;&gt;&lt;img src=&quot;images/2.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot;&gt;&lt;img src=&quot;images/3.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;li&gt;&lt;a href=&quot;/&quot;&gt;&lt;img src=&quot;images/4.jpg&quot;&gt;&lt;span&gt;鼠标悬停&lt;/span&gt;&lt;/a&gt;&lt;/li&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;/ul&gt;</p>\r\n\r\n<p>&nbsp; &lt;/div&gt;</p>\r\n</div>\r\n\r\n<p>CSS代码片段：</p>\r\n\r\n<div class=\"shili\" style=\"margin: 0px; padding: 10px; background-color: rgb(245, 245, 245); border: 1px dotted rgb(119, 136, 85); word-wrap: break-word;\">\r\n<p>/* ex01 */</p>\r\n\r\n<p>.ex01 li{ width:170px; float:left; margin:5px}</p>\r\n\r\n<p>.ex01 li a { position:relative; display:block }</p>\r\n\r\n<p>.ex01 li span{ position: absolute; left: 0px; bottom: 0px;color:#FFF; line-height:24px; display:block; width:170px; -webkit-transition: all 0.5s; -moz-transition: all 0.5s; -o-transition: all 0.5s; -ms-transition: all 0.5s; background:rgba(0,0,0,0.5); text-align:center}</p>\r\n\r\n<p>.ex01 img { width: 170px; height: 120px; -webkit-transition: all 0.5s; -moz-transition: all 0.5s; -o-transition: all 0.5s; display:block}</p>\r\n\r\n<p>.ex01 img:hover { -webkit-transform: scale(1.1); -moz-transform: scale(1.1); -o-transform: scale(1.1); -ms-transform: scale(1.1); }</p>\r\n\r\n<p>.ex01 li:hover span{ opacity:0}</p>\r\n\r\n<p>/* ex02 */</p>\r\n\r\n<p>.ex02 li{width:170px;margin:5px;float:left;}</p>\r\n\r\n<p>.ex02 li img{width: 170px; height: 120px;display:block;}</p>\r\n\r\n<p>.ex02 li span{ opacity:0}</p>\r\n\r\n<p>.ex02 li:hover span{ opacity:1}</p>\r\n\r\n<p>.ex02 li a { position:relative; display:block }</p>\r\n\r\n<p>.ex02 li span{ position: absolute; left: 0px; top: 0px; background:rgba(0,0,0,0.5); color:#FFF; line-height:24px; display:block; width:170px;text-align:center;-webkit-transition: all 0.5s; -moz-transition: all 0.5s; -o-transition: all 0.5s; -ms-transition: all 0.5s; }</p>\r\n</div>\r\n\r\n<ul>\r\n</ul>\r\n','../image_upload/file_1398842463989.jpg','2014-04-30','佚名',1,0,'心得笔记','recommendArticles','心得笔记'),(3,'从摄影作品中获取网页颜色搭配技巧','<p>&nbsp; &nbsp; 搭建一个网站，虽然布局第一、颜色其次。但颜色的搭配在用户视觉体验中确实最重要的元素。网页的色彩也是树立网站形象的关键之一。作为一个优秀、专业的网页设计师，首先要了解各种颜色的象征，以及不同类型网站常用的色彩搭配。色彩搭配看似复杂,但并不神秘。一般来说,网页的背景色应该柔和一些、素一些、淡一些,再配上深色的文字,使人看起来自然、舒畅。色彩是人的视觉最敏感的东西。主页的色彩处理得好，可以锦上添花，达到事半功倍的效果。</p>\r\n\r\n<p><strong>颜色搭配常识：</strong></p>\r\n\r\n<p>1.网页中色彩的表达使用三种颜色，及红(R)、绿(G)、蓝(B)，及通常所说的RGB色彩，它包含了人类所感知的所有颜色，网页中表达颜色如下（红色为例）RGB格式：红色是(255,0,0) 或十六进制hex格式为(FF0000)。</p>\r\n\r\n<p>2.将色彩按&quot;红-&gt;黄-&gt;绿-&gt;蓝-&gt;红&quot;依次过度渐变可得到12色环：红，橙红，橙，橙黄，黄，黄绿，绿，蓝绿，蓝，蓝紫，紫，紫红。</p>\r\n\r\n<p>3.颜色分非彩色和彩色两类。非彩色是指黑，白，灰系统色。彩色是指除了非彩色以外的所有色彩。通常内容文字用非彩色(黑色)，边框，背景，图片用彩色。所以即使页面丰富，看内容依然不会眼花,通常背景与内容对比要大。</p>\r\n\r\n<p><strong>色彩的心理感觉</strong></p>\r\n\r\n<p>红色---是一种激奋的色彩。刺激效果，能使人产生冲动，愤怒，热情，活力的感觉。</p>\r\n\r\n<p>绿色---介于冷暖两中色彩的中间，显得和睦，宁静，健康，安全的感觉。 它和金黄，淡白搭配，可以产生优雅，舒适的气氛。</p>\r\n\r\n<p>橙色---也是一种激奋的色彩，具有轻快，欢欣，热烈，温馨，时尚的效果。</p>\r\n\r\n<p>黄色---具有快乐，希望，智慧和轻快的个性，它的明度最高。</p>\r\n\r\n<p>蓝色---是最具凉爽，清新，专业的色彩。它和白色混合，能体现柔顺，淡雅，浪漫的气氛。</p>\r\n\r\n<p>白色---具有洁白，明快，纯真，清洁的感受。</p>\r\n\r\n<p>黑色---具有深沉，神秘，寂静，悲哀，压抑的感受。</p>\r\n\r\n<p>灰色---具有中庸，平凡，温和，谦让，中立和高雅的感觉。</p>\r\n\r\n<p><strong>网页配色忌讳</strong></p>\r\n\r\n<p>1.不要将所有颜色都用到，尽量控制在三种色彩以内。</p>\r\n\r\n<p>2.背景和前文的对比尽量要大，(绝对不要用花纹繁复的图案作背景)，以便突出主要文字内容</p>\r\n\r\n<p><strong>摄影作品来获取颜色</strong></p>\r\n\r\n<p>网页中背景图片也是至关重要的，使用一张大图片作为网页的背景是吸引访客眼球最快捷的方法。高质量的精美照片和抽象的设计图片能够给网站的外观带去深刻的影响。图片配合色彩是最完美的艺术。</p>\r\n\r\n<ul>\r\n</ul>\r\n','../image_upload/file_1398842746601.jpg','2014-04-30','佚名',1,0,'心得体会','recommendArticles','心得体会'),(4,'HTML5技术将推动移动浏览器变革','<p>&nbsp; &nbsp; 在网络条件日渐成熟的今天，横跨智能手机、平板电脑以及PC终端的HTML5有其无可替代的优势。业内人士分析称，HTML5将为移动互联网带来技术革命，推动移动浏览器变革。</p>\r\n\r\n<p>HTML5最大特点：跨平台</p>\r\n\r\n<p>神奇时代制作人张哲介绍说，HTML5技术是包括HTML、CSS、JavaScript在内的一套技术组合，由谷歌、苹果等几百家公司一起开发，试图将Web带入一个成熟的应用平台。他认为，HTML5最大的特点就是跨平台。一款HTML5游戏，可以随时随地在任何支持HTML5的浏览器上运行。</p>\r\n\r\n<p>张哲表示，HTML5的出现，彻底改变了Web网页的呈现方式，为移动互联网带来了技术革命。无论游戏、视频、音乐、阅读还是其他应用，在HTML5的支持下，都可以在浏览器上拥有更好、更便捷的使用体验。比如在微博、微信里，玩家通过分享一个链接，就可以让好友立刻进入游戏，这些是原生游戏做不到的。</p>\r\n\r\n<p>峰会上，业内人士分析称，HTML5大潮已经袭来，这项技术对移动互联网的发展将起到非常大的驱动作用。2013年，谷歌发布的Android平台新版ChromeBeta支持HTML5，360宣布将全面推动HTML5发展与普及，三星、中兴、摩托罗拉、火狐等新手机都支持HTML5应用。</p>\r\n\r\n<p>数据显示，2013年全球将有10亿手机浏览器支持HTML5，同时，HTML Web开发者数量将达到200万。毫无疑问，HTML5在未来5-10年内依旧会是移动互联网领域的主宰者。同时，HTML5技术标准的发展与普及意味着世界上将出现功能更强大、更稳定的网络应用。</p>\r\n\r\n<ul>\r\n</ul>\r\n','../image_upload/file_1398842886496.jpg','2014-04-30','佚名',0,0,'html5','','html5'),(5,'css3制作3D图书翻页效果','<p><span style=\"color:rgb(102, 102, 102); font-family:宋体,arial,helvetica,sans-serif; font-size:14px\">&nbsp; &nbsp; 景深设置，可以让3D效果更加逼真，perspective定义属性后，子元素会获得透视效果，而不是元素本身，transform-style:preserve-3d保留3D位置。关于CSS3 perspective 更多属性以及教程，请参考</span><a href=\"http://www.w3school.com.cn/cssref/pr_perspective.asp\" style=\"margin: 0px; padding: 0px; text-decoration: none; color: rgb(51, 51, 51); font-family: 宋体, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 28px; text-indent: 28px;\" target=\"_blank\"><span style=\"color:rgb(0, 0, 0)\">W3School网站教程</span></a></p>\r\n','../image_upload/file_1398842990018.jpg','2014-04-30','佚名',0,0,'css3','recommendArticles','css3'),(6,'使用css3伪元素制作时间轴并且实现鼠标选中高亮效果','<p>利用css3来制作时间轴的知识要点：伪元素，以及如何在伪元素上添加锚伪类</p>\r\n\r\n<p>1）<strong>::before</strong> 在元素之前添加内容。</p>\r\n\r\n<p>2）<strong>::after</strong> 在元素之后添加内容。</p>\r\n\r\n<p>提示：亦可写成 :before :after&nbsp;CSS3将伪对象选择符前面的单个冒号(:)修改为双冒号(::)用以区别伪类选择符，但以前的写法仍然有效。</p>\r\n\r\n<p>代码分析：右边那条黑色竖线，可以看成是右边框、也可以用伪元素after或者before实现，所以方法有很多，下面的只作为一种参考，或许你有更好的写法</p>\r\n\r\n<p><strong>使用方法：</strong></p>\r\n\r\n<p>p::before { content: &quot; &quot;; }&nbsp;<span style=\"font-size:12px\"><span style=\"color:rgb(128, 128, 128)\">/*在p元素前添加内容 */</span></span></p>\r\n\r\n<p><strong>html代码：</strong></p>\r\n\r\n<div class=\"shili\" style=\"margin: 0px; padding: 10px; background-color: rgb(245, 245, 245); border: 1px dotted rgb(119, 136, 85); word-wrap: break-word;\">\r\n<p>&lt;div class=&quot;blogs&quot;&gt;</p>\r\n\r\n<p>&nbsp; &lt;div class=&quot;bloglist&quot;&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;h2&gt;&lt;a href=&quot;/&quot; target=&quot;_blank&quot;&gt;我希望我的爱情是这样的&lt;/a&gt;&lt;/h2&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;ul&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;a href=&quot;/&quot;&gt;&lt;img src=&quot;/web/24/images/s1.jpg&quot;&gt;&lt;/a&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &lt;p&gt; 我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。我在岁月中找到他，依靠他，将一生交付给他。做他的妻子，他孩子的母亲，为他做饭，洗衣服，缝一颗掉了的纽扣。然后，我们一起在时光中变老。&lt;/p&gt;</p>\r\n\r\n<p>&nbsp; &nbsp; &lt;/ul&gt;</p>\r\n\r\n<p>&nbsp; &lt;/div&gt;</p>\r\n\r\n<p>&lt;/div&gt;</p>\r\n</div>\r\n\r\n<p><strong>主要CSS代码：</strong></p>\r\n\r\n<div class=\"shili\" style=\"margin: 0px; padding: 10px; background-color: rgb(245, 245, 245); border: 1px dotted rgb(119, 136, 85); word-wrap: break-word;\">\r\n<p>.blogs { width: 670px; margin: 20px auto; position: relative; }</p>\r\n\r\n<p>.blogs<strong>::before</strong>&nbsp;{ content: &quot;&quot;; width: 2px; height: 100%; top: 0; bottom: 0; right: 0; background: #000; position: absolute }&nbsp;<span style=\"color:rgb(128, 128, 128)\"><span style=\"font-size:12px\">/* 右侧的黑色竖轴线 */</span></span></p>\r\n\r\n<p>.bloglist { background: #222; box-shadow: 0px 1px 0px rgba(255,255,255,.1), inset 0px 1px 1px rgba(0,0,0,.7); width: 630px; color: #b9b9b9; border-radius: 6px; }</p>\r\n\r\n<p>.bloglist<strong>::before</strong>&nbsp;{ content: &quot;&quot;; width: 0px; height: 0px; border-style: solid; border-width: 0px 0 20px 22px; border-color: transparent transparent transparent #111; position: absolute; left: 630px; top: 40px; }<span style=\"color:rgb(128, 128, 128)\"><span style=\"font-size:12px\">/* 三角形 */</span></span></p>\r\n\r\n<p>.bloglist:<strong>:after&nbsp;</strong>{ content: &quot;&quot;; width: 10px; height: 10px; border-radius: 50%; position: absolute; left: 662px; top: 36px; background: #000; border: 2px solid #333; z-index: 10; }<span style=\"color:rgb(128, 128, 128)\"><span style=\"font-size:12px\">/* 圆形 */</span></span></p>\r\n\r\n<p>.bloglist<span style=\"color:rgb(255, 0, 0)\">:hover</span><strong>::afte</strong>r { border-color: #ccc; }<span style=\"font-size:12px\"><span style=\"color:rgb(128, 128, 128)\">/* 鼠标放上去 圆形边框变色 */</span></span></p>\r\n</div>\r\n\r\n<ul>\r\n</ul>\r\n\r\n<ul>\r\n</ul>\r\n','../image_upload/file_1398843120275.jpg','2014-04-30','佚名',2,0,'css3','recommendArticles','css3'),(7,'CSS3动画_liffect effect','<p><span style=\"color:rgb(117, 111, 113); font-family:宋体,arial,helvetica,sans-serif; font-size:12px\">css3的animation可以让我们不在依赖于javaScript的脚本就能实现一些动画效果，但这个属性的制作，时间点控制还是需要花时间去调试的，liffect effect可以说是一个css3 animation的动画库，里面涵盖了20多种动画效果，极其类似于jQuery Easing Plugin中的效果。</span></p>\r\n\r\n<ul>\r\n</ul>\r\n','../image_upload/file_1398843318250.png','2014-04-30','佚名',1,0,'css3','','css3');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `authority` varchar(256) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `critique`
--

DROP TABLE IF EXISTS `critique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `critique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `critique`
--

LOCK TABLES `critique` WRITE;
/*!40000 ALTER TABLE `critique` DISABLE KEYS */;
INSERT INTO `critique` VALUES (1,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(2,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(3,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(4,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(5,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(6,NULL,'这个博客不错不错,我很喜欢','赵浩','2014-4-24',NULL,NULL,NULL),(7,NULL,'<p>第一个博客程序</p>\r\n','赵浩','2014-04-30',NULL,'留言',NULL),(8,NULL,'<p>测试一下</p>\r\n','赵浩','2014-04-30',NULL,'留言',NULL),(9,NULL,'<p>留言内容</p>\r\n','赵浩','2014-04-30',NULL,'留言',NULL);
/*!40000 ALTER TABLE `critique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'admin','123456',NULL,NULL);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `me`
--

DROP TABLE IF EXISTS `me`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `me` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(5535) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `me`
--

LOCK TABLES `me` WRITE;
/*!40000 ALTER TABLE `me` DISABLE KEYS */;
INSERT INTO `me` VALUES (1,'<p>请不要假装对我好，我很傻，会当真的。<br />\r\n谁把谁真的当真，谁为谁心疼。<br />\r\n爱那么短，遗忘那么长。<br />\r\n我还在原地等你，你却已经忘记曾来过这里。<br />\r\n谁的寂寞覆我华裳，谁的华裳覆我肩膀。<br />\r\n经不住似水流年，逃不过此间少年。<br />\r\n原来地久天长，只是误会一场。<br />\r\n幸福，就是找一个温暖的人过一辈子。<br />\r\n痛过之后就不会觉得痛了，有的只会是一颗冷漠的心。<br />\r\n没有什么过不去，只是再也回不去。要有多坚强，才敢念念不忘。你是我猜不到的不知所措，我是你想不到的无关痛痒。<br />\r\n感情的戏，我没演技。<br />\r\n一个人，一座城，一生心疼。看着别人的故事，流着自己的眼泪。<br />\r\n我喜欢现在的自己，我怀念过去的我们。离开后，别说祝我幸福，你有什么资格祝我幸福？向来缘浅，奈何情深。<br />\r\n彼年豆蔻，谁许谁地老天荒。我不在乎你对我的不在乎。我相信这个世界永远那么美。<br />\r\n习惯难受，习惯思念，习惯等你，可是却一直没有习惯看不到你。<br />\r\n如花美眷，似水流年。回得了过去，回不了当初。那些最终会让你陷进去的，一开始总是美好。<br />\r\n当你做对的时候，没有人会记得；当你做错的时候，连呼吸都是错。<br />\r\n等待，是一生最初的苍老。有些事一转身就一辈子。<br />\r\n年轻时我们放弃，以为那只是一段感情，后来才知道，那其实是一生。<br />\r\n哀莫过于心不死。<br />\r\n我很好，不吵不闹不炫耀，不要委屈不要嘲笑，也不需要别人知道。<br />\r\n我是你转身就忘的路人甲，凭什么陪你蹉跎年华到天涯？</p>\r\n',NULL,NULL);
/*!40000 ALTER TABLE `me` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `user_name` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `client_id` text CHARACTER SET latin1,
  `authentication` blob,
  `refresh_token` varchar(256) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('79ea650f6c1fc39188e91ecb8ef4c56d','��\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$��\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z���\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0OX\"Tkxpsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$6e2693da-0842-4775-b148-043020ae8ae6','2f5e20167425123c9ca9cb3c53d51774','dongtian','restapp','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0ROLE_APPxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0restappsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0usernamet\0dongtiant\0\ngrant_typet\0passwordt\0	client_idt\0restappxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0\0xppsr\0java.util.Collections$EmptySet�r��(\0\0xpsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\00sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0 q\0~\0!t\0\rclient_secrett\0restappq\0~\0q\0~\0x\0psr\0<org.springframework.security.oauth2.provider.service.AppUser�+�?�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0%w\0\0\0?@\0\0\0\0\0q\0~\0xt\0dongtiant\0dongtian',NULL);
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) CHARACTER SET latin1 NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET latin1 DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('restapp',NULL,'restapp','read','password',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_token`
--

DROP TABLE IF EXISTS `oauth_client_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `user_name` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65B3E3233B5690E` (`album_id`),
  CONSTRAINT `FK65B3E3233B5690E` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,'../image_upload/1.jpg','这是第1张照片','内容','1','相册','2014-4-25',1),(2,'../image_upload/001.jpg','这是第1张照片','内容','2','相册','2014-4-25',1),(3,'../image_upload/2.jpg','这是第2张照片','内容','1',NULL,'2014-4-25',1),(4,'../image_upload/3.jpg','这是第3张照片','内容','1',NULL,'2014-4-25',1),(5,'../image_upload/4.jpg','这是第4张照片','内容','1',NULL,'2014-4-25',1),(6,'../image_upload/5.jpg','这是第5张照片','内容','1',NULL,'2014-4-25',1),(7,'../image_upload/6.jpg','这是第6张照片','内容','1',NULL,'2014-4-25',1),(8,'../image_upload/7.jpg','这是第7张照片','内容','1',NULL,'2014-4-25',1),(9,'../image_upload/8.jpg','这是第8张照片','内容','1',NULL,'2014-4-25',1),(10,'../image_upload/9.jpg','这是第9张照片','内容','1',NULL,'2014-4-25',1),(11,'../image_upload/10.jpg','这是第10张照片','内容','1',NULL,'2014-4-25',1),(12,'../image_upload/11.jpg','这是第11张照片','内容','1',NULL,'2014-4-25',1),(13,'../image_upload/12.jpg','这是第12张照片','内容','1',NULL,'2014-4-25',1),(14,'../image_upload/002.jpg','这是第2张照片','内容','2',NULL,'2014-4-26',2),(15,'../image_upload/003.jpg','这是第3张照片','内容','2',NULL,'2014-4-26',2),(16,'../image_upload/004.jpg','这是第4张照片','内容','2',NULL,'2014-4-26',2),(17,'../image_upload/005.jpg','这是第5张照片','内容','2',NULL,'2014-4-26',2),(18,'../image_upload/006.jpg','这是第6张照片','内容','2',NULL,'2014-4-26',2),(19,'../image_upload/007.jpg','这是第7张照片','内容','2',NULL,'2014-4-26',2),(20,'../image_upload/008.jpg','这是第8张照片','内容','2',NULL,'2014-4-26',2);
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_art_stat`
--

DROP TABLE IF EXISTS `t_art_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_art_stat` (
  `tid` varchar(36) NOT NULL COMMENT '文章ID（主键、外键）',
  `read_count` int(11) DEFAULT NULL COMMENT '文章点击数',
  `upvote` int(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章数据统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_art_stat`
--

LOCK TABLES `t_art_stat` WRITE;
/*!40000 ALTER TABLE `t_art_stat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_art_stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog`
--

DROP TABLE IF EXISTS `t_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog` (
  `tid` varchar(36) NOT NULL COMMENT '文章ID（主键）',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID（外键）',
  `title` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '文章类型（原创、转载）',
  `loadURL` varchar(100) DEFAULT NULL COMMENT '转载地址',
  `label` varchar(100) DEFAULT NULL COMMENT '文章标签',
  `decoration` varchar(100) DEFAULT NULL COMMENT '文章描述信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `alter_time` datetime DEFAULT NULL COMMENT '修改时间',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '文章状态（未审核、保存、审核通过）',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog`
--

LOCK TABLES `t_blog` WRITE;
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_login_log`
--

DROP TABLE IF EXISTS `t_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_login_log` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_login_log`
--

LOCK TABLES `t_login_log` WRITE;
/*!40000 ALTER TABLE `t_login_log` DISABLE KEYS */;
INSERT INTO `t_login_log` VALUES (1,1,'0:0:0:0:0:0:0:1','2017-07-01 16:52:08');
/*!40000 ALTER TABLE `t_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_response_one`
--

DROP TABLE IF EXISTS `t_response_one`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_response_one` (
  `post_id` varchar(36) NOT NULL COMMENT '回复ID（主键）',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID（外键）',
  `tid` varchar(36) DEFAULT NULL COMMENT '文章ID（外键）',
  `text` varchar(1000) DEFAULT NULL COMMENT '回复消息',
  `respond` varchar(36) DEFAULT NULL COMMENT '二级回复ID（值唯一）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`post_id`),
  KEY `t_response_one_t_users_user_id_fk` (`user_id`),
  KEY `t_response_one_t_blog_tid_fk` (`tid`),
  CONSTRAINT `t_response_one_t_blog_tid_fk` FOREIGN KEY (`tid`) REFERENCES `t_blog` (`tid`),
  CONSTRAINT `t_response_one_t_users_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一级回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_response_one`
--

LOCK TABLES `t_response_one` WRITE;
/*!40000 ALTER TABLE `t_response_one` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_response_one` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_response_sec`
--

DROP TABLE IF EXISTS `t_response_sec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_response_sec` (
  `respond_id` varchar(36) NOT NULL COMMENT '二级回复ID编号（主键）',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID（外键）',
  `text` varchar(1000) DEFAULT NULL COMMENT '回复消息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`respond_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二级回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_response_sec`
--

LOCK TABLES `t_response_sec` WRITE;
/*!40000 ALTER TABLE `t_response_sec` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_response_sec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_source`
--

DROP TABLE IF EXISTS `t_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_source` (
  `source_id` varchar(36) NOT NULL COMMENT '资源ID（主键）',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID（外键）',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `sdecotation` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `source_url` varchar(100) DEFAULT NULL COMMENT '资源下载地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`source_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源分享表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_source`
--

LOCK TABLES `t_source` WRITE;
/*!40000 ALTER TABLE `t_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `credits` int(11) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `last_visit` datetime DEFAULT NULL,
  `last_ip` varchar(23) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin',5,'123456','2017-07-01 16:52:08','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_users`
--

DROP TABLE IF EXISTS `t_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_users` (
  `user_id` varchar(36) NOT NULL COMMENT '用户ID(主键)',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `question` varchar(200) DEFAULT NULL COMMENT '密保问题',
  `answer` varchar(200) DEFAULT NULL COMMENT '密保答案',
  `user_img` varchar(100) DEFAULT NULL COMMENT '用户图片',
  `regtime` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `t_users_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users`
--

LOCK TABLES `t_users` WRITE;
/*!40000 ALTER TABLE `t_users` DISABLE KEYS */;
INSERT INTO `t_users` VALUES ('1','bilibili','admin','test','null','img','2017-05-07 13:16:02'),('2','github','github','test',NULL,'bilibili','2017-06-22 01:08:07'),('3','hello','hi','test',NULL,'bilibili','2017-06-22 01:08:07'),('4','hi','hi','test',NULL,'bilibili','2017-06-22 01:08:07');
/*!40000 ALTER TABLE `t_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_users_info`
--

DROP TABLE IF EXISTS `t_users_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_users_info` (
  `user_id` varchar(36) NOT NULL COMMENT '用户ID（主键+外键）',
  `name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `sex` char(1) NOT NULL DEFAULT '0' COMMENT '用户性别(''0'',''1'')(''男'',''女'')',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `tel_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `t_users_info_t_users_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users_info`
--

LOCK TABLES `t_users_info` WRITE;
/*!40000 ALTER TABLE `t_users_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_users_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(256) CHARACTER SET latin1 DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `role` varchar(255) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('dongtian','dongtian',1,'ROLE_APP');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03  0:24:15
