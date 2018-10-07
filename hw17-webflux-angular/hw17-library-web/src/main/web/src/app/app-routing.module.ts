import { BookComponent } from "./book/book.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import {BooksComponent} from "./books/books.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'books',
    pathMatch: 'full'
  },
  {
    path: 'books',
    children: [
      {
        path: '',
        component: BooksComponent
      },
      {
        path: ':id',
        component: BookComponent
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: []
})

export class AppRoutingModule {

}
