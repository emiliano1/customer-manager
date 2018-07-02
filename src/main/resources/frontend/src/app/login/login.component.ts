import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "./login.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  url: string;
  message: string;
  loginForm: FormGroup;

  constructor(public route: ActivatedRoute,
              public router: Router,
              public loginService: LoginService,
              public fb: FormBuilder) {
    this.initForm();
    this.loginService.logout();
    this.url = this.route.snapshot.queryParams['url'] || '/';
  }

  ngOnInit() {

  }

  initForm() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(object: FormGroup) {
    console.log(object);
    if(this.loginForm.valid) {
      this.loginService.login(object.controls['username'].value,
        object.controls['password'].value).pipe(first()).subscribe(user => {
          this.router.navigate([this.url]);
      }, error => {
          this.message = error;
      });
    }
  }

  get f() { return this.loginForm.controls; }

}
