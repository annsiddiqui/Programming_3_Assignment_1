import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;


/**
 * @author 		   Qurrat-al-Ain Siddiqui
 * @date		   Sept 28, 2018
 *                 COMP 2503 - Programming 3
 *                 Assignment 1 - Frequency of Ingredients Using ArrayList
 *                 Instructor: Laura Marik
 *                 
 */
public class A1 {
	private ArrayList<Food> foodList = new ArrayList<Food>();

	/**
	 * Instantiates the A1 object
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.run();
	}

	/**
	 * This method reads & processes all of the recipe books (txt files w/ the data).
	 * The method takes the ingredients of each food item(s) & adds
	 * The method also prints out the total amount of ingredients in the
	 * recipe book, the 10 most common foods (from highest frequency to least), and
	 * all the food item(s) in alphabetical order.
	 */
	public void run() {
		//Instance variables
		String name;
		int i = 0;
		boolean start = false;
		String dashes = "---";
		int index = 0;
		int total = 0;
		int totalFreq = 10;

		//The hard-coding of the BASIC FOOD LIST (from bottom of the assignment guidelines into a single ArrayList.
		basicFoodList();

		//Scanner 
		Scanner kbd = new Scanner(System.in);

		//Reads the recipe book
		while (kbd.hasNext()) {
			name = kbd.next();

			if(name.equals(dashes)) {
				start = true;
				kbd.nextLine();
			} else {
				kbd.nextLine();                 
			}

			while (start == true) {
				//i = 0;

				name = kbd.nextLine();

				if (name.equals(dashes)) {
					start = false;
				} else {
					// Trimming off any white space in the inputs.
					name = name.trim().toLowerCase();
					// Remove the plurals ("s" and "es" from end of food item(s)
					name = removeSAndEs(name);
					index = contains(name);

					// Adding it onto the ArrayList, or adding the frequency of the input.
					if (index == -1) {
						// Trim the inputted String to the food name
						name = trimName(name);
						// Adds the ingredient to the ArrayList
						foodList.add(new Food(name, 1));
						// Sorts from the longest to the shortest name length
						Collections.sort(foodList, Food.LengthComparator);
					} else {
						// Adds the frequency of the food item(s) in ArrayList
						foodList.get(index).addFrequency();
					}
				}
			}
		}

		// Finding the (new) food... Has to have at least a frequency of 1.
		for (Food food : foodList) {
			if (food.getFrequency() != 0) {
				total++;
			}
		}

		// Prints the total amount of all the different ingredients.
		System.out.println("The recipie book has " + total + " different ingredients.");
		System.out.println("----------------------------------------");

		// The total amount of the most to least frequent ingredients printed (10).
		if (total > totalFreq) {
			total = totalFreq;
		}

		// Sort & print the ArrayList from te MOST frequent food item(s) to the LEAST frequent food item(s)
		// Sorts alphabetically.
		Collections.sort(foodList, Food.NameComparator);
		//Sorts by MOST to LEAST frequent
		Collections.sort(foodList);
		System.out.println("Top" + total + " ingredients, from the most common to the least common.");
		for (Food food : foodList) {
			if (i < totalFreq) {
				if (food.getFrequency() != 0) {
					System.out.println("(" + food.getFoodName() + "," + food.getFrequency() + ")");
				}
			}
			i++;
		}

		// Sorts and prints the ArrayList alphabetically
		Collections.sort(foodList, Food.NameComparator);
		System.out.println("----------------------------------------");
		System.out.println("The complete list of foods in the recipie book, in alphabetical order.");
		for (Food food : foodList) {
			if (food.getFrequency() != 0) {
				System.out.println("(" + food.getFoodName() + "," + food.getFrequency() + ")");
			}
		}
	}

	/**
	 * This method trims off the first of the input off that is not part of the ingredients.
	 * Trims a one word ingredient
	 * 
	 * @param name
	 * @return
	 */
	public String trimName(String name) {
		name = name.substring(name.lastIndexOf(" ") + 1);

		return name;
	}

	/**
	 * This method trims off a two word ingredient.
	 * 
	 * @param name
	 * @return 
	 */

	public String trimTwoWordName(String name) {
		int first;
		int second;

		first = name.lastIndexOf(" ");
		second = name.lastIndexOf(" ", first - 1);
		name = name.substring(second + 1);

		return name;
	} 

	/**
	 * This method removes the plurals of ingredients by removing the "s" and "es" at the end of inpytted ingriednt names
	 * 
	 * @param name
	 * @return
	 */
	public String removeSAndEs(String name) {
		String endingS = "s";
		String endingEs = "e";
		String endingSS = "ss";
		int letterIndex = name.length() - 1;
		int twoS = name.length() - 2;
		String lastLetter;
		String secondLast;

		if (letterIndex != -1) {
			// Removes the "s"
			lastLetter = name.substring(letterIndex);
			secondLast =  name.substring(twoS);
			if (!secondLast.equals(endingSS)) {
				if(lastLetter.equals(endingS)) {
					name = name.substring(0, letterIndex);
					letterIndex = name.length() - 1;
					lastLetter = name.substring(letterIndex);
					//Removes the "e" from the "es"
					if (lastLetter.equals(endingEs)) {
						name = name.substring(0, letterIndex);
					}
				}

			}
		}
		return name;
	} 

	/**
	 * This method checks to see if the ArrayList contains the inputed ingredient name
	 * 
	 * @param name
	 * @return
	 */
	public int contains(String name) {
		int i;
		int index = -1;

		for (i = 0; i < foodList.size() && index == -1; i++) {
			if (name.contains(foodList.get(i).getFoodName()))
				index = i;
		}
		return index;
	}





	/**
	 * This method hard codes the basic food list from the bottom 
	 * of the assignment guidelines into the ArrayList
	 */
	public void basicFoodList(){
		foodList.add(new Food("baking powder"));
		foodList.add(new Food("baking soda"));
		foodList.add(new Food("cheese"));
		foodList.add(new Food("broth"));
		foodList.add(new Food("tomato paste"));
		foodList.add(new Food("tomato sauce"));
		foodList.add(new Food("tomato"));
		foodList.add(new Food("flour"));
		foodList.add(new Food("egg"));
		foodList.add(new Food("garlic"));
		foodList.add(new Food("rice"));
		foodList.add(new Food("onion"));
		foodList.add(new Food("salt"));
		foodList.add(new Food("pepper"));
		foodList.add(new Food("vinegar"));
		foodList.add(new Food("carrot"));
		foodList.add(new Food("sweet potato"));
		foodList.add(new Food("potato"));
		foodList.add(new Food("cream"));
		foodList.add(new Food("milk"));
		foodList.add(new Food("bean"));
		foodList.add(new Food("green bean"));
		foodList.add(new Food("beef"));
		foodList.add(new Food("chicken"));
		foodList.add(new Food("cumin"));
		foodList.add(new Food("basil"));
		foodList.add(new Food("oregano"));
		foodList.add(new Food("oil"));
		foodList.add(new Food("fish"));
		Collections.sort(foodList, Food.LengthComparator);
	}

}