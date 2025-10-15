package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */


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
            RAM64GB.setPrice(59.99);
            RAM64GB.setInv(10);


      partRepository.save(RAM4GB);
      partRepository.save(RAM8GB);
      partRepository.save(RAM16GB);
      partRepository.save(RAM32GB);
      partRepository.save(RAM64GB);
    }


    if(productRepository.count() == 0) {
      Product intelI3 = new Product("Intel-i3", 199.99, 25);
      Product intelI5 = new Product("Intel-i5", 299.99, 25);
      Product intelI7 = new Product("Intel-i7", 399.99, 25);
      Product intelI9 = new Product("Intel-i9", 499.99, 25);
      Product intelI12 = new Product("Intel-i12", 599.99, 25);

      productRepository.save(intelI3);
      productRepository.save(intelI5);
      productRepository.save(intelI7);
      productRepository.save(intelI9);
      productRepository.save(intelI12);
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

    }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
