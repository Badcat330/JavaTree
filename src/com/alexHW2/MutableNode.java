package com.alexHW2;

import java.util.Collection;
import java.util.Objects;
import java.util.Vector;

public class MutableNode<T extends Number> implements Node<T>{

    private T value;
    private MutableNode<T> parent;
    private Collection<Node<T>> children;

    public MutableNode(){
        value = null;
        parent = null;
        children = new Vector<>();
    }

    public MutableNode(T value){
        this.value = value;
        parent = null;
        children = new Vector<>();
    }

    public MutableNode(T value, MutableNode<T> parent){
        this.value = value;
        this.parent = parent;
        children = new Vector<>();
    }

    public MutableNode(T value, Collection<Node<T>> children){
        this.value = value;
        this.children = Objects.requireNonNullElseGet(children, Vector::new);
        parent = null;
    }

    public MutableNode(T value, MutableNode<T> parent, Collection<Node<T>> children){
        this.value = value;
        this.children = Objects.requireNonNullElseGet(children, Vector::new);
        this.parent = parent;
    }

    public void setValue(T value){ this.value = value; }

    public void setParent(MutableNode<T> parent){
        this.parent = parent;
    }

    public void setChildren(Collection<MutableNode<T>> children){
        this.children.addAll(children);
    }

    public void addChild(MutableNode<T> child){
        children.add(child);
    }

    public void removeChild(MutableNode<T> child){
        children.remove(child);
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
