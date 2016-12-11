package in.edu.ashoka.TransportApp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**Generates and sends emails on submission of a feedback form
 * 
 * @author Mayukh Nair
 */
public class EmailSender{
    
    /**Sends emails to admin (as of now, Mayukh) upon submission of feedback
     * 
     * @param name Name of user submitting feedback
     * @param email email ID of that user
     * @param comment Feedback submitted
     * @return 0 if email sent successfully; 1 otherwise
     */
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
