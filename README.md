# BookZone
## Overview
BookZone is a Book Management System catered to members of the SG Book Collectors club (members with email addresses that 
end with `@sgbookcollectors.com`), implemented using the MVC (Model-View-Controller) architectural pattern.

The application features `adding`, `editing`, and `deleting` a book.

## Tech Stack
- Programming:
    - Java.
- Frameworks & Tools:
    - Spring Boot.
    - Thymeleaf.
    - Lombok.
    - MySQL.
    - JUnit, Mockito.
    - Git.
    - HTML, CSS.
  
## Prerequisites
- Java 17.
- Git.
- IntelliJ IDEA.
- MySQL, MySQL Workbench.

## Setup
1. `Clone` this repository.
2. Open the `IntelliJ IDE`.
3. Select `Open` and navigate to the location of the cloned repository.
4. Create a `schema` on the MySQL Workbench.
5. Update `application.properties` in the `src/main/resources` folder:
    - Line 5: Set `spring.datasource.url=jdbc:mysql://localhost:3306/name-of-your-schema`.
    - Line 6: Set `spring.datasource.username=name-of-your-mysql-username`.
    - Line 7: Set `spring.datasource.username=name-of-your-mysql-password`.

## Instructions
1. Open the `terminal` of your local machine.
2. Navigate to the location of the cloned repository.
3. Run `git pull origin main` to ensure you have the latest version of the repository.
4. Right-click the `BookzoneApplication` class file (Location: `src/main/java/com/example/WorkPortal/BookzoneApplication.java`).
5. Select `Run 'BookzoneApplic....main()`.
6. If there are issues with running the application, perform the following before repeating Step 1:
    - Click the `Maven icon` on the right.
    - Navigate to `bookzone/Lifecycle`.
    - Click `Clean`, then `Install`.
    - Drop all tables in the schema.
    - Close and reopen IntelliJ IDEA.
7. Enter `localhost:9001/` in your browser after seeing the message `Started BookzoneApplication` in the `console`.
8. Explore the application and examine data in the MySQL Workbench.
