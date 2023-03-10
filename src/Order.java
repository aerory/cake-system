import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {

		ArrayList<Cake> cakeArray = new ArrayList<>();
		try {
			readCake(cakeArray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int option = 0;
		display();
		option = checking(option);

		switch (option) {
		case 1:
			createOrder(cakeArray);
			break;
		case 2: // Update Order
			break;
		case 3: // View Billing
			break;
		case 4:
			searchOrder(cakeArray);
			break;
		case 5:
			System.out.println("see you next time");
			break;
		}
	}

	public static void readCake(ArrayList<Cake> cakeArray) throws FileNotFoundException {
		try {
			FileReader reader = new FileReader("src/designAssignment");
			BufferedReader br = new BufferedReader(reader);
			String line;
			int index = 0;

			while ((line = br.readLine()) != null) { // read line by line,
														// meaning after finished first line
														// only go to second line

				String[] cakeDetails = line.split(";"); // split c001;ChocolateCake;79.99
														// into 3 elements and put them into each
														// single variable
				String cakeId = cakeDetails[0];
				String cakeFlavor = cakeDetails[1];
				double price = Double.parseDouble(cakeDetails[2]);

				Cake cake = new Cake(cakeId, cakeFlavor, price); // assign them into a temperory cake object
				cakeArray.add(cake); // add cake object into the cake arrayList

				index++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error finding files");
		}
	}

	public static void display() {
		System.out.println("-----------------------------");
		System.out.println("\t  MENU");
		System.out.println("-----------------------------");
		System.out.println("[1] \t Order");
		System.out.println("[2] \t Update Order");
		System.out.println("[3] \t View Own Billing");
		System.out.println("[4] \t Search Cakes");
		System.out.println("[5] \t Exit");
	}

	public static void createOrder(ArrayList<Cake> cakeArray) {

		int choice = 1;
		int cakeCount = 0;
		Cake[] cakeCart = new Cake[10]; // one order maximum can have 10 cakes
		char resume;
		do {
			int count = 1;
			System.out.println("-----------------------------");
			System.out.println("\t  Order");
			System.out.println("-----------------------------");
			for (Cake c : cakeArray) {
				System.out.println("[ " + count + " ] \t" + c.getCake());
				count++;
			} // display every cakes from the cake arrayList
			System.out.println("[ 0 ]\t Exit");

			System.out.println("\n kindly enter the index ");
			Scanner input = new Scanner(System.in);
			boolean isValid = false;

			while (!isValid) {
				try {
					choice = Integer.parseInt(input.nextLine());
					isValid = true;
				} catch (NumberFormatException e) {
					System.out.println("Only numbers input are allowed!");
				}
			} // checking for valid inputs(integer)

			if (cancelOrder(choice)) { // cancel method to check whether the
										// input is 0 or not, if 0 then return true
										// when if statement is true will exit
				System.out.println("see you next time!");
				return;
			} // if user chose 0 as their input, system terminates

			cakeCart[cakeCount] = cakeArray.get(choice - 1); // get the specific cake
																// from cake arrayList
			cakeCount++;

			System.out.println("do you want anymore cakes?");
			resume = input.next().charAt(0);

		} while (resume == 'y'); // if input is y then will reloop again for user
									// to order again

		printOrder(cakeCart, cakeCount); // when printOrder method is executed meaning that user has already made
											// payment
		System.out.println("\t\t ====================");
		System.out.println("\t\t  see you next time!");
		System.out.println("\t\t ====================");

	}

	public static boolean cancelOrder(int choice) {
		if (choice == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void printOrder(Cake[] cakeCart, int cakeCount) {
		int numbers = 1;
		double total = 0;
		System.out.println("-----------------------------------------------------");
		System.out.println("\t\t     Your order");
		System.out.println("-----------------------------------------------------");
		System.out.println("  \t\t" + "Cakes\t" + "\t\tPrice");
		System.out.println("  \t\t" + "______\t" + "\t\t______");
		for (int i = 0; i < cakeCount; i++) {
			System.out.println(numbers + ".\t\t" + cakeCart[i].getCake() + "\t\t" + cakeCart[i].getPrice());
			total += cakeCart[i].getPrice();
			numbers++;
		}
		System.out.println("Total\t" + " \t\t\t\t" + df.format(total)); /* cant print out dont know why */
	}

	public static void searchOrder(ArrayList<Cake> cakeArray) {
		String search;
		String foundCake = null;
		
		Scanner find = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("\t Search order");
		System.out.println("-----------------------------");
		System.out.println("kindly enter the name of the cake: ");
		search = find.nextLine();

		for(Cake c : cakeArray) { //declare temperory Cake object 'c' to loop through the cakeArray ArrayList
			
			if(c.getCake().equals(search)) { //object 'c' get the cake name(type String) and use the equal method 
												//to match with the "search" String variable 
				System.out.println("Cake\t\t\t" + "Price");
				System.out.println("----\t\t\t" + "-----");
				System.out.println(c.getCake() + "\t\t" + c.getPrice());
			}else {
				System.out.println("item not found");
			}
		}
	}

	public static int checking(int choice) {
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		while (!isValid) {
			try {
				choice = Integer.parseInt(input.nextLine());
				isValid = true;
			} catch (NumberFormatException e) {
				System.out.println("Only numbers input are allowed!");
			}
		} // checking for valid inputs(integer)
		return choice;
	}

}
