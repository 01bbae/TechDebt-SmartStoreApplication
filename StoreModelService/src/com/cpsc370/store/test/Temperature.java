package src.com.cpsc370.store.test;

import src.com.cpsc370.store.test.util.CommandException;

public interface Temperature {
    public static Temperature valueOf(String level) throws CommandException {
        // [temperature (frozen | refrigerated | ambient | warm | hot )]
        if (level.equals("frozen")) {
            return Frozen.getInstance();
        } else if (level.equals("refrigerated")) {
            return Refrigerated.getInstance();
        } else if (level.equals("ambient")) {
            return Ambient.getInstance();
        } else if (level.equals("warm")) {
            return Warm.getInstance();
        } else if (level.equals("hot")) {
            return Hot.getInstance();
        } else {
            throw new CommandException("Temperature.valueOf",
                    "Temperature does not match 'frozen', 'refrigerated', 'ambient', 'warm', or 'hot'");
        }
    };
}
