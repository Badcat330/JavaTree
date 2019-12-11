package com.alexHW2;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class MutableTree<T extends Number> extends AbstractTree<T>{

    public MutableTree(MutableNode<T> root, BinaryOperator<T> adder, Comparator<T> comparator, T zero){
        super(root, adder, comparator, zero);
    }

    @Override
    public AbstractTree<T> removeSubtree(Node<T> rootSubTree){
        if(rootSubTree == root)
            return null;
        MutableNode<T> parent = (MutableNode<T>) rootSubTree.getParent();
        parent.removeChild((MutableNode<T>) rootSubTree);
        return this;
    }

    @Override
    public AbstractTree<T> maximize(int k){
        //Todo: implement method
        return null;
    }

    @Override
    public AbstractTree<T> maximize(){
        //Todo: implement method
        return null;
    }

}
