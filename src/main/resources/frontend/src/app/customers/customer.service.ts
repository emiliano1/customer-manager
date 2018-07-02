import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CustomerService {

  API: string = 'http://localhost:8080/customers';

  constructor(public httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get(`${this.API}`);
  }

  get(id: number) {
    return this.httpClient.get(`${this.API}/${id}`);
  }

  post(body: Object) {
    return this.httpClient.post(`${this.API}`, body);
  }

  put(body: Object, id: number) {
    return this.httpClient.put(`${this.API}/${id}`, body);
  }

  patch(body: Object, id: number) {
    return this.httpClient.patch(`${this.API}/${id}`, body);
  }
}
