-- erase old db
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS library;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS gift_cards;

-- create tables

CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nickname TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL, -- 'ADMIN', 'PUBLISHER', 'USER'
    balance REAL DEFAULT 0.0
);

CREATE TABLE games (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    tag TEXT NOT NULL,
    baseprice REAL NOT NULL,
    currentprice REAL NOT NULL,
    score REAL DEFAULT 0.0,
    cover_path TEXT,
    publisher_id INTEGER,
    FOREIGN KEY (publisher_id) REFERENCES users(id)
);

CREATE TABLE library (
    user_id INTEGER,
    game_id INTEGER,
    purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, game_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    buyer_id INTEGER NOT NULL,
    game_id INTEGER NOT NULL,
    amount_paid REAL NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (buyer_id) REFERENCES users(id),
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE gift_cards (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT NOT NULL UNIQUE,
    value REAL NOT NULL
);

-- base data creation to begin with

-- 1admin
INSERT INTO users (nickname, password, role, balance) VALUES ('admin', 'admin', 'ADMIN', 1000.0);

-- 3 publishers
INSERT INTO users (nickname, password, role, balance) VALUES ('Ubisoft', '1234', 'PUBLISHER', 5000.0);

INSERT INTO users (nickname, password, role, balance) VALUES ('Rockstar', '1234', 'PUBLISHER', 5000.0);

INSERT INTO users (nickname, password, role, balance) VALUES ('IndieDev', '1234', 'PUBLISHER', 200.0);

-- 1 standard user
INSERT INTO users (nickname, password, role, balance) VALUES ('PlayerOne', 'password', 'USER', 50.0);

-- stock games data creation

-- 1. about_elise.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('About Elise', 'Horror', 14.99, 14.99, 4.5, 'res/images/grids/about_elise.png', 4);

-- 2. artic_wolves.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Arctic Wolves', 'Survival', 19.99, 9.99, 3.8, 'res/images/grids/artic_wolves.png', 4);

-- 3. brane_prototype.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Brane Prototype', 'Sci-Fi', 29.99, 29.99, 4.0, 'res/images/grids/brane_prototype.png', 2);

-- 4. briefcase.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('The Briefcase', 'Mystery', 9.99, 4.99, 4.2, 'res/images/grids/briefcase.png', 4);

-- 5. cairn.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Cairn', 'Adventure', 24.99, 24.99, 4.7, 'res/images/grids/cairn.png', 3);

-- 6. company_of_heroes.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Company of Heroes', 'Strategy', 39.99, 19.99, 4.8, 'res/images/grids/company_of_heroes.png', 2);

-- 7. doom_64.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('DOOM 64', 'FPS', 4.99, 4.99, 4.6, 'res/images/grids/doom_64.png', 2);

-- 8. eradicator.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Eradicator', 'Action', 14.99, 7.49, 3.5, 'res/images/grids/eradicator.png', 3);

-- 9. iron_lung.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Iron Lung', 'Horror', 5.99, 5.99, 4.9, 'res/images/grids/iron_lung.png', 4);

-- 10. nam.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('NAM', 'Shooter', 9.99, 9.99, 3.2, 'res/images/grids/nam.png', 2);

-- 11. pine_hearts.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Pine Hearts', 'Adventure', 12.99, 10.99, 4.3, 'res/images/grids/pine_hearts.png', 4);

-- 12. quake_brutalist_two.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Quake: Brutalist', 'FPS', 19.99, 19.99, 4.8, 'res/images/grids/quake_brutalist_two.png', 2);

-- 13. ride_the_bullet.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Ride The Bullet', 'Action', 29.99, 14.99, 4.1, 'res/images/grids/ride_the_bullet.png', 3);

-- 14. schedule_one.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Schedule One', 'Simulation', 39.99, 39.99, 3.9, 'res/images/grids/schedule_one.png', 2);

-- 15. the_strange_light.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('The Strange Light', 'Puzzle', 14.99, 14.99, 4.4, 'res/images/grids/the_strange_light.png', 4);

-- 16. turn_out_the_lights.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Turn Out The Lights', 'Horror', 9.99, 2.99, 4.0, 'res/images/grids/turn_out_the_lights.png', 4);

-- 17. tyrant_realm.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Tyrant Realm', 'RPG', 49.99, 49.99, 4.5, 'res/images/grids/tyrant_realm.png', 3);

-- 18. war_thunder.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('War Thunder', 'MMO', 0.00, 0.00, 4.2, 'res/images/grids/war_thunder.png', 2);

-- 19. wolf_wool.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Wolf & Wool', 'Indie', 7.99, 7.99, 4.6, 'res/images/grids/wolf_wool.png', 4);

-- 20. wynncraft.png
INSERT INTO games (title, tag, baseprice, currentprice, score, cover_path, publisher_id) 
VALUES ('Wynncraft', 'MMORPG', 0.00, 0.00, 4.9, 'res/images/grids/wynncraft.png', 4);


-- gift cards
INSERT INTO gift_cards (code, value) VALUES ('ORBIT20', 20.0);
INSERT INTO gift_cards (code, value) VALUES ('WELCOME50', 50.0);
INSERT INTO gift_cards (code, value) VALUES ('SUMMER10', 10.0);

-- fist user library with 2 games
INSERT INTO library (user_id, game_id) VALUES (5, 9);
INSERT INTO library (user_id, game_id) VALUES (5, 18);
