import java.util.*;
import java.io.*;

class BSTSort {
    
    ArrayList<Integer> arr = new ArrayList<Integer>();
    
    public static void main(String[] args) {  //driver from geeksforgeeks.org
        BSTSort tree = new BSTSort();
        
        int inputArr[] ={50,20,30,40,70,60,80};
        for (int i = 0; i < inputArr.length; i++){
            tree.insertRec(inputArr[i]);
        }
        tree.sort(tree.root);
        for(int j = 0; j < tree.arr.size(); j++){
            System.out.println(tree.arr.get(j));
        }
        

    }
    
    class Node {
        int value;
        Node left, right;

        public Node(int item) {
            value = item;
            left = right = null;
        }
    }

    Node root;

    BSTSort() {
        root = null;
    }
    
    
    
    void sort(Node root){
        Node curr = root;
        if (curr != null){
            sort(curr.left);
            arr.add(curr.value);
            sort(curr.right);
        }
    }
    
    

    //insert recursive  from 1c
    void insertRec(int value) {
    root = insertHelper(root, value);
    }
    
    Node insertHelper(Node root, int value) {
        if (root == null) {  //makes new node
            root = new Node(value);
            return root;
        }
        else if(root.value < value){  //if greater than
            root.right = insertHelper(root.right, value);
        }
        else if(root.value > value){   //less than
            root.left = insertHelper(root.left, value);
        }
        

        return root;  //won't happen but java
    }
    
}

