insert into recipes (date, description, image, title, sub_category_id, category_id)
values ('2023-07-05 11:13:25', 'vegan', 'https://carlsbadcravings.com/wp-content/uploads/2022/04/Strawberry-Cheesecake-main2.jpg', 'Strawberry Cheesecake', 6, 2);


insert into ingredients (measurement_unit, name, quantity)
values ('ml', 'milk', 200);

insert into recipes_ingredients (recipe_id, ingredients_id)
values (1, 1);