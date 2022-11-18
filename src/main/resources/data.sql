INSERT INTO users (id, email, firstname, lastname, password)
VALUES (1, 'rahul@miu.edu', 'rahul', 'niraula', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (2, 'john@miu.edu', 'john', 'doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (3, 'dean@miu.edu', 'Dean', 'Altarawneh', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'CLIENT');


INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 2);
INSERT INTO users_roles (user_id, roles_id)
VALUES (3, 2);

-- INSERT INTO product (id, name, price, id_user)
-- VALUES (1, 'iPhone', 1200, 1);
-- INSERT INTO product (id, name, price, id_user)
-- VALUES (2, 'iPad', 900, 1);
-- INSERT INTO product (id, name, price, id_user)
-- VALUES (3, 'Pen', 5, 1);
insert into  post(author,content,title,user_id) values('Rahul Niraula','This is content','This is title',1);
insert into  post(author,content,title,user_id) values('Rahul Niraula','This is second content','This is second title',1);
insert into  post(author,content,title,user_id) values('Niraula Rahul','This is third content','This is third title',1);

insert into comment(name,post_id) values('the product is awesome','1');
insert into comment(name,post_id) values('Loved the product','1');
insert into comment(name,post_id) values('the product is awesome again','2');
insert into comment(name,post_id) values('the product is not as aspected','2');