package in.edu.ashoka.TransportApp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URISyntaxException;


/**Manages the text messages to be sent before departure of each shuttle
 *
 * @author Mayukh Nair
 */
public class SMSPush {
    
    public static final String account_sid = "ACbfc56e0d591ca4dd8d2e48439367ecec";
    public static final String auth_key = "7d24be05c1bb9f7ced8f4969e7424756";
    
    /**Sends messages to the required phone number
     * 
     * @param textmessage The text of the message to be sent
     * @param phonenumber The phone number to send the message to
     * @throws URISyntaxException To handle errors
     */
    public void pushMessage(String textmessage, String phonenumber) throws URISyntaxException {
        Twilio.init(account_sid,auth_key);
          Message message = Message.creator(
                  new PhoneNumber(phonenumber),  // To number
                  new PhoneNumber("+12018627593"),  // From number
                  textmessage                  // SMS body
          ).create();
    }
}
