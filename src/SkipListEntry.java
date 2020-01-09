

	public class SkipListEntry<E> {
		
		// data
		public int key;
		public E value;
		SkipListEntry<E>[] next;
		SkipListEntry<E> prev;
		int span[];
		
		// links
		public SkipListEntry<E> up;
		public SkipListEntry<E> down;
		public SkipListEntry<E> left;
		public SkipListEntry<E> right;
		
		// special
		public static final int negInf = Integer.MIN_VALUE;
		public static final int posInf = Integer.MAX_VALUE;
		
		// constructor
		public SkipListEntry(int key, E value) {
			this.key = key;
			this.value = value;
		}

		
		public int getKey() {
	        return key;
	    }
	    public void setKey(int key) {
	        this.key = key;
	    }
	    public E getValue() {
	        return value;
	    }

		
		public void setvalue(E element) {
			this.value =  element;
		}

		
		public SkipListEntry getUp() {
			return up;
		}

		public void setUp(SkipListEntry up) {
			this.up = up;
		}

		public SkipListEntry getDown() {
			return down;
		}

		public void setDown(SkipListEntry down) {
			this.down = down;
		}

		public SkipListEntry getLeft() {
			return left;
		}

		public void setLeft(SkipListEntry left) {
			this.left = left;
		}

		public SkipListEntry getRight() {
			return right;
		}

		public void setRight(SkipListEntry right) {
			this.right = right;
		}
		
		@Override
		public boolean equals(Object o) {
		        if (this==o) {
		            return true;
		        }
		        if (o==null) {
		            return false;
		        }
		        SkipListEntry<E> ent;
		        try {
		            ent = (SkipListEntry<E>)  o; // 检测类型
		        } catch (ClassCastException ex) {
		            return false;
		        }
		        return (ent.getKey() == key) && (ent.getValue() == value);
		    
		}

		public boolean contains(E o) {
			// TODO Auto-generated method stub
			return false;
		}
	
}
