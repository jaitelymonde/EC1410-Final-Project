package socialmedia;

//extend post to implement serializable and get super methods and variables
public class Comment extends Post{

    //variables
    public String commenterHandle;
    public int linkedID;

    //create empty comment constructor for removed comments
    public Comment() {
        numericalIdentifier = -1;
        message = "The original content was removed from the system and is no longer available.";
        numberOfComments = -1;
        numberOfEndorsements = -1;
        accountHandle = "";
        accountIdentifier = -1;
    }

    //create comment using id, account id, accounthandle, linked id, and message
    public Comment(int numericalIdentifier, int accountIdentifier, String accountHandle, int linkedID, String message) {
        super(numericalIdentifier, accountIdentifier, accountHandle, message);
        this.linkedID = linkedID;
    }

    //getter methods
    public int getLinkedID(){
        return linkedID;
    }

}