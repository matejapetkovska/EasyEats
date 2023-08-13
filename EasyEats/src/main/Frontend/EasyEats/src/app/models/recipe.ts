import {Category} from "./category";
import {Ingredient} from "./ingredient";
import {SubCategory} from "./sub_category";
import {User} from "./user";

export interface Recipe {
  id: Number;
  title: String;
  description: String;
  ingredients: Ingredient[];
  image: String;
  date: Date;
  category: Category;
  subCategory: SubCategory;
  user: User;
}
