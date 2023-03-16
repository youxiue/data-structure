package com.youxiue.tree;

import lombok.Data;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by xfb on 2020/07/30 21:48.
 * 赫夫曼编码
 */
public class HuffmanCodingDemo {

    @Test
    public void test() {
        // 将字符串进行赫夫曼编码
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        // 将每个字节都转成 Node
        List<HfNode> nodeList = getNodes(contentBytes);
        System.out.println(nodeList);

        // 创建赫夫曼树
        HfNode hfNode = createHfNode(nodeList);
        preOrder(hfNode);

        // 获取 字符串编码
        Map<Byte, String> codeMap = getCodes(hfNode);
        // 将字符串字节数组 使用赫夫曼树 转成新的字节数组
        byte[] hfCoding = getHfCoding(contentBytes, codeMap);
        System.out.println(Arrays.toString(hfCoding));


        // 再将编码后的字节数组 解码成对应的字符串
        // 1.先将对应的字节数组转成对应的二进制字符串
        // 2.与编码表进行匹配 再转回 原始的 字节数组
        byte[] decodeBytes = decode(hfCoding, codeMap);
        // 3.最后再将字节数组转为对应的字符串
        System.out.println(new String(decodeBytes)); // i like like like java do you like a java

    }

    /**
     * 压缩测试
     */
    @Test
    public void zipTest() {
        String srcFile = "F:\\壁纸\\dio.txt";
        //String srcFile = "F:\\steam\\steamapps\\workshop\\content\\431960\\2073869334\\[标清](3).mp4";
        String dstFile = "F:\\壁纸\\shipin.zip";

        huffmanZip(srcFile, dstFile);
    }

    @Test
    public void encodeZipTest() {
        String srcFile = "F:\\壁纸\\shipin.zip";
        String dstFile = "F:\\壁纸\\jojo.jpg";

        encodeZip(srcFile, dstFile);
    }


