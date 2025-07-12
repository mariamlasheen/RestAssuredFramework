# RestAssuredFramework

A modular and maintainable API automation testing framework using **Java**, **Rest-Assured**, **TestNG**, and **Maven**.

This project follows a **clean Git strategy**:
- `main`: Production (protected, pull requests only)
- `dev`: Staging
---

## 📌 Features

- 🔁 Data-driven REST API test automation
- ⚙️ Configurable via `config.properties`
- 🧪 Organized using TestNG and Maven
- 📂 Clean, scalable folder structure
- 🚀 Git branching flow with `main` as a clean production branch
- 📦 Easy integration with CI/CD pipelines
- ✅ Validates request/response integrity and data consistency
- ✅ Implements error handling for:
  - Invalid requests
  - Network failures
  - Unexpected responses
- ✅ Fully externalized configuration (e.g., base URIs, endpoints)
- ✅ Clear code structure using utilities, request builders, and test packages
- ✅ Uses Git for version control
- ✅ Includes this `README.md` for setup and run instructions

---

## TestCases for :
-  API testing using `https://reqres.in`:
  - Create User (POST `/api/users`)
  - Retrieve User (GET `/api/users/{id}`)
  - Update User (PUT `/api/users/{id}`)


    
## 🛠️ Tech Stack

| Component     | Technology       |
|---------------|------------------|
| Language      | Java 17+         |
| Build Tool    | Maven            |
| Testing       | TestNG           |
| API Testing   | Rest-Assured     |
| Config/Utils  | Java Properties, Custom Utilities |


---

## 🧰 Setup

```bash
git clone https://github.com/mariamlasheen/RestAssuredFramework.git
cd RestAssuredFramework
mvn clean test

```

⚙️ Configuration
Edit config.properties:

``` properties
base.uri=https://reqres.in
endpoint.createUser=/api/users
```

```bash
src/
  ├── main/java/utils/       # Utilities
  └── test/java/
      ├── apis/              # API builders
      └── tests/             # Test cases

config.properties            # Environment config
pom.xml                      # Dependencies
testng.xml                   # Test suite
```

## Project Structure Overview
✅ 1. Test Classes (tests package)
These classes contain the actual test cases using TestNG and Rest-Assured.

🔹 `CreateUserTest.java`
	- Purpose: Tests the POST /api/users endpoint.
		- Key Method:
		`testCreateUser()`
		Sends a POST request with a user JSON body and validates the status code and response content.

🔹 `GetUserTest.java`
	- Purpose: Tests the GET /api/users/{id} endpoint.
		- Key Method:
		`testGetUser()`
		Retrieves a user and asserts the returned data matches expectations.

🔹 `UpdateUserTest.java`
	- Purpose: Tests the PUT /api/users/{id} endpoint.
		Key Method:
		`testUpdateUser()`
		Updates user details and validates the response status and updated fields.

✅ 2. API Classes (apis package)
These handle building and sending requests. They're separated from test logic for better structure.

🔹 `CreateUserApi.java`
		- Method: `createUser(String name, String job)`
			Builds and sends a POST request to create a new user & Returns the API response.

🔹 `GetUserApi.java`
		- Method: `getUserById(int id)`
			Sends a GET request to retrieve a user by ID & Returns the response for validation.

🔹 `UpdateUserApi.java`
		- Method: `updateUser(int id, String name, String job)`
			Sends a PUT request to update user data & Returns the updated user’s response.

✅ 3. Utils (utils package)

🔹 `ConfigReader.java`
	Purpose: Loads values from `config.properties`
	Key Methods:
		- `getBaseUri()`: Returns base URI for the API.
		- `getProperty(String key)`: Generic method to fetch any property.


✅ 4. Configuration Files
🔹 `config.properties`
	Stores environment-specific config like:
		```
		properties
		base.uri=https://reqres.in
		endpoint.createUser=/api/users
		```

🔹 testng.xml
	Defines test execution order via TestNG.




🙋‍♀️ Author
Mariam Lasheen
GitHub
