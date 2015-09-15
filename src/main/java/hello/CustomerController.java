package hello;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;

@Api(value = "service/customer")
@Controller
@RequestMapping("service/customer")
public class CustomerController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(
            value = "find",
            method = RequestMethod.GET,
            produces = "application/json"
            )
            public @ResponseBody
            List<Customer> getCustomer(@RequestParam(value = "lastname") String lastname) {
        return customerRepository.findByLastName(lastname);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
            )
            public @ResponseBody
            Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    @Transactional
    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
            )
            public @ResponseBody
            boolean createCustomer(@RequestBody Customer customer) {
        customer.setId(null);
        em.persist(customer);
        em.flush();
        em.clear();
        Customer persistedCustomer = customerRepository.findOne(customer.getId());
        customerRepository.save(persistedCustomer);
        return true;
    }

    @Transactional
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            produces = "application/json",
            consumes = "application/json"
            )
            public @ResponseBody
            boolean putCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer customer) {
        Customer customerFromDb = customerRepository.findOne(id);
        if (customerFromDb == null) {
            throw new IllegalArgumentException("Customer not found!");
        }
        customerFromDb.setFirstName(customer.getFirstName());
        customerFromDb.setLastName(customer.getLastName());
        customerRepository.save(customerFromDb);
        return true;
    }

}
