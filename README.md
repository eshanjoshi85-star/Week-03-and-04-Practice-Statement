# Week-03-and-04-Practice-Statement
Problem 1: Transaction Fee Sorting for Audit Compliance
-
**Scenario:**

A banking application processes 10,000 daily transactions. Auditors require
transactions sorted by fee amount for compliance reviews.


**Problem Statement:**


Implement a fee‐sorting system that:

● Sorts transactions by fee in ascending order using Bubble Sort (for small batches
≤ 100).

● Sorts by fee + timestamp using Insertion Sort (for medium batches 100–1,000).

● Handles duplicates (stable sort required).

● Flags high‐fee outliers (> $50).

**Concepts Covered:**

● Bubble Sort: adjacent swaps, early termination.

● Insertion Sort: building sorted subarray, shift operations.

● Time complexity: O(n2) analysis, best/worst cases.

● Stability in sorting.

**Hints:**

● Use ArrayList<Transaction> with fee and timestamp.

● Bubble: nested loops, compare i and i+1, swap if needed.

● Insertion: for each element, shift larger ones right.

● Count passes/swaps for optimization.

**Use Cases:**

● Citi transaction audit reports.
● Fraud detection by fee patterns.
● Compliance fee threshold analysis.

**Sample Input/Output:**

Input transactions:
id1, fee=10.5, ts=10:00
id2, fee=25.0, ts=09:30
id3, fee=5.0, ts=10:15

BubbleSort (fees): [id3:5.0, id1:10.5, id2:25.0] // 3 passes, 2 swaps

InsertionSort (fee+ts): [id2:25.0@09:30, id3:5.0@10:15, id1:10.5@10:00]

High-fee outliers: none

Problem 2: Client Risk Score Ranking
-
**Scenario:**

Risk management team needs quick sorting of 500 client risk scores for
priority review.

**Problem Statement:**

● Sort clients by riskScore ascending using Bubble Sort (visualize swaps for demo).

● Sort by riskScore DESC + accountBalance using Insertion Sort.

● Identify top 10 highest risk clients post‐sort.

**Concepts Covered:**

● In‐place sorting algorithms.

● Adaptive behavior (Insertion excels on nearly‐sorted data).

● Space complexity O(1).

**Hints:**

● Client[] array.

● Bubble: for(int i=0; i<n-1; i++) outer, inner j<n-i-1.

● Insertion: for(int i=1; i<n; i++) shift from j=i-1.

**Use Cases:**

● KYC risk prioritization.

● Loan approval ranking.

● AML watchlist generation.

**Sample Input/Output:**

Input: [clientC:80, clientA:20, clientB:50]

Bubble (asc): [A:20, B:50, C:80] // Swaps: 2

Insertion (desc): [C:80, B:50, A:20]

Top 3 risks: C(80), B(50), A(20)

Problem 3: Historical Trade Volume Analysis
-

**Scenario:**

Analyze 1 million daily trades by volume for market trend reports (Citi trading
desk).

**Problem Statement:**

● Sort trades by volume ascending using Merge Sort (stable, guaranteed O(n log
n)).

● Sort by volume DESC using Quick Sort (in‐place, average O(n log n)).

● Merge two sorted trade lists (e.g., morning/afternoon sessions).

● Compute total volume post‐sort.

**Concepts Covered:**

● Merge Sort: divide‐conquer, merge step.

● Quick Sort: pivot selection, partitioning, recursion.

● Stability, worst‐case O(n2) in Quick.

**Hints:**

● Merge: merge(left, right, temp).

● Quick: lomutoPartition(pivotIndex), recurse on partitions.

● Use int[] volumes or Trade[].

**Use Cases:**

● Citi market volume reports.

● Portfolio rebalancing.

● High‐frequency trading analytics.

**Sample Input/Output:**

Input: [trade3:500, trade1:100, trade2:300]

MergeSort: [1:100, 2:300, 3:500] // Stable

QuickSort (desc): [3:500, 2:300, 1:100] // Pivot: median

Merged morning+afternoon total: 900

Problem 4: Portfolio Return Sorting
-
**Scenario:** 

Sort 10,000 assets by historical returns for investment recommendations.

**Problem Statement:**

● Merge Sort assets by returnRate (preserve original order for ties).

● Quick Sort by returnRate DESC + volatility ASC.

● Handle pivot selection (random vs median‐of‐3).

**Concepts Covered:**

● External sorting if data > memory.

● Hybrid algorithms (Quick + Insertion for small partitions).

**Hints:**

● Merge: auxiliary array.

● Quick: partition(low, high, pivot).

**Use Cases:**

● Asset allocation optimization.

● Risk‐parity portfolio construction.

**Sample Input/Output:**

Input: [AAPL:12%, TSLA:8%, GOOG:15%]

Merge: [TSLA:8%, AAPL:12%, GOOG:15%]

Quick (desc): [GOOG:15%, AAPL:12%, TSLA:8%]

Problem 5: Account ID Lookup in Transaction Logs
-

**Scenario:**

Search 1 million transaction logs for specific account IDs (audit/compliance).

**Problem Statement:**

● Implement Linear Search to find first/last occurrence of accountId.

● Use Binary Search (after sorting by ID) for exact match + count occurrences.

● Count comparisons and report time complexity.

● Handle duplicates.

**Concepts Covered:**

● Linear: O(n), simple but worst for large N.

● Binary: O(log n) requires sorted input, pivot/mid logic.

**Hints:**

● Linear: for(int i=0; i<n; i++) if(arr[i].equals(target)).

● Binary: while(low <= high) mid = (low+high)/2.

**Use Cases:**

● Citi transaction forensics.

● Dispute resolution lookups.

● Regulatory reporting.

**Sample Input/Output:**

Sorted logs: [accB, accA, accB, accC]

Linear first accB: index 0 (4 comparisons)

Binary accB: index 1 (3 comparisons), count=2

Problem 6: Risk Threshold Binary Lookup
-

**Scenario:**

Binary search optimal risk bands in sorted client risk table.

**Problem Statement:**

● Linear Search unsorted risk bands for threshold match.

● Binary Search sorted bands to find insertion point for new client.

● Find floor/ceiling values (largest ≤ target, smallest ≥ target).

**Concepts Covered:**

● Binary variants: lower_bound, upper_bound.

● Prerequisites: sorted array.

**Hints:**

● Binary insertion: adjust low/high based on comparison.

**Use Cases:**

● Dynamic risk pricing tables.

● Compliance band assignment.

**Sample Input/Output:**

Sorted risks: [10, 25, 50, 100]

Linear: threshold=30 → not found (4 comps)

Binary floor(30): 25, ceiling: 50 (3 comps)