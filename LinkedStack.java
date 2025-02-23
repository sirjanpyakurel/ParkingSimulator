/*<listing chapter="4" number="5">*/

import java.util.NoSuchElementException;

/** Class to implement interface StackInt as a linked list.
 *  @param <E> The type of elements in the stack
 *  @author Koffman & Wolfgang
 **/
public class LinkedStack<E> implements StackInt<E> {

    // Insert inner class Node<E> here (see Listing 2.1)
    /** A Node is the building block for a single-linked list. */
    private static class Node<E> {
        // Data Fields

        /** The reference to the data. */
        private final E data;
        /** The reference to the next node. */
        private final Node<E> next;

        // Constructors
        /**
         * Creates a new node with a null next field.
         * @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /**
         * Creates a new node that references another node.
         * @param dataItem The data stored
         * @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }
    //End of inserted class
    // Data Fields
    /** The reference to the first stack node. */
    private Node<E> topOfStackRef = null;

    /**
     * Insert a new item on top of the stack.
     * @post The new item is the top item on the stack.
     *       All other items are one position lower.
     * @param obj The item to be inserted
     * @return The item that was inserted
     */
    @Override
    public E push(E obj) {
        topOfStackRef = new Node<>(obj, topOfStackRef);
        return obj;
    }

    /**
     * Remove and return the top item on the stack.
     * @pre The stack is not empty.
     * @post The top item on the stack has been
     *       removed and the stack is one item smaller.
     * @return The top item on the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() {
        if (empty()) {
            throw new NoSuchElementException();
        } else {
            E result = topOfStackRef.data;
            topOfStackRef = topOfStackRef.next;
            return result;
        }
    }

    /**
     * Return the top item on the stack.
     * @pre The stack is not empty.
     * @post The stack remains unchanged.
     * @return The top item on the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() {
        if (empty()) {
            throw new NoSuchElementException();
        } else {
            return topOfStackRef.data;
        }
    }

    /**
     * See whether the stack is empty.
     * @return true if the stack is empty
     */
    @Override
    public boolean empty() {
        return topOfStackRef == null;
    }
    
    /**
     * Returns a String representation of the stack contents
     * @return a String with the Stack elements, with top as the first element
     */
    @Override
    public String toString() {
        String result = "[";
        Node<E> temp = topOfStackRef;
        while (temp != null)
        {
            result += temp.data + ", ";
            temp = temp.next;
        }
        int lastComma = result.lastIndexOf(",");
        if (lastComma >= 0)                            // if not empty
            return result.substring(0, lastComma) + "]";
        return "[]";
    }
    
    public static void main(String [] args)
    {
         StackInt<Integer> st = new LinkedStack<>();
         System.out.println(st);
         st.push(34);
         System.out.println(st);
         st.push(43);
         st.push(33);
         System.out.println(st);
     }
}
/*</listing>*/
