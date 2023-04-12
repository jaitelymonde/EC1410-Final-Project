package socialmedia;

//extend post to implement serializable and get super methods and variables
public class Endorsement extends Post{

    //id variable
    public int originalID;

    //create empty endorsement constructor for removed endorsements
    public Endorsement(){
        numericalIdentifier = -1;
        accountIdentifier = -1;
        accountHandle = "";
        message = "The original content was removed from the system and is no longer available.";
        originalID = -1;
    }

    //create endorsement using id, account id, account handle, message, and original id
    public Endorsement(int numericalIdentifier, int accountIdentifier, String accountHandle, String message, int originalID){
        super(numericalIdentifier, accountIdentifier, accountHandle, message);
        this.originalID = originalID;
    }

    //getter methods
    public int getOriginalID(){
        return originalID;
    }
}