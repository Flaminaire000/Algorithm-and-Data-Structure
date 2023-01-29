package it.unicam.cs.asdl2223.mp3;

/**
 * A simple class for representing a possible item of an expression. An item as a
 * type and a value.
 * 
 * @author Daniele Marchei, Luca Tesei
 *
 */
public class ExpressionItem {
    private final ItemType type;
    private final Object value;

    public ExpressionItem(ItemType type, Object content) {
        if (type == null || content == null)
            throw new NullPointerException("Item null in un ExpressionItem");
        this.type = type;
        this.value = content;
    }

    public ItemType getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
