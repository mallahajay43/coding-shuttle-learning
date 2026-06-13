# Internal Working of Spring Security and Its Components

When a user sends a request to a Spring Boot application, the request first passes through the **Security Filter Chain**, which is the core component of Spring Security. The Security Filter Chain consists of multiple filters, each responsible for handling a specific security concern, such as:

- CSRF protection (`CsrfFilter`)
- Authentication (`UsernamePasswordAuthenticationFilter`, `BearerTokenAuthenticationFilter`, etc.)
- Authorization
- Session management
- Exception handling
- Security context management

One of the important stages in this chain is authorization. Through configurations such as `authorizeHttpRequests()`, Spring Security determines which endpoints are publicly accessible and which require authentication.

If the requested endpoint requires authentication, the corresponding authentication filter extracts the user's credentials (such as username/password or JWT token) and creates an `Authentication` object. This object is then passed to the **AuthenticationManager**.

The `AuthenticationManager` delegates the authentication process to one of its configured **AuthenticationProvider** implementations, such as:

- `DaoAuthenticationProvider` for username/password authentication
- OAuth2 authentication providers
- JWT-based authentication providers
- LDAP authentication providers

For username/password authentication, `DaoAuthenticationProvider` uses the **UserDetailsService** to load user information from the database and compares the provided password with the stored password using a **PasswordEncoder**.

If authentication is successful, a fully authenticated `Authentication` object is returned and stored in the **SecurityContext**, which is managed by the **SecurityContextHolder**. This allows Spring Security to identify the currently authenticated user throughout the request lifecycle.

The request then continues through the remaining filters. Finally, authorization checks are performed to verify whether the authenticated user has the required roles or permissions to access the requested resource.

- If the user is authenticated and authorized, the request reaches the controller.
- If authentication fails, Spring Security returns **401 Unauthorized**.
- If authentication succeeds but the user lacks the required permissions, Spring Security returns **403 Forbidden**.

## Request Flow

```text
Client Request
       |
       v
Security Filter Chain
       |
       v
Authentication Filter
       |
       v
AuthenticationManager
       |
       v
AuthenticationProvider
       |
       v
UserDetailsService
       |
       v
Database
       |
       v
Authentication Success
       |
       v
SecurityContextHolder
       |
       v
Authorization Check
       |
       +----> 403 Forbidden (Insufficient Permissions)
       |
       v
Controller
       |
       v
Response
```

## Key Components

| Component | Responsibility |
|------------|---------------|
| SecurityFilterChain | Intercepts and secures incoming requests |
| Authentication Filter | Extracts credentials or tokens from requests |
| AuthenticationManager | Coordinates the authentication process |
| AuthenticationProvider | Validates user credentials |
| UserDetailsService | Loads user information from the data source |
| UserDetails | Represents authenticated user data |
| PasswordEncoder | Verifies encrypted passwords |
| SecurityContext | Stores authentication information |
| SecurityContextHolder | Provides access to the SecurityContext |
| Authorization Filter | Performs role and permission checks |

