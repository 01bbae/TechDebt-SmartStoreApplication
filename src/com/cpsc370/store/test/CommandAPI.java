package src.com.cpsc370.store.test;

import src.com.cpsc370.store.test.util.CommandException;
import src.com.cpsc370.store.test.util.StoreModelException;
import src.com.cpsc370.store.test.util.LedgerException;

public interface CommandAPI {

    public void processCommand(String commandBefore) throws CommandException, StoreModelException, LedgerException;

    public void processCommandFile(String fileName);

}
