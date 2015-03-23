package com.interview.exam.linkedList;

import java.util.Hashtable;
import java.util.Stack;

public class LinkedListNode {
	LinkedListNode next = null;
	int data;
	
	public LinkedListNode(int d) {
		data = d;
	}
	
	void appendToTail(int d) {
		LinkedListNode end = new LinkedListNode(d);
		LinkedListNode n = this;
		
		while( n.next != null) {
			n = n.next;
		}
		
		n.next = end;
	}
	
	LinkedListNode deleteNode(LinkedListNode head, int d) {
		LinkedListNode n = head;
		
		if(n.data == d) {
			return head.next; /*head �� ����ȴ� */
		}
		
		while( n.next != null) {
			if(n.next.data == d) {
				n.next = n.next.next;
				return head;
			}
			n = n.next;
		}
		
		return head;
	}
	
	/**
	 * ���۰� ������
	 * @param n
	 */
	public static void deleteDups(LinkedListNode n) {
		Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();
		LinkedListNode previous = null;
		while(n != null) {
			if(table.contains(n.data)) {
				previous.next = n.next;
			} else {
				table.put(n.data, true);
				previous = n;
			}
			n = n.next;
		}
	}
	
	public static void deleteDupsNoBuf(LinkedListNode head) {
		if(head == null) return;
		
		LinkedListNode current = head;
		while(current != null) {
			/* ���� ���� ���� ���� ������ ���� */
			LinkedListNode runner = current;
			while( runner.next != null) {
				if(runner.next.data == current.data) {
					runner.next = runner.next.next;
				}else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	public static int nthToLast(LinkedListNode node , int k ) {
		if(node == null)
			return 0;
		int count = nthToLast(node.next, k) + 1;
		
		if(count == k)
			System.out.println(node.data);
		
		return count;
	}
	
	LinkedListNode nthToLastR2(LinkedListNode head, int k, IntWrapper i) {
		if ( head == null) {
			return null;
		}
		
		nthToLastR2(head.next, k, i);
		i.value = i.value + 1;
		if ( i.value == k) {
			return head;
		}
		return head;
	}
	
	LinkedListNode nthToLastIt(LinkedListNode head, int k) {
		if ( k <= 0) return null;
			LinkedListNode p1 = head;
			LinkedListNode p2 = head;
			
			// p2�� ����Ʈ �������� k�� �̵���Ų��.
			for(int i = 0 ; i < k - 1; i++) {
				if( p2 == null ) return null;
				p2 = p2.next;
			}
			
			if(p2 == null) return null;
			
			/* ���� p1�� p2�� ���� �ӵ��� �����δ�. p2�� ������ ��忡 �����ϸ�,
			 * p1�� ����Ű�� ��尡 ���̴�. */
			
			while (p2.next != null) {
				p1 = p1.next;
				p2 = p2.next;
			}
			
			return p1;
	}
	
	/**
	 * �ܹ��� ���� ����Ʈ�� �߰��� �ִ� ��� �ϳ��� �����ϴ� �Լ�
	 * ������ ��忡 ���� ���ٸ� ������ ���
	 * @param n
	 * @return
	 */
	public static boolean deleteNode(LinkedListNode n) {
		if (n == null  || n.next == null) {
			return false; // Failure
		}
		LinkedListNode next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}
	
	public LinkedListNode partition(LinkedListNode node, int x) {
		LinkedListNode beforeStart = null;
		LinkedListNode beforeEnd = null;
		LinkedListNode afterStrat = null;
		LinkedListNode afterEnd = null;
		
		/* ����Ʈ ���� */
		
		while( node != null) {
			LinkedListNode next = node.next;
			node.next = null;
			if(node.data < x) {
				if(beforeStart == null) {
					beforeStart = node;
					beforeEnd = node;
				}else {
					beforeStart.next = node;
					beforeEnd = node;
				}
			}else {
				if(afterStrat == null) {
					afterStrat = node;
					afterEnd = node;
				}else {
					afterStrat.next = node;
					afterEnd = node;
				}
			}
			
			node = next;
		}
		
		if( beforeStart == null) {
			return afterStrat;
		}
		
		beforeEnd.next = afterStrat;
		return beforeStart;
	}
	
	/**
	 * ���� ����Ʈ�� ǥ���� �� ���� ���� �ִٰ� ����. ����Ʈ�� �� ���� �ش� ���� �� �ڸ����� ����Ʈ�� �� �տ�
	 *  ������ �迭�ȴٴ� ���̴�. �� �μ��� ���Ͽ� �� ���� ���� ����Ʈ�� ��ȯ�ϴ� �Լ��� �ۼ��϶�.
	 */
	
	LinkedListNode addList(LinkedListNode node1, LinkedListNode node2, int carry) {
		if( node1 == null && node2 == null && carry == 0) {
			return null;
		}
		
		int value = carry;
		
		if(node1 != null) {
			value += node1.data;
		}
		
		if(node2 != null) {
			value += node2.data;
		}
		
		LinkedListNode head = new LinkedListNode(value % 10);
		
		head.next = addList( node1 == null? null : node1.next, node2 == null ? null : node2.next , value / 10);
		
		return head;
	}
	
	/**
	 * �־��� ���Ḯ��Ʈ�� ȸ������ �˻��ϴ� �Լ��� �ۼ��϶�
	 */
	boolean isPalindrome(LinkedListNode head) {
		LinkedListNode fast = head;
		LinkedListNode slow = head;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		while(fast != null && fast.next != null) {
			stack.push(slow.data);
			fast = fast.next.next;
			slow = slow.next;
		}
		
		if( fast != null) {
			slow = slow.next;
		}
		
		while( slow != null) {
			if( stack.pop() != slow.data )
				return false;
			
			slow = slow.next;
		}
		
		return true;
	}
	
}

class IntWrapper{
	public int value = 0;
}

class PartialSum {
	public LinkedListNode sum = null;
	public int carry = 0;
}



