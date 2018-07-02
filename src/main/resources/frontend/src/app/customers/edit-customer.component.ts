import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "./customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "./customer.model";

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  id: number;
  customer: Customer;
  customerForm: FormGroup;

  constructor(private customerService: CustomerService, private fb: FormBuilder,
              private route: ActivatedRoute, private router: Router) {
    this.initForm();
  }

  ngOnInit() {
    this.route.params.subscribe(p => {
      this.id = p['id'];
      this.customerService.get(p['id']).subscribe((customer: Customer) => {
        if(customer) {
          this.customerForm.controls['name'].patchValue(customer.name);
          this.customerForm.controls['email'].patchValue(customer.email);
          this.customerForm.controls['phone'].patchValue(customer.phone);
          this.customerForm.controls['bio'].patchValue(customer.bio);
        }
      });
    });
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
      id: this.id,
      name: body.controls['name'].value,
      email: body.controls['email'].value,
      phone: body.controls['phone'].value,
      bio: body.controls['bio'].value
    };
    this.customerService.put(customer, this.id).subscribe(res => {
      if(res) {
        location.replace('/customers');
      }
    });
  }

}
