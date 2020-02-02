public class Product {
	private String name;
	private String type; // chips, candy, bakery, bar, gum
	private int amount = -1;
	private int cost = -1;
	private char row = 0; // A-F
	private int column = -1; // 0-5

	public Product() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		type = type.toLowerCase();
		if (type.equals("chips") || type.equals("candy") || type.equals("bakery") || type.equals("bar")
				|| type.equals("gum")) {
			this.type = type;
		} else {
			System.out.println("Please enter a valid category (chips, candy, bakery, bar, or gum).");
		}
	}

	public String getType() {
		return this.type;
	}

	public void setAmount(int amount) {
		if (amount > 0 && amount <= 20) {
			this.amount = amount;
		} else {
			System.out.println("Please enter a number from 1 to 20.");
		}
	}

	public int getAmount() {
		return this.amount;
	}

	public void setCost(int cost) {
		if (cost > 0) {
			this.cost = cost;
		} else {
			System.out.println("Please enter a valid number from 1 to 20.");
		}
	}

	public int getCost() {
		return this.cost;
	}

	public void setRow(char row) {
		if ((row >= 65 && row <= 70) || (row >= 97 && row <= 102)) {
			this.row = row;
		} else {
			System.out.println("Please enter a letter from A to F (uppercase).");
		}
	}

	public char getRow() {
		return this.row;
	}

	public void setColumn(int column) {
		if (column >= 0 && column <= 5) {
			this.column = column;
		} else {
			System.out.println("Please enter a number from 1 to 6.");
		}
	}

	public int getColumn() {
		return this.column;
	}
}
