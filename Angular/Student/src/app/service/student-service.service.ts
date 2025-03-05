import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Students } from '../models/Students';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {

  private apiUrl:string = 'http://localhost:8080/students'

  addStudent(student:Students):Observable<Students>{
    return this.http.post<Students>(this.apiUrl+'/addStudent',student);
  }

  getAllStudents(): Observable<Students[]> {
    return this.http.get<Students[]>(this.apiUrl+'/studentList');
  }

  deleteStudent(studentId: number): Observable<string> {
    return this.http.delete<string>(this.apiUrl+'/deleteStudent/'+studentId,{ responseType: 'text' as 'json' });
  }

  getStudentById(studentId:number):Observable<Students>{
    return this.http.get<Students>(this.apiUrl+'/fetchStudent/'+studentId);
  }

  updateStudent(student:Students):Observable<Students>{
    return this.http.put<Students>(this.apiUrl+"/updateStudent/"+student.studentId,student);
  }

  constructor(private http:HttpClient) { }
}
