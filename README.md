# WESTERN GOVERNORS UNIVERSITY 
## D287 – JAVA FRAMEWORKS
## Ribbal Hussain Performance Assessment

## C. Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
### mainscreen.html - Lines 14 & 19
```html 
    <title>RigSmiths</title>
    <h1>RigSmiths</h1>
    <h2>Pre-Built Computers</h2> 
```

## D. Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
### About.html
```html
    <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>About Us</title>
  </head>
  <body>
    <p>
      RigSmiths is your one stop shop to building or customizing the perfect rig! Profits are an afterthought, first comes first, getting you a good deal!
    </p>
    <a href="/">Link to Main Screen</a>
  </body>
</html>
```

### Mainscreen.html - Line 89
```html
<a th:href="@{about}">About us... </a>
```

### MainScreenControllerr.java - Lines 56-59
```java
@RequestMapping("/about")
public String about() {
    return "about"; 
}
```

## E. Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database. 
### BootStrapData.java - Lines 72-168
```java
    if(partRepository.count() == 0) {
      InhousePart RAM4GB = new InhousePart();
      RAM4GB.setName("RAM4GB");
            RAM4GB.setPrice(15.99);
            RAM4GB.setInv(10);

      InhousePart RAM8GB = new InhousePart();
      RAM8GB.setName("RAM8GB");
            RAM8GB.setPrice(29.99);
            RAM8GB.setInv(10);

      InhousePart RAM16GB = new InhousePart();
      RAM16GB.setName("RAM16GB");
            RAM16GB.setPrice(39.99);
            RAM16GB.setInv(10);

      InhousePart RAM32GB = new InhousePart();
      RAM32GB.setName("RAM32GB");
            RAM32GB.setPrice(49.99);
            RAM32GB.setInv(10);

      InhousePart RAM64GB = new InhousePart();
      RAM64GB.setName("RAM64GB");
            RAM64GB.setPrice(49.99);
            RAM64GB.setInv(10);


      partRepository.save(RAM4GB);
      partRepository.save(RAM8GB);
      partRepository.save(RAM16GB);
      partRepository.save(RAM32GB);
      partRepository.save(RAM64GB);
    }


    if(productRepository.count() == 0) {
      Product sf400 = new Product("SF400", 199.99, 25);
      Product sf600 = new Product("SF600", 299.99, 25);
      Product sf800 = new Product("SF800", 399.99, 25);
      Product sf1000 = new Product("SF1000", 499.99, 25);
      Product sf2000 = new Product("SF2000", 599.99, 25);

      productRepository.save(sf400);
      productRepository.save(sf600);
      productRepository.save(sf800);
      productRepository.save(sf1000);
      productRepository.save(sf2000);
    } 


    if(outsourcedPartRepository.count() == 0) {
        OutsourcedPart ssd512GB = new OutsourcedPart();
            ssd512GB.setName("ssd512GB");
            ssd512GB.setPrice(39.99);
            ssd512GB.setInv(10);
            ssd512GB.setCompanyName("Samsung");

            OutsourcedPart ssd1TB = new OutsourcedPart();
            ssd1TB.setName("ssd1TB");
            ssd1TB.setPrice(49.99);
            ssd1TB.setInv(10);
            ssd1TB.setCompanyName("Samsung");

            OutsourcedPart ssd2TB = new OutsourcedPart();
            ssd2TB.setName("ssd2TB");
            ssd2TB.setPrice(59.99);
            ssd2TB.setInv(10);
            ssd2TB.setCompanyName("Samsung");

            OutsourcedPart ssd3TB = new OutsourcedPart();
            ssd3TB.setName("ssd3TB");
            ssd3TB.setPrice(69.99);
            ssd3TB.setInv(10);
            ssd3TB.setCompanyName("Samsung");

            OutsourcedPart ssd4TB = new OutsourcedPart();
            ssd4TB.setName("ssd4TB");
            ssd4TB.setPrice(59.99);
            ssd4TB.setInv(10);
            ssd4TB.setCompanyName("Samsung");

            outsourcedPartRepository.save(ssd512GB);
            outsourcedPartRepository.save(ssd1TB);
            outsourcedPartRepository.save(ssd2TB);
            outsourcedPartRepository.save(ssd3TB);
            outsourcedPartRepository.save(ssd4TB);

```

## F. Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
- [x] The “Buy Now” button must be next to the buttons that update and delete products.
- [x] The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
- [x] Display a message that indicates the success or failure of a purchase.

### confirmationbuyproduct.html - created
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Purchase Confirmation</title>
  </head>
  <body>
    <h1>Your Order Has Been Confirmed! We Hope you Enjoy!</h1>
    <a href="/">Link to Main Screen</a>
  </body>
</html>
```

### errorbuyproduct.html - created
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Error purchasing product.</title>
  </head>
  <body>
    <h1>Oops! We ran into an issue processing your order. Please make sure everything looks right in your inventory</h1>
    <a href="/">Link to Main Screen</a>
  </body>
</html>
```

### mainscreen.html - Lines 85-86
```html
<a th:href="@{/buyproduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3"
    onclick="if(!(confirm('Are you sure you want to purchase this product?')))return false">Buy Now</a>
```

### Product.java - Lines 108-115
```java
  public boolean buyProduct() {
    if (this.inv >= 1) {
      this.inv--;

      for (Part part : this.getParts()) {
        if (part.getInv() >= 1) {
          part.setInv(part.getInv() - 1);
        } else {
          return false; 
        }
      }
      return true; 
    } else {
      return false; 
    }
  }
```


