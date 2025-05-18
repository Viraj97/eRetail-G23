package Services;

import Models.Admin;
import Models.Customer;
import Models.Seller;

import java.util.List;

public class AuthenticationService {
    private final Admin admin;
    private final List<Customer> customers;
    private final List<Seller> sellers;

    public AuthenticationService(Admin admin, List<Customer> customers, List<Seller> sellers) {
        this.admin = admin;
        this.customers = customers;
        this.sellers = sellers;
    }

public boolean loginAdmin(String username, String password) {
    return admin.getUsername().equals(username) && admin.getPassword().equals(password);
}


    public Customer loginCustomer(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        for (Customer c : customers) {
            if (email.equals(c.getEmail()) && password.equals(c.getPassword())) {
                return c;
            }
        }
        return null;
    }

    public Seller loginSeller(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        for (Seller s : sellers) {
            if (email.equals(s.getEmail()) && password.equals(s.getPassword())) {
                return s;
            }
        }
        return null;
    }
}
