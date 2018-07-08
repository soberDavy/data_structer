package bineary_search_tree;


import java.util.LinkedList;

// ����������
// ����Key��Ҫ�ܹ����бȽϣ�������Ҫextends Comparable<Key>
public class BST<Key extends Comparable<Key>, Value> {

    // ���еĽڵ�Ϊ˽�е���, ��粻��Ҫ�˽�����������ڵ�ľ���ʵ��
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    private Node root;  // ���ڵ�
    private int count;  // ���ֵĽڵ����

    // ���캯��, Ĭ�Ϲ���һ�ÿն���������
    public BST() {
        root = null;
        count = 0;
    }

    // ���ض����������Ľڵ����
    public int size() {
        return count;
    }

    // ���ض����������Ƿ�Ϊ��
    public boolean isEmpty() {
        return count == 0;
    }

    // ������������в���һ���µ�(key, value)���ݶ�
    public void insert(Key key, Value value){
        root = insert(root, key, value);
    }

    // �鿴�������������Ƿ���ڼ�key
    public boolean contain(Key key){
        return contain(root, key);
    }

    // �ڶ�����������������key����Ӧ��ֵ��������ֵ������, �򷵻�null
    public Value search(Key key){
        return search( root , key );
    }

    // ������������ǰ�����
    public void preOrder(){
        preOrder(root);
    }

    // �������������������
    public void inOrder(){
        inOrder(root);
    }

    // �����������ĺ������
    public void postOrder(){
        postOrder(root);
    }

    // �����������Ĳ������
    public void levelOrder(){

        // ����ʹ��LinkedList����Ϊ���ǵĶ���
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(root);
        while( !q.isEmpty() ){

            Node node = q.remove();

            System.out.println(node.key);

            if( node.left != null )
                q.add( node.left );
            if( node.right != null )
                q.add( node.right );
        }
    }

    // Ѱ�Ҷ�������������С�ļ�ֵ
    public Key minimum(){
        assert count != 0;
        Node minNode = minimum( root );
        return minNode.key;
    }

    // Ѱ�Ҷ��������������ļ�ֵ
    public Key maximum(){
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }

    // �Ӷ�����������ɾ����Сֵ���ڽڵ�
    public void removeMin(){
        if( root != null )
            root = removeMin( root );
    }

    // �Ӷ�����������ɾ�����ֵ���ڽڵ�
    public void removeMax(){
        if( root != null )
            root = removeMax( root );
    }

    // �Ӷ�����������ɾ����ֵΪkey�Ľڵ�
    public void remove(Key key){
        root = remove(root, key);
    }

    //********************
    //* �����������ĸ�������
    //********************

    // ����nodeΪ���Ķ�����������, ����ڵ�(key, value), ʹ�õݹ��㷨
    // ���ز����½ڵ��Ķ����������ĸ�
    private Node insert(Node node, Key key, Value value){

        if( node == null ){
            count ++;
            return new Node(key, value);
        }

        if( key.compareTo(node.key) == 0 )
            node.value = value;
        else if( key.compareTo(node.key) < 0 )
            node.left = insert( node.left , key, value);
        else    // key > node->key
            node.right = insert( node.right, key, value);

        return node;
    }

    // �鿴��nodeΪ���Ķ������������Ƿ������ֵΪkey�Ľڵ�, ʹ�õݹ��㷨
    private boolean contain(Node node, Key key){

        if( node == null )
            return false;

        if( key.compareTo(node.key) == 0 )
            return true;
        else if( key.compareTo(node.key) < 0 )
            return contain( node.left , key );
        else // key > node->key
            return contain( node.right , key );
    }

    // ����nodeΪ���Ķ����������в���key����Ӧ��value, �ݹ��㷨
    // ��value������, �򷵻�NULL
    private Value search(Node node, Key key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) == 0 )
            return node.value;
        else if( key.compareTo(node.key) < 0 )
            return search( node.left , key );
        else // key > node->key
            return search( node.right, key );
    }

    // ����nodeΪ���Ķ�������������ǰ�����, �ݹ��㷨
    private void preOrder(Node node){

        if( node != null ){
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // ����nodeΪ���Ķ��������������������, �ݹ��㷨
    private void inOrder(Node node){

        if( node != null ){
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    // ����nodeΪ���Ķ������������к������, �ݹ��㷨
    private void postOrder(Node node){

        if( node != null ){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    // ������nodeΪ���Ķ�������������С��ֵ���ڵĽڵ�
    private Node minimum(Node node){
        if( node.left == null )
            return node;

        return minimum(node.left);
    }

    // ������nodeΪ���Ķ���������������ֵ���ڵĽڵ�
    private Node maximum(Node node){
        if( node.right == null )
            return node;

        return maximum(node.right);
    }

    // ɾ������nodeΪ���Ķ����������е���С�ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMin(Node node){

        if( node.left == null ){

            Node rightNode = node.right;
            node.right = null;
            count --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // ɾ������nodeΪ���Ķ����������е����ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMax(Node node){

        if( node.right == null ){

            Node leftNode = node.left;
            node.left = null;
            count --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // ɾ������nodeΪ���Ķ����������м�ֵΪkey�Ľڵ�, �ݹ��㷨
    // ����ɾ���ڵ���µĶ����������ĸ�
    Node remove(Node node, Key key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove( node.left , key );
            return node;
        }
        else if( key.compareTo(node.key) > 0 ){
            node.right = remove( node.right, key );
            return node;
        }
        else{   // key == node->key

            // ��ɾ���ڵ�������Ϊ�յ����
            if( node.left == null ){
                Node rightNode = node.right;
                node.right = null;
                count --;
                return rightNode;
            }

            // ��ɾ���ڵ�������Ϊ�յ����
            if( node.right == null ){
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }

            // ��ɾ���ڵ�������������Ϊ�յ����

            // �ҵ��ȴ�ɾ���ڵ�����С�ڵ�, ����ɾ���ڵ�����������С�ڵ�
            // ������ڵ㶥���ɾ���ڵ��λ��
            Node successor = new Node(minimum(node.right));//����successorָ��node������������С�Ľ��
            count ++;

            successor.right = removeMin(node.right);//������ɾ����node������������С�ڵ㣬���ϱ���Ҫnewһ���µı�����������removeMin�У�countҲ�����ˣ���count��������û�иı�
            successor.left = node.left;

            node.left = node.right = null;
            count --;

            return successor;
        }
    }
    
    public Node getRoot(){
    	return root;
    }


}
