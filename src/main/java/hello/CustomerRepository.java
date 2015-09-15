package hello;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByLastName(String lastName);

    Customer findByLastNameAndFirstName(String lastName, String firstName);


}
