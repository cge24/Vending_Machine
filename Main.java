import java.util.Scanner;

public class Main {
	private static Product[][] machine = new Product[6][6];
	private static int balance = 0;

	public static void menu() {
		System.out.println("(a) deposit money into vending machine");
		System.out.println("(b) check balance");
		System.out.println("(c) buy a product");
		System.out.println("(d) add new product");
		System.out.println("(e) look at product details");
		System.out.println("(f) see vending machine (shows amount of each item)");
		System.out.println("(g) quit");
	}

	/**
	 * add lowercase letters to coordinates - DONE
	 * 
	 * incorporate hashmap to products (key = coordinate) delete + insert in array
	 * and in hashmap each time
	 * 
	 * get rid of bank account; (a) deposit money into vending machine (keep balance
	 * of how much money is put in and print each time) - DONE
	 * 
	 * 
	 * 
	 * @param sc
	 * @param current
	 */

	public static void commands(Scanner sc, String current) {
		while (true) {
			if (current.equals("a") || current.equals("A")) { // deposit money
				depositMoney(sc);
			} else if (current.equals("b") || current.equals("B")) { // get balance of money put into vending machine
				System.out.println("Current balance = $" + balance);
			} else if (current.equals("c") || current.equals("C")) { // buy a product
				Product product = getProduct(sc);
				if (product == null) {
					System.out.println("There is no product in this location.");
				} else {
					if (checkBalance(product.getCost())) {
						balance -= product.getCost();
						product.setAmount(product.getAmount() - 1);
						System.out.println("One " + product.getName().toLowerCase() + " successfully bought. You have $"
								+ balance + " left.");
					} else {
						System.out.println("You don't have enough money to buy this product.");
					}
				}
			} else if (current.equals("d") || current.equals("D")) { // add new product to vending machine
				addProduct(sc);
			} else if (current.equals("e") || current.equals("E")) { // look at selection of vending machine products
				Product product;
				product = getProduct(sc);
				if (product == null) {
					System.out.println("There is no product in this location.");
				} else {
					details(sc, product);
				}
			} else if (current.equals("f") || current.equals("F")) { // print out vending machine
				printVendingMachine();
			} else if (current.equals("g") || current.equals("G")) { // quit
				break;
			} else {
				System.out.println("Please enter a valid command from the menu." + '\n');
				menu();
				current = sc.nextLine();
				continue;
			}

			System.out.println('\n' + "What would you like to do next?");
			menu();
			current = sc.nextLine();
		}
	}

	public static void depositMoney(Scanner sc) {
		System.out.println("How much money would you like to deposit?");
		while (true) {
			try {
				String current = sc.nextLine();
				int amount = Integer.parseInt(current);
				balance += amount;
				break;
			} catch (Exception e) {
				System.out.println("Try again.");
			}
		}
		System.out.println("Deposit successful. Current balance = $" + balance);
	}

	public static Product getProduct(Scanner sc) {
		String current = "";
		int row;
		int column;

		System.out.println("Enter a row (A-F):");
		while (true) {
			try {
				current = sc.nextLine();
				row = current.charAt(0);
				if ((row >= 65 && row <= 70) || (row >= 97 && row <= 102)) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Please enter a letter from A to F.");
			}
		}

		System.out.println("Enter a column (0-5):");
		while (true) {
			try {
				current = sc.nextLine();
				column = Integer.parseInt(current);
				if (column >= 0 && column <= 5) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Please enter a number from 0 to 5.");
			}
		}

		if (hasProduct(row, column)) {
			if (row >= 65 && row <= 70) {
				row -= 65;
			} else if (row >= 97 && row <= 102) {
				row -= 97;
			}
			return machine[row][column];
		} else {
			return null;
		}
	}

	public static void details(Scanner sc, Product product) {
		String current = "";
		while (true) {
			System.out.println("What would you like to know about this product?");
			System.out.println("\"name\", \"type\", \"amount\", \"cost\", or \"location\" (enter \"quit\" when done)");
			current = sc.nextLine();
			if (current.equals("name")) {
				System.out.println(product.getName());
			} else if (current.equals("type")) {
				System.out.println(product.getType());
			} else if (current.equals("amount")) {
				System.out.println(product.getAmount());
			} else if (current.equals("cost")) {
				System.out.println("$" + product.getCost());
			} else if (current.equals("location")) {
				System.out.println("(" + product.getRow() + ", " + product.getColumn() + ")");
			} else if (current.equals("quit")) {
				break;
			} else {
				System.out.println("Try another command:");
			}
		}
	}

	public static boolean checkBalance(int cost) {
		if (cost <= balance) {
			return true;
		} else {
			return false;
		}
	}

	public static void addProduct(Scanner sc) {
		Product product = new Product();
		String current = "";

		System.out.println("What's the name of the product?");
		while (true) { // name
			try {
				current = sc.nextLine();
				product.setName(current);
				break;
			} catch (Exception e) {
				System.out.println("Enter a string.");
			}
		}

		System.out.println("What type of product is it? (chips, candy, bakery, bar, or gum)");
		while (true) {
			try {
				current = sc.nextLine();
				product.setType(current);
				if (product.getType().equals("")) {
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Try again.");
			}
		}

		System.out.println("How many of this product are you putting in the vending machine?");
		while (true) {
			try {
				current = sc.nextLine();
				int amount = Integer.parseInt(current);
				product.setAmount(amount);
				if (product.getAmount() == -1) {
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Try again.");
			}
		}

		System.out.println("What is the cost of this product?");
		while (true) {
			try {
				current = sc.nextLine();
				int cost = Integer.parseInt(current);
				product.setCost(cost);
				if (product.getCost() == -1) {
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number from 0 to 20.");
			}
		}

		while (true) {
			System.out.println("What row of the vending machine will this go in? (A-F)");
			while (true) {
				try {
					current = sc.nextLine();
					product.setRow(current.charAt(0));
					if (product.getRow() == 0) {
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("Try again.");
				}
			}

			System.out.println("What column of the vending machine will this go in? (0-5)");
			while (true) {
				try {
					current = sc.nextLine();
					int column = Integer.parseInt(current);
					product.setColumn(column);
					if (product.getColumn() == -1) {
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid number from 0-5.");
				}
			}

			boolean inserted = insertProduct(product);
			if (inserted) {
				break;
			} else {
				System.out.println("Current location already has a product. Try again with another location.");
			}
		}

	}

	public static boolean insertProduct(Product product) {
		int row = product.getRow();
		int column = product.getColumn();

		if (!hasProduct(row, column)) { // no product in that location, so can place current one there
			if (row >= 65 && row <= 70) {
				row -= 65;
			} else if (row >= 97 && row <= 102) {
				row -= 97;
			}

			machine[row][column] = product;
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasProduct(int row, int column) {
		if (row >= 65 && row <= 70) {
			row -= 65;
		} else if (row >= 97 && row <= 102) {
			row -= 97;
		}

		if (machine[row][column] != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void printVendingMachine() {
		System.out.println("  0 1 2 3 4 5");
		for (int i = 0; i < machine.length; i++) { // column
			for (int j = 0; j < machine[i].length; j++) { // each row
				if (j == 0) {
					System.out.print("" + (char) (i + 65) + " ");
				}
				if (machine[i][j] != null) {
					System.out.print("" + machine[i][j].getAmount() + " ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to your new vending machine.");
		System.out.println("What would you like to do?");
		menu();
		String current = sc.nextLine();

		commands(sc, current);

		System.out.println("Goodbye!");

		sc.close();
	}
}
