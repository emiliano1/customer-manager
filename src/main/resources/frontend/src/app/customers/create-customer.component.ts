import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "./customer.service";

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  customerForm: FormGroup;

  constructor(private customerService: CustomerService, private fb: FormBuilder) {
    this.initForm();
  }

  ngOnInit() {
  }

  initForm() {
    this.customerForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      bio: ['', [Validators.required]]
    })
  }

  onSubmit(body: FormGroup) {
    let customer = {
      name: body.controls['name'].value,
      email: body.controls['email'].value,
      phone: body.controls['phone'].value,
      bio: body.controls['bio'].value
    };
    this.customerService.post(customer).subscribe(res => {
      if(res) {
        location.replace('/customers');
      }
    });
  }

}
