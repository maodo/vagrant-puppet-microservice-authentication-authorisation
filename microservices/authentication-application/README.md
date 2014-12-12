# Authentication application

Simple dropwizard application for authenticating logins.

Uses static data, only POST /api/login with the following will return 201, rest will return 401:

```
{"username":"user","password":"password"}

OR

{"username":"admin","password":"password"}
```
