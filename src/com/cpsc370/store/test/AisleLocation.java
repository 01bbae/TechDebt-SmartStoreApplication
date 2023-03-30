package src.com.cpsc370.store.test;

import src.com.cpsc370.store.test.util.CommandException;

public interface AisleLocation {

    public static AisleLocation valueOf(String location) throws CommandException {
        if (location.equals("floor")) {
            return Floor.getInstance();
        } else if (location.equals("store_room")) {
            return StoreRoom.getInstance();
        } else {
            throw new CommandException("AisleLocation.valueOf", "Location does not match 'floor' or 'store_room'");
        }
    };

}
