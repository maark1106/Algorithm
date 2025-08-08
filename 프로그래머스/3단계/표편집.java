import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        Node[] nodes = new Node[n];
        
        nodes[0] = new Node();
        Stack<Node> stack = new Stack<>();
        for(int i = 1; i < n; i++){
            nodes[i] = new Node();
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }
        
        Node cur = nodes[k];
        for(int i = 0 ; i < cmd.length; i++){
            if(cmd[i].charAt(0) == 'D'){
                int cnt = Integer.parseInt(cmd[i].substring(2));
                for(int j = 0; j < cnt; j++){
                    cur = cur.next;
                }
            }
            else if(cmd[i].charAt(0) == 'U'){
                int cnt = Integer.parseInt(cmd[i].substring(2));
                for(int j = 0; j < cnt; j++){
                    cur = cur.prev;
                }
            }
            else if(cmd[i].charAt(0) == 'C'){
                cur.isDeleted = true;
                stack.push(cur);
                if(cur.next != null){
                    cur.next.prev = cur.prev;
                }
                if(cur.prev != null){
                    cur.prev.next = cur.next;
                }
                
                if(cur.next != null){
                    cur = cur.next;
                }
                else{
                    cur = cur.prev;
                }
            }
            else if(cmd[i].charAt(0) == 'Z'){
                Node node = stack.pop();
                node.isDeleted = false;
                if(node.next != null){
                    node.next.prev = node;                    
                }
                if(node.prev != null){
                    node.prev.next = node;
                }
            }
        }
        
        for(int i = 0 ; i < n; i++){
            sb.append(nodes[i].isDeleted ? "X" : "O");
        }
        
        return sb.toString();
    }
    
    class Node{
        Node prev = null;
        Node next = null;
        boolean isDeleted = false;       
    }
}
