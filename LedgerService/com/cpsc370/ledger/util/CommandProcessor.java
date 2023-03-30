package com.cpsc370.ledger.util;

import com.cpsc370.ledger.*;

import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.channels.AcceptPendingException;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CommandProcessor class implementation designed to process individual
 * Blockchain commands
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2023-01-01
 */
public class CommandProcessor {

    private Ledger currentLedger;

    /**
     * Process a single Command
     * 
     * @param command
     * @throws CommandProcessorException
     */
    public void processCommand(String command, File logfile) throws CommandProcessorException {

        // Write code for command processing
        try {
            FileWriter writer = new FileWriter(logfile, true);
            String[] args = command.split(" ");
            if (args[0].equals("create-ledger")) {

                // parse command to new Ledger
                String title = args[1];
                String description;
                String seed;
                int descriptionIndex = -1;
                int seedIndex = -1;
                for (int i = 0; i < args.length; ++i) {
                    if (args[i].equals("description")) {
                        descriptionIndex = i;
                    }
                    if (args[i].equals("seed")) {
                        seedIndex = i;
                    }
                }
                // get description/seed and remove surrounding " "
                description = String.join(" ", Arrays.copyOfRange(args, descriptionIndex + 1,
                        seedIndex));
                description = description.substring(1, description.length() - 1);

                seed = String.join(" ", Arrays.copyOfRange(args, seedIndex + 1, args.length));
                seed = seed.substring(1, seed.length() - 1);

                // Create Ledger
                currentLedger = Ledger.getInstance(title, description, seed);
                writer.write("Ledger " + title + " created.\n");

            } else if (args[0].equals("create-account")) {
                try {

                    // create Account
                    String address = args[1];
                    currentLedger.createAccount(address);
                    writer.write("Account " + address + " created.\n");
                } catch (LedgerException e) {
                    System.out.println(e.getAction() + ": " + e.getReason());
                    writer.write("Account creation failed: " + e.getReason() + "\n");
                }
            } else if (args[0].equals("process-transaction")) {
                try {
                    // process-transaction 1 amount 1000 fee 15 note "fund account" payer master
                    // receiver mary

                    // parse command into new Transaction
                    String transactionId = args[1];
                    int amount = Integer.parseInt(args[3]);
                    int fee = Integer.parseInt(args[5]);
                    String note;
                    Account payer;
                    Account reciever;
                    int noteIndex = -1;
                    int payerIndex = -1;
                    int recieverIndex = -1;

                    for (int i = 0; i < args.length; ++i) {
                        if (args[i].equals("note")) {
                            noteIndex = i;
                        } else if (args[i].equals("payer")) {
                            payerIndex = i;
                        } else if (args[i].equals("receiver")) {
                            recieverIndex = i;
                        }
                    }
                    if (noteIndex < 0 || payerIndex < 0 || recieverIndex < 0) {
                        writer.write("Command Parsing Error: Cannot find note, payer, or reciever in command\n");
                        writer.close();
                        throw new Exception(
                                "Command Parsing Exception: Cannot find note, payer, or reciever in command");
                    }
                    note = String.join(" ", Arrays.copyOfRange(args, noteIndex + 1,
                            payerIndex));
                    String payerAddr = String.join(" ", Arrays.copyOfRange(args, payerIndex + 1,
                            recieverIndex));
                    String recieverAddr = String.join(" ", Arrays.copyOfRange(args, recieverIndex + 1,
                            args.length));

                    payer = currentLedger.getUncommittedBlock().getAccount(payerAddr);
                    reciever = currentLedger.getUncommittedBlock().getAccount(recieverAddr);

                    if (payer == null || reciever == null) {
                        writer.write("Get Account Exception: Cannot find account for transaction\n");
                        writer.close();
                        throw new Exception("Get Account Exception: Cannot find account for transaction");
                    }

                    // process transaction
                    currentLedger
                            .processTransaction(new Transaction(transactionId, amount, fee, note, payer,
                                    reciever));
                    writer.write("transactionID - " + transactionId + " complete\n");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (args[0].equals("get-account-balance")) {
                String address = args[1];
                if (currentLedger.getUncommittedBlock().getAccount(address) == null) {
                    writer.write("Account not Found in getting Account Balance.\n");
                    writer.close();
                    throw new Exception("Account not Found Exception");
                }
                System.out.println(address + " has $" + Integer.toString(currentLedger.getAccountBalance(address)));
                writer.write(address + " has $" + Integer.toString(currentLedger.getAccountBalance(address)) + "\n");
            } else if (args[0].equals("validate")) {
                currentLedger.validate();
                writer.write("Ledger Validated\n");
            } else if (args[0].equals("get-block")) {
                int blockID = Integer.parseInt(args[1]);
                if (currentLedger.getBlock(blockID) == null) {
                    writer.write("Block " + Integer.toString(blockID) + " not found\n");
                    writer.close();
                    throw new Exception("Get Block ID Exception: Block not found");
                }
                System.out.println(currentLedger.getBlock(blockID));
                writer.write("Get block: " + currentLedger.getBlock(blockID).getHash() + "\n");
            } else {
                writer.write("Command not recognized: " + String.join(" ", args) + "\n");
                writer.close();
                // command is comment or not recognized
                throw new Exception("Command not Recognized");
            }
            writer.close();
        } catch (LedgerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Process File from the command line
     * 
     * @param fileName
     */
    public void processCommandFile(String fileName) {

        // Write code for file processing

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            String command = buffer.readLine();

            File log = new File("log.txt");
            if (log.createNewFile()) {
                System.out.println("File created: " + log.getName());
            } else {
                System.out.println("File already exists. Continuing.");
            }

            while (command != null) {
                System.out.println("Command: " + command);
                processCommand(command, log);
                command = buffer.readLine();

            }
            buffer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}