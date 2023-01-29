package it.unicam.cs.asdl2223.mp3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Classe di test per la classe PrimMSP.
 * 
 * @author Luca Tesei
 *
 */
class PrimMSPTest {

    @Test
    final void testExceptions() {
        PrimMSP<String> alg = new PrimMSP<String>();
        assertThrows(NullPointerException.class,
                () -> alg.computeMSP(null, null));
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        assertThrows(NullPointerException.class,
                () -> alg.computeMSP(gr, null));
        assertThrows(NullPointerException.class, () -> alg.computeMSP(null, a));
        GraphNode<String> b = new GraphNode<String>("b");
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode(e);
        GraphNode<String> f = new GraphNode<String>("f");
        gr.addNode(f);
        GraphNode<String> g = new GraphNode<String>("g");
        gr.addNode(g);
        GraphNode<String> h = new GraphNode<String>("h");
        gr.addNode(h);
        GraphNode<String> i = new GraphNode<String>("i");
        gr.addNode(i);
        gr.addEdge(new GraphEdge<String>(a, b, false, 4));
        gr.addEdge(new GraphEdge<String>(a, h, false, 8.5));
        gr.addEdge(new GraphEdge<String>(b, h, false, 11));
        gr.addEdge(new GraphEdge<String>(b, c, false, 8));
        gr.addEdge(new GraphEdge<String>(c, i, false, 2));
        gr.addEdge(new GraphEdge<String>(c, d, false, 7));
        gr.addEdge(new GraphEdge<String>(c, f, false, 4));
        gr.addEdge(new GraphEdge<String>(d, f, false, 14));
        gr.addEdge(new GraphEdge<String>(d, e, false));
        gr.addEdge(new GraphEdge<String>(e, f, false, 10));
        gr.addEdge(new GraphEdge<String>(f, g, false, 2));
        gr.addEdge(new GraphEdge<String>(g, i, false, 6));
        gr.addEdge(new GraphEdge<String>(g, h, false, 1));
        gr.addEdge(new GraphEdge<String>(h, i, false, 7));
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
        gr.clear();
        gr.addNode(a);
        gr.addNode(b);
        gr.addNode(c);
        gr.addNode(d);
        gr.addNode(e);
        gr.addNode(f);
        gr.addNode(g);
        gr.addNode(h);
        gr.addNode(i);
        gr.addEdge(new GraphEdge<String>(a, b, false, 4));
        gr.addEdge(new GraphEdge<String>(a, h, false, 8.5));
        gr.addEdge(new GraphEdge<String>(b, h, false, 11));
        gr.addEdge(new GraphEdge<String>(b, c, false, 8));
        gr.addEdge(new GraphEdge<String>(c, i, false, 2));
        gr.addEdge(new GraphEdge<String>(c, d, false, 7));
        gr.addEdge(new GraphEdge<String>(c, f, false, 4));
        gr.addEdge(new GraphEdge<String>(d, f, false, 14));
        gr.addEdge(new GraphEdge<String>(d, e, false, 9));
        gr.addEdge(new GraphEdge<String>(e, f, false, 10));
        gr.addEdge(new GraphEdge<String>(f, g, false, 2));
        gr.addEdge(new GraphEdge<String>(g, i, false, -6));
        gr.addEdge(new GraphEdge<String>(g, h, false, 1));
        gr.addEdge(new GraphEdge<String>(h, i, false, 7));
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
    }

    @Test
    final void testFindMSP1() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode(e);
        GraphNode<String> f = new GraphNode<String>("f");
        gr.addNode(f);
        GraphNode<String> g = new GraphNode<String>("g");
        gr.addNode(g);
        GraphNode<String> h = new GraphNode<String>("h");
        gr.addNode(h);
        GraphNode<String> i = new GraphNode<String>("i");
        gr.addNode(i);
        gr.addEdge(new GraphEdge<String>(a, b, false, 4));
        gr.addEdge(new GraphEdge<String>(a, h, false, 8.5));
        gr.addEdge(new GraphEdge<String>(b, h, false, 11));
        gr.addEdge(new GraphEdge<String>(b, c, false, 8));
        gr.addEdge(new GraphEdge<String>(c, i, false, 2));
        gr.addEdge(new GraphEdge<String>(c, d, false, 7));
        gr.addEdge(new GraphEdge<String>(c, f, false, 4));
        gr.addEdge(new GraphEdge<String>(d, f, false, 14));
        gr.addEdge(new GraphEdge<String>(d, e, false, 9));
        gr.addEdge(new GraphEdge<String>(e, f, false, 10));
        gr.addEdge(new GraphEdge<String>(f, g, false, 2));
        gr.addEdge(new GraphEdge<String>(g, i, false, 6));
        gr.addEdge(new GraphEdge<String>(g, h, false, 1));
        gr.addEdge(new GraphEdge<String>(h, i, false, 7));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == c);
        assertTrue(e.getPrevious() == d);
        assertTrue(f.getPrevious() == c);
        assertTrue(g.getPrevious() == f);
        assertTrue(h.getPrevious() == g);
        assertTrue(i.getPrevious() == c);
    }

    @Test
    final void testFindMSP2() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP3() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, b);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == null);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP4() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, c);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == c);
        assertTrue(c.getPrevious() == null);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP5() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        gr.addEdge(new GraphEdge<String>(a, b, false, 1));
        gr.addEdge(new GraphEdge<String>(a, c, false, 5));
        gr.addEdge(new GraphEdge<String>(b, d, false, 2));
        gr.addEdge(new GraphEdge<String>(b, c, false, 3));
        gr.addEdge(new GraphEdge<String>(c, d, false, 4));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, d);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == d);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == null);
    }

    @Test
    final void testFindMSP6() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode(e);
        gr.addEdge(new GraphEdge<String>(a, c, false, 3));
        gr.addEdge(new GraphEdge<String>(b, c, false, 10));
        gr.addEdge(new GraphEdge<String>(c, d, false, 2));
        gr.addEdge(new GraphEdge<String>(c, e, false, 6));
        gr.addEdge(new GraphEdge<String>(b, d, false, 4));
        gr.addEdge(new GraphEdge<String>(d, e, false, 1));
        gr.addEdge(new GraphEdge<String>(b, b, false, 7));
        gr.addEdge(new GraphEdge<String>(c, c, false, 0));
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, b);
        assertTrue(a.getPrevious() == c);
        assertTrue(a.getFloatingPointDistance() == 3.0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == null);
        assertTrue(b.getFloatingPointDistance() == 0);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(c.getPrevious() == d);
        assertTrue(c.getFloatingPointDistance() == 2.0);
        assertTrue(c.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(d.getPrevious() == b);
        assertTrue(d.getFloatingPointDistance() == 4.0);
        assertTrue(d.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(e.getPrevious() == d);
        assertTrue(e.getFloatingPointDistance() == 1.0);
        assertTrue(e.getColor() == GraphNode.COLOR_BLACK);
    }

    @Test
    final void testFindMSP7() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        PrimMSP<String> alg = new PrimMSP<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == null);
        assertTrue(b.getFloatingPointDistance() == Double.POSITIVE_INFINITY);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        gr.addEdge(new GraphEdge<String>(a, b, false, 3));
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == a);
        assertTrue(b.getFloatingPointDistance() == 3);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(c.getPrevious() == null);
        assertTrue(c.getFloatingPointDistance() == Double.POSITIVE_INFINITY);
        assertTrue(c.getColor() == GraphNode.COLOR_BLACK);
    }
}
