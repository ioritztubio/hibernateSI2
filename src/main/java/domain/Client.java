package domain;


import java.util.ResourceBundle;


import javax.persistence.*;


@Entity
public class Client {

	@Id
	private String userName;
	private String password;
	private float money;
	private boolean admin;
	// private User followed;
	// private boolean privated;
	/*
	 * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) private
	 * Vector<Transaction> movements;
	 * 
	 * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) private
	 * Vector<Bet> bets;
	 * 
	 * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) private
	 * Vector<User> followers; /*@OneToMany(fetch = FetchType.EAGER, cascade =
	 * CascadeType.PERSIST) private Vector<User> friends;
	 */

	public Client(String userName, String password, boolean admin) {
		this.userName = userName;
		this.password = password;
		this.money = 20;
		this.admin = admin;
		/*
		 * this.movements = new Vector<Transaction>(); this.bets = new Vector<Bet>();
		 * this.followers = new Vector<User>(); //this.friends = new Vector<User>();
		 * 
		 */

	}

	/*
	 * public Vector<User> getFriends() { return friends; }
	 * 
	 * public void setFriends(Vector<User> friends) { this.friends = friends; }
	 */

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * public Vector<Transaction> getMovements() { return movements; }
	 * 
	 * public void setMovements(Vector<Transaction> movements) { this.movements =
	 * movements; }
	 * 
	 * public Vector<domain.Bet> getBets() { return this.bets; }
	 * 
	 * public void setBets(Vector<domain.Bet> bets) { this.bets = bets; }
	 * 
	 * public String showMovements() { String s = "{20.0 " + "(" +
	 * ResourceBundle.getBundle("Etiquetas").getString("Deposit") + ")";
	 * 
	 * for (Transaction t : this.movements) { if (t.getType().equals("Bet")) { s = s
	 * + "- " + t.getAmount() + "(" +
	 * ResourceBundle.getBundle("Etiquetas").getString("Bet") + ")"; } else if
	 * (t.getType().equals("Bet Win")) { s = s + "+ " + t.getAmount() + "(" +
	 * ResourceBundle.getBundle("Etiquetas").getString("BetWon") + ")"; } else if
	 * (t.getType().equals("Deposit")) { s = s + "+ " + t.getAmount() + "(" +
	 * ResourceBundle.getBundle("Etiquetas").getString("Deposit") + ")"; } else if
	 * (t.getType().equals("Returned Money")) { s = s + "+ " + t.getAmount() + "(" +
	 * ResourceBundle.getBundle("Etiquetas").getString("Refund") + ")"; } }
	 * 
	 * /* if (this.movements.size() > 0) { s = s.substring(0, s.length() - 2); }
	 */
	/*
	 * 
	 * s += "}";
	 * 
	 * System.out.println(s); return s; }
	 * 
	 * public Vector<User> getFollowers() { return followers; }
	 * 
	 * public void setFollowers(Vector<User> followers) { this.followers =
	 * followers; }
	 * 
	 * public void addBet(Bet b) { if (!this.getBets().contains(b)) {
	 * this.getBets().add(b); } }
	 * 
	 * public void addTransaction(Transaction t) { if
	 * (!this.getMovements().contains(t)) { this.getMovements().add(t); } }
	 * 
	 * public void addFollowers(User u) { if (!this.getFollowers().contains(u)) {
	 * this.getFollowers().add(u); } }
	 * 
	 * public void addFollowed(User u) { this.followed = u; }
	 * 
	 * public String getFollowed() { if (this.followed != null) return
	 * followed.getUserName(); else return "isNull"; } }
	 */

}
