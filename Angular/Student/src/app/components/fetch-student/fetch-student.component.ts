import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Students } from '../../models/Students';
import { StudentServiceService } from '../../service/student-service.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-fetch-student',
  imports: [CommonModule, FormsModule],
  templateUrl: './fetch-student.component.html',
  styleUrl: './fetch-student.component.css'
})
export class FetchStudentComponent implements OnInit {

  message: string = '';
  search: number | null = null;
  studentList: Students[] = [];
  filteredList: Students[] = [];

  constructor(private studentService: StudentServiceService, private router: Router, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.studentService.getAllStudents().subscribe((data) => {
      this.studentList = data;
      this.filteredList = data;
      this.cdr.detectChanges();
    }, (error) => {
      this.message = error.error;
    });
  }

  delete(studentId: number) {
    this.studentService.deleteStudent(studentId).subscribe(
      (data) => {
        this.message = data;
        this.studentList = this.studentList.filter(student => student.studentId !== studentId);
        this.filteredList = this.filteredList.filter(student => student.studentId !== studentId);
        this.cdr.detectChanges();
      },
      (error) => {
        this.message = error.error;
      }
    );
  }

  update(studentId: number) {
    this.router.navigate(['studentlist/editstudent', studentId]);
  }

  searchStudent() {
    if (this.search !== null && this.search !== undefined) {
      this.filteredList = this.studentList.filter(student => student.studentId === this.search);
      if (this.filteredList.length === 0) {
        this.message = `No student found with id ${this.search}`;
      } else {
        this.message = '';
      }
    } else {
      this.filteredList = this.studentList;
      this.message = '';
    }
    this.cdr.detectChanges(); // Trigger change detection after filtering
  }
}
