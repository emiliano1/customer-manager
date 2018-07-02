import {Component, OnInit} from '@angular/core';
import {Customer} from "./customer.model";
import {CustomerService} from "./customer.service";

@Component({
  selector: 'app-list-customers',
  templateUrl: './list-customers.component.html',
  styleUrls: ['./list-customers.component.css']
})
export class ListCustomersComponent implements OnInit {

  customers: any[];

  constructor(private customerService: CustomerService) {

  }

  ngOnInit() {
    this.getCustomers();
  }


  getCustomers() {
    this.customerService.getAll().subscribe((data: Customer[]) => {
      if(data.length > 0) {
        this.customers = data;
      }
    });
  }

}
