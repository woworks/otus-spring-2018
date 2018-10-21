import { BookComponent } from "./book/book.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { BooksComponent } from "./books/books.component";
import { LoginComponent } from "./login/login.component";
import { AuthGuard } from "./shared/guards/auth.guard";

const routes: Routes = [
  { path: '', redirectTo: 'books', pathMatch: 'full' , canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent },
  {
    path: 'books',
    children: [
      {
        path: '',
        component: BooksComponent,
        canActivate: [AuthGuard],
      },
      {
        path: ':id',
        component: BookComponent,
        canActivate: [AuthGuard]
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
