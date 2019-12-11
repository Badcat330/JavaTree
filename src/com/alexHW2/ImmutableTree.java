package com.alexHW2;

import java.util.Comparator;
import java.util.Vector;
import java.util.function.BinaryOperator;

public class ImmutableTree<T extends Number> extends AbstractTree<T>{

    public ImmutableTree(ImmutableNode<T> root, BinaryOperator<T> adder, Comparator<T> comparator, T zero){
        super(root, adder, comparator, zero);
    }

    @Override
    public AbstractTree<T> removeSubtree(Node<T> rootSubTree){
        if(rootSubTree == root)
            return null;
        return new ImmutableTree<T>(removeCheck(rootSubTree, (ImmutableNode<T>) root), adder, comparator, zero);
    }

    private ImmutableNode<T> removeCheck(Node<T> rootSubTree, ImmutableNode<T> currentNode){
        if(currentNode.getChildren().isEmpty()){
           return new ImmutableNode<T>(currentNode.getValue(), (ImmutableNode<T>) currentNode.getParent(), null);
        }
        else {
            Vector<Node<T>> newChildren = new Vector<>();
            for(Node<T> tmp : currentNode.getChildren()){
                if(tmp != rootSubTree)
                    newChildren.add(removeCheck(rootSubTree, (ImmutableNode<T>) tmp));
            }
            return new ImmutableNode<T>(currentNode.getValue(), (ImmutableNode<T>) currentNode.getParent(), newChildren);
        }
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
