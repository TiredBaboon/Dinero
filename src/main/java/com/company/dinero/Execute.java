/*
 * @author tyler
 */

package com.company.dinero;

import java.io.*;

public class Execute {
    Execute(String fileRecords) throws
                NullPointerException,
                FileNotFoundException,
                UnsupportedEncodingException,
                Exception
    {
        new StoreData(fileRecords);
    }
}