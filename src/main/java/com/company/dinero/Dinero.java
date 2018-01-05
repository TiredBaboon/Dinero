/*
 * @author tyler
 */

package com.company.dinero;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dinero {
    private static final Logger LOGGER = Logger.getLogger(Dinero.class.getName());
    
    public static void main(String[] args) {
        String fileRecords = null;
        
        // Parse command line arguments.
        // fileRecords - Full path to CSV file containing transaction records.
        try {
            fileRecords = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(1);
        }

        try {
            new Execute(fileRecords);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(1);
        }
        
        System.exit(0);
    }
}