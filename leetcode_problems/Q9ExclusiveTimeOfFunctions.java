/*
 * Problem: Given the number of functions and a list of logs, calculate the exclusive time of each function.
 * Each log is in the format "function_id:start_or_end:timestamp".
 * The exclusive time of a function is the total time spent in that function, excluding the time spent in any called functions.
 *
 * Example:
 * Input: n = 2, logs = ["0:start:0", "1:start:2", "1:end:5", "0:end:6"]
 * Output: [3, 4]
 *
 * Explanation:
 * Function 0 starts at time 0 and ends at time 6. It calls function 1 from time 2 to time 5.
 * So, the exclusive time for function 0 is (6 - 0 + 1) - (5 - 2 + 1) = 3.
 * The exclusive time for function 1 is (5 - 2 + 1) = 4.
 * 
 * Example:
 * Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
 * 
 * Explanation:
 * Function 0 starts at time 0, calls itself at time 2, 
 * ends the recursive call at time 5, calls itself again at time 6, 
 * ends the second recursive call at time 6, and finally ends the initial call at time 7.
 * So, the exclusive time for function 0 is 2 + 4 + 1 + 1 = 8.
 * 
 * Output: [8]
 */
import java.util.List;
import java.util.Stack;

public class Q9ExclusiveTimeOfFunctions {
    // Main function to calculate exclusive times
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTimestamp = 0;

        for (String log : logs) {
            LogEntry entry = parseLog(log);

            if (entry.isStart) {
                handleStart(stack, result, entry, prevTimestamp);
                prevTimestamp = entry.timestamp;
            } else {
                prevTimestamp = handleEnd(stack, result, entry, prevTimestamp);
            }
        }

        return result;
    }

    // Handle a "start" log
    private void handleStart(Stack<Integer> stack, int[] result, LogEntry entry, int prevTimestamp) {
        if (!stack.isEmpty()) {
            int runningFunc = stack.peek();
            result[runningFunc] += entry.timestamp - prevTimestamp;
        }
        stack.push(entry.funcId);
    }

    // Handle an "end" log
    private int handleEnd(Stack<Integer> stack, int[] result, LogEntry entry, int prevTimestamp) {
        int funcId = stack.pop();
        result[funcId] += entry.timestamp - prevTimestamp + 1;
        return entry.timestamp + 1; // next interval starts after this timestamp
    }

    // Parse a log string into a structured LogEntry
    private LogEntry parseLog(String log) {
        String[] parts = log.split(":");
        int funcId = Integer.parseInt(parts[0]);
        boolean isStart = parts[1].equals("start");
        int timestamp = Integer.parseInt(parts[2]);
        return new LogEntry(funcId, isStart, timestamp);
    }

    // Helper class to hold log data
    private static class LogEntry {
        int funcId;
        boolean isStart;
        int timestamp;

        LogEntry(int funcId, boolean isStart, int timestamp) {
            this.funcId = funcId;
            this.isStart = isStart;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        Q9ExclusiveTimeOfFunctions solution = new Q9ExclusiveTimeOfFunctions();
        List<String> logs = List.of(
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"
        );
        int[] result = solution.exclusiveTime(2, logs); // Output should be [3, 4]
        for (int time : result) {
            System.out.println(time);
        }

        logs = List.of(
                "0:start:0",
                "0:start:2",
                "0:end:5",
                "0:start:6",
                "0:end:6",
                "0:end:7"
        );
        result = solution.exclusiveTime(1, logs); // Output should be [8]
        for (int time : result) {
            System.out.println(time);
        }

        logs = List.of(
            "0:start:0",
            "0:start:2",
            "0:end:5",
            "1:start:6",
            "1:end:6",
            "0:end:7"
        );
        result = solution.exclusiveTime(2, logs); // Output should be [7,1]
        for (int time : result) {
            System.out.println(time);
        }
    }
}
