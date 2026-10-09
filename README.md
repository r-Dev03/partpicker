# PartPicker

**Inventory manager for a PC parts store, built with Spring Boot and Thymeleaf.**

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![H2](https://img.shields.io/badge/H2-Database-blue.svg)](https://www.h2database.com/)

RigSmiths, a fictional PC builder, tracks individual parts (like RAM modules) and the prebuilt systems made from them. Every part has minimum and maximum stock levels, and buying a system pulls one of each linked part out of inventory.

**Built on:** a Spring Boot + Thymeleaf inventory starter template that provided the parts and products CRUD screens and the domain model. Added in this repository: min/max inventory limits with custom validators, the Buy Now purchase flow, inline form errors for product edits that would break part minimums, duplicate-safe sample data, getter/setter tests for the new fields, and the RigSmiths store branding.

## Highlights

- Min/max inventory limits enforced by custom class-level validators
- A purchase flow that checks every linked part before deducting any stock, so a failed purchase changes nothing
- Product edits that would push a linked part below its minimum are rejected with an inline form error instead of crashing at save time

## How It Works

### Inventory limits
Two custom constraints sit on the `Part` entity and compare its fields against each other:

```java
@ValidPartInventory          // inv <= maximum
@ValidPartInventoryMinimum   // inv >= minimum
public abstract class Part implements Serializable { ... }
```

Spring validates both when a part form is submitted, so an out-of-range part shows an error on the form.

### Editing a product
Raising a product's inventory pulls the same amount of stock from each linked part. Those parts are saved directly, so once the minimums were added, a part dropping below its minimum failed validation at save time and crashed the request. The controller now checks every linked part first and rejects the edit with an inline error on the inventory field:

```java
for (Part p : existingProduct.getParts()) {
    if (p.getInv() - invDiff < p.getMinimum()) {
        bindingResult.rejectValue("inv", null,
            "Cannot decrease part inventory: part '" + p.getName() + "' would go below minimum.");
        ...
        return "productForm";
    }
}
```

Only after every part passes does it deduct the stock and save.

### Buying a product
`Product.buyProduct()` checks everything before changing anything:

```
Buy Now clicked
      │
      ▼
Product stock > 1? ── no ──────────────────────────┐
      │ yes                                        │
      ▼                                            │
Every linked part above its minimum? ── no ────────┤
      │ yes                                        │
      ▼                                            ▼
Deduct 1 from the product                Reject: error page,
Deduct 1 from each linked part           no inventory changed
      │
      ▼
Save → confirmation page
```


```java
public boolean buyProduct() {
    if (this.inv <= 1) return false;
    for (Part part : this.getParts()) {
        if (part.getInv() <= part.getMinimum()) return false;
    }
    this.inv--;
    for (Part part : this.getParts()) part.setInv(part.getInv() - 1);
    return true;
}
```

If the product is nearly out of stock, or any linked part is already at its minimum, the purchase is rejected and no inventory moves. Otherwise the product and each of its parts drop by one, and the user sees a confirmation page.

## Getting Started

**Prerequisites:** Java 17

```bash
git clone https://github.com/r-Dev03/partpicker.git
cd partpicker
./mvnw spring-boot:run
```

Open `http://localhost:8080`. On first run, the app loads 5 RAM modules and 5 prebuilt systems (SF400 to SF2000). Each set only loads when its table is empty, so restarts don't create duplicates.

Data is stored in an H2 file database at `~/partpicker`. To inspect it, open `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:file:~/partpicker`, user `sa`, and a blank password.

Run the tests with `./mvnw test`.

## Tech Stack

Java 17 · Spring Boot · Spring MVC · Spring Data JPA / Hibernate · Bean Validation · Thymeleaf · Bootstrap 5 (main screen) · H2 · Maven

## Known Limitations

- **Single-unit purchases.** Buy Now sells one system at a time.
- **No order history.** Purchases change inventory counts but aren't recorded.
- **No authentication.** Anyone with the URL can edit inventory.
