package com.interview.exam.linkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class LinkedListNode {
	LinkedListNode next = null;
	int data;
	
	public LinkedListNode(int d) {
		data = d;
	}
	
	void appendToTail(int d) {
		LinkedListNode end = new LinkedListNode(d);
		LinkedListNode n = this;
		
		while( n . next != null) {
			n = n.next;
		}
		
		n.next = end;
	}
	
	LinkedListNode deleteNode(LinkedListNode head, int d) {
		LinkedListNode n = head;
		
		if(n.data == d) {
			return head.next; /*head 가 변경된다 */
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
	 * 버퍼가 있을시
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
			/* 같은 값을 갖는 이후 노드들을 제거 */
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
	
}
