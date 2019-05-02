CREATE TABLE `likes`(
	`net_id` varchar(200) NOT NULL REFERENCES `users`,
	`likes` varchar(500),
    PRIMARY KEY(`net_id`)
);

CREATE TABLE `matches`(
	`net_id` varchar(200) REFERENCES `users`,
	`matches` varchar(500),
	PRIMARY KEY(`net_id`)
);