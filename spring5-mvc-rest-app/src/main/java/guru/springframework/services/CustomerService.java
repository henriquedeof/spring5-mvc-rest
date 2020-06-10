package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO getCustomerById(Long id);

    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    public void deleteCustomerById(Long id);
}
