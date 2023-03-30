package src.com.cpsc370.store.test;

import src.com.cpsc370.store.test.util.CommandException;

public interface ShelfLevel {
    public static ShelfLevel valueOf(String level) throws CommandException {
        // level (high | medium | low)
        if (level.equals("high")) {
            return High.getInstance();
        } else if (level.equals("medium")) {
            return Medium.getInstance();
        } else if (level.equals("low")) {
            return Low.getInstance();
        } else {
            throw new CommandException("ShelfLevel.valueOf", "ShelfLevel does not match 'high', 'medium', or 'low'");
        }
    };
}
