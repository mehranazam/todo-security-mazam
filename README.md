# TODO Manager w/ Security

## TODO Data Model

    1. text       - String
    2. authorId   - int
    3. isPublic   - boolean
    4. createDate  - LocalDate

## Rules

    1. Guest/Anonymous Role
    2. Author/Standard User Role
    3. Administrator Role

## User Stories

As a \***\*\_\*\***, I should [not] be able to \***\*\_\_\_\*\***.

Preconditions: what must be true for the user story to be relevant.
Postconditions: what must be true after the user story ends.

- [] As any user, I should be able to see all public Todos
- [] As a guest, I should not be able to see any private Todos.
- [] As a guest, I should not be able to create a Todo.
- [] As a guest, I should not be able to remove a Todo.
- [] As a guest, I should not be able to edit a Todo.
- [] As a guest, I should be able to create an account.
- [] As a guest, I should be able to log in to an existing account.
- [] As an Author, I should be able to see my own private Todos.
- [] As an Author, I should not be able to see other Authors' Todos.
- [] As an Author, I should be able to create a Todo.
- [] As an Author, I should be able to remove my own Todos.
- [] As an Author, I should not be able to remove other Authors' Todos.
- [] As an Author, I should be able to edit my own Todo.
- [] As an Author, I should not be able to edit other Authors' Todos.
- [] As an Admin, I should be able to see all Todos.
- [] As an Admin, I should be able to create a Todo.
- [] As an Admin, I should be able to remove all Todos.
- [] As an Admin, I should be able to edit all Todos.
- [] As an Admin, I should be able to promote an Author to Admin.
- [] As an Admin, I should be able to remove Author users.
- [] As an Admin, I should be able to edit the name of Authors.
- [] As an Admin, I should be able to change the password of Authors.

## Tasks

- [x] Create Java API

  - [x] Create Java Project (todo-with-security)
  - [x] Modify pom.xml to include the following dependencies
    - [x] spring-boot-start-security
    - [x] jjwt-api
    - [x] jjwt-impl
    - [x] jjwt-jackson
    - [x] mysql-connector-java
    - [x] spring-boot-starter-jdbc
  - [x] Create models package
    - [x] Create AppUser class
      - [x] Extend from the User (org.springframework.security.core.userdetails)
      - [x] Add Set&lt;String&gt; roles field variable
      - [x] Add int userId field variable
      - [x] Generate getters/setters
      - [x] Generate hashCode/equals
      - [x] Add constructor which takes in username, password, and Set&lt;String&gt; roles
        - [x] call super(username, password, roles.stream().map(r -> new SimpleGrantedAuthority("ROLE\_ " + r).collect(Collectors.toList())))
        - [x] Assign to this.userId
        - [x] Assign to this.roles
    - [x] ToDo class
      - [x] Create String text field variable
      - [x] Create int userId field variable
      - [x] Create boolean isPublic field variable
      - [x] Create LocalDate createDate field variable
      - [x] Generate getters/setters
      - [x] Generate hashCode/equals
  - [] Create data package
    - [x] Create TodoRepo interface
      - [x] List&lt;Todo&gt; findAllPublic()
      - [x] List&lt;Todo&gt; findByUserId(Integer userId)
      - [x] Todo findById(Integer todoId)
      - [x] add(Todo toAdd)
      - [x] boolean remove(Integer todoId)
      - [x] void edit (Todo updated)
    - [x] Create UserRepo interface
      - [x] AppUser findByUsername(String username)
      - [x] AppUser add(AppUser toAdd)
      - [x] boolean remove(Integer userId)
      - [x] void edit(AppUser updated)
    - [x] Create TodoDbRepo class
      - [x] Add @Repository
      - [x] implements TodoRepo -[x] generate functions automatically
    - [x] Create UserDbRepo class
      - [x] Add @Repository
      - [x] implements UserRepo
        - [x] generate functions automatically

- [] Create domain package
  - [x] Create UserService class
    - [x] extends UserDetailService
    - [x] add UserRepo field variable
    - [x] add Password
    - [x] @Override loadUserByUsername (can return AppUser as a UserDetails object)
    - [x] use repo to pass along user
    - [x] add //TODO: validate (later we'll check to make sure username isn't null/empty/etc)
    - [x] if user is not found (we get a null) we throw a new UsernameNotFoundException(username + " not found")
      - [x] otherwise, return user
    - [x] add PasswordEncoder field variable
    - [x] add constructor which takes in UserRepo & PasswordEncoder
    - [x] add AppUser create(String username, String password)
      - [x] for now just return null
  - [x] Create TodoService class
