
# This is a fix for InnoDB in MySQL >= 4.1.x
# It "suspends judgement" for fkey relationships until are tables are set.
SET FOREIGN_KEY_CHECKS = 0;

#-----------------------------------------------------------------------------
#-- event
#-----------------------------------------------------------------------------

DROP TABLE IF EXISTS `event`;


CREATE TABLE `event`
(
	`id` INTEGER  NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255)  NOT NULL,
	`description` LONGTEXT,
	`venue` VARCHAR(255)  NOT NULL,
	`date` DATETIME  NOT NULL,
	`tickets_left` INTEGER default 0 NOT NULL,
	`created_at` DATETIME,
	`updated_at` DATETIME,
	PRIMARY KEY (`id`)
)Type=InnoDB;

#-----------------------------------------------------------------------------
#-- user
#-----------------------------------------------------------------------------

DROP TABLE IF EXISTS `user`;


CREATE TABLE `user`
(
	`id` INTEGER  NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(255)  NOT NULL,
	`password` VARCHAR(64)  NOT NULL,
	`is_manager` TINYINT default 0 NOT NULL,
	`is_admin` TINYINT default 0 NOT NULL,
	`created_at` DATETIME,
	`updated_at` DATETIME,
	PRIMARY KEY (`id`)
)Type=InnoDB;

#-----------------------------------------------------------------------------
#-- ticket
#-----------------------------------------------------------------------------

DROP TABLE IF EXISTS `ticket`;


CREATE TABLE `ticket`
(
	`id` INTEGER  NOT NULL AUTO_INCREMENT,
	`event_id` INTEGER,
	`user_id` INTEGER,
	`created_at` DATETIME,
	PRIMARY KEY (`id`),
	INDEX `ticket_FI_1` (`event_id`),
	CONSTRAINT `ticket_FK_1`
		FOREIGN KEY (`event_id`)
		REFERENCES `event` (`id`)
		ON DELETE SET NULL,
	INDEX `ticket_FI_2` (`user_id`),
	CONSTRAINT `ticket_FK_2`
		FOREIGN KEY (`user_id`)
		REFERENCES `user` (`id`)
		ON DELETE SET NULL
)Type=InnoDB;

# This restores the fkey checks, after having unset them earlier
SET FOREIGN_KEY_CHECKS = 1;
