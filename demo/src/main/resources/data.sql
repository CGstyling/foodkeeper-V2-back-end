INSERT INTO users (email, password, username)
VALUES ('1@test.com', '$2a$12$yS4pEvoWpHLhYyRTDguNEeDM3Y4UStHYMMO0f2GeBwMAY0cj150Re', 'chris'), --chris
       ('2@test.com', '$2a$12$BhTFl6QJRzQjLqaU7K6zauJHY0IPkZXYfokMAbue0LdmyzVYS6Sea', 'jacques'), --jacques
       ('3@test.com', '$2a$12$ZHdjKDjpwab2ObZOAQ3re.auLHJ8OUlk4oUfV2oDDE4HXL4vdHQ9G', 'merlin'), --merlin
       ('4@test.com', '$2a$12$QqOV6lzY3B2K3joVS6.umu1I6.S8bbcOB8RqGrvE9bN6Z5jAuiV4a', 'valentijn'); --valentijn

INSERT INTO auth_roles (role_name) VALUES ('ROLE_USER');
INSERT INTO auth_roles (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles(user_id, auth_role_id) VALUES ('1', '2');
INSERT INTO user_roles(user_id, auth_role_id) VALUES ('2', '2');
INSERT INTO user_roles(user_id, auth_role_id) VALUES ('3', '1');
INSERT INTO user_roles(user_id, auth_role_id) VALUES ('4', '1');

INSERT INTO recipes (recipe_description, recipe_ingredient, recipe_is_private, recipe_name, user_id)
VALUES ('koken en bakken','kaas en tomaat', true ,'vega', 1),
       ('waterkoken','spagettie', false ,'spagettie citroen', 2),
       ('oven aanzetten..','pizzadeeg', true ,'pizza tonno', 3),
       ('pan op het vuur','karbonade', false ,'hollandse keuken', 4);

INSERT INTO comments (comment, recipe_id, user_id)
VALUES ('dit was lekker', 1, 3),
       ('niet was niet lekker', 4, 1),
       ('dit ga ik aanraden', 1, 2);




