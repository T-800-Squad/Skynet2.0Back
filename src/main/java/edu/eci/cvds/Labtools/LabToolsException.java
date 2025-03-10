package edu.eci.cvds.Labtools;

public class LabToolsException extends Exception {

    public static String Email_Not_Found = "The text entered is not an email.";
    public static String Email_Domain_Not_Found = "The domain of the email is incorrect.";
    public static String Void_Email = "Enter a email.";

    public LabToolsException(String message) {
        super(message);
    }
}
