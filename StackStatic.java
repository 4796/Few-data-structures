package Stack;

public class StackStaticki implements Stack {

	int brojElemenata=0;
	int[] niz;
	public StackStaticki(int z) {
		niz=new int[z];
	}
	public StackStaticki() {
		this(3);
	}
	@Override
	public void push(int z) throws Exception {
		if(this.isFull())
			throw new Exception("Niz je vec pun! Ne moze se uneti "+z);
		niz[brojElemenata]=z;
		brojElemenata++;

	}

	@Override
	public int pop() throws Exception {
		if(this.isEmpty())
			throw new Exception("Stack je prazan, ne moze da se procita.");
		brojElemenata--;
		return niz[brojElemenata];
		
	}

	@Override
	public int readFirst() throws Exception {
		if(this.isEmpty())
			throw new Exception("Stack je prazan, ne moze da se procita.");
		System.out.println(niz[brojElemenata-1]);
		return niz[brojElemenata-1];
	}

	@Override
	public void readAll() {
		for(int i=brojElemenata-1; i>=0;i--) {
			System.out.println(niz[i]);
		}

	}

	@Override
	public Boolean isFull() {
		return brojElemenata==niz.length;
		
	}

	@Override
	public Boolean isEmpty() {
		return brojElemenata==0;
	}

}
