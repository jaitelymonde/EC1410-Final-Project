package socialmedia;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SocialMedia is a compiling, functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Victor Smith
 * @author Jai Telymonde
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform {

	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Post> original_posts = new ArrayList<Post>();
	ArrayList<Endorsement> endorsements = new ArrayList<Endorsement>();
	ArrayList<Comment> comments = new ArrayList<Comment>();
	int accID = 0;
	int postID = 0;
	int commentLayer = 0;


		// Account-related methods ****************************************

	/**
	* The method creates an account in the platform with the given handle.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle account’s handle.
	* @throws IllegalHandleException if the handle already exists in the platform.
	* @throws InvalidHandleException if the new handle is empty, has more than 30
	* characters, or has white spaces.
	* @return the ID of the created account.
	*
	*/
	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

		//InvalidHandleException Tests
		if (handle.length() == 0) {
			throw new InvalidHandleException("Handle cannot be empty!");
		} else if (handle.length() > 30) {
			throw new InvalidHandleException("Handle cannot be longer than 30 characters!");
		}
		for (int index = 0; index < handle.length(); index++) {
			if (Character.isWhitespace(handle.charAt(index))) {
				throw new InvalidHandleException("Handle cannot contain white space!");
			}
		}
		//IllegalHandleException Tests
		for (int index = 0; index < accounts.size(); index++) {
			if (accounts.get(index).getHandle().equals(handle)) {
				throw new IllegalHandleException("This handle is already in use!");
			}
		}

		//create account, increment id and print confirmation message
		accID++;
		Account newAccount = new Account(accID, handle);
		accounts.add(newAccount);
		System.out.println("Account " + handle + " created");

		return accID;
	}

	/**
	* The method creates an account in the platform with the given handle and
	* description.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle account’s handle.
	* @param description account’s description.
	* @throws IllegalHandleException if the handle already exists in the platform.
	* @throws InvalidHandleException if the new handle is empty, has more than 30
	* characters, or has white spaces.
	* @return the ID of the created account.
	*/
	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		
		//InvalidHandleException
		if (handle.length() == 0) {
			throw new InvalidHandleException("Handle cannot be empty!");
		} else if (handle.length() > 30) {
			throw new InvalidHandleException("Handle cannot be longer than 30 characters!");
		}
		for (int index = 0; index < handle.length(); index++) {
			if (Character.isWhitespace(handle.charAt(index))) {
				throw new InvalidHandleException("Handle cannot contain white space!");
			}
		}
		//IllegalHandleException
		for (int index = 0; index < accounts.size(); index++) {
			if (accounts.get(index).getHandle().equals(handle)) {
				throw new IllegalHandleException("This handle is already in use!");
			}
		}
		
		// create account, increment id, and print confirmation message
		accID++;
		Account newAccount = new Account(accID, handle, description);
		accounts.add(newAccount);       
		System.out.println("Account " + handle + " created");
		return accID;
	}

	/**
	* The method removes the account with the corresponding ID from the platform.
	* When an account is removed, all of their posts and likes should also be
	* removed.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param id ID of the account.
	* @throws AccountIDNotRecognisedException if the ID does not match to any
	* account in the system.
	*/
	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {

		boolean accountNotFound = true;

		for(int i = 0; i < accounts.size(); i++){
			if(accounts.get(i).getNumericalIdentifier() == id){
				accountNotFound = false;
			}
		}
		//AccountIDNotRecognisedException by ID
		if (accountNotFound) {
			throw new AccountIDNotRecognisedException("No account matched this Handle!");
		} else{
			//loop through accounts, posts, and likes to remove each and add empty post in place
			for(int x = 0; x < accounts.size(); x++){
				if(accounts.get(x).getNumericalIdentifier() == id){
					accountNotFound = false;
					//remove original posts
					String accHandle = accounts.get(x).getHandle();
					for(int i = 0; i < original_posts.size(); i++){
						if(original_posts.get(i).getAccountHandle().equals(accHandle)){
							original_posts.remove(i);
							Post removedPost = new Post();
							original_posts.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					//remove comments
					for(int i = 0; i < comments.size(); i++){
						if(comments.get(i).getAccountHandle().equals(accHandle)){
							comments.remove(i);
							Comment removedPost = new Comment();
							comments.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					//remove endorsements
					for(int i = 0; i < endorsements.size(); i++){
						if(endorsements.get(i).getAccountHandle().equals(accHandle)){
							endorsements.remove(i);
							Endorsement removedPost = new Endorsement();
							endorsements.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					accounts.remove(x);
				}
			}
		
		}
		
	}

	/**
	* The method removes the account with the corresponding handle from the
	* platform. When an account is removed, all of their posts and likes should
	* also be removed.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	12
	* @param handle account’s handle.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	*/
	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		
		boolean accountNotFound = true;

		for(int i = 0; i < accounts.size(); i++){
			if(accounts.get(i).getHandle().equals(handle)){
				accountNotFound = false;
			}
		}
		//AccountIDNotRecognisedException by ID
		if (accountNotFound) {
			throw new HandleNotRecognisedException("No account matched this Handle!");
		} else{
			//loop through accounts, posts, and likes to remove each and add empty post in place
			for(int x = 0; x < accounts.size(); x++){
				if(accounts.get(x).getHandle().equals(handle)){
					accountNotFound = false;
					//remove original posts
					for(int i = 0; i < original_posts.size(); i++){
						if(original_posts.get(i).getAccountHandle().equals(handle)){
							original_posts.remove(i);
							Post removedPost = new Post();
							original_posts.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					//remove comments
					for(int i = 0; i < comments.size(); i++){
						if(comments.get(i).getAccountHandle().equals(handle)){
							comments.remove(i);
							Comment removedPost = new Comment();
							comments.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					//remove endorsements
					for(int i = 0; i < endorsements.size(); i++){
						if(endorsements.get(i).getAccountHandle().equals(handle)){
							endorsements.remove(i);
							Endorsement removedPost = new Endorsement();
							endorsements.add(i, removedPost);
							accounts.get(x).decreasePostCount();
						}
					}
					accounts.remove(x);
				}
			}
		
		}
		
	}


	/**
	* The method replaces the oldHandle of an account by the newHandle.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	6
	* are thrown.
	*
	* @param oldHandle account’s old handle.
	* @param newHandle account’s new handle.
	* @throws HandleNotRecognisedException if the old handle does not match to any
	* account in the system.
	* @throws IllegalHandleException if the new handle already exists in the
	* platform.
	* @throws InvalidHandleException if the new handle is empty, has more
	* than 30 characters, or has white spaces.
	*/
	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
				boolean accountNotFound = true;

			//InvalidHandleException
			if (newHandle.length() == 0) {
				throw new InvalidHandleException("New handle cannot be empty!");
			} else if (newHandle.length() > 30) {
				throw new InvalidHandleException("New handle cannot be longer than 30 characters!");
			}
			for (int index = 0; index < newHandle.length(); index++) {
				if (Character.isWhitespace(newHandle.charAt(index))) {
					throw new InvalidHandleException("New handle cannot contain white space!");
				}
			}
	
			//IllegalHandleException
			for (int index = 0; index < accounts.size(); index++) {
				if (accounts.get(index).getHandle().equals(newHandle)) {
					throw new IllegalHandleException("New handle is already in use!");
				}
			}
	
			//change account handle of account and posts linking back to old account handle
			for(int x = 0; x < accounts.size(); x++){
				if(accounts.get(x).getHandle().equals(oldHandle)){
					accounts.get(x).setHandle(newHandle);;
					accountNotFound = false;
					//change handle linked by original posts
					for(int i = 0; i < original_posts.size(); i++){
						if(original_posts.get(i).getAccountHandle().equals(oldHandle)){
							original_posts.get(i).setAccountHandle(newHandle);
						}
					}
					//change handle linked by comments
					for(int i = 0; i < comments.size(); i++){
						if(comments.get(i).getAccountHandle().equals(oldHandle)){
							comments.get(i).setAccountHandle(newHandle);
						}
					}
					//change handle linked by endorsements
					for(int i = 0; i < endorsements.size(); i++){
						if(endorsements.get(i).getAccountHandle().equals(oldHandle)){
							endorsements.get(i).setAccountHandle(newHandle);
						}
					}
				}
			}
	
			//HandleNotRecognisedException
			if (accountNotFound) {
				throw new HandleNotRecognisedException("No account matched this handle!");
			}

	}

	/**
	* The method updates the description of the account with the respective handle.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle handle to identify the account.
	* @param description new text for description.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	*/
	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// update account description
		boolean accountNotFound = true;
		
		//change account's old description to new description
		for(int x = 0; x < accounts.size(); x++){
			if(accounts.get(x).getHandle().equals(handle)){
				accounts.get(x).setDescriptionField(description);
				accountNotFound = false;
			}
		}

		//HandleNotRecognisedException
		if (accountNotFound) {
			throw new HandleNotRecognisedException("No account matched this handle!");
		}
	}

	/**
	* The method creates a formatted string summarising the stats of the account
	* identified by the given handle. The template should be:
	*
	* <pre>
	* ID: [account ID]
	* Handle: [account handle]
	* Description: [account description]
	* Post count: [total number of posts, including endorsements and replies]
	* Endorse count: [sum of endorsements received by each post of this account]
	* </pre>
	*
	* @param handle handle to identify the account.
	* @return the account formatted summary.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	*/
	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		
		boolean accountNotFound = true;
		String shown = "";

		//loop through accounts to set return variable
		for(int x = 0; x < accounts.size(); x++){
			if(accounts.get(x).getHandle().equals(handle)){
				shown = "ID: " + accounts.get(x).getNumericalIdentifier() + "\nHandle: " + accounts.get(x).getHandle() + "\nDescription: " + accounts.get(x).getDescriptionField() + "\nPost count: " + accounts.get(x).getPostCount() + "Endorsement count: " + accounts.get(x).getEndorsementCount();
				accountNotFound = false;
			}
		}

		//HandleNotRecognisedException
		if (accountNotFound) {
			throw new HandleNotRecognisedException("No account matched this handle!");
		} else {
			return shown;
		}
	}

		// End Account-related methods ****************************************


		// Post-related methods ****************************************

	/**
	* The method creates a post for the account identified by the given handle with
	* the following message.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle handle to identify the account.
	* @param message post message.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	* @throws InvalidPostException if the message is empty or has more than
	* 100 characters.
	* @return the sequential ID of the created post.
	*/
	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		
		boolean accountNotFound = true;

		//InvalidPostException
		if (message.length() == 0) {
			throw new InvalidPostException("Post message cannot be empty!");
		} else if (message.length() > 100){ //message is longer than 100 characters
			throw new InvalidPostException("Post message cannot be longer than 100 characters!!"); 
		} else {
			for(int x = 0; x < accounts.size(); x++){
				//if account handle is the same
				if(accounts.get(x).getHandle().equals(handle)){

					//increment postID, post count, and create new post
					postID++;
					accounts.get(x).increasePostCount();
					int originalAccID = accounts.get(x).getNumericalIdentifier();
					Post newPost = new Post(postID, originalAccID, handle, message);
					original_posts.add(newPost);
					accountNotFound = false;
				}
			}

			//HandleNotRecognisedException
			if (accountNotFound) {
				throw new HandleNotRecognisedException("No account matched this handle!");
			} else {
				System.out.println("Post by: " + handle + " created");
				return postID;
			}
		}
		
	}

	/**
	7
	* The method creates an endorsement post of an existing post, similar to a
	* retweet on Twitter. An endorsement post is a special post. It contains a
	* reference to the endorsed post and its message is formatted as:
	* <p>
	* <code>"EP@" + [endorsed account handle] + ": " + [endorsed message]</code>
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle of the account endorsing a post.
	* @param id of the post being endorsed.
	* @return the sequential ID of the created post.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	* @throws PostIDNotRecognisedException if the ID does not match to any post in
	* the system.
	* @throws NotActionablePostException if the ID refers to a endorsement post.
	* Endorsement posts are not endorsable.
	* Endorsements are not transitive. For
	* instance, if post A is endorsed by post
	* B, and an account wants to endorse B, in
	* fact, the endorsement must refers to A.
	*/
	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
				
			boolean accountNotFound = true;
			boolean postNotFound = true;
	
			//loop through accounts
			for (int x = 0; x < accounts.size(); x++) {
				//if account handle of endorser is found
				if (accounts.get(x).getHandle().equals(handle)) {
					accountNotFound = false;
					//match post id to either an original post or comment to endorse
					//endorse an original post
					for (int i = 0; i < original_posts.size(); i++) {
						if (original_posts.get(i).getNumericalIdentifier() == id) {
							postID++;
							postNotFound = false;
	
							String message = "EP: " + original_posts.get(i).getMessage();
							Endorsement newEndorsement = new Endorsement(postID, accounts.get(x).getNumericalIdentifier(), handle, message, id);
							endorsements.add(newEndorsement);
	
							//increase endorsement count for account being endorsed	
							for(int j = 0; j < accounts.size(); j++){
								if(accounts.get(j).getHandle().equals(original_posts.get(i).getAccountHandle())){
									accounts.get(j).increaseEndorsementCount();
								}
							}
						}
					}
					//endorse a comment
					for (int i = 0; i < comments.size(); i++) {
						if (comments.get(i).getNumericalIdentifier() == id) {
							postID++;
							postNotFound = false;
	
							String message = "EP: " + comments.get(i).getMessage();
							Endorsement newEndorsement = new Endorsement(postID, accounts.get(x).getNumericalIdentifier(), handle, message, id);
							endorsements.add(newEndorsement);
	
							//increase endorsement count for account being endorsed	
							for(int j = 0; j < accounts.size(); j++){
								if(accounts.get(j).getHandle().equals(comments.get(i).getAccountHandle())){
									accounts.get(j).increaseEndorsementCount();
								}
							}
						}
					}
				}
			}
			
			//HandleNotRecognisedException
			if (accountNotFound) {
				throw new HandleNotRecognisedException("No account matched this handle!");
			} else if (postNotFound) {
				//NotActionablePostException
				for (int i = 0; i < endorsements.size(); i++) {
					if (endorsements.get(i).getNumericalIdentifier() == id) {
						throw new NotActionablePostException("Endorsement posts are not endorsable!");
					}	
				}	
	
				//PostIDNotRecognisedException
				throw new PostIDNotRecognisedException("This ID does not match any in the system!");
			} else {
				return postID;
			}
	}

	/**
	* The method creates a comment post referring to an existing post, similarly to
	* a reply on Twitter. A comment post is a special post. It contains a reference
	* to the post being commented upon.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param handle of the account commenting a post.
	* @param id of the post being commented.
	* @param message the comment post message.
	* @return the sequential ID of the created post.
	* @throws HandleNotRecognisedException if the handle does not match to any
	* account in the system.
	* @throws PostIDNotRecognisedException if the ID does not match to any post in
	* the system.
	* @throws NotActionablePostException if the ID refers to a endorsement post.
	* Endorsement posts are not endorsable.
	* Endorsements cannot be commented. For
	* instance, if post A is endorsed by post
	* B, and an account wants to comment B, in
	* fact, the comment must refers to A.
	* @throws InvalidPostException if the comment message is empty or has
	* more than 100 characters.
	*/
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

			boolean accountNotFound = true;
			boolean notActionablePost = false;
			boolean postNotFound = true;
			
			//InvalidPostException
			if (message.length() == 0) {
				throw new InvalidPostException("Post message cannot be empty!");
			} else if (message.length() > 100){
				throw new InvalidPostException("Post message cannot be longer than 100 characters!!"); //message is longer than 100 characters
			} else {
				//loop through accounts to see if handle is found
				for (int x = 0; x < accounts.size(); x++) {
					if (accounts.get(x).getHandle().equals(handle)) {
						accountNotFound = false;
					}
				}
				//loop through endorsements, original posts, and comments to find if the post id exists in the system
				for (int x = 0; x < endorsements.size(); x++) {
					if (endorsements.get(x).getNumericalIdentifier() == id) {
						notActionablePost = true;
					}
				}		
				for (int x = 0; x < original_posts.size(); x++) {
					if (original_posts.get(x).getNumericalIdentifier() == id) {
						postNotFound = false;
					}
				}
				for (int x = 0; x < comments.size(); x++) {
					if (comments.get(x).getNumericalIdentifier() == id) {
						postNotFound = false;
					}
				}
				//if account or post isn't found, or if the post is an endorsement, throw exceptions
				if (accountNotFound) {
					throw new HandleNotRecognisedException("No account matched this handle!");
				} else if (notActionablePost) {
					throw new NotActionablePostException("Endorsement posts are not commentable!");
				} else if (postNotFound) {
					throw new PostIDNotRecognisedException("This ID does not match any posts or comments in the system!");
				} else { //no errors in finding account or post
					for(int x = 0; x < accounts.size(); x++){
						//if account id matches
						if(accounts.get(x).getHandle().equals(handle)){
							//create comment and increment id
							postID++;
							int accountIdentifier = accounts.get(x).getNumericalIdentifier();
							Comment newComment = new Comment(postID, accountIdentifier, handle, id, message);
							comments.add(newComment);
						}
					}
					
					//return postID
					return postID;
				}
			}
	}

	/**
	8
	* The method removes the post from the platform. When a post is removed, all
	* its endorsements should be removed as well. All replies to this post should
	* be updated by replacing the reference to this post by a generic empty post.
	* <p>
	* The generic empty post message should be "The original content was removed
	* from the system and is no longer available.". This empty post is just a
	* replacement placeholder for the post which a reply refers to. Empty posts
	* should not be linked to any account and cannot be acted upon, i.e., it cannot
	* be available for endorsements or replies.
	* <p>
	* The state of this SocialMediaPlatform must be be unchanged if any exceptions
	* are thrown.
	*
	* @param id ID of post to be removed.
	* @throws PostIDNotRecognisedException if the ID does not match to any post in
	* the system.
	*/
	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {

		boolean postNotFound = true;

		//PostIDNotRecognisedException		
		for (int index = 0; index < original_posts.size(); index++) {
			if (original_posts.get(index).getNumericalIdentifier() == id) {
				postNotFound = false;
			}
		}
		for (int index = 0; index < comments.size(); index++) {
			if (comments.get(index).getNumericalIdentifier() == id) {
				postNotFound = false;
			}
		}
		for (int index = 0; index < endorsements.size(); index++) {
			if (endorsements.get(index).getNumericalIdentifier() == id) {
				postNotFound = false;
			}
		}
		//if post isn't found, throw exception
		if (postNotFound) {
			throw new PostIDNotRecognisedException("This ID does not match any posts in the system!");
		}

		//Remove endorements and decrease endorsement count
		for(int index = 0; index < endorsements.size(); index++){
			if(endorsements.get(index).getOriginalID() == id){
				endorsements.remove(index);
				for(int i = 0; i < accounts.size(); i++){
					if(accounts.get(i).getNumericalIdentifier() == comments.get(index).getAccountIdentifier()){
						accounts.get(i).decreaseEndorsementCount();
					}
				}
			}
		}

		//Loop through and remove Comment based of id
		for (int index = 0; index < comments.size(); index++) {
			if (comments.get(index).getNumericalIdentifier() == id) {
				comments.remove(index);
				Comment removedPost = new Comment();
				comments.add(index, removedPost);
				//Decrease linked Account post count
				for(int i = 0; i < accounts.size(); i++){
					if(accounts.get(i).getNumericalIdentifier() == comments.get(index).getAccountIdentifier()){
						accounts.get(i).decreasePostCount();
					}
				}
			}
		}
		//Loop through and remove Post based of id
		for (int index = 0; index < original_posts.size(); index++) {
			if (original_posts.get(index).getNumericalIdentifier() == id) {
				original_posts.remove(index);
				Post removedPost = new Post();
				original_posts.add(index, removedPost);
				//Decrease linked Account post count
				for(int i = 0; i < accounts.size(); i++){
					if(accounts.get(i).getNumericalIdentifier() == original_posts.get(index).getAccountIdentifier()){
						accounts.get(i).decreasePostCount();
					}
				}
			}
		}
	}

	/**
	* The method generates a formated string containing the details of a single
	* post. The format is as follows:
	*
	* <pre>
	* ID: [post ID]
	* Account: [account handle]
	* No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments
	received by the post]
	* [post message]
	* </pre>
	*
	* @param id of the post to be shown.
	* @return a formatted string containing post’s details.
	* @throws PostIDNotRecognisedException if the ID does not match to any post in
	* the system.
	*/
	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		
		String shown = "";
		Account posterAccount = new Account(0, "");
		boolean postIDnotFound = true;
		//loop through accounts to see if id matches, and set posterAccount = to it
		for(int i = 0; i < accounts.size(); i++){
			if(accounts.get(i).getNumericalIdentifier() == id){
				posterAccount = accounts.get(i);
			}
		}
		//find what type of post the id correlates to and display based on the type
		for(int i = 0; i < original_posts.size(); i++){
			if(original_posts.get(i).getNumericalIdentifier() == id){
				shown = "ID: " + original_posts.get(i).getNumericalIdentifier() + "\nAccount: " + posterAccount.getHandle() + "\nNo. Endorsements: " + posterAccount.getEndorsementCount() + "\nPost count: " + posterAccount.getPostCount() + "\n" + original_posts.get(i).getMessage();
				postIDnotFound = false;
			}
		}
		for(int i = 0; i < comments.size(); i++){
			if(comments.get(i).getNumericalIdentifier() == id){
				shown = "ID: " + comments.get(i).getNumericalIdentifier() + "\nAccount: " + posterAccount.getHandle() + "\nNo. Endorsements: " + posterAccount.getEndorsementCount() + "\nPost count: " + posterAccount.getPostCount() + "\n" + comments.get(i).getMessage();
				postIDnotFound = false;
			}
		}
		for(int i = 0; i < endorsements.size(); i++){
			if(endorsements.get(i).getNumericalIdentifier() == id){
				shown = "EP@ " + posterAccount.getHandle() + " " + endorsements.get(i).getMessage();
				postIDnotFound = false;
			}
		}
		//Throw exception if post not found
		if(postIDnotFound){
				throw new PostIDNotRecognisedException("Post ID not recognized");
		}
			
		return shown;
	}

	/**
	* The method builds a StringBuilder showing the details of the current post and
	* all its children posts. The format is as follows (you can use tabs or spaces to represent
	indentation):
	*
	* <pre>
	* {@link #showIndividualPost(int) showIndividualPost(id)}
	* |
	* [for reply: replies to the post sorted by ID]
	* | > {@link #showIndividualPost(int) showIndividualPost(reply)}
	* </pre>
	*
	* See an example:
	*
	* <pre>
	* ID: 1
	* Account: user1
	9
	* No. endorsements: 2 | No. comments: 3
	* I like examples.
	* |
	* | > ID: 3
	* Account: user2
	* No. endorsements: 0 | No. comments: 1
	* No more than me...
	* |
	* | > ID: 5
	* Account: user1
	* No. endorsements: 0 | No. comments: 1
	* I can prove!
	* |
	* | > ID: 6
	* Account: user2
	* No. endorsements: 0 | No. comments: 0
	* prove it
	* | > ID: 4
	* Account: user3
	* No. endorsements: 4 | No. comments: 0
	* Can’t you do better than this?
	*
	* | > ID: 7
	* Account: user5
	* No. endorsements: 0 | No. comments: 1
	* where is the example?
	* |
	* | > ID: 10
	* Account: user1
	* No. endorsements: 0 | No. comments: 0
	* This is the example!
	* </pre>
	*
	* Continuing with the example, if the method is called for post ID=5
	* ({@code showIndividualPost(5)}), the return would be:
	*
	* <pre>
	* ID: 5
	* Account: user1
	* No. endorsements: 0 | No. comments: 1
	* I can prove!
	* |
	* | > ID: 6
	* Account: user2
	* No. endorsements: 0 | No. comments: 0
	* prove it
	* </pre>
	*
	* @param id of the post to be shown.
	* @return a formatted StringBuilder containing the details of the post and its
	* children.
	* @throws PostIDNotRecognisedException if the ID does not match to any post in
	* the system.
	* @throws NotActionablePostException if the ID refers to an endorsement post.
	* Endorsement posts do not have children
	10
	* since they are not endorsable nor
	* commented.
	*/
	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {

		boolean postNotFound = true;
		boolean notActionablePost = true;
		StringBuilder str = new StringBuilder();
		ArrayList<Integer> arrListPostId = new ArrayList<Integer>();
		//used to keep track of indentation
		Integer indentCount = 0;

		for (int index = 0; index < endorsements.size(); index++) {
			if (endorsements.get(index).getNumericalIdentifier() == id) {
				notActionablePost = false;
			}
		}
		//if not endorsement post, throw exception
		if (notActionablePost) {
			throw new NotActionablePostException("This ID is for an Endorsement post!");
		}

		//PostIDNotRecognisedException		
		for (int index = 0; index < original_posts.size(); index++) {
			if (original_posts.get(index).getNumericalIdentifier() == id) {
				postNotFound = false;
			}
		}
		for (int index = 0; index < comments.size(); index++) {
			if (comments.get(index).getNumericalIdentifier() == id) {
				postNotFound = false;
			}
		}
		//if post isn't found, throw exception
		if (postNotFound) {
			throw new PostIDNotRecognisedException("This ID does not match any posts in the system!");
		}
		
		//loop through original_posts and comments to find "Original" post we are finding the children of
		for(int j = 0; j < original_posts.size(); j++){
			if (original_posts.get(j).getNumericalIdentifier() == id) {
				str.append(showIndividualPost(original_posts.get(j).getAccountIdentifier()));
				arrListPostId.add(original_posts.get(j).getNumericalIdentifier());
				indentCount++;
			}
		}
		for(int j = 0; j < comments.size(); j++){
			if(comments.get(j).getNumericalIdentifier() == id){
				str.append(showIndividualPost(comments.get(j).getAccountIdentifier()));
				arrListPostId.add(comments.get(j).getNumericalIdentifier());
				indentCount++;
			}
		}

		//While arrListPostId has posts not yet added to str
		while (arrListPostId.size() != 0) {
			//record id, append last post to and remove last entry 
			int currentPostId = arrListPostId.get(arrListPostId.size() -1);
			arrListPostId.removeIf(lastId -> lastId == currentPostId);
			str.append(showIndividualPost(currentPostId));

			//Loop through comments finding linked posts, recording them in arrListPostId
			for(int j = 0; j < comments.size(); j++){
				if(comments.get(j).getLinkedID() == currentPostId){
					arrListPostId.add(comments.get(j).getNumericalIdentifier());
				}
			}
		}

		return str;
	}

		// End Post-related methods ****************************************


		// Analytics-related methods ****************************************

	/**
	* This method returns the current total number of accounts present in the
	* platform. Note, this is NOT the total number of accounts ever created since
	* the current total should discount deletions.
	*
	* @return the total number of accounts in the platform.
	*/
	@Override
	public int getNumberOfAccounts() {

		//get account size and return
		int numberOfAccounts = accounts.size();
		return numberOfAccounts;
	}

	/**
	* This method returns the current total number of original posts (i.e.,
	* disregarding endorsements and comments) present in the platform. Note, this
	* is NOT the total number of posts ever created since the current total should
	* discount deletions.
	*
	* @return the total number of original posts in the platform.
	*/
	@Override
	public int getTotalOriginalPosts() {

		//create arraylist of total posts
		ArrayList<Post> totaloriginalposts = new ArrayList<Post>();
		//loop through original post array
		for(int i = 0; i < original_posts.size(); i++){
			//if post is not removed
			if(original_posts.get(i).getAccountIdentifier() != -1){
				//add to total array
				totaloriginalposts.add(original_posts.get(i));
			}
		}
		//return the size of the total arraylist
		int numberOfOriginalPosts = totaloriginalposts.size();
		return numberOfOriginalPosts;
	}

	/**
	* This method returns the current total number of endorsement posts present in
	* the platform. Note, this is NOT the total number of endorsements ever created
	* since the current total should discount deletions.
	*
	* @return the total number of endorsement posts in the platform.
	*/
	@Override
	public int getTotalEndorsmentPosts() {

		//create arraylist to store total endorsements
		ArrayList<Endorsement> totalendorsements = new ArrayList<Endorsement>();
		//loop through endorsements arraylist
		for(int i = 0; i < endorsements.size(); i++){
			//if endorsement is not removed
			if(endorsements.get(i).getAccountIdentifier() != -1){
				//add to total endorsements array
				totalendorsements.add(endorsements.get(i));
			}
		}

		//return the size of the total arraylist
		int numberOfEndorsementPosts = totalendorsements.size();
		return numberOfEndorsementPosts;
	}

	/**
	* This method returns the current total number of comments posts present in the
	* platform. Note, this is NOT the total number of comments ever created since
	* the current total should discount deletions.
	13
	*
	* @return the total number of comments posts in the platform.
	*/
	@Override
	public int getTotalCommentPosts() {

		//create arraylist of total comments
		ArrayList<Comment> totalcomments = new ArrayList<Comment>();
		//loop through comments
		for(int i = 0; i < comments.size(); i++){
			//if comment is not removed
			if(comments.get(i).getAccountIdentifier() != -1){
				//add to total array
				totalcomments.add(comments.get(i));
			}
		}

		//return the size of the total arraylist
		int numberOfCommentPosts = totalcomments.size();
		return numberOfCommentPosts;
	}

	/**
	* This method identifies and returns the post with the most number of
	* endorsements, a.k.a. the most popular post.
	*
	* @return the ID of the most popular post.
	*/
	@Override
    public int getMostEndorsedPost() {
        int mostEndorsedPost = 0;
        int mostEndorsedID = 0;

		//Loop through posts and comments to find most endorsed post, update mostEndorsedID based on this
        for (int index = 0; index < original_posts.size(); index++) {
            if (original_posts.get(index).getEndorsementCount() > mostEndorsedPost) {
                mostEndorsedPost = original_posts.get(index).getEndorsementCount();
                mostEndorsedID = original_posts.get(index).getNumericalIdentifier();
            }
        }
        for (int index = 0; index < comments.size(); index++) {
            if (comments.get(index).getEndorsementCount() > mostEndorsedPost) {
                mostEndorsedPost = comments.get(index).getEndorsementCount();
                mostEndorsedID = comments.get(index).getNumericalIdentifier();
            }
        }

        return mostEndorsedID;
    }

	/**
	* This method identifies and returns the account with the most number of
	* endorsements, a.k.a. the most popular account.
	*
	* @return the ID of the most popular account.
	*/
	@Override
	public int getMostEndorsedAccount() {

		//create max variables to store current maximums
		int maxEndorsements = accounts.get(0).getEndorsementCount();
		int mostEndorsed = accounts.get(0).getNumericalIdentifier();

		//loop through accounts
		for(int i = 0; i < accounts.size(); i++){
			//if endorsementcount exceeds current maximum
			if(accounts.get(i).getEndorsementCount() > maxEndorsements){
				maxEndorsements = accounts.get(i).getEndorsementCount();
				mostEndorsed = accounts.get(i).getNumericalIdentifier();
			}
		}
		
		return mostEndorsed;
	}

		// End Analytics-related methods ****************************************


		// Management-related methods ****************************************

	/**
	* Method empties this SocialMediaPlatform of its contents and resets all
	* internal counters.
	*/
	@Override
    public void erasePlatform() {

		//clear arraylists
        accounts.clear();
        original_posts.clear();
        comments.clear();
        endorsements.clear();

		//reset id counters
        accID=0;
        postID=0;
    }
	/**
	* Method saves this SocialMediaPlatform’s contents into a serialised file, with
	* the filename given in the argument.
	*
	* @param filename location of the file to be saved
	* @throws IOException if there is a problem experienced when trying to save the
	* store contents to the file
	*/
	@Override
	public void savePlatform(String filename) throws IOException {
		try {
			//create output streams
            FileOutputStream fileOutputStream = new FileOutputStream(filename+".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			//write all objects into the stream (arrays containing data)
            objectOutputStream.writeObject(accounts);
			objectOutputStream.writeObject(original_posts);
			objectOutputStream.writeObject(comments);
			objectOutputStream.writeObject(endorsements);

			//empty and close output stream
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ioE) { //catch exception
            throw new IOException("Input Output exception");
        }
	}

	/**
	* Method should load and replace this SocialMediaPlatform’s contents with the
	* serialised contents stored in the file given in the argument.
	* <p>
	* The state of this SocialMediaPlatform’s must be be unchanged if any
	* exceptions are thrown.
	*
	* @param filename location of the file to be loaded
	* @throws IOException if there is a problem experienced when trying
	11
	* to load the store contents from the file
	* @throws ClassNotFoundException if required class files cannot be found when
	* loading
	*/
	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		
		//empty platform
		erasePlatform();

		try { 
			//create input streams
            FileInputStream fileInputStream = new FileInputStream(filename+".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
			//create object variables
			Account accountObject = null;
			Post postObject = null;
			Comment commentObject = null;
			Endorsement endorsementObject = null;

			Object obj = objectInputStream.readObject();
			//loop through all arrays to save all objects
			for (int index = 0; index < ((ArrayList<Account>) obj).size(); index++) {
				accountObject 	= (Account) ((ArrayList<Account>) obj).get(index);
				accounts.add(accountObject);
			}

			obj = objectInputStream.readObject();
			for (int index = 0; index < ((ArrayList<Post>) obj).size(); index++) {
				postObject 	= (Post) ((ArrayList<Post>) obj).get(index);
				original_posts.add(postObject);
			}

			obj = objectInputStream.readObject();
			for (int index = 0; index < ((ArrayList<Comment>) obj).size(); index++) {
				commentObject 	= (Comment) ((ArrayList<Comment>) obj).get(index);
				comments.add(commentObject);
			}

			obj = objectInputStream.readObject();
			for (int index = 0; index < ((ArrayList<Endorsement>) obj).size(); index++) {
				endorsementObject 	= (Endorsement) ((ArrayList<Endorsement>) obj).get(index);
				endorsements.add(endorsementObject);
			}
			
			//close input stream
			objectInputStream.close();
        } catch (IOException ioE) { //catch exceptions
            throw new IOException("Input Output exception");
        } catch (ClassNotFoundException cnfE) {
            throw new ClassNotFoundException("File not found exception");
        }
	}

		// End Management-related methods ****************************************

}