import { Category } from "./category";
import { Ingredient } from "./ingredient";
import { SubCategory } from "./sub_category";

export interface Recipe{
    id: Number;
    title: String;
    description: String;
    ingredients: Ingredient[];
    image: String;
    date: Date;
    category: Category;
    sub_category: SubCategory;
}