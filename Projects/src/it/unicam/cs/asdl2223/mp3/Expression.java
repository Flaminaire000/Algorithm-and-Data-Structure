package it.unicam.cs.asdl2223.mp3;

/**
 * An object of this class encodes an arithmetic expression with single-digit
 * integers and operators "+" and "*". Parentheses are not admitted.
 * 
 * @author Daniele Marchei and Luca Tesei
 *
 */
public class Expression {

    private final ExpressionItem[] items;

    private final String text;

    /**
     * Construct an expression from a string. The string must contain only
     * digits, operators "+" and "*", and white spaces. The string must start
     * with a digit and must end with a digit. Each operator must have a digit
     * on the right and a digit on the left. Two or more consecutive digits are
     * not admitted. An empty expresiion is not admitted.
     * 
     * @param text
     *                 The text of the expression
     * @throws NullPointerException
     *                                      if the text is null
     * @throws IllegalArgumentException
     *                                      if the format is not satisfied in
     *                                      the given text
     */
    public Expression(String text) {
        if (text == null)
            throw new NullPointerException("Expression con testo null");
        // Eliminate all the white spaces from the text
        this.text = text.replaceAll("\\s+", "");
        if (this.text.length() == 0)
            throw new IllegalArgumentException("Expression vuota");

        this.items = new ExpressionItem[this.text.length()];
        // Convert each character into a ExpressionItem
        for (int i = 0; i < text.length(); i++) {
            String c = text.charAt(i) + "";
            try {
                int val = Integer.parseInt(c);
                items[i] = new ExpressionItem(ItemType.DIGIT, val);
            } catch (NumberFormatException e) {
                if (!c.equals("*") && !c.equals("+"))
                    throw new IllegalArgumentException(
                            "Formato della espressione non valido");
                items[i] = new ExpressionItem(ItemType.OPERATOR, c);
            }
        }
        // Check that the formula starts with a digit
        if (this.items[0].getType() != ItemType.DIGIT)
            throw new IllegalArgumentException(
                    "L'espressione non inizia con una cifra");
        // Check that each digit is followed by an operator, but the last digit
        // Also check that each operatori is followed by a digit
        for (int i = 0; i < this.items.length - 1; i++) {
            if (this.items[i].getType() == ItemType.DIGIT
                    && this.items[i + 1].getType() != ItemType.OPERATOR)
                throw new IllegalArgumentException(
                        "Cifra non seguita da un operatore");
            if (this.items[i].getType() == ItemType.OPERATOR
                    && this.items[i + 1].getType() != ItemType.DIGIT)
                throw new IllegalArgumentException(
                        "Operatore non seguito da una cifra");
        }
        // Check that the formula ends with a digit
        if (this.items[this.items.length - 1].getType() != ItemType.DIGIT)
            throw new IllegalArgumentException(
                    "L'espressione non termina con una cifra");
    }

    /**
     * Get the item of the expression at the specified index.
     * 
     * @param index
     *                  the index
     * @return the item of the expression at the index
     */
    public ExpressionItem get(int index) {
        return items[index];
    }

    /**
     * Returns the number of items in this expression.
     * 
     * @return the number of items in this expression
     */
    public int size() {
        return items.length;
    }

    @Override
    public String toString() {
        return text;
    }

}
