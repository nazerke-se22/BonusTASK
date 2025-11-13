# Edge Removal from a Minimum Spanning Tree (MST)

This project demonstrates how to efficiently handle **edge removal and replacement** in a **Minimum Spanning Tree (MST)**.  
It is written in **Java**, and designed to compile and run directly after cloning from GitHub.

---

## Project Goal

When an edge is removed from an MST, the tree splits into two components.  
The task is to **find the lightest replacement edge** that reconnects these components so that the resulting graph remains a valid MST.

---

## Features

Builds an MST from a given weighted undirected graph using **Kruskal’s algorithm**  
Displays all MST edges before any removal  
Removes one edge and prints the resulting components  
Finds and adds the optimal replacement edge  
Shows the updated MST after reconnection

---

## Algorithm Overview

1. **Build MST** using Kruskal’s algorithm:
    - Sort all edges by weight.
    - Add edges one by one if they connect different components (Union-Find / DSU).
2. **Remove one edge** from the MST.
3. **Find the resulting components** after the removal.
4. **Search for the lightest edge** in the original graph that reconnects the two components.
5. **Add it** to restore a new valid MST.

---

## Project Structure
src/

├── Edge.java # Edge class with comparable implementation

├── Graph.java # Contains MST construction, DSU, and replacement logic

└── Main.java # Runs the demo and displays results

---

## How to Compile and Run

### Compile
```bash
javac src/*.java
java src.Main


Example output:
Original MST edges:
  (4 - 5, w=1)
  (1 - 2, w=2)
  (3 - 4, w=2)
  (0 - 1, w=4)
  (3 - 5, w=10)

Removing edge: (4 - 5, w=1)

Components after removing (4 - 5, w=1):
  [0, 1, 2, 3, 4]
  [5]

Replacement edge found: (3 - 5, w=10)

New MST edges:
  (1 - 2, w=2)
  (3 - 4, w=2)
  (0 - 1, w=4)
  (3 - 5, w=10)


