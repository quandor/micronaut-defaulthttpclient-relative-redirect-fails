This project demonstrates that DefaultHttpClient of micronaut fails on
redirects with a relative `Location` header.

Execute all tests and check the failing test `redirectToRelativeUrl`.
It fails with
```
io.micronaut.http.client.exceptions.NoHostException: Request URI specifies no host to connect to
	at io.micronaut.http.client.netty.DefaultHttpClient.resolveRequestURI(DefaultHttpClient.java:1199)
	at io.micronaut.http.client.netty.DefaultHttpClient.redirectExchange(DefaultHttpClient.java:736)
```

