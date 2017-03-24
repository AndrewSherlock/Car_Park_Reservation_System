
public class ValidationUtil {
	
	/**
	 * This method loops through characters of string and makes sure that
	 * @param text check if we have only entered letters
	 * @return either continues to next iteration or breaks out of the loop
	 */
	public static boolean checkAllLetters(String text) 
	{
		for(int i = 0; i < text.length(); i++) // loops through every character of the string
		{
			char character = text.charAt(i);
			if(Character.isLetter(character)) // checks if letter
			{
				continue; // continue to the next iteration if so
			} else {
				return false; // else we want to go no futher we break out of the loop and return false
			}
		} 
		return true;
	}
	
	/**
	 * This method is a boolean and checks that our string is between a certain length
	 * @param text  takes the string value
	 * @param minLength and makes sure its above the minLength
	 * @param maxLength and makes sure its below the maxLength
	 * @return a boolean depending on inputs
	 */
	public static boolean isEntryLongEnough(String text, int minLength, int maxLength) // ensure our string is between a certain length
	{
		return	(text.length() >= minLength && text.length() <= maxLength); //returns true or false depending
	}
	
	/**
	 * This method makes sure there is at least 1 capital for the password
	 * @param text takes in the password
	 * @return if it doesnt return true, we have a password that has a capital and lower case
	 */
	public static boolean atLeastOneCap(String text) // method to check if theres at least one capital in the string
	{
		if(!text.equals(text.toLowerCase()) && !text.equals(text.toUpperCase())){ // checks that the string does not match all lower case or all higher case,
			return true; 
		}
		return false; // else false
	}
	
	/**
	 * This method checks that the phone number is all numbers
	 * @param text takes in a number and makes sure it is a number
	 * @return checks that we have matching criteria
	 */
	public static boolean isAllNumberic(String text) // this method checks if we have entered all numbers, similar to the letter method, loops through and
	{													// checks each character, if we have a digit, we continue to the next iteration, else we break out of the loop
		for(int i = 0; i < text.length(); i++)			// as we do not have matchin criteria for the string
		{
			char character = text.charAt(i);
			if(Character.isDigit(character)){
				continue;
			} else{
				return false;
			}
		}
		return true;
	}
}
