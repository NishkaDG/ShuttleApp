package in.edu.ashoka.TransportApp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mayukh Nair on 08-Dec-16.
 */
public class EmailSender{

    public int sendFeedback(String name, String email, String comment)  {

        Email feedbackMail = new SimpleEmail();
        feedbackMail.setHostName("smtp.gmail.com");
        feedbackMail.setSmtpPort(587);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDT = dateFormat.format(date);

        feedbackMail.setAuthenticator(new DefaultAuthenticator("ashokasbshelp@gmail.com","LittleBobbyTables"));
        feedbackMail.setStartTLSRequired(true);

        try{
            feedbackMail.setFrom("ashokasbshelp@gmail.com");
            feedbackMail.addTo("mayukh.nair_ug18@ashoka.edu.in");
            feedbackMail.setSubject("New feedback message from "+email+" at "+currentDT);
            feedbackMail.setMsg("New feedback message \n \nName: "+name+"\nEmail:"+email+"\nDate and time: "+currentDT+"\nComment: "+comment);
            feedbackMail.send();
            return 0;
        }
        catch (EmailException e){
            e.printStackTrace();
            return 1;
        }
    }
}
