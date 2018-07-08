package bineary_search_tree;


/**
 * �ݹ�Ķ��ֲ��ҷ�
 * @author ShiZhiWei
 *	key ��Ҫ���бȽ�
 */
public class BinarySearch<Key extends Comparable<Key>,Value> {
	
	//node�ڵ�����ݽṹ
	private class Node{
		private Key key;//��������
		private Value value;//�ڵ��ֵ
		private Node leftChild;//�ڵ������
		private Node rightChild;//�ڵ���Һ���
		
		public Node(Key key,Value value){
			this.key = key;
			this.value = value;
			this.leftChild = this.rightChild = null;
		}
		public Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.leftChild = node.leftChild;
            this.rightChild = node.rightChild;
		}
	}
	
	
	private Node root;//���ڵ�
	private int count;//���еĽڵ����
	
	private Node insert(Node node,Key key,Value value){
		//���Ϊ����,ֱ�ӽ��µĽڵ���Ϊ���ڵ㷵��
		if(node==null){
			count++;//��������1
			return new Node(key,value);
		}
		
		//���ݼ�ֵ��С���еݹ����	
		if(key.compareTo(node.key)==0){//��ֵ��ȣ�ֱ�Ӹ���
			node.value = value;
		}else if(key.compareTo(node.key)>0){
			node.rightChild = insert(node.rightChild,key,value);
		}else{
			node.leftChild = insert(node.leftChild,key,value);
		}
		//���ظ����
		return node;
	}
	private Value search(Node node,Key key){
		if(node==null){
			return null;
		}
		if(key.compareTo(node.key)==0){
			return node.value;
		}else if(key.compareTo(node.key)>=0){
			return search(node.rightChild, key);
		}else{
			return search(node.leftChild, key);
		}
	}
	
	private boolean contain(Node node,Key key){
		if(node == null){
			return false;
		}
		if(key.compareTo(node.key)==0){
			return true;
		}else if(key.compareTo(node.key)>0){
			return contain(node.rightChild,key);
		}else{
			return contain(node.leftChild, key);
		}
		
	}
	
	//������nodeΪ���Ķ���������С���
	private Node minimum(Node node){
		if(node.leftChild!=null){
			return minimum(node.leftChild);
		}
		return node;
	}
	
	//������nodeΪ���Ķ����������ڵ�
	private Node maxmum(Node node){
		if(node.rightChild!=null){
			return maxmum(node.rightChild);
		}
		return node;
	}
	
	//ɾ����Сֵ��㣬����ɾ��������ĸ����
	private Node removeMin(Node node){
		if(node.leftChild==null){//�ҵ���Сֵ��㣬��Ϊnode			
				Node rightChild = node.rightChild;//���������������Ƿ�Ϊ�գ�������
				//ɾ��node���
				node.rightChild = null;
				count--;
				return rightChild;			
		}
		node.leftChild = removeMin(node.leftChild);
		return node;
	}
	
	private Node delete(Node node,Key key){
		//���nodeΪ�ս�㣬ֱ�ӷ��ؿ�
		if(node==null){
			return null;
		}
		//���Ҽ�ֵΪkey�Ľ��
		if(key.compareTo(node.key)==0){
			//��ɾ�����������Ϊ�յ����
			if(node.leftChild==null){
				Node rightNode = node.rightChild;//��¼һ���ҽ������
				//ɾ����㣬���ҽ����Ϊ��
				node.rightChild = null;
				count--;//��������һ
				//�������������ĸ����
				return rightNode;
			}
			//��ɾ�����������Ϊ�յ����
			if(node.rightChild==null){
				Node leftNode = node.leftChild;
				node.leftChild = null;
				count--;
				return leftNode;
			}
			//��ɾ�����������������Ϊ�յ����
				//1���ҵ���ɾ�����ĺ�̽ڵ��������λ�ã�����Ϊ������������С���
			Node successor = new Node(minimum(node.rightChild));
			count++;
				//2���ҵ���ɾ������������Ϊ�µĸ��������ӣ���ǰ�����Ϊɾ���������������ĸ����
			successor.leftChild = node.leftChild;
			successor.rightChild = removeMin(node.rightChild);
				//3,ɾ��node���
			node.leftChild = node.rightChild = null;
			count--;
			return successor;
			          
		}else if(key.compareTo(node.key)>0){
			node.rightChild = delete(node.rightChild,key);
			return node;//���շ���node���
		}else{//key<node.key
			node.leftChild = delete(node.leftChild,key);
			return node;
		}
		
	}
	
	//���캯��������һ�ſյĶ�����
	public BinarySearch(){
		root = null;
		count = 0;
	}
	
	//���ض����������Ľڵ�ĸ���
	public int size(){
		return count;
	}
	
	//�ж��Ƿ�Ϊ�յĶ�����
	public boolean isEmpty(){
		return count == 0;
	}
	
	//�ݹ��ʵ�ֲ���ڵ���������ض������ĸ����
	public void insert(Key key,Value value){
		//�ݹ�Ĳ���ڵ㣬��ʼ���������ĸ���㴫�ݣ����ز���Ķ������ĸ����
		root = insert(root,key,value);
	}
	
	//��ӡ�������Ľڵ�
	public void InOrder(Node root){
		if(root!=null){
			InOrder(root.leftChild);
			System.out.print(root.key+",");
			InOrder(root.rightChild);
		}		
	}
	
	//���Ҹ����
	public Node getRoot(){
		return root;
	}
	
	//����ָ����ֵ�Ľ��
	public Value search(Key key){
		return search(root,key);
	}
	
	//�Ƿ��������ֵ�Ľ��
	public boolean contain(Key key){
		return contain(root,key);
	}
	
	//ɾ�����
	public void delete(Key key){
		//ͨ���ݹ鷽ʽɺ����㣬����ɾ��������ĸ����,�������ĸ����
		root = delete(root,key);
	}
	
}
