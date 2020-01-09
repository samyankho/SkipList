# SkipList

Motivation

The purpose of this assignment is to compare two different variations of linked lists in terms of time complexity for some of the operations.



Introduction

This assignment will also involve (partially) implementing a List 1 interface. The List interface provides four methods for positional (indexed) access to list elements, two methods to search for a specified object, and two methods to insert and remove multiple elements at an arbitrary point in the list.

While some of these operations often execute in constant amount of time, the others may execute in time proportional to the index value, depending on an implementation (the LinkedList class, for example, is not very efficient when accessing elements in the middle of the list using an index, and ArrayList does not allow for efficient insertion or deletion, unless the element is at the end of the list).

Skip list 2 is a data structure that allows O(log n) search or indexed access complexity as well as O(log n) insertion complexity within an ordered sequence of n elements. While there are other data structures allowing for the same complexity, skip lists do have some advantages over them (e.g., insertions tend to affect nodes only in the immediate vicinity, unlike in balanced trees).




Implementation

In this assignment, you will have to write a skip list-based partial implementation of the List interface. Note that there are two main modes in which these data structures are used: a map mode, where a <key, data> pair is inserted, and a linear ranked list mode similar to how one uses an array (Figure 1). The latter one is applicable in this assignment.
