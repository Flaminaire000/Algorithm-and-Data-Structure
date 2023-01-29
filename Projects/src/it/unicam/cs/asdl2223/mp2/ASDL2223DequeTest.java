package it.unicam.cs.asdl2223.mp2;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ASDL2223DequeTest {

    @Test
    final void testASDL2021Deque() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        assertTrue(dq.isEmpty());
        assertTrue(dq.size()==0);
        assertTrue(dq.getFirstNode() == null);
        assertTrue(dq.getLastNode() == null);
    }

    @Test
    final void testIsEmpty() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        assertTrue(dq.isEmpty());
        dq.add("Pippo");
        assertFalse(dq.isEmpty());
        dq.remove();
        assertTrue(dq.isEmpty());
    }

    @Test
    final void testToArray() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        Object[] as = dq.toArray();
        assertTrue(as.length == dq.size());
        assertThrows (IndexOutOfBoundsException.class, () -> {@SuppressWarnings("unused")
        Object p = as[0];});
        dq.add("Pippo");
        dq.add("Pluto");
        Object[] as1 = dq.toArray();
        assertTrue(as1.length == 2);
        assertTrue(as1[0].equals("Pippo"));
        assertTrue(as1[1].equals("Pluto"));  
        assertThrows (IndexOutOfBoundsException.class, () -> {@SuppressWarnings("unused")
            Object p = as[2];});
    }


    @Test
    final void testContainsAll() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        dq.add("Pluto");
        dq.add("Paperino");
        dq.add("Pippo");
        dq.add("Paperone");
        dq.add("Qui");
        dq.add("Minnie");
        dq.add("Topolino");
        dq.add("Quo");
        dq.add("Qua");
        Set<String> paperi = new HashSet<String>();
        paperi.add("Qui");
        paperi.add("Paperino");
        paperi.add("Qua");
        paperi.add("Paperone");
        paperi.add("Quo");    
        assertTrue(dq.containsAll(paperi));
        List<String> l = new ArrayList<String>();
        l.add(null);
        assertThrows (NullPointerException.class, () -> dq.containsAll(l));
        assertThrows (NullPointerException.class, () -> dq.containsAll(null));
    }

    @Test
    final void testAddAll() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        dq.add("Pluto");
        dq.add("Pippo");
        dq.add("Minnie");
        dq.add("Topolino");
        List<String> paperi = new ArrayList<String>();
        paperi.add("Qui");
        paperi.add("Paperino");
        paperi.add("Qua");
        paperi.add("Paperone");
        paperi.add("Quo");
        dq.addAll(paperi);
        ASDL2223Deque<String> dq1 = new ASDL2223Deque<String>();
        dq1.add("Pluto");
        dq1.add("Paperino");
        dq1.add("Pippo");
        dq1.add("Paperone");
        dq1.add("Qui");
        dq1.add("Minnie");
        dq1.add("Topolino");
        dq1.add("Quo");
        dq1.add("Qua");
        assertTrue(dq.containsAll(dq1));
        List<String> l = new ArrayList<String>();
        l.add(null);
        assertThrows (NullPointerException.class, () -> dq.addAll(l));
        assertThrows (NullPointerException.class, () -> dq.addAll(null));
    }

    @Test
    final void testClear() {
        ASDL2223Deque<String> dq = new ASDL2223Deque<String>();
        dq.add("Pluto");
        dq.add("Pippo");
        dq.add("Minnie");
        dq.add("Topolino");
        dq.clear();
        assertTrue(dq.isEmpty());
        assertTrue(dq.size()==0);
        assertTrue(dq.getFirstNode() == null);
        assertTrue(dq.getLastNode() == null);
    }

    @Test
    final void testAddFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.addFirst(null));
        d.addFirst("Pippo");
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pippo"));
        assertTrue(d.getFirstNode().next == null);
        assertTrue(d.getLastNode() == d.getFirstNode());
        d.addFirst("Pluto");
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pluto"));
        assertTrue(d.getFirstNode().next == d.getLastNode());
        assertTrue(d.getLastNode().prev == d.getFirstNode());
        assertTrue(d.getLastNode().item.equals("Pippo"));
        assertTrue(d.getLastNode().next == null);
        d.addFirst("Minnie");
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        assertTrue(first.item.equals("Minnie"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(first.next.next.item.equals("Pippo"));
    }

    @Test
    final void testAddLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.addLast(null));
        d.addLast("Pippo");
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pippo"));
        assertTrue(d.getFirstNode().next == null);
        assertTrue(d.getLastNode() == d.getFirstNode());
        d.addLast("Pluto");
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first.item.equals("Pippo"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(last.prev == first);
        assertTrue(first.next == last);
        assertTrue(last.next == null);
    }

    @Test
    final void testOfferFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.offerFirst(null));
        assertTrue(d.offerFirst("Pippo"));
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pippo"));
        assertTrue(d.getFirstNode().next == null);
        assertTrue(d.getLastNode() == d.getFirstNode());
        assertTrue(d.offerFirst("Pluto"));
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pluto"));
        assertTrue(d.getFirstNode().next == d.getLastNode());
        assertTrue(d.getLastNode().prev == d.getFirstNode());
        assertTrue(d.getLastNode().item.equals("Pippo"));
        assertTrue(d.getLastNode().next == null);
        assertTrue(d.offerFirst("Minnie"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        assertTrue(first.item.equals("Minnie"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(first.next.next.item.equals("Pippo"));
        assertTrue(d.offerFirst("Topolino"));
        first = d.getFirstNode();
        assertTrue(first.item.equals("Topolino"));
        assertTrue(first.next.item.equals("Minnie"));
        assertTrue(first.next.prev == first);
        assertTrue(first.next.next.item.equals("Pluto"));
        assertTrue(first.next.next.prev.item.equals("Minnie"));
    }

    @Test
    final void testOfferLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.offerLast(null));
        assertTrue(d.offerLast("Pippo"));
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pippo"));
        assertTrue(d.getFirstNode().next == null);
        assertTrue(d.getLastNode() == d.getFirstNode());
        assertTrue(d.offerLast("Pluto"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first.item.equals("Pippo"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(last.prev == first);
        assertTrue(first.next == last);
        assertTrue(last.next == null);
        d.addFirst("Minnie");;
        d.removeLast();
        assertTrue(d.offerLast("Topolino"));
        first = d.getFirstNode();
        assertTrue(first.item.equals("Minnie"));
        assertTrue(first.next.item.equals("Pippo"));
        assertTrue(first.next.next.item.equals("Topolino"));
        last = d.getLastNode();
        assertTrue(last.item.equals("Topolino"));
        assertTrue(last.prev.item.equals("Pippo"));
        assertTrue(last.prev.prev.item.equals("Minnie"));
    }

    @Test
    final void testRemoveFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows(NoSuchElementException.class, () -> d.removeFirst());
        d.add("Pippo");
        String s = d.removeFirst();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == null);
        assertTrue(last == null);
        d.add("Pippo");
        d.offerFirst("Pluto");
        d.offerLast("Minnie");
        assertTrue(d.removeFirst().equals("Pluto"));
        first = d.getFirstNode();
        assertTrue(first.item.equals("Pippo"));
        assertTrue(d.removeFirst().equals("Pippo"));
        assertTrue(d.removeFirst().equals("Minnie"));
        assertTrue(d.isEmpty());
    }

    @Test
    final void testRemoveLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows(NoSuchElementException.class, () -> d.removeLast());
        d.add("Pippo");
        String s = d.removeLast();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == null);
        assertTrue(last == null);
        d.addFirst("Pippo");
        d.addLast("Topolino");
        d.offerFirst("Pluto");
        d.offerLast("Minnie");
        d.addFirst("Paperino");
        assertTrue(d.removeLast().equals("Minnie"));
        last = d.getLastNode();
        assertTrue(last.item.equals("Topolino"));
        first = d.getFirstNode();
        assertTrue(first.item.equals("Paperino"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(d.removeLast().equals("Topolino"));
        assertTrue(d.removeLast().equals("Pippo"));
        first = d.getFirstNode();
        last = d.getLastNode();
        assertTrue(d.size() == 2);
        assertTrue(last.item.equals("Pluto"));
        assertTrue(last.prev == first);
    }

    @Test
    final void testPollFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.pollFirst() == null);
        d.addLast("Pippo");
        String s = d.pollFirst();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == null);
        assertTrue(last == null);
        d.addLast("Pippo");
        d.offerFirst("Pluto");
        d.offerFirst("Minnie");
        d.offerLast("Topolino");
        assertTrue(d.pollFirst().equals("Minnie"));
        first = d.getFirstNode();
        assertTrue(first.item.equals("Pluto"));
        assertTrue(d.pollFirst().equals("Pluto"));
        assertTrue(d.pollFirst().equals("Pippo"));
        assertTrue(d.pollFirst().equals("Topolino"));
        assertTrue(d.isEmpty());
    }

    @Test
    final void testPollLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.pollLast() == null);
        d.addLast("Pippo");
        String s = d.pollLast();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == null);
        assertTrue(last == null);
        d.addFirst("Pippo");
        d.offerFirst("Pluto");
        d.offerLast("Minnie");
        d.offerLast("Topolino");
        assertTrue(d.pollLast().equals("Topolino"));
        last = d.getLastNode();
        assertTrue(last.item.equals("Minnie"));
        assertTrue(last.next == null);
        assertTrue(last.prev.item.equals("Pippo"));
        d.removeLast();
        assertTrue(d.pollFirst().equals("Pluto"));
        assertTrue(d.pollLast().equals("Pippo"));
        assertTrue(d.isEmpty());
    }

    @Test
    final void testGetFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows(NoSuchElementException.class, () -> d.getFirst());
        d.add("Pippo");
        String s = d.getFirst();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == last);
        d.addLast("Pippo");
        d.offerFirst("Pluto");
        d.offerLast("Minnie");
        assertTrue(d.getFirst().equals("Pluto"));
        first = d.getFirstNode();
        assertTrue(first.next.item.equals("Pippo"));
        assertTrue(d.removeFirst().equals("Pluto"));
        assertTrue(d.removeLast().equals("Minnie"));
        assertTrue(d.remove().equals("Pippo"));
        assertTrue(d.getFirst().equals("Pippo"));
    }

    @Test
    final void testGetLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows(NoSuchElementException.class, () -> d.getLast());
        d.add("Pippo");
        String s = d.getLast();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == last);
        d.addLast("Pippo");
        d.offerFirst("Pluto");
        assertTrue(d.getLast().equals("Pippo"));
        first = d.getFirstNode();
        assertTrue(first.next.item.equals("Pippo"));
        assertTrue(first.item.equals("Pluto"));
        assertTrue(d.removeFirst().equals("Pluto"));
    }

    @Test
    final void testPeekFirst() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.peekFirst() == null);
        d.add("Pippo");
        String s = d.peekFirst();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == last);
        d.addLast("Pippo");
        d.offerFirst("Pluto");
        d.offerLast("Minnie");
        assertTrue(d.peekFirst().equals("Pluto"));
        first = d.getFirstNode();
        assertTrue(first.next.item.equals("Pippo"));
        assertTrue(d.removeFirst().equals("Pluto"));
        assertTrue(d.removeLast().equals("Minnie"));
        assertTrue(d.remove().equals("Pippo"));
        assertTrue(d.peekFirst().equals("Pippo"));
    }

    @Test
    final void testPeekLast() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.peekLast() == null);
        d.add("Pippo");
        String s = d.peekLast();
        assertTrue(s.equals("Pippo"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first == last);
        d.addLast("Pippo");
        d.offerFirst("Pluto");
        assertTrue(d.peekLast().equals("Pippo"));
        first = d.getFirstNode();
        assertTrue(first.next.item.equals("Pippo"));
        assertTrue(first.item.equals("Pluto"));
        assertTrue(d.removeFirst().equals("Pluto"));
    }

    @Test
    final void testAdd() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.add(null));
        assertTrue(d.add("Pippo"));
        assertTrue(d.getFirstNode().prev == null);
        assertTrue(d.getFirstNode().item.equals("Pippo"));
        assertTrue(d.getFirstNode().next == null);
        assertTrue(d.getLastNode() == d.getFirstNode());
        assertTrue(d.add("Pluto"));
        ASDL2223Deque.Node<String> first = d.getFirstNode();
        ASDL2223Deque.Node<String> last = d.getLastNode();
        assertTrue(first.item.equals("Pippo"));
        assertTrue(first.next.item.equals("Pluto"));
        assertTrue(last.prev == first);
        assertTrue(first.next == last);
        assertTrue(last.next == null);
    }

    @Test
    final void testOffer() {
        testAdd();
    }

    @Test
    final void testRemove() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NoSuchElementException.class, () -> d.remove());
        d.add("Pippo");
        assertTrue(d.remove().equals("Pippo"));
        assertTrue(d.size()== 0);
        d.add("Pippo");
        d.add("Pluto");
        d.add("Minnie");
        assertTrue(d.remove().equals("Pippo"));
        assertTrue(d.remove().equals("Pluto"));
        assertTrue(d.getFirstNode() == d.getLastNode());
        assertTrue(d.remove().equals("Minnie"));
        assertTrue(d.getFirstNode() == null);
    }

    @Test
    final void testPoll() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.poll() == null);
        d.add("Pippo");
        assertTrue(d.poll().equals("Pippo"));
        assertTrue(d.isEmpty());
        d.addLast("Pippo");
        d.addFirst("Pluto");
        d.offerLast("Minnie");
        assertTrue(d.poll().equals("Pluto"));
        assertTrue(d.poll().equals("Pippo"));
        assertTrue(d.getFirstNode() == d.getLastNode());
        assertTrue(d.poll().equals("Minnie"));
        assertTrue(d.getFirstNode() == null);
    }

    @Test
    final void testElement() {
        testGetFirst();
    }

    @Test
    final void testPeek() {
        testPeekFirst();
    }

    @Test
    final void testPush() {
        testAddFirst();
    }

    @Test
    final void testPop() {
        testRemoveFirst();
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testRemoveObject() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.remove(null));
        d.add("Pippo");
        d.add("Pluto");
        d.add("Minnie");
        d.add("Pippo");
        assertFalse(d.remove(new ArrayList<String>()));
        assertTrue(d.remove("Pippo"));
        d.getFirstNode().item.equals("Pluto");
        d.getLastNode().item.equals("Pippo");
        //System.out.print(d.remove("Pippo"));
        assertTrue(d.remove("Pippo"));
        d.getFirstNode().item.equals("Pluto");
        d.getLastNode().item.equals("Minnie");
        assertFalse(d.remove("Pippo"));
        assertTrue(d.remove("Minnie"));
        assertTrue(d.size() == 1);
        assertTrue(d.remove("Pluto"));
        //System.out.print(d.remove("Topolino"));
        assertFalse(d.remove("Topolino"));
        assertTrue(d.isEmpty());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testContains() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertThrows (NullPointerException.class, () -> d.contains(null));
        d.add("Pippo");
        d.add("Pluto");
        d.add("Minnie");
        d.add("Pippo");
        assertFalse(d.contains(new ArrayList<String>()));
        assertTrue(d.contains("Pippo"));
        assertTrue(d.remove("Pippo"));
        assertTrue(d.contains("Pippo"));
        assertTrue(d.remove("Pippo"));
        assertFalse(d.contains("Pippo"));
        assertFalse(d.contains("Topolino"));
        d.clear();
        assertTrue(d.isEmpty());
        assertFalse(d.contains("Pippo"));
    }

    @Test
    final void testSize() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        assertTrue(d.size() == 0);
        d.add("Pippo");
        assertTrue(d.size() == 1);
        d.add("Pluto");
        assertTrue(d.size() == 2);
        d.add("Minnie");
        assertTrue(d.size() == 3);
        d.remove();
        assertTrue(d.size() == 2);
        d.remove();
        assertTrue(d.size() == 1);
        d.remove();
        assertTrue(d.size() == 0);
    }

    @Test
    final void testIterator() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        d.add("Pippo");
        d.add("Pluto");
        d.add("Minnie");
        d.add("Pippo");
        Iterator<String> it = d.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.next().equals("Pippo"));
        assertTrue(it.next().equals("Pluto"));
        d.add("Topolino");
        assertThrows(ConcurrentModificationException.class, () -> it.next());
        Iterator<String> it1 = d.iterator();
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Pippo"));
        assertTrue(it1.next().equals("Pluto"));
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Minnie"));
        assertTrue(it1.next().equals("Pippo"));
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Topolino"));
        assertFalse(it1.hasNext());
        assertThrows(NoSuchElementException.class, () -> it1.next());
        d.clear();
        Iterator<String> it2 = d.iterator();
        assertFalse(it2.hasNext());
        assertThrows(NoSuchElementException.class, () -> it2.next());
        d.clear();
        Iterator<String> it3 = d.iterator();
        assertFalse(it3.hasNext());
        d.add("Pippo");
        assertThrows(ConcurrentModificationException.class, () -> it3.next());
        d.add("Pluto");
        d.add("Minnie");
        int i = 0;
        for(String s: d) {
            s.charAt(0);
            i++;
        }
        assertTrue(i==3);
    }

    @Test
    final void testDescendingIterator() {
        ASDL2223Deque<String> d = new ASDL2223Deque<String>();
        d.addFirst("Pippo");
        d.addFirst("Pluto");
        d.addFirst("Minnie");
        d.addFirst("Pippo");
        Iterator<String> it = d.descendingIterator();
        assertTrue(it.hasNext());
        assertTrue(it.next().equals("Pippo"));
        assertTrue(it.next().equals("Pluto"));
        d.addFirst("Topolino");
        assertThrows(ConcurrentModificationException.class, () -> it.next());
        Iterator<String> it1 = d.descendingIterator();
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Pippo"));
        assertTrue(it1.next().equals("Pluto"));
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Minnie"));
        assertTrue(it1.next().equals("Pippo"));
        assertTrue(it1.hasNext());
        assertTrue(it1.next().equals("Topolino"));
        assertFalse(it1.hasNext());
        assertThrows(NoSuchElementException.class, () -> it1.next());
        d.clear();
        Iterator<String> it2 = d.descendingIterator();
        assertFalse(it2.hasNext());
        assertThrows(NoSuchElementException.class, () -> it2.next());
        d.clear();
        Iterator<String> it3 = d.descendingIterator();
        assertFalse(it3.hasNext());
        d.addFirst("Pippo");
        assertThrows(ConcurrentModificationException.class, () -> it3.next());
    }

}
