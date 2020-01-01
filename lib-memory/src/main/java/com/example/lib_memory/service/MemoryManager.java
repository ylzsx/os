package com.example.lib_memory.service;

import com.example.lib_memory.MemoryConstants;
import com.example.lib_memory.bean.MemoryBlock;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author YangZhaoxin.
 * @since 2019/12/29 21:05.
 * email yangzhaoxin@hrsoft.net.
 */

public class MemoryManager {

    private MemoryBlock[] memory;

    private MemoryManager() {}

    private static class InstanceHolder {
        private static final MemoryManager INSTANCE = new MemoryManager();
    }

    public static MemoryManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void init() {
        memory = new MemoryBlock[MemoryConstants.MEMORY_BLOCK_TOTAL];
        for (int i = 0; i < MemoryConstants.MEMORY_BLOCK_TOTAL; i++) {
            memory[i] = new MemoryBlock();
        }
    }

    /**
     * 内存分配
     * @return  分配到的内存首地址
     * TODO：之后若写进程也需要占用内存，则需要增加一个分配部分内存的方法
     */
    public ArrayList<Integer> malloc() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < memory.length; i = i + 4) {
            if (!memory[i].isOccupied()) {
                for (int j = i; j < i + MemoryConstants.FILE_MEMORY_BLOCKS; j++) {
                    memory[j].setOccupied(true);
                    result.add(j);
                }
                return result;
            }
        }
        return null;
    }

    /**
     * LRU 模拟内存置换
     * @param memoryBlocks  该线程占用的内存块号
     * @param content   要置换的内容
     */
    public boolean LRU(ArrayList<Integer> memoryBlocks, String content) {
        // 获取到将要被替换的内存块号
        int maxTime = -1;
        int replacedMemoryBlock = -1;
        for (Integer memoryBlock : memoryBlocks) {
            if (maxTime < memory[memoryBlock].getAccessTime()) {
                maxTime = memory[memoryBlock].getAccessTime();
                replacedMemoryBlock = memoryBlock;
            }
        }

        // 进行内存块内容替换，并将其时间置0
        for (Integer memoryBlock : memoryBlocks) {
            if (memoryBlock == replacedMemoryBlock) {
                memory[memoryBlock].setAccessTime(0);
                memory[memoryBlock].setContent(content);
            } else {
                memory[memoryBlock].setAccessTime(memory[memoryBlock].getAccessTime() + 1);
            }
        }
        return true;
    }

    /**
     * 释放线程占用的内存块
     * @param memoryBlocks
     * @return
     */
    public int free(ArrayList<Integer> memoryBlocks) {
        int result = 0;
        for (Integer memoryBlock : memoryBlocks) {
            memory[memoryBlock].setAccessTime(0);
            memory[memoryBlock].setOccupied(false);
            memory[memoryBlock].setContent("");
            result++;
        }
        return result;
    }


    /**
     * 展示当前的内存情况
     * @return
     */
    public ArrayList<MemoryBlock> showMemory() {
        ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>(Arrays.asList(memory));
        return memoryBlocks;
    }

    public MemoryBlock[] getMemory() {
        return memory;
    }
}
