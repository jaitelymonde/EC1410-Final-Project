package socialmedia;

import java.io.Serializable;

//implement serializable to enable load and saving of platform
public class Account implements Serializable{

    //variables
    public int numericalIdentifier;
    public String handle;
    public String descriptionField;
    public int postCount;
    public int endorsementCount;

    //empty constructor to prevent error
    public Account(){
    }

    //create account using id, handle, and description
    public Account(int numericalIdentifier, String handle, String descriptionField){
        //assign variables
        this.numericalIdentifier = numericalIdentifier;
        this.handle = handle;
        this.descriptionField = descriptionField;
        postCount = 0;
        endorsementCount = 0;
    }

    //create account using id and handle
    public Account(int numericalIdentifier, String handle){
        this.numericalIdentifier = numericalIdentifier;
        this.handle = handle;
    }

    //getter methods
    public int getNumericalIdentifier(){
        return numericalIdentifier;
    }

    public String getHandle(){
        return handle;
    }

    public int getPostCount(){
        return postCount;
    }

    public int getEndorsementCount(){
        return postCount;
    }

    public String getDescriptionField(){
        return descriptionField;
    }

    //setter methods
    public void setHandle(String newHandle){
        handle = newHandle;
    }

    public void setDescriptionField(String newDescription){
        descriptionField = newDescription;
    }

    //counting methods to increase or decrease
    public void increasePostCount(){
        postCount++;
    }

    public void decreasePostCount(){
        postCount--;
    }

    public void increaseEndorsementCount(){
        endorsementCount++;
    }

    public void decreaseEndorsementCount(){
        endorsementCount--;
    }

}