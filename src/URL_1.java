/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tatthang
 */
import java.net.URL;

public class URL_1 {

  public static void main(String[] args) {

    try {
      URL u = new URL("http://www.GOOGLE.com");
      String filename = u.getFile();
      Object o = u.getContent();
      System.out.println("I got a " + o.getClass().getName());
      System.out.println("I got a filename "+filename);
    } catch (Exception ex) {
      System.err.println(ex);
    }
  }

}
