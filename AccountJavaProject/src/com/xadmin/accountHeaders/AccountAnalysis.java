package com.xadmin.accountHeaders;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xadmin.tools.AccountTools;

public class AccountAnalysis {

	public static TreeMap<String, String[]> mixedMonthDatabase = new TreeMap<String, String[]>();
	public static HashMap<String, String[]> individualUniquepeople = new HashMap<String, String[]>();
	public static double lastCurrentAmount = 0;
	static TreeMap<String, TreeMap<String, TransactionParameters>> listOfTransactions = new TreeMap<String, TreeMap<String, TransactionParameters>>();
	static ArrayList<String> listWithDate = new ArrayList<String>();

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
		String yearMonthRefrence;
		String[] headers;

		String[] date = new String[3];
		String name = "";
		String[] newHeader = null;
		int count = 0;
		String cleanString = singleMonth.replaceAll("\r", "#").replaceAll("\n", "# ");
		
		// System.out.println(cleanString);
		for (String IndividualLine : tablesToLines) {
			
			// System.out.println(IndividualLine);
			headers = IndividualLine.split(" ");
			
			if (headers.length > 4) {
				String refrenceNum = "";
				
				for (int i = 0; i < headers.length; i++) {

					if (headers[i].matches("../../..")) {
						
						date = headers[i].split("/");
						listWithDate.add(IndividualLine);
						break;
					}

					if (headers[i].matches("../../..") && headers.length == 2) {
						
						name = headers[1];
						System.out.println(name);
						System.out.println("this is the line");
						//newHeader = changeHeader(headers, name);
						break;

					}
					
					if ((   headers[i].matches("^0000\\d{12}") || headers[i].matches("^0000\\d{11}")  )) {
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

						// System.out.println(++count+" "+headers[1]+" "+" "+" "+headers[5]+" ");

					}
				}
			}
		}
		
		count = 0;
		
		for (String string : listWithDate) {
			// System.out.println(++count+" "+string);
		}
		// printOnConsole(mixedMonthDatabase);
		// printOnConsole(individualUniquepeople);
		// printingTranction(mixedMonthDatabase);
		// printingTranction(individualUniquepeople);
		cleanString(singleMonth);
	}

	private static void cleanString(String singleMonth) {

		System.out.println(singleMonth);
		int count = 0;
		Pattern p = Pattern.compile("(\\w.*\\s0000.*\\d\\.\\d{2}##)");
		String cleanString = singleMonth.replaceAll("\r", "#").replaceAll("\n", "# ").replaceAll("Limit : 0.00", "ss");

		String lines = cleanString.replaceAll("## ../../..", "\n");

		System.out.println(lines);
		String[] line = lines.split("\n");
		String filtered = new String();

		for (String string : line) {

			Matcher matches = p.matcher(string);
			if (matches.find()) {
				filtered += (++count + " " + matches.group() + "\n");
			} else {
				filtered += (++count + " " + string + "\n");
			}
		}
		
		System.out.println(filtered);
		filteredStringOperation(filtered);

	}

	private static void filteredStringOperation(String filtered) {

		String[] stringArray = filtered.split("\n");
		int count = 0;

		lastString(stringArray[stringArray.length - 1]);

		for (int i = 0; i < stringArray.length; i++) {

			String[] oneline = stringArray[i].split(" ");
			System.out.println(++count + " " + oneline[oneline.length - 1] + "   " + oneline[oneline.length - 2] + "   "
					+ oneline[oneline.length - 3] + "   " + oneline[oneline.length - 4] + "   " + oneline[0]);
		}

		String listOfWords = (stringArray[stringArray.length - 1]);
		String allButLast = listOfWords.substring(0, listOfWords.lastIndexOf(" "));
		System.out.println(allButLast);
	}

	public static String[] lastString(String lastTransaction) {
		String[] arr = lastTransaction.split(" ");
		String close_Balance = arr[arr.length-1];
		String opening_Balance = arr[arr.length-6];
		String dr_Count = arr[arr.length-5];
		String cr_Count = arr[arr.length-4];
		String debits = arr[arr.length-3];
		String credits = arr[arr.length-2];
		
		System.out.println(close_Balance+" "+debits);
		return null;
		
	}

}
