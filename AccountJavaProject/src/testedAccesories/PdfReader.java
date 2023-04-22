package testedAccesories;


import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PdfReader {

	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\vijay\\Downloads\\story.pdf") ;
			PDDocument document = PDDocument.load(file);
			org.apache.pdfbox.text.PDFTextStripper pdfText = new PDFTextStripper();
			System.out.println(pdfText.getText(document));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
