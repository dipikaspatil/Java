// https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
// points to note - 
// sandwich array does not rotate
// student array can rorate
// Count how many students want 0 and how many want 1.
// Go through the sandwich stack one by one.
// For each sandwich, check if there is any student who wants it:
// If yes → decrement the corresponding count. (after certain rotatin, that respective student will get sandwitch)
// If no → break (remaining students can’t eat). (it means no one want that sandwich and even after rotation there will not be anyone who wants it. That's where all students are blocked)
// Example - students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
// preference_0 = 2, preference_1 = 4
// sandwiches[0] = 1 preference_0 = 2, preference_1 = 3
// sandwiches[1] = 0 preference_0 = 1, preference_1 = 3
// sandwiches[2] = 0 preference_0 = 0, preference_1 = 3
// sandwiches[3] = 0 Since no-one want sandwich 0 .. remaining students are blocked
// remaining studnet = preference_0 + preference_1 = 3
// last state of sandwiches = [0, 1, 1] last state of students = [1, 1, 1]
// time complexity - O(1)
// space complexity - O(1)
class Q13NumberOfStudentsUnableToEatLunch {
    public int countStudents(int[] students, int[] sandwiches) {
        int preference_0 = 0;
        int preference_1 = 0;

        // traverse over students to check count of students with certain preference
        for(int student : students) {
            if (student == 0) {
                ++preference_0;
            } else {
                ++preference_1;
            }
        }

        // check sandwitch array
        for(int sandwich : sandwiches) {
            if(sandwich == 0) {
                if(preference_0 > 0) {
                    --preference_0;
                } else {
                    break;
                }
            } else {
                if(preference_1 > 0) {
                    --preference_1;
                } else {
                    break;
                }
            }
        }

        return preference_0 + preference_1;
    }

    public static void main(String[] args) {
        Q13NumberOfStudentsUnableToEatLunch solution = new Q13NumberOfStudentsUnableToEatLunch();
        int[] students = {1,1,1,0,0,1};
        int[] sandwiches = {1,0,0,0,1,1};
        assert 3 == solution.countStudents(students, sandwiches); // Output should be 3

        students = new int[]{1,1,0,0};
        sandwiches = new int[]{0,1,0,1};
        assert 0 == solution.countStudents(students, sandwiches); // Output should be 0
    }
}