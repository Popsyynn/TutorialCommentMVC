# TutorialCommentMVC
Implemented a microservice architecture for managing tutorials and comments in a Spring Boot backend environment. The project utilized a many-to-one relationship between comments and tutorials, allowing users to post comments on specific tutorials.
Tutorial and Comment Microservice Backend Architecture
Description: Implemented a microservice architecture for managing tutorials and comments in a Spring Boot backend environment. The project utilized a many-to-one relationship between comments and tutorials, allowing users to post comments on specific tutorials.
Key Contributions:
Designed and defined data models for tutorials and comments, establishing the relationship between the entities.
Implemented JPA repositories to perform CRUD operations on the tutorial and comment entities.
Established a connection to a MySQL database to persist tutorial and comment data.
Created REST controllers to handle HTTP requests for creating, retrieving, updating, and deleting tutorials and comments.
Implemented endpoints for retrieving comments for a specific tutorial based on the tutorial ID.
Ensured proper exception handling by throwing custom exceptions, such as "TutorialNotFoundException," when tutorial IDs were not found.
Utilized Spring's ResponseEntity to return appropriate HTTP status codes and responses.
Technologies and Tools:
Spring Boot, Spring Data JPA, MySQL
