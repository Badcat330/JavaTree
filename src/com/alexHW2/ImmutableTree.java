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
            throw new IllegalArgumentException("You can't delet root");
        if(rootSubTree == null)
            throw new NullPointerException();
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
    public AbstractTree<T> maximize(){
        return maximize((ImmutableNode<T>) root, this);
    }

    private AbstractTree<T> maximize(ImmutableNode<T> node, AbstractTree<T> tree){
        if(node.getChildren().isEmpty() && comparator.compare(node.getValue(), zero) < 0){
            return removeSubtree(node);
        } else{
            for(Node<T> tmp : node.getChildren()){
                tree = maximize((ImmutableNode<T>) tmp, tree);
            }
            ImmutableTree<T> subTree = new ImmutableTree<>(node, adder, comparator, zero);
            if(comparator.compare(subTree.getSum(), zero) < 0)
                return removeSubtree(node);
        }
        return tree;
    }
}
