package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	private SpellChecker checker;

	
	@BeforeEach
	public void testSetup() {
		checker = new SpellChecker();
	}
	
	@Test
	public void testNumberOfWords() {
		checker.addWord("cat");
		int actual_words = checker.getNumberOfWords();
		
		assertEquals(1, actual_words);
	}
	
	@Test
	public void wordAdded() {
		int before = checker.getNumberOfWords();
		checker.addWord("cat2");
		int actual = checker.getNumberOfWords();
		assertEquals(before + 1, actual);
	}
	
	@Test
	public void sameWordAdded() {
		SpellChecker test_checker = new SpellChecker();
		test_checker.addWord("cat");
		test_checker.addWord("cat");
		int actual = test_checker.getNumberOfWords();
		assertEquals(1, actual);
	}
	
	@Test
	public void correctlySpelled() {
		checker.addWord("dog");
		boolean spell_check = checkIsCorrect("dog");
		assertTrue(spell_check);
	}
	
	@Test
	public void incorrectlySpelled() {
		checker.addWord("dog");
		boolean spell_check = checkIsCorrect("dof");
		assertFalse(spell_check);
	}
	
	@Test
	public void caseInsensitive() {
		checker.addWord("cat");
		checker.addWord("caT");
		checker.addWord("Cat");
		checker.addWord("CaT");
		int actual = checker.getNumberOfWords();
		assertEquals(1, actual);
	}
	
	public void suggestCorrectWord() {
		checker.addWord("bank");
		checker.addWord("bamk");
		String correct = checker.suggestWord("bamk");
		assertEquals("bank", correct);
	}
	
	public void suggestSameWord() {
		checker.addWord("cat");
		String correct = checker.suggestWord("cat");
		assertEquals("cat", correct);
	}
	
	public void removeWord() {
		int before = checker.getNumberOfWords();
		checker.addWord("cat");
		checker.removeWord("cat");
		int actual = checker.getNumberOfWords();
		assertEquals(before - 1, actual);
	}
	
	public void falseIfWordNotPresent() {
		checker.add("dog");
		assertFalse(checker.removeWord("cat"));
	}
	
	
}
