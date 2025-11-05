### Endpoints

> base_url = http://localhost:8080/api

```json
{
  "status": "OK",
  "message": "get ok",
  "data": {}
}
```

```json
{
  "status": "INVALID",
  "error_code": "INVALID_TOKEN",
  "message": "invalid token",
  "errors": [
    {
      "field": "username",
      "message": "username not valid"
    }
  ]
}
```

| Method | Route                            | Description                      |
|:-------|:---------------------------------|:---------------------------------|
| POST   | /auth/login                      | authenticate user                |
| POST   | /auth/register                   | create new user account          |
| POST   | /auth/logout                     | delete session                   |
| POST   | /oauth/authorize                 | authorize user                   |
| POST   | /oauth/token                     | get token after authorize        |
| GET    | /users                           | get user info                    |
| POST   | /products                        | create new product               |
| GET    | /products?limit={num}&page={num} | get all products                 |
| GET    | /products/{id}                   | get product by id                |
| DELETE | /products/delete?product_id={id} | delete product by id             |
| PUT    | /products/update?product_id={id} | update product by id             |
| GET    | /products/images?product_id={id} | get product images by product id |
| GET    | /products/specs?product_id={id}  | get product specs by product id  |
| POST   | /checkouts/{cart_id}             | checkout and make payment        |