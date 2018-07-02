import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NbAlertModule, NbCardModule, NbLayoutModule, NbThemeModule} from '@nebular/theme';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {CreateCustomerComponent} from './customers/create-customer.component';
import {ListCustomersComponent} from './customers/list-customers.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CustomerService} from "./customers/customer.service";
import {ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from './login/login.component';
import {LoginInterceptor} from "./login/login.interceptor";
import {UserInterceptor} from "./login/user.interceptor";
import {LoginService} from "./login/login.service";
import {AppGuard} from "./app.guard";
import {LayoutComponent} from './components';
import {CommonModule} from "@angular/common";
import {EditCustomerComponent} from "./customers/edit-customer.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'customers', component: ListCustomersComponent, canActivate: [AppGuard]},
  {path: 'customers/new', component: CreateCustomerComponent, canActivate: [AppGuard]},
  {path: 'customers/:id', component: EditCustomerComponent, canActivate: [AppGuard]},
  {path: '', redirectTo: '/customers', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    CreateCustomerComponent,
    EditCustomerComponent,
    ListCustomersComponent,
    LoginComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    NbThemeModule.forRoot({name: 'default'}),
    RouterModule.forRoot(routes, {useHash: true}),
    NbLayoutModule,
    NbCardModule,
    NbAlertModule
  ],
  providers: [
    LoginService,
    CustomerService,
    {provide: HTTP_INTERCEPTORS, useClass: UserInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: LoginInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
