package com.alexHW2;

import com.sun.jdi.connect.Connector;

import java.util.Comparator;
import java.util.Vector;
import java.util.function.BinaryOperator;

public class MutableTree<T extends Number> extends AbstractTree<T>{

    public MutableTree(MutableNode<T> root, BinaryOperator<T> adder, Comparator<T> comparator, T zero){
        super(root, adder, comparator, zero);
    }

    @Override
    public AbstractTree<T> removeSubtree(Node<T> rootSubTree){
        if(rootSubTree == root)
            throw new IllegalArgumentException("You can't delet root");
        if(rootSubTree == null)
            throw new NullPointerException();
        MutableNode<T> parent = (MutableNode<T>) rootSubTree.getParent();
        parent.removeChild((MutableNode<T>) rootSubTree);
        return this;
    }

    @Override
    public AbstractTree<T> maximize(){
        maximize((MutableNode<T>) root);
        return this;
    }

    private MutableNode<T> maximize(MutableNode<T> node){
        if(node.getChildren().isEmpty() && comparator.compare(node.getValue(), zero) < 0){
            return node;
        }
        else{
            Vector<MutableNode<T>> deletedNods = new Vector<>();
            for(Node<T> tmp : node.getChildren()){
                deletedNods.add(maximize((MutableNode<T>) tmp));
            }
            for(MutableNode<T> deletedNod : deletedNods){
                if(deletedNod != null)
                    removeSubtree(deletedNod);
            }
            MutableTree<T> tree = new MutableTree<>(node, adder, comparator, zero);
                if(comparator.compare(tree.getSum(), zero) < 0)
                    return node;
        }
        return null;
    }

}
