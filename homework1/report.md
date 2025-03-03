# JAVA：作业1 简要报告

###### 刘泓尊  2018011446  计84  liu-hz18@mails.tsinghua.edu.cn

------

### programming-1

采用**按位加法**的策略。

先以string方式读入输入的两个整数，然后将该整数按“**低位在前**”的形式存入私有的int[]数组，完成对象的创建。

之后将两数中较长的一个复制到temp数组，在temp数组上和另一个数组按位做原地加法。最后清除末尾的0，存入原始对象的int[]数组。

输出时只需要将int[]数组添加到字符串的末尾，再输出到控制台即可。

###### 复杂度:

$$
O(n)
$$

------

### programming-2

采用循环遍历验证的方式。先检验每个子矩阵M_ij是否满足要求，再检验每行每列是否满足要求。

对于每个子矩阵块，或行，或列，只需要维护一个长度为M的count[]数组，初始化为0。根据鸽笼原理，只需要遍历该块，验证每个count都为1即可。单步复杂度O(m)

因为有n^2 = m个子矩阵块，m行m列。故时间复杂度为：
$$
O((m+m+m)*m) = O(3m^2)
$$
实际上，对于完全随机数据，往往在前几个子矩阵便已经不满足要求而返回。故随机数据的复杂度远低于O(3m^2)。

对于“yes”的情况，复杂度才会跑满。