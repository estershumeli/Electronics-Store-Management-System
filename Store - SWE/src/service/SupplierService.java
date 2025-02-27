package service;

import model.Supplier;
import repository.SupplierRepository;
import repository.SupplierRepositoryImpl;

import java.util.List;

public class SupplierService {
    private static final SupplierRepository supplierRepository;

    static {
        supplierRepository = new SupplierRepositoryImpl();
    }

    public List<Supplier> getAll() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    public Supplier delete(Supplier supplier) {
        return supplierRepository.delete(supplier);
    }
}
