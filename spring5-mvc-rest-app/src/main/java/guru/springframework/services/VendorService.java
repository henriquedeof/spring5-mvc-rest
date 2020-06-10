package guru.springframework.services;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;

public interface VendorService {

    public VendorListDTO getAllVendors();

    public VendorDTO getVendorById(Long id);

    public VendorDTO createNewVendor(VendorDTO vendorDTO);

    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    public void deleteVendorById(Long id);
}
