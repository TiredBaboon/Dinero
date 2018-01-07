/*
 * @author tyler
 */

package com.company.dinero;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoreData {
    private static final Pattern REGEX_DATE = Pattern.compile("^(\\d{2})/(\\d{2})/(\\d{4})$");
    
    StoreData (String fileRecords) throws
                NullPointerException,
                FileNotFoundException,
                UnsupportedEncodingException,
                Exception,
                SQLException,
                ClassNotFoundException
    {
        Reader fr = null;
        File fileTemplate = new File(fileRecords);
        FileInputStream fis = new FileInputStream(fileTemplate);
        fr = new InputStreamReader(fis, "UTF-8");
        
        List<String> values = null;
        values = CSVHelper.parseLine(fr);
        
        //HashMap<Integer, Double> recordData = new HashMap<Integer, Double>();
        
        //int countDays = 0;
        //double expenditures = 0;
        //double income = 0;
        //double sum = 0;
        double charge;
        
        SQLite db = new SQLite("recordsDB", "records");

        while (values != null && !values.isEmpty()) {
            //collection.add( MyClass.constructFromStrings(values) );
            //System.out.println(values.get(6));

            //charge = Double.parseDouble(values.get(6));

            /*
            if (charge > 0) {
                income += charge;
            } else {
                expenditures += Math.abs(charge);
            }

            if (countDays == 30) {
                //System.out.println("Income: " + income);
                //System.out.println("Expenditures: " + expenditures);
                if (income == 0) {
                    System.out.println("X");
                } else {
                    System.out.println("Expense/Income: " + expenditures/income);
                }
                countDays = 0;
                income = 0;
                expenditures = 0;
            }
            */

            db.insert(values.get(2), values.get(4), Double.parseDouble(values.get(6)));
            
            //Matcher match = REGEX_DATE.matcher(values.get(2));
            //charge = Double.parseDouble(values.get(6));

            //if (match.find()) {
                //recordData.put(Integer.parseInt(match.group(3)), charge);
                //recordData.put(Integer.parseInt(match.group(1)), charge);
                //recordData.put(Integer.parseInt(match.group(1)), charge);
            //}
            
            //countDays++;

            //System.out.println(charge);
            //sum += charge;
            //System.out.println(sum);
            
            values = CSVHelper.parseLine(fr);
        }
        //lnr.close();
        fr.close();
        
        db.selectAll();
        
        /*
        for (Integer name: recordData.keySet()){
            String key = name.toString();
            String value = recordData.get(name).toString();  
            System.out.println(key + " > " + value);
        }
        */
    }
}