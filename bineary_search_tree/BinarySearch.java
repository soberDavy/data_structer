package bineary_search_tree;


/**
 * 递归的二分查找法
 * @author ShiZhiWei
 *	key 需要进行比较
 */
public class BinarySearch<Key extends Comparable<Key>,Value> {
	
	//node节点的数据结构
	private class Node{
		private Key key;//结点的索引
		private Value value;//节点的值
		private Node leftChild;//节点的左孩子
		private Node rightChild;//节点的右孩子
		
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
	
	
	private Node root;//根节点
	private int count;//树中的节点个数
	
	private Node insert(Node node,Key key,Value value){
		//如果为空树,直接将新的节点作为根节点返回
		if(node==null){
			count++;//结点个数加1
			return new Node(key,value);
		}
		
		//根据键值大小进行递归插入	
		if(key.compareTo(node.key)==0){//键值相等，直接覆盖
			node.value = value;
		}else if(key.compareTo(node.key)>0){
			node.rightChild = insert(node.rightChild,key,value);
		}else{
			node.leftChild = insert(node.leftChild,key,value);
		}
		//返回根结点
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
	
	//返回以node为根的二叉树的最小结点
	private Node minimum(Node node){
		if(node.leftChild!=null){
			return minimum(node.leftChild);
		}
		return node;
	}
	
	//返回以node为根的二叉树的最大节点
	private Node maxmum(Node node){
		if(node.rightChild!=null){
			return maxmum(node.rightChild);
		}
		return node;
	}
	
	//删除最小值结点，返回删除后的树的根结点
	private Node removeMin(Node node){
		if(node.leftChild==null){//找到最小值结点，即为node			
				Node rightChild = node.rightChild;//不论右子树内容是否为空，都适用
				//删除node结点
				node.rightChild = null;
				count--;
				return rightChild;			
		}
		node.leftChild = removeMin(node.leftChild);
		return node;
	}
	
	private Node delete(Node node,Key key){
		//如果node为空结点，直接返回空
		if(node==null){
			return null;
		}
		//查找键值为key的结点
		if(key.compareTo(node.key)==0){
			//待删除结点左子树为空的情况
			if(node.leftChild==null){
				Node rightNode = node.rightChild;//记录一下右结点的情况
				//删除结点，即右结点设为空
				node.rightChild = null;
				count--;//结点个数减一
				//返回其右子树的根结点
				return rightNode;
			}
			//待删除结点右子树为空的情况
			if(node.rightChild==null){
				Node leftNode = node.leftChild;
				node.leftChild = null;
				count--;
				return leftNode;
			}
			//待删除结点左右子树都不为空的情况
				//1，找到待删除结点的后继节点代替它的位置，其后继为其右子树的最小结点
			Node successor = new Node(minimum(node.rightChild));
			count++;
				//2，找到待删除结点的左孩子作为新的根结点的左孩子，其前驱结点为删除上述结点的子树的根结点
			successor.leftChild = node.leftChild;
			successor.rightChild = removeMin(node.rightChild);
				//3,删除node结点
			node.leftChild = node.rightChild = null;
			count--;
			return successor;
			          
		}else if(key.compareTo(node.key)>0){
			node.rightChild = delete(node.rightChild,key);
			return node;//最终返回node结点
		}else{//key<node.key
			node.leftChild = delete(node.leftChild,key);
			return node;
		}
		
	}
	
	//构造函数，构造一颗空的二叉树
	public BinarySearch(){
		root = null;
		count = 0;
	}
	
	//返回二叉搜索树的节点的个数
	public int size(){
		return count;
	}
	
	//判断是否为空的二叉树
	public boolean isEmpty(){
		return count == 0;
	}
	
	//递归的实现插入节点操作，返回二叉树的根结点
	public void insert(Key key,Value value){
		//递归的插入节点，初始将二叉树的根结点传递，返回插入的二叉树的根结点
		root = insert(root,key,value);
	}
	
	//打印二叉树的节点
	public void InOrder(Node root){
		if(root!=null){
			InOrder(root.leftChild);
			System.out.print(root.key+",");
			InOrder(root.rightChild);
		}		
	}
	
	//查找根结点
	public Node getRoot(){
		return root;
	}
	
	//查找指定键值的结点
	public Value search(Key key){
		return search(root,key);
	}
	
	//是否包含给定值的结点
	public boolean contain(Key key){
		return contain(root,key);
	}
	
	//删除结点
	public void delete(Key key){
		//通过递归方式珊瑚结点，返回删除后的树的根结点,传入树的根结点
		root = delete(root,key);
	}
	
}
