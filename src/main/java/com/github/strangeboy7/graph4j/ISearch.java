package com.github.strangeboy7.graph4j;

import java.util.List;

interface ISearch<T> {

    boolean marked(T t);

    List<T> pathTo(T t);

    int count();
}
