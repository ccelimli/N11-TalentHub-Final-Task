# USER SERVICE / JAVA 21

## User Entity

### Overview
The `User` class is an entity that represents a user in the system. It extends the `BaseEntity` class and includes a variety of fields such as name, birth date, email, and more.

### Annotations
- `@Entity`: Specifies that the class is an entity.
- `@Table(name = "users")`: Maps the entity to the "users" table in the database.
- `@Getter` and `@Setter`: Lombok annotations to generate the getters and setters.
- `@AllArgsConstructor`: Lombok annotation to generate a constructor with all arguments.
- `@NoArgsConstructor`: Lombok annotation to generate a no-argument constructor.

### Fields
- `id`: The unique identifier for the user.
- `firstName`: The user's first name.
- `lastName`: The user's last name.
- `birthDate`: The user's date of birth.
- `email`: The user's email address.
- `phoneNumber`: The user's phone number.
- `username`: The user's username.
- `password`: The user's password.
- `gender`: The user's gender.
- `latitude`: The user's latitude coordinate.
- `longitude`: The user's longitude coordinate.
- `status`: The user's account status.

### Constraints
Each field is annotated with `@NotBlank` to ensure that it is not null or empty. Additionally, the `birthDate` field is annotated with `@Past` to indicate that the date must be in the past.

### Enumerations
- `Gender`: An enumeration that defines the user's gender.
- `Status`: An enumeration that defines the user's account status.

### Usage
This class is used to create, read, update, and delete user information in the application. It is also used to interact with the database through the repository layer.

### Notes
- Ensure that the `BaseEntity` class is properly defined and available in the project.
- Make sure to handle the `LocalDate` type correctly when persisting to the database.
- The `password` field length is set to 28, which suggests it might be storing hashed values. Ensure to use proper security measures for storing passwords.

## UserSaveRequest Record

### Overview
The `UserSaveRequest` record is a data structure used to capture all necessary information to save a user's data. It is designed to ensure that all required fields are present and correctly formatted before the data is persisted.

### Annotations
- `@NotNull`: Used to indicate that a field must not be null.
- `@Min` and `@Max`: Used to set the minimum and maximum values for latitude and longitude.

### Fields
- `firstName`: The user's first name.
- `lastName`: The user's last name.
- `birthDate`: The user's date of birth.
- `email`: The user's email address.
- `phoneNumber`: The user's phone number.
- `countryCode`: The country code associated with the user's phone number.
- `username`: The user's chosen username.
- `password`: The user's chosen password.
- `gender`: The user's gender.
- `latitude`: The user's latitude coordinate, must be between $$-90$$ and $$90$$.
- `longitude`: The user's longitude coordinate, must be between $$-180$$ and $$180$$.

### Usage
This record is primarily used when creating a new user or updating existing user information. It ensures that all the necessary validations are in place for the data integrity.

### Notes
- The `CountryCode` should be an enumeration or a separate class that validates the country code format.
- The `latitude` and `longitude` fields are validated to be within the Earth's coordinate range, which is important for location-based services.
- Ensure that the `Gender` enumeration is consistent with the one used in the `User` entity.

## UserRepository Interface

### Overview
The `UserRepository` interface extends the `JpaRepository` and provides custom methods to change the status of a `User` entity in the database.

### Annotations
- `@Repository`: Indicates that the interface is a repository and a part of the persistence layer.
- `@Modifying`: Indicates that the method will modify the state of the database.
- `@Query`: Used to define the custom query to be executed.

### Methods
- `changeStatusToActive(Long id)`: Updates the status of a user to 'ACTIVE' based on the user's ID.
- `changeStatusToDeactive(Long id)`: Updates the status of a user to 'DEACTIVE' based on the user's ID.

### Parameters
- `@Param("id")`: Binds the method parameter `id` to the named parameter `:id` in the query.

### Usage
These repository methods are used to programmatically change the activation status of a user within the system. This can be part of administrative controls or user account management features.

### Notes
- Ensure that the `User` entity has a `status` field of an appropriate type that matches the status values used in the queries.
- The `@Modifying` annotation is important for executing update queries that modify the database without returning a value.
- These methods should be used within a service layer that handles the business logic of the application.

## UserControllerContractImpl Class

### Overview
The `UserControllerContractImpl` class implements the `UserControllerContract` and provides the business logic for managing user data. It uses the `UserEntityService` to interact with the database and perform CRUD operations.

### Annotations
- `@Service`: Indicates that this class is a service component in the Spring framework.
- `@RequiredArgsConstructor`: Lombok annotation to generate a constructor with required arguments, particularly for final fields.
- `@Transactional`: Specifies that the method is transactional and any failure will result in a rollback.

### Methods
- `save(UserSaveRequest userSaveRequest)`: Validates and saves a new user to the database.
- `update(UserUpdateRequest userUpdateRequest)`: Validates and updates an existing user's information.
- `findById(Long id)`: Retrieves a user by their ID.
- `findAll()`: Retrieves all active users.
- `findAllByDeactive()`: Retrieves all deactivated users.
- `findByIdInDeactive(Long id)`: Retrieves a deactivated user by their ID.
- `delete(Long id)`: Deletes a user by their ID.
- `active(Long id)`: Activates a user by their ID.
- `deactive(Long id)`: Deactivates a user by their ID.

### Usage
This class is used as part of the service layer in a Spring application to handle user-related operations. It ensures that all user data is validated using regular expressions before any action is taken.

