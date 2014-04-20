SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `jbug_users`;
CREATE TABLE `jbug_users` (
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `date_created` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `jbug_projects`;
CREATE TABLE `jbug_projects` (
  `project_id` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `date_created` datetime NOT NULL,
  `user_id` int(255) NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `jbug_issues`;
CREATE TABLE `jbug_issues` (
  `issue_id` int(255) NOT NULL AUTO_INCREMENT,
  `project_id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `date_created` datetime NOT NULL,
  `date_closed` datetime NOT NULL,
  `open` int(11) NOT NULL DEFAULT '1',
  `priority` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`issue_id`),
  CONSTRAINT `project` FOREIGN KEY (`project_id`) REFERENCES `jbug_projects` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `jbug_users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS = 1;
