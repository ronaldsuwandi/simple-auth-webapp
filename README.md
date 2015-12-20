# simple-auth-webapp

An attempt to use Stuart Sierra's [component](component) framework in a [ring](ring)-based web server 
using [Compojure] (compojure) for routing and [Buddy](buddy) for authentication (also uses Stuart Sierra's 
[reloaded](reloaded) workflow)

Essentially the app is divided into 3 components:
1. Auth (which requires `db`)
2. Db (for database)
3. Server (which requires both `auth` and `db`)

Currently `db` component doesn't do anything, the idea is that when you use proper 
database system you'll need to maintain the connection state, currently I simply
make a dummy implementation which just store the values in memory

In terms of authentication part, I experimented with JWS token and I also created
a `buddy.auth.backend` wrapper to read the token value from cookie as well as HTTP header

I think the code is pretty self-explanatory. It has 3 endpoints:

* /about -- simply returns a text output
* /login -- only accepts post request to login
* /users -- return "auth!" text if user is authenticated


## Usage

Either use execute `lein run` or in REPL mode `(user/reset)`

To login

```
curl -v -c cookies.txt -X POST -H "Content-Type: application/json" -d '{"username": "root", "password": "admin_password"}' "localhost:9001/login"

OR add "remember: true" if you want the cookie to be persisted longer 

curl -v -c cookies.txt -X POST -H "Content-Type: application/json" -d '{"username": "root", "password": "admin_password", "remember": true}' "localhost:9001/login"
```

Once logged in
```
curl -v -b cookies.txt -X GET "localhost:9001/users"

OR to use token header

curl -v -H "Authorization: token <TOKEN>" -X GET "localhost:9001/users"
```

## License

Copyright © 2015 Ronald Suwandi

Distributed under the MIT license.


[component]: https://github.com/stuartsierra/component
[ring]: https://github.com/ring-clojure/ring
[compojure]: https://github.com/weavejester/compojure/
[buddy]: https://github.com/funcool/buddy-auth/

