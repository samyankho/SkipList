import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;


public class SkipList<E> implements List<E> {
	
	static final int PossibleLevels = 33;
	public SkipListEntry<E> head;	// First element of the top level
	public SkipListEntry<E> tail;	// Last element of the top level
	SkipListEntry<E>[] last;
	int[] spans; 
	
	public int n, maxLevel;		// number of entries in the Skip List
	public int level;		// Height
	
	public Random r;	// Coin toss
	private static final double PROBABILITY=0.5;
	E element;
	
	public SkipList() {
		SkipListEntry<E> p1, p2;
		
		// 创建一个 -oo 和一个 +oo 对象
		p1 = new SkipListEntry<E>(SkipListEntry.negInf, null);
		p2 = new SkipListEntry<E>(SkipListEntry.posInf, null);
		
		// 将 -oo 和 +oo 相互连接
		p1.right = p2;
		p2.left = p1;
		
		// 给 head 和 tail 初始化
		head = p1;
		tail = p2;
		
		n = 0;
		level = 0;
		r = new Random();
	}
	
	
	
	private SkipListEntry<E> findEntry(int index) {
		
		SkipListEntry<E> p;
		p = head;
		int leftRankSum = 0;
		while(true) {
			if (leftRankSum + p.getKey() < index + 1) {
				leftRankSum += p.getKey();
				p = p.right;
			}
			if(p.down != null) {
				p = p.down;
			} else {
				break;
			}
		}
		return p;
	}
	
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}
	
	public E last() {
		if (tail.prev.getValue() == null)
			return null;
		else
			return tail.prev.getValue();
	}
	
	public Integer chooseLevel() {
		Integer lev = 1 + Integer.numberOfTrailingZeros(r.nextInt());
		if (lev > maxLevel) {
			maxLevel = lev;
		}
		return lev;
	}

	public void find(E x) {
        Comparable<E> ce = (Comparable<E>)x;
		SkipListEntry<E> temp = head;
		int i = maxLevel - 1;
		int span = 0;
		while (i >= 0) {
			// int span = temp.span[i];
			while (temp.next[i] != null && temp.next[i].value != null && ce.compareTo(temp.next[i].value) > 0) {
				span += temp.span[i] + 1;
				temp = temp.next[i];
			}
			last[i] = temp;
			// Total distance between nodes when jumping levels
			spans[i] = span;
			i--;
		}
	}

	
	@Override
	public boolean add(E e) {
		add(size(), e);
		return true;
//		int level = chooseLevel();
//
//		// Exit if already exist
//		if (contains(x))
//			return false;
//
//		SkipListEntry<E> ent = new SkipListEntry<E>(level, x);
//		for (int i = 0; i < level; i++) {
//			boolean isTailNode = last[i].next[i].equals(tail);
//			ent.next[i] = last[i].next[i];
//			ent.span[i] = isTailNode ? n - spans[0] + spans[i] : spans[i] + last[i].span[i] - spans[0];
//			last[i].next[i] = ent;
//			last[i].span[i] = spans[0] - spans[i];
//			// System.out.println("ent: " + ent.span[i]);
//		}
//		ent.next[0].prev = ent;
//		ent.prev = last[0];
//		n += 1;
//
//		for (int i = level; i < PossibleLevels; i++) {
//			if (i < maxLevel) {
//				last[i].span[i] += 1;
//			} else {
//				head.span[i] += 1;
//			}
//		}
//
//		return true;
	}
	public E next() {
		if (head.next == null)
			throw new NoSuchElementException();
		head = head.next[0];
//		ready = true;
		return head.value;
	}

	
	private int chooseLevel(int level) {
        long n = (long)1 << (level - 1);
        long ranNum;
        if (level < 32) {
            ranNum = r.nextInt((int) n) + 1; //[1, n]
        } else {
            //int overflow, use long for calculation
            ranNum = r.nextLong();
            if (ranNum < 0)
                ranNum = (ranNum + Long.MAX_VALUE) % n + 1; // [1, n]
            else if (ranNum > n)
                ranNum = ranNum % n + 1;  // [1, n]
        }
        int ranLevel = 1;
        while (true) {
            n >>= 1;
            if (ranNum > n)
                return ranLevel;
            ranLevel++;
        }
    }
	
//	public void find(E x) {
//		SkipListEntry<E> p = head;
//
//        for (int i = maxLevel - 1; i >= 0; i--) {
//            int nodesSkipped = 0;
//            while (p.next[i] != null && ((Comparable<? super E>) p.next[i].getElement()).compareTo(x) < 0) {
//                nodesSkipped += p.span[i] + 1;
//                p = p.next[i];
//            }
//            last[i] = p;
//            skipped[i] = nodesSkipped;
//        }
//    }



	@Override
	public E get(int index) {
		SkipListEntry<E> p;
		
		p = findEntry(index);
		
		if(p.key==index) {
			return p.value;
		} else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "empty list";
		}
		StringBuilder builder = new StringBuilder();
		SkipListEntry<E> p = head;
		while (p.down != null) {
			p = p.down;
		}
		while (p.left != null) {
			p = p.left;
		}
		if (p.right != null) {
			p = p.right;
		}
		while (p.right != null) {
			builder.append(p);
			builder.append("\n");
			p = p.right;
		}
		return builder.toString();
	}
	
	@Override
	public void add(int index, E element) {
		SkipListEntry<E> t = findEntry(index);
		if(index == t.key) {
			t.setvalue(element);
			return;
		}
		SkipListEntry<E> add = new SkipListEntry<E>(index, element);
		int clevel = 0;
		backLink(add, t);
		while(r.nextDouble() < PROBABILITY) {
			if(clevel >= level) {
				level++;
				SkipListEntry<E> p1 = new SkipListEntry<E>(SkipListEntry.posInf, null);
				SkipListEntry<E> p2 = new SkipListEntry<E>(SkipListEntry.negInf, null);
				horizontalLink(p1, p2);
				vertiacallLink(p1, head);
				vertiacallLink(p2, tail);
				head = p1;
				tail = p2;
			}
			while(t.up == null) {
				t = t.left;
			}
			t = t.up;
			SkipListEntry<E> key = new SkipListEntry<E>(index, null);
			horizontalLink(t, key);
			vertiacallLink(key, add);
			add = key;
			clevel++;
		}
		n++;
	}
	
	public void printSkipList() {
		SkipListEntry p = head;
		while(p!=null) {
			SkipListEntry q = p;
			while(q!=null) {
				System.out.print(String.valueOf(String.valueOf(q.getKey()) + ":" + q.getValue()) + " ");
				q = q.right;
			}
			System.out.println();
			p = p.down;
		}
		System.out.print("\n");
	}

	//node1后面插入node2
    private void backLink(SkipListEntry<E> node1,SkipListEntry<E> node2){
    	if(node1!= null&& node2!=null) {
        node2.left=node1;
        node2.right=node1.right;
        node1.right.left=node2;
        node1.right=node2;
    }
    }
    
    /**
     * 水平双向连接
     * */
    private void horizontalLink(SkipListEntry<E> node1,SkipListEntry<E> node2){
        node1.right=node2;
        node2.left=node1;
    }
    /**
     * 垂直双向连接
     * */
    private void vertiacallLink(SkipListEntry<E> node1,SkipListEntry<E> node2){
        node1.down=node2;
        node2.up=node1;
    }

	
	
	@Override
	public boolean isEmpty() {
		if (n <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		 return head.contains((E) o);
		}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}


	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}


	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	

	

}
