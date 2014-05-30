/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author luis
 */
public class Lock {
        private boolean isLocked;
        private static Lock theLock=null;
        private Lock(){
            isLocked=false;
        }
        public static Lock getInstance(){
            if(theLock==null){
                theLock=new Lock();
            }
            return theLock;
        }
        public synchronized void lock()
                throws InterruptedException {
            while (isLocked) {
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }
