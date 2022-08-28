import com.xadmin.tools.AccountTools;

public class Test {

	public static void main(String[] args) {
		System.out.println(("34,456.343").replaceAll(",",""));
	    
		System.out.println(  AccountTools.StringToDouble(("34,456.343").replaceAll(",",""))*2 );
	}

}
