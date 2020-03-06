import java.util.*;

class BinarySearchTreeIter {
    
    public static void main(String[] args) {   //driver from geeksforgeeks.org
        BinarySearchTreeIter tree = new BinarySearchTreeIter();
        
        ArrayList<Integer> toParse = tree.getRandomArray(100);


        for(int i = 0; i < toParse.size(); i++){
            if(i == 0){
                tree.root = tree.insertIter(tree.root, toParse.get(i));
            }
            else{
                tree.insertIter(tree.root, toParse.get(i));

            }
        }
        
        tree.inorder();


    }
    
    void inorder()  {  //for testing only from geeksforgeeks.org
         inorderRec(root);
      }
    
      //for testing only from geeksforgeeks.org
      void inorderRec(Node root) {
          if (root != null) {
              inorderRec(root.left);
              System.out.println(root.value);
              inorderRec(root.right);
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

    BinarySearchTreeIter() {
        root = null;
    }
    
    //find next largest iterate       - while is wrong
    int findNextIter(Node root){
        ArrayList<Integer> valueList = new ArrayList<>();
        Node curr = root;
        
        valueList.add(curr.value);
        while(curr != null){
            if (curr.right != null){
                valueList.add(curr.right.value);
                curr = curr.right;
            }
            else if (curr.left != null){
                valueList.add(curr.left.value);
                curr = curr.left;
            }
        }
        
        Collections.sort(valueList);
        int position = valueList.indexOf(root.value) + 1;
        return valueList.get(position);
        
    }
    
    
    //find previous - find next smallest iterate   - while is wrong
    int findPrevtIter(Node root){
        ArrayList<Integer> valueList = new ArrayList<>();
        Node curr = root;
        
        valueList.add(curr.value);
        while(curr != null){
            if (curr.right != null){
                valueList.add(curr.right.value);
                curr = curr.right;
            }
            else if (curr.left != null){
                valueList.add(curr.left.value);
                curr = curr.left;
            }
        }
        
        Collections.sort(valueList);
        int position = valueList.indexOf(root.value) - 1;
        return valueList.get(position);
    }
    
    //find smallest value iterate  - done
    int findMinIter(Node root){
        Node curr = root;
        while (curr.left != null){
            curr = curr.left;
        }
        return curr.value;
    }
    
    //find largest value iterate   - done
    int findMaxIter(Node root){
        Node curr = root;
        while (curr.right != null){
            curr = curr.right;
        }
        return curr.value;
    }
    



    //insert iterate               - done
    Node insertIter(Node root, int value) {
       Node current = root;
            
            while(current != null){
                if (current.value < value){
                    if(current.right == null){
                        current.right = new Node(value);
                        break;
                    }
                    current = current.right;
                }
                else if (current.value > value){
                    if(current.left == null){
                        current.left = new Node(value);
                        break;
                    }
                    current = current.left;
                }
            }
            if (current == null) {  //makes new node 1 time
                current = new Node(value);
                return current;
            }
        
            return current;  //won't happen but java
    }
    
    //delete iterate
    Node deleteIter(Node root, int value){
        Node current = root;
        while (current != null){
            if (current.value < value){
                current = current.right;
            }
            else if (current.value > value){
                current = current.left;
            }
            else{
                return current.right;
            }
        }
        return current;
    }
    
    ArrayList<Integer> getRandomArray(int n){
        ArrayList<Integer> rtnArray = new ArrayList<Integer>();
        Random rand = new Random();
        int randNum = rand.nextInt(n) + 1;
        int counter = 0;
        while(counter != n){
            if(rtnArray.contains(randNum) == false){
                rtnArray.add(randNum);
                counter++;
            }
            randNum = rand.nextInt(n) + 1;
            
        }
        
        return rtnArray;
        
    }
    
    ArrayList<Integer> getSortedArray(int n){
        ArrayList<Integer> rtnArray = new ArrayList<Integer>();
        for (int i = n; i > 0; i--){
            rtnArray.add(i);
        }
        return rtnArray;
    }
    

    
}


