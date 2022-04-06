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
  - [] Modify pom.xml to include the following dependencies
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
    - [x] ToDo class
      - [x] Create String text field variable
      - [x] Create int userId field variable
      - [x] Create boolean isPublic field variable
      - [x] Create LocalDate createDate field variable
      - [x] Generate getters/setters
      - [x] Generate hashCode/equals

- [] Create mysql schemas (test/prod)
- [] Create React Front-End
