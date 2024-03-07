package rikkei.academy.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import rikkei.academy.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceIMPL implements ICustomerService {
    public static List<Customer> customerList = new ArrayList<>();

    static {
        customerList.add(new Customer(1L, "Yen", "Ha Noi"));
        customerList.add(new Customer(2L, "Ha", "Duc"));
        customerList.add(new Customer(3L, "Nguyen", "Singapore"));
        customerList.add(new Customer(4L, "Chi", "Chau Phi"));
    }

    @Override
    public List<Customer> findALl() {
        return customerList;
    }

    @Override
    public Customer findById(Long id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        if (findById(customer.getId()) != null) {
            customerList.set(customerList.indexOf(findById(customer.getId())), customer);
        } else {
            customerList.add(customer);
        }
    }

    @Override
    public void deleteById(Long id) {
        customerList.remove(findById(id));
    }
}
