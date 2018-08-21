package hangmanPackage;
import java.util.Random;
import java.util.Scanner;
public class Hangman {
	
	char[] guessedLetters = new char[26];
	String[] words = {"potato","fernando","dmc"};
	int chances = 10;
	int randomNumber = 0;
	String stichedWord = "" ;
	String randomWord = "";
	
	
	public static void main(String[] args) {
		Hangman game = new Hangman();
		game.InitializeGame();
		game.PlayGame();
	}
	
	boolean CheckExists(String guessLetter) {
		boolean alreadyGuessed = false;
		int endOfList = 0;
		for(int i = 0; i<guessedLetters.length;i++) {
			char registeredLetter = guessedLetters[i];
			char guessLetter_char = guessLetter.charAt(0);
			if(registeredLetter == guessLetter_char) {
				alreadyGuessed = true;
				break;
			}
			if(registeredLetter == '!') {
				endOfList = i;
				break;
			}
		}
		if(alreadyGuessed) {
			System.out.println("Already Guessed!");
			
		}
		else {
			guessedLetters[endOfList] = guessLetter.charAt(0);
		}
		
		return alreadyGuessed;
		
	}
	
	void CheckCorrectLetters(String guessLetter) {
		boolean guessedCorrectly = false;
		for(int i = 0; i < randomWord.length(); i++) {
			char randomWord_char = randomWord.charAt(i);
			char guessLetter_char = guessLetter.charAt(0);
			if(randomWord_char == guessLetter_char) {
				char[] brokenString = stichedWord.toCharArray();
				brokenString[i] = guessLetter_char;
				stichedWord = String.valueOf(brokenString);
				guessedCorrectly = true;
			}
			
		}
		if(guessedCorrectly) {
			System.out.println("Correct Guess");
			
		}
		else {
			System.out.println("Wrong");
			
		}
	}
	

	void InitializeGame() {
		
		Random rand = new Random();
		randomNumber = rand.nextInt(words.length) + 1;
		randomWord = words[randomNumber -1 ];
		
		
		for(int j = 0; j < randomWord.length(); j++) {
			stichedWord+="*";
		}
		
		for(int j = 0; j < 26; j++) {
			guessedLetters[j] = '!';
		}
		
	}
	void PlayGame() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You have " + String.valueOf(chances) +" tries left.");
		System.out.println("Word is length of: " + String.valueOf(randomWord.length()));
		System.out.println(stichedWord);
		boolean winGame = false;
		while(chances > 0) {
			System.out.println("Enter letter");
			
			String guessLetter = sc.nextLine();

			if(guessLetter.length() > 1) {
				System.out.println("Invalid Entry");
				continue;
			}
			boolean alreadyGuessed = CheckExists(guessLetter);
			if(alreadyGuessed) {
				continue;
			}
			
			CheckCorrectLetters(guessLetter);
			////////
			if(randomWord.equals(stichedWord)) {
				
				System.out.println("You Win");
				sc.close();
				winGame = true;
				break;
			}
			System.out.println(stichedWord);
			chances --;
			System.out.println("You have " + String.valueOf(chances) +" tries left.");
			
		}
		if(!winGame) {
			System.out.println("Game Over");
			System.out.println("The word was: " + randomWord);
		}
		
		
		
	}
	
}
