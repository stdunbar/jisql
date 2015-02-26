package com.xigole.util.sql;

/**
 * This class attempts to erase characters echoed to the console.
 * <p>
 * This code is derived from
 * http://java.sun.com/features/2002/09/pword_mask.html, The article states
 * <p>
 * <quote>
 * Feel free to reuse and improve the code in this article for your applications.
 * </quote>
 * <p>
 * So I think that it should be ok to include it here.  I have only removed
 * unneeded variables and changed to my coding style.  No "real" changes
 * have been made.
 *
 */
public class MaskingThread extends Thread
{
    private boolean stop = false;
    private String prompt = null;

    /**
     * @param thePrompt The prompt displayed to the user
     */
    public MaskingThread(String thePrompt)
    {
        prompt = thePrompt;
    }

    /**
     * Begin masking until asked to stop.
     */
    public void run()
    {
        while (!stop)
        {
            try
            {
                // attempt masking at this rate (refresh every 1 ms.)
                sleep(1);
            }
            catch (InterruptedException iex)
            {
                iex.printStackTrace();
            }
            if (!stop)
            {
//
// sd - note what this does.  If your prompt is really short
// and your password is really long this won't work - some
// characters will become visible
//
                System.out.print( "\r" + prompt + " \r" + prompt );
            }
            System.out.flush();
        }
    }

    /**
     * Instruct the thread to stop masking.
     */
    public void stopMasking()
    {
        stop = true;
    }
}
