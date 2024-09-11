package system.util;
public class SystemUtil {
	
// Method to read a line and split it into an array of strings, considering commas and quotes
	private final static  int NUMCOLS=6;
		public static String[] lineReader(String line) {
			
			String[] str = new String[NUMCOLS];
			int index = 0;
			final char chComma = ',';
			final char chQuotes = '"';
			int start = 0;
			int end = line.indexOf(chComma);
			String value;
			while (start < end) {
				if (line.charAt(start) == chQuotes) {
					start++;
					end = line.indexOf(chQuotes, start + 1);
				}
				value = line.substring(start, end);
				value = value.trim();
				str[index++] = value;
				if (line.charAt(end) == chQuotes)
					start = end + 2;
				else
					start = end + 1;
				end = line.indexOf(chComma, start + 1);
			}
			if (start < line.length()) {
				value = line.substring(start);
				str[index++] = value;
			}
			return str;
		}
		
		
		// isValid method
		public static boolean isValid(String param) {
		    if (param == null) {
		        return false;
		    }
		   
		    if (param.trim().isEmpty()) {
		        return false;
		    }
		    return true;
		}
}
