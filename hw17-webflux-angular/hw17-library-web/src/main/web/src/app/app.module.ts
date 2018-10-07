import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { BookComponent } from './book/book.component';
import { TableModule } from "primeng/table";
import { AppRoutingModule } from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { CheckboxModule, DropdownModule } from "primeng/primeng";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    BookComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    //primeng modules
    TableModule,
    DropdownModule,
    CheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
