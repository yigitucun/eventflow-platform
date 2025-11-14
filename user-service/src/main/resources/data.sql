
INSERT INTO roles(name) VALUES('ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles(name) VALUES('ROLE_USER') ON CONFLICT DO NOTHING;

INSERT INTO users(name, username, email, password, role_id) VALUES ('efe','efe1232','efe@hotmail.com','$2a$10$i.n6OkctcJpwJltl4e76PeYkuh4DScnyol0NgHAKhyhctjlAoCZgq',2) ON CONFLICT DO NOTHING ;
INSERT INTO users(name, username, email, password, role_id) VALUES ('yigit','yigit1232','yigit@hotmail.com','$2a$10$CeWXTZQGPYVMSdnYQtnm7.1CeH2btc0FyLMNKy3YLCy/iQHuA9ru.',1) ON CONFLICT DO NOTHING