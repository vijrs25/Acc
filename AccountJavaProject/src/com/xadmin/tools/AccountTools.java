package com.xadmin.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class AccountTools {

	public List<String> filesFromFolder() throws IOException {

		final File folder = new File("C:\\Users\\vijrs\\Desktop\\MonthlyStatement");

		return filesFromFolder(folder);
	}

	public static List<String> filesFromFolder(final File folders) throws IOException {

		File[] listOfFiles = folders.listFiles();
		List<String> listOfMonthlyStatement = new ArrayList<String>();
		//int count = 1;
		PDDocument document = null;

		for (File file : listOfFiles) {

			if (file.isFile()) {

				// System.out.println("\n\n\n\n" + (count++) + "\n\n\n");
				document = PDDocument.load(file);
				// System.out.println(new PDFTextStripper().getText(document));
				listOfMonthlyStatement.add(new PDFTextStripper().getText(document));
				document.close();

			}
		}
		return listOfMonthlyStatement;
	}

	public static String yearMonthName(String month, String year) {

		String monthString = "";

		int yearInteger = Integer.parseInt(year);
		String yearString = "";

		if (yearInteger < 90)
			yearString = "20" + year;
		else
			yearString = "19" + year;

		// Switch statement
		switch (Integer.parseInt(month)) {
		// case statements within the switch block
		case 1:
			monthString = "_January";
			break;
		case 2:
			monthString = "_February";
			break;
		case 3:
			monthString = "_March";
			break;
		case 4:
			monthString = "_April";
			break;
		case 5:
			monthString = "_May";
			break;
		case 6:
			monthString = "_June";
			break;
		case 7:
			monthString = "_July";
			break;
		case 8:
			monthString = "_August";
			break;
		case 9:
			monthString = "_September";
			break;
		case 10:
			monthString = "_October";
			break;
		case 11:
			monthString = "_November";
			break;
		case 12:
			monthString = "_December";
			break;
		default:
			System.out.println("Invalid Month!");
		}
		return yearString + monthString;
	}

	public static String yearMonthnameKey(String head) {
		System.out.println(head);
		String[] transactionKeyArray = head.split("/");
		// System.out.println(transactionKeyArray[1]+" "+transactionKeyArray[2]);
		return (AccountTools.yearMonthName(transactionKeyArray[1], transactionKeyArray[2]));
	}
	
	public static double StringToDouble(String string) {

		if(isNull(string) )
		string = string.replaceAll("\r", "");
		string = string.replaceAll(",", "");
		return Double.parseDouble(string);
	
	}
	
	public static boolean isNull(String string) {
		
		if(string == null || string.isEmpty() ) {
			return false;
		}
		return true;
	}

}
	

