package com.xadmin.pdfreader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.xadmin.tools.AccountTools;

public class AccountAnalysis {

	public static double lastCurrentAmount=0;
	static TreeMap<String, TreeMap<String, TransactionParameters>> listOfTransactions = new TreeMap<String, TreeMap<String, TransactionParameters>>();
	
	public static void main(String[] args) throws IOException {
		FileWriter myWriter= new FileWriter("C:\\Users\\vijrs\\Desktop\\filename2.txt");
		try {
			
			
			List<String> monthlyStatementString = new AccountTools().filesFromFolder();
			// System.out.println(monthlyStatementString);

			separatingAccount(monthlyStatementString);
			printingTranction();

		} catch (IOException e) {
			e.printStackTrace();
		}
		myWriter.close();
	}

	public static void separatingAccount(List<String> monthlyStatementString) {

		// single month statement to a single transaction
		for (String singleMonth : monthlyStatementString) {
			transactionEntry(singleMonth);
		}
	}

	private static void transactionEntry(String singleMonth) {

		String[] individualTrancsaction = singleMonth.split("\n");
		String yearMonthRefrence = "";
		boolean flag = true;
		
		TreeMap<String, TransactionParameters> dailyTransactionSet = new TreeMap<String, TransactionParameters>();

		// In a single statement to headers like credit, Debits
		for (int i = 0; i < individualTrancsaction.length; i++) {
			
			String[] headers = individualTrancsaction[i].split(" ");
			if (headers.length > 4 && headers[headers.length - 4].length() == 16) {

				if (flag) {
					yearMonthRefrence = AccountTools.yearMonthnameKey(headers[1]);
					flag = false;
				}
				
				       dailyTransactionSet.put( (headers[1] + "_" + headers[headers.length - 4]) ,
					    new TransactionParameters(headers[headers.length - 4] , headers[headers.length - 2], lastCurrentAmount,AccountTools.StringToDouble(headers[headers.length - 1])));
			    lastCurrentAmount = AccountTools.StringToDouble(headers[headers.length - 1]);     
			}
		}
		
		//System.out.println(yearMonthRefrence+"\n\n"+dailyTransactionSet);
		 listOfTransactions.put(yearMonthRefrence, dailyTransactionSet);
		//System.out.println(listOfTransactions);
	}

	public static void printingTranction() {

		try {

			FileWriter myWriter = new FileWriter("C:\\Users\\vijrs\\Desktop\\filename2.txt");
			myWriter.write("Files in Java \n\n");

			//System.out.println(listOfTransactions);
			
			for (Map.Entry<String, TreeMap<String, TransactionParameters>> month : listOfTransactions.entrySet()) {
				//System.out.println(month.getKey()+" "+month.getValue());
				
				for ( Map.Entry<String, TransactionParameters> daily :month.getValue().entrySet() ) {
//						System.out.print(daily.getKey()+" "+daily.getValue().getRefrence()+"    debit =  "+daily.getValue().getDebited()+"  credit = "+daily.getValue().getCredited());
//					
						System.out.printf("%s %s %20s %20s",daily.getKey(),daily.getValue().getRefrence(),daily.getValue().getDebited(),daily.getValue().getCredited());
					System.out.println();
					myWriter.write(daily.getKey()+" ------ "+daily.getValue().getRefrence()+" ======== "+daily.getValue().getDebited()+" -------- "+daily.getValue().getCredited()+"\n");
					
				}
			}
		
			myWriter.close(); // closing try block
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 //closing try block

	}
	
	

}
