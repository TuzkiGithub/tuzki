package cn.spring.ssm.blockchain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.blockchain
 * User: 25414
 * Date: 2020/2/10
 * Time: 8:54
 * Description:区块定义
 */
public class Block {
    /**
     * 当前区块的hash
     */
    public String hash;

    /**
     * 前一个区块的hash
     */
    public String previousHash;

    /**
     * 当前区块的数据
     */
    private String data;

    /**
     * 时间戳
     */
    private long timeStamp;


    /**
     * 随机数
     */
    private int nonce;


    public Block(String hash, String previousHash, String data) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
    }

    public Block(String data, String previousHash) throws Exception {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * 计算哈希值
     * 根据前一个区块的哈希值，时间戳，随机数，数据计算
     *
     * @return
     * @throws Exception
     */
    public String calculateHash() throws Exception {
        return SignUtils.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data);
    }

    /**
     * 模拟挖矿
     *
     * @param difficulty
     * @throws Exception
     */
    public void mineBlock(int difficulty) throws Exception {
        String target = new String(new char[difficulty]).replace('\0', '0');

        //模拟算术问题
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
            System.out.println("Block Mined hash = " + hash);
        }
    }

}
