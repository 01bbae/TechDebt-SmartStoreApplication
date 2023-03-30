package src.com.cpsc370.store.test;

import src.com.cpsc370.store.test.util.CommandException;
import src.com.cpsc370.store.test.util.StoreModelException;
import src.com.cpsc370.store.test.util.LedgerException;
// import src.com.cpsc370.store.test.StoreControllerService;
// import src.com.cpsc370.store.test.StoreModelAPI;
// import src.com.cpsc370.store.test.CommandAPI;
// import src.com.cpsc370.store.test.AisleLocation;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandProcessor implements CommandAPI {

    private final static StoreControllerService controller = StoreControllerService.getInstance();
    private static Ledger ledger = null;
    // private static final StoreModelAPI storeModelService =
    // StoreModelService.getInstance();
    private static final StoreModelAPI storeModelService = StoreModelAPI.getInstance();

    static {
        controller.setLedgerService(ledger);
        controller.setStoreModelService(storeModelService);
    }

    @Override
    public void processCommand(String commandBefore) throws CommandException, StoreModelException, LedgerException {

        List<String> tokens = new ArrayList<>();
        // Split the line into tokens between spaces and quotes
        Matcher matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(commandBefore);
        while (matcher.find())
            tokens.add(matcher.group(1).replace("\"", ""));

        System.out.println(">>> Processing DSL : " + commandBefore);

        String command = commandBefore.trim().replaceAll(" +", " ");

        if (command.toLowerCase().contains("define store")) {
            Store store = storeModelService.provisionStore(tokens.get(2), tokens.get(4), tokens.get(6), null);

        } else if (command.toLowerCase().contains("show store")) {

            System.out.println("<<< " + storeModelService.showStore(tokens.get(2), null));

        } else if (command.toLowerCase().contains("define aisle")) {

            String[] location = tokens.get(2).split(":");
            Aisle aisle = storeModelService.provisionAisle(location[0], location[1], tokens.get(4), tokens.get(6),
                    AisleLocation.valueOf(tokens.get(8)), null);

        } else if (command.toLowerCase().contains("show aisle")) {
            String[] location = tokens.get(2).split(":");

            System.out.println("<<< " + storeModelService.showAisle(location[0], location[1], null));

        } else if (command.toLowerCase().contains("define shelf")) {

            String[] location = tokens.get(2).split(":");
            Shelf shelf = storeModelService.provisionShelf(location[0], location[1],
                    location[2], tokens.get(4),
                    ShelfLevel.valueOf(tokens.get(6)),
                    tokens.get(8), Temperature.valueOf(tokens.get(10)), null);

        } else if (command.toLowerCase().contains("show shelf")) {
            String[] location = tokens.get(2).split(":");

            System.out.println("<<< " + storeModelService.showShelf(location[0],
                    location[1], location[2], null));

        } else if (command.toLowerCase().contains("define product")) {

            Product product = storeModelService.provisionProduct(tokens.get(2),
                    tokens.get(4), tokens.get(6),
                    tokens.get(8), tokens.get(10), Double.parseDouble(tokens.get(12)),
                    Temperature.valueOf(tokens.get(14)), null);

        } else if (command.toLowerCase().contains("show product")) {

            Product product = storeModelService.showProduct(tokens.get(2), null);
            System.out.println("<<< " + product);
        } else if (command.toLowerCase().contains("define inventory")) {

        String[] location = tokens.get(4).split(":");

        Inventory inventory = storeModelService.provisionInventory(tokens.get(2),
        location[0], location[1],
        location[2], Integer.parseInt(tokens.get(6)),
        Integer.parseInt(tokens.get(8)),
        tokens.get(10), null);

        } else if (command.toLowerCase().contains("show inventory")) {

        System.out.println("<<< " + storeModelService.showInventory(tokens.get(2),
        null));

        } else if (command.toLowerCase().contains("update inventory")) {

        Inventory inventory = storeModelService.updateInventory(tokens.get(2),
        Integer.parseInt(tokens.get(4)),
        null);
        System.out.println(inventory);

        } 
        // else if (command.toLowerCase().contains("define customer")) {

        // Customer customer = storeModelService.provisionCustomer(tokens.get(2),
        // tokens.get(4), tokens.get(6),
        // CustomerType.valueOf(tokens.get(8)), tokens.get(10), tokens.get(12), null);

        // } else if (command.toLowerCase().contains("update customer")) {

        // String[] location = tokens.get(4).split(":");
        // Customer customer = storeModelService.updateCustomer(tokens.get(2),
        // location[0], location[1], null);

        // System.out.println("<<< " + customer);
        // } else if (command.toLowerCase().contains("show customer")) {

        // System.out.println(storeModelService.showCustomer(tokens.get(2), null));
        // } else if (command.toLowerCase().contains("define basket")) {

        // Basket basket = storeModelService.provisionBasket(tokens.get(2), null);

        // } else if (command.toLowerCase().contains("assign basket")) {

        // Basket basket = storeModelService.assignCustomerBasket(tokens.get(4),
        // tokens.get(2), null);

        // } else if (command.toLowerCase().contains("get_customer_basket")) {

        // Basket basket = storeModelService.getCustomerBasket(tokens.get(1), null);
        // System.out.println("<<< " + basket);
        // } else if (command.toLowerCase().contains("add_basket_item")) {

        // Basket basket = storeModelService.addBasketProduct(tokens.get(1),
        // tokens.get(3),
        // Integer.parseInt(tokens.get(5)), null);
        // System.out.println("<<< " + basket);
        // } else if (command.toLowerCase().contains("remove_basket_item")) {

        // Basket basket = storeModelService.removeBasketProduct(tokens.get(1),
        // tokens.get(3),
        // Integer.parseInt(tokens.get(5)), null);
        // System.out.println(basket);
        // } else if (command.toLowerCase().contains("clear_basket")) {

        // Basket basket = storeModelService.clearBasket(tokens.get(1), null);
        // System.out.println("<<< " + basket);
        // } else if (command.toLowerCase().contains("show basket_items")) {

        // Basket basket = storeModelService.showBasket(tokens.get(2), null);
        // System.out.println("<<< " + basket);
        // } else if (command.toLowerCase().contains("define device")) {

        // String[] location = tokens.get(8).split(":");
        // Device device = storeModelService.provisionDevice(tokens.get(2),
        // tokens.get(4),
        // tokens.get(6), location[0], location[1], null);

        // device.addObserver(controller);

        // } else if (command.toLowerCase().contains("show device")) {
        // System.out.println("<<< " + storeModelService.showDevice(tokens.get(2),
        // null));
        // } else if (command.toLowerCase().contains("create event")) {
        // storeModelService.raiseEvent(tokens.get(2), tokens.get(4) + " " +
        // tokens.get(5), null);
        // } else if (command.toLowerCase().contains("create_event")) {

        // if (tokens.size() == 6)
        // storeModelService.raiseEvent(tokens.get(1), tokens.get(3) + " " +
        // tokens.get(4) + " " + tokens.get(5),
        // null);
        // else
        // storeModelService.raiseEvent(tokens.get(1), tokens.get(3) + " " +
        // tokens.get(4), null);

        // } else if (command.toLowerCase().contains("create command")) {
        // storeModelService.issueCommand(tokens.get(2), tokens.get(4) + " " +
        // tokens.get(5), null);
        // } else if (command.toLowerCase().contains("create-ledger")) {
        // if (tokens.size() != 6)
        // throw new CommandException("create-ledger", "Missing Arguments");

        // System.out.println("Creating Ledger: " + tokens.get(1) + " " + tokens.get(3)
        // + " " + tokens.get(5));
        // ledger = Ledger.getInstance(tokens.get(1), tokens.get(3), tokens.get(5));

        // // Assign created ledger
        // controller.setLedgerService(ledger);

        // } else if (command.toLowerCase().contains("create-account")) {
        // if (tokens.size() != 2)
        // throw new CommandException("create-account", "Missing Arguments");

        // System.out.println("Creating Account: " + tokens.get(1));
        // try {
        // ledger.createAccount(tokens.get(1));
        // } catch (LedgerException e) {
        // System.out.println("Failed due to: " + e.getReason());
        // }
        // } else if (command.toLowerCase().contains("get-account-balance")) {
        // if (tokens.size() != 2)
        // throw new CommandException("create-account", "Missing Arguments");

        // System.out.println("Getting Balance for: " + tokens.get(1));
        // try {
        // System.out.println("Account Balance for: " + tokens.get(1) + " is "
        // + ledger.getAccountBalance(tokens.get(1)));

        // } catch (LedgerException e) {
        // System.out.println("Failed due to: " + e.getReason());
        // }
        // } else if (command.toLowerCase().contains("get-account-balances")) {
        // System.out.println("Getting All Balances");

        // Map<String, Integer> map = ledger.getAccountBalances();

        // if (map == null) {
        // System.out.println("No Account Has Been Committed");
        // throw new LedgerException("Get Account Balances",
        // "No Account Has Been Committed");
        // }

        // Set<String> keys = new HashSet<>(map.keySet());

        // for (String key : keys) {
        // System.out.println("Account Balance for: " + key + " is " + map.get(key));
        // }
        // } else if (command.toLowerCase().contains("process-transaction")) {

        // if (tokens.size() != 12)
        // throw new CommandException("process-transaction", "Missing Arguments");

        // System.out.println("Processing Transaction: " + tokens.get(1) + " "
        // + tokens.get(3) + " " + tokens.get(5) + " " + tokens.get(7) + " "
        // + tokens.get(9) + " " + tokens.get(11) + " ");

        // Block block = ledger.getUncommittedBlock();

        // Account payer = block.getAccount(tokens.get(9));
        // Account receiver = block.getAccount(tokens.get(11));

        // if (payer == null || receiver == null) {
        // throw new CommandException("process-transaction", "Account Does Not Exist");
        // }

        // Transaction tempTransaction = new Transaction(tokens.get(1),
        // Integer.parseInt(tokens.get(3)),
        // Integer.parseInt(tokens.get(5)), tokens.get(7), payer, receiver);
        // try {
        // ledger.processTransaction(tempTransaction);
        // } catch (LedgerException e) {
        // System.out.println("Failed due to: " + e.getReason());
        // }
        // } else if (command.toLowerCase().contains("get-block")) {

        // if (tokens.size() != 2)
        // throw new CommandException("get-block", "Missing Arguments");

        // System.out.println("Get Block: " + tokens.get(1));
        // Block block = ledger.getBlock(Integer.parseInt(tokens.get(1)));

        // System.out.println("Block Number: " + block.getBlockNumber() + " "
        // + "Hash: " + block.getHash() + " " + "Previous Hash: " +
        // block.getPreviousHash());

        // for (Transaction transaction : block.getTransactionList()) {
        // System.out.println(transaction.toString());
        // }

        // } else if (command.toLowerCase().contains("get-transaction")) {

        // if (tokens.size() != 2)
        // throw new CommandException("get-transaction", "Missing Arguments");

        // System.out.println("Get Transaction: " + tokens.get(1));
        // Transaction transaction = ledger.getTransaction(tokens.get(1));

        // System.out.println("Transaction ID: " + transaction.getTransactionId() + " "
        // + "Amount: " + transaction.getAmount() + " " + "Fee: "
        // + transaction.getFee() + " " + "Note: " + transaction.getNote() + " " +
        // "Payer: "
        // + transaction.getPayer().getAddress() + " " + "Receiver: "
        // + transaction.getReceiver().getAddress());
        // } else if (command.toLowerCase().contains("validate")) {
        // System.out.print("Validate: ");
        // try {
        // ledger.validate();
        // System.out.println("Valid");
        // } catch (LedgerException e) {
        // System.out.println("Failed due to: " + e.getReason());
        // }

        // } else {
        // throw new CommandException(tokens.get(0), "Invalid Command");
        // }
    }

    @Override
    public void processCommandFile(String fileName) {
        Path path = FileSystems.getDefault().getPath(Path.of(fileName).toAbsolutePath().toString());
        List<String> tokens = new ArrayList<>();

        // Process all the lines in the file
        try (var stream = Files.lines(path)) {
            tokens = stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> iterator = tokens.iterator();

        // Filter out any empty lines and lines that start with #
        for (int i = 0; iterator.hasNext(); i++) {
            String temp = iterator.next();
            if (!temp.trim().startsWith("#") && temp.trim().length() != 0) {
                try {
                    processCommand(temp);
                } catch (CommandException e) {
                    e.setLineNumber(i + 1);
                    System.out.println(
                            "\u001B[31m" + "Failed due to: " + e.getReason() + " for Command: " + e.getCommand()
                                    + " On Line Number: " + e.getLineNumber() + "\u001B[0m");
                } catch (StoreModelException e) {
                    System.out.println("\u001B[31m" + "Failed due to: " + e.getReason() + " for Command: "
                            + e.getAction() + "\u001B[0m");
                } catch (LedgerException e) {
                    System.out.println("\u001B[31m" + "Failed due to: " + e.getReason() + " for Command: "
                            + e.getAction() + "\u001B[0m");
                }
            }
        }

    }
}