import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {User} from "./user.model";

@Injectable({providedIn: 'root'})
export class LoginService {

  url: string = 'http://localhost:8080/auth/login';

  constructor(private httpClient: HttpClient) {
  }

  login(username: string, password: string) {
    return this.httpClient.post<User>(this.url, {username: username, password: password}).pipe(map((res: any) => {
      if (res && res.token) {
        localStorage.setItem('user', JSON.stringify({username, token: res.token}));
      }
    }));
  }

  logout() {
    localStorage.removeItem('user');
  }
}
