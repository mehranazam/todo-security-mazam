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
  - [x] Create learn package
    - [x] Create App Class
      - [x] @SpringBootApplication
      - [x] main
        - [x] SpringApplication.run(App.class, args)
  - [x] Create application.properties file
    - [x] spring.datasource.url=jdbc:mysql://localhost:3306/todo-prod
    - [x] spring.datasource.username=root
    - [x] spring.datasource.password=top-secret-password
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
      - [x] Create Integer todoId
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
    - [x] Create TodoMapper class
      - [x] implements RowMapper&lt;Todo&gt;
      - [x] Generate Interface method
        - [x] Todo toReturn = new Todo()
        - [x] toReturn.setTodoId(rs.getInt("todoId"))
        - [x] toReturn.setText(rs.getString("todoText"))
        - [x] toReturn.setUserId(rs.getInt("authorId"))
        - [x] toReturn.setPublic(rs.getBoolean("isPublic") != 0)
        - [x] toReturn.setCreateDate(LocalDate.parse(rs.getString("createDate")))
        - []
    - [x] Create TodoDbRepo class
      - [x] Add @Repository
      - [x] implements TodoRepo -[x] generate functions automatically
        - [] implement findAllPublic()
          - [] String sql = "select \* from "
    - [x] Create UserMapper class
      - [x] create Set&lt;String&gt; roles field variable
      - [x] Create UserMapper constructor which takes in the set of roles and sets the field variable
      - [x] implement RowMapper&lt;AppUser&gt;
      - [x] auto-generate methods
        - [x] AppUser toBuild = new AppUser(userId, username, password, roles)
    - [x] Create UserDbRepo class
      - [x] Add @Repository
      - [x] implements UserRepo
        - [x] Add @Autowired JdbcTemplate template field variable
        - [x] generate functions automatically
        - [x] Create private Set&lt;String&gt; findRolesByUsername()
          - [x] String sql = select roleName from users u inner join userroles ur on ur userId = u.userId inner join roles r on r.roleId = ur.roleId where username = ?;
          - [x] return template.query(sql, (rowData, rowNum)->rowData.getString("roleName"), username).stream().collect(Collectors.toSet())
        - [x] implement findByUsername()
          - [x] String sql = "select userId, username, password
                from users where username = ?"
          - [x] return template.query(sql, new UserMapper(findRolesByUsername()username)).stream().findAny().orElse(null)

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
    - [x] mark as @Service
    - [x] add @Autowired TodoRepo repo field variable
    - [x] -- should have autogenerated getPublicTodos method from controller --
      - [x] return repo.findAllPublic();
