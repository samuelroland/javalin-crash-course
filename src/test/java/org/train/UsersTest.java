package org.train;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.After;

import io.javalin.Javalin;
import io.javalin.testtools.HttpClient;
import io.javalin.testtools.JavalinTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.train.models.*;

public class UsersTest {

	Javalin app = Main.setupApp();

	private User parseAsUser(String text) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(text, User.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Before
	public void setup() {
		app.start();
	}

	@After
	public void tearDown() {
		app.stop();
	}

	@Test
	public void get_all_users_returns_an_empty_list_at_start() {
		JavalinTest.test(app, (server, client) -> {
			var res = client.get("/users");
			assertEquals(200, res.code());
			assertEquals("[]", res.body().string());
		});
	}

	@Test
	public void create_user_works_but_id_is_ignored() {
		JavalinTest.test(app, (server, client) -> {
			String body = "{\"id\": \"2234\",\"username\": \"john\",\"password\": \"john\"}";
			var res = client.post("/users", body);
			assertEquals(201, res.code()); // HTTP 201 means "resource created"
			User expectedUser = parseAsUser(body);
			User givenUser = parseAsUser(res.body().string());

			assertEquals(expectedUser.username, givenUser.username);
			assertEquals(expectedUser.password, givenUser.password);
			assertNotEquals(expectedUser.id, givenUser.id);
		});
	}

	// @Test
	// public void create_user_without_some_info_fails() {
	// JavalinTest.test(app, (server, client) -> {
	// var res = client.post("/users", "{\"username\": \"john\",\"password\":
	// \"john\"}");
	// assertEquals(400, res.code()); // ME: good code ?
	// });
	// }

	@Test
	public void get_one_users_works() {
		JavalinTest.test(app, (server, client) -> {
			int userId = create3Users(client);
			var finalRes = client.get("/users/" + userId);
			assertEquals(200, finalRes.code());
			assertEquals("{\"id\":" + userId + ",\"username\":\"bb\",\"password\":\"pwdbb\"}",
					finalRes.body().string());
		});
	}

	@Test
	public void get_one_unkown_user_fails() {
		JavalinTest.test(app, (server, client) -> {
			var res = client.get("/users/3232");
			assertEquals(404, res.code());
		});
	}

	// TODO: write the update test and then implement to code update method
	// you can use create3Users()

	@Test
	public void delete_a_user_works() {
		JavalinTest.test(app, (server, client) -> {
			int id = create3Users(client);
			var res = client.delete("/users/" + id);
			assertEquals(204, res.code());
		});
	}

	@Test
	public void delete_an_unkown_user_fails() {
		JavalinTest.test(app, (server, client) -> {
			create3Users(client);
			var res1 = client.get("/users");

			var res = client.delete("/users/3232");
			assertEquals(404, res.code());
			var res2 = client.get("/users");
			// Same list before and after
			assertEquals(res1.body().string(), res2.body().string());
		});
	}

	// TODO: refactor these tests to see how we can setup the db
	private int create3Users(HttpClient client) {
		String body = "{\"id\": \"2234\",\"username\": \"john\",\"password\": \"pwdjohn\"}";
		client.post("/users", body);
		String body2 = "{\"id\": \"2234\",\"username\": \"aaaa\",\"password\": \"pwdaaaa\"}";
		client.post("/users", body);
		String body3 = "{\"id\": \"2234\",\"username\": \"bb\",\"password\": \"pwdbb\"}";
		var res = client.post("/users", body3);
		try {
			User lastUser = parseAsUser(res.body().string());
			return lastUser.id;
		} catch (Exception e) {
			return 0;
		}
	}
}