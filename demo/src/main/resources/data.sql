INSERT INTO users (email, password, username)
VALUES ('1@test.com', 'xxx', 'chris'),
       ('2@test.com', 'xxx', 'jacques'),
       ('3@test.com', 'xxx', 'merlin'),
       ('4@test.com', 'xxx', 'valentijn');

INSERT INTO autority_roles (role_name) VALUES ('ROLE_USER');
INSERT INTO autority_roles (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles(user_id, authority_role_id) VALUES ('1', '2');
INSERT INTO user_roles(user_id, authority_role_id) VALUES ('2', '2');
INSERT INTO user_roles(user_id, authority_role_id) VALUES ('3', '1');
INSERT INTO user_roles(user_id, authority_role_id) VALUES ('4', '1');

INSERT INTO recipes (recipe_description, recipe_ingredient, recipe_is_private, recipe_name, user_id)
VALUES ('koken en bakken','kaas en tomaat', true ,'vega', 1),
       ('waterkoken','spagettie', false ,'spagettie citroen', 2),
       ('oven aanzetten..','pizzadeeg', true ,'pizza tonno', 3),
       ('pan op het vuur','karbonade', false ,'hollandse keuken', 4);

INSERT INTO comments (comment_id, comment, recipe_id, user_id)
VALUES (1, 'dit was lekker', 1, 3),
       (2, 'niet was niet lekker', 4, 1),
       (3, 'dit ga ik aanraden', 3, 3);




