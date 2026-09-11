package org.sadovodBase;

import java.util.*;

public class Main {

    
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int max = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                max = Math.max(max, length);
            }
        }

        return max;
    }


   
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(
                            nums[i], nums[left], nums[right]
                    ));

                    while (left < right && nums[left] == nums[left + 1])
                        left++;

                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }


    
    public static int totalFruit(int[] items) {

        Map<Integer, Integer> count = new HashMap<>();

        int left = 0;
        int answer = 0;

        for (int right = 0; right < items.length; right++) {

            count.put(items[right],
                    count.getOrDefault(items[right], 0) + 1);

            while (count.size() > 2) {

                count.put(items[left],
                        count.get(items[left]) - 1);

                if (count.get(items[left]) == 0) {
                    count.remove(items[left]);
                }

                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }


    
    public static int subarraySum(int[] nums, int k) {

        Map<Long, Integer> map = new HashMap<>();

        map.put(0L, 1);

        long sum = 0;
        int answer = 0;

        for (int num : nums) {

            sum += num;

            if (map.containsKey(sum - k)) {
                answer += map.get(sum - k);
            }

            map.put(sum,
                    map.getOrDefault(sum, 0) + 1);
        }

        return answer;
    }


    
    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        long max = 0;

        while (left < right) {

            int h = Math.min(height[left], height[right]);

            long area = (long)(right - left) * h;

            max = Math.max(max, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return (int)max;
    }


    
    public static String minWindow(String source, String target) {

        if (source.length() < target.length())
            return "";

        Map<Character, Integer> need = new HashMap<>();

        for (char c : target.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }


        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int count = 0;

        int minLength = Integer.MAX_VALUE;
        int start = 0;


        for (int right = 0; right < source.length(); right++) {

            char c = source.charAt(right);

            window.put(c,
                    window.getOrDefault(c, 0) + 1);


            if (need.containsKey(c) &&
                    window.get(c).intValue() <= need.get(c)) {
                count++;
            }


            while (count == target.length()) {

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }


                char leftChar = source.charAt(left);

                window.put(leftChar,
                        window.get(leftChar) - 1);


                if (need.containsKey(leftChar) &&
                        window.get(leftChar) < need.get(leftChar)) {
                    count--;
                }

                left++;
            }
        }


        if (minLength == Integer.MAX_VALUE)
            return "";

        return source.substring(start, start + minLength);
    }


    public static void main(String[] args) {

        System.out.println(longestConsecutive(
                new int[]{100,4,200,1,3,2}
        ));


        System.out.println(threeSum(
                new int[]{-1,0,1,2,-1,-4}
        ));


        System.out.println(totalFruit(
                new int[]{1,2,1}
        ));


        System.out.println(subarraySum(
                new int[]{1,1,1}, 2
        ));


        System.out.println(maxArea(
                new int[]{1,8,6,2,5,4,8,3,7}
        ));


        System.out.println(minWindow(
                "ADOBECODEBANC",
                "ABC"
        ));
    }
}
