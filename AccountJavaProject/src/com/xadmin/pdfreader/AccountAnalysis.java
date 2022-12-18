package com.xadmin.pdfreader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.xadmin.tools.AccountTools;

public class AccountAnalysis {
    
	public static TreeMap<String, String[]> mixedMonthDatabase= new TreeMap<String, String[]>();
	public static HashMap<String, String[]> individualUniquepeople= new HashMap<String, String[]>();
	public static double lastCurrentAmount = 0;
	static TreeMap<String, TreeMap<String, TransactionParameters>> listOfTransactions = new TreeMap<String, TreeMap<String, TransactionParameters>>();

	public static void main(String[] args) throws IOException {

		try {

			List<String> monthlyStatementString = new AccountTools().filesFromFolder();

			separatingAccount(monthlyStatementString);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void separatingAccount(List<String> monthlyStatementString) {

		// single month statement to a single transaction
		for (String singleMonth : monthlyStatementString) {
			transactionEntry(singleMonth);
		}
	}

	private static void transactionEntry(String singleMonth) {

		String[] tablesToLines = singleMonth.split("\n");
		String yearMonthRefrence ;
		String[] headers;
		
		String[] date = new String[3];
		String name="";
		String [] newHeader = null ;
		for (String IndividualLine : tablesToLines) {
			
			    headers = IndividualLine.split(" ");		
				String refrenceNum = "";
				for (int i = 0; i < headers .length; i++) {

					if (headers[i].matches("../../..")) {
						date = headers[i].split("/");
					}
					
					if(headers[i].matches("../../..") && headers.length ==2) {
						name = headers[1];
						System.out.println(name);
						newHeader = changeHeader(headers,name);
						
					}
					if ((headers[i].matches("^0000\\d{12}"))) {
//						if (date.length == 0) {
//							yearMonthRefrence = date[2] + "-" + date[1] + "-" + date[0] + "_" + refrenceNum;
//							mixedMonthDatabase.put(yearMonthRefrence, newHeader);
//
//						}
						refrenceNum = headers[i];
						headers[1] += headers[2];
						yearMonthRefrence = date[2] + "-" + date[1] + "-" + date[0] + "_" + refrenceNum;
						mixedMonthDatabase.put(yearMonthRefrence, headers);
						individualUniquepeople.put(headers[1], headers);
						break;
					}
				}
			}
		printOnConsole(mixedMonthDatabase);
		printOnConsole(individualUniquepeople);
		printingTranction(mixedMonthDatabase);
		printingTranction(individualUniquepeople);

	}
	
	private static void printingTranction(HashMap<String, String[]> _individualUniquepeople) {
		try {

			FileWriter myWriter = new FileWriter("C:\\Programming\\java\\InidividualTransaction.txt");

			for (Entry<String, String[]> entry : _individualUniquepeople.entrySet()) {
				myWriter.write(entry.getKey() + " " + entry.getValue()[1] + " " + "\n");
			}

			myWriter.close(); // closing try block

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static String[] changeHeader(String[] _headers, String name) {
		     String arr[] = new String[_headers.length+1];
		     arr = _headers;
		     arr[arr.length-1] = name;
		return arr;
	}

	public static void printOnConsole(TreeMap<String, String[]> _monthlyDatabase ) {
		int count=0;
		System.out.println("printOnConsole");
		for (Entry<String, String[]> entry : _monthlyDatabase.entrySet()) {
			System.out.println(++count+" "+entry.getKey()+" "+entry.getValue()[1]);
		}
	}
	
	public static void printOnConsole(HashMap<String, String[]> _monthlyDatabase ) {
		int count=0;
		System.out.println("printOnConsole");
		for (Entry<String, String[]> entry : _monthlyDatabase.entrySet()) {
			System.out.println(++count+" "+entry.getKey()+" "+entry.getValue()[1]);
		}
	}
	

	public static void printingTranction(TreeMap<String, String[]> _mixedMonthDatabase) {

		try {

			FileWriter myWriter = new FileWriter("C:\\Programming\\java\\accountStatement.txt");

			for (Entry<String, String[]> entry : _mixedMonthDatabase.entrySet()) {
				myWriter.write(entry.getKey() + " " + entry.getValue()[1] + " " + "\n");
			}

			myWriter.close(); // closing try block

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