    public void encodeZip(String srcFile, String dstFile) {
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        System.out.println("nihao");
        try {
            // 读取文件流
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> codeMap = (Map<Byte, String>) ois.readObject();

            byte[] decode = decode(huffmanBytes, codeMap);
            System.out.println("nihao");
            os = new FileOutputStream(dstFile);
            os.write(decode);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 压缩文件
     *
     * @param srcFile 被压缩文件路径
     * @param dstFile 输出文件路径
     */
    public void huffmanZip(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 读取文件流
            is = new FileInputStream(srcFile);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            // 压缩成对应的赫夫字节数组
            Map<Byte, String> codeMap = getCodeMap(bytes);
            byte[] huffmanBytes = getHfCoding(bytes, codeMap);

            // 写出 压缩后的
            os = new FileOutputStream(dstFile); // 1292296
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            // 写出 赫夫曼编码
            oos.writeObject(codeMap);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Map<Byte, String> getCodeMap(byte[] contentBytes) {
        // 转成树节点
        List<HfNode> nodeList = getNodes(contentBytes);

        // 创建赫夫曼树
        HfNode hfNode = createHfNode(nodeList);

        // 获取 字符串编码
        Map<Byte, String> codeMap = getCodes(hfNode);
        return codeMap;
    }

    /**
     * 将字节数组转成 节点集合
     */
    public List<HfNode> getNodes(byte[] bytes) {
        // 先获取每个字节对应的数量
        Map<Byte, Integer> map = new HashMap<>();

        for (int i = 0; i < bytes.length; i++) {
            if (map.containsKey(bytes[i])) {
                map.put(bytes[i], map.get(bytes[i]) + 1);
            } else {
                map.put(bytes[i], 1);
            }
        }

        ArrayList<HfNode> nodeList = new ArrayList<>();
        // 然后将字节和对应的数量转成对应的 Node
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            HfNode hfNode = new HfNode(entry.getKey(), entry.getValue());
            nodeList.add(hfNode);
        }
        return nodeList;
    }

    /**
     * 将 节点集合 转成 赫夫曼树
     *
     * @param nodeList
     */
    public HfNode createHfNode(List<HfNode> nodeList) {

        while (nodeList.size() > 1) {
            // 进行排序
            Collections.sort(nodeList);
            // 然后将前两个node 组合成一个树
            HfNode left = nodeList.get(0);
            HfNode right = nodeList.get(1);
            HfNode parent = new HfNode(null, left.getWeight() + right.getWeight());
            parent.left = left;
            parent.right = right;

            // 移除被合并的前两个节点
            nodeList.remove(left);
            nodeList.remove(right);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    /**
     * 将赫夫曼树转为编码
     */
    public Map<Byte, String> getCodes(HfNode hfNode) {
        // 向左为0  向右为1

        Map<Byte, String> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        getCodes(map, hfNode, "", sb);
        /*for (Map.Entry<Byte, String> byteStringEntry : map.entrySet()) {
            System.out.println(byteStringEntry.getKey() + "----" + byteStringEntry.getValue());
        }*/
        return map;
    }


    public void getCodes(Map<Byte, String> map, HfNode node, String code, StringBuffer sb) {
        // 左为0  向右为1
        StringBuffer stringBuffer = new StringBuffer(sb);
        stringBuffer.append(code);
        if (node != null) {
            if (node.getValue() == null) {
                // 如果不是叶子节点
                // 则继续递归
                getCodes(map, node.left, "0", stringBuffer);

                getCodes(map, node.right, "1", stringBuffer);
            } else {
                // 如果是叶子节点
                map.put(node.getValue(), stringBuffer.toString());
            }
        }
    }

    // 将原始的字节数组 转成使用 赫夫曼编码的字节数组
    public byte[] getHfCoding(byte[] contentBytes, Map<Byte, String> codeMap) {
        // 先将原本的原始的字节数组 转成使用赫夫曼编码 的字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < contentBytes.length; i++) {
            sb.append(codeMap.get(contentBytes[i]));
        }
        //System.out.println(sb.toString());//1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        // 然后将字符串每8位切割成一个, 转成字节

        // 获取最后转成的字节数组长度 (最后不足8位 , 也算1个)
        int len = (sb.length() + 7) / 8;
        System.out.println(sb.toString());
        byte[] resultByte = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String substring;
            if ((i + 8) <= sb.length()) {
                substring = sb.substring(i, i + 8);
            } else {
                substring = sb.substring(i);
            }
            resultByte[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return resultByte;
    }

    public void preOrder(HfNode node) {
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("weikong!");
        }
    }

    @Test
    public void test2() {
        String s = toBinaryString(true, -1);
        System.out.println(s);
    }

    public byte[] decode(byte[] hfCoding, Map<Byte, String> codeMap) {
        //1.先将对应的字节数组转成对应的二进制字符串
        //StringBuffer sb = new StringBuffer(); // 1292296
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < hfCoding.length; i++) {
            if (i < hfCoding.length - 1) {
                sb.append(toBinaryString(true, hfCoding[i]));
            } else {
                sb.append(toBinaryString(false, hfCoding[i]));
            }
        }
        //System.out.println(sb.toString());
        // 2.与编码表进行匹配 再转回 原始的 字节数组
        // 2.1 先将编码表翻转 , 便于根据编码找到对应的 原始字节
        Set<Map.Entry<Byte, String>> entries = codeMap.entrySet();
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : entries) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 2.2 对字符串进行截取匹配
        //String s = sb.toString();
        System.out.println(sb.toString());
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;

            while (flag) {
                String cur = sb.substring(i, i + count);//10338361 10338362  1038354 61
                if (map.containsKey(cur)) {
                    flag = false;
                    list.add(map.get(cur));
                } else {
                    count++;
                }
            }
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
            //System.out.println(list.get(i));
        }
        return bytes;
    }

    // 字节转对应的二进制字符串
    private String toBinaryString(boolean flag, int b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);
        if (flag) {
            str = str.substring(str.length() - 8);
        }
        System.out.println("---" + str);
        return str;
    }

}

@Data
class HfNode implements Comparable<HfNode> {
    private Byte value;

    private Integer weight;

    public HfNode left;

    public HfNode right;

    public HfNode(Byte value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "HfNode{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(HfNode o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.left.preOrder();
        }
        if (this.getRight() != null) {
            this.right.preOrder();
        }
    }
}