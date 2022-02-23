package code2047;

/*
    句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
    每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
    如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
        1、仅由小写字母、连字符和/或标点（不含数字）。
        2、至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
        3、至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
    这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
    给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
*/

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener.");
    }

    public int countValidWords(String sentence) {
        String[] sentences = sentence.split(" ");
        DataStack stack = new DataStack();
        int num = 0;
        for(int i = 0 ; i < sentences.length ; i++){
            int j = 0;
            for( ; j < sentences[i].length() ; j++){
                char c = sentences[i].charAt(j);
                char topC = stack.getTop();
                if(Character.isDigit(c)){
                    stack.length = 0;
                    stack.lianFlag = false;
                    stack.biaoFlag = false;
                    break;
                }
                else if(Character.isLetter(c) && stack.biaoFlag){
                    stack.length = 0;
                    stack.lianFlag = false;
                    stack.biaoFlag = false;
                    break;
                }
                else if(c == '-' && (!Character.isLetter(topC) || stack.lianFlag)){
                    stack.length = 0;
                    stack.lianFlag = false;
                    stack.biaoFlag = false;
                    break;
                }
                else if((c == ',' || c == '.' || c == '!') && (stack.biaoFlag || topC == '-')){
                    stack.length = 0;
                    stack.lianFlag = false;
                    stack.biaoFlag = false;
                    break;
                }else{
                    if(c != ' '){
                        stack.push(c);
                    }
                    if(c == '.' || c == ',' || c == '!'){
                        stack.biaoFlag = true;
                    }
                    if(c == '-'){
                        stack.lianFlag = true;
                    }
                }
            }
            if(j == sentences[i].length() && stack.getTop() != '-' && sentences[i].length() != 0 && (sentences[i].length() == stack.length)){
                num++;
            }
            stack.length = 0;
            stack.lianFlag = false;
            stack.biaoFlag = false;
        }
        return num;
    }
}

class DataStack{

    private int size = 18;
    private char[] stack = new char[size];
    public int length = 0;
    public boolean lianFlag = false;
    public boolean biaoFlag = false;

    public int push(char data){
        stack[length] = data;
        length++;
        return length - 1;
    }

    public char getTop(){
        if(length == 0){
            return ' ';
        }else{
            return stack[length - 1];
        }
    }

}