package ua.edu.ucu.tries;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.ArrayList;
import java.util.List;

import ua.edu.ucu.tries.Queue;


public class RWayTrie  implements Trie {

    private int size;
    private Node root;

    private static class Node {
        private static int R = 256;
        private Integer value;
        private Node[] next = new Node[R];

    }

    public RWayTrie() {
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.value != null)
            {
                size -= 1;
                x.value = null;
            }
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.value != null) {return x;}
        for (int c = 0; c < Node.R; c++)
            if (x.next[c] != null){
                return x;}
        return null;
    }

    private void collect(Node x, String word,  Queue<String> q) {
        if (x != null) {
            if (x.value != null && x.value > 0) {
                q.enqueue(word);
            }
            for (int i = 0; i < Node.R; i++) {
                collect(x.next[i], word + (char) (i), q);
            }
        }
    }

    private Object get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {return null;}
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) { return null; }
        if (d == key.length()) { return x; }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }


    private Node add(Node x, String key, int val, int d) {
        if (x == null) { x = new Node();
        }
        if (d == key.length()) {
            if (x.value == null) {
                size++;
                val++;
                x.value = val;
                return x;
            }
        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, val, d + 1);
        return x;
    }


    @Override
    public void add(Tuple t) {
        String word = t.term;
        int value = t.weight;
        root = add(root, word, value, 0);
    }



    @Override
    public boolean contains(String word) {
        if (get(word) == null) {
            return false;
        }
        return true;

    }


    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        return true;
    }


    @Override
    public Iterable<String> words() {

        Queue<String> bfs = new Queue<String>();
        collect(root, "", bfs);
        return bfs;
    }


    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue<String> bfs = new Queue<String>();
        Node nodex = get(root, s, 0);
        collect(nodex, s, bfs);
        return bfs;
    }



    @Override
    public int size() {
        return size;
    }

}



