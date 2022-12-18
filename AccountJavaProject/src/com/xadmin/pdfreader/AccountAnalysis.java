package com.xadmin.pdfreader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.xadmin.tools.AccountTools;

public class AccountAnalysis {
    
	public static TreeMap<String, String[]> mixedMonthDatabase= new TreeMap<String, String[]>();
	public static double lastCurrentAmount = 0;
	static TreeMap<String, TreeMap<String, TransactionParameters>> listOfTransactions = new TreeMap<String, TreeMap<String, TransactionParameters>>();

	public static void main(String[] args) throws IOException {

		try {

			List<String> monthlyStatementString = new AccountTools().filesFromFolder();
			// System.out.println(monthlyStatementString);

			separatingAccount(monthlyStatementString);
			printingTranction();

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
		for (String IndividualLine : tablesToLines) {
			
			    headers = IndividualLine.split(" ");		
				String refrenceNum = "";
				
				for (int i = 0; i < headers .length; i++) {

					if (headers[i].matches("../../..")) {
						date = headers[i].split("/");
					}
					if ((headers[i].matches("^0000\\d{12}"))) {

						refrenceNum = headers[i];
						headers[1]+=headers[2];
						yearMonthRefrence = date[2] + "-" + date[1] + "-" + date[0] + "_" + refrenceNum;
						mixedMonthDatabase.put(yearMonthRefrence, headers);

						break;
					}
				}
			}
		printOnConsole(mixedMonthDatabase);

	}
	
	public static void forLongName() {
		  
	}

	public static void printOnConsole(TreeMap<String, String[]> _monthlyDatabase ) {
		int count=0;
		System.out.println("sdf");
		for (Entry<String, String[]> entry : _monthlyDatabase.entrySet()) {
			System.out.println(++count+" "+entry.getKey()+" "+entry.getValue()[1]);
		}
	}
	
	

	public static void printingTranction() {

		try {

			FileWriter myWriter = new FileWriter("C:\\Users\\vijay\\OneDrive\\Desktop\\filename2.txt");
			myWriter.write("Files in Java adsfasdfasf\n\n");

			// System.out.println(listOfTransactions);

			for (Map.Entry<String, TreeMap<String, TransactionParameters>> month : listOfTransactions.entrySet()) {
				// System.out.println(month.getKey()+" "+month.getValue());

				for (Map.Entry<String, TransactionParameters> daily : month.getValue().entrySet()) {
//						System.out.print(daily.getKey()+" "+daily.getValue().getRefrence()+"    debit =  "+daily.getValue().getDebited()+"  credit = "+daily.getValue().getCredited());
//					
					System.out.printf("%s %s %20s %20s", daily.getKey(), daily.getValue().getRefrence(),
							daily.getValue().getDebited(), daily.getValue().getCredited());
					System.out.println();
					myWriter.write(daily.getKey() + " ------ " + daily.getValue().getRefrence() + " ======== "
							+ daily.getValue().getDebited() + " -------- " + daily.getValue().getCredited() + "\n");

				}
			}

			myWriter.close(); // closing try block

		} catch (IOException e) {
			e.printStackTrace();
		}
		// closing try block

	}

}
