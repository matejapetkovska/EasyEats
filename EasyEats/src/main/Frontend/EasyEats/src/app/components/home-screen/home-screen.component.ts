import {Component, OnInit} from '@angular/core';
import {Category} from '../../models/category';
import {CategoryService} from '../../services/category-service.service';


@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  categories: Category[] | undefined

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.categoryService.getAllCategories()
      .subscribe({
        next: (categories) => {
          this.categories = categories;
        },
        error: () => {
          console.log('error in getting categories');
        }
      });
  }
}
