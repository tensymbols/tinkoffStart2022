
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        BitTree trie = new BitTree();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int q = sc.nextInt();
        for(int i =0; i<q; i++){
            int currNum = sc.nextInt();
            numbers.add(currNum);
            trie.insert(currNum);
            int maxXOR = 0;
            for(int j = 0; j<numbers.size(); j++){
                maxXOR = Math.max(trie.maxXOR(numbers.get(j)),maxXOR);
            }
            System.out.println(maxXOR);
        }
    }

}
class Node {
    Node leaves[]=new Node[2];
    public Node(){

    }
    void insert(int bit, Node node){
        leaves[bit]=node;
    }
    boolean contains(int bitIx){
        return leaves[bitIx]!=null;
    }
    Node get(int bitIx){
        return leaves[bitIx];
    }
}
class BitTree{
    private static Node root;
    public BitTree(){
        root = new Node();
    }
    void insert(int num){
        Node node = root;
        for(int i = 31; i>=0; i--){
            int currBit = (num >> i) & 1;
            if(!node.contains( currBit )){
                node.insert(currBit, new Node());
            }
            node = node.get(currBit);
        }
    }
    public int maxXOR(int num){
        Node node = root;
        int maxNumber = 0;
        for(int i = 31; i>=0; i--){
            int currBit = (num >> i) & 1;
            if(node.contains(1-currBit)){
                maxNumber |= (1<<i);
                node = node.get(1-currBit);
            }
            else{
                node = node.get(currBit);
            }
        }
        return maxNumber;
    }

}
