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
│      Services (Business)        │  ← Business logic
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
git clone https://github.com/yourusername/partpicker.git
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
spring.datasource.url=jdbc:h2:file:~/rigsmith-computer-012546575
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
   - **JDBC URL:** `jdbc:h2:file:~/rigsmith-computer-012546575`
   - **Username:** `sa`
   - **Password:** (leave blank)
4. Click "Connect"

**Tip:** You can query tables directly:
```sql
SELECT * FROM part;
SELECT * FROM product;
```

### Customizing Database Name

For a cleaner portfolio presentation, consider renaming the database file:

**Change in `application.properties`:**
```properties
# From:
spring.datasource.url=jdbc:h2:file:~/rigsmith-computer-012546575

# To:
spring.datasource.url=jdbc:h2:file:~/partpicker
```

This will create a new database file called `partpicker.mv.db` in your home directory.

## Usage

### Main Inventory Screen

The main screen displays:
- **Parts List:** All CPUs, GPUs, RAM, storage, etc.
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
   - Decrements product inventory by 1
   - Does NOT affect associated parts inventory
3. Displays success/failure message

### About Page

Navigate to "About" to view company information and navigation back to main screen.

## Sample Inventory

The application auto-loads sample data on first run:

**Parts:**
- Intel Core i9-13900K (CPU) - In-House
- NVIDIA RTX 4090 (GPU) - Outsourced
- Corsair Vengeance 32GB DDR5 (RAM) - Outsourced
- Samsung 980 Pro 2TB NVMe (Storage) - In-House
- NZXT H510 Case (Case) - Outsourced

**Products:**
- Ultimate Gaming Rig
- Content Creator Workstation
- Budget Gaming Build
- Office Productivity PC
- Developer Workstation

**Note:** Sample data only loads when both parts and products tables are empty (prevents duplicates on restart).

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
    │       │   └── productForm.html
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
@GetMapping("/buyProduct")
public String buyProduct(@RequestParam("productID") int id, Model model) {
    Product product = productService.findById(id);
    
    if (product.getInv() > 0) {
        product.setInv(product.getInv() - 1);
        productService.save(product);
        model.addAttribute("message", "Purchase successful!");
    } else {
        model.addAttribute("error", "Product out of stock");
    }
    
    return "mainscreen";
}
```

**Purchase Logic:**
- Decrements product inventory only
- Associated parts inventory unchanged (parts are reusable across products)
- Returns user to main screen with status message

### Sample Data Loading
```java
@Component
public class BootStrapData implements CommandLineRunner {
    @Override
    public void run(String... args) {
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            // Load 5 sample parts
            // Load 5 sample products
        }
    }
}
```

**Conditional Loading:**
- Only runs when database is empty
- Prevents duplicate data on application restart
- Uses H2 file-based persistence

## Testing

### Unit Tests

Located in `src/test/java/com/example/demo/domain/PartTest.java`:
```java
@Test
public void testMinInventoryValidation() {
    Part part = new InhousePart();
    part.setInv(5);
    part.setMinInv(10);
    part.setMaxInv(100);
    
    // Should fail validation (inv < minInv)
    assertFalse(validatePart(part));
}

@Test
public void testMaxInventoryValidation() {
    Part part = new InhousePart();
    part.setInv(150);
    part.setMinInv(10);
    part.setMaxInv(100);
    
    // Should fail validation (inv > maxInv)
    assertFalse(validatePart(part));
}
```

**Run tests:**
```bash
mvn test
```

## Customizations Made

This project was customized from a generic template:

1. **Branding:** Changed shop name to "PartPicker - PC Components & Systems"
2. **Product Names:** Gaming PCs, workstations, etc. (computer-specific)
3. **Part Names:** CPUs, GPUs, RAM, storage, cases
4. **About Page:** Added company description and navigation
5. **Sample Data:** Computer parts inventory (5 parts, 5 products)
6. **Buy Button:** Added purchase functionality to product list
7. **Min/Max Fields:** Extended Part entity with inventory constraints
8. **Validation:** Custom validator for min/max inventory enforcement
9. **Unit Tests:** Added tests for inventory validation
10. **Code Cleanup:** Removed unused validator classes

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

**Current Constraints:**
- No user authentication (single-user system)
- No shopping cart (single-item purchases only)
- Parts inventory not decremented on product sale (simplified model)
- No supplier management beyond company name
- No reporting or analytics
- File-based H2 database (not production-ready for multi-user scenarios)

**Design Simplifications:**
- Products don't track which specific part instances they use
- No multi-quantity purchases
- No order history
- No customer management
- Basic error handling only

## Future Enhancements

**Core Features:**
- Multi-item shopping cart
- User authentication and roles (admin, customer)
- Order history and tracking
- Customer accounts
- Parts inventory deduction on product sales

**Inventory Management:**
- Automatic reorder notifications at min threshold
- Supplier integration
- Price history tracking
- Batch import/export (CSV)

**Business Features:**
- Sales analytics dashboard
- Revenue tracking
- Popular products/parts reports
- Low stock alerts (email notifications)

**Technical Improvements:**
- PostgreSQL/MySQL for production
- REST API layer
- Frontend framework (React/Vue) instead of Thymeleaf
- Comprehensive unit and integration tests
- Docker containerization
- CI/CD pipeline

## Common Issues

**Issue: Sample data loads on every restart**
- Solution: Sample data is conditional - only loads when DB is empty. If you want fresh data, delete the H2 database file (`~/rigsmith-computer-012546575.mv.db`)

**Issue: Validation errors not displaying**
- Solution: Check `@Valid` annotation on controller method parameters and `th:errors` in Thymeleaf templates

**Issue: Buy Now button doesn't decrement inventory**
- Solution: Verify `productRepository.save()` is called after inventory update

**Issue: H2 Console won't connect**
- Solution: Verify JDBC URL matches `application.properties`: `jdbc:h2:file:~/rigsmith-computer-012546575`

**Issue: Port 8080 already in use**
- Solution: Kill the process using port 8080 or run on different port:
```bash
  mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

## License

MIT License - see LICENSE file for details

---

*A Spring Boot MVC application demonstrating inventory management, form validation, and Thymeleaf templating for computer parts retail.*
