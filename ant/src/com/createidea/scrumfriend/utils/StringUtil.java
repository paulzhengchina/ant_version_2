package com.createidea.scrumfriend.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public class StringUtil extends StringUtils {
	private static Vector nonKeyWords = new Vector(4);

	static {
		nonKeyWords.addElement("the");
		nonKeyWords.addElement("a");
		nonKeyWords.addElement("an");
		nonKeyWords.addElement("of");
	}

	private StringUtil() {
	}

	/**
	 * 判断字符串是�?�为�??
	 * 
	 * @by jinxinzhang
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * Capitalizes a string, i.e. changing its first letter to uppercase.
	 * 
	 * @param str
	 *            The String that needs to be capitalized.
	 * @return The capitalized string.
	 */
	public static String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		else
			return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Fully capitalizes all words in a String, i.e. changing the first letter
	 * of each and every word (except for non key words) in the string to
	 * uppercase.
	 * 
	 * @param str
	 *            The string to be fully capitalized.
	 * @return The fully capitalized string.
	 */
	public static String capitalizeAllWords(String str) {
		if (str == null || str.length() == 0)
			return str;

		StringTokenizer tokens = new StringTokenizer(str, " ");
		StringBuffer sb = new StringBuffer();
		String word;

		while (tokens.hasMoreTokens()) {
			word = tokens.nextToken();
			sb.append(' ');

			if (!nonKeyWords.contains(word)) {
				sb.append(word.substring(0, 1).toUpperCase());
				sb.append(word.substring(1));
			} else
				sb.append(word);
		}

		return sb.toString().substring(1);
	}

	/**
	 * Combines the strings values in the string array into one single string,
	 * delimited by the specified delimiter. An emtpy String is returned if the
	 * given values array is of size 0.
	 * 
	 * @param values
	 *            The strings to be combined.
	 * @param delimiter
	 *            The delimiter used to separate the different strings.
	 * @return The resultant string combined from the string array separated by
	 *         the specified delimiter. Return an emtpy String if the given
	 *         values array is of size 0.
	 * @throws NullPointerException
	 *             if the values argument is <code>null</code>
	 */
	public static String combine(String[] values, String delimiter) {

		if (values == null) {
			throw new NullPointerException("values array is null");
		}

		if (values.length == 0) {
			return "";
		}

		StringBuffer result = new StringBuffer();

		for (int i = 1; i < values.length; i++) {
			result.append(delimiter);
			result.append(values[i]);
		}

		result.insert(0, values[0]);

		return result.toString();
	}

	/**
	 * Removes redundant spaces (the second consecutive space onwards) from a
	 * String.
	 * 
	 * @param str
	 *            The String that needs to be compacted.
	 * @return The String which has been compacted.
	 */
	public static String compact(String str) {
		if (str == null || str.length() == 0)
			return str;

		int len = str.length();
		char buf[] = new char[len];
		StringBuffer sb = new StringBuffer();
		str.getChars(0, len, buf, 0);
		int i = 0;

		while (i < len) {
			if (buf[i] != ' ') /* Found the first space */
				sb.append(buf[i++]);
			else {
				sb.append(' ');
				while (i < len && buf[i] == ' ')
					/* Skip the rest of the spaces */
					i++;
			}
		}

		return sb.toString();
	}

	/**
	 * If a string is null, return it as "".
	 * 
	 * @param str
	 *            The String that needs to be checked for null value.
	 * @return The String that is converted to appropriate string value.
	 */
	public static String deNull(String str) {
		if (str == null)
			return "";
		return str;
	}

	/**
	 * To return a string which is filled with a specified string. e.g.
	 * duplicate("*", 5) returns "*****", duplicate("OK", 3) returns "OKOKOK"
	 * repeated for given number of times
	 * 
	 * @param str
	 *            String to be repeated/duplicated
	 * @param times
	 *            Number of time the string to be repeated/duplicated
	 * @return The resulted string with <code>str</code> repeated the
	 *         specified number of times.
	 */
	public static String duplicate(String str, int times) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < times; i++) {
			result.append(str);
		}
		return (result.toString());
	}

	/**
	 * Get the count of occurrences of the character in the target string.
	 * 
	 * @param str
	 *            The String used to check for the character occurrenct count.
	 * @param ch
	 *            The character to be counted in the string.
	 * @return Number of occurrences of the character in the target string.
	 */
	public static int getCount(String str, char ch) {
		int pos;
		int count = 0;

		do {
			pos = str.indexOf(ch);

			if (pos != -1) {
				count++;

				if (pos != str.length())
					str = str.substring(pos + 1, str.length());
				else
					pos = -1;
			}

		} while (pos != -1);

		return count;
	}

	/**
	 * Checks if the length of the string is of the length specified.
	 * 
	 * @param str
	 *            The string to test for the length.
	 * @param len
	 *            The length that the string should conform to.
	 * @return A boolean value that indicates if the string is of the length
	 *         specified.
	 */
	public static boolean isLengthEqual(String str, int len) {
		if (str == null) {
			return false;
		} // if (str == null)

		return (str.length() == len) ? true : false;
	}

	/**
	 * Tests whether the specified string's length is less than or equal to the
	 * specified length.
	 * 
	 * @param str
	 *            The string to test for the length.
	 * @param len
	 *            The length that the string should conform to.
	 * @return A boolean value that indicates if the string is at most the
	 *         length specified.
	 */
	public static boolean isLengthLessThan(String str, int len) {
		if (str == null) {
			return false;
		} // if (str == null)

		return (str.length() <= len) ? true : false;
	}

	/**
	 * To pad the given string with a user specified character on the left up to
	 * the given length. e.g. lPad("ABCD", 10, 'X') returns "XXXXXXABCD" which
	 * has a length of 10. This method has built-in 'intelligence' to handle
	 * cases where calling method If <I>str</I> already longer than <I>length</I>,
	 * return <I>str</I> itself. tries to be funny and supply the following : -
	 * lPad("abc", 10, "123") it will return, "1231231abc"
	 * 
	 * @param str
	 *            String to be padded
	 * @param length
	 *            he required length of the resulted string.
	 * @param padString
	 *            The required padding string
	 * @return The padded string
	 */
	public static String lPad(String str, int length, String padString) {
		if (str == null)
			str = "";
		int lOriginal = str.length();
		int lPadStr = padString.length();
		int times2Pad = 0;
		int lPadded = 0;

		if (lOriginal >= length)
			return str;

		StringBuffer sb = new StringBuffer();
		String padded;

		times2Pad = (length - lOriginal) / lPadStr; // will give (1) if 3/2

		padded = duplicate(padString, times2Pad);
		lPadded = padded.length();
		sb.append(padded); // pad in the repetitive characters

		// if still insufficient by the modulus e.g. 30/20 is 10
		if (lOriginal + lPadded < length) {
			int more = length - (lOriginal + lPadded);

			// add in the difference which is less entire length of padStr
			sb.append(padString.substring(0, more));
		}

		sb.append(str); // pad the original string behind

		return sb.toString();
	}

	/**
	 * Pads the string with prevailing spaces.
	 * 
	 * @param str
	 *            String to be padded with spaces on the left.
	 * @param len
	 *            The number of spaces to pad to the left of the string.
	 * @return The space-padded string.
	 */
	public static String lPad(String str, int len) {
		if (str == null)
			str = "";
		return lPad(str, len, " ");
	}

	/**
	 * Remove all occurrences of the match in the target string.
	 * 
	 * @param str
	 *            The String to be checked and have the occurrences of the
	 *            matching String removed.
	 * @param match
	 *            The matching string.
	 * @return The resultant string with all matching string removed.
	 */
	public static String removeAllMatch(String str, String match) {

		if (str == null || match == null || str.length() == 0
				|| match.length() == 0) {
			return "";
		}

		StringBuffer newStr = new StringBuffer();

		int endpos = 0;
		for (int startpos = str.indexOf(match, endpos); startpos != -1; startpos = str
				.indexOf(match, endpos)) {
			newStr.append(str.substring(endpos, startpos));
			endpos = startpos + match.length();
		}

		newStr.append(str.substring(endpos));

		return newStr.toString();
	}

	/**
	 * Replace the occurrence of a key within the existing string with the
	 * required value.
	 * 
	 * @param str
	 *            Existing String to be replace
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The replaced value
	 * @return The resulted string
	 */
	public static String replaceAll(String str, String key, String replacement) {

		// Split the string with the key as the delimiter
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(str)) {
			String[] parts = StringUtil.split(str, key);
			sb.append(parts[0]);
			for (int i = 1; i < parts.length; i++) {
				sb.append(replacement + parts[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * Replaces the first substring of this string that matches the given key
	 * with the given replacement.
	 * 
	 * @param str
	 *            The String to be replaced
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The String used to replace
	 * @return The String with the first occurence of the key value replaced.
	 */
	public static String replaceFirst(String str, String key, String replacement) {
		StringBuffer result = new StringBuffer(str);

		int pos = str.indexOf(key);

		if (pos >= 0) {
			result.replace(pos, pos + key.length(), replacement);
			// System.out.println( "result = " + result );
		}
		return result.toString();
	}

	/**
	 * Replaces the last substring of this string that matches the given key
	 * with the given replacement.
	 * 
	 * @param str
	 *            The String to be replaced
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The String used for replacement
	 * @return The String with the last occurence of the key value replaced.
	 */
	public static String replaceLast(String str, String key, String replacement) {
		StringBuffer result = new StringBuffer(str);

		int pos = str.lastIndexOf(key);

		if (pos >= 0) {
			result.replace(pos, pos + key.length(), replacement);
			System.out.println("result = " + result);
		}
		return result.toString();
	}

	/**
	 * To pad the given string with spaces up to the given length. <br>
	 * e.g. rPad("ABCD", 10, ' ') returns "ABCD " which has a length of 10.
	 * 
	 * This method has built-in 'intelligence' to handle cases where calling
	 * method tries to be funny and supply the following<br> - rPad("abc", 10,
	 * "123") it will return, "abc1231231"
	 * 
	 * @param str
	 *            String to be padded
	 * @param length
	 *            The required length of the resulted string
	 * @param padString
	 *            The required padding string.
	 * @return The padded string. If str already <I>longer</I> than <I>length</I>,
	 *         return str itself.
	 */
	public static String rPad(String str, int length, String padString) {
		if (str == null)
			str = "";
		if (length <= 0)
			return str;
		int lOriginal = str.length();
		int lPadStr = padString.length();
		int times2Pad = 0;
		int lPadded = 0;

		if (lOriginal >= length)
			return str;

		StringBuffer sb = new StringBuffer(str); // add the original str
		// first
		String padded;

		times2Pad = (length - lOriginal) / lPadStr; // will give (1) if 3/2

		padded = duplicate(padString, times2Pad);
		lPadded = padded.length();
		sb.append(padded); // pad in the repetitive characters

		// if still insufficient by the modulus e.g. 30/20 is 10
		if (lOriginal + lPadded < length) {
			int more = length - (lOriginal + lPadded);

			// add in the difference which is less entire length of padStr
			sb.append(padString.substring(0, more));
		}

		return sb.toString();
	}

	/**
	 * Pads the string with following spaces.
	 * 
	 * @param str
	 *            The String to be padded with spaces on the right.
	 * @param len
	 *            The number of spaces to pad to the right of the string.
	 * @return The resultant string with spaces padded on the right.
	 */
	public static String rPad(String str, int len) {
		if (str == null)
			str = "";
		return rPad(str, len, " ");
	}

	/**
	 * Parse a string and split into various parts by the specified delimiters.
	 * 
	 * @param str
	 *            the string to be split
	 * @param delimiter
	 *            delimiting character(s)
	 * @return The string array containing the parts delimited by the specified
	 *         delimiter.
	 */
	public static String[] split(String str, String delimiter) {
		// tentatively allocate only vector of size 3.
		// if not enough, vector will increment 3 each time.
		Vector result = new Vector(3, 3);

		int index = 0;
		int pos = 0;
		int count = 0;

		while (true) {
			pos = str.indexOf(delimiter, index);

			// no more parts
			if (pos == -1) {
				result.add(count, str.substring(index));
				count++;
				break;
			}

			// put into the array
			result.add(count, str.substring(index, pos));

			// increment count
			count++;

			// must cater for delimiter with length > 1
			// so cannot just + 1
			index = pos + delimiter.length();

		} // parseString()

		// compact the array
		String[] tmp = new String[count];
		System.arraycopy(result.toArray(), 0, tmp, 0, count);

		result = null;
		return tmp;
	}

	/**
	 * &quot;normalize&quot; the given absolute path.
	 * 
	 * <p>
	 * This includes:
	 * <ul>
	 * <li>Uppercase the drive letter if there is one.</li>
	 * <li>Remove redundant slashes after the drive spec.</li>
	 * <li>resolve all ./, .\, ../ and ..\ sequences.</li>
	 * <li>DOS style paths that start with a drive letter will have \ as the
	 * separator.</li>
	 * </ul>
	 * 
	 * @param path
	 *            the absolute path
	 * @throws java.lang.NullPointerException
	 *             if the file path is equal to null.
	 */
	public static String normalize(String path) {
		String orig = path;

		path = path.replace('/', File.separatorChar).replace('\\',
				File.separatorChar);

		// make sure we are dealing with an absolute path
		int colon = path.indexOf(":");

		if (!path.startsWith(File.separator) && (colon == -1)) {
			throw new RuntimeException(path + " is not an absolute path");
		}

		boolean dosWithDrive = false;
		String root = null;
		// Eliminate consecutive slashes after the drive spec
		if (path.length() >= 2 && Character.isLetter(path.charAt(0))
				&& path.charAt(1) == ':') {

			dosWithDrive = true;

			char[] ca = path.replace('/', '\\').toCharArray();
			StringBuffer sbRoot = new StringBuffer();
			for (int i = 0; i < colon; i++) {
				sbRoot.append(Character.toUpperCase(ca[i]));
			}
			sbRoot.append(':');
			if (colon + 1 < path.length()) {
				sbRoot.append(File.separatorChar);
			}
			root = sbRoot.toString();

			// Eliminate consecutive slashes after the drive spec
			StringBuffer sbPath = new StringBuffer();
			for (int i = colon + 1; i < ca.length; i++) {
				if ((ca[i] != '\\') || (ca[i] == '\\' && ca[i - 1] != '\\')) {
					sbPath.append(ca[i]);
				}
			}
			path = sbPath.toString().replace('\\', File.separatorChar);

		} else {
			if (path.length() == 1) {
				root = File.separator;
				path = "";
			} else if (path.charAt(1) == File.separatorChar) {
				// UNC drive
				root = File.separator + File.separator;
				path = path.substring(2);
			} else {
				root = File.separator;
				path = path.substring(1);
			}
		}

		Stack s = new Stack();
		s.push(root);
		StringTokenizer tok = new StringTokenizer(path, File.separator);
		while (tok.hasMoreTokens()) {
			String thisToken = tok.nextToken();
			if (".".equals(thisToken)) {
				continue;
			} else if ("..".equals(thisToken)) {
				if (s.size() < 2) {
					throw new RuntimeException("Cannot resolve path " + orig);
				} else {
					s.pop();
				}
			} else { // plain component
				s.push(thisToken);
			}
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.size(); i++) {
			if (i > 1) {
				// not before the filesystem root and not after it, since root
				// already contains one
				sb.append(File.separatorChar);
			}
			sb.append(s.elementAt(i));
		}

		path = sb.toString();
		if (dosWithDrive) {
			path = path.replace('/', '\\');
		}
		return path;
	}

	public static boolean contains(String[] array, String s) {
		return (indexOf(array, s) > -1);
	}

	public static int indexOf(String[] array, String s) {
		for (int i = 0; i < array.length; i++) {
			if (s != null && s.equals(array[i]))
				return i;
		}
		return -1;
	}

	public static String[] unite(String[] array1, String[] array2) {
		String[] result = new String[(array1 == null ? 0 : array1.length)
				+ (array2 == null ? 0 : array2.length)];
		for (int i = 0; i < array1.length; i++)
			result[i] = array1[i];
		for (int i = 0; i < array2.length; i++)
			result[array1.length + i] = array2[i];

		return result;
	}

	/*
	 * public static void main ( String[] args ) { String mystring = "<PSiRequestEnvelope>This
	 * is a test.\nTest test test.</PSiRequestEnvelope>";
	 * 
	 * mystring = StringUtil.removeAllMatch( mystring, "</PSiRequestEnvelope>" );
	 * System.out.println( "mystring = " + mystring ); }
	 */

	public static String escapeSQLString(String inputString) {
		StringBuffer oldString = new StringBuffer(inputString);
		StringBuffer newString = new StringBuffer();
		char c;
		for (int i = 0; i < inputString.length(); i++) {
			c = oldString.charAt(i);
			switch (c) {
			case '%': {
				newString.append("/%");
				break;
			}
			case '_': {
				newString.append("/_");
				break;
			}
			case '/': {
				newString.append("//");
				break;
			}
			default: {
				newString.append(c);
			}
			}
		}
		return "%" + newString + "%";
	}

	public static String filter(String input) {
		return StringUtil.filter(input, false);
	}

	public static String filter(String input, boolean convert) {
		if (input != null) {
			StringBuffer filtered = new StringBuffer(input.length());
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				if (c == '<') {
					filtered.append("&lt;");
				} else if (c == '>') {
					filtered.append("&gt;");
				} else if (c == '"') {
					filtered.append("&quot;");
				} else if (c == '&') {
					filtered.append("&amp;");
				} else if (convert && c == '\n') {
					filtered.append("<br>");
				} else {
					filtered.append(c);
				}
			}
			return (filtered.toString());
		} else {
			return null;
		}
	}

	public static String shiftLastAlphabets(String id) {
		if (id == null || "".equals(id) || id.length() <= 1)
			return id;

		int firstNumberPosition = 0;
		int lastNumberPosition = id.length() - 1;

		while (firstNumberPosition < id.length()
				&& Character.isLetter(id.charAt(firstNumberPosition)))
			firstNumberPosition++;
		while (lastNumberPosition >= 0
				&& Character.isLetter(id.charAt(lastNumberPosition)))
			lastNumberPosition--;

		if (firstNumberPosition > lastNumberPosition)
			return id;

		StringBuffer sb = new StringBuffer();
		sb.append(id.substring(0, firstNumberPosition));
		sb.append(id.substring(lastNumberPosition + 1, id.length()));
		sb.append(id.substring(firstNumberPosition, lastNumberPosition + 1));
		return sb.toString();
	}

	/**
	 * For removing specified value from a array
	 * 
	 * @param fieldList
	 * @param exludeElement
	 * @return String[]
	 */
	public static String[] removeFromArray(String[] fieldList,
			String exludeElement) {
		String[] newList = new String[fieldList.length - 1];
		List oldList = null;
		int j = 0;

		if (fieldList != null) {
			oldList = Arrays.asList(fieldList);
			if (!oldList.contains(exludeElement)) {
				return fieldList;
			}
			for (int i = 0; i < fieldList.length; i++) {
				if (!exludeElement.equals(fieldList[i])) {
					newList[j] = fieldList[i];
					j++;
				}
			}
			return newList;
		} else {
			return null;
		}
	}

	public static String getCommaSeparatedSQLString(String[] arrayOfString) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < arrayOfString.length; i++) {
			list.add(arrayOfString[i]);
		}
		return getCommaSeparatedSQLString(list);
	}

	public static String getCommaSeparatedSQLString(List listOfString) {
		StringBuffer sqlStringBuffer = new StringBuffer();
		for (int i = 0; listOfString != null & i < listOfString.size(); i++) {
			String str = (String) listOfString.get(i);
			if ((str != null) && (str.trim().length() != 0)) {
				if (i > 0) {
					sqlStringBuffer.append(",");
				}
				sqlStringBuffer.append("'");
				sqlStringBuffer.append(str);
				sqlStringBuffer.append("'");
			}
		}
		if (sqlStringBuffer.length() == 0)
			sqlStringBuffer.append("''");
		return sqlStringBuffer.toString();
	}

	public static String[] mergeArray(String[] arr1, String[] arr2) {
		String[] reslutArray;
		int arrayLenth = 0;
		if (arr1 != null) {
			arrayLenth = arr1.length;
			if (arr2 != null)
				arrayLenth += arr2.length;
			else
				return arr1;
		} else {
			if (arr2 != null)
				return arr2;
			else
				return new String[0];
		}
		reslutArray = new String[arrayLenth];
		System.arraycopy(arr1, 0, reslutArray, 0, arr1.length);
		System.arraycopy(arr2, 0, reslutArray, arr1.length, arr2.length);
		return reslutArray;

	}

	public static String trim(String s) {
		if (isNotEmpty(s))
			return s.trim();
		else
			return s;
	}

	public static String filterDir(String input) 
	{
			
		if (input != null) {
			StringBuffer filtered = new StringBuffer(input.length());
			char c;
			for (int i = 0; i < input.length(); i++) 
			{
				c = input.charAt(i);
				if (c != '!'&&c != '@'&&c != '#'&&c != '$'&&c != '%'&&c != '^'&&c != '&'&&c != '*'
					&&c != '('&&c != ')'&&c != '{'&&c != '}'&&c != '['&&c != ']'&&c != '|'&&c != ' '
					&& c!='.'&&c != '-'&&c != '='&&c != ':'&&c != ';'&&c != '"'&&c != '\''&& c!='<'
					&& c!='>'&& c!='/' && c!=':'&&c!='"'&& c!='?'&&c!='|' &&c!='\\' ) 
				{
					filtered.append(c);
				}
			}
			return (filtered.toString());
		} else {
			return null;
		}
		/*  Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		  Matcher m = p.matcher(input);
		  String after = m.replaceAll(""); 
		  return after;*/
	}

	/*
	 * Change Revision
	 * -------------------------------------------------------------------------
	 * \$Log: StringUtil.java,v $ \Revision 1.4 2007/12/20 05:19:21 yu_xing \UC
	 * 56 Load Inst Data \ \Revision 1.3 2007/11/28 10:39:30 zhili \no message \
	 * \Revision 1.2 2007/11/28 10:07:56 zhili update rPad and lPad \ \Revision
	 * 1.1 2007/09/12 07:18:57 zhili \no message \
	 * =========================================================================
	 */
	
	/**
	 * �?�String�??�?�几�??
	 */
	public static String getStrLast (String str,int i){		
		return str.substring(str.length()-i,str.length());
	}
	
	
	
	/**
	 * 判断是该字符串是�?�是图片或�??�是图片连接
	 * jpg,bmp,gif,jpeg
	 */
	public static boolean isImage (String str){	
		boolean flag = false;
		String type1 = StringUtil.getStrLast(str, 4);
		String type2 = StringUtil.getStrLast(str, 5);
		type1 = type1.toLowerCase();
		type2 = type2.toLowerCase();
		if(type1.equals(".jpg")){
			flag = true;
		}else if(type1.equals(".bmp") ){
			flag = true;
		}else if(type1.equals(".gif")){
			flag = true;
		}

		if(type2.equals(".jpeg")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 去除处�?�树形 返回�?? 中的父节点ID 
	 */
	public static String dealRoleUser(String roleStr,String selStr)
	{
		String str[]=selStr.split(",");
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<str.length;i++)
		{
			if(roleStr.indexOf(str[i])==-1)
			{
			    sb.append(str[i]+",");
			}
		}
		String result=sb.toString();
		return result.substring(0, result.length()-1);
	}
	
	/**
	 * 显示 html代�?
	 * @param source
	 * @return
	 */
	public static String HTMLChange(String source){ 
		String changeStr=""; 
		//changeStr=source.replaceAll("\n","<br>");
		changeStr=source.replaceAll("&","&amp;"); 
		//changeStr=changeStr.replaceAll("\r\n","<br/>"); 
		changeStr=changeStr.replaceAll("<","&lt;"); 
		changeStr=changeStr.replaceAll(">","&gt;"); 
		
		changeStr=changeStr.replaceAll("\"", "&quot;"); 
		return changeStr; 
	} 
	
	/**
	 * 将显示html转�?代�?存入
	 * @param source
	 * @return
	 */
	public static String changeHTML(String source){ 
		String changeStr=""; 
		changeStr=source.replaceAll("<br>","\r\n"); 
		//changeStr=changeStr.replaceAll("<br>","\n");
		
		changeStr=changeStr.replaceAll("&amp;","&"); 
		changeStr=changeStr.replaceAll("&lt;","<"); 
		changeStr=changeStr.replaceAll("&gt;",">"); 
		changeStr=changeStr.replaceAll("&quot;","\""); 
		return changeStr; 
	} 
	/**
	 * 过滤<, >,\n 字符的方法�????
	 * @param input �??�?过滤的字符
	 * @return 完�?过滤以�?�的字符串
	 */
	public static String filterSelect(String input) {
		if (input == null) {
			return null;
		}
		if (input.length() == 0) {
			return input;
		}
		input = input.replaceAll("%", "&quot;%");
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;");
		input = input.replaceAll("\"", "&quot;");
		return input.replaceAll("\n", "<br>");
	}
	
//	public static String getTimeString(){
//		String time = DateUtil.curDateTimeStr19();
//		time = time.replaceAll(" ", "");
//		time = time.replaceAll(":", "");
//		time = time.replaceAll("-", "");
//		return time;
//	}
	
	
	/**
	 * 把�?String数组的里�?�为空的去掉放入新数�??
	 * @param src原数�?? str已�?定义好长度的新数�??
	 */
	public static String[] trim(String[] src,String[] str){
		int len = src.length;
		int j=0;
		for(int i=0; i<len; i++){
			if(StringUtil.isNotBlank(src[i])){
				str[j++] = src[i];
			}
		}
		return str;
	}
}