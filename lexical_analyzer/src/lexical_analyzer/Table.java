package lexical_analyzer;

public class Table {

	public static String[] Keyword = {
	"private","protected","public","abstract","class",
	"extends","final","implements","interface","native",
	"new","static","strictfp","synchronized","transident",
	"volatile","break","continue","return","do","while",
	"switch","case","default","try","cathc","throw","throws",
	"import","package","boolean","byte","char","double","float",
	"true","false","super","this","void","goto","const"};
	
	public static char[]  operator= {'+','-','*','/','=','>','<','!','%','|'};
	public static char[]  Boundary= {',',';','{','}','(',')'};
	
	
	
}
