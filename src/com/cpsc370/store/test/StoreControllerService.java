package src.com.cpsc370.store.test;

public class StoreControllerService {
    // Code snippet modified from Geeks for Geeks
    // Static variable reference of single_instance
    // of type Singleton
    private static StoreControllerService instance = null;
    private Ledger ledger;
    private StoreModelAPI storeModelService;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private StoreControllerService() {

    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized StoreControllerService getInstance() {
        if (instance == null)
            instance = new StoreControllerService();

        return instance;
    }

    public void setLedgerService(Ledger ledger) {
        this.ledger = ledger;
    }

    public void setStoreModelService(StoreModelAPI storeModelService) {
        this.storeModelService = storeModelService;
    }

}
