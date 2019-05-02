CREATE TABLE `messages`(
    `messID` int(100) AUTO_INCREMENT,
    `senderID` int(100),
    `chatID` int(100),
    `MESSAGE` varchar(9000),
    PRIMARY KEY(`MessID`)
);