-- Breakfast 1
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'In a medium bowl, whisk the eggs, cottage cheese, salt and black pepper to taste with a fork. Heat a medium nonstick pan over medium-low heat and spray with oil. When warm, pour the eggs in. Using a rubber spatula, slowly scrape sections of eggs to the center of the pan as the bottom starts to set, creating soft folds for about 1 1/2 to 2 minutes total until just set.',
        'scrambled_eggs.jpg',
        'Scrambled Eggs with Cottage Cheese', 1, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (33, 34),
       (33, 35),
       (33, 36),
       (33, 37);

-- Breakfast
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Add all of your ingredients to a blender. Mix until combined. Serve how you would like!',
        'banana_smoothie.jpg',
        'Peanut Butter Banana Smoothie', 1, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (34, 38),
       (34, 39),
       (34, 40),
       (34, 41),
       (34, 42),
       (34, 43);

-- Brunch 2
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Heat a medium nonstick pan over medium heat. Add the bacon and cook, 5 to 6 minutes, turning halfway until crisp. When cooked, set aside on a paper towel and wipe some of the fat, leaving a little to cook the egg. Crack the egg, cover and cook until the yolk is set.
        Meanwhile, toast the bread. Assemble the sandwich by putting the mayo on the bottom, then the egg, then the lettuce, bacon and tomatoes. Cut in half and eat right away.',
        'blt.jpg',
        'BLT Egg Sandwich', 2, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (35, 44),
       (35, 45),
       (35, 46),
       (35, 47),
       (35, 48),
       (35, 49);


-- Lunch 3
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Mix the dry ingredients with the meat. Shape into patties. Cook patties on medium-high heat for 4-5 mins each side until cooked through (165°F/74°C). Add cheese to melt. Toast buns. Assemble burgers with patties, cheese, and toppings. Serve with a side or enjoy on its own. Enjoy your delicious high-protein burger!',
        'burger.jpg',
        'High-Protein Burger', 3, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (36, 50),
       (36, 51),
       (36, 52),
       (36, 53),
       (36, 54),
       (36, 55);

-- Dinner 4
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Cook pasta according to package instructions. Drain and set aside. In a pan, heat olive oil over medium heat. Add minced garlic and cook for a minute. Toss in halved cherry tomatoes and cook for about 3-4 minutes until they soften. Add fresh spinach to the pan and cook for about 2 minutes. Mix in the cooked pasta and heat through. Season with salt and pepper to taste. Sprinkle with grated parmesan cheese before serving. Enjoy your quick and high-protein pasta dish!',
        'pasta.jpg',
        'Quick Pasta with Spinach and Tomatoes', 4, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (37, 56),
       (37, 57),
       (37, 58),
       (37, 59),
       (37, 60),
       (37, 61);

-- Soups and salads 5
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'In a large mixing bowl, combine all of your ingredients and toss gently to coat the lettuce in caesar dressing. This recipe makes enough for two full salads so you’ll have them ready to go for round 2!',
        'salad.jpg',
        'Caesar Salad', 5, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (38, 62),
       (38, 63),
       (38, 64),
       (38, 65),
       (38, 66);

-- Desserts 6
insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Whizz the banana, oats, eggs, milk, baking powder, cinnamon and protein powder in a blender for 1-2 mins until smooth. Check the oats have broken down, if not, blend for another minute. Heat a drizzle of oil in a pan. Pour in 2-3 rounds of batter, leaving a little space between each to spread. Cook for 1-2 minutes, until bubbles start to appear on the surface and the underside is golden. Flip over and cook for another minute until cooked through. Transfer to a warmed oven and repeat with the remaining batter. Serve in stacks with nut butter, maple syrup and fruit.',
        'pancakes.jpg',
        'Banana Oat Protein Pancakes', 6, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES
       (39, 67),
       (39, 68),
       (39, 69),
       (39, 70),
       (39, 71),
       (39, 72);

insert into recipes(date, description, image, title, sub_category_id, category_id, user_id)
values ('2023-08-11 10:10:10',
        'Place 6 paper liners in the wells of a muffin tin and set aside. In a small bowl, mix together the protein powder and peanut butter until smooth. Place the chocolate chips and coconut oil in a bowl; microwave in 30-second increments until fully melted. Place approximately 1 tablespoon of melted chocolate in the bottom of each liner. Place the muffin tin in the freezer for 10 minutes or until chocolate is set. Place 2 teaspoons of the peanut butter mixture on top of the chocolate layer. Add an additional tablespoon of chocolate on top of the peanut butter mixture. Chill until firm. Store in the refrigerator until ready to serve.',
        'peanut_butter_cups.jpg',
        'Protein Peanut Butter Cups', 6, 5, 21);

INSERT INTO recipes_ingredients (recipe_id, ingredients_id)
VALUES (40, 73),
       (40, 74),
       (40, 75),
       (40, 76);
