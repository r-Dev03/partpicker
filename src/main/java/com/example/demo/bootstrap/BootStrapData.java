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


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
