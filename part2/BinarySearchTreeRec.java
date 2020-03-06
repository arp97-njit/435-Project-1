class BinarySearchTreeRec {
    
    public static void main(String[] args) {  //driver from geeksforgeeks.org
        BinarySearchTreeRec tree = new BinarySearchTreeRec();
        
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

    BinarySearchTreeRec() {
        root = null;
    }
    
    //find next largest recursive
    int findNextRec(Node randomNode){  //not root bc we don't know where we are starting
        //value = randomNode.value
        //given input of a node, find the next largest value in the tree
        //check if right node exists
            //else parent
            //if right exists, check if left exists
                //resursive call to left, else right and check again
        
        Node curr = randomNode;
        
        if (curr.right == null){  //there was nothing larger
            return -1;  //-1 is an error case
        }
        if (curr.right != null){            //not exactly it bc it will check right of left but we just want left
            if (curr.right.left != null){
                //left exists
                findNextRec(curr.right.left);
            }
            else {
                findNextRec(curr.right);
            }
        }
        
        //need to check right child recursively until a left child is found
        //at this point, need to check left child recusivly until none left
        
        
        
        
        
        
        
        
        return randomNode.value;
    }
    
    
    //find previous - find next smallest recursive
    int findPrevtRec(Node root, int value){
        return root.value;
    }
    
    //find smallest value recursive     - done
    int findMinRec(Node root){
        Node curr = root;
        if (curr.left != null){
            findMinRec(curr.left);
        }
        return curr.value;
    }
    
    //find largest value recursive       - done
    int findMaxRec(Node root){
        Node curr = root;
        if (curr.right != null){
            findMinRec(curr.right);
        }
        return curr.value;
    }
    



    //insert recursive                  - done
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
    
    //delete recursive
    void deleteRec(int value){
        root = deleteHelper(root, value);
    }
    
    Node deleteHelper(Node root, int value){
        if (root == null){
            return root;
        }
        else {
            if (root.value < value){
                root.right = deleteHelper(root.right, value);
            }
            else if (root.value > value){
                root.left = deleteHelper(root.left, value);
            }
            else { //found our node
                return root.right;
            }
            
        }
        return root;
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

