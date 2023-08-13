import {Recipe} from "./recipe";
import {Review} from "./review";

export interface RecipeReview {
  recipe: Recipe,
  reviews: Review[]
}
