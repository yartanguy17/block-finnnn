package com.ebanking.blockchain.entity;

import com.ebanking.blockchain.common.CommonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blockchain")
public class Blockchain  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "blockChain", nullable = false)
    private ArrayList<Block> blocks;

    public Blockchain( Block block) {
        this.blocks.add(block);
    }

    public void addedBlock(Block block) throws InvalidTargetObjectTypeException {
        if (!this.last().isNextValid(block)){
            throw new InvalidTargetObjectTypeException();
        }
        this.blocks.add(block);
    }

    public Block last() {
      return this.blocks.get(this.blocks.size() - 1);
    }

    public boolean isValide() {
        if (!this.blocks.get(0).isEqual(Block.genesis())) {
            return false;
        }
        int size = this.blocks.size() -1;
        for (int i = 0; i < size; i++) {
            if ( this.blocks.get(i).isNextValid(this.blocks.get(i+1))) {
                return false;
            }
        }
        return true;
    }
}
