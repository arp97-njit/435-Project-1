import java.util.*;

class avlIter {
    
    public static void main(String[] args) {
        avlIter tree = new avlIter();
        
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
    
    void inorder()  {
         inorderRec(root);
      }
    
      void inorderRec(Node root) {
          if (root != null) {
              inorderRec(root.left);
            //  System.out.println(root.value);
              inorderRec(root.right);
          }
      }

    
    class Node {
        int value;
        int height;
        int bf;
        Node left, right, parent;

        public Node(int item) {
            value = item;
            height = 1;
            bf = 0;
            left = right = parent = null;
        }
    }

    Node root;

    avlIter() {
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
    



    //insert iterate - avl
    Node insertIter(Node root, int value) {
       Node current = root;
        int traversalCounter = 0;
            
            while(current != null){
                if (current.value < value){
                    if(current.right == null){
                        current.right = new Node(value);
                        current.right.parent = current;
                        current = current.right;
                        traversalCounter++;
                        break;
                    }
                    else{
                        traversalCounter++;
                        current = current.right;
                    }
                }
                else if (current.value > value){
                    if(current.left == null){
                        current.left = new Node(value);
                        current.left.parent = current;
                        current = current.left;
                        traversalCounter++;
                        break;
                    }
                    else{
                        traversalCounter++;
                        current = current.left;
                    }
                }
            }
            if (current == null) {  //makes new node 1 time
                current = new Node(value);
                System.out.println("traveled: " + traversalCounter + "levels to insert - " + value);
                return current;   //do i need to remove this return??? - probably
                
            }
        
        System.out.println("traveled: " + traversalCounter + "levels to insert - " + value);
       
        
        Node cpCurrForHeight = current; //makes a copy of current (bottom of tree) that will be used to iterate up the parents to adjust their heights
        //now that the heights of all nodes are correct - untested but probably
        
        int heightCounter = 1; //will keep track of actual iterations
        while (cpCurrForHeight.parent != null){  //updates heights every time insertion
            heightCounter++;
            cpCurrForHeight = cpCurrForHeight.parent;
            if(cpCurrForHeight.height > heightCounter){
                break; //break out of while loop bc the node we inserteds' height doesn't effect our tree heights
            }
            else{
                cpCurrForHeight.height = heightCounter;  //update the tree heights
            }
            
        }
        
        //then need to calculate the BFs of a given node
        //should probably make this it's own function too - that way height and BF can run whenever and just update everything
        Node cpCurrForBF = current;
        while(cpCurrForBF != null){  //updates BFs every time insertion
            int leftHeight = 0;
            int rightHeight = 0;
            
            if(cpCurrForBF.left != null){
                leftHeight = cpCurrForBF.left.height;
            }
            if(cpCurrForBF.right != null){
                rightHeight = cpCurrForBF.right.height;
            }
            int balanceFactor = leftHeight - rightHeight;  //get bf
            cpCurrForBF.bf = balanceFactor;  //add bf to new node
            System.out.println(cpCurrForBF.value +" "+ cpCurrForBF.bf);
            cpCurrForBF = cpCurrForBF.parent; //iterate up - will update all BFs on every node insert
           
        }

        //now that all BFs and Heights are attached to their nodes
        //time to check the balance - rotate when needed
        Node cpCurrForReBalance = current;
        while(cpCurrForReBalance != null){
            
            if(cpCurrForReBalance.bf == 1 || cpCurrForReBalance.bf == 0 ||cpCurrForReBalance.bf == -1 ){
                cpCurrForReBalance = cpCurrForReBalance.parent;
            }
            else{
                selfBalance(cpCurrForReBalance);
            }
        }
        
            return current;  //won't happen but java
    }
    
    void selfBalance(Node curr){
        Node toRebalance = curr;

        
        if(toRebalance.bf > 1){
            if(toRebalance.left.bf < 0){
                //do left rotation  - left right case
                rotateLeft(toRebalance, 1); //1 is a flag
            }
            
            //do right rotation
            rotateRight(toRebalance, 0); //0 is a flag
            
        }
        else if(toRebalance.bf < -1){
            
            if(toRebalance.right.bf > 0){
                //do right rotation - right left case
                rotateRight(toRebalance, 0);
            }
            
            //do left rotation
            rotateLeft(toRebalance, 1);
        }
    }
    
    void rotateRight(Node toRebalance, int flagToCheck){  //1 - left, 0 - right
        //do rotation - right
        Node pivot = toRebalance.left.left;
        Node pivotParent = toRebalance.left;
        Node pivotGrandParent = toRebalance;
        
        if (pivot == null){ //just in case
            return;
        }
        
        pivotParent.left = pivot.right;
        pivot.right = pivotParent;
        
        //fix connections
        if(pivotGrandParent == null){
            root = pivot;  //root of tree is now pivot
        }
        else if(flagToCheck == 1){
            pivotGrandParent.left = pivot;
        }
        else{
            pivotGrandParent.right = pivot;
        }
        
        updateHeight(pivotParent);
        updateBalanceFactor(pivotParent);
        updateHeight(pivot);
        updateBalanceFactor(pivot);
        
    }
    
    void rotateLeft(Node toRebalance, int flagToCheck){
        //do rotation - left
        Node pivot = toRebalance.right.right;
        Node pivotParent = toRebalance.right;
        Node pivotGrandParent = toRebalance;
        
        if(pivot == null){ //just in case
            return;
        }
        
        pivotParent.right = pivot.left;
        pivot.left = pivotParent;
        
        //fix connections
        if(pivotGrandParent == null){
            root = pivot;
        }
        else if(flagToCheck == 1){
            pivotGrandParent.left = pivot;
        }
        else{
            pivotGrandParent.right = pivot;
        }
        
        updateHeight(pivotParent);
        updateBalanceFactor(pivotParent);
        updateHeight(pivot);
        updateBalanceFactor(pivot);
        
    }
    
    void updateBalanceFactor(Node curr){ //per node
        Node bfTracker = curr;
        int left = 0;
        int right = 0;
        if(bfTracker.left != null){
            left = bfTracker.left.height;
        }
        if(bfTracker.right != null){
            right = bfTracker.right.height;
        }
        int balanceFactor = left - right;
        bfTracker.bf = balanceFactor;
    
    }
    
    void updateHeight(Node curr){ //per node
        Node heightTracker = curr;
        int left = 0;
        int right = 0;
        if(heightTracker.left != null){
            left = heightTracker.left.height;
        }
        if(heightTracker.right != null){
            right = heightTracker.right.height;
        }
        heightTracker.height = (Math.max(left, right) + 1); //+1 bc higher level
        
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
    

    
}


