package com.alexHW2;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public abstract class AbstractTree<T extends Number>{
    private Node<T> root;
    private BinaryOperator<T> adder;
    private T sum;
    private Comparator<T> comparator;
    private T zero;

    public Node<T> getRoot(){
        return root;
    }

    public int getSize(){
        return getSize(root);
    }

    private int getSize(Node<T> node){
        if(node.getChildren().isEmpty())
            return 1;
        else{
            int count = 0;
            for(Node<T> tmp : node.getChildren()){
                count += getSize(tmp);
            }
            return 1 + count;
        }
    }

    public T getSum(){
        sum = zero;
        UpdateSum(root);
        return sum;
    }

    private void UpdateSum(Node<T> node){
        if(node.getChildren().isEmpty())
             sum = adder.apply(node.getValue(), sum);
        else{
            for(Node<T> tmp : node.getChildren()){
                UpdateSum(tmp);
            }
            adder.apply(sum, node.getValue());
        }
    }

    public abstract AbstractTree<T> removeSubtree(Node<T> rootSubTree);
    public abstract AbstractTree<T> maximize(int k);
    public abstract AbstractTree<T> maximize();

}
