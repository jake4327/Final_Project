DROP TABLE `ticket`;

CREATE TABLE ticket (
    ticketId BIGINT PRIMARY KEY AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    description VARCHAR(255),
    topic VARCHAR(255),
    localDateTime DATETIME2,
    status BOOLEAN
);
