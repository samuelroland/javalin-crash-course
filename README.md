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

There is no authentication and login system, there is permissions, everyone can do any action on all items. Your goal is to implement the basic CRUD (Create Read Update Delete) operations on comments and users, and on top of that a few sort and filter system on comments to discover more parts of the Javalin framework.
## License

Javalin crash course, by Samuel Roland is a free software released under the AGPL-3.0-or-later. See [LICENSE](LICENSE) file.

