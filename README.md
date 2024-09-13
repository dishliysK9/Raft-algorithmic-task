# TASK DESCRIPTION

Georgi is with his herd of goats on the riverbank. He wants to ferry them across using a raft with a certain capacity, making no more than K trips. The goats are not identical - some are small, some are large, some are sick, etc. Georgi wants to calculate the minimum capacity of the raft that would allow him to ferry all his N goats to the other side in no more than K trips.

Since it matters which goats he will ferry in the first trip, which in the second, and so on, he chooses the following strategy: In each trip, he first puts the largest goat that fits, then the next largest one that fits with the first, then the next one that fits with the first two, and so on. After no other goat can fit, he ferries them to the other side, returns, and applies the same procedure with the remaining goats. If after the K-th trip there are still goats left, it means that the capacity of the raft is insufficient.

From the described strategy, it follows that the capacity of the raft must be at least as large as the largest goat - otherwise, it would not be able to be transported in any of the trips.

Help Georgi write a program called raft that calculates the minimum capacity of the raft given the number of trips K, the number of goats N, and the weight of each goat A1, A2, ..., AN. Since it is a constant (and small), Georgi's weight can be neglected.

Input:
The first line:
N and K - respectively the number of goats and the maximum number of trips.
The second line:
A1, A2, ..., AN - the weights of the N goats, N integers.

Output:
One integer - the minimum capacity of the raft that would allow all goats to be transported in no more than K trips, using Georgi's strategy.
Constraints:
1 ≤ N ≤ 1000, 1 ≤ K ≤ 1000, 1 ≤ Ai ≤ 100000

EXAMPLES
Input
6 2
26 7 10 30 5 4
Output
42

The capacity of the raft must be at least 30, but this is insufficient to transport all of them in 2 trips. Three trips would be needed - in the first, the goat with a weight of 30 would be, in the second, those with 26 and 4, and in the third, those with 10, 7, and 5. The first number that allows transportation in 2 trips is 42 - so the first trip is (30, 10) and the second is (26, 7, 5, 4). Indeed, at 41, three trips are still needed - (30, 10), (26, 7, 5), and (4). Note that there is a strategy where 41 is sufficient - the trips are (30, 7, 4) and (26, 10, 5), but it does not follow Georgi's rules!

Input
6 2
4 8 15 16 23 42
Output
54
With a capacity of 54, the trips are (42, 8, 4) and (23, 16, 15), with both vans being fully filled, which guarantees that the answer is optimal.

Input
15 3
666 42 7 13 400 511 600 200 202 111 313 94 280 72 42
Output
1186
