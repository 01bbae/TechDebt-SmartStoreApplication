package com.cpsc370.ledger.test;

import javax.swing.plaf.synth.SynthStyle;

import com.cpsc370.ledger.util.CommandProcessor;

/**
 * Test Driver Class for testing Blockchain
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2023-01-01
 */
public class TestDriver {
    public static void main(String[] args) {

        CommandProcessor processor = new CommandProcessor();
        processor.processCommandFile(args[0]);
    }
}
