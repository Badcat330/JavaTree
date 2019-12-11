package com.alexHW2;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MutableNodeTest{
    final int NumberOfAdds = 5;
    final Random rnd = new Random();

    @Test
    void setGetValueInt(){
        MutableNode<Integer> testNode = new MutableNode<>();
        for(int i = 0; i < NumberOfAdds; i++){
            int newValue = rnd.nextInt();
            testNode.setValue(newValue);
            assertEquals(newValue, testNode.getValue());
        }
    }

    @Test
    void setGetValueDouble(){
        MutableNode<Double> testNode = new MutableNode<>();
        for(int i = 0; i < NumberOfAdds; i++){
            double newValue = rnd.nextDouble() + rnd.nextInt();
            testNode.setValue(newValue);
            assertEquals(newValue, testNode.getValue());
        }
    }

    @Test
    void setGetParent(){
        for(int i = 0; i < NumberOfAdds; i++){
            int value = rnd.nextInt();
            MutableNode<Integer> parentNode = new MutableNode<>(value, null, null);
            MutableNode<Integer> childrenNode = new MutableNode<>(null, null, null);
            childrenNode.setParent(parentNode);
            assertEquals(childrenNode.getParent(), parentNode);
            assertEquals(childrenNode.getParent().getValue(), value);
        }
    }

    @Test
    void setGetChildren(){
        Vector<MutableNode<Integer>> children = new Vector<>();
        for(int i = 0; i < NumberOfAdds; i++){
            children.add(new MutableNode<>(i, null, null ));
        }
        MutableNode<Integer> parent = new MutableNode<>();
        parent.setChildren(children);
        int i = 0;
        for(Node<Integer> node : parent.getChildren()){
            assertEquals(node, children.get(i));
            assertEquals(node.getValue(), children.get(i).getValue());
            i++;
        }
    }

    @Test
    void addChild(){
        MutableNode<Integer> parentNode = new MutableNode<>();
        MutableNode<Integer> childNode1 = new MutableNode<>();
        MutableNode<Integer> childNode2 = new MutableNode<>();
        MutableNode<Integer> childNode3 = new MutableNode<>();
        Vector<MutableNode<Integer>> children = new Vector<>();
        children.add(childNode1);
        children.add(childNode2);
        children.add(childNode3);
        for(int i =0 ; i< children.size(); i++){
            children.get(i).setValue(i);
            parentNode.addChild(children.get(i));
        }
        Collection<Node<Integer>> output = parentNode.getChildren();
        int i = 0;
        for(Node<Integer> out : output){
            assertEquals(out.getValue(), children.get(i).getValue());
            i++;
        }
    }

    //ToDo: Change remove
    @Test
    void removeChild1(){
        MutableNode<Integer> parentNode = new MutableNode<>();
        MutableNode<Integer> childNode1 = new MutableNode<>();
        MutableNode<Integer> childNode2 = new MutableNode<>();
        MutableNode<Integer> childNode3 = new MutableNode<>();
        Vector<MutableNode<Integer>> children = new Vector<>();
        children.add(childNode1);
        children.add(childNode2);
        children.add(childNode3);
        for(int i =0 ; i< children.size(); i++){
            children.get(i).setValue(i);
        }
        parentNode.setChildren(children);
        for(MutableNode<Integer> child : children){
            parentNode.removeChild(child);
        }
        Collection<Node<Integer>> output = parentNode.getChildren();
        assertEquals(output.size(), 0);
    }

    @Test
    void removeChild2(){
        MutableNode<Integer> parentNode = new MutableNode<>();
        MutableNode<Integer> childNode1 = new MutableNode<>();
        MutableNode<Integer> childNode2 = new MutableNode<>();
        MutableNode<Integer> childNode3 = new MutableNode<>();
        Vector<MutableNode<Integer>> children = new Vector<>();
        children.add(childNode1);
        children.add(childNode2);
        children.add(childNode3);
        for(int i =0 ; i< children.size(); i++){
            children.get(i).setValue(i);
        }
        parentNode.setChildren(children);
        parentNode.removeChild(childNode1);
        parentNode.removeChild(childNode3);
        Collection<Node<Integer>> output = parentNode.getChildren();
        assertEquals(childNode2.getValue(), output.iterator().next().getValue());
    }

}