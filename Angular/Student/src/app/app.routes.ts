import { Routes } from '@angular/router';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { FetchStudentComponent } from './components/fetch-student/fetch-student.component';

export const routes: Routes = [
    {path: '', redirectTo:'studentlist',pathMatch:'full'},
    {path:"addstudent", component:AddStudentComponent},
    {path:"studentlist", component: FetchStudentComponent},
    {path:"studentlist/editstudent/:id", component:AddStudentComponent}
];
