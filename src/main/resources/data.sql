INSERT INTO public.authorities(id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO public.authorities(id, authority) VALUES(2, 'ROLE_USER');

INSERT INTO public.users (id, created_at, updated_at, email, password, username) VALUES (1, '2018-06-30 07:00:27.896000', '2018-06-30 07:00:30.523000', 'admin@project.com', '$2a$10$abNTt3/77heHv/FCM9MTs.AgdpVaI62uon9z.1IM3jyX2Sx0ggFLW', 'admin');
INSERT INTO public.users (id, created_at, updated_at, email, password, username) VALUES (2, '2018-06-30 07:00:27.896000', '2018-06-30 07:00:30.523000', 'user@project.com', '$2a$10$abNTt3/77heHv/FCM9MTs.AgdpVaI62uon9z.1IM3jyX2Sx0ggFLW', 'user');

INSERT INTO public.user_authorities (user_id, authority_id) VALUES (1, 1);
INSERT INTO public.user_authorities (user_id, authority_id) VALUES (2, 2);

INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (1, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 1', 'customer1@test.com', 'Customer 1', '+555 555-5555');
INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (2, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 2', 'customer2@test.com', 'Customer 2', '+555 555-6665');
INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (3, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 3', 'customer3@test.com', 'Customer 3', '+555 555-7775');
INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (4, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 4', 'customer4@test.com', 'Customer 4', '+555 555-8885');
INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (5, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 5', 'customer5@test.com', 'Customer 5', '+555 555-9995');
INSERT INTO public.customer (id, created_at, updated_at, bio, email, name, phone) VALUES (6, '2018-07-01 17:13:41.166000', '2018-07-01 17:13:43.864000', 'Text bio customer 6', 'customer6@test.com', 'Customer 6', '+555 555-0005');

ALTER SEQUENCE hibernate_sequence RESTART WITH 7;

