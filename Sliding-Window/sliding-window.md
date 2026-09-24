# Sliding Window
## What is the Sliding Window Technique?
The **Sliding Window technique** is an optimization pattern used to reduce nested loops ($O(N^2)$ or $O(N^3)$) down to a single pass ($O(N)$).It works by maintaining a "window" defined by two pointers (`left` and `right`) over a continuous block of data. Instead of re-evaluating every possible sub-array from scratch, you slide the window forward across the array step-by-step:
- The `right` pointer expands the window to explore new elements.

- The `left` pointer contracts or repositions the window whenever a specific condition is broken.

## When to Use Sliding Window?
You should consider Sliding Window whenever a problem has these characteristics:

- **Linear Data Structure:** The input is an array, string, or list.

- **Contiguous Elements:** You are looking for a subarray or substring (elements must be adjacent).

- **Optimization Goal:** You are asked to find a maximum, minimum, longest, shortest, or target value across contiguous elements.