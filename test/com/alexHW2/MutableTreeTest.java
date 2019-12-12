package com.alexHW2;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.Vector;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class MutableTreeTest{
    final int NumberOfAdds = 3;
    final Random rnd = new Random();

    @Test
    void getRoot(){
        int value = rnd.nextInt();
        MutableNode<Integer> parent = new MutableNode<>(value);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        assertEquals(tree.getRoot(), parent);
        assertEquals(tree.getRoot().getValue(), value);
    }

    @Test
    void getSize(){
        int value = rnd.nextInt();
        MutableNode<Integer> parent = new MutableNode<>(value);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new MutableNode<>(i, parent));
            children.get(i).addChild(new MutableNode<>(i, parent));
        }
        parent.setChildren(children);
        assertEquals(tree.getSize(), 1 + NumberOfAdds * 2);
    }

    @Test
    void getSum(){
        int value = 5;
        int sum = value;
        MutableNode<Integer> parent = new MutableNode<>(value);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new MutableNode<>(i, parent));
            children.get(i).addChild(new MutableNode<>(i, children.get(i)));
            sum += 2 * i;
        }
        parent.setChildren(children);
        assertEquals(tree.getSum(), sum);
    }

    @Test
    void removeSubtree(){
        int value = rnd.nextInt();
        MutableNode<Integer> parent = new MutableNode<>(value);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new MutableNode<>(i, parent));
            children.get(i).addChild(new MutableNode<>(i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(i, children.get(i)));
        }
        parent.setChildren(children);
        tree.removeSubtree(children.get(0));
        assertEquals(tree.getSize(),  NumberOfAdds * 4 + - 3);
    }

    private int generatRandomInt(Integer sum){
        int buf = rnd.nextInt();
        if(buf > 0)
            sum = new Integer(buf + sum);
        return  buf;
    }

    @Test
    void maximize(){
        int sum = 5;
        MutableNode<Integer> parent = new MutableNode<>(5);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            children.add(new MutableNode<>(i, parent));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            sum += i;
        }
        parent.setChildren(children);
        assertNotEquals(tree.getSum(), sum);
        tree.maximize();
        assertEquals(tree.getSum(), sum);
    }

    @Test
    void maximize2(){
        int sum = 5;
        MutableNode<Integer> parent = new MutableNode<>(5);
        MutableTree<Integer> tree = new MutableTree<>(parent, Integer::sum, Integer::compareTo, 0);
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i =0; i < NumberOfAdds; i++){
            if(i == NumberOfAdds - 1){
                sum -= i;
                children.add(new MutableNode<>(-i, parent));
            }
            else
                children.add(new MutableNode<>(i, parent));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            children.get(i).addChild(new MutableNode<>(-i, children.get(i)));
            sum += i;
        }
        parent.setChildren(children);
        assertNotEquals(tree.getSum(), sum);
        tree.maximize();
        assertEquals(tree.getSum(), sum);
    }

}