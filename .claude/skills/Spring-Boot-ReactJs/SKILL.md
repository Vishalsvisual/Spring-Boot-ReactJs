```markdown
# Spring-Boot-ReactJs Development Patterns

> Auto-generated skill from repository analysis

## Overview
This skill teaches best practices and common workflows for developing and maintaining the `Spring-Boot-ReactJs` Java backend codebase. It covers file organization, coding conventions, exception handling, and service layer patterns, as well as how to extend the application safely and consistently.

## Coding Conventions

### File Naming
- **Java Classes:** Use PascalCase (e.g., `EmployeeService.java`, `ResourceNotFoundException.java`)
- **Test Files:** Suffix with `.test.` (e.g., `EmployeeService.test.java`)

### Import Style
- Use **relative imports** within the Java package structure.
  ```java
  import emp.management.service.EmployeeService;
  ```

### Export Style
- Use **named exports** (Java's standard `public class`).
  ```java
  public class EmployeeService { ... }
  ```

### Commit Patterns
- Freeform commit messages, often describing the change directly.
  ```
  Add new exception handler for employee not found
  ```

## Workflows

### Add or Update Service Layer with Exception Handling
**Trigger:** When implementing or modifying a service and ensuring proper exception handling is in place.  
**Command:** `/add-service-with-exception`

1. **Edit or create service implementation file**  
   - Location: `react-springboot-api/src/main/java/emp/management/service/impl/`
   - Example:  
     ```java
     public class EmployeeServiceImpl implements EmployeeService {
         // Service methods
     }
     ```
2. **Edit or create exception classes**  
   - Location: `react-springboot-api/src/main/java/emp/management/exception/`
   - Example:  
     ```java
     public class ResourceNotFoundException extends RuntimeException {
         public ResourceNotFoundException(String message) {
             super(message);
         }
     }
     ```
3. **Edit or update `GlobalExceptionHandler.java`**  
   - Add or update methods to handle new or updated exceptions.
   - Example:  
     ```java
     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
         ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
     }
     ```
4. **Optionally update utility classes**  
   - Location: `react-springboot-api/src/main/java/emp/management/utils/MapperUtil.java`
   - Update or add mapping utilities as needed.

## Testing Patterns

- **Test Framework:** Unknown (not detected)
- **Test File Pattern:** Files matching `*.test.*`
- **Example:**  
  ```java
  // EmployeeService.test.java
  public class EmployeeServiceTest {
      // Test methods here
  }
  ```

## Commands

| Command                     | Purpose                                                          |
|-----------------------------|------------------------------------------------------------------|
| /add-service-with-exception | Add or update a service implementation with exception handling   |
```
