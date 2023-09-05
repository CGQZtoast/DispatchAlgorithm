package com.zard.backend.util;

import java.util.Arrays;

/**
 * @Author 乌贼
 * @Date 2023-09-04 8:36
 * @Description
 */

public class KMAlgorithm {
    private static int[][] graph; // 假设graph的行是顶点X集合（其中的顶点简称X顶点），列是顶点Y集合（其中的顶点简称Y顶点）
    private static int xCount; // X集合中元素个数
    private static int yCount; // Y集合中元素个数
    private static boolean[] xUsed; // 在每次循环中每个X顶点是否访问过
    private static boolean[] yUsed; // 在每次循环中每个Y顶点是否访问过
    private static int[] match; // 每个Y顶点匹配的X顶点，即第i个Y顶点匹配的是第match[i]个X顶点

    private static int[] X; // 每个X顶点的顶标，初始化为最大权值
    private static int[] Y; // 每个Y顶点的顶标，初始化为0

    private static int[] less;

    private static final int INFINITE = Integer.MAX_VALUE;

    private static void init(int[][] weights) {
        graph = weights;

        xCount = graph.length;
        yCount = graph[0].length;

        xUsed = new boolean[xCount];
        Arrays.fill(xUsed, false);

        yUsed = new boolean[yCount];
        Arrays.fill(yUsed, false);

        match = new int[yCount];
        Arrays.fill(match, -1);

        // 初始化每个X顶点的顶标为与之相连的边中最大的权
        X = new int[xCount];
        for (int i = 0; i < xCount; i++) {
            X[i] = graph[i][0];
            for (int l = 0; l < yCount; l++) {
                X[i] = Math.max(X[i], graph[i][l]);
            }
        }

        // 初始化每个Y顶点的顶标为0
        Y = new int[yCount];
        for (int i = 0; i < yCount; i++) {
            Y[i] = 0;
        }

        less = new int[yCount];
    }

    public static int[] KM(int[][] weights) {
        init(weights);

        // 遍历每个X顶点
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                less[j] = INFINITE;
            }
            while (true) {  // 寻找能与X顶点匹配的Y顶点，如果找不到就降低X顶点的顶标继续寻找
                // 初始化为未访问
                for (int j = 0; j < xCount; j++) {
                    xUsed[j] = false;
                }
                for (int j = 0; j < yCount; j++) {
                    yUsed[j] = false;
                }

                if (hungaryDFS(i)) break;  // 寻找到匹配的Y顶点，退出

                // 如果没有找到能够匹配的Y顶点，则降低X顶点的顶标，提升Y顶点的顶标，再次循环
                int diff = INFINITE;        // diff是顶标变化的数值
                for (int j = 0; j < yCount; j++) {
                    if (!yUsed[j]) diff = Math.min(diff, less[j]);
                }
                // diff等于为了使该顶点X能够匹配到一个Y顶点，其X的顶标所需要降低的最小值

                // 更新顶标
                for (int j = 0; j < yCount; j++) {
                    if (xUsed[j]) X[j] -= diff;
                    if (yUsed[j]) Y[j] += diff;
                    else less[j] -= diff;
                }
            }
        }

        // 匹配完成，可以输出结果
        int res = 0;
        for (int i = 0; i < xCount; i++) {
            System.out.println("乘客" + i + "匹配司机" + match[i]);
            res += graph[match[i]][i];
        }
        System.out.println("最终最小权值：" + -res);

        return match;
    }

    private static boolean hungaryDFS(int i) {
        // 设置这个X顶点在此轮循环中被访问过
        xUsed[i] = true;

        // 对于这个X顶点，遍历每个Y顶点
        for (int j = 0; j < yCount; j++) {
            if (yUsed[j]) continue;   // 每轮循环中每个Y顶点只访问一次
            int gap = X[i] + Y[j] - graph[i][j];      // KM算法的顶标变化公式

            // 只有X顶点的顶标加上Y顶点的顶标等于graph中它们之间的边的权时才能匹配成功
            if (gap == 0) {
                yUsed[j] = true;
                if (match[j] == -1 || hungaryDFS(match[j])) {
                    match[j] = i;
                    return true;
                }
            } else {
                less[j] = Math.min(less[j], gap);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] weight = {
                {-3, -5},
                {-5, -12}
        };

        KMAlgorithm.KM(weight);

    }
}
