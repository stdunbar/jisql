package com.xigole.util.sql.outputformatter;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import joptsimple.OptionParser;
import joptsimple.OptionSet;


/**
 * This is the "null" formatter - it simply throws away any output
 * This is useful on rare occasions - e.g. when benchmarking how quickly data can be 
 * fetched from a database without wanting to view the data itself
 *
 */
public class NullFormatter implements JisqlFormatter {

    private boolean fetchData = false;

     /**
     * Sets a the option list for this formatter.
     * 
     * @param parser the OptionParser to use.
     * 
     */
    public void setSupportedOptions( OptionParser parser ) {
        parser.accepts( "fetch" );
    }
    
    /**
     * Consumes any options that were specified on the command line.
     *
     * @param options the OptionSet that the main driver is using.
     *
     * @throws Exception if there is a problem parsing the command line arguments.
     *
     */
    public void consumeOptions( OptionSet options ) throws Exception {
        if( options.has( "fetch" ) )
        	fetchData = true;
    }
    
    /**
     * Called to output a usage message to the command line window.  This
     * message should contain information on how to call the formatter.
     *
     */
    public void usage( PrintStream out ) {
        out.println("\t-fetch Fetches the data even though we don't output it - useful for benchmarking." );
    }
    

    /**
     * Outputs a header for a query.  For the NullFormatter this is a no-op.
     *
     * @param out a PrintStream to send any output to.
     * @param metaData the ResultSetMetaData for the output.
     *
     */
    public void formatHeader( PrintStream out, ResultSetMetaData metaData ) throws Exception {
    	/* no output for the NullFormatter */
    }

    
    /**
     * Called to output the data.
     *
     * @param out the PrintStream to output data to.
     * @param resultSet the ResultSet for the row.
     * @param metaData the ResultSetMetaData for the row.
     *
     */
    public void formatData( PrintStream out, ResultSet resultSet, ResultSetMetaData metaData ) throws Exception {
        if (fetchData) {
            while( resultSet.next() ) {
                // Fetch the data even though we don't output it - otherwise the query
                // stops after the first block is fetched (based on fetchsize)
            }
        }
    }

    
    /**
     * Outputs a footer for a query. This method isn't used in the NullFormatter.
     *
     * @param out the PrintStream to output data to.
     * @param metaData the ResultSetMetaData for the output.
     *
     */
    public void formatFooter( PrintStream out, ResultSetMetaData metaData ) throws Exception {
    	/* no output for the NullFormatter */
    }
}
