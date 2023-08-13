-- Brunch 2
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Place the tortilla on a clean surface. Layer avocado, shredded carrots, greens and cucumber on top. Sprinkle with a pinch of salt and pepper. Add herbs if you like. Optionally, add olives or pickles for extra flavor. Fold in the sides and roll it tightly from the bottom. Cut the wrap in half diagonally and secure with toothpicks if needed. Enjoy immediately or wrap in paper for on-the-go eating.',
        'wrap.jpg',
        'Vegan Wrap', 2, 2, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (26, 56),
       (26, 57),
       (26, 58),
       (26, 59),
       (26, 60),
       (26, 61),
       (26, 62);

-- Lunch 3
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Cook the pasta according to the package instructions until al dente. Drain and set aside. In a large skillet, heat the olive oil over medium heat. Add minced garlic, diced tomatoes (with their juices) to the skillet. If using fresh tomatoes, you can dice them and include them in the skillet. Stir in the dried basil and season with salt and pepper. Let the tomato mixture simmer for about 5-7 minutes, allowing the flavors to meld and the sauce to thicken slightly. Toss the cooked pasta into the skillet, coating it with the tomato sauce. Taste and adjust the seasoning as needed. If you like some heat, you can add a pinch of red pepper flakes. Serve the vegan tomato basil pasta in bowls, garnished with fresh chopped basil. Enjoy!',
        'vegan-pasta.jpg',
        'Tomato Basil Pasta', 3, 2, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (27, 63),
       (27, 64);

-- Dessert 3
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Mix the flour and baking powder with ½ tsp salt in a bowl. Mix the vegan butter with the sugar in a separate bowl. Combine the soy milk with 175 ml water in a jug. Pour this gradually into the dry ingredients, continually stirring, then add the butter and sugar mixture and keep stirring to form a smooth batter. Add a little more water or soy milk if the mixture is very thick – you should be able to spoon it out. Heat the waffle iron. Butter both sides of the waffle iron. Spoon about 3 tbsp of the batter into the hot waffle iron, close and cook for 4-6 mins until golden brown (the time will vary depending on your waffle iron). Remove the finished waffle and cook the rest of the batter in the same way. The dough makes about 10-12 vegan waffles. Serve straightaway with your favourite toppings.',
        'waffles.jpg',
        'Vegan Waffles', 6, 2, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (28, 65),
       (28, 66),
       (28, 67),
       (28, 68),
       (28, 69);


-- Dessert 3
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Divide the chia seeds and coconut milk between two serving bowls and stir well. Leave to soak for 5 mins, stirring occasionally, until the seeds swell and thicken when stirred. Meanwhile, combine the rest of the ingredients in a small food processor.  Swirl a spoonful into each bowl, then arrange the nectarine or peach slices on top and scatter with the berries. Will keep in the fridge for 1 day. Add the toppings just before serving.',
        'chia.jpg',
        'Raspberry chia pudding', 6, 2, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (29, 70),
       (29, 71),
       (29, 72),
       (29, 73),
       (29, 74),
       (29, 75);