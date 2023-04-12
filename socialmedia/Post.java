package socialmedia;

//extend account to implement serializable
public class Post extends Account{

    //variables
    public int numericalIdentifier;
    public String message;
    public int numberOfComments;
    public int numberOfEndorsements;
    public String accountHandle;
    public int accountIdentifier;

    //create post using id, account id, account handle, and message
    public Post(int numericalIdentifier, int accountIdentifier, String accountHandle, String message){
        //assign variables
        this.numericalIdentifier = numericalIdentifier;
        this.accountIdentifier = accountIdentifier;
        this.accountHandle = accountHandle;
        this.message = message;
    }

    //create empty post constructor for removed post
    public Post(){
        numericalIdentifier = -1;
        message = "The original content was removed from the system and is no longer available.";
        numberOfComments = -1;
        numberOfEndorsements = -1;
        accountHandle = "";
        accountIdentifier = -1;
        //super.decreasePostCount();
    }

    //getter methods
    public int getNumericalIdentifier(){
        return numericalIdentifier;
    }

    public int getAccountIdentifier(){
        return accountIdentifier;
    }

    public String getMessage(){
        return message;
    }

    public String getAccountHandle() {
        return accountHandle;
    }

    //setter methods
    public void setAccountHandle(String newAccountHandle) {
        accountHandle = newAccountHandle;
    }

}