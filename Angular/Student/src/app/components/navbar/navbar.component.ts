import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  imports: [CommonModule],
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isStudentListPage: boolean = false;
  isAddStudentPage: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Check if the current URL is the student list or add student page
        this.isStudentListPage = event.urlAfterRedirects === '/studentlist';
        this.isAddStudentPage = event.urlAfterRedirects === '/addstudent';
      }
    });
  }
}
