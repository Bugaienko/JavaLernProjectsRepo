package lesson03;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * @author Sergii Bugaienko
 * Haffman algorith
 */

public class Compress {
    public static void main(String[] args) {
        String text = String.format("A team of researchers led by Cambridge University analysed five locks of hair to sequence the composer's genome. They were, however, unable to establish a definitive cause of his hearing loss.\n Lead author, Tristan Begg, said genetic risk factors, coupled with Beethoven's high alcohol consumption, may have contributed to his liver condition.\n The international team analysed strands from eight locks of hair kept in public and private collections, in a bid to shed light on Beethoven's health problems.\n Five locks were deemed 'authentic' by the researchers and came from a single European male.");

        String milk = "MOLOKO";

        //1. частота
        TreeMap<Character, Integer> freq = countChar(text);

        //2. Генерируем список узлов
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for (Character c :freq.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, freq.get(c)));
        }

        //3. Строим кодовое дерево
        CodeTreeNode tree = getHuff(codeTreeNodes);

        //4. Генерируем таблицу кодов для наших символов
        TreeMap<Character, String> codes = new TreeMap<>();
        for (Character c : freq.keySet()) {
            codes.put(c, tree.getCodeForChar(c, ""));
        }

        // 5. Наши коды
        System.out.println("Code table: " + codes);

        //6. Кодируем текст, заменяя символы соответсвующими кодами
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Text size: " + text.getBytes().length * 8 + " BIT");
        System.out.println("Compressed text size: " + encoded.length() + " BIT");

        System.out.println("Compressed text bits: " + encoded);

        //Раскодируем

        String decoded = getDecode(encoded.toString(), tree);
        System.out.println("Decoded text: " + decoded);

    }

    //1. Подсчет количества символов

    private static TreeMap<Character, Integer> countChar(String text) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = map.get(c);
            map.put(c, count != null ? count + 1 : 1);
        }
        return map;
    }

    //2. Дерево
    private static class CodeTreeNode implements Comparable<CodeTreeNode> {
        Character content;
        int weight; //кол-во повторений
        CodeTreeNode left;
        CodeTreeNode right;

        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }
        //4. Обойти дерево и выстроить символы. Кодировать текст
        public String getCodeForChar(Character ch, String parentPath) {
            if (content == ch) {
                return parentPath;
            } else {
                if (left != null) {
                    String path = left.getCodeForChar(ch, parentPath + 0);
                    if (path != null) {
                        return path;
                    }
                }
                if (right != null) {
                    String path = right.getCodeForChar(ch, parentPath + 1);
                    if (path != null) {
                        return path;
                    }
                }
            }
            return null;
        }
    }

    //3. Алгоритм обхода, который принимает список узлов,
    // а возвращать будет дерево

    private static CodeTreeNode getHuff(ArrayList<CodeTreeNode> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size()-1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size()-1);
            CodeTreeNode parent = new CodeTreeNode(null, left.weight + right.weight, left, right);

            codeTreeNodes.add(parent);
        }
        return codeTreeNodes.get(0);
    }

    private static String getDecode(String encoded, CodeTreeNode tree) {
        StringBuilder decoded = new StringBuilder();

        CodeTreeNode node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();

    }
}
