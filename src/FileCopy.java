/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tatthang
 */
import java.io.*;

/**
 * This class is a standalone program to copy a file, and also defines a 
 * static copy() method that other programs can use to copy files.
 **/
public class FileCopy {
    /** The main() method of the standalone program.  Calls copy(). */
    public static void main(String[] args) {
        
        String file_src = "C:\\Users\\tatthang\\Desktop\\Accounts_sbyrosq1t.xml";
        String file_des = "C:\\Users\\tatthang\\Desktop\\Accounts_sbyrosq1t_COPY.xml";
        // Call copy() to do the copy; display any error messages
        try { 
            copy(args[0], args[1]); 
        }
        catch (IOException e) { System.err.println(e.getMessage()); }
        
    }
    
    /**
     * The static method that actually performs the file copy.
     * Before copying the file, however, it performs a lot of tests to make
     * sure everything is as it should be.
     */
    public static void copy(String from_name, String to_name)
	throws IOException
    {
        File from_file = new File(from_name);  // Get File objects from Strings
        File to_file = new File(to_name);
        
        // First make sure the source file exists, is a file, and is readable.
	// These tests are also performed by the FileInputStream constructor
	// which throws a FileNotFoundException if they fail.
        if (!from_file.exists())
            abort("no such source file: " + from_name);
        if (!from_file.isFile())
            abort("can't copy directory: " + from_name);
        if (!from_file.canRead())
            abort("source file is unreadable: " + from_name);
        
        // If the destination is a directory, use the source file name
        // as the destination file name
        if (to_file.isDirectory())
            to_file = new File(to_file, from_file.getName());
        
        // If the destination exists, make sure it is a writeable file
        // and ask before overwriting it.  If the destination doesn't
        // exist, make sure the directory exists and is writeable.
        if (to_file.exists()) {
            if (!to_file.canWrite())
                abort("destination file is unwriteable: " + to_name);
            // Ask whether to overwrite it
            System.out.print("Overwrite existing file " + to_file.getName() +
			     "? (Y/N): ");
            System.out.flush();
            // Get the user's response.
            BufferedReader in=
		new BufferedReader(new InputStreamReader(System.in));
            String response = in.readLine();
            // Check the response.  If not a Yes, abort the copy.
            if (!response.equals("Y") && !response.equals("y"))
                abort("existing file was not overwritten.");
        }
        else {  
            // If file doesn't exist, check if directory exists and is
            // writeable.  If getParent() returns null, then the directory is
            // the current dir.  so look up the user.dir system property to
            // find out what that is.
            String parent = to_file.getParent();  // The destination directory
            if (parent == null)     // If none, use the current directory
		parent = System.getProperty("user.dir");
            File dir = new File(parent);          // Convert it to a file.
            if (!dir.exists())
                abort("destination directory doesn't exist: "+parent);
            if (dir.isFile())
                abort("destination is not a directory: " + parent);
            if (!dir.canWrite())
                abort("destination directory is unwriteable: " + parent);
        }
        
        // If we've gotten this far, then everything is okay.
        // So we copy the file, a buffer of bytes at a time.
        FileInputStream from = null;  // Stream to read from source
        FileOutputStream to = null;   // Stream to write to destination
        try {
            from = new FileInputStream(from_file);  // Create input stream
            to = new FileOutputStream(to_file);     // Create output stream
            byte[] buffer = new byte[4096];         // To hold file contents
            int bytes_read;                         // How many bytes in buffer

            // Read a chunk of bytes into the buffer, then write them out,
            // looping until we reach the end of the file (when read() returns
            // -1).  Note the combination of assignment and comparison in this
            // while loop.  This is a common I/O programming idiom.
            while((bytes_read = from.read(buffer)) != -1) // Read until EOF
                to.write(buffer, 0, bytes_read);            // write
            
        }
        // Always close the streams, even if exceptions were thrown
        finally {
            if (from != null) try { from.close(); } catch (IOException e) { ; }
            if (to != null) try { to.close(); } catch (IOException e) { ; }
        }
    }

    /** A convenience method to throw an exception */
    private static void abort(String msg) throws IOException { 
        throw new IOException("FileCopy: " + msg); 
    }
}
