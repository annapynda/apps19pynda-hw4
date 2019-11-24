package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int res = 0;
        for (int i = 0; i < strings.length; i++) {
            String[] newStr = strings[i].split(" ");
            for (String el : newStr) {
                if (el.length() > 2) {
                    res++;
                    trie.add(new Tuple(el, el.length()));
                }
            }
        }
        return res;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        if (trie.contains(word)) {
            trie.delete(word);
            return true;
        }
        return false;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if(pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2) {
            ArrayList<String> lstf = new ArrayList<>();
            Iterable<String> prefW = wordsWithPrefix(pref);
            for (String line : prefW) {
                if (line.length() < pref.length() + k) {
                    lstf.add(line);
                }
            }
            return lstf;
        }
        return null;
    }

    public int size() {
        return trie.size();
    }
}
