package com.hncu.taoshu.utils;

import java.util.regex.Pattern;

public class TextUtils
{


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

}
