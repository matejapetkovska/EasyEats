ALTER TABLE recipes
    ALTER COLUMN description TYPE TEXT;

UPDATE recipes
SET description = 'Gather Ingredients: Cream cheese, sugar, eggs, sour cream (optional), graham cracker crumbs, butter, vanilla extract, lemon juice (optional), and any desired toppings.\n\nPrep the Pan: Grease a 9-inch springform pan.\n\nPrepare the Crust: Mix melted butter with graham cracker crumbs and press into the pan.\n\nPreheat the Oven: Set it to 325°F (160°C).\n\nPrepare the Filling: Beat cream cheese, sugar, vanilla extract, lemon juice (optional), and sour cream until smooth, then add eggs one at a time.\n\nPour the Filling: Spread it evenly over the crust.\n\nBake the Cheesecake: Bake at 325°F (160°C) for 50-60 minutes until edges are set and center is slightly jiggly.\n\nCool and Chill: Let it cool for an hour, then refrigerate for several hours or overnight to firm up and develop flavors.'
WHERE recipes.id = 1;

