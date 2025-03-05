import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { StudentServiceService } from '../../service/student-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Students } from '../../models/Students';

@Component({
  selector: 'app-add-student',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.css'
})
export class AddStudentComponent {

  studentForm:FormGroup
  isEdit:boolean = false
  studentId:number = 0
  message:string = ''

  constructor(private formBuilder: FormBuilder, private studentService:StudentServiceService, private route:ActivatedRoute, private router:Router){
    this.studentForm = this.formBuilder.group({
      studentId:['',[Validators.required, Validators.min(1)]],
      studentName:['',Validators.required],
      studentAge:['',[Validators.required,Validators.min(18)]],
      studentEmail:['',[Validators.required, Validators.email]],
      studentAddress:['',Validators.required],
    })
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.studentId = +id;
        this.isEdit = true;
        this.loadStudent(this.studentId);
      }
    });
  }

  loadStudent(studentId: number): void {
    this.studentService.getStudentById(studentId).subscribe(data => {
      this.studentForm.patchValue(data);
    });
  }

  onSubmit(): void {
    if (this.studentForm.valid) {
      if (this.isEdit) {
        this.studentService.updateStudent(this.studentForm.value).subscribe((data) => {
          this.router.navigate(['/studentlist']);
        });
      } else {
        this.addStudent();
      }
    }
  }
  


  addStudent(){
    if(this.studentForm.valid){
      console.log(this.studentForm.value)
      this.studentService.addStudent(this.studentForm.value).subscribe((data)=>{
        this.router.navigate(['/studentlist'])
      },(error)=>{
        this.message = error.error
      })
    }
  }
}
