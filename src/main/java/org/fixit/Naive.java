package org.fixit;

public class Naive implements IAlgoStringMatching  {
	
	@Override
	public Boolean Search(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
 
        for (int i = 0; i <= N - M; i++)
        {
            int j;
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j))
                   break;
            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                return true;
        }
        return false;
    }
 
}


