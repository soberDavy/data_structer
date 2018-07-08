package bineary_search_tree;

public class main {

	public static void main(String[] args) {
		BinarySearch<Integer, String> bst = new BinarySearch<Integer, String>();
		bst.insert(28, "根结点");
		bst.insert(16, "第一个插入结点");
		bst.insert(30, "第二个插入结点");
		bst.insert(10, "第三个插入结点");
		bst.insert(26, "第四个插入结点");
		bst.insert(40, "第四个插入结点");
	
		bst.delete(16);
		
		bst.InOrder(bst.getRoot());
		
		

	}

}