- [x] Create security package
  - [x] Create SecurityConfig class
    - [x] @EnableWebSecurity
    - [x] extends WebSecurityConfigurerAdapter
    - [x] @Override protected void configure(HttpSecurity http) throws Exception
      - [x] http.csrf.disable()
      - [x] http.cors()
      - [x] http.authorizeRequests()
        - [x] .antMatchers("/\*\*").denyAll()
        - [x] .and()
        - [x] .sessionManagement()
          - [x] .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    - [x] public PasswordEncoder getEncoder(){return new BCryptPasswordEncoder();}
      - [x] mark with @Bean
    - [x] @Override protected AuthenticationManager authenticationManager() throws Exception
      - [x] just return super.authenticationManager()
      - [x] mark with @Bean
  - [x] Create JwtConverter class
    - [x] Mark as @Component
    - [x] add a Key field variable (secretKey) assign Keys.secretKeyFor(SignatureAlgorithm.HS256)
    - [x] add public String getToken FromUser(User toConvert)
      - [x] throw new UnsupportedOperationException()
    - [x] add public User getUserFromToken(String token)
      - [x] throw new UnsupportedOperationException()
  - [x] Create JwtRequestFilter class
    - [x] extends BasicAuthenticationFilter
    - [x] add a JwtConverter field
    - [x] Add constructor that takes in JwtConvert and AuthenticationManager
      - [x] super(authManager)
      - [x] store JwtConverter in the field variable
    - [x] @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      - [x] throw new UnsupportedOperationException()
    - [x] In SecurityConfig
      - [x] Create @Autowired JwtRequestFilter field variable (reqFilter)
      - [x] right after .and() call .addFilter()
  - [x] Create controllers package
    - [x] Add AuthController class
      - [x] mark as @RestController
      - [x] add @RequestMapping("/api/security")
      - [x] add AuthenticationManager field variable
      - [x] add JwtConverter field variable
      - [x] add UserService field variable
      - [x] add constructor that takes in all field variables and sets them
      - [x] add ResponseEntity&lt;String&gt; login(@RequestBody Map&lt;String, String&gt; credentials)
        - [x] throw new UnsupportedOperationException()
- [x] Create mysql schemas (test/prod)

  - [] create sql folder in project folder
  - [] create todo-test.sql
  - [] create todo-prod.sql
  - [] drop database if exists todo-X
  - [] create database todo-X
  - [] use todo-X
  - [] create table todos

    - [] todoId int primary key auto_increment
    - [] todoText text not null
    - [] authorId int not null
    - [] isPublic bit(1) not null
    - [] createDate date not null
    - [] constraint fk_todos_users foreign key(suthorId) references users (userId)

  - [x] create table users
    - [x] userId int primary key auto_increment
    - [x] username varchar(300) not null unique
    - [x] password varchar(2048)not null
  - [x] create table roles
    - [x] roleId int primary key auto_increment
    - [x] roleName varchar(20) not null unique
  - [x] create table userroles

    - [x] userId int not null
    - [x] roleId int not null
    - [x] constraint pk_userroles (userId, roleId)
    - [x] constraint fk_users_userroles foreign key (userId) references users (userId)
    - [x] constraint fk_roles_userroles foreign key (roleId) references users (roleId)

  - [x] insert into users (username, password) values ('bob', '$2a$12$K./TSEIs3HAtzpf2xM0h9.SCIEzhBYK0VrhFlX5KNjqCJ4VkmCmj2') pw is password
  - [x] insert into users (username, password) values ('june', '$2a$12$86VIk8ecC1SynL1Q2YRqvOC/5O25GyWUif.oEg4SZ1JuF5lCUOkPS') pw is admin-password
  - [x] insert into roles (roleName) values ('AUTHOR'), ('ADMIN')
  - [x] insert into userroles (userId, roleId) values (1, 1), (2, 2)
  - [x] insert into todos (todoText, authorId, isPublic, createDate) values ('this is a private todo', 1, 0, '2020-04-06'), ('this is a public todo', 2, 1, '2020-04-05')
  - [x] generate reset stored procedure in db
    - [x] delete from userroles
    - [x] delete from users
    - [x] alter table users set auto_increment = 1
    - [x] delete from todos
    - [x] alter table todos set auto_increment = 1
    - [x] delete from roles
    - [x] alter table roles set auto_increment = 1
    - [x] (copy all inserts from prod)

- [] Create React Front-End
