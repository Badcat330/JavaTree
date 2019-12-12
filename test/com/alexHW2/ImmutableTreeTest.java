package com.alexHW2;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableTreeTest{
    final int NumberOfAdds = 5;
    final Random rnd = new Random();

    @Test
    void getRoot(){
        int value = rnd.nextInt();
        ImmutableNode<Integer> root = new ImmutableNode<>(value);
        ImmutableTree<Integer> tree = new ImmutableTree<>(root, Integer::sum, Integer::compareTo, 0);
        assertEquals(tree.getRoot(), root);
        assertEquals(tree.getRoot().getValue(), value);
    }

    @Test
    void getSize(){
        int value = rnd.nextInt();
        Vector<Node<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new ImmutableNode<>(i));
        }
        ImmutableNode<Integer> parent = new ImmutableNode<Integer>(value, children);
        ImmutableTree<Integer> tree = new ImmutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        assertEquals(tree.getSize(), 1 + NumberOfAdds);
    }

    @Test
    void getSum(){
        int value = rnd.nextInt();
        int sum = value;
        Vector<Node<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new ImmutableNode<>(i));
            sum += i;
        }
        ImmutableNode<Integer> parent = new ImmutableNode<Integer>(value, children);
        ImmutableTree<Integer> tree = new ImmutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        assertEquals(tree.getSum(), sum);
    }

    @Test
    void removeSubtree(){
        int value = rnd.nextInt();
        Vector<Node<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            Vector<Node<Integer>> grandChildren = new Vector<>();
            grandChildren.add(new ImmutableNode<>(i));
            grandChildren.add(new ImmutableNode<>(i));
            grandChildren.add(new ImmutableNode<>(i));
            children.add(new ImmutableNode<>(i, grandChildren));
        }
        ImmutableNode<Integer> parent = new ImmutableNode<Integer>(value, children);
        ImmutableTree<Integer> tree = new ImmutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        tree = (ImmutableTree<Integer>) tree.removeSubtree(children.get(0));
        assertEquals(tree.getSize(), NumberOfAdds * 4 + - 3);
    }
}