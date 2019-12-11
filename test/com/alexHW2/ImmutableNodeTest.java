package com.alexHW2;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableNodeTest{
    final int NumberOfAdds = 5;
    final Random rnd = new Random();

    @Test
    void getValueInt(){
        for(int i = 0; i < NumberOfAdds; i++){
            int value = rnd.nextInt();
            ImmutableNode<Integer> newNode = new ImmutableNode<>(value, null, null);
            assertEquals(value, newNode.getValue());
        }
    }

    @Test
    void getValueDouble(){
        for(int i = 0; i < NumberOfAdds; i++){
            double value = rnd.nextInt() + rnd.nextDouble();
            ImmutableNode<Double> newNode = new ImmutableNode<>(value, null, null);
            assertEquals(value, newNode.getValue());
        }
    }

    @Test
    void getParent(){
        for(int i = 0; i < NumberOfAdds; i++){
            int value = rnd.nextInt();
            ImmutableNode<Integer> parentNode = new ImmutableNode<>(value, null, null);
            ImmutableNode<Integer> childrenNode = new ImmutableNode<>(null, parentNode, null);
            assertEquals(childrenNode.getParent(), parentNode);
            assertEquals(childrenNode.getParent().getValue(), value);
        }
    }

    @Test
    void getChildren(){
        Vector<Node<Integer>> children = new Vector<>();
        for(int i = 0; i < NumberOfAdds; i++){
            children.add(new ImmutableNode<>(i, null, null ));
        }
        ImmutableNode<Integer> parent = new ImmutableNode<>(null, null, children);
        int i = 0;
        for(Node<Integer> node : parent.getChildren()){
            assertEquals(node, children.get(i));
            assertEquals(node.getValue(), children.get(i).getValue());
            i++;
        }
    }
}