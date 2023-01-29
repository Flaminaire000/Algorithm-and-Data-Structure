package it.unicam.cs.asdl2223.mp3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Classe di test per la classe AdjacencyMatrixUndirectedGraph.
 * 
 * @author Luca Tesei
 */
class AdjacencyMatrixUndirectedGraphTest {

    @Test
    final void testAdjacencyMatrixUndirectedGraph() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testNodeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<String>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<String>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testSize() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.size() == 0);
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.size() == 1);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.size() == 2);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        assertTrue(g.size() == 3);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        assertTrue(g.size() == 4);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertTrue(g.size() == 5);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        assertTrue(g.size() == 6);
        g.addEdge(new GraphEdge<String>(nx, nu, false, 2.05));
        assertTrue(g.size() == 6);
        g.clear();
        assertTrue(g.size() == 0);
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testClear() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertFalse(g.isDirected());
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.addNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.addNode((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(g.getNode(ns) == null);
        g.addNode(ns);
        assertTrue(g.getNode(nsTest) != null);
        String lu = "u";
        String luTest = "u";
        assertTrue(g.getNode(luTest) == null);
        g.addNode(lu);
        assertTrue(g.getNode(luTest) != null);
    }

    @Test
    final void testRemoveNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.removeNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.removeNode((String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(0));
        g.addNode("a");
        g.addNode("b");
        g.addNode(new GraphNode<String>("c"));
        g.addNode("d");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("a", "a");
        g.addEdge("b", "d");
        g.addEdge("a", "d");
        g.addEdge("c", "d");
        assertTrue(g.getNodeIndexOf("a") == 0);
        assertTrue(g.getNodeIndexOf("b") == 1);
        assertTrue(g.getNodeIndexOf("c") == 2);
        assertTrue(g.getNodeIndexOf("d") == 3);
        assertTrue(g.nodeCount() == 4);
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("e"));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(4));
        g.removeNode("b");
        assertTrue(g.getNodeIndexOf("a") == 0);
        assertTrue(g.getNodeIndexOf("c") == 1);
        assertTrue(g.getNodeIndexOf("d") == 2);
        assertTrue(g.nodeCount() == 3);
        assertTrue(g.getNode("b") == null);
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertTrue(g.getEdge("a", "a") != null);
        assertTrue(g.getEdge("a", "d") != null);
        assertTrue(g.getEdge("c", "d") != null);
        assertTrue(g.getEdge("c", "a") == null);
        assertTrue(g.getEdge("d", "d") == null);
        assertTrue(g.getEdge("c", "c") == null);
        g.removeNode(0);
        assertTrue(g.getNodeIndexOf("c") == 0);
        assertTrue(g.getNodeIndexOf("d") == 1);
        assertTrue(g.nodeCount() == 2);
        assertTrue(g.getNode("a") == null);
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertTrue(g.getEdge("c", "d") != null);
        assertTrue(g.getEdge("d", "d") == null);
        assertTrue(g.getEdge("c", "c") == null);
    }

    @Test
    final void testGetNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getNode((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(g.getNode(nsTest) == null);
        g.addNode(ns);
        assertTrue(g.getNode(nsTest) != null);
        g.addNode("a");
        GraphNode<String> na = g.getNode("a");
        assertTrue(na != null);
        na.setColor(GraphNode.COLOR_BLACK);
        assertTrue(g.getNode("a").getColor() == GraphNode.COLOR_BLACK);
        assertFalse(g.addNode("a"));
        assertTrue(g.getNode(na).getColor() == GraphNode.COLOR_BLACK);
        assertTrue(g.getNode("b") == null);
    }

    @Test
    final void testGetNodeInt() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(1));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(nsTest.equals(g.getNode(0)));
        assertTrue(g.getNode(0).getColor() == 1);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(2));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertTrue(nuTest.equals(g.getNode(1)));
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getNodeIndexOf((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getNodeIndexOf((String) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.getNodeIndexOf("u") == 1);
        assertTrue(g.getNodeIndexOf("s") == 0);
        g.addNode("x");
        assertTrue(g.getNodeIndexOf("x") == 2);
        g.addEdge("s", "x");
        assertTrue(g.getNodeIndexOf("s") == 0);
        g.removeNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertFalse(g.addNode("s"));
        assertFalse(g.addNode("x"));
        assertTrue(g.getNodeIndexOf("x") == 1);
        g.removeNode("s");
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("s"));
        assertTrue(g.getNodeIndexOf("x") == 0);
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<GraphNode<String>>();
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertTrue(nodes.equals(testNodes));
        GraphNode<String> nuTestBis = new GraphNode<String>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertTrue(nodes.equals(testNodes));
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, true)));
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertTrue(g.addEdge(esu));
        assertTrue(g.getEdge(new GraphEdge<String>(ns, nu, false)) != null);
        assertFalse(g.addEdge(new GraphEdge<String>(nu, ns, false, 6.0)));
        g.addNode("x");
        assertTrue(g.addEdge("x", "s"));
        assertTrue(g.getEdge("s", "x") != null);
        assertTrue(g.getEdge("x", "s") != null);
        g.addNode("t");
        assertTrue(g.addWeightedEdge("s", "t", 5.0));
        GraphEdge<String> est = g.getEdge("t", "s");
        assertTrue(est != null);
        assertTrue(est.getWeight() == 5);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        assertTrue(g.addWeightedEdge(nw, nu, 4.0));
        assertTrue(g.getEdge("u", "w").getWeight() == 4);
        assertFalse(g.addEdge("w", "u"));
    }

    @Test
    final void testRemoveEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.removeEdge((GraphEdge<String>) null));
        GraphNode<String> ns = new GraphNode<String>("s");
        assertThrows(NullPointerException.class,
                () -> g.removeEdge((GraphNode<String>) null, ns));
        assertThrows(NullPointerException.class,
                () -> g.removeEdge(ns, (GraphNode<String>) null));
        g.addNode(ns);
        g.addNode("a");
        g.addEdge("s", "a");
        GraphNode<String> nt = new GraphNode<String>("t");
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(ns, nt));
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(nt, ns));
        g.addNode(nt);
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(ns, nt));
        g.addEdge("t", "s");
        assertTrue(g.getEdge("a", "s") != null);
        g.removeEdge("a", "s");
        assertTrue(g.getEdge("a", "s") == null);
        assertTrue(g.getEdge("s", "a") == null);
        GraphEdge<String> ets = new GraphEdge<String>(nt, ns, false);
        assertTrue(g.getEdge(ets) != null);
        g.removeEdge(ets);
        assertTrue(g.getEdge(ets) == null);
        g.addEdge("a", "t");
        int i = g.getNodeIndexOf("a");
        int j = g.getNodeIndexOf(nt);
        assertTrue(g.getEdge(i, j) != null);
        g.removeEdge(j, i);
        assertTrue(g.getEdge(i, j) == null);
    }

    @Test
    final void testGetEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getEdge((GraphEdge<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getEdge((String) null, (String) null));
        assertThrows(NullPointerException.class, () -> g
                .getEdge((GraphNode<String>) null, (GraphNode<String>) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class,
                () -> g.getEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertTrue(g.getEdge(new GraphEdge<String>(ns, nu, false)) == null);
        g.addEdge(esu);
        assertTrue(g.getEdge(new GraphEdge<String>(ns, nu, false)) != null);
        g.addNode("a");
        g.addNode("b");
        g.addEdge("a", "s");
        g.addWeightedEdge("s", "b", 1);
        assertTrue(g.getEdge("s", "a").getNode1().getLabel().equals("s")
                || g.getEdge("s", "a").getNode1().getLabel().equals("a"));
        assertTrue(g.getEdge(new GraphNode<String>("b"),
                new GraphNode<String>("s")) != null);
        assertTrue(g.getEdge("u", "b") == null);
        int is = g.getNodeIndexOf(ns);
        int ia = g.getNodeIndexOf("a");
        int ib = g.getNodeIndexOf("b");
        assertTrue(g.getEdge(is, ia) != null);
        assertTrue(g.getEdge(is, ib) != null);
        assertTrue(g.getEdge(ib, ia) == null);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 5));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getAdjacentNodesOf((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getAdjacentNodesOf((String) null));
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getAdjacentNodesOf(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertTrue(g.getAdjacentNodesOf(ns).equals(adjNodes));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getAdjacentNodesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);
        assertTrue(g.getAdjacentNodesOf(nsTest).equals(adjNodes));
        adjNodes.clear();
        adjNodes.add(nsTest);
        assertTrue(g.getAdjacentNodesOf(nxTest).equals(adjNodes));
        assertTrue(g.getAdjacentNodesOf(nuTest).equals(adjNodes));
        GraphNode<String> np = new GraphNode<String>("p");
        GraphNode<String> npTest = new GraphNode<String>("p");
        g.addNode(np);
        adjNodes.clear();
        assertTrue(g.getAdjacentNodesOf(npTest).equals(adjNodes));
        g.addNode("q");
        g.addEdge("x", "u");
        g.addEdge("u", "q");
        g.addEdge("p", "u");
        adjNodes.add(nsTest);
        adjNodes.add(nxTest);
        adjNodes.add(new GraphNode<String>("q"));
        adjNodes.add(new GraphNode<String>("p"));
        assertTrue(g.getAdjacentNodesOf("u").equals(adjNodes));
        g.addNode("r");
        g.removeEdge("u", "p");
        g.removeEdge("u", "q");
        g.addEdge("r", "q");
        g.addEdge("p", "r");
        g.addEdge("r", "r");
        adjNodes.remove(nsTest);
        adjNodes.remove(nxTest);
        adjNodes.add(new GraphNode<String>("r"));
        int i = g.getNodeIndexOf("r");
        assertTrue(g.getAdjacentNodesOf(i).equals(adjNodes));
        adjNodes.remove(new GraphNode<String>("r"));
        g.removeEdge("r", "r");
        assertTrue(g.getAdjacentNodesOf(i).equals(adjNodes));
    }

    @Test
    final void testGetEdgesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(NullPointerException.class,
                () -> g.getEdgesOf((GraphNode<String>) null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getEdgesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertTrue(g.getEdgesOf(ns).equals(edgesTest));
        edgesTest.clear();
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(nx, ns, false));
        assertTrue(g.getEdgesOf(nx).equals(edgesTest));
        edgesTest.clear();
        assertTrue(g.getEdgesOf(nw).equals(edgesTest));
        g.addWeightedEdge("x", "x", 8.9);
        edgesTest.add(esx);
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("x"),
                new GraphNode<String>("x"), false));
        assertTrue(g.getEdgesOf("x").equals(edgesTest));
        g.addEdge("y", "w");
        int j = g.getNodeIndexOf("y");
        edgesTest.clear();
        edgesTest.add(eys);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("w"),
                new GraphNode<String>("y"), false));
        assertTrue(g.getEdgesOf(j).equals(edgesTest));
    }

    @Test
    final void testGetEdges() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphEdge<String> esuTest = new GraphEdge<String>(nu, ns, false);
        edgesTest.add(esuTest);
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertTrue(g.getEdges().equals(edgesTest));
        g.clear();
        edgesTest.clear();
        assertTrue(g.getEdges().equals(edgesTest));
    }

    @Test
    final void testGetDegreeOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.getDegreeOf(ns) == 0);
        assertThrows(NullPointerException.class,
                () -> g.getDegreeOf((GraphNode<String>) null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class, () -> g.getDegreeOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, false, 7.07);
        g.addEdge(euw);
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode(nz);
        GraphEdge<String> ezy = new GraphEdge<String>(nz, ny, false, 7.107);
        g.addEdge(ezy);
        assertTrue(g.getDegreeOf(ns) == 3);
        assertTrue(g.getDegreeOf(nu) == 3);
        assertTrue(g.getDegreeOf(nx) == 3);
        assertTrue(g.getDegreeOf(ny) == 3);
        assertTrue(g.getDegreeOf(nz) == 1);
        assertTrue(g.getDegreeOf(nw) == 1);
    }

}
