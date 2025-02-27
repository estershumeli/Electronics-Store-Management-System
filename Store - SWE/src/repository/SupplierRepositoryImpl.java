package repository;

import start.Application;
import model.Supplier;
import repository.SupplierRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static util.Constant.baseLocation;

public class SupplierRepositoryImpl implements SupplierRepository {
//    Test
    private static final String dataLocation = "Store - SWE/static/data/suppliers.dat";

//    Production
//    private static String dataLocation = baseLocation + "/data/suppliers.dat";
    private static ObjectOutputStream suppliersOutput;

    private static List<Supplier> suppliers;

    static {
        initializeData();
    }

    private static void initializeData() {
        suppliers = tryToInitializeData();
    }

    private static List<Supplier> tryToInitializeData() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            ObjectInputStream suppliersInput = new ObjectInputStream(new FileInputStream(dataLocation));
            suppliers = (ArrayList<Supplier>) suppliersInput.readObject();
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find suppliers data.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            if (! (e instanceof EOFException)) {
            	System.out.println("Failed to read suppliers data.");
                e.printStackTrace();
            }
        }

        return suppliers;
    }

    private static void initializeOutput() {
        try {
            suppliersOutput = new ObjectOutputStream(new FileOutputStream(dataLocation));
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find suppliers data.");
            e.printStackTrace();
        } catch (IOException e) {
            if (! (e instanceof EOFException)) {
            	System.out.println("Failed to read suppliers data.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public Supplier create(Supplier supplier) {
        Supplier created = tryToCreateSupplier(supplier);
        return created;
    }

    private Supplier tryToCreateSupplier(Supplier supplier) {
        List<Supplier> suppliers = findAll();
        initializeOutput();

        try {
            suppliers.add(supplier);
            suppliersOutput.writeObject(suppliers);
            suppliersOutput.flush();
            return supplier;
        } catch (IOException e) {
        	System.out.println("Failed to create supplier.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Supplier findById(String id) {
        Supplier found = null;

        for(Supplier supplier: suppliers) {
            if (supplier.getId().equals(id)) {
                found = supplier;
            }
        }

        return found;
    }


    @Override
    public List<Supplier> findAll() {
        return suppliers;
    }


    @Override
    public Supplier update(Supplier supplier) {
        initializeOutput();
        Integer index = getIndexOfElement(supplier, suppliers);

        Supplier updated = tryToUpdateSupplier(index, supplier, suppliers);
        return updated;
    }

    private static Supplier tryToUpdateSupplier(Integer index, Supplier supplier, List<Supplier> suppliers) {
        try {
            if (index != null) {
                suppliers.set(index, supplier);
                suppliersOutput.writeObject(suppliers);
                suppliersOutput.flush();
                return supplier;
            }
        } catch (IOException e) {
        	System.out.println("Failed to update supplier.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Supplier delete(Supplier supplier) {
        initializeOutput();
        Integer index = getIndexOfElement(supplier, suppliers);

        Supplier deleted = tryToDeleteSupplier(index, suppliers);
        return deleted;
    }

    private static Supplier tryToDeleteSupplier(Integer index, List<Supplier> suppliers) {
        try {
            if (index != null) {
                Supplier supplier = suppliers.remove((int) index);
                suppliersOutput.writeObject(suppliers);
                suppliersOutput.flush();
                return supplier;
            }
        } catch (IOException e) {
        	System.out.println("Failed to delete supplier.");
            e.printStackTrace();
        }

        return null;
    }


    private static Integer getIndexOfElement(Supplier supplier, List<Supplier> suppliers) {
        Integer index = null;

        for (int i = 0; i < suppliers.size(); i++) {
            Supplier currentSupplier = suppliers.get(i);
            if (currentSupplier.getId().equals(supplier.getId())) {
                index = i;
            }
        }

        return index;
    }
}
