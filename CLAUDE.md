# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a pure Java DSA (Data Structures & Algorithms) practice repository managed as an IntelliJ IDEA multi-module project. There is no build tool (no Maven/Gradle) — compilation is handled by IntelliJ or `javac` directly.

## Modules

The project has three IntelliJ modules:

- **`src/`** (root module `dsa.iml`) — the primary and most comprehensive module, organized by topic → difficulty
- **`79LastMoment/src/`** — an older/parallel practice set with overlapping topics
- **`NeetCode150/src/`** — NeetCode 150 problems (in progress; currently only ArraysAndHashing)

## Build & Run

Since there is no build tool, compile and run individual files with `javac`/`java` from the command line:

```powershell
# Compile (from repo root, e.g. for root module)
javac src\Array\Medium\TwoSum.java

# Compile NeetCode150 module
javac NeetCode150\src\ArraysAndHashing\GroupAnagrams.java

# Run (class must have a main method)
java -cp src Main
```

IntelliJ IDEA is the primary development environment — open the project and run individual files via the IDE gutter icons.

There are no tests; solutions are verified manually or via LeetCode/online judges.

## Code Architecture & Conventions

### Root module topic structure (`src/`)

```
Array/          Easy, Medium, Hard
BinarySearch/
BinarySearchTree/  Easy, Medium, Hard
BinaryTree/        Easy, Medium, Hard (shared TreeNode.java at src/BinaryTree/TreeNode.java)
DynamicProgramming/  DP2D, DPOnSquares, DPOnStocks, DPOnStrings, DPOnSubsequences, Easy, LIS, PartitionDP
Graphs/         Easy, Medium  (MST at src/MST/Medium/)
GreedyAlgorithm/ Easy, Medium, Hard
LinkedList/     CircularLinkedList, DoublyLinkedList, DoublyLinkedListMed, FastAndSlowPointers, LinkedListMed
Recursion/      (also RecursionAndBackTracking/)
Stack/          Medium, Hard, StackArray, StackLinkedList, InfixToPostfix
Queue/          CircularQueue, DEQueue, QueueArray, QueueLinkedList
Strings/        Medium  (also String/, StringLeetCode/ for LeetCode string problems)
Tries/
TwoPointers/
SortingAlgos/
DesignGurusEasy/   DesignGurus platform problems
HashSet/
```

### Recurring patterns to follow

- **Multiple solutions in one file**: Brute force and optimal solutions often coexist as separate methods within the same class (e.g., `groupAnagrams` vs `groupAnagramsOptimal`).
- **Recursive + tabulation pairs**: Many DP problems have two files — `FooBar.java` (memoization/recursive) and `FooBarDP.java` (bottom-up tabulation).
- **Complexity comments**: Inline `// Time: O(...)` and `// Space: O(...)` comments at the end of each approach are standard.
- **No main methods in solutions**: Most solution files are plain classes with public methods, no `main()`. The `79LastMoment/src/Main.java` is a scratch entry point.
- **Shared node/tree types**: `TreeNode.java` lives at `src/BinaryTree/TreeNode.java` and `ListNode.java` at `src/LinkedList/FastAndSlowPointers/ListNode.java` — reuse these rather than redefining.
- **Package declarations**: Root module files use no `package` declaration. `NeetCode150` files use `package ArraysAndHashing;` etc. `79LastMoment` files also have no package declarations.
