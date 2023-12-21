# Javalin crash course
A pretty small crash course on [Javalin](https://javalin.io/), a simple Java web framework, just to get started with importants concepts to develop a web API.

You are going to create a simple comments system called `Comment?` (this is a French joke, don't lose time understanding it).

Your API only supports operations related to comments and their authors.

First an example of comments:
```json
[
	{
		"id": 3,
		"content": "Hello there, I really like the sunshine today",
		"date": "2023-12-20",
		"author_id": 1
	},
	{
		"id": 5,
		"content": "It's seems to be cloudy today.",
		"date": "2023-12-30",
		"author_id": 2
	},
	{
		"id": 12,
		"content": "Let's talk about weather specialist that are ALWAYS wrong. New Year's Eve dinner without any snow !",
		"date": "2023-12-31",
		"author_id": 6
	},
	{
		"id": 21,
		"content": "Already feeling the vibe of 2025.",
		"date": "2024-12-03",
		"author_id": 1
	}
]
```

Then an example of users:
```json
[
	{
		"id": 3,
		"username": "Michele",
		"password": "Michele",
	},
	{
		"id": 5,
		"username": "John",
		"password": "John",
	},
	{
		"id": 10,
		"username": "Carla",
		"password": "Carla",
	}
]
```
As you noticed passwords are not hashed, this is very bad in terms of security, but you will implement this later.

## Steps
There is no authentication and login system, there is no permission system, everyone can do any action on all items. Your goal is to implement the basic CRUD (Create Read Update Delete) operations on comments and users, and on top of that a few sort and filter system on comments to discover more parts of the Javalin framework. The route are defined in the tests and documented here so you learn to read and understand those tests !

To make these tests fully pass you have to:
1. Setup the `Main` class to have a working Javalin server on the given port, returns the welcome message. Then build and run the app with this command:
```bash
mvn package -DskipTests -Dmaven.test.skip=true -T 8 && java -jar target/server-*.jar
```

This kind of output on the server start is expected:
```
22:53:43.119 [main] INFO  io.javalin.Javalin - Starting Javalin ...
22:53:43.269 [main] INFO  org.eclipse.jetty.server.Server - jetty-11.0.17; built: 2023-10-09T18:39:14.424Z; git: 48e7716b9462bebea6732b885dbebb4300787a5c; jvm 21.0.1+12
22:53:43.344 [main] INFO  o.e.j.s.s.DefaultSessionIdManager - Session workerName=node0
22:53:43.356 [main] INFO  o.e.j.server.handler.ContextHandler - Started i.j.j.@72035809{/,null,AVAILABLE}
22:53:43.374 [main] INFO  o.e.jetty.server.AbstractConnector - Started ServerConnector@66d18979{HTTP/1.1, (http/1.1)}{0.0.0.0:7000}
22:53:43.395 [main] INFO  org.eclipse.jetty.server.Server - Started Server@57250572{STARTING}[11.0.17,sto=0] @643ms
22:53:43.395 [main] INFO  io.javalin.Javalin - 
       __                  ___          ______
      / /___ __   ______ _/ (_)___     / ____/
 __  / / __ `/ | / / __ `/ / / __ \   /___ \
/ /_/ / /_/ /| |/ / /_/ / / / / / /  ____/ /
\____/\__,_/ |___/\__,_/_/_/_/ /_/  /_____/

       https://javalin.io/documentation

22:53:43.395 [main] INFO  io.javalin.Javalin - Your JDK supports Loom. Javalin will prefer Virtual Threads by default. Disable with `ConcurrencyUtil.useLoom = false`.
22:53:43.401 [main] INFO  io.javalin.Javalin - Listening on http://localhost:7000/
22:53:43.414 [main] INFO  io.javalin.Javalin - You are running Javalin 5.6.3 (released October 15, 2023).
22:53:43.417 [main] INFO  io.javalin.Javalin - Javalin started in 299ms \o/
Javalin crash course
```

The first test `home_page_returns_welcome_message` should also pass !

1. Implement everything required to make tests in `UsersTest.java`.
   1. I already prepared a working model `User.java` inside a `models` subfolder.
   1. Routes definition will be in `Main` but your logic related to users must be in a controller file like `UsersController.java`, you can create a subfolder `controllers` for them to not mix them with other classes.
   <!-- 1. Make sure the attributes you want to be in the JSON content are public ! And that there is a default constructor, or you will get parsing errors from Jackson. -->
   1. Store your users into a `ConcurrentHashMap` with integer keys and User values.
   1. To return the elements of the hashmap not the indexed content, use the `.elements()`
   1. Use exception `NotFoundResponse` instead of just a 404 status
1. Implement everything required to make tests in `CommentsTest.java`.
   1. NOT IMPLEMENT FOR NOW sorry...
   1. Same rules and idea that for users.

## Contribution
If you would like to improve this project, improve some explanations, add tests and their features, you are welcome ! Just try to send small PRs with a single goal, so it's easier to review and discuss changes.

## License

Javalin crash course, by Samuel Roland is a free software released under the [AGPL-3.0-or-later](LICENSE).