### Notes
- The class relies on `UserEntityService` for database operations, which should be an interface extending `JpaRepository`.
- `UserMapper` is used for converting between `UserSaveRequest`, `UserUpdateRequest`, and `UserDTO` objects. Ensure that this mapper is correctly implemented using MapStruct or another mapping framework.
- Exception handling is done using custom exceptions like `ItemNotFoundException` which should be defined elsewhere in the application.
- The `RegularExpression` utility class is used for input validation; it should contain methods to validate emails, phone numbers, and names.

## UserController Class

### Overview
The `UserController` class is a REST controller that provides endpoints for managing users within the application. It is tagged with "User Controller" for API documentation purposes and is mapped to handle requests at `/api/v1/users`.

### Annotations
- `@RestController`: Indicates that this class is a REST controller component in the Spring framework.
- `@RequestMapping("/api/v1/users")`: Specifies the base URL for all endpoints in this controller.
- `@Tag`: Provides metadata for the API documentation, including the name and description of the controller.

### Endpoints
- `POST /`: Creates a new user with the provided `UserSaveRequest` data.
- `GET /`: Retrieves a list of all registered users.
- `PATCH /active/{id}`: Changes the status of a registered user to active.
- `PATCH /deactive/{id}`: Changes the status of a registered user to deactive.
- `GET /{id}`: Retrieves the details of a user by their ID.
- `PUT /{id}`: Updates the information of a registered user with the provided `UserUpdateRequest` data.
- `DELETE /{id}`: Deletes a user by their ID.
- `GET /with-deactives`: Lists all registered users whose status is set to 'DEACTIVE'.
- `GET /with-deactives/{id}`: Retrieves a specific user by their ID, provided their status is 'DEACTIVE'.

### Swagger/OpenAPI Annotations
- `@Operation`: Describes an individual endpoint's operation in the API documentation.
- `@RequestBody`: Indicates that a method parameter is expected to be bound to the body of the web request.
- The `deactive` method is annotated with `@Operation` to provide a description, summary, and request body example for changing a user's status to 'DEACTIVE'.
- The `findById` method is annotated with `@Operation` to describe the operation of finding a user by their ID.
- The `update` method is annotated with `@Operation` to provide a description, summary, and request body example for updating a user's information.
- The `delete` method is annotated with `@Operation` to describe the operation of deleting a user by their ID.
- The `findAllByDeactive` method is annotated with `@Operation` to provide a description and summary for listing all deactivated users.
- The `findByIdInDeactive` method is annotated with `@Operation` to describe the operation of fetching a specific deactivated user by their ID.

### Usage
- This controller is used to expose HTTP endpoints for creating, retrieving, and updating user data. It utilizes the `UserControllerContract` to perform the necessary actions.
- The `deactive` endpoint is used to deactivate a user's account. It accepts a user ID as a path variable and returns the updated `UserDTO`.
- The `findById` endpoint is used to find a user by their unique identifier. It also accepts a user ID as a path variable and returns the corresponding `UserDTO`.
- The `update` endpoint is used to update a user's account information. It accepts a user ID as a path variable and the `UserUpdateRequest` as the request body.
v
- The `delete` endpoint is used to remove a user from the system. It also accepts a user ID as a path variable.

### Notes
- The `save` method includes a Swagger example object to illustrate the expected request body format.
- The `active` method uses a `@PatchMapping` to partially update the user's status.
- All endpoints return a `ResponseEntity` with a `RestResponse` wrapper to standardize the API responses.
- The `deactive` endpoint provides an example object in the Swagger documentation to illustrate the expected JSON request format.
- The `findById` endpoint does not require a request body, as the user ID is provided in the URL path.
- Both endpoints use `@PathVariable` to bind the method parameter `id` to the URI template variable `{id}`.
- The `update` endpoint provides an example object in the Swagger documentation to illustrate the expected JSON request format for updating a user.
- The `delete` endpoint does not require a request body, as the user ID is provided in the URL path, and the operation is straightforward.
- Both endpoints use `@GetMapping` to handle HTTP GET requests.
- The `findByIdInDeactive` endpoint uses `@PathVariable` to bind the method parameter `id` to the URI template variable `{id}`.
- These endpoints are particularly useful for administrative purposes, such as managing deactivated user accounts.

## Gender Enum

### Overview
The `Gender` enum is used to represent gender categories within the application, with each enum constant having an associated message in Turkish.

### Enum Constants
- `MALE`: Represents the male gender, associated with the message "Erkek".
- `FEMALE`: Represents the female gender, associated with the message "Kadın".

### Fields
- `message`: A private string that stores the message for the gender.

### Constructor
- `Gender(String message)`: A constructor that assigns the provided message to the `message` field.

### Methods
- `getMessage()`: Returns the message associated with the gender.

### Usage
This enum can be utilized wherever gender information is required, providing a way to store and retrieve gender-specific messages.

### Notes
- The messages are provided in Turkish, aligning with the likely user base or localization requirements.
- The `message` field is private, ensuring encapsulation, and can only be accessed through the `getMessage()` method.

## Status Enum

### Overview
The `Status` enum is used to represent the possible states of a user's account within the application.

### Enum Constants
- `ACTIVE`: Indicates that the user's account is active and in good standing.
- `DEACTIVE`: Signifies that the user's account is deactivated and not currently in use.

### Usage
This enum can be used to easily manage and check the status of user accounts throughout the application, ensuring clear and consistent account state management.

### Notes
- The enum provides a straightforward way to handle the binary state of a user's account status.
- It is recommended to use these enum constants when performing checks or updates on user account statuses to maintain code clarity and reliability.