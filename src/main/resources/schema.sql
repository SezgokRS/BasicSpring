CREATE TABLE IF NOT EXISTS UserModel(
    id INT NOT NULL,
    login varchar(250) NOT NULL,
    email varchar(250) NOT NULL,
    password varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS TextModel(
    id INT NOT NULL,
    header varchar(50) NOT NULL,
    text varchar(250) NOT NULL,
    time_added TIMESTAMP NOT NULL
);