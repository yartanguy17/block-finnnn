package com.ebanking.blockchain.entity;

public class Miner {

   private Blockchain blockchain;
   private int difficult;

    public Block mineBlock(String data){

       int nonce = 0;
       var lastBlock = this.blockchain.last();
       int difficult = lastBlock.getDifficult();
       int index = lastBlock.getIndex() + 1;
       String previousHash = lastBlock.getPreviousHash();

       while (true){
            var hash = Block.calculateHash(index,previousHash,data,difficult,nonce);
            if ()
       }

    }
}
