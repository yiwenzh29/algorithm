##Modular Programming
* separate interface and implementation
* Client: program using operations defined in interface
* implementation: code implementing operations
* interface: description of data type, basic operations

##Stacks


### Problems:


##Resizing Arrays
###push:
* O(N)?

### pop:
* if halve size of array when one-half full: **thrashing**
* one-quarter full
* O(N)?

##Queue
###enqueue
###dequeue

##Generics
* different types of data
* casting in client: not goot
* generics: check mismatch at compile time


```$xslt
public class Stack<Item>
...
private class Node {
    Item item; 
    }
...
public Item pop() {
Item item;
}
```
* Arrays: not allowed by Java; has to use casting
* Primitive types (e.g.Integers)
```Stack<Integer> stack = new Stack<Integer>();```

##Iterators
* Iterable interface
```
//implement Iterable

//implement Iterator
public interface Iterable<Item>{
    Iterator<Item> iterator();
    }
public interface Iterator<Item>{
    boolean hasNext();
    Item next();
//foreach statement

```

##Bag
* order doesn't matter; just add and iterator

**?**Iterators and queues?

