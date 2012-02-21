/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tatthang
 */
import java.net.*;
import java.io.*;
    
public class readFromURL
{
    public static void main(String[] args){
        try
        {
            URL url = new URL("http://cuakinh.com.vn/");
           
            URLConnection connection = url.openConnection();
            connection.setDoInput(true); 
            InputStream inStream = connection.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inStream));


            String line = "";
            while ((line = input.readLine()) != null)
                System.out.println(line);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }    
    }
}