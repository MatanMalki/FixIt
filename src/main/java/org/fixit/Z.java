package org.fixit;

public class Z implements IAlgoStringMatching  {
	
	@Override
	public Boolean Search(String pattern, String text)
    {
  
        String concat = pattern + "$" + text;
  
        int l = concat.length();
  
        int Z[] = new int[l];
  
        getZarr(concat, Z);
  
        for(int i = 0; i < l; ++i)
        {
            if(Z[i] == pattern.length())
            {
               return true;
            }
        }
        return false;
    }
	
  
    private static void getZarr(String str, int[] Z)
    {
  
        int n = str.length();
        int L = 0, R = 0;
  
        for(int i = 1; i < n; ++i) {

            if(i > R)
            {
  
                L = R = i;
                while(R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                  
                Z[i] = R - L;
                R--;
  
            }
            else
            {

                int k = i - L;
                if(Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else
                {
                    L = i;
                    while(R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                      
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }

}
