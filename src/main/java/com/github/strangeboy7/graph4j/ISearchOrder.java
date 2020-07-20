package com.github.strangeboy7.graph4j;

import java.util.List;

interface ISearchOrder<T> {
    List<T> pre();
    List<T> post();
    List<T> reversePost();
}
