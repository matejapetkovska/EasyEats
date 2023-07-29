ALTER TABLE reviews DROP CONSTRAINT IF EXISTS uk_b592fv72lwdbrow9yqxj36xob;
ALTER TABLE reviews DROP CONSTRAINT IF EXISTS fk_reviews_recipe;


ALTER TABLE reviews ALTER COLUMN recipe_id DROP NOT NULL;

ALTER TABLE reviews ADD CONSTRAINT FK_reviews_recipe
    FOREIGN KEY (recipe_id) REFERENCES recipes (id);


insert into reviews(comment, rating, recipe_id, user_id)
values ('Tasty!',4,1,16)