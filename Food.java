import java.util.Comparator;
/**
 * @author 		   Qurrat-al-Ain Siddiqui
 * @date		   Sept 28, 2018
 *                 COMP 2503 - Programming 3
 *                 Assignment 1 - Frequency of Ingredients Using ArrayList
 *                 Instructor: Laura Marik
 *                 
 */
public class Food implements Comparable <Food>{
	private int frequency;
	private String foodName;

	/**
	 * compareTo method compares the frequency.
	 * 
	 * @return
	 */
	@Override
	public int compareTo(Food f) {
		if (this.getFrequency() < f.getFrequency())
			return 1;
		if (this.getFrequency() > f.getFrequency())
			return -1;
		else
			return 0;
	}

	/**
	 * Comparator to order the food item(s) in alphabetical order.
	 */
	public static Comparator<Food> NameComparator = new Comparator<Food>() {
		public int compare(Food f1, Food f2) {
			return f1.getFoodName().compareTo(f2.getFoodName());
		}
	};

	/**
	 * Comparator to order the food item(s) in their relative String lengths (largets to smallest)
	 * 
	 */
	public static Comparator<Food> LengthComparator = new Comparator<Food>() {
		public int compare(Food f1, Food f2) {
			if (f1.getFoodName().length() < f2.getFoodName().length())
				return 1;
			if (f1.getFoodName().length() > f2.getFoodName().length())
				return -1;
			else
				return 0;
		}
	};

	/**
	 * Default Constructor
	 */
	public Food() {
		frequency = 0;
		foodName = null;
	}

	/**
	 * Specific COnstrctor
	 * 
	 * @param name
	 */
	public Food(String name) {
		this.foodName = name;
		this.frequency = 0;
	}

	/**
	 * Specific constructor
	 * 
	 * @param name
	 * @param freq
	 */
	public Food(String name, int freq) {
		this.foodName = name;
		this.frequency = freq;
	}

	/**
	 * Accessor method for the frequency of ingredients
	 * 
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Accessor method for the food name
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * The mutator method for the food name
	 * 
	 * @param name
	 */
	public void setFoodName(String name) {
		foodName = name;
	}

	/**
	 * Adds 1 to frequency for each time a food item appears again
	 * 
	 * @param freq
	 */
	public void addFrequency() {
		this.frequency += 1;
	}

	/**
	 * The equals method.
	 * 
	 * @param f
	 * @return 
	 */
	public boolean equals(Food f) {
		boolean isEquals;
		if (f.getFoodName().equals(foodName))
			isEquals = true;
		else
			isEquals = false;

		return isEquals;
	}

}