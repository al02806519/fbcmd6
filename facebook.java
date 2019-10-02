import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Facebook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient Scanner keyboard = new Scanner(System.in);
	private Map<String, FacebookUser> users;

	public Facebook() {
		users = new HashMap<String, FacebookUser>();

	}

	// prints users
	public void printUsers() {
		for (String key : users.keySet()) {
			System.out.println(users.get(key));
		}
	}

	// adds a user
	public void addUser() {

		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (users.containsKey(username)) {
			System.out.println("that user exist");
		} else {
			System.out.println("Please enter a password.");
			String password = keyboard.nextLine();
			System.out.println("Please enter a password hint.");
			String passWordHint = keyboard.nextLine();
			FacebookUser facebookUser = new FacebookUser(username, password);
			facebookUser.setPasswordHint(passWordHint);
			users.put(username, facebookUser);
		}
	}

	// deletes a user
	public void deleteUser() {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (users.containsKey(username)) {
			FacebookUser facebookUser = users.get(username);
			System.out.println("Please enter a password");
			String password = keyboard.nextLine();
			if (facebookUser.password.equals(password)) {
				users.remove(username);
				System.out.println("User Removed");
			}
		} else {
			System.out.println("That username does not exist");
		}
	}

	public void getPasswordHelp() {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (users.containsKey(username)) {
			FacebookUser facebookUser = users.get(username);
			facebookUser.getPasswordHelp();
		} else {
			System.out.println("That user does not exist");
		}
	}

	public void friend() {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (username != null && !users.containsKey(username)) {
			System.out.println("that user does not exist");
		} else {
			FacebookUser facebookUser = users.get(username);
			System.out.println("Please enter a password.");
			String password = keyboard.nextLine();
			if (facebookUser.password.equals(password)) {

				System.out.println("Please enter a friend's name");
				String friendName = keyboard.nextLine();
				if (friendName.equals(username)) {
					System.out.println("You cannot friend yourself");
				} else {
					FacebookUser friend = users.get(friendName);
					facebookUser.friend(friend);
					users.put(username, facebookUser);
				}
			}

		}
	}

	public void defriend(FacebookUser formerFriend) {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (username != null && !users.containsKey(username)) {
			System.out.println("that user does not exist");
		} else {
			FacebookUser facebookUser = users.get(username);
			System.out.println("Please enter a password.");
			String password = keyboard.nextLine();
			if (facebookUser.password.equals(password)) {

				System.out.println("Please enter a friend's name");
				String friendName = keyboard.nextLine();
				if (friendName.equals(username)) {
					System.out.println("You cannot de-friend yourself");
				} else {
					FacebookUser friend = users.get(friendName);
					facebookUser.defriend(friend);
				}
			}

		}
	}

	public void listFriends() {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (username != null && !users.containsKey(username)) {
			System.out.println("that user does not exist");
		} else {
			FacebookUser facebookUser = users.get(username);
			System.out.println("Please enter a password.");
			String password = keyboard.nextLine();
			if (facebookUser.password.equals(password)) {
				System.out.println("Friends List:");
				facebookUser.getFriends();
			}

		}
	}

	public void getRecommendations() {
		System.out.println("Please enter a username.");
		String username = keyboard.nextLine();
		if (username != null && !users.containsKey(username)) {
			System.out.println("that user does not exist");
		} else {
			FacebookUser facebookUser = users.get(username);
			System.out.println("Please enter a password.");
			String password = keyboard.nextLine();
			if (facebookUser.password.equals(password)) {
				ArrayList<FacebookUser> recommendations = new ArrayList<FacebookUser>();
				for (FacebookUser f : facebookUser.friends) {
					getRecommendations(f, recommendations);
				}

				if (!recommendations.isEmpty()) {

					System.out.println("Recommendation friends List:");
					for (FacebookUser u : recommendations) {
						System.out.println(u);
					}
				}
			}

		}
	}

	private void getRecommendations(FacebookUser user,
			ArrayList<FacebookUser> recommendations) {
		if (user != null && user.friends != null && !user.friends.isEmpty()) {
			for (FacebookUser friend : user.friends) {
				if (!recommendations.contains(friend)) {
					recommendations.add(friend);
					getRecommendations(friend, recommendations);
				}
			}
		}
	}

}
