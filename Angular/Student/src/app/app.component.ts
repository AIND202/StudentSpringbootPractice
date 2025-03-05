import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FetchStudentComponent } from './components/fetch-student/fetch-student.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Student';
}
