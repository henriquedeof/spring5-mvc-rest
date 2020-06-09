package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.v1.CategoryController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return this.vendorRepository.findById(id)
                .map(this.vendorMapper::vendorToVendorDTO)
                .map(customerDTO -> {
                    //set API URL
                    customerDTO.setVendorUrl(this.getVendorUrl(id));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return createOrUpdateVendor(this.vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    /*
        Auxiliary method that saves and update a Vendor.
     */
    private VendorDTO createOrUpdateVendor(Vendor vendor) {
        Vendor savedVendor = this.vendorRepository.save(vendor);
        VendorDTO vendorDTOConverted = this.vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTOConverted.setVendorUrl(this.getVendorUrl(savedVendor.getId()));

        return vendorDTOConverted;
    }

    @Override //Method that comes from PUT (Http method)
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = this.vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return this.createOrUpdateVendor(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return this.vendorRepository.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnDto = this.vendorMapper.vendorToVendorDTO(this.vendorRepository.save(vendor));
            returnDto.setVendorUrl(this.getVendorUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        this.vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return CategoryController.BASE_URL + "/" + id;
    }

}
