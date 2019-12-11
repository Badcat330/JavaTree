package com.alexHW2;

import java.util.Collection;
import java.util.Objects;
import java.util.Vector;

public class ImmutableNode<T extends Number> implements Node<T>{
    private T value;
    private ImmutableNode<T> parent;
    private Collection<Node<T>> children;

    public ImmutableNode(T value, ImmutableNode<T> parent, Collection<Node<T>> children){
        this.value = value;
        this.parent = parent;
        this.children = Objects.requireNonNullElseGet(children, Vector::new);
        for(Node<T> child : this.children){
            child = adopt((ImmutableNode<T>) child);
        }

    }

    public ImmutableNode(T value, Collection<Node<T>> children){
        this.value = value;
        parent = null;
        this.children = Objects.requireNonNullElseGet(children, Vector::new);
        for(Node<T> child : this.children){
            child = adopt((ImmutableNode<T>) child);
        }
    }

    public ImmutableNode(T value){
        this.value = value;
        parent = null;
        children = new Vector<>();
    }

    private ImmutableNode<T> adopt(ImmutableNode<T> node){
        return new ImmutableNode<>(node.getValue(), this, node.getChildren());
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
