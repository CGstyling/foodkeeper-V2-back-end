INSERT INTO users (id, biography, email_address, password, user_name, recipe_id)
VALUES (1, 'Hallo ik ben christina', 'Christina@gmail.com', 'xxx', 'Guseva', 1),
       (2, 'Hallo ik ben jacques', 'Jacques@gmail.com','xxx', 'Peters', 2);

insert into recipes (id, title, ingredients, description, is_private)
values
    (1, 'Pasta', 'Pasta, water, room, tomaten', 'kook het water en leg de pasta er in', false),
    (2, 'Cake', 'bloem, eieren, bakpoeder, kaneel, vanille', 'meng de eieren en de bloem samen tot 1 geheel', true),
    (2, 'Oesters', 'Oesters 12 stuks, rode ui, rode wijn azijn', 'breek de oesters open, snipper de ui en meng het met de rode wijnazijn', true);