package main.kernkraft.javachain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.List;

public class javachain {

    public  static List<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;


    public static void main(String[] args){
        blockchain.add(new Block("First Block","0"));
        System.out.println("Mining genesis block!");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 3");
        blockchain.get(2).mineBlock(difficulty);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\n The block chain: ");
        System.out.println(blockchainJson);
    }
    // check the validity
    public static Boolean Validity() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Hash not equal!");
                return false;
            }

            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Previous hash is not equal!");
                return false;
            }
        }
        return true;
    }
}
