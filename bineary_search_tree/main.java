package bineary_search_tree;

public class main {

	public static void main(String[] args) {
		BinarySearch<Integer, String> bst = new BinarySearch<Integer, String>();
		bst.insert(28, "�����");
		bst.insert(16, "��һ��������");
		bst.insert(30, "�ڶ���������");
		bst.insert(10, "������������");
		bst.insert(26, "���ĸ�������");
		bst.insert(40, "���ĸ�������");
	
		bst.delete(16);
		
		bst.InOrder(bst.getRoot());
		
		

	}

}
