package com.example.project;

import java.util.LinkedList;

public class TqsStack<T> {

    private final LinkedList<T> list = new LinkedList<>();

    public T tqsStack_pop(){
        return list.pop();
    }
    public void tqsStack_push(T item){
        list.push(item);
    }
    public boolean tqsStack_isEmpty(){
        return list.isEmpty();
    }
    public int tqsStack_size(){
        return list.size();
    }
    public T tqsStack_peek(){
        return list.getFirst();
    }
}
