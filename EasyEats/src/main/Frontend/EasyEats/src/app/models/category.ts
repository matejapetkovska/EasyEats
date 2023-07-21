import { SubCategory } from "./sub_category";

export interface Category{
    id: Number;
    name: String;
    description: String;
    sub_categories: SubCategory[];
}