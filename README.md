# PartPicker

**Spring Boot Inventory Management System for Computer Parts Retail**

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0-green.svg)](https://www.thymeleaf.org/)
[![H2 Database](https://img.shields.io/badge/H2-Database-blue.svg)](https://www.h2database.com/)

## Overview

PartPicker is a web-based inventory management system designed for computer parts retailers. Built with Spring Boot and Thymeleaf, it manages products (complete PC builds) composed of individual parts (CPUs, GPUs, RAM, etc.) with inventory tracking, validation, and purchasing functionality.

**Core Features:**
- Product and parts inventory management
- Min/max inventory level enforcement with validation
- Direct purchase workflow with inventory validation
- Sample data initialization
- Customized UI for computer parts retail
- H2 database with web console

This project demonstrates Spring Boot MVC architecture, Thymeleaf templating, JPA entity relationships, and form validation.

## Business Context

Computer parts retailers need to track both complete systems (gaming PCs, workstations) and individual components (processors, graphics cards, memory modules). PartPicker provides:

- **Products:** Complete PC builds or systems
- **Parts:** Individual components that make up products
- **Inventory Control:** Min/max thresholds to prevent stockouts and overstocking
- **Purchase Validation:** Ensures inventory levels are maintained during sales

## Tech Stack

**Backend:**
- Java 17
- Spring Boot 2.7
- Spring MVC
- Spring Data JPA
- Hibernate ORM

**Frontend:**
- Thymeleaf (server-side templating)
- Bootstrap 4
- HTML5/CSS3

**Database:**
- H2 Database (file-based persistence)
- H2 Console (web-based database viewer)

**Build Tool:**
- Maven

**Development:**
- IntelliJ IDEA Ultimate Edition

## Architecture

### MVC Pattern
```
┌─────────────────────────────────┐
│      Browser (Client)           │
└────────────┬────────────────────┘
             │ HTTP
┌────────────▼────────────────────┐
│    Controllers (MVC)            │  ← Handle requests
│  - MainScreenController         │
│  - AddInhousePartController     │
│  - AddProductController         │
└────────────┬────────────────────┘
             │
┌────────────▼────────────────────┐
│    Services (Business)          │  ← Business logic
│  - PartService                  │
│  - ProductService               │
└────────────┬────────────────────┘
             │
┌────────────▼────────────────────┐
│    Repositories (Data)          │  ← Data access
│  - PartRepository               │
│  - ProductRepository            │
└────────────┬────────────────────┘
             │ JPA
┌────────────▼────────────────────┐
│       H2 Database               │
└─────────────────────────────────┘
```

### Data Model

**Part (Abstract)**
- `InhousePart`: Parts manufactured in-house
- `OutsourcedPart`: Parts from external suppliers
- Fields: ID, name, price, inventory, min, max

**Product**
- Complete PC builds/systems
- Fields: ID, name, price, inventory
- Many-to-many relationship with Parts

## Installation

### Prerequisites
```bash
# Java 17 or higher
java -version
```

### Setup
```bash
# Clone repository
git clone https://github.com/r-Dev03/partpicker.git
cd partpicker

# Build with Maven
mvn clean install

# Run the application
mvn spring-boot:run
```

Access the application at: `http://localhost:8080`

## Database Configuration

PartPicker uses H2, a lightweight Java database that stores data in a file.

**Current Configuration** (`src/main/resources/application.properties`):
```properties
# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Database (file-based persistence)
spring.datasource.url=jdbc:h2:file:~/partpicker
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Accessing H2 Console

View and query the database directly through the web console:

1. Start the application: `mvn spring-boot:run`
2. Navigate to: `http://localhost:8080/h2-console`
3. Enter connection details:
   - **JDBC URL:** `jdbc:h2:file:~/partpicker`
   - **Username:** `sa`
   - **Password:** (leave blank)
4. Click "Connect"

**Tip:** You can query tables directly:
```sql
SELECT * FROM part;
SELECT * FROM product;
```

**Note:** The database file will be created as `partpicker.mv.db` in your home directory.

## Usage

### Main Inventory Screen

The main screen displays:
- **Parts List:** Computer parts inventory
- **Products List:** Complete PC builds
- Actions: Add, Update, Delete for both parts and products

### Adding Parts

**In-House Parts** (manufactured internally):
1. Click "Add In-House Part"
2. Enter: Name, Price, Inventory, Min, Max, Part ID
3. Submit

**Outsourced Parts** (from suppliers):
1. Click "Add Outsourced Part"
2. Enter: Name, Price, Inventory, Min, Max, Company Name
3. Submit

**Validation:**
- Inventory must be between Min and Max
- Price must be positive
- All fields required

### Adding Products

1. Click "Add Product"
2. Enter product details (e.g., "Gaming PC RTX 4090")
3. Select associated parts from available inventory
4. Submit

### Purchasing Products

1. Click "Buy Now" next to any product
2. System validates:
   - Product inventory > 0
   - Part inventory levels meet minimum thresholds
3. Displays confirmation or error page with specific error messages

### About Page

Navigate to "About" to view company information and navigation back to main screen.

## Project Structure
```
partpicker/
├── mvnw                         # Maven wrapper (Unix)
├── mvnw.cmd                     # Maven wrapper (Windows)
├── pom.xml                      # Maven dependencies
├── flake.nix                    # Nix development environment
├── flake.lock                   # Nix lock file
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/example/demo/
    │   │       ├── bootstrap/
    │   │       │   └── BootStrapData.java       # Sample data loader
    │   │       ├── controllers/
    │   │       │   ├── MainScreenController.java
    │   │       │   ├── AddInhousePartController.java
    │   │       │   ├── AddOutsourcedPartController.java
    │   │       │   ├── AddProductController.java
    │   │       │   └── BuyProductController.java
    │   │       ├── domain/
    │   │       │   ├── Part.java
    │   │       │   ├── InhousePart.java
    │   │       │   ├── OutsourcedPart.java
    │   │       │   └── Product.java
    │   │       ├── repositories/
    │   │       │   ├── PartRepository.java
    │   │       │   └── ProductRepository.java
    │   │       ├── service/
    │   │       │   ├── PartService.java
    │   │       │   ├── PartServiceImpl.java
    │   │       │   ├── ProductService.java
    │   │       │   └── ProductServiceImpl.java
    │   │       └── validators/
    │   │           └── ValidInventory.java      # Min/max validation
    │   └── resources/
    │       ├── templates/                        # Thymeleaf templates
    │       │   ├── mainscreen.html
    │       │   ├── about.html
    │       │   ├── InhousePartForm.html
    │       │   ├── OutsourcedPartForm.html
    │       │   ├── productForm.html
    │       │   ├── confirmationbuyproduct.html
    │       │   └── errorbuyproduct.html
    │       └── application.properties
    └── test/
        └── java/
            └── com/example/demo/
                └── domain/
                    └── PartTest.java             # Unit tests
```

## Key Features Explained

### Min/Max Inventory Validation

Custom validator enforces inventory constraints:
```java
@ValidInventory  // Custom annotation
public class Part {
    private int inv;      // Current inventory
    private int minInv;   // Minimum threshold
    private int maxInv;   // Maximum threshold
}
```

**Validation Rules:**
- `inv >= minInv`: Prevents stockouts
- `inv <= maxInv`: Prevents overstocking
- Enforced on create and update operations

**Error Messages:**
- "Inventory cannot be less than minimum"
- "Inventory cannot exceed maximum"
- "Minimum cannot be greater than maximum"

### Buy Now Functionality
```java
@GetMapping("/buyproduct")
public String buyProduct(@RequestParam("productID") int pId, Model pModel) {
    ProductService productService = context.getBean(ProductServiceImpl.class);
    Product product2 = productService.findById(pId);
    try {
        boolean purchaseConfirmation = product2.buyProduct();
        if (purchaseConfirmation) {
            productService.save(product2);
            return "confirmationbuyproduct";
        }
        pModel.addAttribute("errorMessage",
            "Purchase failed: One or more parts do not have enough inventory to complete the order.");
        return "errorbuyproduct";
    } catch (javax.validation.ConstraintViolationException e) {
        pModel.addAttribute("errorMessage", 
            "Purchase failed: A part's inventory cannot be lower than its required minimum.");
        return "errorbuyproduct";
    } catch (Exception e) {
        pModel.addAttribute("errorMessage", "An unexpected error occurred while processing the purchase.");
        return "errorbuyproduct";
    }
}
```

**Purchase Logic:**
- Calls `buyProduct()` method on Product entity
- Validates inventory constraints before completing purchase
- Returns confirmation page on success
- Returns error page with specific error messages on failure
- Handles validation exceptions for inventory minimum thresholds

### Sample Data Loading
```java
@Component
public class BootStrapData implements CommandLineRunner {
    @Override
    public void run(String... args) {
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            // Load sample parts and products
        }
    }
}
```

**Sample Data:**
- 5 RAM modules (4GB, 8GB, 16GB, 32GB, 64GB)
- 1 pre-built computer system
- Only loads when database is empty to prevent duplicates

## Testing

The project includes unit tests for the Part entity covering basic getter/setter functionality, object equality, and toString implementations.

**Run tests:**
```bash
mvn test
```

## Development Workflow

### Option 1: Standard Maven
```bash
mvn spring-boot:run
```

### Option 2: Nix Development Environment
```bash
nix develop
mvn spring-boot:run
```

The Nix environment provides consistent development dependencies across different machines.

## Limitations

- No user authentication (single-user system)
- No shopping cart (single-item purchases only)
- Parts inventory not decremented on product sale (simplified model)
- No supplier management beyond company name
- No reporting or analytics
- File-based H2 database (not production-ready for multi-user scenarios)
- Products don't track which specific part instances they use
- No multi-quantity purchases
- No order history
- No customer management

## License

MIT License - see LICENSE file for details
