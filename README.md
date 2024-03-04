# Shop Finding and Navigation System

This project implements a Shop Finding and Navigation System in Java, allowing users to manage shops in a simulated shopping mall environment. The system provides functionalities such as adding and deleting shops, managing shop connections, updating shop information, and performing graph traversal algorithms for navigation.

## How to Run

1. Compile the Java files using any Java compiler, such as javac:

`javac Main.java`

2. Run Main Program: Execute the Main class to start the Shop Finding and Navigation System.

`java Main`

## Features

- Shop Management: Users can add, delete, and update shop information including shop number, name, category, location, and rating.
- Graph Visualization: The system utilizes a graph data structure to represent the connections between shops, enabling efficient navigation.
- Graph Traversal Algorithms: Users can perform Depth First Search (DFS) and Breadth First Search (BFS) algorithms to find paths between shops.
- Category Search: Users can search for shops by category, allowing for targeted navigation.
- TestGraph: a demonstration on all the possible scenarios that trigger all possible exceptions for all possible Graph methods. Shows real time updates for Graph methods.

## Custom Data Structures

This project includes custom data structures implemented in Java for managing shops and graph operations:

- Graph.java: Implements the graph data structure with methods for adding, deleting, and traversing shops.
- HashEntry.java and HashTable.java: Implements a hash table for efficient storage and retrieval of shop information.
- LinkedList.java: Implements a linked list data structure for handling edges in the graph.
- MaxHeap.java: Implements a max heap data structure for prioritizing shops based on their ratings.
- Shop.java: Defines the Shop class for storing information about each shop.

## Context

Final datastructures and algorithm assignment, demonstrates knowledge of making custom data structures in Java. Interesting project, a culmination of a semester worths of weekly practical assignments.
