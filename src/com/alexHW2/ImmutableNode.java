package com.alexHW2;

import java.util.Collection;

public class ImmutableNode<T extends Number> implements Node<T>{
    private T value;
    private ImmutableNode<T> parent;
    private Collection<Node<T>> children;

    //ToDO: Подумать над сигнатурой конструктора
    public ImmutableNode(T value, ImmutableNode<T> parent, Collection<Node<T>> children){
        this.value = value;
        this.parent = parent;
        this.children = children;
    }

    @Override
    public Node<T> getParent(){
        return parent;
    }

    @Override
    public Collection<Node<T>> getChildren(){
        return children;
    }

    @Override
    public void print(int indents){
        System.out.println(" ".repeat(indents) + value);
    }

    @Override
    public T getValue(){
        return value;
    }
}
