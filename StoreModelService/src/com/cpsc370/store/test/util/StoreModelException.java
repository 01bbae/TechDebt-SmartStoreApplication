package src.com.cpsc370.store.test.util;

/**
 * StoreModelException class implementation designed display errors for
 * StoreModel
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2023-01-01
 */
public class StoreModelException extends Exception {

    private String command;
    private String reason;
    private String action;
    private int lineNumber;

    /**
     * Exception Constructor
     * 
     * @param command
     * @param reason
     * @param action
     */
    public StoreModelException(String command, String reason, String action) {
        this.command = command;
        this.reason = reason;
        this.action = action;
    }

    /**
     * Getter method for command
     * 
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * Getter method for action
     * 
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter method for command
     * 
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * Getter method for line number
     * 
     * @return
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Setter method for command
     * 
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Setter method for reason
     * 
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Setter method for line number
     * 
     * @param lineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Setter method for action
     * 
     * @param action
     */
    public void setLineNumber(String action) {
        this.action = action;
    }
}
