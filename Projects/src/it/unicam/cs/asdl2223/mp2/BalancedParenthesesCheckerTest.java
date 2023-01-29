package it.unicam.cs.asdl2223.mp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BalancedParenthesesCheckerTest {

    @Test
    final void testCheck1() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertTrue(checker.check(""));
        assertTrue(checker.check("  \t"));
        assertTrue(checker.check(" \n\n\n\n\n\n    \n\t \t  "));
    }

    @Test
    final void testCheck2() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertThrows(IllegalArgumentException.class,
                () -> checker.check("( ( \n [ ( P )] \t ))"));
        assertThrows(IllegalArgumentException.class,
                () -> checker.check(" \tPippo\n"));
    }

    @Test
    final void testCheck3() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertTrue(checker.check("()"));
        assertTrue(checker.check(" (( [ \n  ( {\t ( \t) [   ] } ) \n ] ) ) "));
    }

    @Test
    final void testCheck4() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertFalse(checker.check("(]"));
        assertFalse(checker.check(")("));
        assertFalse(checker.check("( ([ ) ]) "));
        assertFalse(checker.check("( { } "));
        assertFalse(checker.check("{ (  ) "));
        assertFalse(checker.check("( }(([])))"));
        assertFalse(checker.check(" } (([]))"));
        assertFalse(checker.check(" (\t ) } (([]))"));
    }

}
