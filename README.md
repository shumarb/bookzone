# BookZone
## Overview
BookZone is a Book Management System catered to members of the SG Book Collectors club (members with email addresses that 
end with `@sgbookcollectors.com`), implemented using the MVC (Model-View-Controller) architectural pattern.

The application features `adding`, `editing`, and `deleting` a book.

## Prerequisites
- Java 17.
- Git.
- IntelliJ IDEA.
- MySQL, and MySQL Workbench.

## Setup
1. `Clone` this repository.
2. Open the `IntelliJ IDE`.
3. Select `Open` and navigate to the location of the cloned repository.
4. Create a `schema` on the MySQL Workbench.
5. Update `application.properties` in the `src/main/resources` folder:
    - Line 5: Set `spring.datasource.url=jdbc:mysql://localhost:3306/name-of-your-schema`.
    - Line 6: Set `spring.datasource.username=name-of-your-mysql-username`.
    - Line 7: Set `spring.datasource.username=name-of-your-mysql-password`.
6. Run `git pull origin main` to ensure you have the latest version of the repository.

## Instructions
1. Right-click the `BookzoneApplication` class file (Location: `src/main/java/com/example/WorkPortal/BookzoneApplication.java`).
2. Select `Run 'BookzoneApplic....main()`.
3. If there are issues with running the application, perform the following before repeating Step 1:
    - Click the `Maven icon` on the right.
    - Navigate to `bookzone/Lifecycle`.
    - Click `Clean`, then `Install`.
    - Drop all tables in the schema.
    - Close and reopen IntelliJ IDEA.
4. Enter `localhost:9001/` in your browser after seeing the message `Started BookzoneApplication` in the `console`.
5. Explore the application.
6. Explore data in theMySQL Workbench:
   - Expand the schema to view `Books (denoted as books)`, `Librarians (denoted as librarians)`, and `Persons (denoted as persons)` tables.
   - `Books` table: Contains `id`, `author`, `category`, `title`, `year`.
   - `Person` table: Contains `id`, `name`, `email`, and `password`.
   - `Library` table: Contains `id`.

## Tech Stack
- Programming: Java.
- Frameworks/Tools: Spring Boot, JUnit, Mockito, Bootstrap, Git, HTML, MySQL.
