package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    //I could use implements ApplicationListener<ContextRefreshedEvent> above.
    //The difference is CommandLineRunner works only with Spring Boot and ApplicationListener<ContextRefreshedEvent> works without Spring Boot.

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Any argument passed through JVM will appear in the 'args' param.

        this.createCategories();
        this.createCustomers();
    }

    private void createCustomers(){
        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Newman");
        this.customerRepository.save(joe);

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Lachappele");
        this.customerRepository.save(michael);

        Customer david = new Customer();
        david.setFirstName("David");
        david.setLastName("Winter");
        this.customerRepository.save(david);

        Customer anne = new Customer();
        anne.setFirstName("Anne");
        anne.setLastName("Hine");
        this.customerRepository.save(anne);

        Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Eastman");
        this.customerRepository.save(alice);

        System.out.println("Customers Loaded = " + this.customerRepository.count() );

    }

    private void createCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = " + categoryRepository.count() );
    }

}
