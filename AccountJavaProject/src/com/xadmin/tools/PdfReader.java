package com.xadmin.tools;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfReader {

	public static void main(String[] args) {
		try {
			
			PDDocument document = PDDocument.load("C:\\Users\\vijrs\\Desktop\\ab.pdf");
			PDFTextStripper pdfText = new PDFTextStripper();
			System.out.println(pdfText.getText(document));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
