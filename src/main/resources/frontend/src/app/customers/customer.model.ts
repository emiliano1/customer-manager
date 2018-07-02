export class Customer {
  id: number;
  name: string;
  email: string;
  phone: string;
  bio: string;

  constructor(id: number, name: string, email: string, phone: string, bio: string) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.bio = bio;
  }
}