- [x] Create security package

  - [x] Create SecurityConfig class
    - [x] @EnableWebSecurity
    - [x] extends WebSecurityConfigurerAdapter
    - [x] @Override protected void configure(HttpSecurity http) throws Exception
      - [x] http.csrf.disable()
      - [x] http.cors()
      - [x] http.authorizeRequests()
        - [x] .antMatchers("/api/security/login").permitAll()
        - [x] .antMatchers(HttpMethod.GET, "/api/todo/public").permitAll()
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
    - [x] add public String getTokenFromUser(User toConvert)
      - [x] generate comma separated string of authorities granted to user (retrieve those with .getAuthorities())
      - [x] return Jwts.builder()
        - [x] .setIssuer("todo-app")
        - [x] setSubject(toConvert.getUsername())
        - [x] .claim("authorities", commaSeparatedString)
        - [x] .setExpiration(new Date(System.currentTimeMillis() + 15 x 60 x 1000))
        - [x] .signWithKey(secretKey)
        - [x] .compact();
      - [x] throw new UnsupportedOperationException()
    - [x] add public User getUserFromToken(String token)

      - [] try/catch(JwtException)

        - [] JwtParser parser = Jwts.parseBuilder().requireIssuer("todo-app").setSigningKey(secretKey).build()
        - [] Jws&lt;Claim&gt; claims = parser.parseClaimsJws(token.substring(7))
        - [] String username = claims.getBody().getSubject()
        - [] String authorities = (String)claims.getBody().get("authorities")
        - [] String [] authSplit = authorities.split(",")
        - [] List&lt;GrantedAuthorities&gt; grantedAuthorities = new ArrayList<>()
        - [] for(String auth : authSplit){ grantedAuthorities.add(new GrantedAuthority(auth))
        - [] return new User(username, username, grantedAuthorities)
        - [] catch(JwtException ex)
          - []

        }

      - [x] throw new UnsupportedOperationException()

  - [x] Create JwtRequestFilter class

    - [x] extends BasicAuthenticationFilter
    - [x] add a JwtConverter field
    - [x] Add constructor that takes in JwtConvert and AuthenticationManager
      - [x] super(authManager)
      - [x] store JwtConverter in the field variable
    - [x] @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)

      - [] String authHeader = request.getHeader("Authorization")
      - [] if(authHeader != null && authHeader.startsWith("Bearer ")){
        - [] User converted = converter.getUserFromToken(authHeader)
        - [] if(converted != null){
          - [] UsernamePasswordAuthentication token = new UsernamePasswordAuthenticationToken(converted.getUsername(), null, convertedUser.getAuthorities())
          - [] SecurityContextHolder.getContext().setAuthentication(token)
            }
        - [] else{
          - [] response.setStatus(403)
            }

      }

      - [x] throw new UnsupportedOperationException()

    - [x] In SecurityConfig
      - [x] Create @Autowired JwtRequestFilter field variable (reqFilter)
      - [x] right after .and() call .addFilter()

- [x] Create controllers package

  - [x] Add TodoController class

    - [x] mark as @RestController
    - [x] @RequestMapping("/api/todo")
    - [x] add @Autowired TodoService field variable (service)
    - [x] add a GET endpoint for retrieving all todos
      - [x] List&lt;Todo&gt; pubTodos - service.getPublicTodos()
      - [x] generate TodoService.getPublicTodos()
      - [x] return ResponseEntity.ok(pubTodos)
    - [] add a DELETE endpoint ("/{todoId}")

      - [] public ResponseEntity delete(@PathVariable Integer todoId, Principal user){
        - [] call service.deleteById(todoId, user)
        - [] generate TodoService.deleteById()
        - [] return ResponseEntity.ok()

      }

  - [x] Add AuthController class
    - [x] mark as @RestController
    - [x] add @RequestMapping("/api/security")
    - [x] add AuthenticationManager field variable
    - [x] add JwtConverter field variable
    - [x] add UserService field variable
    - [x] add constructor that takes in all field variables and sets them
    - [x] add ResponseEntity login(@RequestBody Map&lt;String, String&gt; credentials)
      - [x] throw new UnsupportedOperationException()
    - [x] try/catch (AuthenticationException ex) block...
      - [x] Authentication authResult = authManager.authenticate(token);
      - [x] if(authResult.isAuthenticated()){
        - [x] String jwt = converter.getTokenFromUser ((User)authResult.getPrincipal());
        - [x] Map&lt;String, String&gt; tokenMapper = new HashMap<>();
        - [x] tokenWrapper.put("jwt_token", jwt)
        - [x] return new ResponseEntity.ok(tokenWrapper)
              }
    - [x] catch(Authenticated)

- [x] Create mysql schemas (test/prod)

  - [x] create sql folder in project folder
  - [x] create todo-test.sql
  - [x] create todo-prod.sql
  - [x] drop database if exists todo-X
  - [x] create database todo-X
  - [x] use todo-X
  - [x] create table todos

    - [x] todoId int primary key auto_increment
    - [x] todoText text not null
    - [x] authorId int not null
    - [x] isPublic bit(1) not null
    - [x] createDate date not null
    - [x] constraint fk_todos_users foreign key(suthorId) references users (userId)

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
