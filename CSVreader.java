import java.io.*;

import java.io.IOException;

public class CSVreader {

	BufferedReader input = null;
    BufferedWriter output = null;
    int columns = 0;
    int rows = 0;
    
    String fileName = null;
    String line = null;
    String data[][] = null;
    
    /*
     * Constructor
     * Takes a filename and opens that file.  First it does a column and row count, discarding top row, which will be headers.
     * It will then call the processCSV method, which will pull data from the csv into a multidimensional string array.
     * 
     * @param	filename	absolute filepath to csv file
     */
    public CSVreader(String filename) throws IOException
    {
    	try {
        	input = new BufferedReader(new FileReader(filename));

            while ((line = input.readLine()) != null) {
            	if (columns == 0)
            	{
            		columns = itemCount(line.toString());
            	}
            	rows++;
            }
        } 
    	catch (IOException e)
    	{
    		System.out.println(e.toString());
    	}
    	finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    	rows--; //subtract row of column headers
    	processCSV(filename);
    }
    
    /*
     * Accessor method that returns number of columns in the csv.
     * 
     * @return number of columns
     */
    public int getColumns()
    {
    	return columns;
    }
    
    /*
     * Accessor method that returns number of rows in the csv.
     * 
     * @return number of rows
     */
    public int getRows()
    {
    	return rows;
    }
    
    /*
     * Accessor method that returns data that was in the csv.
     * 
     * @return multidimensional String array of data that was in the csv.
     */
    public String[][] getData()
    {
    	return data;
    }
    
    /*
     * Method that opens csv file, reads it line by line and breaks up the row by each comma separated column.
     * It then writes each piece to a multidimensional String array that can be used in a program.
     * 
     * @param absolute path to csv file.
     */
    public void processCSV(String filename) throws IOException
    {
    	data = new String[rows][columns];
    	String line;
    	String[] temp;
    	
    	try {
    		input = new BufferedReader(new FileReader(filename));
    		line = input.readLine(); //don't use first line, which is column headers
    		for (int i = 0; i < rows; i++)
        	{
    			line = input.readLine();
    			temp = line.split(",");
        		for (int j = 0; j < columns; j++)
        		{
        			data[i][j] = temp[j];
        		}
        	}
    	}
    	finally {
            if (input != null) {
                input.close();
            }
    	}
    }
    
	
	/*
     * Method that counts the number of columns in the csv.
     * 
     * @param	s	String of text separated by commas that denote a column.
     * @return		number of columns
     */
	private int itemCount(String s)
	{
		String[] temp = s.split(",");
		return temp.length;
	}

}
