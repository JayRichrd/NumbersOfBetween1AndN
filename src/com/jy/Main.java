package com.jy;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		System.out.print("请输入n:");
		n = scanner.nextInt();
		System.out.println("输入的数n:" + n);

		System.out.println("=======================分割线=========================");

		// 开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("从1到" + n + "的这些数中，1出现的次数:" + numbersOfBetween1AndN(n));
		// 结束时间
		long stopTime = System.currentTimeMillis();
		System.out.println("耗时:" + (stopTime - startTime) + "ms");

		System.out.println("=======================分割线=========================");

		// 开始时间
		startTime = System.currentTimeMillis();
		System.out.println("从1到" + n + "的这些数中，1出现的次数:" + numbersOfBetween1AndNRecursively(n));
		// 结束时间
		stopTime = System.currentTimeMillis();
		System.out.println("耗时:" + (stopTime - startTime) + "ms");

		// 释放资源
		scanner.close();
	}

	/**
	 * 求从1到n的数中，1出现的次数
	 * 
	 * @param n
	 *            最大的数
	 * @return 返回1出现的次数
	 */
	public static int numbersOfBetween1AndN(int n) {
		if (n <= 0)
			throw new RuntimeException("Invalid Input!!!");
		// 1总共出现的次数
		int numbers = 0;
		// 循环查找每一个数中1出现的次数
		for (int i = 1; i <= n; i++)
			numbers += numbersOf1(i);
		return numbers;
	}

	/**
	 * 某个数中1的个数
	 * 
	 * @param n
	 *            输入的数
	 * @return 数中1的个数
	 */
	public static int numbersOf1(int n) {
		if (n <= 0)
			throw new RuntimeException("Invalid Input!!!");
		// 1的个数
		int numbers = 0;
		while (n != 0) {
			if (n % 10 == 1) // 对10取余，看个位数是否为1
				numbers++;
			// 每次除以10
			n /= 10;
		}
		return numbers;
	}

	/**
	 * 使用递归的方式，求从1到n的数中，1出现的次数
	 * 
	 * @param n
	 *            最大的数
	 * @return 返回1出现的次数
	 */
	public static int numbersOfBetween1AndNRecursively(int n) {
		if (n <= 0)
			throw new RuntimeException("Invalid Input!!!");
		// 将数字转换成字符
		String value = n + "";
		int[] numbers = new int[value.length()];
		// 填充每一个数组元素
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = value.charAt(i) - '0';
		return numbrsOf1Recursively(numbers, 0);
	}

	/**
	 * 使用递归的方式，求从1到n的数中，1出现的次数
	 * 
	 * @param numbers
	 *            数n对应的数组
	 * @param curIndex
	 *            用来计算的数组开始的位置
	 * @return 返回1出现的次数
	 */
	public static int numbrsOf1Recursively(int[] numbers, int curIndex) {
		int arrayLength = numbers.length;
		if (numbers == null || curIndex >= arrayLength || curIndex < 0)
			return 0;
		// 待处理的第一个数字
		int first = numbers[curIndex];
		// 要处理的位数
		int length = arrayLength - curIndex;
		// 如果要处理的仅仅只有1位，切这位上的数为0
		if (length == 1 && first == 0)
			return 0;
		// 如果要处理的仅仅只有1位，且这位上的数为大于0
		if (length == 1 && first > 0)
			return 1;
		// 处理1出现在最高位上总共有多少
		int numFirstDigit = 0;
		if (first > 1)
			// 假设numbers是21345
			// numFirstDigit是数字10000-19999的第一个位中的数目
			numFirstDigit = (int) Math.pow(10, length - 1);
		else if (first == 1)
			// 如果最高位是1，如12345，在[2346, 12345]中，最高位1出现的只在[10000, 12345]中，总计2345+1个
			numFirstDigit = atoi(numbers, curIndex + 1) + 1;
		// 再计算除了最高位外剩下位数1出现的个数
		int numOtherDigit = (int) (first * (length - 1) * Math.pow(10, length - 2));
		// 在递归计算除去高位后剩下的数
		int numRecursive = numbrsOf1Recursively(numbers, curIndex + 1);
		return numFirstDigit + numOtherDigit + numRecursive;
	}

	/**
	 * 将数字数组转换成数值，如{1, 2, 3, 4, 5}，i = 2，结果是345
	 * 
	 * @param numbers
	 *            数组
	 * @param i
	 *            开始的位置
	 * @return 转换结果
	 */
	private static int atoi(int[] numbers, int i) {
		int result = 0;
		for (int j = i; j < numbers.length; j++)
			result = (result * 10 + numbers[j]);
		return result;
	}

}