### AddProductController.java - Lines 177-190
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
        "Purchase failed: A part’s inventory cannot be lower than its required minimum.");
      return "errorbuyproduct";
    } catch (Exception e) {
      pModel.addAttribute("errorMessage", "An unexpected error occurred while processing the purchase.");
      return "errorbuyproduct";
    }
  }
```

## G. Modify the parts to track maximum and minimum inventory by doing the following:
- [x] Add additional fields to the part entity for maximum and minimum inventory.

### mainscreen.html - Lines 38-39 & Lines 48-49
```html
<th>Minimum</th>
<th>Maximum</th>

<td th:text="${tempPart.minimum}">1</td>
<td th:text="${tempPart.maximum}">1</td>
```

- [x] Modify the sample inventory to include the maximum and minimum fields.

### Part.java - Lines 37-39 & 97-101
```java 
    @Min (value = 0, message = "Minimum inventory must be > 0")
        int minimum;
        int maximum;

        public void setMinimum(int minimum) { this.minimum = minimum; }
        public int getMinimum() { return this.minimum; }
         
        public void setMaximum(int maximum) { this.maximum = maximum; }
        public int getMaximum() { return this.maximum; }
```

- [x] Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.

### InhousePartForm.html - Lines 24 - 35
```html
<p><input type="text" th:field="*{partId}" placeholder="Part ID" class="form-control mb-4 col-4"/></p>

<p><input type="text" th:field="*{minimum}" placeholder="Minimum" class="form-control mb-4 col-4"/></p>

<p><input type="text" th:field="*{maximum}" placeholder="Maximum" class="form-control mb-4 col-4"/></p>

<p><input type="text" th:field="*{partId}" placeholder="Part ID" class="form-control mb-4 col-4"/></p>

<p>
<div th:if="${#fields.hasAnyErrors()}">
    <ul><li th:each="err: ${#fields.allErrors()}" th:text="${err}"></li></ul>
</div>
```

### OutsourcedPartForm.html - Lines 25-29
```html
<p><input type="text" th:field="*{minimum}" placeholder="Minimum" class="form-control mb-4 col-4"/></p>
<p th:if="${#fields.hasErrors('inv')}" th:errors="*{inv}">Inventory Error</p>

<p><input type="text" th:field="*{maximum}" placeholder="Maximum" class="form-control mb-4 col-4"/></p>
<p th:if="${#fields.hasErrors('inv')}" th:errors="*{inv}">Inventory Error</p>

<div th:if="${#fields.hasAnyErrors()}">
    <ul>
        <li th:each="err : ${#fields.allErrors()}" th:text="${err}"></li>
    </ul>
 </div>

```

- [x] Rename the file the persistent storage is saved to.

### application.properties - Line 6
```jproperties 
 spring.datasource.url=jdbc:h2:file:~/src/main/resources/spring-boot-h2-db102
```
- [x] Modify the code to enforce that the inventory is between or at the minimum and maximum value.

### Part.java - Lines 89-95
```java
    public void validateLimits() {
      if (this.inv < this.minimum) {
        this.inv = this.minimum;
      } else if (this.inv > this.maximum ) {
          this.inv = this.maximum;
      }
    }
```

### InhousePartServiceImpl.java & OutSourcedPartServiceImpl.java - Line 52
```java
 thePart.validateLimits();
```



## H. Add validation for between or at the maximum and minimum fields. The validation must include the following:
- [x] Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
- [x] Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
### Part.java - Lines 21-22 
```java
@ValidPartInventory
@ValidPartInventoryMinimum
```

### PartInventoryMinimumValidator.java - created
```java
package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartInventoryMinimumValidator implements ConstraintValidator<ValidPartInventoryMinimum, Part> {
    @Autowired
    private ApplicationContext context;

    public static  ApplicationContext myContext;

    @Override
    public void initialize(ValidPartInventoryMinimum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        return part.getInv() > part.getMinimum();
    }
}
```

### ValidPartInventoryMinimum.java - created
```java
package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PartInventoryMinimumValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPartInventoryMinimum {
    String message() default "Inventory cannot be lower than required minimum";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
```

- [x] Display error messages when adding and updating parts if the inventory is greater than the maximum.
### PartInventoryValidator.java - created

```java
package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartInventoryValidator implements ConstraintValidator<ValidPartInventory, Part> {
    @Autowired
    private ApplicationContext context;

    public static  ApplicationContext myContext;

    @Override
    public void initialize(ValidPartInventory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        return part.getInv() <= part.getMaximum();
    }
}
```

### ValidPartInventory.java - created
```java
package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PartInventoryValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPartInventory {
    String message() default "Inventory cannot exceed maximum value";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
```

## I. Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package. 
### PartTest.java - Lines 159-176
```java
  @Test
    void getMinimum() {
        int minimum=5;
        partIn.setMinimum(minimum);
        assertEquals(minimum, partIn.getMinimum());
        partOut.setMinimum(minimum);
        assertEquals(minimum, partOut.getMinimum());
    }

    @Test
    void getMaximum() {
        int maximum=5;
        partIn.setMaximum(maximum);
        assertEquals(maximum, partIn.getMaximum());
        partOut.setMaximum(maximum);
        assertEquals(maximum, partOut.getMaximum());
    }
```
## J. Remove the class files for any unused validators in order to clean your code.
```
References to each validator:
    ValidDeletePart (DeletePartValidator) - Used in Part.java 

    ValidEnufParts (EnufPartsValidator) - Used in Product.java

    ValidPartInventory (PartInventoryValidator) - Used in Part.java

    ValidPartInventoryMinimum (PartInventoryMinimumValidator) - Used in Part.java

    ValidProductPrice (PriceProductValidator) - Used in Product.java

All 5 validators in the project are being used.
```
