import {Component, OnInit} from '@angular/core';
import {Category} from '../../models/category';
import {CategoryService} from '../../services/category-service.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  categories: Category[] | undefined

  constructor(private categoryService: CategoryService, private router: Router) {
  }

  ngOnInit(): void {
    this.categoryService.getAllCategories()
      .subscribe({
        next: (categories) => {
          this.categories = categories;
          this.addPathToImages(categories);
        },
        error: () => {
          console.log('error in getting categories');
        }
      });
  }

  addPathToImages(list: Category[]) {
    for (let i = 0; i < list.length; i++) {
      list[i].image = "../../assets/images/" + list[i].image;
    }
  }
}
