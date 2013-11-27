package com.hncu.taoshu.utils;

import java.util.regex.Pattern;

public class TextUtils
{
//  private static StringComparator stringComparator;
//  private static StringComparatorIgnoreCase stringComparatorIgnoreCase = new StringComparatorIgnoreCase(null);

  static
  {
//    stringComparator = new StringComparator(null);
  }

  public static boolean isBlankButNotEmpty(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (paramCharSequence.length() == 0))
      throw new IllegalArgumentException();
    return Pattern.matches("\\s*", paramCharSequence);
  }

  public static boolean isEmpty(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.toString().length() == 0);
  }

  public static boolean isEmptyOrBlank(CharSequence paramCharSequence)
  {
    return (isEmpty(paramCharSequence)) || (paramCharSequence.toString().trim().length() == 0);
  }

  public static boolean isOnlyNumbers(CharSequence paramCharSequence)
  {
//    AssertUtil.checkStringNullOrEmpty(paramCharSequence);
    return Pattern.matches("\\s*\\d+\\s*", paramCharSequence);
  }

  public static int showCharNum(String paramString, char paramChar)
  {
    boolean bool = isEmpty(paramString);
    int i = 0;
    char[] arrayOfChar = null;
    int j = 0;
    int k = 0;
    if (!bool)
    {
      arrayOfChar = paramString.toCharArray();
      j = 0;
      k = arrayOfChar.length;
    }
    while (true)
    {
      if (j >= k)
        return i;
      if (paramChar == arrayOfChar[j])
        ++i;
      ++j;
    }
  }

  public static String showControlCharacters(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int i = 0;
    int j = arrayOfChar.length;
    if (i >= j)
      label23: return localStringBuilder.toString();
    if ((arrayOfChar[i] == '') || ((arrayOfChar[i] >= 0) && (arrayOfChar[i] <= ' ')))
      localStringBuilder.append("[\\u").append(arrayOfChar[i]).append("]");
    while (true)
    {
      ++i;
//      break label23:
      localStringBuilder.append("[").append(arrayOfChar[i]).append("]");
    }
  }




/*  public static List<String> sort(List<String> paramList)
  {
    AssertUtil.checkCollectionNullOrEmpty(paramList);
//    Collections.sort(paramList, stringComparator);
    return paramList;
  }*/

/*  public static String[] sort(String[] paramArrayOfString)
  {
//    AssertUtil.checkArrayNullOrEmpty(paramArrayOfString);
//    Arrays.sort(paramArrayOfString, stringComparator);
    return paramArrayOfString;
  }*/

/*  public static List<String> sortIgnoreCase(List<String> paramList)
  {
    AssertUtil.checkCollectionNullOrEmpty(paramList);
    Collections.sort(paramList, stringComparatorIgnoreCase);
    return paramList;
  }*/

/*  public static String[] sortIgnoreCase(String[] paramArrayOfString)
  {
//    AssertUtil.checkArrayNullOrEmpty(paramArrayOfString);
//    Arrays.sort(paramArrayOfString, stringComparatorIgnoreCase);
    return paramArrayOfString;
  }*/

/*  public static String[] splitTrim(String paramString1, String paramString2)
  {
    return trim(paramString1.split(paramString2));
  }*/

/*  public static String[] trim(String[] paramArrayOfString)
  {
    AssertUtil.checkArrayNullOrEmpty(paramArrayOfString);
    int i = paramArrayOfString.length;
    LinkedList localLinkedList = new LinkedList();
    int j = 0;
    if (j >= i)
      return (String[])localLinkedList.toArray(new String[0]);
    String str = paramArrayOfString[j].trim();
    if (isEmptyOrBlank(str));
    while (true)
    {
      ++j;
//      break label17:
//      localLinkedList.add(str);
    }
  }*/

  public static String trimIgnoreNull(CharSequence paramCharSequence)
  {
    if (isEmptyOrBlank(paramCharSequence))
      return "";
    return paramCharSequence.toString().trim();
  }
  
  
	public static String[] getString(int id, int language)
	{

		switch (language)
		{
			case Const.LANGUAGE_ZH_CN:
				return ConstExt.chineseMap.get(id);
			case Const.LANGUAGE_EN_US:
				return ConstExt.chineseMap.get(id);
		}
		return new String[]
		{ "", "", "" };
	}

/*  private static class StringComparator
    implements Comparator<String>, Serializable
  {
    private static final long serialVersionUID = 4900701236481733031L;

    public int compare(String paramString1, String paramString2)
    {
      return paramString1.compareTo(paramString2);
    }
  }*/

/*  private static class StringComparatorIgnoreCase
    implements Comparator<String>, Serializable
  {
    private static final long serialVersionUID = -7885288744567606076L;

    public int compare(String paramString1, String paramString2)
    {
      return paramString1.compareToIgnoreCase(paramString2);
    }
  }*/
}
