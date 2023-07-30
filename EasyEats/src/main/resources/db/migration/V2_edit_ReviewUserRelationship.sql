ALTER TABLE reviews DROP CONSTRAINT IF EXISTS uk_1rd6lic349nmvj7qio2pmghj4;

ALTER TABLE reviews ALTER COLUMN user_id DROP NOT NULL;

ALTER TABLE reviews ADD CONSTRAINT fk_reviews_users FOREIGN KEY (user_id) REFERENCES users (id);
